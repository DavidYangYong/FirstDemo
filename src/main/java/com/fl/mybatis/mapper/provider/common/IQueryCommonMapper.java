package com.fl.mybatis.mapper.provider.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yunhan.scc.tools.component.module.query.QueryCondition;

public interface IQueryCommonMapper<T extends QueryCondition> {
	/**
	 * 根据实体中的属性值进行查询，查询条件使用等号
	 *
	 * @param record
	 * @return
	 */
	
	List queryCommonList(@Param("queryId") String queryId, @Param("requestMap") Map requestMap);
}
