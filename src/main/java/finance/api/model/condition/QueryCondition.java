package finance.api.model.condition;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import finance.core.common.constants.QueryConstants;

/**
 * <p>查询条件</p>
 * @author lili
 * @version 1.0: QueryCondition.java, v0.1 2018/11/26 7:00 PM lili Exp $
 */
public class QueryCondition implements QueryConstants, Serializable {
	private static final long serialVersionUID = -1631598695528368695L;

	/**
	 * 超时时间
	 */
	private Integer httpTimeout;
	/**
	 * 版本号
	 */
	private String version = DEFAULT_VERSION;
	/**
	 * 客户端标识
	 */
	private String clientId;
	/**
	 * 主键
	 */
	private String primaryKey;

	/**
	 * 默认构造
	 */
	public QueryCondition() {
	}

	/**
	 * 主键构造
	 *
	 * @param primaryKey
	 */
	public QueryCondition(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Integer getHttpTimeout() {
		return httpTimeout;
	}

	public void setHttpTimeout(Integer httpTimeout) {
		this.httpTimeout = httpTimeout;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
