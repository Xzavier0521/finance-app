package finance.ext.task.weChat;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import finance.domainservice.service.wechat.CustomerMessageNotificationSendService;

/**
 * <p>客户留言通知</p>
 *
 * @author lili
 * @version 1.0: CustomerMessageNotificationSendTask.java, v0.1 2018/12/4 3:23 PM PM lili Exp $
 */
@Slf4j
@Service
public class CustomerMessageNotificationSendTask implements SchedulingConfigurer {

    @Value("${weChat.custMessageNotice.send}")
    private String                                 cron;
    @Value("${weChat.custMessageNotice.send.switch}")
    private String                                 sendSwitch;

    @Resource
    private CustomerMessageNotificationSendService customerMessageNotificationSendService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            log.info("[开始发送客户留言通知]，当前时间：{}", LocalDateTime.now());
            if ("1".equalsIgnoreCase(sendSwitch)) {
                customerMessageNotificationSendService.process();
            }
            log.info("[结束发送客户留言通知]，当前时间：{}", LocalDateTime.now());
        }, triggerContext -> {
            // 定时任务触发，可修改定时任务的执行周期 this.corn
            CronTrigger trigger = new CronTrigger(cron);
            return trigger.nextExecutionTime(triggerContext);
        });
    }
}
