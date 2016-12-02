package com.fl.test.jackson;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Json2Xml {
	
	public static void main(String[] args)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		String json = "{\"name\":\"reiz\"}";
		map = (Map) objectMapper.readValue(json, Map.class);
		System.out.println(map);
		XmlMapper xml = new XmlMapper();
		System.out.println(xml.writeValueAsString(map));
	}
	
	public static String beanToJson(Object obj) throws IOException {
		// 这里异常都未进行处理，而且流的关闭也不规范。开发中请勿这样写，如果发生异常流关闭不了
		ObjectMapper mapper = new ObjectMapper();
		StringWriter writer = new StringWriter();
		JsonGenerator gen = new JsonFactory().createJsonGenerator(writer);
		mapper.writeValue(gen, obj);
		gen.close();
		String json = writer.toString();
		writer.close();
		return json;
	}
}
