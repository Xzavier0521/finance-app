package finance.domainservice.service.banner.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import finance.domainservice.service.banner.BannerBiz;
import finance.mapper.FinanceBannerInfoDAO;
import finance.model.po.FinanceBannerInfo;
import finance.api.model.vo.BannerDetailVO;

/**
 * @program: finance-server
 *
 * @description: bannerBIZ
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-20 17:58
 **/
@Service
public class BannerBizImpl implements BannerBiz {
    @Resource
    private FinanceBannerInfoDAO financeBannerInfoMapper;

    @Override
    public List<BannerDetailVO> queryBannerByCodeAndType(Long pageCode, Long bannerType) {
        List<FinanceBannerInfo> financeBannerInfoList = financeBannerInfoMapper
            .queryBannerByCodeAndType(pageCode.intValue(), bannerType.intValue());
        BannerDetailVO bannerDetailVO;
        List<BannerDetailVO> bannerList = new ArrayList<>();
        if (financeBannerInfoList != null && financeBannerInfoList.size() > 0) {
            for (FinanceBannerInfo fbi : financeBannerInfoList) {
                bannerDetailVO = new BannerDetailVO();
                bannerDetailVO.setId(fbi.getId());
                bannerDetailVO.setPageCode(pageCode);
                bannerDetailVO.setBannerType(bannerType);
                bannerDetailVO.setTitle(fbi.getTitle());
                bannerDetailVO.setSeqNo(fbi.getSeqNo());
                bannerDetailVO.setBannerUrl(fbi.getBannerUrl());
                bannerDetailVO.setRedirectUrl(fbi.getRedirectUrl());
                bannerList.add(bannerDetailVO);
            }
        }
        return bannerList;
    }
}
