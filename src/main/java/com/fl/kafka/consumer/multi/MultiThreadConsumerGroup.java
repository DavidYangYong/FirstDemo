package com.fl.kafka.consumer.multi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.fl.kafka.KafkaProducer;
import com.fl.kafka.KeywordMessage;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class MultiThreadConsumerGroup {
	
	private final ConsumerConnector consumer;
	private final String topic;
	private ExecutorService executor;
	
	public MultiThreadConsumerGroup(String a_zookeeper, String a_groupId,
			String a_topic) {
		consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
				createConsumerConfig(a_zookeeper, a_groupId));
		this.topic = a_topic;
	}
	
	public void shutdown() {
		if (consumer != null)
			consumer.shutdown();
		if (executor != null)
			executor.shutdown();
	}
	
	public void run(int a_numThreads)
			throws InterruptedException, ExecutionException {
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, new Integer(a_numThreads));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
				.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
		
		// 启动所有线程
		executor = Executors.newFixedThreadPool(a_numThreads);
		
		// 开始消费消息
		int threadNumber = 0;
		for (final KafkaStream stream : streams) {
			Future future = executor
					.submit(new MultiThreadConsumer(stream, threadNumber));
			
			threadNumber++;
		}
	}
	
	private static ConsumerConfig createConsumerConfig(String a_zookeeper, String a_groupId) {
		Properties props = new Properties();
		// zookeeper 配置
		props.put("zookeeper.connect", a_zookeeper);// 声明zk
		// props.put("serializer.class", StringEncoder.class.getName());
		// props.put("metadata.broker.list", "10.100.4.102:9092");// 声明kafka
		// group 代表一个消费组
		props.put("group.id", a_groupId);
		
		props.put("zk.connectiontimeout.ms", "15000");
		// zk连接超时
		props.put("zookeeper.session.timeout.ms", "4000");
		props.put("zookeeper.sync.time.ms", "200");
		props.put("auto.commit.interval.ms", "1000");
		props.put("auto.offset.reset", "smallest");
		// 序列化类
		// props.put("serializer.class", "kafka.serializer.StringEncoder");
		
		props.put("serializer.class", KeywordMessage.class.getName());
		
		return new ConsumerConfig(props);
	}
	
	public static void main(String[] args) {
		String zooKeeper = "10.100.4.102:2181";
		String groupId = "jd-group";
		String topic = KafkaProducer.TOPIC;
		int threads = 1;
		
		MultiThreadConsumerGroup example = new MultiThreadConsumerGroup(
				zooKeeper, groupId, topic);
		try {
			example.run(threads);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException ie) {
			
		}
		example.shutdown();
	}
}
