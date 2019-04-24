package com.zjj.service;

import com.zjj.model.BizTheme;

public interface BizThemeService extends BaseService<BizTheme>  {
    int useTheme(Integer id);

    BizTheme selectCurrent();

    int deleteBatch(Integer[] ids);

}
