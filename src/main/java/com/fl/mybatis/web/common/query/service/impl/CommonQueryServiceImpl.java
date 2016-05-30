package com.fl.mybatis.web.common.query.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fl.mybatis.mapper.provider.common.IQueryCommonMapper;
import com.fl.mybatis.web.common.query.message.MessageHandler;
import com.fl.mybatis.web.common.query.service.ICommonQueryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunhan.scc.tools.component.module.query.QueryResultCount;

@Service
public class CommonQueryServiceImpl implements ICommonQueryService {
	@Resource(name = "messageHandler")
	private MessageHandler messageHandler;
	
	@Autowired
	private IQueryCommonMapper queryCommonMapper;
	
	public QueryResultCount query(HttpServletRequest request, Map<String, String> requestMap, int offset, int limit)
			throws Exception {
		// 首先进行基础参数有效性验证
		if (this.messageHandler.requestParamCheck(requestMap)) {
			// 通过基本参数有效性验证后开始初始化创建查询参数对象
			// QueryCondition condition = this.commonQueryPersistence
			// .buildQueryCondition(requestMap);// 对象的属性都为null?
			//
			// // 处理请求参数,将所有参数从map中取出填充查询参数对象
			// this.messageHandler.handRequestParam(requestMap, condition);
			//
			// // 处理有返回的情况，把查询条件放入session
			// Map<String, Object> backMap = (Map<String, Object>) request
			// .getSession().getAttribute("backMap");
			// if (null != backMap) {
			// Map<String, Object> thisBack = (Map<String, Object>) backMap
			// .get(request.getParameter("query_id"));
			// if (null != thisBack) {
			// thisBack.put("condition", condition);
			// }
			// }
			QueryResultCount resultCount = new QueryResultCount();
			// 执行查询数据库
			Page page = PageHelper.startPage(offset, limit, true);
			List list = queryCommonMapper.queryCommonList("queryCommonList1",
					requestMap);
			// resultCount.setCondition(condition);
			resultCount.setQueryResultList(page.getResult());
			resultCount.setSize(page.getTotal());
			return resultCount;
		} else {
			throw new Exception("基础数据验证未通过，请求地址：" + request.getRequestURI());
		}
	}
}
