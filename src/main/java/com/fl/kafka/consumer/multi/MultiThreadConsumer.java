package com.fl.kafka.consumer.multi;

import java.util.ArrayList;
import java.util.List;

import com.fl.kafka.ProducerData;
import com.fl.utils.BeanUtils;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

public class MultiThreadConsumer implements Runnable {
	
	private KafkaStream m_stream;
	private int m_threadNumber;
	
	public MultiThreadConsumer(KafkaStream a_stream, int a_threadNumber) {
		m_threadNumber = a_threadNumber;
		m_stream = a_stream;
	}
	
	public void run() {
		ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
		List<ProducerData> list = new ArrayList<>();
		while (it.hasNext()) {
			// System.out.println("Thread " + m_threadNumber + ": "
			// + new String(it.next().message()));
			System.out.println("Shutting down Thread: " + m_threadNumber);
			byte[] producerDataBytes = it.next().message();
			if (producerDataBytes != null) {
				ProducerData producerData = (ProducerData) BeanUtils
						.bytes2Object(producerDataBytes);
				System.out.println(producerData.getUser());
				list.add(producerData);
			}
		}
		JdbcWriteDemo.process(list);
		
	}
}
