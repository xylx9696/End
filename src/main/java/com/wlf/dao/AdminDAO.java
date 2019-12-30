package com.wlf.dao;

import com.wlf.entity.Admin;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


@Repository
public interface AdminDAO extends Mapper<Admin> {

}
