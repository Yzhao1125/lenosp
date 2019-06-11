package com.len.controller;

import com.alibaba.fastjson.JSONObject;
import com.len.base.CurrentRole;
import com.len.base.CurrentUser;
import com.len.base.Principal;
import com.len.core.RanGenerator.Generator;
import com.len.core.SocketThread.Handler;
import com.len.core.shiro.ShiroUtil;
import com.len.entity.PDeviceS;


import com.len.entity.PUserDevice;
import com.len.entity.SysUser;
import com.len.exception.MyException;
import com.len.service.DeviceService;
import com.len.entity.PDevice;
import com.len.service.PUserDeviceService;
import com.len.service.SysUserService;
import com.len.util.Checkbox;
import com.len.util.CommonUtil;
import com.len.util.JsonUtil;
import com.len.util.ReType;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * created by Yuan
 * 2019.4.15
 */
@Controller
//@RequestMapping(value = "")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private SysUserService userService;

    @Autowired
    private PUserDeviceService pUserDeviceService;

    private Generator generator;

    public String deviceID;

    @Value("${adminRole}")
    private String adminRole;

    @RequestMapping(value ="/showDevice")
  //  @RequiresPermissions("device:show")
    public String showDevice(){
        return "/system/device/deviceList";
    }

    @RequestMapping(value="/showAddDevice")
    public String addDevice(){
        return "/system/device/addDevice";
    }

    /**
     * 显示前台设备管理页面的设备实例表格
     * @param pDevice
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/showDeviceList")
    @ResponseBody
    public ReType showDeviceList(PDevice pDevice, String page, String limit){
        CurrentUser user = CommonUtil.getUser();
        List<CurrentRole> currentRoleList = user.getCurrentRoleList();
        long count = currentRoleList.stream().filter(s -> adminRole.equals(s.getRoleName())).count();
        if(count==0)
            pDevice.setUserId(user.getId());
        return deviceService.show(pDevice, Integer.valueOf(page), Integer.valueOf(limit));
    }

    @RequestMapping(value="/addDevice")
    @ResponseBody
    public JsonUtil addDevice(PDeviceS pDeviceS){
    //    System.out.println("接收到数据为："+pDeviceS.getDname()+pDeviceS.getDevicePw());
        if (pDeviceS == null){
            return JsonUtil.error("获取数据失败");
        }
        if (StringUtils.isEmpty(pDeviceS.getDname())){
          JsonUtil.error("设备名称不能为空");
        }
        if(StringUtils.isEmpty(pDeviceS.getDevicePw())){
            JsonUtil.error("设备密码不能为空");
        }
        int result = deviceService.checkDeviceName(pDeviceS.getDname()); //检查设备名称是否已存在
        if(result > 0){
            return JsonUtil.error("设备名称已存在");
        }

        JsonUtil jsonUtil = new JsonUtil();

        //获取当前用户
        CurrentUser user = CommonUtil.getUser();
        String userid = user.getId();

        //
        PDevice pDevice= new PDevice();
        pDevice.setConnect("设备尚未认证");
        pDevice.setDname(pDeviceS.getDname());
        pDevice.setDevicepw(pDeviceS.getDevicePw());

        generator = new Generator();
        deviceID =generator.generateString();
        pDevice.setDeviceid(deviceID); //随机生成设备编号，还缺少一个与已有设备编号比对的过程
        pDevice.setIp("");
        try {
            deviceService.addDevice(pDevice);
             PUserDevice pUserDevice = new PUserDevice();
             pUserDevice.setDeviceid(deviceID);
             pUserDevice.setUserid(userid);
            pUserDeviceService.addUserDevice(pUserDevice);
            jsonUtil.setMsg("保存成功");
        }catch (MyException e){
            jsonUtil.setMsg("保存失败");
            jsonUtil.setFlag(false);
            e.printStackTrace();
        }

          return jsonUtil;
    }


    @GetMapping(value = "updateDevice")
    public String goUpdateUser(String eid, Model model, boolean detail) {
        System.out.println("0000000000000"+eid);
        if (StringUtils.isNotEmpty(eid)) {

            PDevice pDevice = deviceService.selectDevicebyeid(eid);
            System.out.println("1111111111"+pDevice.getDname());
            String uid = pUserDeviceService.getuidbyeid(eid);
            System.out.println("22222222"+uid);
            SysUser user = userService.selectByPrimaryKey(uid);
            System.out.println("333333"+user.getUsername());

            model.addAttribute("pdevice", pDevice);
            model.addAttribute("user",user);
         //   model.addAttribute("boxJson", checkboxList);
        }
        model.addAttribute("detail", detail);
        return "system/device/update-device";
    }

}
