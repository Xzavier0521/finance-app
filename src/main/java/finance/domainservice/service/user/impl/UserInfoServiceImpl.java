package finance.domainservice.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import finance.api.model.vo.userinfo.UserInfoVo;
import finance.core.dal.dao.UserInfoDAO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import finance.domainservice.service.user.UserInfoService;
import finance.core.dal.dao.IdCardInfoDAO;
import finance.core.dal.dao.UserBankCardInfoDAO;
import finance.core.dal.dataobject.IdCardInfoDO;
import finance.core.dal.dataobject.UserBankCardInfoDO;
import finance.core.dal.dataobject.UserInfoDO;

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
