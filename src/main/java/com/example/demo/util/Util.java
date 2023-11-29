package com.example.demo.util;

public class Util {
	public static boolean empty(Object obj) {

		if (obj == null) {
			return true;
		}

		String str = (String) obj;

		return str.trim().length() == 0;
	}
	
	public static String f(String format, Object... args) { // ...은 매개변수가 1개, 2개, 3개 들어올때 다 받을 수 있음
		// format은 내가 보여주고 싶은 문자열 + %d, %s : args는 치환할 변수
		return String.format(format, args);
	}
}
