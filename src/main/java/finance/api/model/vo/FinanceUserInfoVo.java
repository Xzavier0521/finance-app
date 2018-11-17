package finance.api.model.vo;

import lombok.Data;
import finance.core.dal.dataobject.FinanceUserInfo;
import lombok.EqualsAndHashCode;

/**
 * 用户信息展示实体类.
 * @author hewenbin
 * @version v1.0 2018年7月21日 下午5:03:56 hewenbin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FinanceUserInfoVo extends FinanceUserInfo {

    private static final long serialVersionUID = 8243220380361884211L;
    private String            channelCode;
    private String            channelDetail;
    private String            platformCode;
    private String            platformDetail;
    private String            realName;
    private String            accountNo;
    private String            inviteMobileNum;
    private String            registerTime;

}
