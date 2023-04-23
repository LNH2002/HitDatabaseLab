package com.itheima.service.impl;

import com.itheima.pojo.Course;
import com.itheima.pojo.Student;
import com.itheima.service.CourseService;
import com.itheima.service.StudentService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {

    @Override
    public List<Course> select(String sql) throws Exception {
        List<Course> courseList = new ArrayList<>();
        //2. 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/school";
        String username = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url, username, password);
        //4. 获取执行sql的对象 Statement
        Statement stmt = conn.createStatement();
        //5. 执行sql
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println("yes");
            String Cid = rs.getString("Cid");
            String Cname = rs.getString("Cname");
            String Credit = rs.getString("Credit");
            int Chours = rs.getInt("Chours");
            String Tid = rs.getString("Tid");

            Course course = new Course();
            course.setCid(Cid);
            course.setCname(Cname);
            course.setCredit(Credit);
            course.setChours(Chours);
            course.setTid(Tid);
            courseList.add(course);
        }
        return courseList;
    }
}
