package finance.domainservice.service.email;

/**
 * <p>邮件发送</p>
 *
 * @author lili
 * @version 1.0: EmailService.java, v0.1 2018/11/14 10:12 AM PM lili Exp $
 */
public interface EmailService {

    /**
     * 告警邮件发送
     * @param title 主题
     * @param content 内容
     */
    void sendAlarmMail(String title, String content);
}
