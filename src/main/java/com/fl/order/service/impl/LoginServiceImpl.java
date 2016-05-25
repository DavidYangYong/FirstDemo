package com.fl.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fl.order.model.TLogin;
import com.fl.order.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {
	
	// private TLoginDao loginDao;
	
	@Override
	public void saveLogin(TLogin login) {
		// TODO Auto-generated method stub
		// loginDao.insert(login);
	}
	
	@Override
	public List<TLogin> queryAll() {
		// TODO Auto-generated method stub
		// return loginDao.selectAll();
		return new ArrayList<TLogin>();
	}
	
}
