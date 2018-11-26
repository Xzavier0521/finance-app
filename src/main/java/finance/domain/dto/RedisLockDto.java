package finance.domain.dto;

/**
 * @program: finance-server
 *
 * @description:
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-29 16:37
 **/
public class RedisLockDto<T, K> {
	/** 锁Id */
	private String lockId;
	/** 是否获取到锁 */
	private Boolean hasLock = false;
	/** 方法入参 */
	private T param;
	/** 方法返回值 */
	private K res;

	public RedisLockDto(String lockId, T param, K res) {
		this.lockId = lockId;
		this.param = param;
		this.res = res;
	}

	public String getLockId() {
		return lockId;
	}
	public T getParam() {
		return param;
	}
	public K getRes() {
		return res;
	}
	public void setRes(K res) {
		this.res = res;
	}
	/** 是否获取到锁并执行 */
	public Boolean hasLock() {
		return hasLock;
	}
	public void setHasLock(Boolean hasLock) {
		this.hasLock = hasLock;
	}

	@Override
	public String toString() {
		return "RedisLockDto:{“lockId\":\"" + lockId + "\",\"hasLock\":\"" + hasLock + "\",\"param\":\"" + param
				+ "\",\"res\":\"" + res + "\"}  ";
	}

}
