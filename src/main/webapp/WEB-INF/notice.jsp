<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2022/2/25
  Time: 0:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.servletContext.contextPath}/js/jquery-3.2.1.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap-paginator.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            var button=$("input[name='del']");
            button.click(function(){
                var clickRow=$(this).parents();//获取被点击按钮所在的行, 返回的是javascript 的DOM对象,要注意
                var file=$(clickRow).find("li");

                var title={"title":file[0].textContent};
                $(clickRow).find("li").remove();
                $.ajax({
                    data: title,
                    url:"${pageContext.servletContext.contextPath}/controller/noticeDel",
                    success: function(data) {
                        if(data==1){
                            alert("删除成功");
                        }else {
                            alert("删除失败");
                        }
                    }
                });
            });
            var look=$("input[name='look']");
            look.click(function(){
                var clickRow=$(this).parents();//获取被点击按钮所在的行, 返回的是javascript 的DOM对象,要注意
                var file=$(clickRow).find("li");
                var title={"title":file[0].textContent};
                $.ajax({
                    data: title,
                    url:"${pageContext.servletContext.contextPath}/controller/lookNotice",
                    success: function(data) {
                        if(data==1){

                        }

                    }
                });
            })

        });
    </script>
</head>
<body>
<div class="row pre-scrollable">
<ul class="list-group">
    <c:forEach items="${noticeList}" var="notice">
        <li class="list-group-item">${notice.title} <input type="button" name="del" value="删除" /><input type="button" name="look" value="查看"/></li>
    </c:forEach>
</ul>
</div>
</body>
</html>
