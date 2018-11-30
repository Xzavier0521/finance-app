package finance.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>登录接口参数</p>
 * 
 * @author lili
 * @version $Id: LoginParamDto.java, v0.1 2018/11/14 4:35 PM lili Exp $
 */
@Slf4j
@Data
public class LoginParamDto implements Serializable {

    private static final long serialVersionUID = 8133562851231464575L;
    /**
     * 登录方式
     */
    @NotBlank(message = "登录方式不能为空")
    private String            type;
    //@NotBlank(message = "活动类型不能为空")
    private String            activityType;
    private String            wechatPubName;
    /**
     * 活动代码
     */
    private String            activityCode;
    private String            openId;

    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空")
    private String            mobileNum;
    /**
     * 短信验证码
     */
    @Size(min = 4, max = 4, message = "手机验证码长度不合法")
    private String            mobileCode;
    private String            realName;
    @Size(min = 32, max = 32, message = "图片验证码ID长度不合法")
    private String            imgCodeId;
    /**
     * 图片验证码
     */
    @Size(min = 4, max = 4, message = "图片验证码不合法")
    private String            imgCode;
    /**
     * 邀请码
     */
    @Size(min = 32, max = 32, message = "邀请码不合法")
    private String            inviteCode;
    private String            channelCode;
    private String            channelDetail;
    /**
     * 平台代码
     */
    // @NotBlank(message = "平台编码不能为空")
    private String            platformCode;
    private String            platformDetail;
    private String            ip;
    private String            userAgent;
    private String            approach1;
    private String            approach2;
    private String            approach3;
    private String            approach4;
    private String            approach5;
    private String            approach6;
    private String            approach7;
    private String            approach8;
    private String            approach9;
    private String            approach10;

}
