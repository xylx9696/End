package com.wlf.controller;

import com.wlf.entity.Admin;
import com.wlf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Class AdminController
 * Date  2019/12/17 16:37
 * Author 王龙飞
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public Map<String,Object> login(Admin admin, String code, HttpServletRequest httpServletRequest){
        Map<String,Object> map = new HashMap<String,Object>();
        Admin admin1 = adminService.login(admin);
        if (admin1!=null){
            if(admin1.getPassword().equals(admin.getPassword())){
                HttpSession session = httpServletRequest.getSession();
                String imageCode = (String) session.getAttribute("Code");
                if (code.equals(imageCode)){
                    map.put("result","success");
                    session.setAttribute("login",admin1);
                    return map;
                }else {
                    map.put("result","登陆失败,验证码错误");
                    return map;
                }
            }else{
                map.put("result","登陆失败,密码错误");
                return map;
            }
        }else{
            map.put("result","登陆失败,用户名不存在");
            return map;
        }
    }
}
