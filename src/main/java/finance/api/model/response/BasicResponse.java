package finance.api.model.response;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import finance.core.common.enums.ReturnCode;

/**
 * <p>
 * 基础返回结果
 * </p>
 *
 * @author lili
 * @version 1.0: BasicResponse.java, v0.1 2018/11/14 5:36 PM PM lili Exp $
 */
@Data
public class BasicResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	protected boolean isSuccess;

	/**
	 * 返回码
	 */
	private String returnCode;

	/**
	 * 返回消息
	 */
	private String returnMessage;

	/**
	 * 扩展信息，JSON map
	 */
	private Map<String, Object> extension;

	public BasicResponse() {
		this.returnCode = ReturnCode.SUCCESS.getCode();
		this.returnMessage = ReturnCode.SUCCESS.getDesc();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
