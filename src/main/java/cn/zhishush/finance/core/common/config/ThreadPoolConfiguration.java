package cn.zhishush.finance.core.common.config;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>线程池配置</p>
 * 
 * @author lili
 * @version $Id: ThreadPoolConfiguration.java, v0.1 2018/10/31 11:37 PM lili Exp$
 */
@Slf4j
@Configuration
public class ThreadPoolConfiguration {

    /**
     * 最大线程数
     */
    @Value("${finance.threadPool.max}")
    private int maximumPoolSize;
    /**
     * 最小线程数
     */
    @Value("${finance.threadPool.min}")
    private int corePoolSize;

    @Bean("threadPoolExecutor")
    public ThreadPoolExecutor getExecutorPool() {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), r -> {
                Thread thread = new Thread(r);
                thread.setName("fiance-app-pool");
                return thread;
            });
    }
}
