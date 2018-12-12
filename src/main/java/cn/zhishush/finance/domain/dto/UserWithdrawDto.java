package cn.zhishush.finance.domain.dto;

import java.math.BigDecimal;

import cn.zhishush.finance.core.dal.dataobject.coin.CoinLogDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserInfoDO;
import org.springframework.util.StringUtils;

import cn.zhishush.finance.core.dal.dataobject.user.UserBankCardInfoDO;

/**
 * 用户申请提现参数接受类.
 * 
 * @author hewenbin
 * @version v1.0 2018年8月31日 上午9:28:44 hewenbin
 */
public class UserWithdrawDto {

    /** 账户密码 */
    private String                  pwd;
    /** 提现金额 */
    private String                  money;
    /** 是否使用金币抵扣 1：是，0：否 */
    private Integer                 byCoin;

    private UserInfoDO user;
    private UserBankCardInfoDO bankCard;
    /** 扣减的金币对象 */
    private CoinLogDO               coinLog;

    public Boolean validateParam() {
        if (StringUtils.isEmpty(pwd) || this.getMoneyDecimal().compareTo(BigDecimal.TEN) < 0) {
            // 小于10块，不给提现
            return false;
        }
        return true;
    }

    /**
     * 是否使用金币抵扣.
     * 
     * @return
     * @author hewenbin
     * @version UserWithdrawDto.java, v1.0 2018年8月31日 上午9:35:57 hewenbin
     */
    public Boolean isByCoin() {
        return 1 == this.getByCoin();
    }

    /**
     * 获取BigDecimal类型的金额
     * 
     * @return
     * @author hewenbin
     * @version UserWithdrawDto.java, v1.0 2018年8月31日 下午5:30:23 hewenbin
     */
    public BigDecimal getMoneyDecimal() {
        try {
            return new BigDecimal(money);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMoney() {
        return this.money;
    }

    public void setMoney(String money) {
        if (StringUtils.isEmpty(money)) {
            this.money = "0";
        } else {
            this.money = money;
        }
    }

    public Integer getByCoin() {
        return byCoin;
    }

    public void setByCoin(Integer byCoin) {
        this.byCoin = byCoin;
    }

    public UserInfoDO getUser() {
        return user;
    }

    public void setUser(UserInfoDO user) {
        this.user = user;
    }

    public UserBankCardInfoDO getBankCard() {
        return bankCard;
    }

    public void setBankCard(UserBankCardInfoDO bankCard) {
        this.bankCard = bankCard;
    }

    public CoinLogDO getCoinLog() {
        return coinLog;
    }

    public void setCoinLog(CoinLogDO coinLog) {
        this.coinLog = coinLog;
    }

    @Override
    public String toString() {
        return "UserWithdrawDto{" + "pwd='" + pwd + '\'' + ", money='" + money + '\'' + ", byCoin="
               + byCoin + ", user=" + user + ", bankCard=" + bankCard + ", coinLog=" + coinLog
               + '}';
    }

}
