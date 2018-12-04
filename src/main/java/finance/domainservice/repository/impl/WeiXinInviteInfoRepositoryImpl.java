package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.api.model.base.Page;
import finance.api.model.condition.WeiXinInviteInfoQueryCondition;
import finance.core.dal.dao.WeiXinInviteInfoDAO;
import finance.domain.weixin.WeiXinInviteInfo;
import finance.domainservice.converter.WeiXinInviteInfoConverter;
import finance.domainservice.repository.WeiXinInviteInfoRepository;

/**
 * <p>微信用户邀请关系</p>
 *
 * @author lili
 * @version 1.0: WeiXinInviteInfoRepositoryImpl.java, v0.1 2018/12/3 5:28 PM PM lili Exp $
 */
@Slf4j
@Repository("weiXinInviteInfoRepository")
public class WeiXinInviteInfoRepositoryImpl implements WeiXinInviteInfoRepository {

    @Resource
    private WeiXinInviteInfoDAO weiXinInviteInfoDAO;

    @Override
    public int save(WeiXinInviteInfo weiXinInviteInfo) {
        return weiXinInviteInfoDAO
            .insertSelective(WeiXinInviteInfoConverter.convert(weiXinInviteInfo));
    }

    @Override
    public int delete(String activityCode, String openId) {
        return weiXinInviteInfoDAO.delete(activityCode, openId);
    }

    @Override
    public WeiXinInviteInfo query(String activityCode, String openId) {

        WeiXinInviteInfo weiXinInviteInfo = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("activityCode", activityCode);
        parameters.put("openId", openId);
        List<WeiXinInviteInfo> weiXinInviteInfoList = WeiXinInviteInfoConverter
            .convert2List(weiXinInviteInfoDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(weiXinInviteInfoList)) {
            weiXinInviteInfo = weiXinInviteInfoList.get(0);
        }
        return weiXinInviteInfo;
    }

    @Override
    public void batchSave(List<WeiXinInviteInfo> weiXinInviteInfoList) {
        weiXinInviteInfoDAO
            .batchInsert(WeiXinInviteInfoConverter.convert2DoList(weiXinInviteInfoList));
    }

    @Override
    public Page<WeiXinInviteInfo> query4Page(WeiXinInviteInfoQueryCondition condition) {

        Page<WeiXinInviteInfo> page = new Page<>(condition.getPageSize(),
            (long) condition.getCurrentPage());
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("activityCode", condition.getActivityCode());
        parameters.put("isSend", condition.getIsSend().getCode());
        parameters.put("page", page);
        int count = weiXinInviteInfoDAO.count(parameters);
        page.setTotalCount((long) count);
        if (count > 0) {
            page.setDataList(
                WeiXinInviteInfoConverter.convert2List(weiXinInviteInfoDAO.query(parameters)));
        }
        return page;
    }

    @Override
    public int update(WeiXinInviteInfo weiXinInviteInfo) {
        return weiXinInviteInfoDAO
            .updateByPrimaryKeySelective(WeiXinInviteInfoConverter.convert(weiXinInviteInfo));
    }
}
