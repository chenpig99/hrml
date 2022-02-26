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
            console.log(${jobinf.pageIndex}),
            $("#deluser").click(function () {
                $('#tab').find(':checkbox').each(function(){
                    if ($(this).is(":checked")) {
                        var k=$(this).parent().parent().find("td");
                        $(this).parent().parent().remove();
                        delarray.push(k[1].innerHTML);
                    }
                    var par={"deptlist":delarray};
                    $.ajax({
                        type:"post",
                        data: par,
                        url:"${pageContext.servletContext.contextPath}/controller/jobDel",
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
                currentPage:${jobinf.pageIndex},
                totalPages:${jobinf.totalPageSum},
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
<form class="form-horizontal" id="frmSearch" action="${pageContext.request.contextPath}/controller/queryJobByPage" method="post">
    <input type="hidden" name="pageIndex" value="${job.pageIndex}" id="pageIndex">
    <div class="form-group">
        <label for="deptNameId" class="col-sm-2 control-label">职位名称</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" id="deptNameId"  name="name" value="${job.name}">
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
        <th>职位名称</th>
        <th>详细信息</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${jobList}" var="job">
        <tr>
            <td><input type='checkbox' name='checked'  ></td>
            <td>${job.name}</td>
            <td>${job.remark}</td>
            <td><button type='button' name='update'/>操作</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!--代码-->
<ul id="pagination"></ul>

</body>
</html>
