package com.sicdlib.util.mailUtil;

import java.util.Random;

public class ValidationCode {

	public static String getValidationCode(int NUM){
		Random random = new Random();
		String code = "";
		for (int i = 0; i < NUM; i++) {
			code = code+random.nextInt(10);
		}
		System.out.println(code);
		return "";
	}
	
	public static void main(String[] args) {
		getValidationCode(4);
	}
}
