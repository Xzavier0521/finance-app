package finance.ext.task;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import finance.domainservice.service.activity.LeaderBoardSynchronizeService;

/**
 * <p>排行榜定时任务</p>
 * @author lili
 * @version $Id: LeaderBoardSynchronizeTask.java, v0.1 2018/10/25 5:13 PM lili Exp $
 */
@Slf4j
//@Service
public class LeaderBoardSynchronizeTask implements SchedulingConfigurer,
                                        ApplicationListener<ApplicationReadyEvent> {

    @Value("${leaderBoard.job.syncData}")
    private String                        cron;

    @Resource
    private LeaderBoardSynchronizeService leaderBoardSynchronizeService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        leaderBoardSynchronizeService.process();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            log.info("[开始同步排行榜数据]，当前时间：{}", LocalDateTime.now());
            leaderBoardSynchronizeService.process();
            log.info("[结束同步排行榜数据]，当前时间：{}", LocalDateTime.now());
        }, triggerContext -> {
            // 定时任务触发，可修改定时任务的执行周期 this.corn
            CronTrigger trigger = new CronTrigger(cron);
            return trigger.nextExecutionTime(triggerContext);
        });
    }
}
