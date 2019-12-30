package com.wlf.controller;

import com.wlf.entity.*;
import com.wlf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Class CmfzController
 * Date  2019/12/26 14:21
 * Author 王龙飞
 */

@RestController
@RequestMapping("/cmfz")
public class CmfzController {
    @Autowired
    BannerService bannerService;
    @Autowired
    AlbumService albumService;
    @Autowired
    ChapterService chapterService;
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;

    @RequestMapping("/first_page")
    public Map<String,Object> first_page(String uid,String type,String sub_type){
        Map<String,Object> map = new HashMap<>();
        if(uid==null||type==null){
            map.put("error","参数不能为空");
        }else{
            if(type.equals("all")){
                List<Banner> list = new ArrayList<>();
                list = bannerService.selectAllBanners();
                map.put("header",list);
            }
            if(type.equals("wen")){
                List<Album> list = albumService.selectAllAlbums();
                map.put("body",list);
            }
            if(type.equals("si")){
                if(sub_type==null){
                    map.put("error","参数不能为空");
                }else{
                    if(sub_type.equals("ssyj")){
                        List<Article> list = articleService.selectAllArticles();
                        map.put("body",list);
                    }
                    if(sub_type.equals("xmfy")){
                        map.put("body","显秘法要的文章数据");
                    }
                }
            }
        }
        return map;
    }

    @RequestMapping("/wen")
    public Map<String,Object> wen(String id,String uid){
        Map<String,Object> map = new HashMap<>();
        if (uid==null){
            map.put("error","用户未登录");
        }else{
            Album album = albumService.selectAlbum(id);
            List<Chapter> list = chapterService.selectAllChapters(id);
            map.put("chapters",list);
            map.put("album",album);
        }
        return map;
    }

    @RequestMapping("/login")
    public Map<String,Object> login(String phone,String code,String password){
        Map<String,Object> map = new HashMap<>();
        User user = new User();
        user.setPhone(phone);
        User user2 = userService.selectOne(user);
        if(password!=null) {
            if (user2.getPassword().equals(password)){
                map.put("user",user2);
            }else {
                map.put("error","密码错误");
            }
        }
        return map;
    }

    @RequestMapping("/regist")
    public Map<String,Object> regist(String phone,String password){
        Map<String,Object> map = new HashMap<>();
        if (phone==null||password==null){
            map.put("error","参数不为空");
        }else{

        }

        User user = new User();
        user.setPhone(phone);
        User user1 = userService.selectOne(user);
        if (user1==null){
            user.setPassword(password);
            String id = UUID.randomUUID().toString();
            user.setId(id);
            userService.insertOne(user);
            map.put("user",user);
        }
        return map;
    }

    @RequestMapping("/modify")
    public Map<String,Object> modify(String uid,User user){
        Map<String,Object> map = new HashMap<>();
        userService.update(uid,user);
        User user1 =new User();
        user1.setId(uid);
        userService.selectOne(user1);
        map.put("user",user1);
        return map;
    }

    @RequestMapping("/obtain")
    public Map<String,Object> obtain(String phone){
        Map<String,Object> map = new HashMap<>();
        return map;
    }
}
