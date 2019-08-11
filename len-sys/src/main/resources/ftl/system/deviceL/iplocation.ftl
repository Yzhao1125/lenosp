<!DOCTYPE html>
<html>
<#--<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=S9GPZ6RA1MXNlNfr76ecnTmd7pAWuZMN"></script>
    <title>IP定位获取当前城市</title>
</head>-->
<head>
    <meta charset="UTF-8">
    <title>设备定位地图</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />

    <#--<link rel="shortcut icon" href="<%=request.getContextPath()%>/plugin/x-admin/favicon.ico" type="image/x-icon" />-->
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${re.contextPath}/plugin/x-admin/css/font.css">
    <link rel="stylesheet" href="${re.contextPath}/plugin/x-admin/css/xadmin.css">

    <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/echart/echarts.min.js"></script>
    <script src="http://www.w3school.com.cn/jquery/jquery.js" type="text/javascript"></script>

    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=AK"></script>

    <style type="text/css">
        html, body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }

        #map {
            width: 70%;
            height: 100%;
            float: left;
        }
        #list{
            width: 30%;
            height: 100%;
            overflow: scroll;
            float: right;
        }
    </style>






</head>
<body>


<div id="map"> </div>
<div id="list"> </div>

<canvas id="canvas"></canvas>
<script type="text/javascript" src="http://api.map.baidu.com/api??v=2.0&ak=S9GPZ6RA1MXNlNfr76ecnTmd7pAWuZMN""></script>
<#--<script type="text/javascript" src="mapv.js"></script>-->
<script type="text/javascript" src="http://mapv.baidu.com/build/mapv.min.js"></script>
<script type="text/javascript">
    var markers = [];
    var markerdivs = [];
    // 百度地图API功能
    /* var map = new BMap.Map("allmap");
     var point = new BMap.Point(116.331398,39.897445);
     map.centerAndZoom(point,12);

     function myFun(result){
         var cityName = result.name;
         map.setCenter(cityName);
       //  alert("当前定位城市:"+cityName);
     }
     var myCity = new BMap.LocalCity();
     myCity.get(myFun);*/



    var markerArr =[];
    $.ajax({
        url: 'ip',
        type: 'post',
        data: {},
        datatype: "json",
        async: false,
        success: function (data) {
         //   console.log("后台传值：" + data);
             markerArr = eval("[" + data + "]");
            // console.log("markerArr:"+ markerArr);
        },
        error: function () {
            console.info("提交失败");
        }


    });

    function showpoint (i){

      //  console.log(markers[i]);
        openInfo("",this,markers[i][1],i)
        // marker.click();
    }



    /*    var markerArr = [
            { title: "名称：广州火车站", point: "113.264531,23.157003", address: "广东省广州市广州火车站", tel: "12306" },
            { title: "名称：广州塔（赤岗塔）", point: "113.330934,23.113401", address: "广东省广州市广州塔（赤岗塔） ", tel: "18500000000" },
            { title: "名称：广州动物园", point: "113.312213,23.147267", address: "广东省广州市广州动物园", tel: "18500000000" },
            { title: "名称：天河公园", point: "113.372867,23.134274", address: "广东省广州市天河公园", tel: "18500000000" },
            { title: "名称：武汉理工大学南湖校区",point:"114.339401,30.514676",address:"武汉理工大学南湖校区",tel:"18500000000"}
        ];*/
    var div = document.createElement("div");
    div.innerHTML = '<div id="r-result">搜索设备:<input type="text" id="suggestId" size="20" value="百度" style="width:150px;" /></div><div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>';


    var map;  //Map实例
    function map_init(){
        map = new BMap.Map("map"); // 创建Map实例
        map.centerAndZoom(new BMap.Point(105.403119, 38.028658), 5);  // 初始化地图,设置中心点坐标和地图级别
        map.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放
        /*     map.setMapStyle({
                 style: 'light'
             });
     */
        //第4步：向地图中添加缩放控件
        var ctrlNav = new window.BMap.NavigationControl({
            anchor: BMAP_ANCHOR_TOP_LEFT,
            type: BMAP_NAVIGATION_CONTROL_LARGE
        });
        map.addControl(ctrlNav);
        //第5步：向地图中添加缩略图控件
        var ctrlOve = new window.BMap.OverviewMapControl({
            anchor: BMAP_ANCHOR_BOTTOM_RIGHT,
            isOpen: 1
        });
        map.addControl(ctrlOve);

        //第6步：向地图中添加比例尺控件
        var ctrlSca = new window.BMap.ScaleControl({
            anchor: BMAP_ANCHOR_BOTTOM_LEFT
        });
        map.addControl(ctrlSca);

        //第7步：绘制点
        /*    for (var i = 0; i < markerArr.length; i++) {
                var p0 = markerArr[i].point.split(",")[0];
                var p1 = markerArr[i].point.split(",")[1];
                //  var p2 = markerArr[i].point.split(",")[2];
                //  var p3 = markerArr[i].point.split(",")[3];
                var maker = addMarker(new window.BMap.Point(p0, p1), i);
                addInfoWindow(maker, markerArr[i], i);
            }*/
        var tempseat = 0;
        var htmllist = '<ol style="list-style: none; padding: 0px; margin: 0px;">' ;

        for (var i = 0; i < markerArr.length; i++) {
            var p0 = markerArr[i].x;
            var p1 = markerArr[i].y;

/*            for(var ii = 0; ii<i;ii++){
                if(markerArr[i].x==markerArr[ii].x){
                    if ( markerArr[i].y==markerArr[ii].y){
                        p0 = p0+1.1;
                    }
                }
            }*/
            // console.log(html);
            var maker = addMarker(new window.BMap.Point(p0, p1), i);
            addInfoWindow(maker, markerArr[i]);
            markers.push([i,markerArr[i]]);
            markerdivs.push([i,maker]);
            htmllist += '<li onclick="showpoint('+
                i+
                ')" style="margin: 2px 0px; padding: 0px 5px 5px 0px; cursor: pointer; overflow: hidden; line-height: 17px;"><span style="background:url(http://api.map.baidu.com/images/markers.png) -23px '+tempseat+'px no-repeat;width:19px;height:25px;cursor:pointer;float:left;*zoom:1;overflow:hidden;margin:2px 3px 0 5px;*margin-right:0px;display:inline;">&nbsp;</span><div style="zoom: 1; overflow: hidden; padding: 0px 5px; background-color: rgb(240, 240, 240);"><div style="line-height:20px;font-size:12px;"><span style="color:#00c;">'+ markerArr[i]['dname']+'</span><a target="_blank" href="checkdevice2?param='+markerArr[i].deviceid+'" style="margin-left:5px;font-size:12px;color:#3d6dcc;font-weight:normal;text-decoration:none;">查看»</a></div><div style="padding:2px 0;line-height:18px;*zoom:1;overflow:hidden;"><b style="float:left;font-weight:bold;*zoom:1;overflow:hidden;padding-right:5px;*margin-right:-3px;">地址:</b><span style="color:#666;display:block;zoom:1;overflow:hidden;">'+markerArr[i].addressname+'</span></div><div style="padding:2px 0;line-height:18px;*zoom:1;overflow:hidden;"><b style="float:left;font-weight:bold;*zoom:1;overflow:hidden;padding-right:5px;*margin-right:-3px;">设备编号:</b><span style="color:#666;">'+markerArr[i].deviceid+'</span></div></div></li>';
            tempseat-=25;
        }
        htmllist += '</ol>';
        document.getElementById("list").innerHTML = htmllist;





    }
    function con(log){
        console.log(log)
    }
    //在地图上加标注点
    function addMarker(point, index) {
        var myIcon = new BMap.Icon("http://api.map.baidu.com/img/markers.png",
            new BMap.Size(23, 25), {
                offset: new BMap.Size(10, 25),
                imageOffset: new BMap.Size(0, 0 - index * 25)
            });


        var marker = new BMap.Marker(point, {icon: myIcon});

        map.addOverlay(marker);
        return marker;
    }

    //添加信息窗口
    function addInfoWindow(marker, poi) {
        //pop弹窗标题
        var title = '<div style="font-weight:bold;color:#CE5521;font-size:14px">' + poi.dname + '</div>';
        //pop弹窗信息
        var html = [];
        html.push('<table cellspacing="0" style="table-layout:fixed;width:100%;font:12px arial,simsun,sans-serif"><tbody>');
        html.push('<tr>');
        html.push('<td style="vertical-align:top;line-height:16px;width:38px;white-space:nowrap;word-break:keep-all">地址:</td>');
        html.push('<td style="vertical-align:top;line-height:16px">' + poi.addressname + ' </td>');
        html.push('</tr>');
        html.push('</tbody></table>');


        /*   html.push('<table cellspacing="0" style="table-layout:fixed;width:100%;font:12px arial,simsun,sans-serif"><tbody>');
           html.push('<tr>');
           html.push('<td style="vertical-align:top;line-height:16px;width:38px;white-space:nowrap;word-break:keep-all">电话:</td>');
           html.push('<td style="vertical-align:top;line-height:16px">' + poi.tel + ' </td>');
           html.push('</tr>');
           html.push('</tbody></table>'); */

        html.push('<table cellspacing="0" style="table-layout:fixed;width:100%;font:12px arial,simsun,sans-serif"><tbody>');
        html.push('<tr>');
        html.push('<td style="vertical-align:top;line-height:16px;width:38px;white-space:nowrap;word-break:keep-all">查看:</td>');
        html.push('<td style="vertical-align:top;line-height:16px"> <a href ="checkdevice2?param='+poi.deviceid+'" target="main-body" >实时状态监测</a> </td>');
        html.push('</tr>');
        html.push('</tbody></table>');


        html.push('<table cellspacing="0" style="table-layout:fixed;width:100%;font:12px arial,simsun,sans-serif"><tbody>');
        html.push('<tr>');
        html.push('<td style="vertical-align:top;line-height:16px;width:65px;white-space:nowrap;word-break:keep-all">设备编号:</td>');
        html.push('<td style="vertical-align:top;line-height:16px">' + poi.deviceid + ' </td>');
        html.push('</tr>');
        html.push('</tbody></table>');
        var infoWindow = new BMap.InfoWindow(html.join(""), { title: title, width: 200 });

        // var openInfoWinFun = function () {
        //     marker.openInfoWindow(infoWindow);
        // };

        marker.addEventListener("click",function(e){
            openInfo(infoWindow,e)}
        );
        // marker.addEventListener("click", openInfoWinFun);
     //   return openInfoWinFun;
    }
    function openInfo(infoWindow,e,marker,count){


        var infoWindow = infoWindow;



        if(marker){
            var point = new BMap.Point(marker.x, marker.y);

            var title = '<div style="font-weight:bold;color:#CE5521;font-size:14px">' + marker.dname + '</div>';
            //pop弹窗信息
            var html = [];
            html.push('<table cellspacing="0" style="table-layout:fixed;width:100%;font:12px arial,simsun,sans-serif"><tbody>');
            html.push('<tr>');
            html.push('<td style="vertical-align:top;line-height:16px;width:38px;white-space:nowrap;word-break:keep-all">地址:</td>');
            html.push('<td style="vertical-align:top;line-height:16px">' + marker.addressname + ' </td>');
            html.push('</tr>');
            html.push('</tbody></table>');


            /*   html.push('<table cellspacing="0" style="table-layout:fixed;width:100%;font:12px arial,simsun,sans-serif"><tbody>');
               html.push('<tr>');
               html.push('<td style="vertical-align:top;line-height:16px;width:38px;white-space:nowrap;word-break:keep-all">电话:</td>');
               html.push('<td style="vertical-align:top;line-height:16px">' + poi.tel + ' </td>');
               html.push('</tr>');
               html.push('</tbody></table>'); */

            html.push('<table cellspacing="0" style="table-layout:fixed;width:100%;font:12px arial,simsun,sans-serif"><tbody>');
            html.push('<tr>');
            html.push('<td style="vertical-align:top;line-height:16px;width:38px;white-space:nowrap;word-break:keep-all">查看:</td>');
            html.push('<td style="vertical-align:top;line-height:16px"> <a href ="checkdevice2?param='+marker.deviceid+'" target="main-body" >实时状态监测</a> </td>');
            html.push('</tr>');
            html.push('</tbody></table>');


            html.push('<table cellspacing="0" style="table-layout:fixed;width:100%;font:12px arial,simsun,sans-serif"><tbody>');
            html.push('<tr>');
            html.push('<td style="vertical-align:top;line-height:16px;width:65px;white-space:nowrap;word-break:keep-all">设备编号:</td>');
            html.push('<td style="vertical-align:top;line-height:16px">' + marker.deviceid + ' </td>');
            html.push('</tr>');
            html.push('</tbody></table>');
            infoWindow = new BMap.InfoWindow(html.join(""), { title: title, width: 200 });


            for(var allnum = 0;allnum<markerdivs.length;allnum++){
                markerdivs[allnum][1].setTop(false);
            }
            markerdivs[count][1].setTop(true);
            // markerdivs[count][1].setZIndex(111111);
            // markerdivs[count][1].setTop(true);
        }else {
            var p = e.target;
            var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
        }
        map.openInfoWindow(infoWindow,point); //开启信息窗口
    }



    /*   var data =[];
       //构造数据
       data.push({
           geometry: {
               type: 'Point',
               coordinates:[12725759.65,3556525.7]
           },
           count:1
       });

       data.push({
           geometry: {
               type: 'Point',
               coordinates:[12725759.65,3556525.7]
           },
           count:1
       });
   */


    /*    var citys = ["北京","天津","上海","重庆","石家庄","太原","呼和浩特","哈尔滨","长春","沈阳","济南","南京","合肥","杭州","南昌","福州","郑州","武汉","长沙","广州","南宁","西安","银川","兰州","西宁","乌鲁木齐","成都","贵阳","昆明","拉萨","海口"];
        var randomCount = 300;
        while(randomCount--){
            var cityCenter= mapv.utilCityCenter.getCenterByCityName(citys[parseInt(Math.random()*citys.length)]);
            data.push({
                geometry:{
                    type:'Point',
                    coordinates:[cityCenter.lng-2+Math.random()*4,cityCenter.lat-2+Math.random()*4]
                },
                count:30*Math.random()
            });
        }*/
    /*    var dataSet = new mapv.DataSet(data);
        console.log(data);

        var options ={
            fillStyle: 'red',
            shadowColor: 'red',
            shadowBlur: 1,
            globalCompositeOperation: 'lighter',
            methods: {
                click: function (item) {
                    console.log(item);
                }
            },
            size: 5,
            draw: 'simple'
        }
        var mapvLayer =new mapv.baiduMapLayer(map,dataSet,options);*/





    //异步调用百度js
    function map_load() {
        var load = document.createElement("script");
        load.src = "http://api.map.baidu.com/api?v=1.4&callback=map_init";
        document.body.appendChild(load);
    }
    window.onload = map_load;






</script>
</body>
</html>