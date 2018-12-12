package cn.zhishush.finance.domainservice.repository.third;

import cn.zhishush.finance.domain.third.ThirdChannelConfig;

/**
 * <p>第三方渠道配置</p>
 *
 * @author lili
 * @version 1.0: ThirdChannelConfigRepository.java, v0.1 2018/12/5 6:19 PM PM lili Exp $
 */
public interface ThirdChannelConfigRepository {

    ThirdChannelConfig query(String mobileNum);
}
