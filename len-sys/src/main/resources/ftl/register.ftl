
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
<body background="/plugin//build/images/beijin.png "
      style=" background-repeat:no-repeat ;

background-size:100% 100%;

background-attachment: fixed;">
<ul>
    <img src="/plugin//build/images/logo.jpg" alt="404" width="500" height="100"  style="float: left;">
</ul>


</div>

<div class="login" style="margin-top:50px; height=450px; position:absolute; right: 150px;"><#--<div class="login" style="position: absolute;right: 150px;">-->
    <div class="message" style="background-color:pink; color:black">用户注册</div>
    <div id="darkbannerwrap"></div>
    <form class="layui-form" method="post" action="/register" >
        <input value="${user.username}"   name="username" placeholder="用户名" autocomplete="off"  type="text" lay-verify="username" class="layui-input" >
        <br>
        <input id="password"  name="password" lay-verify="password" placeholder="密码" autocomplete="off"  type="password" class="layui-input">
        <br>
        <input  name="checkPassword" lay-verify="checkPassword" placeholder="确认密码" autocomplete="off"  type="password" class="layui-input">
        <br>
        <input name="schoolname" lay-verify="contact" placeholder="学校名称" autocomplete="off"  type="text" class="layui-input">
        <br>
        <input name="realName" lay-verify="contact" placeholder="联系人姓名" autocomplete="off"  type="text" class="layui-input">
        <br>
        <input name="depart" lay-verify="phoneCheck" placeholder="学校职位/科目" autocomplete="off"  type="text" class="layui-input">
        <br>
        <input name="Tel" lay-verify="phoneCheck" placeholder="联系电话" autocomplete="off"  type="text" class="layui-input">
        <br>
        <input value="${user.email}" name="email" lay-verify="emailCheck" placeholder="邮箱" autocomplete="off"  type="email" class="layui-input">
        <br>
   <#--     <input value="注册" lay-submit="" lay-filter="login" style="width:100%;" type="submit"  >-->
        <button  class="layui-btn " type="submit"  lay-filter="login" lay-submit="" style="width:100%; background-color:pink; color:black">
            注册
        </button>
    </form>
    <label class="layui-form-label" style="width:180px;padding: 0px 22px 0px 100px;font-style: italic;margin-top:15px"><a href="/login" style="font-size:13px">已有账号？直接登录</a></label>
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
                        return "姓名不能为空";
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
                        return "请输入邮箱";
                    }
                 },Tel:function(v){
                    if(v.trim()==''){
                        return '请输入联系电话';
                    }
                },schoolname: function(v){
                    if(v.trim()==''){
                        return '请输入学校名称';
                    }
               },realName: function(v){
                    if(v.trim()==''){
                        return '请输入学校名称';
                    }
                },depart: function (v) {
                    return '请输入学校职位/科目'

                }
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
<div class="layui-footer"  style="height: 40px;width: 100%;position: absolute;top:95%;left:3%;">
    <!-- 底部固定区域 -->
    2019 &copy;
    <div style="color: white"><a target="_blank" href="http://www.joye3d.com/" style="color: white;">湖北嘉一三维高科股份有限公司</a> <#--技术交流请联系本人-154040976@qq.com-->
        &emsp;&emsp;&emsp;全国热线：400-027-1133
        &emsp;&emsp;&emsp;公司总机:027-88991133
        &emsp;&emsp;&emsp;E-mail：jygk@joye3d.com
        &emsp;&emsp;&emsp;总部地址：湖北省武汉市武昌区徐东大街338号纺织大厦
        &emsp;&emsp;&emsp;生产基地：湖北省赤壁市赤马港嘉一科技园</div>
</div>
</html>
