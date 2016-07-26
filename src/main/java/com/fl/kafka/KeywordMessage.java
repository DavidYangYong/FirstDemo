package com.fl.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fl.utils.BeanUtils;

import kafka.message.Message;
import kafka.utils.VerifiableProperties;

public class KeywordMessage implements kafka.serializer.Encoder<ProducerData> {
	
	public static final Logger LOG = LoggerFactory
			.getLogger(ProducerData.class);
	
	public KeywordMessage() {
		
	}
	
	public KeywordMessage(VerifiableProperties props) {
		
	}
	
	public Message toMessage(ProducerData words) {
		LOG.info("start in encoding...");
		return new Message(words.toString().getBytes());
	}
	
	@Override
	public byte[] toBytes(ProducerData data) {
		System.out.println("encoder ---> " + data);
		return BeanUtils.object2Bytes(data);
	}
	
}
