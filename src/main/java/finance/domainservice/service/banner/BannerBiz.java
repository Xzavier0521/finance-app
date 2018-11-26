package finance.domainservice.service.banner;

import java.util.List;

import finance.api.model.vo.info.BannerDetailVO;

/**
 * @program: finance-server
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-08-20 17:57
 **/
public interface BannerBiz {
	/**
	 * 功能描述:查询banner根据code和type
	 * 
	 * @author: moruihai
	 * @date: 2018/8/20 18:04
	 * @param: [pageCode,
	 *             bannerType]
	 * @return: java.util.List<finance.model.vo.BannerDetailVO>
	 */
	List<BannerDetailVO> queryBannerByCodeAndType(Long pageCode, Long bannerType);
}
