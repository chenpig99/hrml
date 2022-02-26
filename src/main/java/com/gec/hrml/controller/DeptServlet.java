package com.gec.hrml.controller;

import com.gec.hrml.entity.DeptInf;
import com.gec.hrml.srvice.DeptService;
import com.gec.hrml.srvice.Impl.DeptServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DeptServlet", value = {
        "/controller/DeptAdd",
        "/controller/DeptDel",
        "/controller/ForDept",
        "/controller/queryDeptByPage"})
public class DeptServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取提交路径
        String uri=request.getRequestURI();
        String action=uri.substring(uri.indexOf("/controller/")+12,uri.length());
        request.setCharacterEncoding("utf-8");
        //创建业务类对象
        DeptService deptService=new DeptServiceImpl();

        update(request, response, action, deptService);

    }

    private void update(HttpServletRequest request, HttpServletResponse response, String action, DeptService deptService) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        switch (action) {
            case "DeptAdd": {
                //读取请求参数
                Map<String, String[]> parameterMap = request.getParameterMap();
                DeptInf deptInf = new DeptInf();
                try {
                    BeanUtils.populate(deptInf, parameterMap);
                    int pos = deptService.save(deptInf);
                    if (pos > 0) {
                        //数据保存成功
                        response.getWriter().println("保存成功");
                    } else {
                        //数据保存失败
                        response.getWriter().println("保存失败");
                    }
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                break;
            }
            case "DeptDel":
                //实现删除功能
                int del = 0;
                Map<String, String[]> deptName = request.getParameterMap();
                for (String[] value2 : deptName.values()) {
                    for (String value : value2) {
                        del = deptService.delete(value);
                    }
                }
                response.setCharacterEncoding("utf-8");
                if (del == 1) {
                    response.getWriter().println(1);
                } else {
                    response.getWriter().println(0);
                }
                break;
            case "ForDept": {
                request.getRequestDispatcher("/WEB-INF/deptupdate.jsp").forward(request, response);
                break;
            }
            case "queryDeptByPage": {
                //获取当前页码
                String pageIndex = request.getParameter("pageIndex");
                if(pageIndex==null)
                {
                    pageIndex="1";
                }
                //1. 获取请求参数
                Map<String, String[]> parameterMap = request.getParameterMap();
                DeptInf dept = new DeptInf();
                dept.setPageIndex(Integer.parseInt(pageIndex));
                try {
                    //映射对象
                    BeanUtils.populate(dept, parameterMap);
                    //查询表的所有数据
                    List<DeptInf> deptList = deptService.queryByPage(dept);
                    //输出到前端
                    response.setCharacterEncoding("utf-8");
                    request.setAttribute("deptList",deptList);
                    request.setAttribute("dept",dept);
                    request.getRequestDispatcher("/WEB-INF/dept.jsp").forward(request,response);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException | ServletException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
