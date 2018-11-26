package finance.domainservice.service.banner.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import finance.core.dal.dataobject.BannerInfoDO;
import org.springframework.stereotype.Service;

import finance.domainservice.service.banner.BannerBiz;
import finance.core.dal.dao.BannerInfoDAO;
import finance.api.model.vo.info.BannerDetailVO;

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
	private BannerInfoDAO financeBannerInfoMapper;

	@Override
	public List<BannerDetailVO> queryBannerByCodeAndType(Long pageCode, Long bannerType) {
		List<BannerInfoDO> bannerInfoDOList = financeBannerInfoMapper.queryBannerByCodeAndType(pageCode.intValue(),
				bannerType.intValue());
		BannerDetailVO bannerDetailVO;
		List<BannerDetailVO> bannerList = new ArrayList<>();
		if (bannerInfoDOList != null && bannerInfoDOList.size() > 0) {
			for (BannerInfoDO fbi : bannerInfoDOList) {
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
