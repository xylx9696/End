package com.wlf.service.serviceImpl;

import com.wlf.dao.AdminDAO;
import com.wlf.entity.Admin;
import com.wlf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class AdminServiceImpl
 * Date  2019/12/17 16:40
 * Author 王龙飞
 */

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;


    @Override
    public Admin login(Admin admin) {
        Admin admin1 = new Admin();
        admin1.setUsername(admin.getUsername());
        Admin admin2 = adminDAO.selectOne(admin1);
        return admin2;
    }
}
