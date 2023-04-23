package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Student;
import com.itheima.service.StudentService;
import com.itheima.service.impl.StudentServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet("/selectStudentServlet")
public class SelectStudentServlet extends HttpServlet {

    private final StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String sql = request.getParameter("sql");
        sql = URLDecoder.decode(sql, String.valueOf(StandardCharsets.UTF_8));

        List<Student> studentList = null;
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "查询成功");
        try {
            System.out.println("=======>" + sql);
            studentList = studentService.select(sql);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            e.printStackTrace();
        }
        map.put("data", studentList);
        if (studentList != null) {
            map.put("rowCnt", studentList.size());
        } else {
            map.put("rowCnt", 0);
        }

        List<Map<String, String>> tableHead = new ArrayList<>();
        Map<String, String> sidMap = new HashMap<>();
        sidMap.put("column_name", "sid");
        sidMap.put("column_comment", "Sid");
        tableHead.add(sidMap);

        Map<String, String> snameMap = new HashMap<>();
        snameMap.put("column_name", "sname");
        snameMap.put("column_comment", "Sname");
        tableHead.add(snameMap);

        Map<String, String> sageMap = new HashMap<>();
        sageMap.put("column_name", "sage");
        sageMap.put("column_comment", "Sage");
        tableHead.add(sageMap);

        Map<String, String> ssexMap = new HashMap<>();
        ssexMap.put("column_name", "ssex");
        ssexMap.put("column_comment", "Ssex");
        tableHead.add(ssexMap);

        Map<String, String> sclassMap = new HashMap<>();
        sclassMap.put("column_name", "sclass");
        sclassMap.put("column_comment", "Sclass");
        tableHead.add(sclassMap);

        Map<String, String> sdeptMap = new HashMap<>();
        sdeptMap.put("column_name", "sdept");
        sdeptMap.put("column_comment", "Sdept");
        tableHead.add(sdeptMap);

        Map<String, String> saddrMap = new HashMap<>();
        saddrMap.put("column_name", "saddr");
        saddrMap.put("column_comment", "Saddr");
        tableHead.add(saddrMap);
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