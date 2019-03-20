package com.dragon.xiaomi.admin.controller;

import com.dragon.xiaomi.user.pojo.User;
import com.dragon.xiaomi.user.service.UserService;
import com.dragon.xiaomi.utils.PasswordUtils;
import com.dragon.xiaomi.utils.TextUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Administrator
 * @Date 2019/1/4 22:34
 * @Classname AdminLoginController
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController {
    private static final Logger logger=Logger.getLogger(AdminLoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/adminLogin")
    public String adminLogin(HttpServletRequest request, HttpServletResponse response){

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //获取密码盐
        String passwordSalt = userService.getPasswordSaltByUsername(username);

        if (TextUtils.empty(username)) {
            request.setAttribute("msg", "用户名不能为空");
            return "redirect:/admin/login.jsp";
        }
        if (TextUtils.empty(password)) {
            request.setAttribute("msg", "密码不能为空");
            return "redirect:/admin/login.jsp";
        }

        //管理员登录
        password= PasswordUtils.getPasswordMD5(password, passwordSalt, 1024);
        User admin = null;
        try {
            admin = userService.adminLogin(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("管理员登录失败");
            return "redirect:/admin/admin.jsp";
        }
        request.getSession().setAttribute("admin", admin);
        logger.info("管理员登录成功!==>"+username);
        return "redirect:/admin/admin.jsp";
    }

    @RequestMapping("/searchuserlist")
    @ResponseBody
    public List<User> searchUserList(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String gender = request.getParameter("gender");
        List<User> userList=null;
        if (username != null || gender!=null) {
            userList = userService.findAllUser(username, gender);
        }
        logger.info("查询用户==>"+userList.toString());
        return userList;
    }

    @RequestMapping("/getuserlist")
    public String getUserList(HttpServletRequest request){
        List<User> userList = userService.getUserList();
        request.getSession().setAttribute("userList", userList);
        logger.info("获取所有用户==>"+userList.toString());
        return "redirect:/admin/userList.jsp";
    }

    @RequestMapping("/getinvaliduserlist")
    public String getInvalidUserList(HttpServletRequest request){
        List<User> userList = userService.getInvalidUserList();
        request.getSession().setAttribute("userList", userList);
        logger.info("获取无效用户==>"+userList.toString());
        return "redirect:/admin/invalidUser.jsp";
    }

    @GetMapping("/deleteuser")
    public String deleteUser(HttpServletRequest request, HttpServletResponse response){
        String _id = request.getParameter("id");
        if (TextUtils.empty(_id)) {
            return null;
        }
        int id = Integer.parseInt(_id);
        userService.remove(id);
        logger.info("删除用户,被删除的用户id为==>"+id);
        return "forward:getuserlist.action";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("admin");
        request.getSession().invalidate();
        logger.info("管理员退出成功!");
        return "/admin/login";
    }

}
