package com.internalaudit.client.util;

import java.util.Random;

public class MyUtil {




	public static String getRandom(){
		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		for (int i=0;i<2;i++) {
			sb.append('a'+random.nextInt(26));
		}
		String code = sb.toString();
		return code;
	}
}