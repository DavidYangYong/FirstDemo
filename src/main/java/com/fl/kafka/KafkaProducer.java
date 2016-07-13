package com.fl.kafka;

import java.util.List;
import java.util.Properties;

import com.fl.kafka.consumer.multi.JdbcReadDemo;

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
		// ProducerData producerData = new ProducerData();
		// producerData.setId(0);
		// producerData.setUser("Chenhui");
		// producerData.setKeyword("china");
		// producerData.setCurrentDate(new Date().toString());
		// List<Keyword> msg = new ArrayList<Keyword>();
		// msg.add(producerData);
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
		long startTotalQuery = System.currentTimeMillis();
		List<ProducerData> list = JdbcReadDemo.process();
		long endTotalQuery = System.currentTimeMillis();
		
		long startTotal = System.currentTimeMillis();
		
		System.out.println(list.size());
		for (int nEvents = 0; nEvents < list.size(); nEvents++) {
			/** 制造数据 */
			ProducerData producerData = list.get(nEvents);
			// ProducerData producerData = new ProducerData();
			// producerData.setId(nEvents + "");
			// producerData.setUser("Chenhui" + nEvents);
			// producerData.setKeyword("china");
			// producerData.setCurrentDate(new Date().toString());
			
			// System.out.println(producerData);
			
			// KeyedMessage<ProducerData, ProducerData> data = new
			// KeyedMessage<ProducerData, ProducerData>(
			// TOPIC, producerData, producerData);
			// System.out.println(producerData);
			KeyedMessage<ProducerData, ProducerData> data1 = new KeyedMessage<ProducerData, ProducerData>(
					TOPIC, producerData, producerData);
			long start = System.currentTimeMillis();
			producer.send(data1);
			long end = System.currentTimeMillis();
			// System.out.println("every one : " + ((end - start)));
		}
		long endTotal = System.currentTimeMillis();
		System.out.println(
				"total Query : " + ((endTotalQuery - startTotalQuery) / 1000));
		System.out.println("total : " + ((endTotal - startTotal) / 1000));
		producer.close();
		System.out.println("producer is successful .");
		
	}
	
	private Producer createProducer() {
		Properties properties = new Properties();
		properties.put("zookeeper.connect", "zoo:2181");// 声明zk
		// properties.put("serializer.class", StringEncoder.class.getName());
		properties.put("metadata.broker.list", "zoo:9092");// 声明kafka
		// broker
		// 同步还是异步发送消息，默认“sync”表同步，"async"表异步。异步可以提高发送吞吐量,
		// 也意味着消息将会在本地buffer中,并适时批量发送，但是也可能导致丢失未发送过去的消息
		properties.put("producer.type", "async");
		// 序列化类
		properties.put("serializer.class", "com.fl.kafka.KeywordMessage");
		properties.put("request.required.acks", "1");
		return new Producer<ProducerData, ProducerData>(
				new ProducerConfig(properties));
	}
	
	public static void main(String[] args) {
		new KafkaProducer("test").execKafka();// 使用kafka集群中创建好的主题 test
		
	}
}
