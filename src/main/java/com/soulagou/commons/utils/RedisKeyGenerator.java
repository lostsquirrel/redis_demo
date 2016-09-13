package com.soulagou.commons.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RedisKeyGenerator {
	
	public static String generate(String prefix, String key) {

		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		messageDigest.update(key.getBytes());

		byte[] b = messageDigest.digest();

		StringBuilder output = new StringBuilder(32);

		for (int i = 0; i < b.length; i++) {
			String temp = Integer.toHexString(b[i] & 0xff);
			if (temp.length() < 2) {
				output.append("0");
			}
			output.append(temp);
		}
		
		if (prefix == null || prefix.length() < 1) {
			return output.toString();
		}

		return prefix + ":" + output.toString();
	}

}
