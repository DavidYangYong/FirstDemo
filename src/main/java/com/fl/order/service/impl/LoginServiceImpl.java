package com.fl.order.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fl.order.dao.mapper.TLoginDao;
import com.fl.order.model.TLogin;
import com.fl.order.service.ILoginService;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

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
		EntityTable entityTable = EntityHelper.getEntityTable(TLogin.class);
		Set<EntityColumn> columnList = EntityHelper.getColumns(TLogin.class);
		StringBuilder sql = new StringBuilder();
		for (EntityColumn entityColumn : columnList) {
			sql.append(entityColumn.getColumn()).append(",");
		}
		System.out.println(sql);
		return loginDao.selectAll();
		
	}
	
}
