package com.fl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fl.mybatis.mapper.provider.common.QueryCommonSelectProvider;
import com.fl.order.model.TLogin;

public class Test {
	
	public static void main(String[] args) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		TLogin parameterObject = new TLogin();
		Class parameterType = (parameterObject == null) ? Object.class
				: parameterObject.getClass();
		QueryCommonSelectProvider queryCommonSelectProvider = new QueryCommonSelectProvider(
				null, null);
		test(queryCommonSelectProvider);
		System.out.println(1);
	}
	
	public static void test(Object provider) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		String providerMethodName = (String) provider.getClass()
				.getMethod("queryCommonList").invoke(provider);
		for (Method m : provider.getClass().getMethods()) {
			if (providerMethodName.equals(m.getName())) {
				if (m.getReturnType() == String.class) {
					
					System.out.println(m.getName());
				}
			}
		}
	}
}
