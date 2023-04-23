package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.service.SCService;
import com.itheima.service.impl.SCServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/executeSqlServlet")
public class ExecuteSqlServlet extends HttpServlet {

    private final SCService ScService = new SCServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 输出sql语句
        String sql = request.getParameter("sql");
        String sct = request.getParameter("sct");
        sql = URLDecoder.decode(sql, String.valueOf(StandardCharsets.UTF_8));
        System.out.println(sct);
        System.out.println("=======>" + sql);


        String url = "jdbc:mysql://127.0.0.1:3306/" + sct;
        String username = "root";
        String password = "123456";

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "查询成功");
        Connection conn;
        ResultSet rs = null;
        ResultSetMetaData metaData = null;
        List<Map<String, Object>> data = new ArrayList<>();

        int columnCount = 0;
        try {
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            //5. 执行sql
            rs = stmt.executeQuery(sql);
            metaData = rs.getMetaData();
            columnCount = metaData.getColumnCount();

            //4. 获取执行sql的对象 Statement

            while (true) {
                if (!rs.next()) break;
                Map<String, Object> temp = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    temp.put(columnName, value);
                }
                data.add(temp);
            }
        } catch (SQLException e) {
            map.put("msg", e.getMessage());
            String jsonString = JSON.toJSONString(map);
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(jsonString);
            e.printStackTrace();
            return;
        }
        map.put("data", data);
        map.put("rowCnt", 0);

        List<Map<String, String>> tableHead = new ArrayList<>();
        ResultSetMetaData finalMetaData = metaData;
        for (int i = 1; i <= columnCount; i++) {
            int finalI = i;
            tableHead.add(new HashMap<String, String>() {{
                try {
                    put("column_name", finalMetaData.getColumnName(finalI));
                    put("column_comment", finalMetaData.getColumnName(finalI));
                } catch (SQLException e) {
                    map.put("msg", e.getMessage());
                    String jsonString = JSON.toJSONString(map);
                    response.setContentType("text/json;charset=utf-8");
                    response.getWriter().write(jsonString);
                    e.printStackTrace();

                }
            }});
        }

        map.put("tableHead", tableHead);


        //2. 转为JSON
        String jsonString = JSON.toJSONString(map);


        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}