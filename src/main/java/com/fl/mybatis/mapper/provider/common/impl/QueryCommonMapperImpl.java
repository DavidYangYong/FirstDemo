package com.fl.mybatis.mapper.provider.common.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fl.mybatis.mapper.provider.common.IQueryCommonMapper;
import com.fl.mybatis.web.common.query.message.MessageHandler;
import com.yunhan.scc.tools.component.module.query.QueryCondition;

@Repository(value = "queryCommonMapper")
public class QueryCommonMapperImpl
		implements IQueryCommonMapper<QueryCondition> {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Resource(name = "messageHandler")
	private MessageHandler messageHandler;
	
	/**
	 * 建立查询条件
	 */
	public QueryCondition buildQueryCondition(String queryId)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		if (!queryId.equals("")) {
			String conditionClassName = sqlSessionTemplate.getConfiguration()
					.getMappedStatement(queryId).getParameterMap().getType()
					.getName();
			return (QueryCondition) Class.forName(conditionClassName)
					.newInstance();
		}
		return null;
	}
	
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public MessageHandler getMessageHandler() {
		return messageHandler;
	}
	
	public void setMessageHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}
	
	@Override
	public List queryCommonList(String queryId, Map requestMap) {
		// TODO Auto-generated method stub
		List list = new ArrayList<>();
		try {
			QueryCondition queryCondition = buildQueryCondition(queryId);
			messageHandler.handRequestParam(requestMap, queryCondition);
			list = getSqlSessionTemplate().selectList(queryId, queryCondition);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
