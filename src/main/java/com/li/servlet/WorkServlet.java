package com.li.servlet;

import com.alibaba.fastjson.JSON;
import com.li.pojo.PageBean;
import com.li.pojo.Work;
import com.li.service.WorkService;
import com.li.service.impl.WorkServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/work/*")
public class WorkServlet extends BaseServlet {

    private WorkService workService = new WorkServiceImpl();

    public void test(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("这里是测试方法。。。");
    }

    public void select(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //获取请求行携带的参数
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int pageCount = Integer.parseInt(req.getParameter("pageCount"));
        //获取请求体携带的参数
        BufferedReader br = req.getReader();
        String params = br.readLine();
        //将请求体参数的json格式转换成work对象
        Work work = JSON.parseObject(params, Work.class);
        //调用work业务层对象按条件查询
        PageBean<Work> workPageBean = workService.selectWorksByPageAndCondition(currentPage, pageCount, work);
        //转换成json格式后响应到浏览器
        resp.getWriter().write(JSON.toJSONString(workPageBean));
    }

    public void selectOne(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //获取请求行携带的参数
        int wid = Integer.parseInt(req.getParameter("wid"));
        //调用work业务层对象按wid查询
        Work work = workService.selectWorkByWid(wid);
        //转换成json格式后响应到浏览器
        resp.getWriter().write(JSON.toJSONString(work));
    }


    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //获取请求体携带的参数
        BufferedReader br = req.getReader();
        String params = br.readLine();
        //将请求体参数的json格式转换成work对象
        Work work = JSON.parseObject(params, Work.class);
        //调用work业务层对象添加作品
        int count = workService.addWork(work);
        //如果返回的条数大于0，则响应浏览器success，反之响应浏览器warning
        if (count > 0) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("warning");
        }
    }


    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //获取请求体携带的参数
        BufferedReader br = req.getReader();
        String params = br.readLine();
        //将请求体参数的json格式转换成work对象
        Work work = JSON.parseObject(params, Work.class);
        //调用work业务层对象添加作品
        int count = workService.updateWork(work);
        //如果返回的条数大于0，则响应浏览器success，反之响应浏览器warning
        if (count > 0) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("warning");
        }
    }



    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //通过地址获取参数
        int wid = Integer.parseInt(req.getParameter("wid"));
        //调用work业务层对象删除作品，并返回删除的条数
        int count = workService.deleteWorkByWid(wid);
        //如果返回的条数大于0，则响应浏览器success，反之响应浏览器warning
        if (count > 0) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("warning");
        }
    }
}
