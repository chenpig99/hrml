<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2022/2/22
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.servletContext.contextPath}/js/jquery-3.2.1.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap-paginator.js"></script>
    <script type="text/javascript">
        var delarray=new Array();
        <%--function k() {--%>
        <%--    var name=$(this).parent();--%>
<%--        &lt;%&ndash;    console.log(name);&ndash;%&gt;--%>
<%--            var filename=new {"filename":name[2].innerHTML};--%>
<%--            console.log(filename);--%>
<%--            $.ajax({--%>
<%--                data: filname,--%>
<%--                url:"${pageContext.servletContext.contextPath}/controller/DownloadServlet",--%>
<%--                success: function(data) {--%>
<%--                }--%>
<%--            });--%>
        <%--}--%>
        // $('table tr').click(function () {
        //     // 查找出索引
        //     console.log($(this).index());
        // });
        $(document).ready(function(){

            $("#deluser").click(function () {
                $('#tab').find(':checkbox').each(function(){
                    if ($(this).is(":checked")) {
                        var k=$(this).parent().parent().find("td");
                        $(this).parent().parent().remove();
                        delarray.push(k[1].innerHTML);
                    }
                    //返回删除的标题数组
                    var par={"documentlist":delarray};
                    $.ajax({
                        data: par,
                        url:"${pageContext.servletContext.contextPath}/controller/documentDel",
                        success: function(data) {
                            if(data==1){
                                delarray=new Array();
                            }
                        }
                    });
                });
            });
            var button=$("input[name='download']");
            button.click(function(){
                var clickRow=$(this).parents("tr")[0];//获取被点击按钮所在的行, 返回的是javascript 的DOM对象,要注意
                var file=$(clickRow).find("td")[2].innerHTML;
                var filename={"filename":file};
                console.log(filename);
                $.ajax({
                    data: filename,
                    url:"${pageContext.servletContext.contextPath}/controller/DownloadServlet",
                    success: function(data) {
                        if(data){
                            alert("文件响应成功!请在响应中下载!");
                        }
                    }
                });
            })

        });
        // $("button[name='download']")

        $(function(){
            $("#querybtnId").click(function () {

                $("#pageIndex").val(1);
                $("#frmSearch").submit();
            });
            $('#pagination').bootstrapPaginator({
                bootstrapMajorVersion:3,
                currentPage:${document.pageIndex},
                totalPages:${document.totalPageSum},
                onPageClicked:function(event, originalEvent, type, page){
                    $('#pageIndex').val(page);
                    $('#frmSearch').submit();
                },
                itemTexts: function (type, page, current) {
                    switch (type) {
                        case "first":
                            return "首页";
                        case "prev":
                            return "上一页";
                        case "next":
                            return "下一页";
                        case "last":
                            return "末页";
                        case "page":
                            return page;
                    }
                }
            });
        });
    </script>

</head>
<body>
<form class="form-horizontal" id="frmSearch" action="${pageContext.request.contextPath}/controller/queryDocumentByPage" method="post">
    <input type="hidden" name="pageIndex" value="${document.pageIndex}" id="pageIndex">
    <div class="form-group">
        <label for="downloadId" class="col-sm-2 control-label">标题</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" id="downloadId" placeholder="请输入您的用户名" name="title" value="${document.title}">
        </div>
        <div class="col-sm-6">
            <button type="button" class="btn btn-default" id="querybtnId">查询</button>
            <button type="button" class="btn btn-default" id="deluser">删除</button>
        </div>
    </div>
</form>
<table class="table table-bordered table-responsive" id="tab">
    <thead>
    <tr>
        <th><input type='checkbox' name='checked'  ></th>
        <th>标题</th>
        <th>文件名</th>
        <th>创建时间</th>
        <th>创建人</th>
        <th>描述</th>
        <th>操作</th>
        <th>下载</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${documentList}" var="document">
        <tr>
            <td><input type='checkbox' name='checked'  ></td>
            <td>${document.title}</td>
            <td>${document.filename}</td>
            <td>${document.createDate}</td>
            <td>${document.userId}</td>
            <td>${document.remark}</td>
            <td><button type='button' name='update' value="操作"/></td>
            <td><input type='button' name='download' value="下载"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!--代码-->
<ul id="pagination"></ul>

</body>
</html>

