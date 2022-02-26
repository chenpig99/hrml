package com.gec.hrml.controller;

import com.gec.hrml.entity.DocumentInf;
import com.gec.hrml.entity.UserInf;
import com.gec.hrml.srvice.DocumentService;
import com.gec.hrml.srvice.Impl.DocumentServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
@WebServlet(name = "DocumentServlet", value = {
                "/controller/documentAdd",
                "/controller/documentDel",
                "/controller/ForDocument",
                "/controller/queryDocumentByPage",
                 "/controller/UploadFileServlet",
                "/controller/UploadFileJsp",
        "/controller/DownloadServlet"
})
@MultipartConfig
public class DocumentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取提交路径
        String uri=request.getRequestURI();
        String action=uri.substring(uri.indexOf("/controller/")+12,uri.length());
        request.setCharacterEncoding("utf-8");
        //创建业务类对象
        DocumentService documentService=new DocumentServiceImpl();

        update(request, response, action, documentService);

    }

    private void update(HttpServletRequest request, HttpServletResponse response, String action, DocumentService documentService) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        switch (action) {
            case "documentDel":
                //实现删除功能
                int del = 0;
                Map<String, String[]> deptName = request.getParameterMap();
                for (String[] value2 : deptName.values()) {
                    for (String value : value2) {
                        del = documentService.delete(value);
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
                request.getRequestDispatcher("/WEB-INF/document.jsp").forward(request, response);
                break;
            }
            case "queryDocumentByPage": {
                //获取当前页码
                String pageIndex = request.getParameter("pageIndex");
                if(pageIndex==null)
                {
                    pageIndex="1";
                }
                //1. 获取请求参数
                String title=request.getParameter("title");
                DocumentInf document = new DocumentInf();
                document.setPageIndex(Integer.parseInt(pageIndex));
                try {
                    document.setTitle(title);
                    //查询表的所有数据
                    List<DocumentInf> documentList = documentService.queryByPage(document);
                    //输出到前端
                    for (DocumentInf user1 : documentList) {
                        System.out.println(user1.getTitle()+"  "+user1.getFilename()+"name插叙");
                    }
                    response.setCharacterEncoding("utf-8");
                    request.setAttribute("documentList",documentList);
                    request.setAttribute("document",document);
                    request.getRequestDispatcher("/WEB-INF/document.jsp").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                }
                break;
        } case "UploadFileJsp":{

            //跳转到上传页
            request.getRequestDispatcher("/WEB-INF/uploaddoucment.jsp").forward(request, response);
            }
        case "UploadFileServlet" :{
            Part part=request.getPart("file");
            String contentDisposition=part.getHeader("Content-Disposition");
            //获得上传文件名
            String upLoadFileName=contentDisposition.substring(contentDisposition.indexOf("filename=\"")+10,contentDisposition.lastIndexOf("\""));
            //去除文件后缀
            String[] up=upLoadFileName.split("\\.");
            String FileName=up[0];
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
            //得到登陆的用户信息
//            UserInf loginUser= (UserInf) request.getAttribute("loginUser");
            UserInf loginUser=new UserInf();
            loginUser.setId(11);
            String title=request.getParameter("title");
            String remark=request.getParameter("remark");
            //创建时间
            Date date = new Date();//获得系统时间.

            String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);//将时间格式转换成符合Timestamp要求的格式.

            Timestamp goodsC_date =Timestamp.valueOf(nowTime);//把时间转换
            //创建上传文件的记录
            DocumentInf uploadDoucment=new DocumentInf();
            uploadDoucment.setCreateDate(goodsC_date);
            uploadDoucment.setRemark(remark);
            uploadDoucment.setTitle(title);
            uploadDoucment.setFilename(upLoadFileName);
            //保存数据
            int pos=documentService.save(uploadDoucment, loginUser);
            if (pos > 0) {
                //数据保存成功
                response.getWriter().println("上传成功");
            } else {
                //数据保存失败
                response.getWriter().println("上传失败");
            }
            System.out.println(uploadFilePath+"上传路径");

        } case "DownloadServlet":{
                //文件名
                String filename=request.getParameter("filename");
                String uploadFilePath= getServletContext().getRealPath("/upload/"+filename);
                response.setContentType("application/zip");
                response.setHeader("Content-disposition","attachment;filename=\""+filename+"\"");

               String downFilePath=getServletContext().getRealPath("/WEB-INF/servlet.zip");

                OutputStream outputStream=response.getOutputStream();
                FileInputStream fileInputStream=new FileInputStream(uploadFilePath);

                System.out.println(uploadFilePath);

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
                response.getWriter().println(1);
            }
            }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
