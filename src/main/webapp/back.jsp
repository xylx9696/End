<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2019/12/12
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/End/statics/boot/css/bootstrap.min.css" rel="stylesheet">
    <%--引入bootStrap的css样式--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/boot/css/bootstrap.min.css">
    <%--引入jqgrid与bootstrap整合的css样式--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入jquery的js文件--%>
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-3.3.1.min.js"></script>
    <%--引入bootStrap的js文件--%>
    <script src="${pageContext.request.contextPath}/statics/boot/js/bootstrap.min.js"></script>
    <%--引入jqgrid的js文件--%>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"></script>
    <script src="${pageContext.request.contextPath}/user/echarts.min.js"></script>
    <title>驰名法洲后台管理系统</title>
</head>
<body>
<!--顶部导航-->
<div class="navbar navbar-inverse" style="background-color: #8c8c8c">
    <div class="container-fluid">
        <div class="navbar-header">
            <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#cc">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="javascript:0" class="navbar-brand" style="color: #0f0f0f">持明法州后台管理系统</a>
        </div>
        <div class="navbar-collapse collapse" id="cc" style="float: right">
            <%--<h4>欢迎&nbsp用户名:${sessionScope.login}</h4>--%>
            <a href="javascript:0" class="navbar-brand" style="color: #0f0f0f">欢迎&nbsp用户名:${sessionScope.login.username}</a>
            <a href="javascript:0" class="navbar-brand" style="color: #0f0f0f">安全退出</a>
        </div>
    </div>
</div>
<!--主体内容-->
<div class="container">
    <div class="row">
        <!--左侧导航-->
        <div class="col-md-3">
            <div class="panel-group" id="acc">
                <!--轮播图管理-->
                <div class="panel panel-primary">
                    <div class="panel-heading" style="background-color: #8c8c8c">
                        <a href="#pc1" class="panel-title" data-toggle="collapse" data-parent="#acc">轮播图管理</a>
                    </div>
                    <div class="panel-collapse collapse" id="pc1">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="javascript:$('#content').load('${pageContext.request.contextPath}/banner/banner_show.jsp')" class="list-group-item">所有轮播图</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!--专辑管理-->
                <div class="panel panel-primary">
                    <div class="panel-heading" style="background-color: #8c8c8c">
                        <a href="#pc2" class="panel-title" data-toggle="collapse" data-parent="#acc">专辑管理</a>
                    </div>
                    <div class="panel-collapse collapse" id="pc2">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="javascript:$('#content').load('${pageContext.request.contextPath}/album/album_show.jsp')" class="list-group-item">所有专辑</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!--文章管理-->
                <div class="panel panel-primary">
                    <div class="panel-heading" style="background-color: #8c8c8c">
                        <a href="#pc3" class="panel-title" data-toggle="collapse" data-parent="#acc">文章管理</a>
                    </div>
                    <div class="panel-collapse collapse" id="pc3">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="javascript:$('#content').load('${pageContext.request.contextPath}/article/article_show.jsp')" class="list-group-item">文章表</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!--用户管理-->
                <div class="panel panel-primary">
                    <div class="panel-heading" style="background-color: #8c8c8c">
                        <a href="#pc4" class="panel-title" data-toggle="collapse" data-parent="#acc">用户管理</a>
                    </div>
                    <div class="panel-collapse collapse" id="pc4">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="javascript:$('#content').load('${pageContext.request.contextPath}/user/user_show.jsp')" class="list-group-item">用户表</a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!--内容区域-->
        <div class="col-sm-9" id="content">
            <div class="jumbotron">
                <h2>欢迎光临驰名法洲后台管理系统</h2>
            </div>
            <img src="${pageContext.request.contextPath}/image/shouye.jpg" style="width: 100%">
        </div>
    </div>
    <%--&lt;%&ndash;页脚&ndash;%&gt;--%>
    <%--<div class="panel panel-footer text-center" style="position: absolute;bottom: 0px;width: 100%">--%>
        <%--驰名法洲后台管理系统                     @百知教育2019-12-18--%>
    <%--</div>--%>
</div>
<!--商品类别添加的模态框-->
<div class="modal fade" id="add_dept1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                添加类别
            </div>
            <div class="modal-body">
                <form action="">
                    <div class="form-group">
                        <label for="cname">类别名称:</label>
                        <input type="text" class="form-control" id="cname" name="cname">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" type="button" id="category1Add_btn">添加  <span class="glyphicon glyphicon-ok"></span></button>
            </div>
        </div>
    </div>
</div>
<!--信息添加模态框-->
<div class="modal fade" id="add_dept">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                supplier添加
            </div>
            <div class="modal-body">
                <form action="">
                    <div class="form-group">
                        <label for="id">ID:</label>
                        <input type="text" class="form-control" id="uid" name="uid" value="">
                    </div>
                    <div class="form-group">
                        <label for="name">名字:</label>
                        <input type="text" class="form-control" id="uname" name="uname" value="">
                    </div>
                    <div class="form-group">
                        <label for="leader">老板:</label>
                        <input type="text" class="form-control" id="uleader" name="uleader" value="">
                    </div>
                    <div class="form-group">
                        <label for="iphone">电话:</label>
                        <input type="text" class="form-control" id="uiphone" name="uiphone" value="">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" type="button" id="add" onclick="add()">添加 <span class="glyphicon glyphicon-ok"></span></button>
            </div>
        </div>
    </div>
</div>
<!--信息修改模态框-->
<div class="modal fade" id="update_dept">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                supplier修改
            </div>
            <div class="modal-body">
                <form action="">
                    <div class="form-group">
                        <label for="id">ID:</label>
                        <input type="text" class="form-control" id="id" name="id" value="" readonly>
                    </div>
                    <div class="form-group">
                        <label for="name">名字:</label>
                        <input type="text" class="form-control" id="name" name="name" value="">
                    </div>
                    <div class="form-group">
                        <label for="leader">老板:</label>
                        <input type="text" class="form-control" id="leader" name="leader" value="">
                    </div>
                    <div class="form-group">
                        <label for="iphone">电话:</label>
                        <input type="text" class="form-control" id="iphone" name="iphone" value="">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" type="button" id="supplierUpdate">修改 <span class="glyphicon glyphicon-ok"></span></button>
            </div>
        </div>
    </div>
</div>


</body>
</html>















