package cn.zhishush.finance.ext.task.weChat;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import cn.zhishush.finance.domainservice.service.wechat.WeChatInviteInfoSynchronizeService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

/**
 * <p>微信用户邀请关系数据同步</p>
 *
 * @author lili
 * @version 1.0: WeChatInviteInfoSynchronizeTask.java, v0.1 2018/12/3 6:24 PM PM lili Exp $
 */
@Slf4j
@Service
public class WeChatInviteInfoSynchronizeTask implements SchedulingConfigurer,
                                             ApplicationListener<ApplicationReadyEvent> {

    @Resource
    private WeChatInviteInfoSynchronizeService weChatInviteInfoSynchronizeService;

    @Value("${weChat.inviteInfo.sync}")
    private String                             cron;

    @Value("${weChat.inviteInfo.sync.switch}")
    private String                             syncSwitch;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            log.info("[开始同步微信公众号数据]，当前时间：{}", LocalDateTime.now());
            if ("1".equalsIgnoreCase(syncSwitch)) {
                weChatInviteInfoSynchronizeService.process();
            }
            log.info("[结束同步微信公众号数据]，当前时间：{}", LocalDateTime.now());
        }, triggerContext -> {
            // 定时任务触发，可修改定时任务的执行周期 this.corn
            CronTrigger trigger = new CronTrigger(cron);
            return trigger.nextExecutionTime(triggerContext);
        });
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("[开始同步微信公众号数据]，当前时间：{}", LocalDateTime.now());
        if ("1".equalsIgnoreCase(syncSwitch)) {
            weChatInviteInfoSynchronizeService.process();
        }
        log.info("[结束同步微信公众号数据]，当前时间：{}", LocalDateTime.now());
    }
}
