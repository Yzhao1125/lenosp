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
<#--<div class="lenos-search">
    <div class="select">
        设备名称：
        <div class="layui-inline">
            <input class="layui-input" height="20px" id="uname" autocomplete="off">
        </div>
        &lt;#&ndash; 邮箱：
         <div class="layui-inline">
             <input class="layui-input" height="20px" id="email" autocomplete="off">
         </div>&ndash;&gt;
        <button class="select-on layui-btn layui-btn-sm" data-type="select"><i class="layui-icon"></i>
        </button>
        <button class="layui-btn layui-btn-sm icon-position-button" id="refresh" style="float: right;"
                data-type="reload">
            <i class="layui-icon">ဂ</i>
        </button>
    </div>
<#--</div>&ndash;&gt;
<table id="deviceMList" class="layui-hide" lay-filter="deviceM"></table>-->
<#--<script type="text/html" id="MbarDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="check">实时状态查看</a>
    &lt;#&ndash;<@shiro.hasPermission name="device:select">&ndash;&gt;
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="pause">暂停</a>
    &lt;#&ndash;  </@shiro.hasPermission>&ndash;&gt;
    &lt;#&ndash;  <@shiro.hasPermission name="device:update">&ndash;&gt;
    <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="recovery">恢复</a>
    &lt;#&ndash;    </@shiro.hasPermission>&ndash;&gt;
    &lt;#&ndash;    <@shiro.hasPermission name="device:del">&ndash;&gt;
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="stop">停止</a>
    &lt;#&ndash;    </@shiro.hasPermission>&ndash;&gt;
</script>-->

<#--<div class="layui-form layui-form-pane" style="margin-left: 20px;">
    <div class="layui-form-item">
        <div class="layui-input-inline">
            <input type="control" id="control" name="control"  lay-verify="control"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
       <button  class="layui-btn layui-btn-normal" lay-filter="add" lay-submit="">
        提交
        </button>
    </div>
</div>-->

<div class="lenos-search">
    <div class="select">
        设备名称
        <div class="layui-inline">
            <input class="layui-input" height="20px" id="dname" autocomplete="off">
        </div>

        <button class="select-on layui-btn layui-btn-sm" data-type="select" style="background-color: deepskyblue"><i class="layui-icon"></i>
        </button>
        <button class="layui-btn layui-btn-sm icon-position-button" id="refresh" style="float: right; background-color: deepskyblue"
                data-type="reload">
            <i class="layui-icon">ဂ</i>
        </button>
    </div>
</div>

<table id="ConDeList" class="layui-hide" lay-filter="Device"></table>
<script type="text/html" id="tools">
    <a class="layui-btn layui-btn-xs" lay-event="checkstatus"  >实时状态查看</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="pause">暂停</a>
    <a class="layui-btn layui-btn-xs" lay-event="recover" onclick="">恢复</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="stop" onclick="">停止</a>
    <#-- <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="cease" onclick="stop()">测试2</a>
     <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="cease" onclick="stop()">测试3</a>-->
</script>
<script type="text/javascript" src="${re.contextPath}/plugin/device/device.js"></script>
<script>
    // 执行一个 table 实例
    layui.use('table', function () {
        var table = layui.table;
        var $ = layui.$;
        var datass = '';
        //方法级渲染
        table.render({
            id: 'ConDeList',
            elem: '#ConDeList'
            //url加时间戳解决ie浏览器缓存数据问题
            , url: 'showConDevice?timestamp='+ new Date().getTime()
            , cols: [[
                {checkbox: true, fixed: true, width: '5%'}
                // , {
                //     field: 'id',
                //     title: '序号',
                //     width: '7%',
                //     sort: true,
                //     style: 'background-color: #009688; color: #fff;'
                // }
                , {field: 'dname', title: '设备名称', width: '15%', sort: true}
                , {field: 'deviceid', title: '设备编号', width: '22%'}
                , {field: 'connect', title: '连接状态', width: '11%'}
                /*, {field: 'photo', title: '头像', width: '13%', template: '#switchTpl'}*/
                , {field: 'right', title: '操作', width: '40%', toolbar: "#tools"}
            ]]
            , page: true,
            height: 'full-83'
        });

        var $ = layui.$, active={
            select:function () {
                var dname = $('#dname').val();
 //               console.info(dname);
                table.reload('ConDeList', {
                    where: {
                        dname: dname,
                    }
                });
            },
            reload:function(){
                var dname = $('#dname').val();
                $('#dname').val('');
                /*   $('#email').val('');*/
                table.reload('ConDeList', {
                    where: {
                        dname: dname,
                        /*   email: null*/
                    }
                });
            },
        };

        table.on('tool(Device)',function (obj){
            var data = obj.data;
            var eid = data.deviceid;
            var dname = data.dname;
//            console.info(data);
//            console.info(eid);
            if(obj.event === 'checkstatus'){
                start(eid); //请求发送状态数据
                checkstatus( dname +'实时状态','/checkdevice?eid=' + eid, 700, 450 )
            }else if(obj.event === 'pause'){
                pause(eid)
            }else if (obj.event === 'recover'){
                recover(eid)
            }else if (obj.event === 'stop'){
                stop(eid)
            }

        })

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

    function checkstatus(title, url, w, h) {
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
            id: 'checkDstatus',
            type: 2,
            area: ['100%', '100%'],
            fix: false,
            maxmin: true,
            shadeClose: true,
            shade: 0.4,
            title: title,
            content: url,
            // btn:['关闭']
        });
    }

    function start(eid) {
        $.ajax({
            url: 'start',
            type: 'post',
            data: {'ss': eid},
            datatype: "json",
            async: false,
            success: function (res) {
//                console.info(res.msg);
            },
            error: function () {
                console.info("提交失败");
            }
        });
    }

    function stopsend(eid) {
        $.ajax({
            url: 'stop',
            type: 'post',
            data: {'ss': eid},
            datatype: "json",
            async: false,
            success: function (res) {
 //               console.info(res.msg);
                layer.msg(res);
            },
            error: function () {
                console.info("提交失败");
            }
        });
    }
    function recover(eid) {
        $.ajax({
            url: 'recover',
            type: 'post',
            data: {'ss': eid},
            datatype: "json",
            async: false,
            success: function (res) {
  //              console.info(res.msg);
                layer.msg(res);
            },
            error: function () {
                console.info("提交失败");
            }
        });
    }

    function pause(eid) {
        $.ajax({
            url: 'pause',
            type: 'post',
            data: {'ss': eid},
            datatype: "json",
            async: false,
            success: function (res) {
 //               console.info(res.msg);
                layer.msg(res);
            },
            error: function () {
                console.info("提交失败");
            }
        });
    }
    function stop(eid) {
        $.ajax({
            url: 'stopdevice',
            type: 'post',
            data: {'ss':eid},
            datatype: "json",
            async:false,
            success:function(res){
  //              console.info(res.msg);
                layer.msg(res);
            },
            error:function(){
                console.info("提交失败");
            }
        });
    }
</script>

</body>


</html>