package cn.zhishush.finance.domainservice.service.creditcard.impl;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.creditCard.CardParameter;
import cn.zhishush.finance.api.model.vo.creditCard.CreditCardScheduleVO;
import cn.zhishush.finance.api.model.vo.creditCard.CreditCardTeamVO;
import cn.zhishush.finance.core.common.enums.CreditCardStateType;
import cn.zhishush.finance.core.common.enums.PopularizeModuleTypeEnum;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.enums.UserInviteInfoEnum;
import cn.zhishush.finance.core.common.util.DateUtils;
import cn.zhishush.finance.core.common.util.PreconditionUtils;
import cn.zhishush.finance.core.dal.dao.product.*;
import cn.zhishush.finance.core.dal.dao.user.UserInfoDAO;
import cn.zhishush.finance.core.dal.dao.user.UserInviteInfoDAO;
import cn.zhishush.finance.core.dal.dataobject.account.BankInfoDO;
import cn.zhishush.finance.core.dal.dataobject.product.CashBackConfigDO;
import cn.zhishush.finance.core.dal.dataobject.product.CreditCardApplyInfoDO;
import cn.zhishush.finance.core.dal.dataobject.product.CreditCardDetailsDO;
import cn.zhishush.finance.core.dal.dataobject.product.CreditCardInfoDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserInfoDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserInviteInfoDO;
import cn.zhishush.finance.domainservice.service.creditcard.CreditCardScheduleServer;
import cn.zhishush.finance.domainservice.service.jwt.JwtService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @program: finance-app
 * @description: 信用卡查询模块
 * @author: Mr.Zhang
 * @create: 2018-12-25 15:02
 **/

@Slf4j
@Service
public class CreditCardScheduleServerImpl implements CreditCardScheduleServer {
    @Resource
    private UserInfoDAO userInfoDAO;
    @Resource
    private CreditCardApplyInfoDAO creditCardApplyInfoDAO;
    @Resource
    private UserInviteInfoDAO userInviteInfoDAO;
    @Resource
    private JwtService jwtService;
    @Resource
    private BankInfoDAO bankInfoDAO;
    @Resource
    private CashBackConfigDAO cashBackConfigDAO;
    @Resource
    private CreditCardDetailsDAO creditCardDetailsDAO;
    @Resource
    private CreditCardInfoDAO creditCardInfoDAO;
    @Override
    public Page<CreditCardTeamVO> query4Page(Map<String, Object> params, int pageSize, Long pageNum) {
            /*
             * .jwt 获取用户user_id
             */
            UserInfoDO userInfoDO = jwtService.getUserInfo();
            Long userId = userInfoDO.getId();
            Page<CreditCardTeamVO> page = new Page<>(pageSize, pageNum);
            UserInviteInfoEnum userInviteInfoEnum = UserInviteInfoEnum
                    .getByCode(MapUtils.getString(params, "queryType"));
            log.info("userInviteInfoEnum{}",userInviteInfoEnum);
            String progressStatus = MapUtils.getString(params, "progressStatus");
            List<Long> userIds = Lists.newArrayList();
            PreconditionUtils.checkArgument(Objects.nonNull(userInviteInfoEnum), ReturnCode.SYS_ERROR);
            switch (userInviteInfoEnum) {
                case FIRST:
                    // 根据userId查询一级好友
                     userIds = userInviteInfoDAO.selectListByPrrentId(userId,page);
                    log.info("[开始查询用户一级好友]userIds{}",userIds);
                     break;
                case SECOND:
                    userIds= userInviteInfoDAO.selectOneByParentTwoUserId(userId,page);
                    // 根据userId查询二级好友
                    break;
                case SELF:
                    // 根据userId查询一级好
                    userIds.add(userId);

                    break;
                default:
            }
            if (CollectionUtils.isNotEmpty(userIds)) {
                page = queryByType(userIds, userInviteInfoEnum, progressStatus,page);
                page.setTotalCount((long)userIds.size());
            }
            return page;
        }

        private Page<CreditCardTeamVO> queryByType(List<Long> userIds, UserInviteInfoEnum userInviteInfoEnum,
                String progressStatus,Page<CreditCardTeamVO>page) {
        List<CreditCardTeamVO> creditCardTeamVOS = new ArrayList<>();
            CreditCardTeamVO creditCardTeamVO;
            for (Long userId:userIds){
                // 1.查询信用卡申请记录表
                List<CreditCardApplyInfoDO> creditCardApplyInfoDOList = creditCardApplyInfoDAO.selectByMessage(userId,progressStatus);
                log.info("得到的信用卡申请进度{}",creditCardApplyInfoDOList);
                for (CreditCardApplyInfoDO creditCardApplyInfoDO: creditCardApplyInfoDOList) {
                    // 2.根据产品代码查询 finance_credit_card_info 获取到bank_code product_name
                    CreditCardInfoDO creditCardInfoDO = creditCardInfoDAO.selectBycardId(creditCardApplyInfoDO.getProductCode());
                    log.info("信用卡信息{}",creditCardInfoDO);
                    // 3.查询信用卡明细表获取到返现配置id，根据cashback_config_id 查询返现配置表 拿到返现配置,根据类型 拼接返现文本
                    CreditCardDetailsDO creditCardDetailsDO =creditCardDetailsDAO.selectByCardcode(creditCardInfoDO.getCardCode());
                    // 3.查询信用卡明细表获取到返现配置id，根据cashback_config_id 查询返现配置表 拿到返现配置,根据类型 拼接返现文本
                    // 直推推广(一级) ，间接推广(二级)，本人
                    CashBackConfigDO cashBackConfigDO = cashBackConfigDAO.selectByConfigKey(creditCardDetailsDO.getCashbackConfigId());
                    UserInfoDO userInfoDO = userInfoDAO.selectByPrimaryKey(userId);
                    creditCardTeamVO = new CreditCardTeamVO();
                    BeanUtils.copyProperties(userInfoDO,creditCardTeamVO);
                    BeanUtils.copyProperties(creditCardInfoDO,creditCardTeamVO);
                    BeanUtils.copyProperties(creditCardDetailsDO,creditCardTeamVO);
                    creditCardTeamVO.setUserId(userInfoDO.getId());
                    creditCardTeamVO.setProductName(creditCardDetailsDO.getCardName());
                    creditCardTeamVO.setRealName(creditCardApplyInfoDO.getRealName());
                    creditCardTeamVO.setQueryType(userInviteInfoEnum.getCode());
                    creditCardTeamVO.setLogoUrl(creditCardInfoDO.getCardLogoUrl());
                    creditCardTeamVO.setApplyTime(DateUtils.format(creditCardApplyInfoDO.getCreateTime(), DateUtils.LONG_WEB_FORMAT));
                    if(userInviteInfoEnum.getCode().equals(UserInviteInfoEnum.FIRST.getCode())){
                        creditCardTeamVO.setContent("恭喜您获得返现"+cashBackConfigDO.getDirectBonus());
                    }else if (userInviteInfoEnum.getCode().equals(UserInviteInfoEnum.SECOND.getCode())){
                        creditCardTeamVO.setContent("恭喜您获得返现"+cashBackConfigDO.getIndirectBonus());
                    }else if(userInviteInfoEnum.getCode().equals(UserInviteInfoEnum.SELF.getCode())){
                        creditCardTeamVO.setContent("恭喜您获得返现"+cashBackConfigDO.getTerminalBonus());
                    }
                    if(progressStatus.equals(CreditCardStateType.not_queried.getCode())){
                        creditCardTeamVO.setProgressStatus(CreditCardStateType.not_queried.getDesc());
                    }else if(progressStatus.equals(CreditCardStateType.check_sucess.getCode())){
                        creditCardTeamVO.setProgressStatus(CreditCardStateType.check_sucess.getDesc());
                    }else if(progressStatus.equals(CreditCardStateType.check_failure.getCode())){
                        creditCardTeamVO.setProgressStatus(CreditCardStateType.check_failure.getDesc());
                    }
                    creditCardTeamVOS.add(creditCardTeamVO);
                    log.info("最终得到的值为{}",creditCardTeamVOS);
                }
            }
            page.setDataList(creditCardTeamVOS);
                return page;

        }



    @Override
    public Page<CreditCardScheduleVO> querySchedule(CardParameter cardParameter) {
        /** 1.jwt 获取用户user_id **/
//        Long userId = jwtService.getUserInfo().getId();
        Page<CreditCardScheduleVO> page = new Page<>(cardParameter.getPageSize(), cardParameter.getPageNum());

        //查找银行
        return null;
//        CreditCardApplyInfoDO creditCardApplyInfoDO = new CreditCardApplyInfoDO();
//        creditCardApplyInfoDO.setRealName(cardParameter.getRealName());
//        creditCardApplyInfoDO.setIdentificationNumber(cardParameter.getIdCard());
//        creditCardApplyInfoDO.setBankCode(cardParameter.getBankCode());
//        creditCardApplyInfoDO.setUserId(1398L);
//        List<CreditCardScheduleVO> creditCardScheduleVOList = new ArrayList<>();
//        /**根据银行代码查找信用卡信息查找信用卡申请记录**/
//
//        Map<String, Object> parameters = Maps.newHashMap();
//        parameters.put("bankCode",cardParameter.getBankCode());
//
//        parameters.put("page",page);
//
//        List<CreditCardInfoDO> creditCardDetailsDOList = creditCardInfoDAO.query(parameters);
//        log.info("creditCardDetailsDOList的值,{}",creditCardDetailsDOList);
//        for (CreditCardInfoDO creditCardInfoDO:creditCardDetailsDOList) {
//            /**根据信用卡**用户申请信用卡记录**/
//            CreditCardApplyInfoDO creditCardApplyInfoDO1 = creditCardApplyInfoDAO.selectByMessage(1398L, creditCardInfoDO.getCardCode());
//
//            if (creditCardApplyInfoDO1 != null) {
//                CreditCardScheduleVO creditCardScheduleVO = new CreditCardScheduleVO();
//                creditCardScheduleVO.setCardName(creditCardInfoDO.getCardName());
//                creditCardScheduleVO.setIncomingDate(DateUtils.format(creditCardInfoDO.getCreateTime(), DateUtils.LONG_WEB_FORMAT));
//                if (creditCardApplyInfoDO1.getProgressStatus().equals(CreditCardStateType.not_queried.getCode())) {
//                    creditCardScheduleVO.setApplyStatus(CreditCardStateType.not_queried.getDesc());
//                } else if (creditCardApplyInfoDO1.getProgressStatus().equals(CreditCardStateType.check_sucess.getCode())) {
//                    creditCardScheduleVO.setApplyStatus(CreditCardStateType.check_sucess.getDesc());
//                } else if (creditCardApplyInfoDO1.getProgressStatus().equals(CreditCardStateType.check_failure.getCode())) {
//                    creditCardScheduleVO.setApplyStatus(CreditCardStateType.check_failure.getDesc());
//                }
//                 log.info("CreditCardScheduleVO{}", creditCardScheduleVO);
//                 creditCardScheduleVOList.add(creditCardScheduleVO);
//                 page.setDataList(creditCardScheduleVOList);
//            }
//            page.setTotalCount((long)creditCardDetailsDOList.size());
//
//        }
//
//        return page;
    }


}