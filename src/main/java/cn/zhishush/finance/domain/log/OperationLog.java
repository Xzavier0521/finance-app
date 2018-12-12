package cn.zhishush.finance.domain.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 * 用户操作日志
 * </p>
 *
 * @author lili
 * @version 1.0: OperationLog.java, v 0.1 2018/9/28 下午4:23 lili Exp $
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationLog {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 日志id
	 */
	private Long logId;

	/**
	 * 用户id
	 */
	private Long userId;

	private String opCode;

	private String opName;

	private String creator;

	private String updator;

	private Integer version;

	private Date createTime;

	private Date updateTime;
}
