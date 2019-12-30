// 自定义jQuery扩展函数
/**
 *
 * @param options  设计成一个js对象形式的参数，要求的属性有：
 *          colNames:['','','']
 *          url:'xxx'  // 加载远程数据属性，要求返回数据为json格式
 *
 */
$.fn.funTable = function (options) {
    // 动态的创建一个表格
    var $tr = $('<tr></tr>');
    var colNames = options.colNames;
    // 列的数量
    var colnum = colNames.length;

    for (var i = 0; i < colNames.length; i++) {
        var $th = $('<th>' + colNames[i] + '</th>');
        // 放到tr中
        $tr.append($th);
    }
    var $thead = $('<thead></thead>');
    $thead.append($tr);
    $(this).append($thead);
    $(this).attr('width', options.width+'px');
    $(this).attr('border', '1px');
    $(this).attr('cellpadding', '5px');

    //当前数据表格对象用变量保存
    var tt = $(this);
    // 根据指定url加载到的数据渲染表格
    $.ajax({
        url: options.url,
        dataType: 'json',
        success: function (d) {
            console.log(d.id,d.name)
            var tbTr = $('<tr></tr>')
            // 开始渲染表格
            for (var i = 0; i < colnum; i++) {
                var $td = $('<td>'+d.name+'</td>');
                tbTr.append($td);
            }
            tt.append($('<tbody></tbody>').append(tbTr));
        }
    });

}