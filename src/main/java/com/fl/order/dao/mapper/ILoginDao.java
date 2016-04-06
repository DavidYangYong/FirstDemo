package com.fl.order.dao.mapper;

import com.fl.login.domain.Login;

import tk.mybatis.mapper.common.Mapper;

public interface ILoginDao extends Mapper<Login> {
	public void saveLogin(Login login);
}
