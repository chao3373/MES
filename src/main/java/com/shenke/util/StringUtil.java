package com.shenke.util;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 字符串工具类
 * 
 * @author Administrator
 *
 */
public class StringUtil {

	@Resource

	/**
	 * 判断是否为空
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		if (string == null || "".equals(string.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否不是空
	 * 
	 * @return
	 */
	public static boolean isNotEmpty(String string) {
		if ((string != null) && !"".equals(string.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 下单时候生成发货编码
	 * 
	 * @param code
	 * @return
	 */
	public static String formatCode(String code) {

		System.out.println("util code:  "+code);
		int length = code.length();
		Integer num = Integer.parseInt(code.substring(length - 8, length)) + 1;

		System.out.println("util num:  "+ num);

		String codeNum = num.toString();

		System.out.println("util codeNum : "+codeNum);

		int codeLength = codeNum.length();

		System.out.println("codeLength : "+codeLength);

		for (int i = 8; i > codeLength; i--) {
			System.out.println("for循环codeNum ：" +codeNum);
			codeNum = "0" + codeNum;
		}
		return codeNum;
	}
}
