package com.wlf.controller;

import com.wlf.util.SecurityCode;
import com.wlf.util.SecurityImage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class CodeController
 * Date  2019/12/17 17:21
 * Author 王龙飞
 */

@RestController
@RequestMapping("code")
public class CodeController {

    @RequestMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String imageCode = SecurityCode.getSecurityCode();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("Code",imageCode);
        ImageIO.write(SecurityImage.createImage(imageCode),"jpeg",response.getOutputStream());
    }
}
