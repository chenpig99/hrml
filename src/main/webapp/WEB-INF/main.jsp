<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../jsp/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.servletContext.contextPath}/js/jquery-3.2.1.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>
    <script type="text/javascript">

        /*
        * 此方法就是构建标签页
        * */
        function addTabs(options) {
            console.log($("#" + options.id));
            console.log($("#" + options.id).length);
            //判断是否已存在指定ID的tab
            if ($("#" + options.id).length > 0) {
                $($("#" + options.id + "-li").children("a").get(0)).tab('show');
                $("#" + options.id).siblings().removeClass("active");
                $("#" + options.id).addClass("tab-pane fade in active");

                //重新加载窗体内容
                var iframeObj=$("#" + options.id).children("iframe");
                var src=iframeObj.attr("src");
                $("#" + options.id).children("iframe").attr("src",src);
            }else
            {
                /*      <li><a href="" onclick="">标签页内容<span onclick="">X</span></a></li>
                      <li><a href="" onclick="">标签页内容<span onclick="">X</span></a></li>
                      <li><a href="" onclick="">标签页内容<span onclick="">X</span></a></li>
                      <li><a href="" onclick="">标签页内容<span onclick="">X</span></a></li>*/
                //构建li元素
                var li = $("<li />", {
                    "id": options.id + "-li",
                });

                //构建a元素
                var a = $("<a />", {
                    "href": "#" + options.id,
                    "text": options.title,
                    "click": function () {
                        console.log($(this));
                        $(this).tab("show");
                    }
                });

                //构建span元素
                var aspan = $("<span />", {
                    "text": 'X',
                    "click": function () {
                        console.log($(this).parent().parent().prev().children("a").get(0));
                        $($(this).parent().parent().prev().children("a").get(0)).tab('show');
                        $(this).parent().parent().remove();
                        $("#" + options.id).prev().addClass("tab-pane fade in active");
                        $("#" + options.id).remove();
                    }
                });

                a.append(aspan);

                //合并li和a元素
                li.append(a);


                var ul = $("ul.nav-tabs");
                //合并ul和li元素
                ul.append(li);

                //添加完成显示当前li
                $(li).tab("show");

                //构建div内容元素
                var div = $("<div />", {
                    "id": options.id,
                    "class": "tab-pane fade in active",
                });

                var iframecontent="<iframe src=\""+options.url+"\" id=\"mainFrame\" name=\"mainFrame\" frameborder=\"0\" width=\"100%\"  height=\"520px\" frameBorder=\"0\"></iframe>";
                //兼容纯文本和html片段
                div.html(iframecontent);
                var container = $(".tab-content");
                container.append(div);
                //添加完成后显示div
                $(div).siblings().removeClass("active");
            }
        }


        /*
        * 标签页的名称
        * 标签页的id
        * 标签页内容的url地址
        * */
        function addtab(title,tabId,url) {

            console.log("main addtab");
            addTabs({
                "id": tabId,
                "title": title,
                "url": url
            });
        }

        function removeTabToDeptList(removeId) {

            console.log("removeTab");
            $("#" + removeId).remove();
            //删除标签页
            $("#"+removeId+"-li").remove();
            //$("#" + removeId).prev().addClass("tab-pane fade in active");
            //删除标签所分配的内容
            console.log("--------remove tab end");

            addtab('部门列表','deptlist_tab','${ctx}/deptlist.action');

        }

    </script>
</head>
<body>
<nav class="navbar navbar-inverse" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
                <img src="images/a.jpg" height="100%"/>
            </a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a class="icon-bar" href="#">管理系统</a>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a>欢迎您,${loginUser.loginname}</a>
                </li>
                <li><a href="${pageContext.servletContext.contextPath}/Loginout">安全退出</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<%--<div class="container-fluid">--%>

<head></head>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">
            <a data-toggle="collapse" href="#collapseOne" class="list-group-item">
                <!-- 小图标样式设置 --> <span class="glyphicon glyphicon-th-list"> </span>用户管理</a>
            <div id="collapseOne" class="panel-collapse collapse">
                <div style="padding-left: 15px">
                    <a href="#myTabContent" class="list-group-item" onclick="addtab('用户管理','usermanage','${pageContext.servletContext.contextPath}/controller/queryUserByPage');" > <span class="glyphicon glyphicon-align-left" aria-hidden="true"> </span>用户查询</a>
                    <a href="#myTabContent" class="list-group-item" onclick="addtab('添加用户','useradd','${pageContext.servletContext.contextPath}/controller/FindUserName')"> <span class="glyphicon glyphicon-camera" aria-hidden="true"> </span>添加用户</a>
                </div>
            </div>
            <a data-toggle="collapse" href="#collapsetwo" class="list-group-item">
                <!-- 小图标样式设置 --> <span class="glyphicon glyphicon-th-list"> </span>部门管理</a>
            <div id="collapsetwo" class="panel-collapse collapse">
                <div style="padding-left: 15px">
                    <a href="#myTabContent" class="list-group-item" onclick="addtab('部门查询','deptQuery','${pageContext.servletContext.contextPath}/controller/queryDeptByPage');" > <span class="glyphicon glyphicon-align-left" aria-hidden="true"> </span>部门查询</a>
                    <a href="#myTabContent" class="list-group-item" onclick="addtab('添加部门','deptadd','${pageContext.servletContext.contextPath}/controller/ForDept')"> <span class="glyphicon glyphicon-camera" aria-hidden="true"> </span>添加部门</a>
                </div>
            </div>
            <a data-toggle="collapse" href="#collapseThree" class="list-group-item">
                <!-- 小图标样式设置 --> <span class="glyphicon glyphicon-th-list"> </span>职位管理</a>
            <div id="collapseThree" class="panel-collapse collapse">
                <div style="padding-left: 15px">
                    <a href="#myTabContent" class="list-group-item" onclick="addtab('职位查询','jobQuery','${pageContext.servletContext.contextPath}/controller/queryJobByPage');" > <span class="glyphicon glyphicon-align-left" aria-hidden="true"> </span>职位查询</a>
                    <a href="#myTabContent" class="list-group-item" onclick="addtab('添加职位','jobadd','${pageContext.servletContext.contextPath}/controller/')"> <span class="glyphicon glyphicon-camera" aria-hidden="true"> </span>添加职位</a>
                </div>
            </div>
            <a data-toggle="collapse" href="#collapseFour" class="list-group-item">
                <!-- 小图标样式设置 --> <span class="glyphicon glyphicon-th-list"> </span>员工管理</a>
            <div id="collapseFour" class="panel-collapse collapse">
                <div style="padding-left: 15px">
                    <a href="#myTabContent" class="list-group-item" onclick="addtab('员工查询','employeeQuery','${pageContext.servletContext.contextPath}/controller/queryemployeeByPage');" > <span class="glyphicon glyphicon-align-left" aria-hidden="true"> </span>员工查询</a>
                    <a href="#myTabContent" class="list-group-item" onclick="addtab('添加员工','employeeadd','${pageContext.servletContext.contextPath}/controller/')"> <span class="glyphicon glyphicon-camera" aria-hidden="true"> </span>添加员工</a>
                </div>
            </div>
            <a data-toggle="collapse" href="#collapseFives" class="list-group-item">
                <!-- 小图标样式设置 --> <span class="glyphicon glyphicon-th-list"> </span>公告管理</a>
            <div id="collapseFives" class="panel-collapse collapse">
                <div style="padding-left: 15px">
                    <a href="#myTabContent" class="list-group-item" onclick="addtab('公告查询','noticeQuery','${pageContext.servletContext.contextPath}/controller/queryNoticeByPage');" > <span class="glyphicon glyphicon-align-left" aria-hidden="true"> </span>公告查询</a>
                    <a href="#myTabContent" class="list-group-item" onclick="addtab('添加公告','noticeadd','${pageContext.servletContext.contextPath}/controller/forNotice')"> <span class="glyphicon glyphicon-camera" aria-hidden="true"> </span>添加公告</a>
                </div>
            </div>
            <a data-toggle="collapse" href="#collapseSix" class="list-group-item">
                <!-- 小图标样式设置 --> <span class="glyphicon glyphicon-th-list"> </span>文档管理</a>
            <div id="collapseSix" class="panel-collapse collapse">
                <div style="padding-left: 15px">
                    <a href="#myTabContent" class="list-group-item" onclick="addtab('文档查询','DocumentQuery','${pageContext.servletContext.contextPath}/controller/queryDocumentByPage');" > <span class="glyphicon glyphicon-align-left" aria-hidden="true"> </span>文档查询</a>
                    <a href="#myTabContent" class="list-group-item" onclick="addtab('上传文件','Documentadd','${pageContext.servletContext.contextPath}/controller/UploadFileJsp')"> <span class="glyphicon glyphicon-camera" aria-hidden="true"> </span>上传文档</a>
                </div>
            </div>
        </div>
        <!--左边菜单栏-->
        <div class="col-sm-10">
            <ul id="myTab" class="nav nav-tabs">
            </ul>
            <div id="myTabContent" class="tab-content">
            </div>
        </div>
    </div>
<%--        <!--右边标签页及中央内容显示-->--%>
<%--        <div class="col-sm-10" id="myTabContent" >--%>
<%--            <ul id="myTab" class="nav nav-tabs">--%>
<%--            </ul>--%>

<%--&lt;%&ndash;            <%@ include file='/hrml_war_exploded/user.jsp'%>&ndash;%&gt;--%>
<%--            </div>--%>
</div>

<!-- 底部页脚部分 -->
<div class="footer">
    <p class="text-center">
        2021 &copy; gec.
    </p>
</div>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->

</body>
</html>