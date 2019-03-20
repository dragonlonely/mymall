package com.dragon.xiaomi.utils;

public class TextUtils {
	public static boolean empty(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}
}
