package cn.zhishush.finance.domain.user;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 用户操作日志
 * </p>
 *
 * @author lili
 * @version 1.0: UserOperationLog.java, v 0.1 2018/9/28 下午1:59 lili Exp $
 */
@Data
@Builder
public class UserOperationLog {

	private Long userId;

	private Date updateTime;
}
