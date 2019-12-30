<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<script>
    $(function () {
        $("#album-table").jqGrid(
            {
                url: '${pageContext.request.contextPath}/album/selectAllAlbum',
                editurl:'${pageContext.request.contextPath}/album/edit',
                datatype: "json",
                height: 190,
                colNames: ['编号', '标题', '封面', '作者', '播音', '分数', '集数','简介','时间'],
                colModel: [
                    {name: 'id',hidden:true},
                    {name: 'title',editable:true},
                    {name: 'cover',editable:true,edittype:'file',formatter:function (value,options,rows) {
                            return "<img style='width:100px;height:60px' src='${pageContext.request.contextPath}/album/image/"+rows.cover+"'/>";
                        }},
                    {name: 'author',editable:true},
                    {name: 'beam',editable:true},
                    {name: 'score',editable:true},
                    {name: 'count',editable:true},
                    {name: 'intro',editable:true},
                    {name: 'createDate'}
                ],
                styleUI:"Bootstrap",
                height:300,
                autowidth:true,
                rowNum: 2,
                rowList: [2, 5, 10, 20],
                pager: '#album-pager',
                viewrecords: true,
                multiselect: false,
                subGrid: true,
                caption: "展示专辑",
                subGridRowExpanded : function(subgrid_id, row_id) {//1.当前父容器的id   2.当前专辑的id
                    var subgrid_table_id = subgrid_id + "_t";
                    var pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html(
                        "<table id='" + subgrid_table_id + "' class='scroll'></table>" +
                        "<div id='" + pager_id + "' class='scroll'></div>");
                    $("#" + subgrid_table_id).jqGrid(
                        {
                            url : '${pageContext.request.contextPath}/chapter/selectAllChapter?id='+row_id,
                            editurl:'${pageContext.request.contextPath}/chapter/edit?aid='+row_id,
                            datatype : "json",
                            colNames : [ '编号', '标题', '大小', '时长','文件名','创建时间','在线播放' ],
                            colModel : [
                                {name : "id",hidden:true},
                                {name : "title",editable:true},
                                {name : "size"},
                                {name : "duration"},
                                {name : "cover",editable:true,edittype:'file'},
                                {name : "createDate"},
                                {name : "aaa",width:300,formatter:function (value,options,rows) {
                                        return "<audio controls loop>\n" +
                                            "  <source src='${pageContext.request.contextPath}/album/mv/"+rows.cover+"' type=\"audio/ogg\">\n" +
                                            "</audio>"
                                    }},
                            ],
                            styleUI:"Bootstrap",
                            autowidth:true,
                            rowNum : 2,
                            pager : pager_id,
                            height : '100%'
                        }).jqGrid('navGrid',
                        "#" + pager_id, {
                            edit : true,
                            add : true,
                            del : false
                        },{
                            //控制章节修改
                            closeAfterEdit:close,
                            beforeShowForm:function (frm) {
                                frm.find("#cover").attr("disabled",true);
                            }
                        },{
                            //控制章节的添加
                            closeAfterAdd:close,
                            afterSubmit:function (response) {
                                var status=response.responseJSON.status;
                                var id=response.responseJSON.message;
                                console.log(id)
                                if(status){
                                    $.ajaxFileUpload({
                                        type:"post",
                                        url:"${pageContext.request.contextPath}/chapter/upload",
                                        data:{id:id},
                                        fileElementId:"cover",
                                        success:function () {
                                            $("#album-table").trigger("reloadGrid");
                                        }
                                    })
                                }
                                return "1231";
                            }
                        });
                },
    }).navGrid("#album-pager", {edit : true,add : true,del : true,search:false},{
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
                        url:"${pageContext.request.contextPath}/album/upload",
                        data:{id:id},
                        fileElementId:"cover",
                        success:function () {
                            $("#album-table").trigger("reloadGrid");
                        }
                    })
                }
                return "1231";
            }

        });
    })
</script>
<div class="page-header">
    <h2>展示专辑</h2>
</div>
<table id="album-table"></table>
<div id="album-pager" style="height: 80px"></div>