package com.fl.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fl.login.domain.Login;
import com.fl.order.dao.mapper.ILoginDao;
import com.fl.order.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {
	
	@Autowired
	private ILoginDao loginDao;
	
	@Override
	public void saveLogin(Login login) {
		// TODO Auto-generated method stub
		loginDao.saveLogin(login);
	}
	
}
