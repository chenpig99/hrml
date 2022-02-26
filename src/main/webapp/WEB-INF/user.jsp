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
            $(document).ready(function(){

                $("#deluser").click(function () {
                    $('#tab').find(':checkbox').each(function(){
                        if ($(this).is(":checked")) {
                            var k=$(this).parent().parent().find("td");
                            $(this).parent().parent().remove();
                            delarray.push(k[1].innerHTML);
                        }
                        var par={"userlist":delarray};
                        $.ajax({
                            data: par,
                            url:"${pageContext.servletContext.contextPath}/controller/userDel",
                            success: function(data) {
                               if(data==1){
                                    delarray=new Array();
                                }
                            }
                        });
                    });
                })

            });

            $(function(){
            $("#querybtnId").click(function () {

                $("#pageIndex").val(1);
                $("#frmSearch").submit();
            });
            $('#pagination').bootstrapPaginator({
            bootstrapMajorVersion:3,
            currentPage:${user.pageIndex},
            totalPages:${user.totalPageSum},
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
<form class="form-horizontal" id="frmSearch" action="${pageContext.request.contextPath}/controller/queryUserByPage" method="post">
    <input type="hidden" name="pageIndex" value="${user.pageIndex}" id="pageIndex">
    <div class="form-group">
        <label for="userNameId" class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" id="userNameId" placeholder="请输入您的用户名" name="username" value="${user.username}">
        </div>
        <label for="userNameId" class="col-sm-2 control-label">用户状态</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" id="userStatus" placeholder="请输入状态" name="status" value="${user.status}">
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
        <th>登录名</th>
        <th>用户密码</th>
        <th>用户名</th>
        <th>状态</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userList}" var="user">
        <tr>
                <td><input type='checkbox' name='checked'  ></td>
                <td>${user.loginname}</td>
                <td>${user.password}</td>
                <td>${user.username}</td>
                <td>${user.status}</td>
                <td>${user.createdate}</td>
                <td><button type='button' name='update'/>操作</td>
            </tr>
    </c:forEach>
    </tbody>
</table>
<!--代码-->
<ul id="pagination"></ul>

</body>
</html>
