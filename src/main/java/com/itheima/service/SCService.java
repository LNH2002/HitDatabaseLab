package com.itheima.service;

import com.itheima.pojo.SC;

import java.util.List;

public interface SCService {

    /**
     * 查询所有
     * @return
     */
    List<SC> select(String sql) throws Exception;
}
