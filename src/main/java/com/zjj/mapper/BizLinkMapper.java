package com.zjj.mapper;

import com.zjj.model.BizLink;
import com.zjj.util.MyMapper;

import java.util.List;

public interface BizLinkMapper extends MyMapper<BizLink> {

    List<BizLink> selectLinks(BizLink bizLink);

    int deleteBatch(Integer[] ids);

}
