package finance.domainservice.service.userinfo;

import java.util.List;
import java.util.Map;

import finance.api.model.response.ResponseResult;
import finance.api.model.vo.invite.InviteOrdersVo;
import finance.api.model.vo.userinfo.UserProfitInfoDetailVo;
import finance.api.model.vo.userinfo.UserInfoDetailVo;
import finance.core.common.enums.PwdType;
import finance.core.dal.dataobject.UserBankCardInfoDO;
import finance.domain.dto.IdCardInfoDto;
import finance.domain.dto.UserBankCardDto;

/**
 * 用户信息接口.
 * 
 * @author hewenbin
 * @version v1.0 2018年7月3日 下午8:58:57 hewenbin
 */
public interface UserInfoBiz {

	/**
	 * 通过用户ID查询用户信息.
	 * 
	 * @param userId
	 *            > 0
	 * @return 用户基本信息
	 * @author hewenbin
	 * @version LoginService.java, v1.0 2018年7月3日 下午8:46:01 hewenbin
	 */
	UserInfoDetailVo queryUserInfo(Long userId);

	/**
	 * 通过用户ID查询用户收益信息.
	 * 
	 * @param userId
	 *            > 0
	 * @return 用户收益信息
	 * @author panzhongkang
	 * @version LoginService.java, v1.0 2018年9月7日 下午1:46:01 panzhongkang
	 */
	UserProfitInfoDetailVo queryUserProfitInfo(Long userId);

	/**
	 * 保存个人银行卡信息.
	 * 
	 * @param bankCardDto
	 *            not null
	 * @return
	 * @author hewenbin
	 * @version UserInfoBiz.java, v1.0 2018年7月10日 下午7:39:46 hewenbin
	 */
	ResponseResult<String> saveBankCard(UserBankCardDto bankCardDto);

	/**
	 * 查询个人默认银行卡信息.
	 * 
	 * @param userId
	 *            > 0
	 * @return
	 * @author hewenbin
	 * @version UserInfoBiz.java, v1.0 2018年7月10日 下午7:38:38 hewenbin
	 */
	UserBankCardInfoDO queryDefaultBankCard(Long userId);

	/**
	 * 保存密码.
	 * 
	 * @param pwtType
	 * @param userId
	 * @param pwd
	 * @param mobileCode
	 *            如果是第一次设置，则不校验，否则会校验
	 * @author hewenbin
	 * @version UserInfoBiz.java, v1.0 2018年7月11日 下午2:50:12 hewenbin
	 */
	ResponseResult<String> savePwd(PwdType pwtType, Long userId, String pwd, String mobileCode);

	/**
	 * 查询用户邀请的人员列表（两级）.
	 * 
	 * @param userId
	 * @param maxCount
	 *            团队成员查询上限
	 * @param type
	 *            0：全部，1：一级，2：二级
	 * @return
	 * @author hewenbin
	 * @version UserInfoBiz.java, v1.0 2018年7月11日 下午7:19:05 hewenbin
	 */
	Map<String, Object> queryInviteList(Long userId, int maxCount, int type);

	Map<String, Object> queryInviteListEx(Long userId, int maxCount, int type);

	/**
	 * 查询邀请人排行榜（前三十个）.
	 * 
	 * <pre>
	 * 查询下一级和二级
	 * </pre>
	 * 
	 * @author hewenbin
	 * @version UserInfoBiz.java, v1.0 2018年8月17日 下午3:55:24 hewenbin
	 */
	List<InviteOrdersVo> queryInviteOrders();

	ResponseResult<Boolean> saveIdCardInfo(IdCardInfoDto idCardInfoDto);

	Boolean isFlowWeChatPub(String openId);
}
