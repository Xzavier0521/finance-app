package finance.ext.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import finance.domainservice.service.activity.RedEnvelopeRainRankingRewardService;

/**
 * <p>红包雨活动-排行榜奖励定时任务</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainRankingRewardTask.java, v0.1 2018/11/17 6:20 AM PM lili Exp $
 */
@Slf4j
@Service
public class RedEnvelopeRainRankingRewardTask implements SchedulingConfigurer {

    @Value("${redEnvelopeRain.job.reward}")
    private String                              cron;

    @Resource
    private RedEnvelopeRainRankingRewardService redEnvelopeRainRankingRewardService;

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

            log.info("[开始处理{}日红包雨活动-排行榜奖励]，当前时间：{}",
                LocalDate.now().plusDays(-1).format(DateTimeFormatter.ISO_DATE),
                LocalDateTime.now());
            redEnvelopeRainRankingRewardService.process(LocalDate.now().plusDays(-1), "1001");
            log.info("[结束处理}日红包雨活动-排行榜奖励]，当前时间：{}",
                LocalDate.now().plusDays(-1).format(DateTimeFormatter.ISO_DATE),
                LocalDateTime.now());
        }, triggerContext -> {
            // 定时任务触发，可修改定时任务的执行周期 this.corn
            CronTrigger trigger = new CronTrigger(cron);
            return trigger.nextExecutionTime(triggerContext);
        });
    }
}
