package com.wlf.controller;


import com.wlf.entity.Article;
import com.wlf.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ArticleController
 * @Discription
 * @Author
 * @Date 2019/12/23 0023 11:44
 * @Version 1.0
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @RequestMapping("selectAllArticle")
    public Map<String,Object> selectAllArticle(Integer page,Integer rows){
        return articleService.selectAllArticle(page, rows);
    }
    @RequestMapping("edit")
    public Map<String,Object> edit(Article article, String oper){
        Map<String,Object> map=new HashMap<>();
        System.out.println("进行的操作："+oper);
         if(oper.equals("add")){
             map=add(article);
         }
         if(oper.equals("edit")){
             map=edit(article);
         }
         if(oper.equals("del")){
             map=del(article);
         }
         return map;
    }
    public Map<String,Object> add(Article article){
        Map<String,Object> map=new HashMap<>();
        try{
            System.out.println("添加的文章："+article);
            articleService.add(article);
            map.put("status",true);
        }catch (Exception e){
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }
    public Map<String,Object> edit(Article article){
        Map<String,Object> map=new HashMap<>();
        try{
            articleService.edit(article);
            map.put("status",true);
        }catch (Exception e){
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }
    public Map<String,Object> del(Article article){
        Map<String,Object> map=new HashMap<>();
        try{
            articleService.del(article);
            map.put("status",true);
        }catch (Exception e){
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }
}
