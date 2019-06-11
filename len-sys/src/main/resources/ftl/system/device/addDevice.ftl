<#--Created by IntelliJ IDEA.
User: Administrator
Date: 2017/12/27
Time: 12:40
To change this template use File | Settings | File Templates.-->

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
            <#--<div class="layui-form-item">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                    <legend style="font-size:16px;">基础信息</legend>
                </fieldset>
            </div>-->
            <div style="margin-left:25%">
                <div class="layui-form-item">
                    <label for="dname" class="layui-form-label">
                        <span class="x-red">*</span>设备名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="dname" id="dname" name="dname"  lay-verify="dname"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label for="devicePw" class="layui-form-label">
                        <span class="x-red">*</span>设备密码
                    </label>
                    <div class="layui-input-inline">
                        <input type="password" id="devicePw" name="devicePw"  lay-verify="devicePw"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label for="devicePw" class="layui-form-label">
                        <span class="x-red">*</span>确认密码
                    </label>
                    <div class="layui-input-inline">
                        <input type="password" id="devicePwcheck" name="devicePwcheck"  lay-verify="devicePwcheck"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div style="height: 60px"></div>
            </div>
        </div>
        <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
            <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                <button  class="layui-btn layui-btn-normal" lay-filter="add" lay-submit="">
                    增加
                </button>
                <button  class="layui-btn layui-btn-primary" id="close">
                    取消
                </button>
            </div>
        </div>
    </form>
</div>
<script>
    layui.use(['form','layer'], function(){
        $ = layui.jquery;
        var form = layui.form
            ,layer = layui.layer;
       <#--layui.tree({-->
            <#--elem:'#tree',-->
            <#--nodes:${menus}-->
            <#--,click: function(node){-->
                <#--if(node.menuType=='1'){-->
                    <#--layer.msg('请勿选择按钮', {icon: 5,anim:6});-->
                    <#--return false;-->
                <#--}-->
                <#--$('#pId').val(node.id);-->
                <#--$('#pName').val(node.name);-->
            <#--}-->
        <#--});-->
/*        $('#select_icon').click(function(){
            parent.layer.open({
                id:'icon',
                type: 2,
                area: ['800px','600px'],
                shade: 0.4,
                zIndex: layer.zIndex,
                title: '图标',
                content: '/plugin/html/icon.html'
            });
        });*/
        //自定义验证规则
        var type=$('#menuType');

            form.verify({
                dname:function(v){
                    if(v.trim()==''){
                        return '请设置设备名称';
                    }
                }
                ,devicePw:function(v)
                {
                    if (v.trim() == '') {
                        return '请设置设备密码';
                    }
                }
                ,devicePwcheck: function (v) {
                    var password = document.getElementById('devicePw');
                    if(v.trim()==''){
                        return '请再输入一次设备密码';
                    }else if(v.trim()!= password.value){
                        return '设备密码不一致';
                    }

                }
        });

    /*    form.on('select(menuType)', function(data){
            if(data.value=='2'){
                $('#pId').val('');
                dOs('pName',true);dOs('permission',true);dOs('url',false);
            }else if(data.value=='1'){//按钮
                dOs('url',true);dOs('pName',false);dOs('permission',false);
            }else if(data.value=='0'){
                dOs('url',false);dOs('pName',false);dOs('permission',false);
            }
        });*/
        /**
         * id :元素id
         * flag true:禁止输入，false 允许输入
         */
        function dOs(id,flag){
            var $id= $("#"+id);
            if(flag){
                $id.val('');
                $id.attr('disabled','disabled').css('background','#e6e6e6');
            }
            else
                $id.removeAttr('disabled').css('background','white')
        }

        $('#close').click(function(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        //监听提交
        form.on('submit(add)', function(data){
            layerAjax('addDevice',data.field,'deviceList');
            return false;
        });
        form.render();
    });

</script>
</body>

</html>
