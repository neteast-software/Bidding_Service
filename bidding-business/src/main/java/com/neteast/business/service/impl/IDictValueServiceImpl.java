package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.dict.DictHistory;
import com.neteast.business.domain.dict.DictKey;
import com.neteast.business.domain.dict.DictValue;
import com.neteast.business.mapper.DictHistoryMapper;
import com.neteast.business.mapper.DictValueMapper;
import com.neteast.business.service.IDictHistoryService;
import com.neteast.business.service.IDictValueService;
import com.neteast.common.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月22 10:43
 */

@Service
public class IDictValueServiceImpl extends ServiceImpl<DictValueMapper, DictValue> implements IDictValueService {

    @Resource
    DictValueMapper dictValueMapper;

    @Resource
    IDictHistoryService dictHistoryService;

    @Override
    public List<DictValue> getKeyValue(Integer keyId) {
        return lambdaQuery().eq(DictValue::getKeyId,keyId).eq(DictValue::getUse,"1").list();
    }

    @Override
    public List<DictValue> getDictValueList(DictValue dictValue) {
        return dictValueMapper.getList(dictValue);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delDictValueData(Integer valueId) {

        removeById(valueId);
        dictHistoryService.delByValueId(valueId);
        return true;
    }

    @Override
    public boolean delDictValueByKeyId(Integer keyId) {
        return remove(lambdaQuery().eq(DictValue::getKeyId,keyId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateDictValue(DictValue dictValue) {
        DictHistory dictHistory = DictHistory.convert(dictValue);
        dictHistoryService.save(dictHistory);
        updateById(dictValue);
        return true;
    }
}
