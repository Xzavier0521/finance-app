package finance.ext.task.appstart;

import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import finance.core.common.util.LogUtil;
import finance.domainservice.service.email.EmailService;
import finance.domainservice.service.wechat.WechatService;

/**
 * <p>
 * app启动之后执行的任务
 * </p>
 * 
 * @author lili
 * @version $Id: AppStartTask.java, v0.1 2018/11/14 10:23 AM lili Exp $
 */
@Slf4j
@Component
public class AppStartTask {

    @Resource
    private WechatService      wechatService;
    @Resource
    private EmailService       emailService;
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 任务启动入口. <br>
     * 将所有需要启动的任务添加到这里</br>
     */
    void start() {
        this.getWeChatPubAccessToken();
    }

    /**
     * 获取weChat access_token
     */
    private void getWeChatPubAccessToken() {

        threadPoolExecutor.execute(() -> {
            while (true) {
                try {
                    int i = 0;
                    String access_token = null;
                    while (StringUtils.isEmpty(access_token) && i < 3) {
                        i++;
                        access_token = wechatService.getWechatPubAccessToken();
                    }
                    Thread.sleep(10 * 60 * 1000);
                } catch (final Exception e) {
                    log.error(LogUtil.getFormatLog(null, "获取access_token失败"), e);
                    // 发送邮件
                    String title = "获取access_token失败";
                    String content = LogUtil.getFormatLog(null, "获取access_token失败") + e;
                    emailService.sendAlarmMail(title, content);
                    break;
                }
            }
        });
    }

}
