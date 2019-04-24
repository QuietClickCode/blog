package com.zjj.mapper;

import com.zjj.model.BizTags;
import com.zjj.util.MyMapper;

import java.util.List;

public interface BizTagsMapper extends MyMapper<BizTags> {

    List<BizTags> selectTags(BizTags bizTags);

    int deleteBatch(Integer[] ids);
}
