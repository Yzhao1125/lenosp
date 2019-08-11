<#-- Created by IntelliJ IDEA.
 User: Administrator
 Date: 2017/12/6
 Time: 14:00
 To change this template use File | Settings | File Templates.
 设备管理-->
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
    <script type="text/javascript" src="${re.contextPath}/plugin/websocket/sockjs.min.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/websocket/stomp.min.js"></script>
<style>
    .d-right{
        position: absolute;
        right: 0;
        top: 0;
        height: 100%;
        width: 50%;
    }
    .right-body{
        margin: 20px 100px 0 100px;
    }
    .layui-timeline-title{
        font-size: 15px;
    }
</style>
</head>

<body>
<#--<div style="width: 50%;border-right: 1px solid gray">-->
<div class="lenos-search">
    <div class="select">
        设备名称
        <div class="layui-inline">
            <input class="layui-input" height="20px" id="dname" autocomplete="off">
        </div>

        <button class="select-on layui-btn layui-btn-sm" data-type="select" style="background-color:deepskyblue"><i class="layui-icon"></i>
        </button>
        <button class="layui-btn layui-btn-sm icon-position-button" id="refresh" style="float: right; background-color:deepskyblue"
                data-type="reload" >
            <i class="layui-icon">ဂ</i>
        </button>
    </div>
</div>
<div class="layui-col-md12" style="height:40px;margin-top:3px;">
    <div class="layui-btn-group">
   <#--     <@shiro.hasPermission name="device:select">-->
            <button class="layui-btn layui-btn-normal" data-type="add"  style="background-color: deepskyblue">
                <i class="layui-icon" >&#xe608;</i>新增
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
<#--</div>-->
<table id="deviceList" class="layui-hide" lay-filter="device"></table>
</div>
<#--<div class="d-right">-->
    <#--<div id="right-body" class="right-body">-->
    <#--<ul class="layui-timeline">-->
        <#--<li class="layui-timeline-item">-->
            <#--<i class="layui-icon layui-timeline-axis">&#xe63f;</i>-->
            <#--<div class="layui-timeline-content layui-text">-->
                <#--<div class="layui-timeline-title">-->
                <#--<span>设备名称：设备1</span>-->
                    <#--<span>状态：<font color="#7fff00">已连接</font></span>-->
            <#--</div>-->
            <#--</div>-->
        <#--</li>-->
        <#--<li class="layui-timeline-item">-->
            <#--<i class="layui-icon layui-timeline-axis">&#xe63f;</i>-->
            <#--<div class="layui-timeline-content layui-text">-->
                <#--<div class="layui-timeline-title">-->
                    <#--<span>设备名称：设备1</span>-->
                    <#--<span>状态：<font color="#7fff00">已连接</font></span>-->
                <#--</div>-->
            <#--</div>-->
        <#--</li>-->
        <#--<li class="layui-timeline-item">-->
            <#--<i class="layui-icon layui-timeline-axis">&#xe63f;</i>-->
            <#--<div class="layui-timeline-content layui-text">-->
                <#--<div class="layui-timeline-title">-->
                    <#--<span>设备名称：设备1</span>-->
                    <#--<span>状态：<font color="#7fff00">已连接</font></span>-->
                <#--</div>-->

            <#--</div>-->
        <#--</li>-->
        <#--<li class="layui-timeline-item">-->
            <#--<i class="layui-icon layui-timeline-axis">&#xe63f;</i>-->
            <#--<div class="layui-timeline-content layui-text">-->
                <#--<div class="layui-timeline-title">-->
                    <#--<span>设备名称：设备1</span>-->
                    <#--<span>状态：<font color="#7fff00">已连接</font></span>-->
                <#--</div>-->
            <#--</div>-->
        <#--</li>-->
    <#--</ul>-->
    <#--</div>-->

<#--</div>-->
<script type="text/html" id="barDemo">
    <#--<@shiro.hasPermission name="device:select">-->
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail" >查看</a>
  <#--  </@shiro.hasPermission>-->
  <#--  <@shiro.hasPermission name="device:update">-->
        <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="edit" style="background-color:deepskyblue">修改</a>
<#--    </@shiro.hasPermission>-->
<#--    <@shiro.hasPermission name="device:del">-->
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" >删除</a>
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
    };

    $(function(){
       if(typeof (WebSocket)== "undefined" ){
           console.log("您的浏览器不支持WebSocket");
       } else{
           console.log("您的浏览器支持WebSocket");
           socket = new WebSocket("ws:localhost:8092/websocket");
           socket.onopen = function () {
               console.log("websocket已打开");
           };
           socket.onmessage=function(res){

           }
       }
    });

    var tableIns;
    var curdata = new Array();
    var deviceids = [];
    layui.use(['table','laypage','element'], function () {
        var table = layui.table
            ,laypage = layui.laypage
            ,$ = layui.$
            ,element = layui.element;
        var tabledata='';
        //方法级渲染
          tableIns= table.render({
            id: 'deviceList',
            elem: '#deviceList'
            , url: '/showDeviceList?timestamp=' + new Date().getTime()
            , cols: [[
                {checkbox: true, fixed: true, width: '5%'}
                // , {
                //     field: 'id',
                //     title: '序号',
                //     width: '7%',
                //     sort: true,
                //     style: 'background-color: #009688; color: #fff;'
                // }
                , {field: 'dname', title: '设备名称', width: '20%', sort: true}
                , {field: 'deviceid', title: '设备编号', width: '22%'}
                , {field: 'connect', title: '状态', width: '20%'}
                /*, {field: 'photo', title: '头像', width: '13%', template: '#switchTpl'}*/
                , {field: 'right', title: '操作', width: '26%', toolbar: "#barDemo"}
            ]]
            , page: true,
            height: 'full-83',
              done: function(res, curr, count){
               // console.log(res);
                curdata = res.data; //获取当前页的数据
                if(deviceids!=null){
                    deviceids =[];
                }
                for (i=0;i<curdata.length;i++){
                    var eid = curdata[i].deviceid;
                 //   console.log(eid);
                    deviceids.push(eid);
                }

                //console.log ("sss"+ deviceids);
                //console.log(curdata);



              }
       });

        // curdata = tableIns.cache['deviceList'];
        //curdata = tableIns.getTable().cache.table;


        var $ = layui.$, active = {  //根据检索条件，刷新设备表格
            select: function () {
                var dname = $('#dname').val();
                console.info(dname);
                table.reload('deviceList', {
                    where: {
                        dname: dname,
                    }
                });
            },
            reload: function(){
                var dname = $('#dname').val();
                table.reload('deviceList', {
                    where: {
                        dname: dname,

                    }
                });
            },

            add: function () {  //新增设备
                add('添加设备', 'showAddDevice', 700, 450);
            }


        };
        // $(".layui-laypage-btn")[0].click();

        //监听表格复选框选择
        table.on('checkbox(device)', function (obj) {
            console.log(obj)
        });
        //监听工具条
        table.on('tool(device)', function (obj) {
            var data = obj.data;
            var eid = data.deviceid;
         //   console.log("ssssssssssss"+eid);
            if (obj.event === 'detail') {
                detail('查看设备', 'checkDevice?eid=' + eid, 700, 450);
            } else if (obj.event === 'del') {
                layer.confirm('确定删除设备[<label style="color: #00AA91;">' + data.dname + '</label>]?', {
                    btn: ['确定', '取消']
                }, function (index) {
                    toolDelByFlag2(eid,'deviceList',true);
                    layer.close(index);
                    console.log(eid);
                }, function () {
                    layer.close();
                });
            } else if (obj.event === 'edit') {
                update('修改设备信息', 'updateDevice?eid=' + eid, 700, 450);
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



       //  setInterval(function(){
       //      var data=[];
       //      for(var i=0;i<10;i++){
       //          data.push({
       //              code:i,
       //              name:'设备'+i,
       //              status:i%2===0?0:1
       //          });
       //      }
       //      var htm= ``;
       //          for (var item in data){
       //          console.log(data[item].name);
       //          var status=data[item].status===0?'已连接':'未连接';
       //          var color=data[item].status===0?'green':'red';
       //          htm+='<li class="layui-timeline-item">'+
       //      '<i class="layui-icon layui-timeline-axis">&#xe63f;</i>'+
       //      '<div class="layui-timeline-content layui-text">'+
       //          '<div class="layui-timeline-title">'+
       //          '<span>'+data[item].name+'</span>'+
       //              '<span>状态：<font>'+status+'</font></span>'+
       //              '</div>'+
       //      '</div>'+
       //  '</li>';
       //      }
       //      console.log(htm);
       //      var ul=$('.layui-timeline');
       //      ul.html(htm);
       //  }, 3000);
        setInterval(func, 10000);
    });



  function func() {
          var cr = tableIns.config.page.curr;
          var connect = $('#connect').val();
          var data1 = [];

      $.ajax({
          url: "/getDeviceConnect",
          type: "post",
          data: {"deviceids": deviceids},
          dataType: "json",
          cache: false,
          async: false,
          success: function(data) {
             // console.log(data);
              data1 = data;
              var trArr = [];
              var connect =[];
              for (var j = 0; j < data1.length; j++) {
                  var data2 = data1[j];
                  connect.push(data2.connect);
                  console.log("后台数据：");
                  console.log(data2);
              }

              //console.log(tableId);
              var trArr = $(".layui-table-body.layui-table-main tr"); //行数据
            //  console.log(trArr);
              for (var i = 0; i < trArr.length; i++) {
                  $(trArr[i]).find("td").each(function(){
                      if ($(this).attr("data-field") == "connect"){
                        //  console.log("前端数据");
                        //  console.log($(this).text());

                          $(this).children(":first").text(connect[i]);

                        //  console.log("修改后的数据");
                         // console.log($(this).text());
                      }
                  })

              }

          }
          })
      }

    //查看设备信息
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
            id: 'device-detail',
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



    function toolDelByFlag2(eid,list, flag) {
        var data={deviceid:eid};
        if(flag!=null){
            data.flag=flag;
        }
        $.ajax({
            url:'/deldevice?eid='+ eid,
            type:'post',
            data:data,
            success:function(d){
                if(d.flag){
                    window.top.layer.msg(d.msg,{icon:6,offset: 'rb',area:['120px','80px'],anim:2});
                    layui.table.reload(list);

                }else{
                    window.top.layer.msg(d.msg,{icon:5,offset: 'rb',area:['120px','80px'],anim:2});

                }
            },error:function(){
                alert('error');
            }
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
