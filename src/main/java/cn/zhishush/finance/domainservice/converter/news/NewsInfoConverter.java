package cn.zhishush.finance.domainservice.converter.news;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.news.NewsInfoDO;
import cn.zhishush.finance.domain.news.NewsInfo;
import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * <p>资讯</p>
 *
 * @author lili
 * @version 1.0: NewsInfoConverter.java, v0.1 2018/12/5 4:45 PM PM lili Exp $
 */
public class NewsInfoConverter {
    public static NewsInfo convert(NewsInfoDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        NewsInfo to = new NewsInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static NewsInfoDO convert(NewsInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        NewsInfoDO to = new NewsInfoDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<NewsInfo> convert2List(List<NewsInfoDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<NewsInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<NewsInfoDO> convert2DoList(List<NewsInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<NewsInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
