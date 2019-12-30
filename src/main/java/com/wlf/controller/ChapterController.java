package com.wlf.controller;


import com.wlf.entity.Chapter;
import com.wlf.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName ChapterController
 * @Discription
 * @Author
 * @Date 2019/12/20 0020 10:32
 * @Version 1.0
 */
@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    ChapterService chapterService;
    @RequestMapping("selectAllChapter")
    public Map<String,Object> selectAllChapter(String id,Integer page,Integer rows){
        Map<String, Object> map = chapterService.selectAllChapter(id, page, rows);
        return map;
    }
    @RequestMapping("edit")
    public Map<String,Object> edit(String aid, String oper, Chapter chapter){
        Map<String,Object> map=new HashMap<>();
        System.out.println("接收的id:"+chapter.getId());
        if(oper.equals("add")){
            map=add(chapter, aid);
        }
        if(oper.equals("edit")){
            map=edit(chapter);
        }
        if(oper.equals("del")){
            map=del(chapter);
        }
        return map;
    }
    public Map<String,Object> add(Chapter chapter,String aid){
        Map<String,Object> map=new HashMap<>();
        try {
            String id=chapterService.add(aid, chapter);
            map.put("status",true);
            map.put("message",id);
        } catch (Exception e) {
            map.put("status",false);
            map.put("message",e.getMessage());
            e.printStackTrace();
        }
        return map;
    }
    public Map<String,Object> edit(Chapter chapter){
        Map<String,Object> map=new HashMap<>();
        try {
            if(chapter.getCover().equals("")){
                chapter.setCover(null);
            }
            chapterService.edit(chapter);
            map.put("status",true);
        } catch (Exception e) {
            map.put("status",false);
            map.put("message",e.getMessage());
            e.printStackTrace();
        }
        return map;
    }
    public Map<String,Object> del(Chapter chapter){
        Map<String,Object> map=new HashMap<>();
        try {
            chapterService.del(chapter);
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
        //音频文件上传
        String name= UUID.randomUUID().toString()+cover.getOriginalFilename();
        System.out.println(name);
        File file=new File(request.getSession().getServletContext().getRealPath("/album/mv"),name);
        try {
            cover.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //修改章节信息
        Chapter chapter=new Chapter();
        chapter.setId(id);
        chapter.setCover(name);
        //保存文件的大小
        BigDecimal size=new BigDecimal(cover.getSize());
        BigDecimal dom=new BigDecimal(1024);
        BigDecimal bigDecimal = size.divide(dom).divide(dom).setScale(2, BigDecimal.ROUND_HALF_UP);
        chapter.setSize(bigDecimal+"MB");
        //获取文件的时长
        Encoder encoder=new Encoder();
        try {
            long duration = encoder.getInfo(file).getDuration();
            chapter.setDuration(duration/1000/60+":"+duration/1000%60);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        chapterService.edit(chapter);
    }
}
