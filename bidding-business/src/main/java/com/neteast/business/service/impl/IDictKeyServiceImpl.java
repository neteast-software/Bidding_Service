package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.dict.DictHistory;
import com.neteast.business.domain.dict.DictKey;
import com.neteast.business.domain.dict.DictValue;
import com.neteast.business.domain.dict.vo.DictKeyVO;
import com.neteast.business.mapper.DictKeyMapper;
import com.neteast.business.service.IDictHistoryService;
import com.neteast.business.service.IDictKeyService;
import com.neteast.business.service.IDictValueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月22 10:42
 */

@Service
public class IDictKeyServiceImpl extends ServiceImpl<DictKeyMapper, DictKey> implements IDictKeyService {

    @Resource
    DictKeyMapper dictKeyMapper;

    @Resource
    IDictValueService dictValueService;

    @Resource
    IDictHistoryService dictHistoryService;

    @Override
    public List<DictKeyVO> getDictKeyList(DictKeyVO dictKey) {
        return dictKeyMapper.getList(dictKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delDictKey(Integer keyId) {
        removeById(keyId);
        dictHistoryService.delByKeyId(keyId);
        dictValueService.delDictValueByKeyId(keyId);
        return true;
    }


}
