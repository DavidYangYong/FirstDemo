package com.fl.order.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fl.order.model.TLogin;
import com.fl.order.service.ILoginService;

@Controller
@RequestMapping("index")
public class IndexController {
	@Autowired
	private ILoginService loginService;
	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;
	
	@RequestMapping("hello")
	public String save(HttpServletRequest request, ModelAndView modelAndView) {
		TLogin login = new TLogin();
		String string = request.getParameter("strJson");
		
		login = JSON.parseObject(string, TLogin.class);
		loginService.saveLogin(login);
		
		// KafkaProducer kafkaProducer = new KafkaProducer("test");
		// kafkaProducer.execKafka();
		// List<ProducerData> list = JdbcReadDemo.process();
		// for (int nEvents = 0; nEvents < list.size(); nEvents++) {
		// /** 制造数据 */
		// ProducerData producerData = list.get(nEvents);
		//
		// long start = System.currentTimeMillis();
		// kafkaTemplate.send(KafkaProducer.TOPIC, producerData.getId());
		// kafkaTemplate.flush();
		// long end = System.currentTimeMillis();
		// // System.out.println("every one : " + ((end - start)));
		// }
		
		return "hello";
	}
	
	private Log log = LogFactory.getLog(IndexController.class);
	
	@RequestMapping("queryAll")
	@ResponseBody
	public void queryAll(HttpServletRequest request, HttpServletResponse response) {
		// String zooKeeper = "10.100.4.102:2181";
		// String groupId = "jd-group";
		// String topic = KafkaProducer.TOPIC;
		// int threads = 1;
		//
		// MultiThreadConsumerGroup example = new MultiThreadConsumerGroup(
		// zooKeeper, groupId, topic);
		// try {
		// example.run(threads);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (ExecutionException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// try {
		// Thread.sleep(10000);
		// } catch (InterruptedException ie) {
		//
		// }
		// example.shutdown();
		
		// Json json = new Json();
		// try {
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// log.error("获取数据失败", e);
		// json.setSuccess(false);
		// json.setMsg("获取数据失败");
		// }
		// writeJson(json, response);
		List<TLogin> list = loginService.queryAll();
		System.out.println(list.size());
	}
	
	public void writeJson(Object object, HttpServletResponse response) {
		try {
			// String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd
			// HH:mm:ss");
			response.setContentType("text/html;charset=utf-8");
			response.setContentType("application/json");
			String msg = "";
			msg = JSON.toJSONString(object);
			
			// String json = JSON.toJSONStringWithDateFormat(object,
			// "yyyy-MM-dd
			// HH:mm:ss");
			response.getWriter().write(msg);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
