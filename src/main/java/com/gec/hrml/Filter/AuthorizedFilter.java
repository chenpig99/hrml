package com.gec.hrml.Filter;



import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author 1
 */
@WebFilter("/controller/*")
public class AuthorizedFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest httpRequest=(HttpServletRequest) request;
        HttpServletResponse httpResponse=(HttpServletResponse) response;

        httpRequest.setCharacterEncoding("UTF-8");

        String servletPath=httpRequest.getServletPath();

        System.out.println("servletPath="+servletPath);

        boolean isFilter=true;

        if(isFilter)
        {
            //如何判断是否登录
            //通过session
            HttpSession session=httpRequest.getSession(false);
            if(session!=null&&session.getAttribute("loginUser")!=null)
            {
                chain.doFilter(request, response);

            }else
            {
                //返回登录页
                httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath()+"/login.jsp");
            }
        }else
        {
            //可以访问目标组件
            chain.doFilter(request, response);
        }

    }
}
