package com.fl.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.message.Message;

public class KeywordMessage implements kafka.serializer.Encoder<ProducerData> {
	
	public static final Logger LOG = LoggerFactory
			.getLogger(ProducerData.class);
	
	public Message toMessage(ProducerData words) {
		LOG.info("start in encoding...");
		return new Message(words.toString().getBytes());
	}
	
	@Override
	public byte[] toBytes(ProducerData data) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
