package com.fl.test.jackson;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Map2Xml {
	
	public static void main(String[] args) throws JsonProcessingException {
		// Map转换xml文档
		XmlMapper xml = new XmlMapper();
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("A", "A1");
		
		map.put("B", "B1");
		
		System.out.println(xml.writeValueAsString(map));
	}
}
