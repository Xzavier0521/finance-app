package finance.domainservice.service.invitefriends;

import finance.api.model.response.ResponseResult;
import finance.domain.UserInfo;

/**
 * <p>唤醒好友</p>
 *
 * @author lili
 * @version :1.0 WakeupFriendsService.java, v 0.1 2018/9/28 下午8:56 lili Exp $
 */
public interface WakeupFriendsService {

    /**
     *  支付金币邀请好友
     * @param parentUserId 邀请者用户id
     * @param userId 被邀请者用户id
     * @param coinNum  金币数
     * @return ResponseResult
     */
    ResponseResult<UserInfo> wakeup(Long parentUserId, Long userId, int coinNum);

}
