package finance.ext.task;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import finance.domainservice.service.activity.RedEnvelopeRainNoticeService;

/**
 * <p>红包雨活动微信模版消息通知</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainNoticeTask.java, v0.1 2018/11/20 6:34 PM PM lili Exp $
 */
@Slf4j
@Service
public class RedEnvelopeRainNoticeTask implements SchedulingConfigurer {

    @Resource
    private RedEnvelopeRainNoticeService redEnvelopeRainNoticeService;

    @Resource
    private ThreadPoolExecutor           threadPoolExecutor;
    @Value("${redEnvelopeRain.job.notice}")
    private String                       cron;

    /**
     * Callback allowing a {@link TaskScheduler
     * TaskScheduler} and specific {@link Task Task}
     * instances to be registered against the given the {@link ScheduledTaskRegistrar}
     *
     * @param taskRegistrar the registrar to be configured.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    /*    taskRegistrar.addTriggerTask(() -> {
            log.info("[开始同步红包雨活动-消息推送]，当前时间：{}", LocalDateTime.now());
            redEnvelopeRainNoticeService.process();
            log.info("[结束同步红包雨活动-消息推送]，当前时间：{}", LocalDateTime.now());
        }, triggerContext -> {
            // 定时任务触发，可修改定时任务的执行周期 this.corn
            CronTrigger trigger = new CronTrigger(cron);
            return trigger.nextExecutionTime(triggerContext);
        });*/
    }
}
