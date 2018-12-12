package cn.zhishush.finance.web.controller.oauth.invite.builder;

import java.util.List;

import cn.zhishush.finance.domain.team.InviteInfoAndIncome;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.userAccount.InviteInfoAndIncomeVo;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version : InviteInfoAndIncomeBuilder.java.java, v 0.1 2018/9/27 下午8:33 lili
 *          Exp $
 */
public class InviteInfoAndIncomeBuilder {

	public static Page<InviteInfoAndIncomeVo> build(Page<InviteInfoAndIncome> resultPage) {
		Page<InviteInfoAndIncomeVo> responsePage = new Page<>(resultPage.getPageSize(), resultPage.getPageNum());
		BeanUtils.copyProperties(resultPage, responsePage, "dataList");
		if (CollectionUtils.isNotEmpty(resultPage.getDataList())) {
			List<InviteInfoAndIncomeVo> tos = Lists.newArrayListWithCapacity(resultPage.getDataList().size());
			InviteInfoAndIncomeVo to;
			for (InviteInfoAndIncome from : resultPage.getDataList()) {
				to = new InviteInfoAndIncomeVo();
				to.setUserId(from.getUserId());
				to.setParentUserId(from.getParentUserId());
				to.setPhoneNumber(from.getPhoneNumber());
				to.setTotalIncome(from.getTotalIncome());
				to.setPredictIncome(from.getPredictIncome());
				to.setRegisterDate(from.getRegisterDate());
				tos.add(to);
			}
			responsePage.setDataList(tos);
		}
		return responsePage;
	}

}
