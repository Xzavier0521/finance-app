package cn.zhishush.finance.api.model.vo.activity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * <p>阶梯红包信息展示类</p>
 * @author lili
 * @version 1.0: StepRewardsDetailVo.java, v0.1 2018/11/26 7:10 PM lili Exp $
 */
@Data
public class StepRewardsDetailVo implements Serializable {
    private static final long             serialVersionUID = -4247903681416685737L;
    /** 是否结束 */
    private String                        isEnd;
    /** 奖池金额 */
    private BigDecimal                    poolAmount;
    /** 是否新参加红包活动 */
    private String                        isNew;
    /** 已邀请人数 */
    private Integer                       inviteNum;
    /** 已参加人数 */
    private Integer                       joinNum;
    /** 当前阶梯序号 */
    private Integer                       stepNo;
    /** 当前阶梯所需人数 */
    private Integer                       thisStepNum;
    /** 当前阶梯红包金额 */
    private BigDecimal                    thisStepMoney;
    /** 下一个阶梯所需人数 */
    private Integer                       nextStepNum;
    /** 下一个阶梯红包金额 */
    private BigDecimal                    nextStepMoney;
    /** 下两个阶梯所需人数 */
    private Integer                       nextTwoStepNum;
    /** 下两个阶梯红包金额 */
    private BigDecimal                    nextTwoStepMoney;
    /** 已获得金额 */
    private BigDecimal                    haveAmount;
    /** 已参加用户列表 */
    private List<StepRewardsJoinDetailVo> joinList;
    /** 已邀请用户列表 */
    private List<String>                  inviteList;
}
