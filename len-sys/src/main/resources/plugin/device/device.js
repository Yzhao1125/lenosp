var table;
var layer;
layui.use([ 'layer', 'table', 'element' ], function() {
    table = layui.table;
    layer = layui.layer;
    // 执行一个 table 实例
    table.render({
        elem : '#user',
        height:350,
        url : '/plugin/device/devicem.json',
        page :true, // 开启分页
        cols : [ [ // 表头
            {
                fixed : 'left',
                type : 'checkbox'
            }, {
                field : 'ordernumber',
                title : '序号',
                width : 100
            }, {
                field : 'name',
                title : '名称',
                width : 100,
            },
             {
                field : 'number',
                title : '编号',
                width : 100,
            },
            {
                field : 'state',
                title : '状态',
                width : 100,
            },

            {
                title : '操作',
                width : 300,
                align : 'center',
                toolbar : '#tools'
            } ] ]

    });

// 监听工具条
    table.on('tool(tools)', function(obj) { // 注：tool是工具条事件名，test是table原始容器的属性
        var data = obj.data // 获得当前行数据
            , layEvent = obj.event; // 获得 lay-event 对应的值
        if ('edit' == layEvent) {
            addTea(data.id)
        } else if ('del' == layEvent) {
            del(data.id);
        }
    });
});

function queryUser(){
    var keyword = $("#keyword").val();
    table.reload('user', {
        where : {
            keyword : keyword
        },
        page : {
            curr : 1
        }
    });
}

var index;
function addUser(id) {
    index = parent.layer.open({
        type : 2,
        title : "用户信息",
        area: ['550px', '400px'],
        content : '/user/edit?id=' + id
    });
    layer.full(index);
}

function del(id) {
    parent.layer.open({
        type : 1,
        content : '<div style="padding: 20px 80px;">确定删除记录?</div>',
        btn : [ '确定', '取消' ],
        yes : function(index, layero) {
            $.ajax({
                url : "/user/delete",
                data : {
                    "id" : id
                },
                dataType : "text",
                success : function(data) {
                    if(data==0){
                        layer.msg("删除成功！");
                        layer.close(index);
                        queryUser();
                    }else{
                        layer.msg("删除失败！");
                    }
                },
                error : function() {
                }
            });
        }
    });

}

/**
 * 获取选中数据
 */
function getDatas(){
    var checkStatus = table.checkStatus('user');
    var data = checkStatus.data;
    var id = "";
    for(var i=0;i<data.length;i++){
        id += data[i].id;
        if(i<data.length-1){
            id += ",";
        }
    }
    if(data.length != 0){
        del(id);
    }
}