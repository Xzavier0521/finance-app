package cn.zhishush.finance.ext.task.weChat;

import javax.annotation.Resource;

import cn.zhishush.finance.domainservice.service.wechat.WeChatDataSynchronizeService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;

/**
 * <p>微信公众号用户数据同步到redis</p>
 * 
 * @author lili
 * @version $Id: WeChatDataSynchronizeTask.java, v0.1 2018/10/23 5:45 PM lili Exp $
 */
@Slf4j
@Service
public class WeChatDataSynchronizeTask implements SchedulingConfigurer {

    @Value("${weChat.job.syncData}")
    private String                       cron;

    @Resource
    private WeChatDataSynchronizeService weChatDataSynchronizeService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        /*
         * taskRegistrar.addTriggerTask(() -> { log.info("[开始从微信同步关注的用户数据]，当前时间：{}",
         * LocalDateTime.now()); weChatDataSynchronizeService.process();
         * log.info("[结束从微信同步关注的用户数据]，当前时间：{}", LocalDateTime.now()); }, triggerContext
         * -> { // 定时任务触发，可修改定时任务的执行周期 this.corn CronTrigger trigger = new
         * CronTrigger(cron); return trigger.nextExecutionTime(triggerContext); });
         */
    }
}
