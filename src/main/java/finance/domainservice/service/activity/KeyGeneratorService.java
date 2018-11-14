package finance.domainservice.service.activity;

/**
 * <p>注释</p>
 * @author lili¬
 * @version $Id: KeyGeneratorService.java, v0.1 2018/11/6 3:01 PM lili¬ Exp $
 */
public interface KeyGeneratorService {

    /**
     * 根据code 返回自增key
     * @param code 自定义代码
     * @return Long
     */
    Long generatorKeyByCode(String code);
}
