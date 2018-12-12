package cn.zhishush.finance.core.common.util;

import static cn.zhishush.finance.core.common.util.ResponseUtils.buildResp;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

import cn.zhishush.finance.api.model.response.BasicResponse;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * <p>线程工具类</p>
 *
 * @author lili
 * @version 0.1: ConcurrentUtils.java, v 0.1 2017/10/18 lili Exp $$
 */
@Slf4j
public class ConcurrentUtils {

    /**
     *  获取异步线程的结果
     * @param completableFutures 完成通知
     * @return BasicResponse
     */
    public static BasicResponse getExecuteResult(List<CompletableFuture<BasicResponse>> completableFutures) {
        BasicResponse response;
        CompletableFuture<BasicResponse>[] futures = completableFutures.stream()
            .toArray((IntFunction<CompletableFuture<BasicResponse>[]>) CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        List<BasicResponse> responseList = completableFutures.stream().map(future -> {
            BasicResponse basicResponse;
            try {
                basicResponse = future.get();
            } catch (final InterruptedException | ExecutionException e) {
                basicResponse = ResponseUtils.buildResp(ReturnCode.SYS_ERROR, e.getMessage());
                log.error("[线程执行异常]:{}", ExceptionUtils.getStackTrace(e));
            }
            return basicResponse;
        }).collect(Collectors.toList());
        boolean flag = responseList.size() == responseList.stream().filter(BasicResponse::isSuccess)
            .collect(Collectors.toList()).size();
        if (flag) {
            response = ResponseUtils.buildResp(ReturnCode.SUCCESS);
        } else {
            response = ResponseUtils.buildResp(ReturnCode.SYS_ERROR,
                responseList.stream().filter(basicResponse -> !basicResponse.isSuccess())
                    .map(BasicResponse::getReturnMessage).collect(Collectors.joining(",")));
        }
        return response;
    }

}
