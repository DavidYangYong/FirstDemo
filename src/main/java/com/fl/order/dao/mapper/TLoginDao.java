package com.fl.order.dao.mapper;

import com.fl.order.model.TLogin;
import java.math.BigDecimal;
import java.util.List;

public interface TLoginDao {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(TLogin record);

    TLogin selectByPrimaryKey(BigDecimal id);

    List<TLogin> selectAll();

    int updateByPrimaryKey(TLogin record);
}