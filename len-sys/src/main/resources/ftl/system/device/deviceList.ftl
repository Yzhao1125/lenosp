<#-- Created by IntelliJ IDEA.
 User: Administrator
 Date: 2017/12/6
 Time: 14:00
 To change this template use File | Settings | File Templates.
 用户管理-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>设备管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="${re.contextPath}/plugin/lenos/main.css">
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js"
            charset="utf-8"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js"></script>

</head>

<body>
<div class="lenos-search">
    <div class="select">
        设备名称：
        <div class="layui-inline">
            <input class="layui-input" height="20px" id="uname" autocomplete="off">
        </div>
       <#-- 邮箱：
        <div class="layui-inline">
            <input class="layui-input" height="20px" id="email" autocomplete="off">
        </div>-->
        <button class="select-on layui-btn layui-btn-sm" data-type="select"><i class="layui-icon"></i>
        </button>
        <button class="layui-btn layui-btn-sm icon-position-button" id="refresh" style="float: right;"
                data-type="reload">
            <i class="layui-icon">ဂ</i>
        </button>
    </div>

</div>
<div class="layui-col-md12" style="height:40px;margin-top:3px;">
    <div class="layui-btn-group">
   <#--     <@shiro.hasPermission name="device:select">-->
            <button class="layui-btn layui-btn-normal" data-type="add">
                <i class="layui-icon">&#xe608;</i>新增
            </button>
       <#-- </@shiro.hasPermission>-->
     <#--   <@shiro.hasPermission name="device:select">-->
           <#-- <button class="layui-btn layui-btn-normal" data-type="update">
                <i class="layui-icon">&#xe642;</i>编辑
            </button>-->
      <#--  </@shiro.hasPermission>-->
       <#-- <@shiro.hasPermission name="device:del">-->
           <#-- <button class="layui-btn layui-btn-normal" data-type="detail">
                <i class="layui-icon">&#xe605;</i>查看
            </button>-->
    <#--    </@shiro.hasPermission>-->
        <#--<@shiro.hasPermission name="device:repass">
            <button class="layui-btn layui-btn-normal" data-type="changePwd">
                <i class="layui-icon">&#xe605;</i>修改密码
            </button>
        </@shiro.hasPermission>-->
    </div>
</div>
<table id="deviceList" class="layui-hide" lay-filter="device"></table>
<script type="text/html" id="barDemo">
    <#--<@shiro.hasPermission name="device:select">-->
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
  <#--  </@shiro.hasPermission>-->
  <#--  <@shiro.hasPermission name="device:update">-->
        <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="edit">编辑</a>
<#--    </@shiro.hasPermission>-->
<#--    <@shiro.hasPermission name="device:del">-->
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
<#--    </@shiro.hasPermission>-->
</script>
<#--<script type="text/html" id="switchTpl">
    <input type="checkbox" name="sex" lay-skin="switch" lay-text="女|男" lay-filter="sexDemo">
</script>-->
<script>
    document.onkeydown = function (e) { // 回车提交查询表单
        var theEvent = window.event || e;
        var code = theEvent.keyCode || theEvent.which;
        if (code == 13) {
            $(".select .select-on").click();
        }
    }
    layui.use('table', function () {
        var table = layui.table;
        //方法级渲染
        table.render({
            id: 'deviceList',
            elem: '#deviceList'
            , url: '/showDeviceList'
            , cols: [[
                {checkbox: true, fixed: true, width: '5%'}
                , {
                    field: 'id',
                    title: '序号',
                    width: '7%',
                    sort: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'dname', title: '设备名称', width: '20%', sort: true}
                , {field: 'deviceid', title: '设备编号', width: '22%'}
                , {field: 'connect', title: '状态', width: '20%'}
                /*, {field: 'photo', title: '头像', width: '13%', template: '#switchTpl'}*/
                , {field: 'right', title: '操作', width: '26%', toolbar: "#barDemo"}
            ]]
            , page: true,
            height: 'full-83'
        });

        var $ = layui.$, active = {  //根据检索条件，刷新设备表格
            select: function () {
                var dname = $('#dname').val();
               /* var email = $('#email').val();*/
                console.info(dname);
                table.reload('deviceList', {
                    where: {
                        dname: dname,
                      /*  email: email*/
                    }
                });
            },
            reload:function(){
                $('#dname').val('');
             /*   $('#email').val('');*/
                table.reload('deviceList', {
                    where: {
                        dname: null,
                     /*   email: null*/
                    }
                });
            },
            add: function () {  //新增设备
                add('添加设备', 'showAddDevice', 700, 450);
            },
            update: function () {
                var checkStatus = table.checkStatus('deviceList')
                    , data = checkStatus.data;
                if (data.length != 1) {
                    layer.msg('请选择一行编辑,已选['+data.length+']行', {icon: 5});
                    return false;
                }
                update('编辑设备', 'updateDevice?id=' + data[0].id, 700, 450);
            },
            detail: function () {
                var checkStatus = table.checkStatus('deviceList')
                    , data = checkStatus.data;
                if (data.length != 1) {
                    layer.msg('请选择一行查看,已选['+data.length+']行', {icon: 5});
                    return false;
                }
                detail('查看设备信息', 'updateDevice?id=' + data[0].id, 700, 450);
            },
           /* changePwd:function(){
                var checkStatus = table.checkStatus('deviceList')
                    , data = checkStatus.data;
                if (data.length != 1) {
                    layer.msg('请选择一个用户,已选['+data.length+']行', {icon: 5});
                    return false;
                }
                rePwd('修改密码','goRePass?id='+data[0].id,500,350);
            }*/
        };

        //监听表格复选框选择
        table.on('checkbox(device)', function (obj) {
            console.log(obj)
        });
        //监听工具条
        table.on('tool(device)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                detail('编辑设备', 'updateDevice?id=' + data.id, 700, 450);
            } else if (obj.event === 'del') {
                layer.confirm('确定删除设备[<label style="color: #00AA91;">' + data.dname + '</label>]?', {
                    btn: ['逻辑删除', '物理删除']
                }, function () {
                    toolDelByFlag(data.id,'deviceList',true);
                }, function () {
                    toolDelByFlag(data.id,'deviceList',false);
                });
            } else if (obj.event === 'edit') {
                update('编辑设备', 'updateDevice?id=' + data.id, 700, 450);
            }
        });

        $('.layui-col-md12 .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('.select .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
  /*  function rePwd(title,url,w,h){
        if (title == null || title == '') {
            title = false;
        };
        if (url == null || url == '') {
            url = "404.html";
        };
        if (w == null || w == '') {
            w = ($(window).width() * 0.9);
        };
        if (h == null || h == '') {
            h = ($(window).height() - 50);
        };
        layer.open({
            id: 'user-rePwd',
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: true,
            shade: 0.4,
            title: title,
            content: url,
        });
    }*/
    function detail(title, url, w, h) {
        if (title == null || title == '') {
            title = false;
        };
        if (url == null || url == '') {
            url = "error/404";
        };
        if (w == null || w == '') {
            w = ($(window).width() * 0.9);
        };
        if (h == null || h == '') {
            h = ($(window).height() - 50);
        };
        layer.open({
            id: 'user-detail',
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: true,
            shade: 0.4,
            title: title,
            content: url + '&detail=true',
            // btn:['关闭']
        });
    }
    /**
     * 更新设备
     */
    function update(title, url, w, h) {
        if (title == null || title == '') {
            title = false;
        }
        if (url == null || url == '') {
            url = "404.html";
        }
        if (w == null || w == '') {
            w = ($(window).width() * 0.9);
        }
        if (h == null || h == '') {
            h = ($(window).height() - 50);
        }
        layer.open({
            id: 'device-update',
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: false,
            shade: 0.4,
            title: title,
            content: url + '&detail=false'
        });
    }

    /*弹出层*/
    /*
     参数解释：
     title   标题
     url     请求的url
     id      需要操作的数据id
     w       弹出层宽度（缺省调默认值）
     h       弹出层高度（缺省调默认值）
     */
    function add(title, url, w, h) {
        if (title == null || title == '') {
            title = false;
        }
        ;
        if (url == null || url == '') {
            url = "404.html";
        }
        ;
        if (w == null || w == '') {
            w = ($(window).width() * 0.9);
        }
        ;
        if (h == null || h == '') {
            h = ($(window).height() - 50);
        }
        ;
        layer.open({
            id: 'device-add',
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: false,
            shade: 0.4,
            title: title,
            content: url
        });
    }
</script>
</body>

</html>
