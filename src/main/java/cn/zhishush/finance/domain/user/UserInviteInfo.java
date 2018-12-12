package cn.zhishush.finance.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 注释
 * </p>
 *
 * @author lili
 * @version 1.0: UserInviteInfo.java, v 0.1 2018/9/29 上午11:31 lili Exp $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInviteInfo implements Serializable {

	private static final long serialVersionUID = -6823897888895059949L;
	private Long id;

	private Long userId;

	private Long parentUserId;

	private Integer inviteType;

	private Integer isDelete;

	private String creator;

	private String updator;

	private Integer version;

	private Date createTime;

	private Date updateTime;
}
