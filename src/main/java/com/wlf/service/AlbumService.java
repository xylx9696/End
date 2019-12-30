package com.wlf.service;



import com.wlf.entity.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    public Map<String,Object> selectAllAlbum(Integer page, Integer rows);
    public String add(Album album);
    public void edit(Album album);
    public void del(Album album);

    List<Album> selectAllAlbums();

    Album selectAlbum(String id);
}
