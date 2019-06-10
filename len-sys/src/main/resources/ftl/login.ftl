
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台登录</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <#--<link rel="shortcut icon" href="<%=request.getContextPath()%>/plugin/x-admin/favicon.ico" type="image/x-icon" />-->
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${re.contextPath}/plugin/x-admin/css/font.css">
    <link rel="stylesheet" href="${re.contextPath}/plugin/x-admin/css/xadmin.css">
    <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>

</head>
<body class="login-bg">

<div class="login">
    <div class="message">3D打印物联平台</div>
    <div id="darkbannerwrap"></div>

    <form method="post" action="/login" class="layui-form" >
        <input name="uname" placeholder="用户名" autocomplete="off"  type="text" lay-verify="uname" class="layui-input" >
        <hr class="hr15">
        <input name="pwd" lay-verify="pwd" placeholder="密码" autocomplete="off"  type="pwd" class="layui-input">
        <hr class="hr15">
        <div  class="layui-inline">
            <label class="layui-form-label" style="width:40px;padding: 9px 0px;">验证码:</label>
               <div class="layui-input-inline">
                     <input type="text" name="code" style="width:150px;height:35px;" autocomplete="off" lay-verify="code"   class="layui-input">
              </div>
            <div class="layui-input-inline">
                <img src="" id="code">
            </div>

        </div>
        <#--<div>-->
        <#--<label class="layui-form-label" style="width:40px;padding: 9px 0px;">记住我</label>  由于不好验证 目前去掉-->
            <#--<input type="checkbox"   name="rememberMe" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF">-->
        <#--</div>-->
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20" >
    </form>

    <label class="layui-form-label" style="width:160px;padding: 0px 22px 0px 180px;font-style:italic;margin-top:15px;font-size:13px"><a href="/register">还没有账号？注册账号</a></label>
</div>


<script>
  var layer;
  $(function  () {
    layui.use(['form','layer'], function(){
      var form = layui.form;
      form.verify({
        uname:function(v){
          if(v.trim()==''){
            return "用户名不能为空";
          }
        }
        ,pwd:function(v){
          if(v.trim()==''){
            return "密码不能为空";
          }
        },code:function(v){
              if(v.trim()==''){
                  return '验证码不能为空';
              }
          }
      });
      form.render();
    });
    layer = layui.layer;
    var msg='${message}';
    if(msg.trim()!=""){
        layer.msg(msg, {icon: 5,anim:6,offset: 't'});
    }
      $("#code").click(function(){
          var url = "/getCode?"+new Date().getTime();
          this.src = url;
      }).click().show();
    $('#code').on('mouseover',function(){
        layer.tips('点击刷新验证码', this,{time:1000});
    });
  })

  if (window != top)
    top.location.href = location.href;
</script>


<!-- 底部结束 -->
</body>
</html>
