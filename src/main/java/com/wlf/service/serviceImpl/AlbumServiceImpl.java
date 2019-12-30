package com.wlf.service.serviceImpl;


import com.wlf.dao.AlbumDAO;
import com.wlf.dao.ChapterDAO;

import com.wlf.entity.Album;
import com.wlf.service.AlbumService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName AlbumServiceImpl
 * @Discription
 * @Author
 * @Date 2019/12/20 0020 9:51
 * @Version 1.0
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumDAO albumDao;
    @Autowired
    ChapterDAO chapterDao;
    @Override
    public Map<String, Object> selectAllAlbum(Integer page, Integer rows) {
        Map<String,Object> map=new HashMap<>();
        Album album=new Album();
        RowBounds row=new RowBounds((page-1)*rows,rows);
        List<Album> albums = albumDao.selectByRowBounds(album, row);
        Integer count=albumDao.selectCount(album);
        map.put("page",page);
        map.put("rows",albums);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }

    @Override
    public String add(Album album) {
        String id= UUID.randomUUID().toString();
        album.setId(id);
        album.setCreateDate(new Date());
        int i = albumDao.insertSelective(album);
        if(i==0){
            throw new RuntimeException("添加失败");
        }
        return id;
    }

    @Override
    public void edit(Album album) {
        int i = albumDao.updateByPrimaryKeySelective(album);
        if(i==0){
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public void del(Album album) {
        System.out.println("删除的专辑："+album);
        int i = albumDao.deleteByPrimaryKey(album);
        /*Chapter chapter=new Chapter();
        chapter.setAlbumId(album.getId());
        int delete = chapterDao.delete(chapter);*/
        if(i==0){
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    public List<Album> selectAllAlbums() {
        List<Album> list = albumDao.selectAll();
        return list;
    }

    @Override
    public Album selectAlbum(String id) {
        Album album = new Album();
        album.setId(id);
        Album albums = albumDao.selectOne(album);
        return albums;
    }
}
