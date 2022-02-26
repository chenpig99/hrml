<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2022/2/23
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
<script src="${pageContext.servletContext.contextPath}/js/jquery-3.2.1.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        <%--$("#add").click(function (){--%>
        <%--    $.ajax({--%>
        <%--        type:"post",--%>
        <%--        data:$("#form").submit(),--%>
        <%--        url:'${pageContext.servletContext.contextPath}/controller/DeptAdd',--%>
        <%--        success: function(data) {--%>
        <%--            if(data==0){--%>
        <%--                alert('修改失败');--%>
        <%--            }if(data==1){--%>
        <%--                alert('修改成功');--%>
        <%--            }--%>
        <%--        }--%>
        <%--    })--%>
        <%--})--%>
        $("#cancel").click(function () {

        })
    })
</script>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form class="form-horizontal" id="form" method="post" action="${pageContext.servletContext.contextPath}/controller/DeptAdd">
    <p>
        部门名称:
    <input type="text" class="form-control" name="name">
    </p>
    <p>
        详细描述:
        <input type="text" class="form-control" name="remark" >
    </p>
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" value="保存" id="add">保存</button>
            <button type="button" value="取消" id="cancel">取消</button>
            <a href="javascript:window.opener=null;window.open('','_self');window.close();">关闭</a>
        </div>
</form>
</body>
</html>
