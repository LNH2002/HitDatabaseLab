package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Course;
import com.itheima.pojo.Student;
import com.itheima.service.CourseService;
import com.itheima.service.StudentService;
import com.itheima.service.impl.CourseServiceImpl;
import com.itheima.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/selectCourseServlet")
public class SelectCourseServlet extends HttpServlet {

    private final CourseService courseService = new CourseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String sql = request.getParameter("sql");
        sql = URLDecoder.decode(sql, String.valueOf(StandardCharsets.UTF_8));

        List<Course> courseList = null;
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "查询成功");
        try {
            System.out.println("=======>" + sql);
            courseList = courseService.select(sql);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            e.printStackTrace();
        }
        map.put("data", courseList);
        if (courseList != null) {
            map.put("rowCnt", courseList.size());
        } else {
            map.put("rowCnt", 0);
        }

        List<Map<String, String>> tableHead = new ArrayList<>();
        Map<String, String> sidMap = new HashMap<>();
        sidMap.put("column_name", "cid");
        sidMap.put("column_comment", "Cid");
        tableHead.add(sidMap);

        Map<String, String> snameMap = new HashMap<>();
        snameMap.put("column_name", "cname");
        snameMap.put("column_comment", "Cname");
        tableHead.add(snameMap);

        Map<String, String> sageMap = new HashMap<>();
        sageMap.put("column_name", "credit");
        sageMap.put("column_comment", "Credit");
        tableHead.add(sageMap);

        Map<String, String> ssexMap = new HashMap<>();
        ssexMap.put("column_name", "chours");
        ssexMap.put("column_comment", "Chours");
        tableHead.add(ssexMap);

        Map<String, String> sclassMap = new HashMap<>();
        sclassMap.put("column_name", "tid");
        sclassMap.put("column_comment", "Tid");
        tableHead.add(sclassMap);

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