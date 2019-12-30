package com.wlf.service;



import com.wlf.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    public Map<String,Object> selectAllArticle(Integer page, Integer rows);
    public void add(Article article);
    public void edit(Article article);
    public void del(Article article);

    List<Article> selectAllArticles();
}
