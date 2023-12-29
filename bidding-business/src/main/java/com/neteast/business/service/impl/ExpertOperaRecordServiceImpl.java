package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.bid.CompletionStatus;
import com.neteast.business.domain.project.ExpertOperaRecord;
import com.neteast.business.mapper.ExpertOperaRecordMapper;
import com.neteast.business.service.IExpertOperaRecordService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年12月28 18:22
 */

@Service
public class ExpertOperaRecordServiceImpl extends ServiceImpl<ExpertOperaRecordMapper, ExpertOperaRecord> implements IExpertOperaRecordService {

    @Override
    public boolean updateRecordByItemId(ExpertOperaRecord record) {
        return this.lambdaUpdate().eq(ExpertOperaRecord::getItemId,record.getItemId()).
                eq(ExpertOperaRecord::getExpertId,record.getExpertId()).update(record);
    }

    @Override
    public boolean getConsistentBySupplierId(Integer itemId, Integer supplierId) {
        return false;
    }

    @Override
    public boolean getOutBySupplierId(Integer packageId, Integer supplierId,Integer itemId) {
        return false;
    }

    @Override
    public Long getCountByCompletionStatus(CompletionStatus completionStatus) {
        return this.lambdaQuery().eq(ExpertOperaRecord::getSupplierId,completionStatus.getSupplierId())
                .eq(ExpertOperaRecord::getExpertId,completionStatus.getUserId())
                .eq(ExpertOperaRecord::getItemId,completionStatus.getItemId()).count();

    }
}
