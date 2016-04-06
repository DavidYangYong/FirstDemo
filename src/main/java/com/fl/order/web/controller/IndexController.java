package com.fl.order.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fl.login.domain.Login;
import com.fl.order.service.ILoginService;

@Controller
@RequestMapping("index")
public class IndexController {
	@Autowired
	private ILoginService loginService;
	
	@RequestMapping("hello")
	public ModelAndView handleRequest(HttpServletRequest request, ModelAndView modelAndView) {
		Login login = new Login();
		login.setLastName("lastname");
		loginService.saveLogin(login);
		return new ModelAndView("hello", "login", login);
	}
}
