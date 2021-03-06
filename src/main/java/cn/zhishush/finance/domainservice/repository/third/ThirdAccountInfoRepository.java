package cn.zhishush.finance.domainservice.repository.third;

import cn.zhishush.finance.domain.user.ThirdAccountInfo;

/**
 * <p>账户绑定信息</p>
 * 
 * @author lili
 * @version $Id: ThirdAccountInfoRepository.java, v0.1 2018/10/24 3:40 PM lili Exp $
 */
public interface ThirdAccountInfoRepository {

    /**
     * 查询用户绑定信息
     * @param userId 用户id
     * @return ThirdAccountInfo
     */
    ThirdAccountInfo queryByCondition(Long userId);

    ThirdAccountInfo queryByOenId(String openId);
}
