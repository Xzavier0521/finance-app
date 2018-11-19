package finance.ext.task;

import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import finance.domainservice.service.activity.RedEnvelopeRainRankingRewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainRankingSecondRewardTask.java, v0.1 2018/11/19 5:36 PM PM lili Exp $
 */
@Slf4j
@Service
public class RedEnvelopeRainRankingSecondRewardTask implements SchedulingConfigurer {

    @Value("${redEnvelopeRain.job.firstReward}")
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

            RedEnvelopeRainTimeCodeEnum timeCode = RedEnvelopeRainTimeCodeEnum.FIRST;
            log.info("[开始处理{}日红包雨活动-排行榜奖励]，当前时间：{}",
                    LocalDate.now().plusDays(-1).format(DateTimeFormatter.ISO_DATE),
                    LocalDateTime.now());
            redEnvelopeRainRankingRewardService.process(LocalDate.now(), "1001", timeCode);
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
