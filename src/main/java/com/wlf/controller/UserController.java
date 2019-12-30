package com.wlf.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.wlf.entity.User;
import com.wlf.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Class UserController
 * Date  2019/12/24 15:43
 * Author 王龙飞
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/selectAllUser")
    public Map<String,Object> selectAllUser(Integer page, Integer rows){
        return userService.selectAllUser(page,rows);
    }

    @RequestMapping("userOut")
    public void userOut(HttpServletResponse response, HttpServletRequest request) throws IOException {
        List<User> users = userService.selectAll();
        //将路径补全
        String path = request.getSession().getServletContext().getRealPath("/user/image");
        for(User u:users){
            u.setPhoto(path+"//"+u.getPhoto());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("魔教弟子","小滑头"),
                User.class, users);
        //设置响应头
        String encode= URLEncoder.encode("182班.xls","UTF-8");
        response.setHeader("content-disposition","attachment;filename="+encode);
        workbook.write(response.getOutputStream());
    }
}
