package com.gec.hrml.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gec.hrml.entity.JobInf;
import com.gec.hrml.srvice.Impl.JobServiceImpl;
import com.gec.hrml.srvice.JobService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "JobServlet", value = {
        "/controller/JobAdd",
        "/controller/JobDel",
        "/controller/JobUpdateSave",
        "/controller/queryJobByPage"})
public class JobServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取提交路径
        String uri=request.getRequestURI();
        String action=uri.substring(uri.indexOf("/controller/")+12,uri.length());

        //创建业务类对象
        JobService jobService=new JobServiceImpl();

        update(request, response, action, jobService);

    }

    private void update(HttpServletRequest request, HttpServletResponse response, String action, JobService jobService) throws IOException {
        switch (action) {
            case "JobAdd": {
                //读取请求参数h
                Map<String, String[]> parameterMap = request.getParameterMap();
                JobInf jobInf = new JobInf();
                try {
                    BeanUtils.populate(jobInf, parameterMap);
                    int pos = jobService.save(jobInf);
                    if (pos > 0) {
                        //数据保存成功
                        response.getWriter().println("1");
                    } else {
                        //数据保存失败
                        response.getWriter().println("0");
                    }
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "JobDel":
                //实现删除功能
                int del = 0;
                Map<String, String[]> deptName = request.getParameterMap();
                for (String[] value2 : deptName.values()) {
                    for (String value : value2) {
                        del = jobService.delete(value);
                    }
                }
                if (del == 1) {
                    response.getWriter().println(1);
                } else {
                    response.getWriter().println(0);
                }
                break;
            case "JobUpdateSave": {
                //得到前端数据
                Map<String, String[]> parameterMap = request.getParameterMap();
                JobInf jobInf= new JobInf();
                try {
                    //将数据映射到对象中
                    BeanUtils.populate(jobInf, parameterMap);
                    int pos = jobService.update(jobInf);
                    //实现更新功能
                    if (pos > 0) {
                        //数据保存成功
                        response.getWriter().println("1");
                    } else {
                        //数据保存失败
                        response.getWriter().println("0");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "queryJobByPage": {
                //获取当前页码
                String pageIndex = request.getParameter("pageIndex");
                if(pageIndex==null)
                {
                    pageIndex="1";
                }
                request.setCharacterEncoding("UTF-8");
                //1. 获取请求参数
                Map<String, String[]> parameterMap = request.getParameterMap();
                JobInf jobinf= new JobInf();
                try {
                    //映射到对象中,前端传入json
                    BeanUtils.populate(jobinf, parameterMap);
                    jobinf.setPageIndex(Integer.parseInt(pageIndex));
                    //查询表的所有数据
                    List<JobInf> jobList = jobService.queryByPage(jobinf);
                    System.out.println("回到家风"+jobinf.getPageIndex());
                    //输出到前端
                    response.setCharacterEncoding("utf-8");
                    request.setAttribute("jobList",jobList);
                    request.setAttribute("jobinf",jobinf);
                    request.getRequestDispatcher("/WEB-INF/job.jsp").forward(request,response);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
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
