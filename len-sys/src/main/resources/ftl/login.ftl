
<!doctype html>
<html xmlns="http://www.w3.org/1999/html">
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

<body background="/plugin//build/images/beijin.png"
      style=" background-repeat:no-repeat ;

background-size:100% 100%;

background-attachment: fixed;">

<ul>
    <img src="/plugin//build/images/logo.jpg" alt="404" width="500" height="100"  style="float: left;">
</ul>
<div class="login" style="position: absolute;right: 150px;">
    <div class="message" style="background-color:pink; color:black">用户登录</div>
    <div id="darkbannerwrap"></div>

    <form method="post" action="/login" class="layui-form" >
        <input value="${user.username}" name="uname" placeholder="用户名" autocomplete="off"  type="text" lay-verify="uname" class="layui-input" >
        <hr class="hr15">
        <input name="pwd" type="password" lay-verify="pwd" placeholder="密码" autocomplete="off"  class="layui-input">
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
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%; background-color:pink; color:black" type="submit"  >
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
 <div class="layui-footer"  style="height: 40px;width: 100%;position: absolute;top:95%;left: 3%;">
     <!-- 底部固定区域 -->
     2019 &copy;
     <div style="color: white"><a target="_blank" href="http://www.joye3d.com/" style="color: white;">湖北嘉一三维高科股份有限公司</a> <#--技术交流请联系本人-154040976@qq.com-->
     &emsp;&emsp;&emsp;全国热线：400-027-1133
     &emsp;&emsp;&emsp;公司总机:027-88991133
     &emsp;&emsp;&emsp;E-mail：jygk@joye3d.com
     &emsp;&emsp;&emsp;总部地址：湖北省武汉市武昌区徐东大街338号纺织大厦
     &emsp;&emsp;&emsp;生产基地：湖北省赤壁市赤马港嘉一科技园</div>
 </div>
