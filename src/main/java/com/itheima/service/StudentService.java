package com.itheima.service;

import com.itheima.pojo.Student;

import java.util.List;

public interface StudentService {

    /**
     * 查询所有
     * @return
     */
    List<Student> select(String sql) throws Exception;
}
