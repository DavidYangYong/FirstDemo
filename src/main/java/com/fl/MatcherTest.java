package com.fl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherTest {
	
	public static void main(String[] args) {
		String searchString = "Mapper";
		String replaceString = "Dao";
		System.out.println("searchString start:" + searchString);
		String oldType = "TBasedataMapper.xml";
		Pattern pattern = Pattern.compile(searchString);
		Matcher matcher = pattern.matcher(oldType);
		oldType = matcher.replaceAll(replaceString);
		System.out.println("old type end:" + oldType);
	}
}
