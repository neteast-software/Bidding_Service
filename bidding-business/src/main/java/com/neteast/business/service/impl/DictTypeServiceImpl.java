package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.dict.DictType;
import com.neteast.business.mapper.DictTypeMapper;
import com.neteast.business.service.IDictKeyService;
import com.neteast.business.service.IDictTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lzp
 * @date 2023年12月19 18:14
 */

@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements IDictTypeService {

    @Resource
    IDictKeyService dictKeyService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeDictType(Integer id) {
        this.removeById(id);
        dictKeyService.delDictKeyByDictType(id);
        return true;
    }
}
