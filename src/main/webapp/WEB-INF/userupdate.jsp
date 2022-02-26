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
        $("#update").click(function (){
            var pat={"username":$("#username").val(),"status":$("#status").val(),"password":$("#password").val(),"loginname":$("#loginname").val()};
            $.ajax({
                type:"post",
                data:pat,
                url:'${pageContext.servletContext.contextPath}/controller/userUpdateSave',
                success: function(data) {
                    if(data==0){
                        alert('修改失败');
                    }if(data==1){
                        alert('修改成功');
                    }
                }
            })
        })
        $("#add").click(function (){
            var pa={"username":$("#username").val(),"status":$("#status").val(),"password":$("#password").val(),"loginname":$("#loginname").val()};
            $.ajax({
                type:"post",
                data:pa,
                url:'${pageContext.servletContext.contextPath}/controller/userAdd',
                success: function(data) {
                    if(data==0){
                        alert('保存失败');
                    }if(data==1){
                        alert('保存成功');
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


<form class="form-horizontal" id="form">
    <div class="form-group">
        <label  class="col-sm-2 control-label">姓名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="username" name="username" value="${loginUser.username}" >
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-2 control-label">状态</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="status" name="status" value="${loginUser.status}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id='password' name="password" value="${loginUser.password}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">登陆名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="loginname" name="loginname" value="${loginUser.loginname}">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" value="修改" id="update">修改</button>
            <button type="button" value="保存" id="add">保存</button>
        </div>
    </div>
</form>
</body>
</html>
