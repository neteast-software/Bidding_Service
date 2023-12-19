package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.dict.DictType;

/**
 * @author lzp
 * @date 2023年12月19 18:14
 */
public interface IDictTypeService extends IService<DictType> {

    boolean removeDictType(Integer id);
}
