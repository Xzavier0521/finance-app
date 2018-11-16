package finance.ext.task;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import finance.domainservice.service.activity.RedEnvelopeRainRankingSyncService;
import org.springframework.stereotype.Service;

/**
 * <p>红包雨活动定时任务</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainTask.java, v0.1 2018/11/17 2:10 AM PM lili Exp $
 */
@Slf4j
@Service
public class RedEnvelopeRainTask implements SchedulingConfigurer,
                                 ApplicationListener<ApplicationReadyEvent> {

    @Value("${leaderBoard.job.syncData}")
    private String                            cron;

    @Resource
    private RedEnvelopeRainRankingSyncService redEnvelopeRainRankingSyncService;

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
       redEnvelopeRainRankingSyncService.process();
    }

    /**
     * Callback allowing a {@link TaskScheduler
     * TaskScheduler} and specific {@link Task Task}
     * instances to be registered against the given the {@link ScheduledTaskRegistrar}
     *
     * @param taskRegistrar the registrar to be configured.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            log.info("[开始同步红包雨活动-排行榜数据]，当前时间：{}", LocalDateTime.now());
            redEnvelopeRainRankingSyncService.process();
            log.info("[结束同步红包雨活动-排行榜数据]，当前时间：{}", LocalDateTime.now());
        }, triggerContext -> {
            // 定时任务触发，可修改定时任务的执行周期 this.corn
            CronTrigger trigger = new CronTrigger(cron);
            return trigger.nextExecutionTime(triggerContext);
        });
    }
}
