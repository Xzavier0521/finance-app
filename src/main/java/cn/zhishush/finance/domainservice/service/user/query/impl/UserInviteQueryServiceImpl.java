package cn.zhishush.finance.domainservice.service.user.query.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cn.zhishush.finance.domainservice.repository.account.UserAccountRepository;
import cn.zhishush.finance.domainservice.repository.log.OperationRecordRepository;
import cn.zhishush.finance.domainservice.repository.third.impl.product.ProductMainRepository;
import cn.zhishush.finance.domainservice.repository.user.UserInfoRepository;
import cn.zhishush.finance.domainservice.repository.user.UserInviteInfoRepository;
import cn.zhishush.finance.core.dal.dataobject.log.OperationRecordDO;
import cn.zhishush.finance.core.dal.dataobject.product.ProductMain;
import cn.zhishush.finance.core.dal.dataobject.account.UserAccountDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserInviteInfoDO;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.common.util.CommonUtils;
import cn.zhishush.finance.domain.team.InviteInfoAndIncome;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domainservice.service.user.query.UserInviteQueryService;

/**
 * <p>用户邀请信息查询</p>
 * 
 * @author lili
 * @version :1.0 UserInviteQueryServiceImpl.java.java, v 0.1 2018/9/27 下午8:34 lili Exp $
 */
@Slf4j
@Service("userInviteQueryService")
public class UserInviteQueryServiceImpl implements UserInviteQueryService {

    @Resource
    private UserInviteInfoRepository userInviteInfoRepository;
    @Resource
    private UserInfoRepository userInfoRepository;
    @Resource
    private UserAccountRepository userAccountRepository;
    @Resource
    private OperationRecordRepository operationRecordRepository;
    @Resource
    private ProductMainRepository productMainRepository;

    @Override
    public Page<InviteInfoAndIncome> queryInviteInfoAndIncome(Long userId, int pageSize,
                                                              int pageNum) {
        Page<InviteInfoAndIncome> inviteInfoAndIncomePage = new Page<>(pageSize, (long) pageNum);
        // 1.根据邀请关系查询直接好友列表-只到一级
        Page<UserInviteInfoDO> userInviteInfoPage = userInviteInfoRepository
            .queryByCondition(pageNum, pageSize, userId);
        if (userInviteInfoPage.getTotalCount() == 0) {
            inviteInfoAndIncomePage.setTotalCount(0L);
        } else {
            if (CollectionUtils.isNotEmpty(userInviteInfoPage.getDataList())) {
                inviteInfoAndIncomePage.setTotalCount(userInviteInfoPage.getTotalCount());
                List<InviteInfoAndIncome> inviteInfoAndIncomeList = Lists
                    .newArrayListWithCapacity(userInviteInfoPage.getDataList().size());
                List<Long> ids = userInviteInfoPage.getDataList().stream()
                    .map(UserInviteInfoDO::getUserId).collect(Collectors.toList());
                // 2.查询用户信息 -手机号码
                List<UserInfo> userInfoList = userInfoRepository.queryByCondition(ids);
                // 3.查询账户信息表-总收益字段
                List<UserAccountDO> userAccountList = userAccountRepository
                    .queryCondition(ids);
                // 4.计算预计收益
                // 4.1，查询操作流水，需要去除产品id重复
                List<OperationRecordDO> operationRecordList = operationRecordRepository
                    .query(ids);
                // 按照用户id分组
                Map<Long, List<OperationRecordDO>> productMap = operationRecordList.stream()
                    .collect(Collectors.groupingBy(OperationRecordDO::getUserId));
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
                    List<UserAccountDO> userAccountDOList = userAccountList.stream()
                        .filter(account -> account.getUserId().equals(userInfo.getId()))
                        .collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(userAccountDOList)) {
                        UserAccountDO userAccount = userAccountDOList.get(0);
                        // 总收益
                        inviteInfoAndIncome.setTotalIncome(userAccount.getSumChargeMoney());
                    } else {
                        inviteInfoAndIncome.setTotalIncome(BigDecimal.ZERO);
                    }
                    // 预计收益
                    // 4.2 根据产品id获取产品的信息计算预计收益(按照间接推广金额汇总)
                    List<OperationRecordDO> operationRecordDOS = productMap
                        .get(userInfo.getId());
                    if (CollectionUtils.isNotEmpty(operationRecordDOS)) {
                        List<Long> productIds = operationRecordDOS.stream()
                            .map(OperationRecordDO::getProductId).collect(Collectors.toList());
                        ProductMain productMain = productMainRepository.sumBonus(productIds);
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
