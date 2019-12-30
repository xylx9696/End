package com.wlf.dao;


import com.wlf.entity.Banner;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface BannerDAO extends Mapper<Banner> {
}
