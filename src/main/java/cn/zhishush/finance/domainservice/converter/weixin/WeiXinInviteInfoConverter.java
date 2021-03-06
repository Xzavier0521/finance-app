package cn.zhishush.finance.domainservice.converter.weixin;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import cn.zhishush.finance.core.dal.dataobject.weixin.WeiXinInviteInfoDO;
import cn.zhishush.finance.domain.weixin.WeiXinInviteInfo;
import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;

/**
 * <p>微信用户邀请关系</p>
 *
 * @author lili
 * @version 1.0: WeiXinInviteInfoConverter.java, v0.1 2018/12/3 5:43 PM PM lili Exp $
 */
public class WeiXinInviteInfoConverter {

    public static WeiXinInviteInfo convert(WeiXinInviteInfoDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        WeiXinInviteInfo to = new WeiXinInviteInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static WeiXinInviteInfoDO convert(WeiXinInviteInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        WeiXinInviteInfoDO to = new WeiXinInviteInfoDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        /*  if (StringUtils.isNotBlank(from.getNickName())) {
            to.setNickName(EmojiStringUtils.replaceEmoji(from.getNickName()));
        }
        if (StringUtils.isNotBlank(from.getParentNickName())) {
            to.setParentNickName(EmojiStringUtils.replaceEmoji(from.getParentNickName()));
        
        }
        if (StringUtils.isBlank(from.getParentNickName())) {
            to.setParentNickName(CommonConstant.DEFAULT_WE_CHAT_NUMBER);
        }*/
        if (Objects.nonNull(from.getIsSend())) {
            to.setIsSend(from.getIsSend().getCode());
        }
        return to;
    }

    public static List<WeiXinInviteInfo> convert2List(List<WeiXinInviteInfoDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<WeiXinInviteInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<WeiXinInviteInfoDO> convert2DoList(List<WeiXinInviteInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<WeiXinInviteInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
