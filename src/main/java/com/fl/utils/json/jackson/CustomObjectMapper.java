package com.fl.utils.json.jackson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CustomObjectMapper extends ObjectMapper {
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public CustomObjectMapper() {
		super();

		// configure to ignore unknown property
		configure(Feature.IGNORE_UNKNOWN, false);

		SimpleModule module = new SimpleModule("v3",
				new Version(3, 0, 0, null));

		module.addSerializer(Date.class, new JsonSerializer<Date>() {
			@Override
			public void serialize(Date value, JsonGenerator jsonGenerator,
					SerializerProvider provider)
							throws IOException, JsonProcessingException {
				jsonGenerator.writeString(sdf.format(value));
			}
		});
		module.addDeserializer(Date.class, new JsonDeserializer<Date>() {
			@Override
			public Date deserialize(JsonParser jp, DeserializationContext ctxt)
					throws IOException, JsonProcessingException {
				try {
					return sdf.parse(jp.getText());
				} catch (Exception e) {
					return null;
				}

			}
		});

		this.registerModule(module);

	}
}