package com.fl.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;

public class Test {
	public static void main(String[] args) {
		/** 配置producer必要的参数 */
		Properties props = new Properties();
		props.put("zk.connect", "localhost:2181");
		/** 选择用哪个类来进行序列化 */
		props.put("serializer.class", "org.gfg.kafka.message.KeywordMessage");
		props.put("zk.connectiontimeout.ms", "6000");
		ProducerConfig config = new ProducerConfig(props);
		
		/** 制造数据 */
		ProducerData keyword = new ProducerData();
		keyword.setUser("Chenhui");
		keyword.setId(0 + "");
		keyword.setKeyword("china");
		
		List<ProducerData> msg = new ArrayList<ProducerData>();
		msg.add(keyword);
		
		/** 构造数据发送对象 */
		Producer<String, ProducerData> producer = new Producer<String, ProducerData>(
				config);
		// producer.send(
		// new KeyedMessage<Integer, String>(topic, "message: " + i++));
	}
}
