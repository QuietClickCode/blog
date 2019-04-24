package com.zjj.service;

import com.zjj.model.BizLink;

import java.util.List;

public interface BizLinkService extends BaseService<BizLink> {
    List<BizLink> selectLinks(BizLink bizLink);

    int deleteBatch(Integer[] ids);

}
