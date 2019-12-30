package com.wlf.service.serviceImpl;

import com.wlf.dao.UserDAO;

import com.wlf.entity.Album;
import com.wlf.entity.User;
import com.wlf.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class UserServiceImpl
 * Date  2019/12/24 16:03
 * Author 王龙飞
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public Map<String, Object> selectAllUser(Integer page, Integer rows) {
        Map<String,Object> map=new HashMap<>();
        User user=new User();
        RowBounds row=new RowBounds((page-1)*rows,rows);
        List<User> users = userDAO.selectByRowBounds(user, row);
        Integer count=userDAO.selectCount(user);
        map.put("page",page);
        map.put("rows",users);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }

    @Override
    public List<User> selectAll() {
        List<User> users = userDAO.selectAll();
        return users;
    }

    @Override
    public User selectOne(User user) {
        User user1 = userDAO.selectOne(user);
        return user1;
    }

    @Override
    public void insertOne(User user) {
        userDAO.insert(user);
    }

    @Override
    public void update(String uid, User user) {
        user.setId(uid);
        userDAO.updateByPrimaryKey(user);
    }
}
