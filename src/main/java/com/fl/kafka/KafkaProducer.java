package com.fl.kafka;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

public class KafkaProducer extends Thread {
	
	public final static String TOPIC = "TEST-TOPIC";
	
	public KafkaProducer(String topic) {
		super();
		
	}
	
	@Override
	public void run() {
		Producer producer = createProducer();
		int i = 0;
		while (true) {
			producer.send(new KeyedMessage<Integer, String>(TOPIC,
					"message: " + i++));
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Producer createProducer() {
		Properties properties = new Properties();
		properties.put("zookeeper.connect", "localhost:2181");// 声明zk
		properties.put("serializer.class", StringEncoder.class.getName());
		properties.put("metadata.broker.list", "localhost:9092");// 声明kafka
																	// broker
		return new Producer<Integer, String>(new ProducerConfig(properties));
	}
	
	public static void main(String[] args) {
		new KafkaProducer("test").start();// 使用kafka集群中创建好的主题 test
		
	}
}
