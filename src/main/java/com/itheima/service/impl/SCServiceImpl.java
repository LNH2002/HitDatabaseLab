package com.itheima.service.impl;

import com.itheima.pojo.SC;
import com.itheima.pojo.Student;
import com.itheima.service.SCService;
import com.itheima.service.StudentService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SCServiceImpl implements SCService {

    @Override
    public List<SC> select(String sql) throws Exception {
        List<SC> SCList = new ArrayList<>();
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
            String Cid = rs.getString("Cid");
            String Score = rs.getString("Score");
            SC Sc = new SC();
            Sc.setSid(Sid);
            Sc.setCid(Cid);
            Sc.setScore(Score);
            SCList.add(Sc);
        }
        return SCList;
    }
}
