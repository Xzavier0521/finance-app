package cn.zhishush.finance.domainservice.service.user.query;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.domain.user.UserInfo;

/**
 * <p>用户信息查询</p>
 *
 * @author lili
 * @version 1.0: UserInfoQueryService.java.java, v 0.1 2018/9/27 下午8:33 lili Exp$
 */
public interface UserInfoQueryService {

    /**
     * 查询未唤醒的邀请好友
     *
     * @param userId   用户id
     * @param pageNum  第几页
     * @param pageSize 每页记录数
     * @return Page<UserInfo>
     */
    Page<UserInfo> querySleepUserInfo(Long userId, int pageNum, int pageSize);
}
