package com.fl.order.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fl.order.model.TLogin;
import com.fl.order.service.ILoginService;
import com.fl.utils.json.jackson.Json;

@Controller
@RequestMapping("index")
public class IndexController {
	@Autowired
	private ILoginService loginService;
	
	@RequestMapping("hello")
	public String save(HttpServletRequest request, ModelAndView modelAndView) {
		TLogin login = new TLogin();
		String string = request.getParameter("strJson");
		
		login = JSON.parseObject(string, TLogin.class);
		loginService.saveLogin(login);
		return "queryAll";
	}
	
	private Log log = LogFactory.getLog(IndexController.class);
	
	@RequestMapping("queryAll")
	@ResponseBody
	public void queryAll(HttpServletRequest request, HttpServletResponse response) {
		Json json = new Json();
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取数据失败", e);
			json.setSuccess(false);
			json.setMsg("获取数据失败");
		}
		writeJson(json, response);
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
