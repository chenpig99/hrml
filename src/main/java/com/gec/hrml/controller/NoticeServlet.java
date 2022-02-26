package com.gec.hrml.controller;

import com.gec.hrml.entity.DeptInf;
import com.gec.hrml.entity.NoticeInf;
import com.gec.hrml.entity.UserInf;
import com.gec.hrml.srvice.DeptService;
import com.gec.hrml.srvice.Impl.DeptServiceImpl;
import com.gec.hrml.srvice.Impl.NoticeServiceImpl;
import com.gec.hrml.srvice.NoticeService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "NoticeServlet", value = {
        "/controller/noticeAdd",
        "/controller/noticeUpdate",
        "/controller/noticeDel",
        "/controller/forNotice",
        "/controller/queryNoticeByPage",
        "/controller/lookNotice",
})
public class NoticeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取提交路径
        String uri=request.getRequestURI();
        String action=uri.substring(uri.indexOf("/controller/")+12,uri.length());
        request.setCharacterEncoding("utf-8");
        //创建业务类对象
        NoticeService noticeService=new NoticeServiceImpl();

        update(request, response, action, noticeService);

    }

    private void update(HttpServletRequest request, HttpServletResponse response, String action, NoticeService noticeService) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        switch (action) {
            case "noticeAdd": {
                //读取请求参数
                Map<String, String[]> parameterMap = request.getParameterMap();
                NoticeInf notice = new NoticeInf();
                try {
                    BeanUtils.populate(notice, parameterMap);
                    //得到登陆用户
                   // UserInf userInf=(UserInf) request.getAttribute("user");
                    UserInf userInf=new UserInf();
                    userInf.setId(11);
                    //创建时间
                    Date date = new Date();//获得系统时间.

                    String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);//将时间格式转换成符合Timestamp要求的格式.

                    Timestamp goodsC_date =Timestamp.valueOf(nowTime);//把时间转换
                    userInf.setCreatedate(goodsC_date);
                    int pos =noticeService.save(notice, userInf);
                    response.setContentType("utf-8");
                    if (pos > 0) {
                        //数据保存成功
                        response.getWriter().println("Saved successfully!");
                    } else {
                        //数据保存失败
                        response.getWriter().println("Failed to save!");
                    }
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                break;
            }
            case "noticeDel":
                //实现删除功能
                String titleDel=request.getParameter("title");
                int del=noticeService.delete(titleDel);
                response.setCharacterEncoding("utf-8");
                if (del == 1) {
                    response.getWriter().println(1);
                } else {
                    response.getWriter().println(0);
                }
                break;
            case "forNotice": {
                request.getRequestDispatcher("/WEB-INF/noticeadd.jsp").forward(request, response);
                break;
            }
            case "queryNoticeByPage": {


                try {
                    //查询表的所有数据
                    List<NoticeInf> noticeList = noticeService.query();
                    for (NoticeInf user1 : noticeList) {
                        System.out.println(user1.getTitle()+"bitaotii");
                    }
                    //输出到前端
                    request.setAttribute("noticeList",noticeList);
                    System.out.println(request.getAttribute("notice"));
                    request.getRequestDispatcher("/WEB-INF/notice.jsp").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "noticeUpdate":{
                String title=request.getParameter("title");
                String content=request.getParameter("content");
                NoticeInf noticeInf=new NoticeInf();
                noticeInf.setTitle(title);
                noticeInf.setContent(content);
                int pos=noticeService.update(noticeInf);
                if (pos>0){
                    response.getWriter().println(1);
                }else {
                    response.getWriter().println(0);
                }
                break;
            } case "lookNotice":{
                request.setCharacterEncoding("utf-8");
                String titleLook=request.getParameter("title");
                System.out.println("啥都好说"+titleLook);
                NoticeInf noticeInf= noticeService.queryByTitle(titleLook).get(0);
                if (!noticeInf.getTitle().isEmpty()){
                    response.getWriter().println(noticeInf.getContent().toString());
                }
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
