<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2019/12/19
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<script>
    $(function(){
        $("#banner-table").jqGrid({
            url : '${pageContext.request.contextPath}/banner/selectAllBanner',
            datatype : "json",
            colNames : [ '编号', '名字', '封面', '描述', '状态','时间'],
            colModel : [
                {name : 'id',hidden:true},
                {name : 'name',editable:true},
                {name : 'cover',editable:true,edittype:'file',formatter:function (value,options,rows) {
                        return "<img style='width:100px;height:60px' src='${pageContext.request.contextPath}/banner/image/"+rows.cover+"'/>"
                    }},
                {name : 'description',editable:true},
                {name : 'status',editable:true,edittype:'select',editoptions:{value:"展示:展示;不展示:不展示"}},
                {name : 'create_date'}
            ],
            styleUI:"Bootstrap",
            height:200,
            autowidth:true,
            rowNum : 3,
            rowList : [ 3, 5, 10 ],
            pager : '#banner-pager',
            viewrecords : true,
            editurl : "${pageContext.request.contextPath}/banner/edit"
        }).navGrid("#banner-pager", {edit : true,add : true,del : true,search:false},{
            //控制修改的相关操作
            closeAfterEdit:close,
            beforeShowForm:function (frm) {
                frm.find("#cover").attr("disabled",true);
            }
        },{
            //控制添加的相关操作
            closeAfterAdd:close,
            afterSubmit:function (response) {
                var status=response.responseJSON.status;
                var id=response.responseJSON.message;
                if(status){
                    $.ajaxFileUpload({
                        type:"post",
                        url:"${pageContext.request.contextPath}/banner/upload",
                        data:{id:id},
                        fileElementId:"cover",
                        success:function () {
                            $("#banner-table").trigger("reloadGrid");
                        }
                    })
                }
                return "1231";
            }

        });
    })
</script>
<div class="page-header">
    <h2>查询所有的轮播图</h2>
</div>
<table id="banner-table"></table>
<div id="banner-pager" style="height: 80px"></div>


