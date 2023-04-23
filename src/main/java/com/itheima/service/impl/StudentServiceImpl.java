package com.itheima.service.impl;

import com.itheima.pojo.Student;
import com.itheima.service.StudentService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    @Override
    public List<Student> select(String sql) throws Exception {
        List<Student> studentList = new ArrayList<>();
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
            String Sid = rs.getString("Sid");
            String Sname = rs.getString("Sname");
            String Sage = rs.getString("Sage");
            String Ssex = rs.getString("Ssex");
            String Sclass = rs.getString("Sclass");
            String Sdept = rs.getString("Sdept");
            String Saddr = rs.getString("Saddr");
            Student student = new Student();
            student.setSid(Sid);
            student.setSname(Sname);
            student.setSage(Sage);
            student.setSsex(Ssex);
            student.setSclass(Sclass);
            student.setSdept(Sdept);
            student.setSaddr(Saddr);
            studentList.add(student);
        }
        return studentList;
    }
}
