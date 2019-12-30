<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#user-table").jqGrid({
            url : '${pageContext.request.contextPath}/user/selectAllUser',
            datatype : "json",
            colNames : [ '编号', '用户名', '法号','头像', '省份','城市','签名','性别','电话','状态','时间'],
            colModel : [
                {name : 'id',hidden:true},
                {name : 'username',editable:true},
                {name : 'dharma',editable:true},
                {name : 'photo',editable:true,edittype:'file',formatter:function (value,options,rows) {
                        return "<img style='width:100px;height:60px' src='${pageContext.request.contextPath}/user/"+rows.photo+"'/>"
                    }},
                {name : 'province',editable:true},
                {name : 'city'},
                {name : 'sign'},
                {name : 'sex'},
                {name : 'phone'},
                {name : 'status'},
                {name : 'createDate'}
            ],
            styleUI:"Bootstrap",
            height:300,
            autowidth:true,
            rowNum : 2,
            rowList : [ 2, 5, 10 ],
            pager : '#article-pager',
            viewrecords : true,
            caption : "展示文章数据",
            editurl : "${pageContext.request.contextPath}/article/edit"
        }).navGrid("#article-pager", {edit : false,add : false,del : true,search:false})
    })
    function UserOut() {
        window.location.href="${pageContext.request.contextPath}/user/userOut"
    }
</script>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">展示用户</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" onclick="UserOut()" data-toggle="tab">用户数据导出</a></li>
        <li role="presentation"><a href="javascript:$('#content').load('${pageContext.request.contextPath}/user/echarts_show.jsp')">用户数据图</a></li>
    </ul>
</div>
<table id="user-table"></table>
<div id="user-pager" style="height: 80px"></div>