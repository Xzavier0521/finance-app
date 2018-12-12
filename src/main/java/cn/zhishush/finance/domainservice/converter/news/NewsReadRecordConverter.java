package cn.zhishush.finance.domainservice.converter.news;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.news.NewsReadRecordDO;
import cn.zhishush.finance.domain.news.NewsReadRecord;
import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * <p>资讯</p>
 *
 * @author lili
 * @version 1.0: NewsReadRecordConverter.java, v0.1 2018/12/5 3:36 PM PM lili Exp $
 */
public class NewsReadRecordConverter {

    public static NewsReadRecord convert(NewsReadRecordDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        NewsReadRecord to = new NewsReadRecord();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static NewsReadRecordDO convert(NewsReadRecord from) {
        if (Objects.isNull(from)) {
            return null;
        }
        NewsReadRecordDO to = new NewsReadRecordDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<NewsReadRecord> convert2List(List<NewsReadRecordDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<NewsReadRecord> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<NewsReadRecordDO> convert2DoList(List<NewsReadRecord> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<NewsReadRecordDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
