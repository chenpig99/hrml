package com.gec.hrml.controller;

import com.gec.hrml.entity.UserInf;
import com.gec.hrml.srvice.Impl.UserServiceImpl;
import com.gec.hrml.srvice.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = {"/LoginServlet",
"/Loginout",
})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            String loginname=request.getParameter("loginname");
            String loginpassword=request.getParameter("password");
        String uri=request.getRequestURI();
            //得到上下文
            String cont=request.getContextPath();
        String action=uri.substring(uri.indexOf(cont)+cont.length(),uri.length());

        System.out.println(action);
        UserService userService=new UserServiceImpl();
       UserInf loginuser=userService.findUserByName(loginname);
       if(action.equals("/LoginServlet")) {
           if (loginuser != null) {
               if (loginuser.getPassword().equals(loginpassword)) {
                   if (loginuser.getStatus().equals("0")) {
                       loginuser.setStatus("1");
                       //存到session中
                       HttpSession session = request.getSession();
                       session.setAttribute("loginUser", loginuser);
                       request.getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                   } else {
                       response.getWriter().println("登陆失败!");
                    //git branch -M mainresponse.sendRedirect(request.getServletContext().getContextPath()+"/login.jsp");
                   }
               }
               {
                   response.getWriter().println("登陆失败!");
                //response.sendRedirect(request.getServletContext().getContextPath()+"/login.jsp");
               }
           } else {
               response.getWriter().println("登陆失败!");
            //response.sendRedirect(request.getServletContext().getContextPath()+"/login.jsp");
           }
       }else if (action.equals("/Loginout")){
           UserInf userloginOut= (UserInf) request.getSession().getAttribute("loginUser");

           userloginOut.setStatus("0");
           request.getSession().invalidate();

           response.sendRedirect(request.getServletContext().getContextPath()+"/login.jsp");
       }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
    }
}
