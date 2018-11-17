package finance.ext.task;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainRankingRewardTask.java, v0.1 2018/11/17 6:20 AM PM lili Exp $
 */
@Slf4j
@Service
public class RedEnvelopeRainRankingRewardTask implements SchedulingConfigurer {

    @Value("${redEnvelopeRain.job.syncData}")
    private String cron;
    /**
     * Callback allowing a {@link TaskScheduler
     * TaskScheduler} and specific {@link Task Task}
     * instances to be registered against the given the {@link ScheduledTaskRegistrar}
     *
     * @param taskRegistrar the registrar to be configured.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

    }
}
