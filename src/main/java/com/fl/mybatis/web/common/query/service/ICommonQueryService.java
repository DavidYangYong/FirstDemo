package com.fl.mybatis.web.common.query.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yunhan.scc.tools.component.module.query.QueryResultCount;

public interface ICommonQueryService {
	/**
	 * 通查接口
	 *
	 * @param request
	 * @param requestMap
	 * @return
	 * @throws Exception
	 * @date 2016-5-29
	 */
	public QueryResultCount query(HttpServletRequest request, Map<String, String> requestMap, int offset, int limit)
			throws Exception;
}
