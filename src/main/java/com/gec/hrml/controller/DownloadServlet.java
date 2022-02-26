package com.gec.hrml.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "DownloadServlet", value = "/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //文件名
        String filename="servlet.zip";
        String uploadFilePath= getServletContext().getRealPath("/upload");
        response.setContentType("application/zip");
        response.setHeader("Content-disposition","attachment;filename=\""+filename+"\"");

        String downFilePath=getServletContext().getRealPath("/WEB-INF/servlet.zip");

        OutputStream outputStream=response.getOutputStream();
        FileInputStream fileInputStream=new FileInputStream(downFilePath);

        System.out.println(downFilePath);

        int len=-1;
        byte buf[]=new byte[128];

        while ((len=fileInputStream.read(buf))!=-1)
        {
            outputStream.write(buf,0,len);
        }

        //关闭输入流
        fileInputStream.close();
        //关闭输出流
        outputStream.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
