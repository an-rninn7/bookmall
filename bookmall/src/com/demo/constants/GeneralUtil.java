/**   
 * Copyright (c) 2004-2015 i-Sprint Technologies, Inc.
 * address: 
 * All rights reserved. 
 * 
 * This software is the confidential and proprietary information of 
 * i-Sprint Technologies, Inc. ("Confidential Information").  You shall not 
 * disclose such Confidential Information and shall use it only in 
 * accordance with the terms of the license agreement you entered into 
 * with i-Sprint. 
 *
 * @Title: GeneralUtil.java 
 * @author yongget 
 * @Package com.isprint.cnaac.portal.utils 
 * @Description: TODO(simple description this file what to do.) 
 * @date Mar 24, 2015 5:42:51 PM 
 * @version V1.0   
 */
package com.demo.constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: GeneralUtil
 * @Description: TODO(simple description this class what to do.)
 * @author yongget
 * @version 1.0
 */
public class GeneralUtil {

	private static final SimpleDateFormat YYMMDD = new SimpleDateFormat(
			"yyyyMMdd");

	private static final SimpleDateFormat YYMMDDHHMMSS = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}

	/**
	 * @Title: validatorRegex
	 * @Description: TODO
	 * @author yongget
	 * @date Mar 24, 2015 5:46:47 PM
	 * @param email
	 * @return
	 * @return boolean
	 */
	public static boolean validatorRegex(String regexStr, String value) {
		Pattern p = Pattern.compile(regexStr);
		Matcher m = p.matcher(value);
		return m.matches();
	}

	/**
	 * @Title: getYMD
	 * @Description: TODO(simple description this method what to do.)
	 * @author yongget
	 * @date Mar 27, 2015 5:51:44 PM
	 * @return
	 * @return String
	 */
	public static String getYMD() {
		return YYMMDD.format(new Date());
	}

	public static String getBizNo() {
		return YYMMDDHHMMSS.format(new Date());
	}

	public static String getCode(int length, int type) {
		StringBuffer buffer = null;
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		r.setSeed(new Date().getTime());
		switch (type) {
		case 0:
			buffer = new StringBuffer("0123456789");
			break;
		case 1:
			buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
			break;
		case 2:
			buffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			break;
		case 3:
			buffer = new StringBuffer(
					"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
			break;
		case 4:
			buffer = new StringBuffer(
					"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
			sb.append(buffer.charAt(r.nextInt(buffer.length() - 10)));
			length = length - 1;
			break;
		case 5:
			String s = UUID.randomUUID().toString();
			sb.append(s.substring(0, 8) + s.substring(9, 13)
					+ s.substring(14, 18) + s.substring(19, 23)
					+ s.substring(24));
			break;
		default:
		}
		if (type != 5) {
			int range = buffer.length();
			for (int i = 0; i < length; i++) {
				sb.append(buffer.charAt(r.nextInt(range)));
			}
		}
		return sb.toString();
	}

}
