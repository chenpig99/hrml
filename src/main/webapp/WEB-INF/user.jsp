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
            return "??????";
            case "prev":
            return "?????????";
            case "next":
            return "?????????";
            case "last":
            return "??????";
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
        <label for="userNameId" class="col-sm-2 control-label">?????????</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" id="userNameId" placeholder="????????????????????????" name="username" value="${user.username}">
        </div>
        <label for="userNameId" class="col-sm-2 control-label">????????????</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" id="userStatus" placeholder="???????????????" name="status" value="${user.status}">
        </div>
        <div class="col-sm-6">
            <button type="button" class="btn btn-default" id="querybtnId">??????</button>
            <button type="button" class="btn btn-default" id="deluser">??????</button>
        </div>
    </div>
</form>
<table class="table table-bordered table-responsive" id="tab">
    <thead>
    <tr>
        <th><input type='checkbox' name='checked'  ></th>
        <th>?????????</th>
        <th>????????????</th>
        <th>?????????</th>
        <th>??????</th>
        <th>????????????</th>
        <th>??????</th>
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
                <td><button type='button' name='update'/>??????</td>
            </tr>
    </c:forEach>
    </tbody>
</table>
<!--??????-->
<ul id="pagination"></ul>

</body>
</html>
