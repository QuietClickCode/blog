package com.zjj.mapper;

import com.zjj.model.BizTheme;
import com.zjj.util.MyMapper;

public interface BizThemeMapper extends MyMapper<BizTheme> {
    int setInvaid();
    int updateStatusById(Integer id);
    int deleteBatch(Integer[] ids);
}