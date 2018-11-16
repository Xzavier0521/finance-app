package finance.domainservice.service.user.query.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import finance.api.model.base.Page;
import finance.core.common.util.CommonUtils;
import finance.domain.team.InviteInfoAndIncome;
import finance.domain.user.UserInfo;
import finance.domainservice.repository.*;
import finance.domainservice.service.user.query.UserInviteQueryService;
import finance.core.dal.dataobject.FinanceOperationRecord;
import finance.core.dal.dataobject.FinanceProductMain;
import finance.core.dal.dataobject.FinanceUserAccount;
import finance.core.dal.dataobject.FinanceUserInviteInfo;

/**
 *  <p>用户邀请信息查询</p>
 * @author  lili
 * @version :1.0 UserInviteQueryServiceImpl.java.java, v 0.1 2018/9/27 下午8:34 lili Exp $
 */
@Slf4j
@Service("userInviteQueryService")
public class UserInviteQueryServiceImpl implements UserInviteQueryService {

    @Resource
    private UserInviteRepository      userInviteRepository;
    @Resource
    private UserInfoRepository        userInfoRepository;
    @Resource
    private UserAccountRepository     userAccountRepository;
    @Resource
    private OperationRecordRepository operationRecordRepository;
    @Resource
    private ProductMainRepository     productMainRepository;

    @Override
    public Page<InviteInfoAndIncome> queryInviteInfoAndIncome(Long userId, int pageSize,
                                                              int pageNum) {
        Page<InviteInfoAndIncome> inviteInfoAndIncomePage = new Page<>(pageSize, (long) pageNum);
        // 1.根据邀请关系查询直接好友列表-只到一级
        Page<FinanceUserInviteInfo> userInviteInfoPage = userInviteRepository
            .queryByCondition(pageNum, pageSize, userId);
        if (userInviteInfoPage.getTotalCount() == 0) {
            inviteInfoAndIncomePage.setTotalCount(0L);
        } else {
            if (CollectionUtils.isNotEmpty(userInviteInfoPage.getDataList())) {
                inviteInfoAndIncomePage.setTotalCount(userInviteInfoPage.getTotalCount());
                List<InviteInfoAndIncome> inviteInfoAndIncomeList = Lists
                    .newArrayListWithCapacity(userInviteInfoPage.getDataList().size());
                List<Long> ids = userInviteInfoPage.getDataList().stream()
                    .map(FinanceUserInviteInfo::getUserId).collect(Collectors.toList());
                // 2.查询用户信息 -手机号码
                List<UserInfo> userInfoList = userInfoRepository.queryByCondition(ids);
                // 3.查询账户信息表-总收益字段
                List<FinanceUserAccount> userAccountList = userAccountRepository
                    .queryCondition(ids);
                // 4.计算预计收益
                // 4.1，查询操作流水，需要去除产品id重复
                List<FinanceOperationRecord> operationRecordList = operationRecordRepository
                    .query(ids);
                // 按照用户id分组
                Map<Long, List<FinanceOperationRecord>> productMap = operationRecordList.stream()
                    .collect(Collectors.groupingBy(FinanceOperationRecord::getUserId));
                InviteInfoAndIncome inviteInfoAndIncome;
                for (UserInfo userInfo : userInfoList) {
                    inviteInfoAndIncome = new InviteInfoAndIncome();
                    // 用户id
                    inviteInfoAndIncome.setParentUserId(userId);
                    // 被邀请者id
                    inviteInfoAndIncome.setUserId(userInfo.getId());
                    // 手机号码
                    inviteInfoAndIncome
                        .setPhoneNumber(CommonUtils.mobileEncrypt(userInfo.getMobileNum()));
                    inviteInfoAndIncome.setRegisterDate(userInfo.getRegisterDate());
                    List<FinanceUserAccount> financeUserAccountList = userAccountList.stream()
                        .filter(account -> account.getUserId().equals(userInfo.getId()))
                        .collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(financeUserAccountList)) {
                        FinanceUserAccount userAccount = financeUserAccountList.get(0);
                        // 总收益
                        inviteInfoAndIncome.setTotalIncome(userAccount.getSumChargeMoney());
                    } else {
                        inviteInfoAndIncome.setTotalIncome(BigDecimal.ZERO);
                    }
                    // 预计收益
                    // 4.2 根据产品id获取产品的信息计算预计收益(按照间接推广金额汇总)
                    List<FinanceOperationRecord> financeOperationRecords = productMap
                        .get(userInfo.getId());
                    if (CollectionUtils.isNotEmpty(financeOperationRecords)) {
                        List<Long> productIds = financeOperationRecords.stream()
                            .map(FinanceOperationRecord::getProductId).collect(Collectors.toList());
                        FinanceProductMain productMain = productMainRepository.sumBonus(productIds);
                        if (Objects.nonNull(productMain)
                            && Objects.nonNull(productMain.getIndirectBonus())) {
                            inviteInfoAndIncome.setPredictIncome(productMain.getIndirectBonus());
                        } else {
                            inviteInfoAndIncome.setPredictIncome(BigDecimal.ZERO);
                        }
                    } else {
                        inviteInfoAndIncome.setPredictIncome(BigDecimal.ZERO);
                    }
                    inviteInfoAndIncomeList.add(inviteInfoAndIncome);
                }
                inviteInfoAndIncomePage.setDataList(inviteInfoAndIncomeList);

            }
        }
        return inviteInfoAndIncomePage;
    }
}
