package com.fl.kafka.consumer.simple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.fl.kafka.KafkaProducer;
import com.fl.kafka.KeywordMessage;
import com.fl.kafka.ProducerData;
import com.fl.utils.BeanUtils;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.utils.VerifiableProperties;

/**
 * 消费者示例代码
 */
public class KafkaConsumer {
	private final ConsumerConnector consumer;
	
	private KafkaConsumer() {
		Properties props = new Properties();
		// zookeeper 配置
		props.put("zookeeper.connect", "10.100.4.102:2181");// 声明zk
		// props.put("serializer.class", StringEncoder.class.getName());
		props.put("metadata.broker.list", "10.100.4.102:9092");// 声明kafka
		// group 代表一个消费组
		props.put("group.id", "jd-group");
		
		props.put("zk.connectiontimeout.ms", "15000");
		// zk连接超时
		props.put("zookeeper.session.timeout.ms", "4000");
		props.put("zookeeper.sync.time.ms", "200");
		props.put("auto.commit.interval.ms", "1000");
		props.put("auto.offset.reset", "smallest");
		// 序列化类
		// props.put("serializer.class", "kafka.serializer.StringEncoder");
		
		props.put("serializer.class", KeywordMessage.class.getName());
		
		ConsumerConfig config = new ConsumerConfig(props);
		
		consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
	}
	
	void consume() {
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(KafkaProducer.TOPIC, new Integer(1));
		
		// StringDecoder keyDecoder = new StringDecoder(
		// new VerifiableProperties());
		// StringDecoder valueDecoder = new StringDecoder(
		// new VerifiableProperties());
		
		KeywordMessage keyDecoder = new KeywordMessage(
				new VerifiableProperties());
		
		KeywordMessage valueDecoder = new KeywordMessage(
				new VerifiableProperties());
		
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
				.createMessageStreams(topicCountMap);
		for (String key : consumerMap.keySet()) {
			List<KafkaStream<byte[], byte[]>> streams = consumerMap
					.get(KafkaProducer.TOPIC);
			for (int i = 0; i < streams.size(); i++) {
				KafkaStream<byte[], byte[]> kafkaStream = streams.get(i);
				ConsumerIterator<byte[], byte[]> it = kafkaStream.iterator();
				while (it.hasNext()) {
					byte[] producerDataBytes = it.next().message();
					if (producerDataBytes != null) {
						ProducerData producerData = (ProducerData) BeanUtils
								.bytes2Object(producerDataBytes);
						System.out.println(producerData.getUser());
					}
					
				}
			}
			
		}
		
		consumer.commitOffsets();
		consumer.shutdown();
	}
	
	public static void main(String[] args) {
		new KafkaConsumer().consume();
	}
}
