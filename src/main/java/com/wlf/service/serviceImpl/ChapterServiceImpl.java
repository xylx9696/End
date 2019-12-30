package com.wlf.service.serviceImpl;


import com.wlf.dao.AlbumDAO;
import com.wlf.dao.ChapterDAO;
import com.wlf.entity.Album;
import com.wlf.entity.Chapter;
import com.wlf.service.ChapterService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName ChapterServiceImpl
 * @Discription
 * @Author
 * @Date 2019/12/20 0020 10:29
 * @Version 1.0
 */
@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterDAO chapterDao;
    @Autowired
    AlbumDAO albumDao;
    @Override
    public Map<String, Object> selectAllChapter(String id, Integer page, Integer rows) {
        Map<String,Object> map=new HashMap<>();
        Chapter chapter=new Chapter();
        chapter.setAlbumId(id);
        RowBounds row=new RowBounds((page-1)*rows,rows);
        List<Chapter> chapters = chapterDao.selectByRowBounds(chapter, row);
        Integer count=chapterDao.selectCount(chapter);
        map.put("page",page);
        map.put("rows",chapters);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }

    @Override
    public String add(String aid, Chapter chapter) {
        String id= UUID.randomUUID().toString();
        chapter.setId(id);
        chapter.setAlbumId(aid);
        chapter.setCreateDate(new Date());
        Album a=new Album();
        a.setId(aid);
        Album album=albumDao.selectOne(a);
        album.setCount(album.getCount()+1);
        albumDao.updateByPrimaryKeySelective(album);
        int i = chapterDao.insertSelective(chapter);
        if(i==0){
            throw new RuntimeException("添加失败");
        }
        return id;
    }

    @Override
    public void edit(Chapter chapter) {
        int i = chapterDao.updateByPrimaryKeySelective(chapter);
        if(i==0){
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public void del(Chapter chapter) {
        int i = chapterDao.deleteByPrimaryKey(chapter);
        if(i==0){
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    public List<Chapter> selectAllChapters(String id) {
        Chapter chapter = new Chapter();
        chapter.setAlbumId(id);
        List<Chapter> list = chapterDao.select(chapter);
        return list;
    }
}
