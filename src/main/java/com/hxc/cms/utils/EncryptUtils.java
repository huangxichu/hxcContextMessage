package com.hxc.cms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

public class EncryptUtils {
    
    private EncryptUtils(){}
	
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"a", "b", "c", "d", "e", "f" };

	public static String encode(File file) {
		FileInputStream in = null;
		MessageDigest md5 = null;
		try {
			in = new FileInputStream(file);
			FileChannel ch = in.getChannel();
			MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return toHex(md5.digest());
	}
	
	public static String encode(String text) {
		if (!ObjectUtil.isNotBlank(text)) {
			text = "";
		}
		MessageDigest sha = null;
		try {
			sha = MessageDigest.getInstance("SHA");
			sha.update(text.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toHex(sha.digest());
	}
	
	public static String encodemd5(String text) {
		if (!ObjectUtil.isNotBlank(text)) {
			text = "";
		}
		MessageDigest sha = null;
		try {
			sha = MessageDigest.getInstance("MD5");
			sha.update(text.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toHex(sha.digest());
	}

	private static String toHex(byte[] bytes) {
		StringBuffer result = new StringBuffer();
		for (byte b : bytes) {
			result.append(hexDigits[(b & 0xf0) >> 4]);
			result.append(hexDigits[(b & 0x0f)]);
		}
		return result.toString();
	}
	
	public static void main(String[] args){
		System.out.println(EncryptUtils.encode("123456"));
	}
	//7c4a8d09ca3762af61e59520943dc26494f8941b
	//7c4a8d09ca3762af61e59520943dc26494f8941b
}
