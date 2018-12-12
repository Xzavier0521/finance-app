package cn.zhishush.finance.domainservice.service.activity;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: KeyGeneratorService.java, v0.1 2018/11/26 9:31 AM lili Exp $
 */
public interface KeyGeneratorService {

	/**
	 * 根据code 返回自增key
	 * 
	 * @param code
	 *            自定义代码
	 * @return Long
	 */
	Long generatorKeyByCode(String code);
}
