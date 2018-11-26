package finance.domainservice.service.email.impl;

import java.net.InetAddress;
import java.text.MessageFormat;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import finance.domainservice.service.email.EmailService;

/**
 * <p>
 * 邮件发送
 * </p>
 *
 * @author lili
 * @version 1.0: EmailServiceImpl.java, v0.1 2018/11/14 10:13 AM PM lili Exp $
 */
@Slf4j
@Service("EmailService")
public class EmailServiceImpl implements EmailService {

	@Resource
	private JavaMailSender mailSender;
	@Value("${spring.mail.username}")
	private String emailFrom;
	@Value("${spring.mail.alarm.sendto}")
	private String emailSendTo;

	/**
	 * 告警邮件发送
	 *
	 * @param title
	 *            主题
	 * @param content
	 *            内容
	 */
	@Async
	@Override
	public void sendAlarmMail(String title, String content) {
		try {
			String[] sendTo = this.emailSendTo.split(",");
			if (sendTo.length == 0) {
				return;
			}
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(this.emailFrom);
			message.setTo(sendTo);
			message.setSubject(title);
			message.setText(
					MessageFormat.format("from:{0};<br>内容：{1}", InetAddress.getLocalHost().getHostAddress(), content));
			this.mailSender.send(message);
		} catch (final Exception e) {
			log.warn("告警邮件发送失败,{}", ExceptionUtils.getStackTrace(e));
		}

	}
}
