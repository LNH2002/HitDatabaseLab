package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.SC;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/selectScServlet")
public class SelectScServlet extends HttpServlet {

    private final SCService ScService = new SCServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String sql = request.getParameter("sql");
        sql = URLDecoder.decode(sql, String.valueOf(StandardCharsets.UTF_8));

        List<SC> SCList = null;
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "查询成功");
        try {
            System.out.println("=======>" + sql);
            SCList = ScService.select(sql);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            e.printStackTrace();
        }
        map.put("data", SCList);
        if (SCList != null) {
            map.put("rowCnt", SCList.size());
        } else {
            map.put("rowCnt", 0);
        }

        List<Map<String, String>> tableHead = new ArrayList<>();

        Map<String, String> sidMap = new HashMap<>();
        sidMap.put("column_name", "sid");
        sidMap.put("column_comment", "Sid");
        tableHead.add(sidMap);

        Map<String, String> cidMap = new HashMap<>();
        cidMap.put("column_name", "cid");
        cidMap.put("column_comment", "Cid");
        tableHead.add(cidMap);

        Map<String, String> scoreMap = new HashMap<>();
        scoreMap.put("column_name", "score");
        scoreMap.put("column_comment", "Score");
        tableHead.add(scoreMap);


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