package com.wlf.dao;


import com.wlf.entity.Album;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AlbumDAO extends Mapper<Album> {
}
