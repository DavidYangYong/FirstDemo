package com.fl;

import org.apache.commons.lang.math.NumberUtils;

public class IntegerTest {
	
	public static void main(String[] args) {
		int a = 128;
		Integer bInteger = new Integer(128);
		System.out.println(a == bInteger);
		int d = 129;
		Integer cInteger = new Integer(129);
		System.out.println(d == cInteger);
		Integer eInteger = new Integer(128);
		Integer fiInteger = new Integer(128);
		System.out.println(eInteger == fiInteger);
		System.out.println(NumberUtils.toInt(eInteger.toString()) == NumberUtils
				.toInt(eInteger.toString()));
	}
}
