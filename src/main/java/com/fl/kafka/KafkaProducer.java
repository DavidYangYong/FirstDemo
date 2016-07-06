package com.fl.kafka;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * 生产者示例代码
 */
public class KafkaProducer {
	
	public final static String TOPIC = "TEST-TOPIC1";
	
	public KafkaProducer(String topic) {
		super();
		
	}
	
	/** 制造数据 */
	public void createMessage() {
		ProducerData producerData = new ProducerData();
		producerData.setId(0);
		producerData.setUser("Chenhui");
		producerData.setKeyword("china");
		producerData.setCurrentDate(new Date().toString());
		List<Keyword> msg = new ArrayList<Keyword>();
		msg.add(producerData);
	}
	
	public void execKafka() {
		Producer producer = createProducer();
		int i = 0;
		long events = 10;
		
		// producer.send(new KeyedMessage<Integer, String>(TOPIC,
		// "message: " + i++));
		
		// try {
		// TimeUnit.SECONDS.sleep(1);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		for (long nEvents = 0; nEvents < events; nEvents++) {
			/** 制造数据 */
			ProducerData producerData = new ProducerData();
			producerData.setId(nEvents);
			producerData.setUser("Chenhui" + nEvents);
			producerData.setKeyword("china");
			producerData.setCurrentDate(new Date().toString());
			
			System.out.println(producerData);
			
			KeyedMessage<ProducerData, ProducerData> data = new KeyedMessage<ProducerData, ProducerData>(
					TOPIC, msg, msg);
			System.out.println(msg);
			KeyedMessage<User, User> data = new KeyedMessage<User, User>(TOPIC,
					msg, msg);
			producer.send(data);
		}
		producer.close();
		System.out.println("producer is successful .");
		
	}
	
	private Producer createProducer() {
		Properties properties = new Properties();
		properties.put("zookeeper.connect", "localhost:2181/kafka");// 声明zk
		// properties.put("serializer.class", StringEncoder.class.getName());
		properties.put("metadata.broker.list", "localhost:9092");// 声明kafka
																	// broker
		// 序列化类
		props.put("serializer.class", "com.fl.kafka.KeywordMessage");
		props.put("request.required.acks", "1");
		return new Producer<ProducerData, ProducerData>(
				new ProducerConfig(properties));
	}
	
	public static void main(String[] args) {
		new KafkaProducer("test").execKafka();// 使用kafka集群中创建好的主题 test
		
	}
}
