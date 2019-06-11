
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <#--<link rel="shortcut icon" href="<%=request.getContextPath()%>/plugin/x-admin/favicon.ico" type="image/x-icon" />-->
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${re.contextPath}/plugin/x-admin/css/font.css">
    <link rel="stylesheet" href="${re.contextPath}/plugin/x-admin/css/xadmin.css">

</head>
<body class="login-bg">

<div class="login">
    <div class="message">用户注册</div>
    <div id="darkbannerwrap"></div>
    <form class="layui-form" method="post" action="/register" >
        <input value="${user.username}"   name="username" placeholder="用户名" autocomplete="off"  type="text" lay-verify="username" class="layui-input" >
        <br>
        <input id="password"  name="password" lay-verify="password" placeholder="密码" autocomplete="off"  type="password" class="layui-input">
        <br>
        <input  name="checkPassword" lay-verify="checkPassword" placeholder="确认密码" autocomplete="off"  type="password" class="layui-input">
        <br>
        <#--<input name="contact" lay-verify="contact" placeholder="联系人" autocomplete="off"  type="contact" class="layui-input">
        <br>-->
        <input value="${user.email}" name="email" lay-verify="emailCheck" placeholder="邮箱" autocomplete="off"  type="email" class="layui-input">
        <br>
       <#-- <input name="tel" lay-verify="phoneCheck" placeholder="电话" autocomplete="off"  type="text" class="layui-input">
        <br>-->
   <#--     <input value="注册" lay-submit="" lay-filter="login" style="width:100%;" type="submit"  >-->
        <button  class="layui-btn layui-btn-normal" lay-filter="login" lay-submit="" style="width:100%" color ="green">
            注册
        </button>
    </form>
    <label class="layui-form-label" style="width:160px;padding: 0px 22px 0px 180px;font-style: italic;margin-top:15px"><a href="/login" style="font-size:13px">已有账号？直接登录</a></label>
</div>
<script>
   var layer;
    $(function () {
        layui.use(['form','layer'], function(){
            var form = layui.form;
            var layer = layui.form;
            form.verify({
                username:function(v){
                    if(v.trim()==''){
                        return "用户名不能为空";
                    }
                },password:function(v){
                    if(v.trim()==''){
                        return "密码不能为空";
                    }
                },checkPassword:function (v){
                    var password=document.getElementById('password');
                    if(v.trim()==''){
                        return "确认密码不能为空";
                    }else if(v.trim()!==password.value){
                        return "密码不一致";
                    }
                },email:function (v) {
                    if(v.trim()==''){
                        return "邮箱不能为空";
                    }
                 }
                //tel:function(v){
               //      if(v.trim()==''){
               //          return '电话不能为空';
               //      }
               //  },contact: function(v){
               //      if(v.trim()==''){
               //          return '联系人不能为空';
               //      }
               // }
            });
           /* form.on('submit(login)',function (data){
                $.ajax({
                    url:'goregister',
                    type: 'post',
                    data: data.field,
                    success:function (d) {
                        layer.msgbox ("注册成功");
                    },error:function (e) {
                        layer.msg("注册失败");
                    }
                })
            });*/
            //form.render();
        });
       <#--layer = layui.layer;-->
        <#--var msg='${message}';-->
        <#--if(msg.trim()!=""){-->
            <#--layer.msg(msg, {icon: 5,anim:6,offset: 't'});-->
        <#--}-->

    });
   var msg='${msg}';
   if(msg){
       layer.msg(msg, {icon: 5,anim:6,offset: 't'});
   }
</script>
<!-- 底部结束 -->
</body>
</html>
