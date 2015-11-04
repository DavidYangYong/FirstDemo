package com.fl.springmvc.annotation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fl.login.domain.Login;

@Controller
@RequestMapping("index")
public class IndexController {
	@RequestMapping("hello")
	public ModelAndView handleRequest(HttpServletRequest request, ModelAndView modelAndView) {
		Login login = new Login();
		login.setLastName("lastname");
		return new ModelAndView("hello", "login", login);
	}
}
