package com.hxc.cms.utils;

import java.security.MessageDigest;

public class MD5Utils {
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
		"a", "b", "c", "d", "e", "f" };
	
	private MD5Utils(){}
	
	public static String encode(String text) {
		if (!ObjectUtil.isNotBlank(text)) {
			text = "";
		}
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(text.getBytes("GBK"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toHex(md5.digest());
	}
	
	private static String toHex(byte[] bytes) {
		StringBuffer result = new StringBuffer(32);
		for (byte b : bytes) {
			result.append(hexDigits[(b & 0xf0) >> 4]);
			result.append(hexDigits[(b & 0x0f)]);
		}
		return result.toString();
	}
	
	public static String encode(String text, String charsetName) {
		if (!ObjectUtil.isNotBlank(text)) {
			text = "";
		}
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(text.getBytes(charsetName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toHex(md5.digest());
	}
	
}
