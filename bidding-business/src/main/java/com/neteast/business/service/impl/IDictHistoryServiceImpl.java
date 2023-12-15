package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.dict.DictHistory;
import com.neteast.business.mapper.DictHistoryMapper;
import com.neteast.business.service.IDictHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月22 10:41
 */

@Service
public class IDictHistoryServiceImpl extends ServiceImpl<DictHistoryMapper, DictHistory> implements IDictHistoryService {

    @Resource
    DictHistoryMapper dictHistoryMapper;

    @Override
    public boolean delByKeyId(Integer keyId) {
        QueryWrapper<DictHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("key_id",keyId);
        return remove(queryWrapper);
    }

    @Override
    public boolean delByValueId(Integer valueId) {
        QueryWrapper<DictHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("value_id",valueId);
        return remove(queryWrapper);
    }

    @Override
    public List<DictHistory> getDictHistoryList(DictHistory dictHistory) {
        return dictHistoryMapper.getList(dictHistory);
    }
}
