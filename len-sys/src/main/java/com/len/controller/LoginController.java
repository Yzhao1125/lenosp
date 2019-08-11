package com.len.controller;

import com.alibaba.fastjson.JSONArray;
import com.len.core.annotation.Log;
import com.len.core.shiro.ShiroUtil;
import com.len.entity.PUser;
import com.len.entity.PUserRole;
import com.len.entity.SysMenu;
import com.len.entity.SysUser;
import com.len.exception.MyException;
import com.len.service.*;
import com.len.util.JsonUtil;
import com.len.util.VerifyCodeUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2017/12/4.
 * @email 154040976@qq.com
 * 登录、退出页面
 */
@Controller
@Slf4j
public class LoginController {

    @Autowired
    SysUserService userService;

//    @Autowired
////    private PUserService pUserService;

    @Autowired
   RoleService roleService;

    @Autowired
    PUserRoleService pUserRoleService;

    @Autowired
    private MenuService menuService;



    @GetMapping(value = "")
    public String loginInit() {
        return loginCheck();
    }

    @GetMapping (value ="/register")
    public String register(){
        return "register";
    }

    @PostMapping(value ="/register")
    public String goRegister(Model model,SysUser user){

        model.addAttribute("user",user);

        if (StringUtils.isBlank(user.getUsername())){
            model.addAttribute("msg","用户名不能为空");
            return "/register";
        }
        if(StringUtils.isBlank(user.getPassword())){
            model.addAttribute("msg","密码不能为空");
            return "/register";
        }

        if(StringUtils.isBlank(user.getRealName())){
            model.addAttribute("msg","请输入联系人姓名");
            return "/register";
        }

        if(StringUtils.isBlank(user.getSchoolname())){
            model.addAttribute("msg","请输入学校名称");
            return "/register";
        }

        if(StringUtils.isBlank(user.getDepart())){
            model.addAttribute("msg","请输入所在科目或学校职位");
            return "/register";
        }

        if(StringUtils.isBlank(user.getTel())){
            model.addAttribute("msg","请输入联系人电话");
            return "/register";
        }

        if(StringUtils.isBlank(user.getEmail())){
            model.addAttribute("msg","请输入邮箱");
            return "/register";
        }

        int result = userService.checkUser(user.getUsername());

        if(result >0){
            model.addAttribute("msg","用户名已存在");
            return "/register";
        }
        JsonUtil j = new JsonUtil();
        try{
               userService.register(user);

            model.addAttribute("msg","注册成功，请登录");
            return "/login";
        }catch (MyException e){
            model.addAttribute("msg","保存用户失败");
            e.printStackTrace();
        }
        return "/register";
    }


    @GetMapping(value = "goLogin")
    public String goLogin(Model model, ServletRequest request) {
        Subject sub = SecurityUtils.getSubject();
        if (sub.isAuthenticated()) {
            return "/main/main";
        } else {
            model.addAttribute("message", "请重新登录");
            return "/login";
        }
    }

    @GetMapping(value = "/login")
    public String loginCheck() {
        Subject sub = SecurityUtils.getSubject();
        Boolean flag2 = sub.isRemembered();
        boolean flag = sub.isAuthenticated() || flag2;
        Session session = sub.getSession();
        if (flag) {
            return "/main/main";
        }
        return "/login";
    }

    /**
     * 登录动作
     *
     * @param user
     * @param model
     * @param rememberMe
     * @return
     */
//    @ApiOperation(value = "/login", httpMethod = "POST", notes = "登录method")
//    @PostMapping(value = "/login")
//    public String login(SysUser user, Model model, String rememberMe, HttpServletRequest request) {
//        String codeMsg = (String) request.getAttribute("shiroLoginFailure");
//        if ("code.error".equals(codeMsg)) {
//            model.addAttribute("message", "验证码错误");
//            return "/login";
//        }
//        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername().trim(),
//                user.getPassword());
//        Subject subject = ShiroUtil.getSubject();
//        String msg = null;
//        try {
//            subject.login(token);
//            //subject.hasRole("admin");
//            if (subject.isAuthenticated()) {
//                return "redirect:/main";
//            }
//        } catch (UnknownAccountException e) {
//            msg = "用户名/密码错误";
//        } catch (IncorrectCredentialsException e) {
//            msg = "用户名/密码错误";
//        } catch (ExcessiveAttemptsException e) {
//            msg = "登录失败多次，账户锁定10分钟";
//        }
//        if (msg != null) {
//            model.addAttribute("message", msg);
//        }
//        return "/login";
//    }

    @ApiOperation(value = "/login", httpMethod = "POST", notes = "登录method")
    @PostMapping(value = "/login")
    public String login(PUser user, Model model, String rememberMe, HttpServletRequest request) {
        System.out.println("&&&&&&&&&&&"+user.getUname()+user.getPwd());
        String codeMsg = (String) request.getAttribute("shiroLoginFailure");
        if ("code.error".equals(codeMsg)) {
            model.addAttribute("message", "验证码错误");
            return "/login";
        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUname().trim(),
                user.getPwd());
        Subject subject = ShiroUtil.getSubject();
        String msg = null;
        try {
            subject.login(token);
            //subject.hasRole("admin");
            if (subject.isAuthenticated()) {
                return "redirect:/main";
            }
        } catch (UnknownAccountException e) {
            msg = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            msg = "用户名/密码错误";
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败多次，账户锁定10分钟";
        }
        if (msg != null) {
            model.addAttribute("message", msg);
        }
        return "/login";
    }

    @GetMapping("/main")
    public String main(){
        return "main/main";
    }

    @Log(desc = "用户退出平台")
    @GetMapping(value = "/logout")
    public String logout() {
        Subject sub = SecurityUtils.getSubject();
        sub.logout();
        return "/login";
    }

    /**
     * 组装菜单json格式
     * update by 17/12/13
     *
     * @return
     */
    public JSONArray getMenuJson() {
        List<SysMenu> mList = menuService.getMenuNotSuper();
        JSONArray jsonArr = new JSONArray();
        for (SysMenu sysMenu : mList) {
            SysMenu menu = getChild(sysMenu.getId());
            jsonArr.add(menu);
        }
        return jsonArr;
    }

    public SysMenu getChild(String id) {
        SysMenu sysMenu = menuService.selectByPrimaryKey(id);
        List<SysMenu> mList = menuService.getMenuChildren(id);
        for (SysMenu menu : mList) {
            SysMenu m = getChild(menu.getId());
            sysMenu.addChild(m);
        }
        return sysMenu;
    }


    @GetMapping(value = "/getCode")
    public void getYzm(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpg");

            //生成随机字串
            String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
            log.info("verifyCode:{}",verifyCode);
            //存入会话session
            HttpSession session = request.getSession(true);
            session.setAttribute("_code", verifyCode.toLowerCase());
            //生成图片
            int w = 146, h = 33;
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
