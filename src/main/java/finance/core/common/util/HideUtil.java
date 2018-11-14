package finance.core.common.util;
/**
 * @author lyao 
 * @time   2018-1-22 下午3:04:16
 * @Description: 脱敏
 */
public class HideUtil {
	public static String hide(String str, int prefixShowCount,
			int suffixShowCount) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}
		if (prefixShowCount < 0) {
			prefixShowCount = 0;
		}
		if (suffixShowCount < 0) {
			suffixShowCount = 0;
		}
		if ((prefixShowCount >= str.length())
				|| (suffixShowCount >= str.length())) {
			return str;
		}
		char[] chs = str.toCharArray();
		int i = prefixShowCount;
		for (int k = chs.length - suffixShowCount; i < k; ++i) {
			chs[i] = '*';
		}
		return new String(chs);
	}

	public static String hideCardNo(String cardNo) {
		if ((cardNo == null) || (cardNo.length() == 0)) {
			return cardNo;
		}
		if (cardNo.length() < 5) {
			return hide(cardNo, 0, 1);
		}
		if (cardNo.length() < 10) {
			return hide(cardNo, 3, 1);
		}
		return hide(cardNo, 6, 4);
	}

	public static String hideName(String name) {
		if ((name == null) || (name.length() == 0)) {
			return name;
		}
		if (name.length() < 3) {
			return hide(name, 1, 0);
		}
		return hide(name, 1, 1);
	}

	public static String hideEnName(String name) {
		if ((name == null) || (name.length() == 0)) {
			return name;
		}
		if (name.length() < 8) {
			return hide(name, 1, 0);
		}
		return hide(name, 1, 1);
	}

	public static String hideMobile(String mobile) {
		if ((mobile == null) || (mobile.length() == 0)) {
			return mobile;
		}
		return hide(mobile, 3, 3);
	}

	public static String hideUserCert(String cert) {
		if ((cert == null) || (cert.length() == 0)) {
			return cert;
		}

		if ((cert.length() == 15) || (cert.length() == 18)) {
			return hide(cert, 4, 3);
		}

		return hide(cert, 2, 3);
	}

	public static String hideSecurityLink(String str) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}
		return hide(str, 6, 6);
	}

	public static String hideCvn(String cvn) {
		if ((cvn == null) || (cvn.length() == 0)) {
			return cvn;
		}
		if (cvn.length() == 3) {
			return hide(cvn, 1, 0);
		}
		return hide(cvn, 1, 1);
	}

	public static String hideExpireDate(String expireDate) {
		if ((expireDate == null) || (expireDate.length() == 0)) {
			return expireDate;
		}
		return hide(expireDate, 1, 1);
	}

	public static String hideEmail(String email) {
		if ((email == null) || ("".equals(email.trim()))) {
			return email;
		}
		int index = email.indexOf("@");
		if (index < 0) {
			return email;
		}
		String sub1 = hide(email.substring(0, index), 1, 1);
		String sub2 = email.substring(index, email.length());
		email = sub1 + sub2;

		return email;
	}
	public static void main(String[] args){
		System.out.println(hideMobile("18551721604"));
		System.out.println(hideEnName("姚磊"));
	}
}