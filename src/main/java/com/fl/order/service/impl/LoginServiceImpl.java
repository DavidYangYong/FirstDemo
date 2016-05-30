package com.fl.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fl.order.dao.mapper.TLoginDao;
import com.fl.order.model.TLogin;
import com.fl.order.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {
	
	@Autowired
	private TLoginDao loginDao;
	
	@Override
	public void saveLogin(TLogin login) {
		// TODO Auto-generated method stub
		// loginDao.insert(login);
	}
	
	@Override
	public List<TLogin> queryAll() {
		// TODO Auto-generated method stub
		// PageHelper.startPage(1, 10);
		TLogin tLogin = new TLogin();
		Map map = new HashMap<>();
		map.put("id", 1);
		// return loginDao.queryCommonList("queryCommonList1", map);
		return null;
	}
	
}
