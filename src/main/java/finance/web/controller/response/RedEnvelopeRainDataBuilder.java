package finance.web.controller.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import finance.api.model.base.Page;
import finance.api.model.request.RedEnvelopeRainDataQueryRequest;
import finance.api.model.vo.activity.RedEnvelopeRainDataVO;
import finance.api.model.vo.activity.UserCurrentRankingVO;
import finance.core.common.util.CommonUtils;
import finance.core.common.util.ConvertBeanUtil;
import finance.domain.activity.RedEnvelopeRainData;

/**
 * <p>红包雨活动数据</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainDataBuilder.java, v0.1 2018/11/15 12:27 AM PM lili Exp $
 */
public class RedEnvelopeRainDataBuilder {

    public static Page<RedEnvelopeRainDataVO> build(RedEnvelopeRainDataQueryRequest request,
                                                    Page<RedEnvelopeRainData> dataPage) {
        Page<RedEnvelopeRainDataVO> page = new Page<>(request.getPageSize(), request.getPageNum());
        ConvertBeanUtil.copyBeanProperties(dataPage, page);
        if (CollectionUtils.isNotEmpty(dataPage.getDataList())) {
            List<RedEnvelopeRainDataVO> dataList = Lists
                .newArrayListWithCapacity(dataPage.getDataList().size());
            RedEnvelopeRainDataVO data;
            for (RedEnvelopeRainData redEnvelopeRainData : dataPage.getDataList()) {
                data = new RedEnvelopeRainDataVO();
                ConvertBeanUtil.copyBeanProperties(redEnvelopeRainData, data);
                if (StringUtils.isNotBlank(redEnvelopeRainData.getMobilePhone())) {
                    data.setMobilePhone(
                        CommonUtils.mobileEncrypt(redEnvelopeRainData.getMobilePhone()));
                }
                data.setTotalAmount(Objects.nonNull(redEnvelopeRainData.getTotalAmount())
                    ? redEnvelopeRainData.getTotalAmount().longValue()
                    : 0);
                dataList.add(data);
            }
            page.setDataList(dataList);
        }
        return page;
    }

    public static List<UserCurrentRankingVO> build(List<RedEnvelopeRainData> redEnvelopeRainDataList) {
        List<UserCurrentRankingVO> userCurrentRankingVOS = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(redEnvelopeRainDataList)) {
            UserCurrentRankingVO userCurrentRankingVO;
            for (RedEnvelopeRainData redEnvelopeRainData : redEnvelopeRainDataList) {
                userCurrentRankingVO = new UserCurrentRankingVO();
                userCurrentRankingVO.setRanking(String.valueOf(redEnvelopeRainData.getRanking()));
                userCurrentRankingVO.setMobilePhone(redEnvelopeRainData.getMobilePhone());
                userCurrentRankingVO.setTotalNum(redEnvelopeRainData.getTotalNum());
                userCurrentRankingVO
                    .setTotalAmount(redEnvelopeRainData.getTotalAmount().longValue());
                userCurrentRankingVOS.add(userCurrentRankingVO);
            }
        }
        return userCurrentRankingVOS;
    }

}
