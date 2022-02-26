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
        //修改操作
        $("#update").click(function (){

            var pa={"title":$("#title").val(),"content":$("#content").text()};
            $.ajax({
                type:"post",
                data:pa,
                url:'${pageContext.servletContext.contextPath}/controller/noticeUpdate',
                success: function(data) {
                    if(data==0){
                        alert('修改失败');
                    }if(data==1){
                        alert('修改成功');
                    }
                }
            })
        })

    })
</script>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form class="form-horizontal" id="form" method="post" action="${pageContext.servletContext.contextPath}/controller/noticeAdd">
    <p>
        公告标题:
        <input type="text" class="form-control" name="title" id="title">
    </p>
    <p>
        公告描述:
        <input type="text" class="form-control" name="content" id="content" >
    </p>
    <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" value="保存" id="add">保存</button>
        <button type="button" value="修改" id="update">修改</button>
    </div>
</form>
</body>
</html>
