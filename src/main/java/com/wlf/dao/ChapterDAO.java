package com.wlf.dao;


import com.wlf.entity.Chapter;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ChapterDAO extends Mapper<Chapter> {
}
