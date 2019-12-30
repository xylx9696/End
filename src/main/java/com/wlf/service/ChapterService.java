package com.wlf.service;



import com.wlf.entity.Chapter;

import java.util.List;
import java.util.Map;

public interface ChapterService {
    public Map<String,Object> selectAllChapter(String id, Integer page, Integer rows);
    public String add(String aid, Chapter chapter);
    public void edit(Chapter chapter);
    public void del(Chapter chapter);

    List<Chapter> selectAllChapters(String id);
}
