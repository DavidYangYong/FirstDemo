package com.fl.order.service;

import java.util.List;

import com.fl.order.model.TLogin;

public interface ILoginService {
	
	public void saveLogin(TLogin login);
	
	public List<TLogin> queryAll();
}
