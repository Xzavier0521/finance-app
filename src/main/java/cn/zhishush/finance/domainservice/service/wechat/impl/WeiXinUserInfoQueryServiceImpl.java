package cn.zhishush.finance.domainservice.service.wechat.impl;

import java.io.InputStream;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.zhishush.finance.api.model.vo.weixin.WeiXinUserInfoDetailVO;
import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.common.util.HttpClientUtil;
import cn.zhishush.finance.domain.user.ThirdAccountInfo;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domainservice.repository.third.ThirdAccountInfoRepository;
import cn.zhishush.finance.domainservice.service.aliyunOss.StoreClient;
import cn.zhishush.finance.domainservice.service.wechat.WechatService;
import cn.zhishush.finance.domainservice.service.wechat.WeiXinUserInfoQueryService;
import cn.zhishush.finance.ext.api.model.WeiXinUserInfoDetail;
import cn.zhishush.finance.ext.integration.weixin.WeiXinUserInfoQueryClient;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: WeiXinUserInfoQueryServiceImpl.java, v0.1 2018/12/2 12:40 PM PM lili Exp $
 */
@Service("weiXinUserInfoQueryService")
public class WeiXinUserInfoQueryServiceImpl implements WeiXinUserInfoQueryService {

    @Resource
    private WeiXinUserInfoQueryClient  weiXinUserInfoQueryClient;

    @Resource
    private ThirdAccountInfoRepository thirdAccountInfoRepository;
    @Resource
    private WechatService              wechatService;
    @Resource
    private StoreClient                storeClient;

    @Override
    public WeiXinUserInfoDetailVO query(UserInfo userInfo) {

        WeiXinUserInfoDetailVO weiXinUserInfoDetailVO = null;
        String token = wechatService.getWechatPubAccessToken();
        ThirdAccountInfo thirdAccountInfo = thirdAccountInfoRepository
            .queryByCondition(userInfo.getId());
        if (Objects.nonNull(thirdAccountInfo)) {
            WeiXinUserInfoDetail weiXinUserInfoDetail = weiXinUserInfoQueryClient
                .queryUserInfo(token, thirdAccountInfo.getOpenId());
            weiXinUserInfoDetailVO = new WeiXinUserInfoDetailVO();
            ConvertBeanUtil.copyBeanProperties(weiXinUserInfoDetail, weiXinUserInfoDetailVO);
            if (StringUtils.isNotBlank(weiXinUserInfoDetailVO.getHeadimgurl())) {
                String picName = userInfo.getMobileNum() + "headImg.jpg";
                storeClient.init();
                InputStream inputStream = HttpClientUtil
                    .getImageStream(weiXinUserInfoDetailVO.getHeadimgurl());
                String headImgUrl = storeClient.putObject(picName, inputStream);
                weiXinUserInfoDetailVO.setHeadimgurl(headImgUrl);
            }

        }
        return weiXinUserInfoDetailVO;
    }

}
