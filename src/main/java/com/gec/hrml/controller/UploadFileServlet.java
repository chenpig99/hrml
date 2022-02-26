package com.gec.hrml.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "UploadFileSservlet", value = "/UploadFileServlet")
//上传文件的注解
@MultipartConfig
public class UploadFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part=request.getPart("file");
        String contentDisposition=part.getHeader("Content-Disposition");
        //获得上传文件名
        String upLoadFileName=contentDisposition.substring(contentDisposition.indexOf("filename=\"")+10,contentDisposition.lastIndexOf("\""));
        //设置上传路径
        String uploadFilePath= getServletContext().getRealPath("/upload");
        File file=new File(uploadFilePath,upLoadFileName);
        File parentFile=file.getParentFile();
        if (!parentFile.exists()){
            //目录不存在则创建文件目录
            parentFile.mkdirs();
        }
        if (!file.exists()){
            //文件不存在则创建文件
            file.createNewFile();
        }
        //输出文件的输出流
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        //获得文件内容
        InputStream in=part.getInputStream();
        byte[] buf=new byte[128];
        int len=-1;
        while ((len=in.read(buf))!=-1)
        {
            fileOutputStream.write(buf,0,len);
        }
        fileOutputStream.close();
        in.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
