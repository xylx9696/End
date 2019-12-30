package com.wlf.service;


import com.wlf.entity.Chapter;

import java.util.Map;

public interface DebateService {
    Map<String, Object> debateQuery(Integer page, Integer rows, String album_id);

    String add(Chapter chapter);

    void edit(Chapter chapter);
}
