package cn.zhishush.finance.domainservice.service.creditcard.impl;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.creditCard.CardParameter;
import cn.zhishush.finance.api.model.vo.creditCard.CreditCardScheduleVO;
import cn.zhishush.finance.api.model.vo.creditCard.CreditCardTeamVO;
import cn.zhishush.finance.core.common.enums.CreditCardStateType;
import cn.zhishush.finance.core.common.enums.UserInviteInfoEnum;
import cn.zhishush.finance.core.common.util.DateUtils;
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
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        /** 1.jwt 获取用户user_id **/

        Page<CreditCardTeamVO> page = new Page<>(pageSize, pageNum);

        if (params.get("queryType").equals(UserInviteInfoEnum.FIRST.getCode())) {
            /**根据id查找用户关系**/
            List<UserInviteInfoDO> userInviteInfoDOList = userInviteInfoDAO.selectOneByParentUserId(1398L,page);
            log.info("得到一级好友的结果为{}", userInviteInfoDOList);
            if (userInviteInfoDOList.isEmpty()) {
                return page;
            }
            return query4Page(userInviteInfoDOList,params,pageSize,pageNum);

        } else if (params.get("queryType").equals(UserInviteInfoEnum.SECOND.getCode())) {
            List<UserInviteInfoDO> userInviteInfoDOList = userInviteInfoDAO.selectOneByParentTwoUserId(1398L,page);
            log.info("二级好友{}",userInviteInfoDOList);
            if (userInviteInfoDOList.isEmpty()) {
                return page;
            }
            return query4Page(userInviteInfoDOList,params,pageSize,pageNum);


        }else if(params.get("queryType").equals(UserInviteInfoEnum.FLEF.getCode())){
            UserInfoDO userInfoDO =userInfoDAO.selectByPrimaryKey(1398L);
            if (userInfoDO==null){
                return page;
            }
            Map<String, Object> parameters = Maps.newHashMap();
            parameters.put("userId",userInfoDO.getId());
            parameters.put("progressStatus",params.get("progressStatus"));
            parameters.put("page",page);
            List<CreditCardApplyInfoDO> creditCardApplyInfoDOList ;


            List<CreditCardTeamVO> creditCardApplyInfoVOList = new ArrayList<>() ;
            CashBackConfigDO cashBackConfigDO = new CashBackConfigDO();

            /**得到好友信用卡信息**/
            creditCardApplyInfoDOList = creditCardApplyInfoDAO.query(parameters);
            log.info("得到的好友信用卡信息{}",creditCardApplyInfoDOList);

            for (CreditCardApplyInfoDO creditCardApplyInfoDO1:creditCardApplyInfoDOList) {
                /**银行明细**/
                CreditCardDetailsDO creditCardDetailsDO = new CreditCardDetailsDO();
                /**银行明细**/
                creditCardDetailsDO =creditCardDetailsDAO.selectByCardcode(creditCardApplyInfoDO1.getProductCode());
                log.info("查出来的银行信息为{}",creditCardDetailsDO);
                if(creditCardDetailsDO==null){
                    return page;
                }
                CreditCardTeamVO creditCardTeamVO = new CreditCardTeamVO();
                /**信用卡申请信息拷贝到放回VO**/
                BeanUtils.copyProperties(creditCardApplyInfoDO1,creditCardTeamVO);
                creditCardTeamVO.setApplyTime(DateUtils.format(creditCardApplyInfoDO1.getCreateTime(), DateUtils.LONG_WEB_FORMAT));
                creditCardTeamVO.setMobileNum(userInfoDO.getMobileNum());
                creditCardTeamVO.setProductName(creditCardDetailsDO.getCardName());
                /**查找银行信用得到银行code**/
                CreditCardInfoDO creditCardInfoDO = creditCardInfoDAO.selectBycardId(creditCardDetailsDO.getCardCode());
                if(creditCardInfoDO ==null){
                    return  page;
                }
                creditCardTeamVO.setBankCode(creditCardInfoDO.getBankCode());
                creditCardTeamVO.setLogoUrl(creditCardInfoDO.getCardLogoUrl());
                if (params.get("progressStatus").equals(CreditCardStateType.not_queried.getCode())){
                    creditCardTeamVO.setQueryType(UserInviteInfoEnum.FLEF.getCode());
                    creditCardTeamVO.setProgressStatus(CreditCardStateType.not_queried.getDesc());
                }else if(params.get("progressStatus").equals(CreditCardStateType.check_sucess.getCode())){
                    creditCardTeamVO.setQueryType(UserInviteInfoEnum.FLEF.getCode());
                    creditCardTeamVO.setProgressStatus(CreditCardStateType.check_sucess.getDesc());
                    cashBackConfigDO =cashBackConfigDAO.selectByConfigKey(creditCardDetailsDO.getCashbackConfigId());
                    if (cashBackConfigDO==null){
                        return page;
                    }
                    log.info("推广返佣对象的值{}",cashBackConfigDO);
                    creditCardTeamVO.setContent("恭喜您获得返现"+cashBackConfigDO.getTotalBonus());
                }else if(params.get("progressStatus").equals(CreditCardStateType.check_failure.getCode())){

                    creditCardTeamVO.setQueryType(UserInviteInfoEnum.FLEF.getCode());
                    creditCardTeamVO.setProgressStatus(CreditCardStateType.check_failure.getDesc());

                }
                 creditCardApplyInfoVOList.add(creditCardTeamVO);
                 page.setDataList(creditCardApplyInfoVOList);
                page.setTotalCount((long)creditCardApplyInfoDOList.size());

            }
            return page;
        }
    return page;
    }

    @Override
    public Page<CreditCardScheduleVO> querySchedule(CardParameter cardParameter) {
        Page<CreditCardScheduleVO> page = new Page<>(cardParameter.getPageSize(), cardParameter.getPageNum());
        CreditCardApplyInfoDO creditCardApplyInfoDO = new CreditCardApplyInfoDO();
        creditCardApplyInfoDO.setRealName(cardParameter.getRealName());
        creditCardApplyInfoDO.setIdentificationNumber(cardParameter.getIdCard());
        creditCardApplyInfoDO.setBankCode(cardParameter.getBankCode());
        creditCardApplyInfoDO.setUserId(1398L);
        List<CreditCardScheduleVO> creditCardScheduleVOList = new ArrayList<>();
        /**根据银行代码查找信用卡信息查找信用卡申请记录**/

        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("bankCode",cardParameter.getBankCode());

        parameters.put("page",page);

        List<CreditCardInfoDO> creditCardDetailsDOList = creditCardInfoDAO.query(parameters);
        log.info("creditCardDetailsDOList的值,{}",creditCardDetailsDOList);
        for (CreditCardInfoDO creditCardInfoDO:creditCardDetailsDOList) {
            /**根据信用卡**用户申请信用卡记录**/
            CreditCardApplyInfoDO creditCardApplyInfoDO1 = creditCardApplyInfoDAO.selectByMessage(1398L, creditCardInfoDO.getCardCode());

            if (creditCardApplyInfoDO1 != null) {
                CreditCardScheduleVO creditCardScheduleVO = new CreditCardScheduleVO();
                creditCardScheduleVO.setCardName(creditCardInfoDO.getCardName());
                creditCardScheduleVO.setIncomingDate(DateUtils.format(creditCardInfoDO.getCreateTime(), DateUtils.LONG_WEB_FORMAT));
                if (creditCardApplyInfoDO1.getProgressStatus().equals(CreditCardStateType.not_queried.getCode())) {
                    creditCardScheduleVO.setApplyStatus(CreditCardStateType.not_queried.getDesc());
                } else if (creditCardApplyInfoDO1.getProgressStatus().equals(CreditCardStateType.check_sucess.getCode())) {
                    creditCardScheduleVO.setApplyStatus(CreditCardStateType.check_sucess.getDesc());
                } else if (creditCardApplyInfoDO1.getProgressStatus().equals(CreditCardStateType.check_failure.getCode())) {
                    creditCardScheduleVO.setApplyStatus(CreditCardStateType.check_failure.getDesc());
                }
                 log.info("CreditCardScheduleVO{}", creditCardScheduleVO);
                 creditCardScheduleVOList.add(creditCardScheduleVO);
                 page.setDataList(creditCardScheduleVOList);
            }
            page.setTotalCount((long)creditCardDetailsDOList.size());

        }

        return page;
    }

    private Page<CreditCardTeamVO> query4Page(List<UserInviteInfoDO> userInviteInfoDOList, Map<String, Object> params, int pageSize,long pageNum) {
        Page<CreditCardTeamVO> page = new Page<>(pageSize, (long)pageNum);
        /**用户申请信用卡记录**/
        List<CreditCardApplyInfoDO> creditCardApplyInfoDOList ;
        /**返回结果list**/
        List<CreditCardTeamVO> creditCardTeamVOS = new ArrayList<>() ;
        Map<String, Object> parameters = Maps.newHashMap();

        /**根据queryType查找信息**/


        for (UserInviteInfoDO userInviteInfoDO:userInviteInfoDOList) {
            /**查找一级好友信息**/
            UserInfoDO userInfoDO =  userInfoDAO.selectByPrimaryKey(userInviteInfoDO.getUserId());
            log.info("得到的好友信息为{}",userInfoDO);
            /**根据用好友的id,查找银行配置**/
            parameters.put("userId",userInviteInfoDO.getUserId());
            parameters.put("progressStatus",params.get("progressStatus"));
            parameters.put("page",page);
            /**得到一级好友信用卡信息**/
            creditCardApplyInfoDOList = creditCardApplyInfoDAO.query(parameters);
            log.info("查出信用卡的信息为{}",creditCardApplyInfoDOList);
            /**循环遍历一级好友信用卡信息**/
            for (CreditCardApplyInfoDO creditCardApplyInfoDO1:creditCardApplyInfoDOList) {
                /**银行明细**/
                CreditCardDetailsDO creditCardDetailsDO = new CreditCardDetailsDO();
                /**银行明细**/
                creditCardDetailsDO =creditCardDetailsDAO.selectByCardcode(creditCardApplyInfoDO1.getProductCode());
                log.info("查出来的银行详情{}",creditCardDetailsDO);
                if(creditCardDetailsDO==null){
                    return page;
                }
                CreditCardTeamVO creditCardTeamVO = new CreditCardTeamVO();
                /**信用卡申请信息拷贝到放回VO**/
                BeanUtils.copyProperties(creditCardApplyInfoDO1,creditCardTeamVO);
                creditCardTeamVO.setApplyTime(DateUtils.format(creditCardApplyInfoDO1.getCreateTime(), DateUtils.LONG_WEB_FORMAT));
                creditCardTeamVO.setMobileNum(userInfoDO.getMobileNum());
                creditCardTeamVO.setProductName(creditCardDetailsDO.getCardName());
                /**查找银行信用得到银行code**/
                CreditCardInfoDO creditCardInfoDO = creditCardInfoDAO.selectBycardId(creditCardDetailsDO.getCardCode());
                log.info("查找银行信用得到银行code{}",creditCardInfoDO);
                if(creditCardInfoDO ==null){
                    return  page;
                }
                creditCardTeamVO.setBankCode(creditCardInfoDO.getBankCode());
                creditCardTeamVO.setLogoUrl(creditCardInfoDO.getCardLogoUrl());

                if(params.get("progressStatus").equals(CreditCardStateType.not_queried.getCode())) {
                    creditCardTeamVO.setQueryType(params.get("queryType").equals(UserInviteInfoEnum.FIRST.getCode())?UserInviteInfoEnum.FIRST.getCode():UserInviteInfoEnum.SECOND.getCode());
                    creditCardTeamVO.setProgressStatus(CreditCardStateType.not_queried.getDesc());

                }else if(params.get("progressStatus").equals(CreditCardStateType.check_sucess.getCode())){
                    creditCardTeamVO.setProgressStatus(CreditCardStateType.check_sucess.getDesc());
                    CashBackConfigDO cashBackConfigDO =cashBackConfigDAO.selectByConfigKey(creditCardDetailsDO.getCashbackConfigId());
                    if (cashBackConfigDO==null){
                        return page;
                    }

                    log.info("推广返佣对象的值{}",cashBackConfigDO);
                    cashBackConfigDO =cashBackConfigDAO.selectByConfigKey(cashBackConfigDO.getConfigId());
                    if (cashBackConfigDO==null){
                        return page;
                    }
                    creditCardTeamVO.setQueryType(params.get("queryType").equals(UserInviteInfoEnum.FIRST.getCode())?UserInviteInfoEnum.FIRST.getCode():UserInviteInfoEnum.SECOND.getCode());
                  if (params.get("queryType").equals(UserInviteInfoEnum.FIRST.getCode())){
                      creditCardTeamVO.setContent("恭喜您获得返现"+cashBackConfigDO.getDirectBonus());
                  }else if(params.get("queryType").equals(UserInviteInfoEnum.SECOND.getCode())){
                      creditCardTeamVO.setContent("恭喜您获得返现"+cashBackConfigDO.getIndirectBonus());
                  }
                    creditCardTeamVO.setProgressStatus(CreditCardStateType.check_sucess.getDesc());


                }else if(params.get("progressStatus").equals(CreditCardStateType.check_failure.getCode())){
                    creditCardTeamVO.setQueryType(params.get("queryType").equals(UserInviteInfoEnum.FIRST.getCode())?UserInviteInfoEnum.FIRST.getCode():UserInviteInfoEnum.SECOND.getCode());
                    creditCardTeamVO.setQueryType(UserInviteInfoEnum.FIRST.getCode());
                    creditCardTeamVO.setProgressStatus(CreditCardStateType.check_failure.getDesc());
                }

                page.setTotalCount((long)creditCardApplyInfoDOList.size());
                creditCardTeamVOS.add(creditCardTeamVO);
                page.setDataList(creditCardTeamVOS);

            }

        }



        return page;

    }
}