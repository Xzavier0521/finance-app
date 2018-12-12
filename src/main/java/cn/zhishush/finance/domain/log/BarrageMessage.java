package cn.zhishush.finance.domain.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 * 注释
 * </p>
 *
 * @author lili
 * @version 1.0: BarrageMessage.java, v 0.1 2018/9/29 上午9:59 lili Exp $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BarrageMessage {

	private Long id;

	private String messageCode;

	private String messageDesc;

	private String creator;

	private String updator;

	private Integer version;

	private Date createTime;

	private Date updateTime;
}
