package com.wlf.controller;

import com.wlf.entity.Banner;
import com.wlf.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Class BannerService
 * Date  2019/12/19 15:11
 * Author 王龙飞
 */

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("selectAllBanner")
    public Map<String,Object> selectAllBanner(Integer page, Integer rows){
        Map<String, Object> map = bannerService.selectAllBanner(page, rows);
        return map;
    }

    @RequestMapping("edit")
    public Map<String,Object> edit(String oper, Banner banner){
        Map<String,Object> map=new HashMap<>();
        if(oper.equals("add")){
            map=add(banner);
        }
        if(oper.equals("edit")){
            map=edit(banner);
        }
        if(oper.equals("del")){
            map=del(banner);
        }
        return map;
    }
    public Map<String,Object> add(Banner banner){
        Map<String,Object> map=new HashMap<>();
        System.out.println(banner);
        try {
            String id=bannerService.add(banner);
            map.put("status",true);
            map.put("message",id);
        } catch (Exception e) {
            map.put("status",false);
            map.put("message",e.getMessage());
            e.printStackTrace();
        }
        return map;
    }
    public Map<String,Object> edit(Banner banner){
        Map<String,Object> map=new HashMap<>();
        try {
            if(banner.getCover().equals("")){
                banner.setCover(null);
            }
            bannerService.edit(banner);
            map.put("status",true);
        } catch (Exception e) {
            map.put("status",false);
            map.put("message",e.getMessage());
            e.printStackTrace();
        }
        return map;
    }
    public Map<String,Object> del(Banner banner){
        Map<String,Object> map=new HashMap<>();
        try {
            bannerService.del(banner);
            map.put("status",true);
        } catch (Exception e) {
            map.put("status",false);
            map.put("message",e.getMessage());
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("upload")
    public void upload(String id, MultipartFile cover, HttpServletRequest request){
        //1.进行文件上传
        String name= UUID.randomUUID().toString()+cover.getOriginalFilename();
        try {
            cover.transferTo(new File(request.getSession().getServletContext().getRealPath("/banner/image"),name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.修改对应库表中数据
        Banner banner=new Banner();
        banner.setId(id);
        banner.setCover(name);
        bannerService.edit(banner);
    }
}
