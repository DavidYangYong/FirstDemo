package com.fl.mybatis.web.common.query.controller;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fl.mybatis.web.common.query.message.MessageHandler;
import com.fl.mybatis.web.common.query.service.ICommonQueryService;
import com.yunhan.scc.tools.component.module.query.QueryResultCount;

@Controller
@RequestMapping("/commonQuery")
public class CommonQueryController {
	protected Log log = LogFactory.getLog(this.getClass());
	@Autowired
	private ICommonQueryService commonQueryService;
	@Autowired
	private MessageHandler messageHandler;
	
	private Map<String, String> buildRequestMap(HttpServletRequest request) {
		Map requestMap = new HashMap();
		for (Enumeration e = request.getParameterNames(); e
				.hasMoreElements();) {
			String key = e.nextElement().toString();
			Object value = null;
			value = request.getParameter(key);
			try {
				if (request.getMethod().equals("GET"))
					value = new String(
							value.toString().trim().getBytes("ISO-8859-1"),
							"UTF-8");
			} catch (UnsupportedEncodingException e1) {
				this.log.error("参数转码异常", e1);
			}
			
			if ((key.equals("page"))
					&& (request.getParameter("_search1") != null)
					&& ("true".equals(request.getParameter("_search1")))) {
				value = "1";
			}
			
			requestMap.put(key, value.toString());
		}
		
		Map backMap = (Map) request.getSession().getAttribute("backMap");
		if (backMap != null) {
			if ((request.getParameter("_hisType") != null)
					&& ("back".equals(request.getParameter("_hisType")))) {
				Map thisBack = (Map) backMap
						.get(request.getParameter("query_id"));
				if (thisBack != null) {
					Map requestMap1 = (Map) thisBack.get("postData");
					if (requestMap1 != null) {
						requestMap = requestMap1;
						requestMap.put("_hisType",
								request.getParameter("_hisType"));
					}
				} else {
					Map thisQuery = new HashMap();
					thisQuery.put("postData", requestMap);
					backMap.put(request.getParameter("query_id"), thisQuery);
				}
			} else {
				Map thisQuery = new HashMap();
				thisQuery.put("postData", requestMap);
				backMap.put(request.getParameter("query_id"), thisQuery);
			}
		}
		
		return requestMap;
	}
	
	@RequestMapping(value = "/query")
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StringBuffer result = new StringBuffer();
		long start = System.currentTimeMillis();
		Map<String, String> reqeustMap = null;
		String callback = null;
		
		try {
			callback = request.getParameter("callback");
			
			// 先将所有请求数据提出来生成一个map
			reqeustMap = this.buildRequestMap(request);
			
			if (log.isDebugEnabled()) {
				String queryID = "";
				if (null != reqeustMap && null != reqeustMap.get("query_id")) {
					queryID = reqeustMap.get("query_id");
				}
				log.debug("CommonQuery [" + queryID
						+ "] start ===  RequestMethod[POST]");
			}
			
			QueryResultCount queryResultCount = this.commonQueryService
					.query(request, reqeustMap, 1, 10);
			this.messageHandler.toJson(queryResultCount, result);
		} catch (Exception e) {
			
			result.delete(0, result.length());
			// result.append(this.QUERY_NULL_RETURN);
			String queryID = "";
			if (null != reqeustMap && null != reqeustMap.get("query_id")) {
				queryID = reqeustMap.get("query_id");
			}
			log.error(
					"CommonQuery [" + queryID + "] 出现[" + e.toString() + "]异常",
					e);
			throw e;
		}
		// 设置字符编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// 返回json对象（通过PrintWriter输出）
		if (null != callback) {
			response.getWriter()
					.write(callback + "(" + result.toString() + ")");
		} else {
			response.getWriter().write(result.toString());
		}
		
		long end = System.currentTimeMillis();
		long time = end - start;
		if (time > 5000) {
			String queryID = "";
			if (null != reqeustMap) {
				queryID = reqeustMap.get("query_id");
			}
			// log.debug("CommonQuery [" + queryID + "] result ===: [" +
			// result.toString() + "]");
			log.info("CommonQuery [" + queryID + "] time is ===: " + time
					+ "ms");
		}
	}
}
