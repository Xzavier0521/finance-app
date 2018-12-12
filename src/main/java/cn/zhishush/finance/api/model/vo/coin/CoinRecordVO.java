package cn.zhishush.finance.api.model.vo.coin;

import java.util.Date;

import cn.zhishush.finance.core.common.util.DateUtil;

/**
 * <p>金币获取或者消耗VO</p>
 * @author lili
 * @version 1.0: CoinRecordVO.java, v0.1 2018/11/26 7:13 PM lili Exp $
 */
public class CoinRecordVO {
    private String datetime;
    private String coinNum;
    private String desc;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = DateUtil.dateToString(datetime, "yyyy-MM-dd HH:mm:ss");
    }

    public String getCoinNum() {
        return coinNum;
    }

    public void setCoinNum(Integer coinNum) {
        if (coinNum.intValue() >= 0) {
            this.coinNum = "+" + coinNum;
        } else {
            this.coinNum = coinNum.toString();
        }

    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "CoinRecordVO{" + "datetime='" + datetime + '\'' + ", coinNum='" + coinNum + '\''
               + ", desc='" + desc + '\'' + '}';
    }
}
