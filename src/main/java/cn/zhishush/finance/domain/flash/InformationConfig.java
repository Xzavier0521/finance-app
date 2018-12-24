package cn.zhishush.finance.domain.flash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationConfig implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 快讯文案
    快讯文案
    快讯文案
     */
    private String informationText;

    /**
     * 跳转url
     */
    private String redirectUrl;

    /**
     * 状态 online-上线 默认为offline-未上线
     */
    private String status;

    /**
     * 开始时间
     */
    private Date createTime;

    /**
     * 结束时间
     */
    private Date updateTime;

    /**
     * 创建者
     */
    private String creater;

    /**
     * 更新者
     */
    private String updater;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 银行名称  或者贷款平台
     */
    private String bank;

    private String tail;

    private String isDelete;

    /**
     * 贷款金额
     */
    private String applicationPage;

    /**
     * 贷款金额  默认为100
     */
    private Long amountOf;

    /**
     * 快讯类型 1—银行  2—贷款平台
     */
    private String type;


}