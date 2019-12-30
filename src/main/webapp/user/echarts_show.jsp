<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html lang="en">
<head>


</head>
<body>
    <div id="main" style="width: 800px;height: 400px"></div>
    <script type="text/javascript">
        var myChart=echarts.init(document.getElementById('main'));
        var option={
            title:{
                text:'三周男女注册比例图'
            },
            tooltip:{},
            legend:{
                data:['男','女']
            },
            xAxis:{
                data:['第一周','第二周','第三周']
            },
            yAxis:{},
            series:[{
                name:['男'],
                type:'line'
            },{
                    name:['女'],
                    type:'line'
                }]
        };
        myChart.setOption(option);
        $.ajax({
            url:'${pageContext.request.contextPath}/echarts/getAll?sex='+'男',
            dataType:'json',
            type:'get',
            success:function (data) {
                myChart.setOption({
                    series:[{
                        name:['男'],
                        data:data
                    }]
                })
            }
        })
        $.ajax({
            url:'${pageContext.request.contextPath}/echarts/getAll?sex='+'女',
            dataType:'json',
            type:'get',
            success:function (data) {
                myChart.setOption({
                    series:[{
                        name:['女'],
                        data:data
                    }]
                })
            }
        })
        var goEasy = new GoEasy({
            host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BS-2bc9159bde2d44de840bbe2fc9c3254c", //替换为您的应用appkey
        });
        goEasy.subscribe({
            channel:'cmfz',
            onMessage:function (message) {
                var data=message.content;
                var d=JSON.parse(data);
                myChart.setOption({
                    series:[{
                        data:d
                    }]
                });
            }
        });
    </script>
</body>
</html>