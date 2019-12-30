package com.wlf.service;

import com.wlf.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String, Object> selectAllUser(Integer page, Integer rows);

    List<User> selectAll();

    User selectOne(User user);

    void insertOne(User user);

    void update(String uid, User user);
}
