package com.wlf.service.serviceImpl;

import com.wlf.dao.BannerDAO;
import com.wlf.entity.Banner;
import com.wlf.service.BannerService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Class BannerServiceImpl
 * Date  2019/12/19 15:14
 * Author 王龙飞
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDAO bannerDAO;

    @Override
    public Map<String, Object> selectAllBanner(Integer page, Integer rows) {
        Map<String,Object> map=new HashMap<>();
        Banner banner=new Banner();
        RowBounds row=new RowBounds((page-1)*rows,rows);
        List<Banner> banners = bannerDAO.selectByRowBounds(banner, row);
        int i = bannerDAO.selectCount(banner);
        map.put("page",page);
        map.put("rows",banners);
        map.put("total",i%rows==0?i/rows:i/rows+1);
        map.put("records",i);
        return map;
    }

    @Override
    public String add(Banner banner) {
        String id= UUID.randomUUID().toString();
        banner.setId(id);
        banner.setCreate_date(new Date());
        int i = bannerDAO.insertSelective(banner);
        if(i==0){
            throw new RuntimeException("添加失败");
        }
        return id;
    }

    @Override
    public List<Banner> selectAllBanners() {
        List<Banner> list = new ArrayList<>();
        list = bannerDAO.selectAll();
        return list;
    }

    @Override
    public void edit(Banner banner) {
        int i = bannerDAO.updateByPrimaryKeySelective(banner);
        if(i==0){
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public void del(Banner banner) {
        int i = bannerDAO.delete(banner);
        if(i==0){
            throw new RuntimeException("删除失败");
        }
    }
}
