package finance.core.common.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.collections4.CollectionUtils;

import finance.api.model.response.ValidateResponse;

/**
 * <p>
 * 参数验证工具类
 * </p>
 * 
 * @author lili
 * @version $Id: ValidatorTools.java, v0.1 2018/11/14 4:06 PM lili Exp $
 */
public class ValidatorTools {

	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	public static ValidateResponse validate(Object obj) {

		ValidateResponse resp = new ValidateResponse(true, "成功");
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(obj);

		if (!CollectionUtils.isEmpty(violations)) {
			StringBuilder buf = new StringBuilder();
			for (ConstraintViolation<Object> violation : violations) {
				buf.append(violation.getMessage()).append(";");
			}
			resp.setStatus(false);
			resp.setErrorMsg(buf.toString());
			return resp;
		} else {
			return resp;
		}
	}

}
