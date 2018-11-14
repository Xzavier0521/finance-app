package finance.domainservice.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import finance.api.model.vo.FinanceUserInfoVo;
import finance.domainservice.service.user.UserInfoService;
import finance.mapper.FinanceIdCardInfoDAO;
import finance.mapper.FinanceUserBankCardInfoDAO;
import finance.mapper.FinanceUserInfoDAO;
import finance.model.po.FinanceIdCardInfo;
import finance.model.po.FinanceUserBankCardInfo;
import finance.model.po.FinanceUserInfo;

/**
 * @author hewenbin
 * @version v1.0 2018年7月12日 下午5:36:16 hewenbin
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private FinanceUserInfoDAO         userInfoMapper;
    @Resource
    private FinanceUserBankCardInfoDAO bankCardMapper;
    @Resource
    private FinanceIdCardInfoDAO       idCardInfoMappe;

    @Override
    public List<FinanceUserInfoVo> queryUserInfos(List<Long> userIds) {
        List<FinanceUserInfo> users = userInfoMapper.selectByPrimaryKeys(userIds);
        List<FinanceIdCardInfo> idCards = idCardInfoMappe.selectByUserIdList(userIds);
        List<FinanceUserBankCardInfo> bankCards = bankCardMapper
            .selectDefaultBankCardByUserIds(userIds);

        Map<Long, String> idCardMap = new HashMap<>();// userId:realName
        idCards.forEach(n -> idCardMap.put(n.getUserId(), n.getRealName()));

        Map<Long, String> bankCardMap = new HashMap<>();// userId:accountNo
        bankCards.forEach(n -> bankCardMap.put(n.getUserId(), n.getAccountNo()));
        List<FinanceUserInfoVo> userVos = new ArrayList<>();
        users.forEach(n -> {
            FinanceUserInfoVo userVo = new FinanceUserInfoVo();
            BeanUtils.copyProperties(n, userVo);
            userVo.setRealName(idCardMap.get(n.getId()));
            userVo.setAccountNo(bankCardMap.get(n.getId()));
            userVos.add(userVo);
        });
        return userVos;
    }
}
