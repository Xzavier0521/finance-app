package cn.zhishush.finance.domain.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 数据宝银行卡验证返回数据
 * </p>
 * 
 * @author lili
 * @version $Id: DataPayAuthResDto.java, v0.1 2018/11/14 3:43 PM lili Exp $
 */
@Data
public class DataPayAuthResDto implements Serializable {
	private static final long serialVersionUID = -1553947312458376300L;
	/**
	 * 请求code码
	 */
	private String code;
	/**
	 * code码说明
	 */
	private String message;
	/**
	 * 接口返回数据体
	 */
	private String data;
	/**
	 * 调用唯一标识
	 */
	private String seqNo;

}
