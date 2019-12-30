package com.wlf.controller;


import com.wlf.entity.Album;
import com.wlf.service.AlbumService;
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
 * @ClassName AlbumController
 * @Discription
 * @Author
 * @Date 2019/12/20 0020 10:03
 * @Version 1.0
 */
@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    AlbumService albumService;
    @RequestMapping("selectAllAlbum")
    public Map<String,Object> selectAllAlbum(Integer page, Integer rows){
        return  albumService.selectAllAlbum(page, rows);
    }
    @RequestMapping("edit")
    public Map<String,Object> edit(String oper, Album album){
        Map<String,Object> map=new HashMap<>();
        if(oper.equals("add")){
            map=add(album);
        }
        if(oper.equals("edit")){
            map=edit(album);
        }
        if(oper.equals("del")){
            map=del(album);
        }
        return map;
    }
    public Map<String,Object> add(Album album){
        Map<String,Object> map=new HashMap<>();
        try {
            String id = albumService.add(album);
            map.put("message",id);
            map.put("status",true);
        } catch (Exception e) {
            map.put("message",e.getMessage());
            map.put("status",false);
            e.printStackTrace();
        }
        return map;
    }
    public Map<String,Object> edit(Album album){
        Map<String,Object> map=new HashMap<>();
        try {
            if(album.getCover().equals("")){
                album.setCover(null);
            }
            albumService.edit(album);
            map.put("status",true);
        } catch (Exception e) {
            map.put("message",e.getMessage());
            map.put("status",false);
            e.printStackTrace();
        }
        return map;
    }
    public Map<String,Object> del(Album album){
        Map<String,Object> map=new HashMap<>();
        try {
            albumService.del(album);
            map.put("status",true);
        } catch (Exception e) {
            map.put("message",e.getMessage());
            map.put("status",false);
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("upload")
    public void upload(String id, MultipartFile cover,HttpServletRequest request){
        String name= UUID.randomUUID().toString()+cover.getOriginalFilename();
        //上传图片
        try {
            cover.transferTo(new File(request.getSession().getServletContext().getRealPath("/album/image"),name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //修改对应专辑的信息
        Album album=new Album();
        album.setId(id);
        album.setCover(name);
        albumService.edit(album);
    }
}
