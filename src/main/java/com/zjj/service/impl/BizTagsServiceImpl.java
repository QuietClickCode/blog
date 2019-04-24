package com.zjj.service.impl;

import com.zjj.mapper.BizTagsMapper;
import com.zjj.model.BizTags;
import com.zjj.service.BizTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BizTagsServiceImpl extends BaseServiceImpl<BizTags> implements BizTagsService {

    @Autowired
    private BizTagsMapper bizTagsMapper;

    @Override
    public List<BizTags> selectTags(BizTags bizTags) {
        return bizTagsMapper.selectTags(bizTags);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return bizTagsMapper.deleteBatch(ids);
    }
}
