package com.itheima.service;

import com.itheima.pojo.Course;

import java.util.List;

public interface CourseService {

    /**
     * 查询所有
     *
     * @return
     */
    List<Course> select(String sql) throws Exception;
}
