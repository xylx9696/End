package com.wlf.service;

import com.wlf.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {
    Map<String, Object> selectAllBanner(Integer page, Integer rows);

    void edit(Banner banner);

    void del(Banner banner);

    String add(Banner banner);

    List<Banner> selectAllBanners();
}
