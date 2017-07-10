package suangrenduobao.daiqile.com.mvlib.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证统一管理
 * @author Mr Guo
 *
 * 手机号码
 * 身份证号码
 * 邮箱
 * 验证固话号码
 * 验证传真号码
 * 验证QQ号码
 */
public final class RegexValidateUtil {

//	/**
//	 * 正则表达式：验证用户名
//	 */
//	public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";
//
//	/**
//	 * 正则表达式：验证密码
//	 */
//	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

	/**
	 * 正则表达式：验证手机号
	 */
	public static final String REGEX_MOBILE = "^1(3[0-9]|5[^4,\\D]|8[0,2,5-9]|7[\\d{1}])\\d{8}$";

//	/**
//	 * 正则表达式：验证邮箱
//	 */
//	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
//
//	/**
//	 * 正则表达式：验证汉字
//	 */
//	public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
//
//	/**
//	 * 正则表达式：验证身份证
//	 */
	public static final String REGEX_ID_CARD = "^\\d{15}|\\d{17}[0-9Xx]$";
//
//	/**
//	 * 正则表达式：验证URL
//	 */
//	public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
//
//	/**
//	 * 正则表达式：验证IP地址
//	 */
//	public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

	private static boolean check(String regex, String cellphone){
		//Pattern 模版   Matcher 匹配
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher =pattern.matcher(cellphone);
		return matcher.matches();
	}

	/**
	 *  验证手机号码
	 *  
	 *  移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
	 *  联通号码段:130、131、132、136、185、186、145
	 *  电信号码段:133、153、180、189
	 * @param cellphone  手机号码
	 * @return true(手机号码正确)
	 */
	public static boolean checkCellphone(String cellphone) {
		return check(RegexValidateUtil.REGEX_MOBILE,cellphone);
	}


//	/**
//	 * 验证身份证号码
//	 * @param iDStr  身份证
//	 * @return true（身份证正确）
//	 */
	public static boolean checkIDCard(String iDStr) {
		return check(RegexValidateUtil.REGEX_ID_CARD, iDStr);
	}
//
//
//	/**
//	 * 验证邮箱
//	 * @param email
//	 * @return
//	 */
//	public static boolean checkEmail(String email) {
//		return check(RegexValidateUtil.REGEX_EMAIL, email);
//	}
//
//	/**
//	 * 验证密码
//	 * @param email
//	 * @return
//	 */
//	public static boolean checkPassword(String password) {
//		return check(RegexValidateUtil.REGEX_PASSWORD, password);
//	}
//
//
//	/**
//	 * 验证URL
//	 * @param url
//	 * @return
//	 */
//	public static boolean checkUrl(String url) {
//		return check(RegexValidateUtil.REGEX_URL, url);
//	}
//
//	/**
//	 * 验证IP地址
//	 * @param ip
//	 * @return
//	 */
//	public static boolean checkIp(String ip) {
//		return check(RegexValidateUtil.REGEX_IP_ADDR, ip);
//	}


}
