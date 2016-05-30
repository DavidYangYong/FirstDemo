package com.fl.mybatis.web.common.query.message;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yunhan.scc.tools.component.module.query.QueryCondition;
import com.yunhan.scc.tools.component.module.query.QueryResultCount;
import com.yunhan.scc.tools.component.module.query.QueryResultMap;
import com.yunhan.scc.tools.json.JSONException;

public interface MessageHandler {
	public boolean requestParamCheck(Map<String, String> paramMap);
	
	public Map<String, String> buildRequestMap(HttpServletRequest paramHttpServletRequest);
	
	public void handRequestParam(Map<String, String> paramMap, QueryCondition paramQueryCondition)
			throws SecurityException, IllegalArgumentException,
			NoSuchFieldException, NoSuchMethodException, IllegalAccessException,
			InvocationTargetException;
	
	public void toJson(QueryResultCount paramQueryResultCount, StringBuffer paramStringBuffer)
			throws JSONException, SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException;
	
	public void toJson(QueryResultMap paramQueryResultMap, StringBuffer paramStringBuffer)
			throws JSONException, SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException;
}
