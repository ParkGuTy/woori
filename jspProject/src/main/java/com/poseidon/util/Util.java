package com.poseidon.util;

public class Util {
	public static boolean str2Int(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	//12A3 -> 123
	
	public static String HTML2str(String str) {
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		return str;
	}
	
}