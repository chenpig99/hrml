<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2022/2/24
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form method="post" enctype="multipart/form-data" class="form-horizontal">
    <div class="form-group">
        <label for="exampleInputEmail1">文档标题</label>
        <input type="text" class="form-control" id="exampleInputEmail1" name="title" >
    </div>
    <div class="form-group">
        <label>文档描述</label>
        <div class="input-group input-group-lg">
            <span class="input-group-addon" id="sizing-addon1"></span>
            <input type="text" class="form-control"  name="remark" aria-describedby="sizing-addon10">
        </div>
    </div>
    <div class="form-group">
        <label for="exampleInputFile">文档</label>
        <input type="file" name="file" id="exampleInputFile">
    </div>
    <button type="submit" class="btn btn-default">上传</button>
    <button type="reset" class="btn btn-default">重置</button>
</form>
</body>
</html>
