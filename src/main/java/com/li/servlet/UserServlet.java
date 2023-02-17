package com.li.servlet;

import com.alibaba.fastjson.JSON;
import com.li.pojo.User;
import com.li.service.UserService;
import com.li.service.impl.UserServiceImpl;
import com.utils.CheckCodeUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    public void test(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("这里是测试方法。。。");
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {

//        String isRemember = req.getParameter("isRemember");

        BufferedReader br = req.getReader();
        String params = br.readLine();

        User user = JSON.parseObject(params, User.class);

        user = userService.login(user.getUserName(), user.getPassword());

        if (user != null) {

//            if( "true".equals(isRemember) ) {
//                Cookie c_userName = new Cookie("userName", user.getUserName());
//                Cookie c_password = new Cookie("password", user.getPassword());
//
//                c_userName.setMaxAge(60 * 60 * 24 * 7);
//                c_password.setMaxAge(60 * 60 * 24 * 7);
//
//                resp.addCookie(c_userName);
//                resp.addCookie(c_password);
//            }

            req.getSession().setAttribute("user", user);

            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("warning");
        }
    }


    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String checkCode1 = req.getParameter("checkCode");
        String checkCode2 = (String) req.getSession().getAttribute("checkCode");

        if (!checkCode1.equalsIgnoreCase(checkCode2)) {

            resp.getWriter().write("check_msg");

            return;
        }


        BufferedReader br = req.getReader();
        String params = br.readLine();

        User user = JSON.parseObject(params, User.class);

        int count = userService.register(user);

        if (count > 0) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("warning");
        }
    }


    public void checkCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletOutputStream outputStream = resp.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, outputStream, 4);

        req.getSession().setAttribute("checkCode", checkCode);
    }
}
