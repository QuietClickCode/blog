package com.zjj.mapper;

import com.zjj.model.BizCategory;
import com.zjj.util.MyMapper;

import java.util.List;

public interface BizCategoryMapper extends MyMapper<BizCategory> {

    List<BizCategory> selectCategories(BizCategory bizCategory);

    int deleteBatch(Integer[] ids);

    BizCategory selectById(Integer id);
}
