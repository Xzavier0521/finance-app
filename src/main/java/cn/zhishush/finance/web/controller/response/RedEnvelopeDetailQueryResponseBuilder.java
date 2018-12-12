package cn.zhishush.finance.web.controller.response;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.redenvelope.ParticipantInfoVO;
import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.domain.activity.ParticipantInfo;

import com.google.common.collect.Lists;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: RedEnvelopeDetailQueryResponseBuilder.java, v0.1 2018/10/20 1:39 PM lili Exp $
 */
public class RedEnvelopeDetailQueryResponseBuilder {

    public static Page<ParticipantInfoVO> build(Page<ParticipantInfo> from) {

        Page<ParticipantInfoVO> participantInfoVOPage = new Page<>(from.getPageSize(),
            from.getPageNum());
        participantInfoVOPage.setTotalCount(from.getTotalCount());
        if (CollectionUtils.isNotEmpty(from.getDataList())) {
            List<ParticipantInfoVO> items = Lists.newArrayList();
            ParticipantInfoVO item;
            for (ParticipantInfo participantInfo : from.getDataList()) {
                item = new ParticipantInfoVO();
                ConvertBeanUtil.copyBeanProperties(participantInfo, item);
                items.add(item);
            }
            participantInfoVOPage.setDataList(items);

        }

        return participantInfoVOPage;
    }
}
