package cn.zhishush.finance.domainservice.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.zhishush.finance.api.model.vo.userinfo.UserInfoVo;
import cn.zhishush.finance.core.dal.dao.user.UserInfoDAO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.zhishush.finance.domainservice.service.user.UserInfoService;
import cn.zhishush.finance.core.dal.dao.account.IdCardInfoDAO;
import cn.zhishush.finance.core.dal.dao.account.UserBankCardInfoDAO;
import cn.zhishush.finance.core.dal.dataobject.account.IdCardInfoDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserBankCardInfoDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserInfoDO;

/**
 * @author hewenbin
 * @version v1.0 2018年7月12日 下午5:36:16 hewenbin
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoDAO         userInfoMapper;
    @Resource
    private UserBankCardInfoDAO bankCardMapper;
    @Resource
    private IdCardInfoDAO       idCardInfoMappe;

    @Override
    public List<UserInfoVo> queryUserInfos(List<Long> userIds) {
        List<UserInfoDO> users = userInfoMapper.selectByPrimaryKeys(userIds);
        List<IdCardInfoDO> idCards = idCardInfoMappe.selectByUserIdList(userIds);
        List<UserBankCardInfoDO> bankCards = bankCardMapper
            .selectDefaultBankCardByUserIds(userIds);

        Map<Long, String> idCardMap = new HashMap<>();// userId:realName
        idCards.forEach(n -> idCardMap.put(n.getUserId(), n.getRealName()));

        Map<Long, String> bankCardMap = new HashMap<>();// userId:accountNo
        bankCards.forEach(n -> bankCardMap.put(n.getUserId(), n.getAccountNo()));
        List<UserInfoVo> userVos = new ArrayList<>();
        users.forEach(n -> {
            UserInfoVo userVo = new UserInfoVo();
            BeanUtils.copyProperties(n, userVo);
            userVo.setRealName(idCardMap.get(n.getId()));
            userVo.setAccountNo(bankCardMap.get(n.getId()));
            userVos.add(userVo);
        });
        return userVos;
    }
}
