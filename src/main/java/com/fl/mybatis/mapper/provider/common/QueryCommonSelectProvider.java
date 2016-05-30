package com.fl.mybatis.mapper.provider.common;

import java.util.Map;

import org.apache.ibatis.mapping.MappedStatement;

import com.yunhan.scc.tools.component.module.query.QueryCondition;
import com.yunhan.scc.tools.component.module.query.QueryConstant;

import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

public class QueryCommonSelectProvider extends MapperTemplate {
	
	public QueryCommonSelectProvider(Class<?> mapperClass,
			MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
		// TODO Auto-generated constructor stub
	}
	
	public QueryCondition buildQueryCondition(Map<String, String> parameterMap)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		String queryID = parameterMap.get(QueryConstant.QUERY_ID).toString();
		if (!queryID.equals("")) {
			// String conditionClassName = this.sqlSessionFactoryBean
			// .getConfiguration().getMappedStatement(queryID)
			// .getParameterMap().getType().getName();
			// return (QueryCondition) Class.forName(conditionClassName)
			// .newInstance();
		}
		return null;
	}
	
	public String queryCommonList(MappedStatement ms) {
		
		Class<?> entityClass = getEntityClass(ms);
		// 将返回值修改为实体类型
		// setResultType(ms, entityClass);
		StringBuilder sql = new StringBuilder("SELECT ");
		sql.append("<if test=\"distinct\">distinct</if>");
		// 支持查询指定列
		sql.append(SqlHelper.exampleSelectColumns(entityClass));
		sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
		sql.append(SqlHelper.exampleWhereClause());
		sql.append(SqlHelper.exampleOrderBy(entityClass));
		return sql.toString();
	}
}
