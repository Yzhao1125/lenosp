<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>菜单管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js"></script>
</head>
<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;height:500px;overflow: auto;">
            <div class="layui-form-item">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                    <legend style="font-size:16px;">设备信息</legend>
                </fieldset>
            </div>
            <div style="margin-left:25%">
                <div class="layui-form-item">
                    <div class="layui-inline">
                    <label for="dname" class="layui-form-label">
                        <span class="x-red">*</span>设备名称
                    </label>
                    <div class="layui-input-inline">
                        <input value = "${pdevice.dname}" id="dname" name="dname"  lay-verify="dname"
                               autocomplete="off" class="layui-input">
                    </div>
                    </div>
                    <div class="layui-inline">
                        <label for="dname" class="layui-form-label">
                            <span class="x-red">*</span>设备编号
                        </label>
                        <div class="layui-input-inline">
                            <input value = "${pdevice.deviceid}" id="deviceid" name="deviceid"  lay-verify="deviceid"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                 </div>
                </div>

                <div class="layui-form-item">
                    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                        <legend style="font-size:16px;">设备用户信息</legend>
                    </fieldset>
                </div>
              <div style="margin-left:25%">
                <div class="layui-form-item">
                    <div class ="layui-inline">
                    <label for="username" class="layui-form-label">
                        <span class="x-red">*</span>用户名
                    </label>
                    <div class="layui-input-inline">
                        <input value ="${user.username}" id="username" name="username"  lay-verify="username"
                               autocomplete="off" class="layui-input">
                    </div>
                    </div>

                    <div class ="layui-inline">
                        <label for="email" class="layui-form-label">
                            <span class="x-red">*</span>邮箱
                        </label>
                        <div class="layui-input-inline">
                            <input value ="${user.email}" id="emai" name="email"  lay-verify="email"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
              </div>


                  <div class="layui-form-item">
                      <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                          <legend style="font-size:16px;">修改密码</legend>
                      </fieldset>
                  </div>
                  <div style="margin-left:25%">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                          <label for="devicePw" class="layui-form-label">
                            <span class="x-red">*</span>旧密码
                          </label>
                            <div class="layui-input-inline">
                            <input type="password" id="oldpw" name="oldpw" lay-verify="oldpw"
                               autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label for="devicePw" class="layui-form-label">
                                <span class="x-red">*</span>新密码
                            </label>
                            <div class="layui-input-inline">
                            <input type="password" id="newpw" name="newpw" lay-verify="newpw"
                                   autocomplete="off" class="layui-input">
                            </div>
                        </div>
                </div>
                <div style="height: 60px"></div>
            </div>
        </div>
        <#if !detail>
            <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
      position: fixed;bottom: 1px;margin-left:-20px;">
                <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                    <button  class="layui-btn layui-btn-normal" lay-filter="add" lay-submit>
                        确定
                    </button>
                    <button  class="layui-btn layui-btn-primary" id="close">
                        取消
                    </button>

                </div>
            </div>
        </#if>
    </form>
</div>
</body>
<script>
    layui.use(['form','layer','upload'], function(){
        $ = layui.jquery;
        var form = layui.form
            ,layer = layui.layer;


        //自定义验证规则
     /*   form.verify({
            username: function(value){
                if(value.trim()==""){
                    return "用户名不能为空";
                }
                if(value.match(/[\u4e00-\u9fa5]/)){
                    return "用户名不能为中文";
                }
                if(!/(.+){3,12}$/.test(value)){
                    return "用户名必须3到12位";
                }
                if(typeof(flag)=='undefined'){
                    return "用户名验证ing";
                }
                if(!flag){
                    return msg;
                }
            }
            ,email:function(value){
                if(value!=""){
                    if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(value)){
                        return "邮箱格式不正确";
                    }
                }
            }
        });*/

        $('#close').click(function(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        //监听提交
        form.on('submit(add)', function(data){
            var r=document.getElementsByName("role");
            var role=[];
            for(var i=0;i<r.length;i++){
                if(r[i].checked){
                    console.info(r[i].value);
                    role.push(r[i].value);
                }
            }
            data.field.role=role;
            layerAjax('updateUser', data.field, 'userList');
            return false;
        });
        form.render();
    });
</script>



</html>