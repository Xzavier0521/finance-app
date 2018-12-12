package cn.zhishush.finance.core.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author lyao
 * @time 2017-11-17 上午9:37:45
 * @Description:数字计算工具类
 */
public class ArithUtil {

	private static final int DEF_DIV_SCALE = 10;
	private ArithUtil() {
	}

	/**
	 * @author lyao
	 * @time 2017-11-17 上午9:37:54
	 * @Title add
	 * @Description: 两数相加
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * @author lyao
	 * @time 2017-11-17 上午9:37:54
	 * @Title moreAdd
	 * @Description: 多数相加
	 */
	public static double moreAdd(double... addends) {

		BigDecimal result = new BigDecimal(0.0);
		for (double addend : addends) {
			BigDecimal aAddend = new BigDecimal(Double.toString(addend));
			result = result.add(aAddend);
		}
		return result.doubleValue();
	}

	/**
	 * @author lyao
	 * @time 2017-11-17 上午9:37:54
	 * @Title sub
	 * @Description: 两数相减
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	/**
	 * @author lyao
	 * @time 2017-11-17 上午9:37:54
	 * @Title sub
	 * @Description: 两数相减
	 */
	public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
		return v1.subtract(v2);
	}
	/**
	 * @author lyao
	 * @time 2017-11-17 上午9:37:54
	 * @Title mul
	 * @Description: 两数相乘
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).setScale(2, BigDecimal.ROUND_UP).doubleValue();
	}

	public static double mul_string(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * @author lyao
	 * @time 2017-11-17 上午9:37:54
	 * @Title div
	 * @Description: 两数相除
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * @author lyao
	 * @time 2017-11-17 上午9:37:54
	 * @Title div
	 * @Description: 带精度两数相除
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_UP).doubleValue();
	}

	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static double doubleFormat(Double number, int bit) {
		if (number == null) {
			return 0.0;
		}
		DecimalFormat decimalFormat = null;
		switch (bit) {
			case 0 :
				decimalFormat = new DecimalFormat("#");
				break;
			case 1 :
				decimalFormat = new DecimalFormat("#.#");
				break;
			case 2 :
				decimalFormat = new DecimalFormat("#.##");
				break;
			case 3 :
				decimalFormat = new DecimalFormat("#.###");
				break;
			case 4 :
				decimalFormat = new DecimalFormat("#.####");
				break;
			default :
				return number;
		}

		String doubleStr = decimalFormat.format(number);
		return Double.parseDouble(doubleStr);
	}

	public static BigDecimal formatNum(BigDecimal num) {
		if (num == null) {
			return null;
		}
		DecimalFormat df2 = (DecimalFormat) DecimalFormat.getInstance();
		df2.applyPattern("0.00");
		return new BigDecimal(df2.format(num));
	}

	public static int fetchModNum(int totalPageNum, int pageNum) {

		int totalPage = totalPageNum / pageNum;
		int modeNum = totalPageNum % pageNum;
		return modeNum == 0 ? totalPage : totalPage + 1;

	}

	public static void main(String[] args) {
		BigDecimal v1 = new BigDecimal("99");
		BigDecimal v2 = new BigDecimal("1000");

		System.out.println(v1.compareTo(v2));

	}

}