package finance.ext.integration.config;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: HttpLoggingInterceptor.java, v0.1 2018/10/21 7:14 PM lili Exp $
 */
@Slf4j
public final class HttpLoggingInterceptor implements Interceptor {

    /**
     * 编码
     */
    private static final Charset UTF8  = Charset.forName("UTF-8");
    private volatile Level       level = Level.NONE;

    private static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = (buffer.size() < 64) ? buffer.size() : 64;

            buffer.copyTo(prefix, 0, byteCount);

            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }

                int codePoint = prefix.readUtf8CodePoint();

                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }

            return true;
        } catch (EOFException e) {
            return false;
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");

        return (contentEncoding != null) && !contentEncoding.equalsIgnoreCase("identity");
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Level level = this.level;
        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }

        boolean logBody = level == Level.BODY;
        boolean logHeaders = logBody || (level == Level.HEADERS);
        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;
        Connection connection = chain.connection();
        Protocol protocol = (connection != null) ? connection.protocol() : Protocol.HTTP_1_1;
        String requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' '
                                     + protocol;

        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }

        log.info(requestStartMessage);

        if (logHeaders) {
            if (hasRequestBody) {

                // Request body headers are only present when installed as a network interceptor. Force
                // them to be included (when available) so there values are known.
                if (requestBody.contentType() != null) {
                    log.info("Content-Type: " + requestBody.contentType());
                }

                if (requestBody.contentLength() != -1) {
                    log.info("Content-Length: " + requestBody.contentLength());
                }
            }

            Headers headers = request.headers();

            for (int i = 0, count = headers.size(); i < count; i++) {
                String name = headers.name(i);

                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equalsIgnoreCase(name)
                    && !"Content-Length".equalsIgnoreCase(name)) {
                    log.info(name + ": " + headers.value(i));
                }
            }

            if (!logBody || !hasRequestBody) {
                log.info("--> END " + request.method());
            } else if (bodyEncoded(request.headers())) {
                log.info("--> END " + request.method() + " (encoded body omitted)");
            } else {
                Buffer buffer = new Buffer();

                requestBody.writeTo(buffer);

                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();

                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                log.info("");

                if (isPlaintext(buffer)) {
                    log.info(buffer.readString(charset));
                    log.info("--> END " + request.method() + " (" + requestBody.contentLength()
                             + "-byte body)");
                } else {
                    log.info("--> END " + request.method() + " (binary "
                             + requestBody.contentLength() + "-byte body omitted)");
                }
            }
        }

        long startNs = System.nanoTime();
        Response response;

        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            log.info("<-- HTTP FAILED: " + e);

            throw e;
        }

        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodySize = (contentLength != -1) ? contentLength + "-byte" : "unknown-length";

        //log.info(
        //    "<-- " + response.code() + ' ' + response.message() + ' ' + response.request().url()
        //         + " (" + tookMs + "ms" + (!logHeaders ? ", " + bodySize + " body" : "") + ')');

        if (logHeaders) {
            Headers headers = response.headers();

            for (int i = 0, count = headers.size(); i < count; i++) {
                log.info(headers.name(i) + ": " + headers.value(i));
            }

            if (!logBody || !HttpHeaders.hasBody(response)) {
                log.info("<-- END HTTP");
            } else if (bodyEncoded(response.headers())) {
                log.info("<-- END HTTP (encoded body omitted)");
            } else {
                BufferedSource source = responseBody.source();

                source.request(Long.MAX_VALUE); // Buffer the entire body.

                Buffer buffer = source.buffer();
                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();

                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                if (!isPlaintext(buffer)) {
                    log.info("");
                    log.info("<-- END HTTP (binary " + buffer.size() + "-byte body omitted)");

                    return response;
                }

                if (contentLength != 0) {
                    log.info("");
                    log.info(buffer.clone().readString(charset));
                }

                log.info("<-- END HTTP (" + buffer.size() + "-byte body)");
            }
        }

        return response;
    }

    public Level getLevel() {
        return level;
    }

    /**
     * Change the level at which this interceptor logs.
     */
    public HttpLoggingInterceptor setLevel(Level level) {
        if (level == null) {
            throw new NullPointerException("level == null. Use Level.NONE instead.");
        }

        this.level = level;

        return this;
    }

    public enum Level {

                       /**
                        * No logs.
                        */
                       NONE,

                       /**
                        * Logs request and response lines.
                        * <p>
                        * <p>Example:
                        * <pre>{@code
                        * --> POST /greeting http/1.1 (3-byte body)
                        *
                        * <-- 200 OK (22ms, 6-byte body)
                        * }</pre>
                        */
                       BASIC,

                       /**
                        * Logs request and response lines and their respective headers.
                        * <p>
                        * <p>Example:
                        * <pre>{@code
                        * --> POST /greeting http/1.1
                        * Host: example.com
                        * Content-Type: plain/text
                        * Content-Length: 3
                        * --> END POST
                        *
                        * <-- 200 OK (22ms)
                        * Content-Type: plain/text
                        * Content-Length: 6
                        * <-- END HTTP
                        * }</pre>
                        */
                       HEADERS,

                       /**
                        * Logs request and response lines and their respective headers and bodies (if present).
                        * <p>
                        * <p>Example:
                        * <pre>{@code
                        * --> POST /greeting http/1.1
                        * Host: example.com
                        * Content-Type: plain/text
                        * Content-Length: 3
                        *
                        * Hi?
                        * --> END POST
                        *
                        * <-- 200 OK (22ms)
                        * Content-Type: plain/text
                        * Content-Length: 6
                        *
                        * Hello!
                        * <-- END HTTP
                        * }</pre>
                        */
                       BODY
    }
}