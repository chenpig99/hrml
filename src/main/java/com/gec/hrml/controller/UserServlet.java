package com.gec.hrml.controller;

import com.gec.hrml.entity.UserInf;
import com.gec.hrml.srvice.Impl.UserServiceImpl;
import com.gec.hrml.srvice.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(value = {
        "/controller/userAdd",
        "/controller/userDel",
        "/controller/userUpdateSave",
        "/controller/FindUserName",
        "/controller/queryUser",
        "/controller/queryUserByPage"})
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取提交路径
        String uri=request.getRequestURI();
        String action=uri.substring(uri.indexOf("/controller/")+12,uri.length());
        request.setCharacterEncoding("utf-8");
        //创建业务类对象
        UserService userService=new UserServiceImpl();
        if(action.equals("userAdd"))
        {
            request.setCharacterEncoding("UTF-8");
            //实现用户保存操作
            UserInf user=new UserInf();
            //读取请求参数
            String userName = request.getParameter("username");
            String passWord = request.getParameter("password");
            String loginame = request.getParameter("loginname");
            String statue = request.getParameter("status");
            //创建时间
            Date date = new Date();//获得系统时间.

            String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);//将时间格式转换成符合Timestamp要求的格式.

            Timestamp goodsC_date =Timestamp.valueOf(nowTime);//把时间转换
            user.setUsername(userName);
            user.setPassword(passWord);
            user.setLoginname(loginame);
            user.setStatus(statue);
            user.setCreatedate(goodsC_date);

            System.out.println(user.toString());

            //1. 获取请求参数
//            Map<String, String[]> parameterMap = request.getParameterMap();
//            try {
//                BeanUtils.populate(user,parameterMap);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }

            int pos=userService.save(user);

            if(pos>0)
            {
                //数据保存成功
                response.getWriter().println("1");
            }else {
                //数据保存失败
                response.getWriter().println("0");
            }
        }else if(action.equals("userDel"))
        {
            int del=0;
            Map<String, String[]> loginname = request.getParameterMap();
            for (String[] value2 : loginname.values()) {
                for (String value : value2) {
                    del=userService.delete(value);
                }
            }
            if(del==1){
                response.getWriter().println(1);
            }else {
                response.getWriter().println(0);
            }


            //实现删除功能
//
        }else if(action.equals("userUpdateSave"))
        {
            //读取请求参数
//            String userName = request.getParameter("username");
//            String passWord = request.getParameter("password");
//            String loginame = request.getParameter("loginame");
//            String statue = request.getParameter("statue");
//            user.setUsername(userName);
//            user.setPassword(passWord);
//            user.setLoginname(loginame);
//            user.setStatus(Long.parseLong(statue));
            //创建一个User对象
            int pos=0;
            UserInf user=new UserInf();
            request.setCharacterEncoding("UTF-8");
            //1. 获取请求参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            try {
                BeanUtils.populate(user,parameterMap);
                System.out.println(user.toString());
                //实现更新功能
                pos= userService.update(user);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


            if(pos>0)
            {
                //数据修改成功
                response.getWriter().println("1");
            }else {
                //修改
                response.getWriter().println("0");
            }
        }else if(action.equals("queryUser"))
        {
            UserInf user=new UserInf();
            //查询表的所有数据
            List<UserInf> userList = userService.query(user);
            for (UserInf user1 : userList) {
                System.out.println(user1.getUsername()+"  "+user1.getPassword());
            }

            //将集合对象解释成json数据格式，依赖jackson库
//            ObjectMapper objectMapper=new ObjectMapper();
//            String json=objectMapper.writeValueAsString(userList);
//
//            response.setContentType("text/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().println(json);
            //将数据保存到请求作用域
            request.setAttribute("userList",userList);
            //转发到视图组件显示
            request.getRequestDispatcher("/main.jsp").forward(request,response);
        }else if(action.equals("FindUserName")) {
            //1. 获取请求参数
            Map<String, String[]> parameterMap = request.getParameterMap();

            request.setCharacterEncoding("UTF-8");


            UserInf user = new UserInf();
            UserInf userAdd = null;
            try {
                BeanUtils.populate(user, parameterMap);
                //找到用户所有信息
                userAdd = userService.findUserByName(user.getUsername());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            request.setAttribute("user", userAdd);
            request.getRequestDispatcher("/WEB-INF/userupdate.jsp").forward(request, response);
        }else if(action.equals("queryUserByPage"))

        {
            //获取当前页码
            String pageIndex = request.getParameter("pageIndex");
            if(pageIndex==null)
            {
                pageIndex="1";
            }

            UserInf user=new UserInf();
            user.setPageIndex(Integer.valueOf(pageIndex));
            request.setCharacterEncoding("UTF-8");
            //1. 获取请求参数
            Map<String, String[]> parameterMap = request.getParameterMap();

            try {
                BeanUtils.populate(user,parameterMap);
                //查询表的所有数据
                List<UserInf> userList = userService.queryByPage(user);
                for (UserInf user1 : userList) {
                    System.out.println(user1.getUsername()+"  "+user1.getPassword()+"name插叙");
                }
                request.setAttribute("userList",userList);
                request.setAttribute("user",user);
                request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}
