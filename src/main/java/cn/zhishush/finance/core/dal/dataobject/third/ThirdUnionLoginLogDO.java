package cn.zhishush.finance.core.dal.dataobject.third;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>第三方联合登陆日志</p>
 * @author lili
 * @version 1.0: ThirdUnionLoginLogDO.java, v0.1 2018/11/28 8:34 PM lili Exp $
 */
@Data
public class ThirdUnionLoginLogDO implements Serializable {
    private static final long serialVersionUID = -8195486918491543877L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 真实姓名
     */
    private String            realName;

    /**
     * 手机号码
     */
    private String            mobileNum;

    /**
     * 第三方用户标识
     */
    private String            thirdUserId;

    /**
     * 渠道
     */
    private String            thirdType;

    /**
     * 渠道商编号
     */
    private String            deptId;

    /**
     * 产品代码
     */
    private String            productCode;

    /**
     * 产品名称
     */
    private String            productName;

    /**
     * 返回码
     */
    private String            errorCode;

    /**
     * 返回消息
     */
    private String            errorMsg;

    /**
     * 创建时间
     */
    private Date              createTime;

    /**
     * 更新时间
     */
    private Date              updateTime;

    /**
     * 创建者
     */
    private String            creator;

    /**
     * 更新者
     */
    private String            updator;

    /**
     * 是否删除0-否 1-是
     */
    private String            isDelete;

    /**
     * 版本号
     */
    private Long              version;

}