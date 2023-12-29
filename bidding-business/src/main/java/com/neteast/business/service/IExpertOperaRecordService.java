package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.bid.CompletionStatus;
import com.neteast.business.domain.project.ExpertOperaRecord;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月28 18:21
 */
public interface IExpertOperaRecordService extends IService<ExpertOperaRecord> {

    boolean updateRecordByItemId(ExpertOperaRecord record);

    /** 判断供应商的评分项的得分一致性 */
    boolean getScoreConsistentBySupplierId(Integer itemId,Integer supplierId);

    boolean getChooseConsistentBySupplierId(Integer itemId,Integer supplierId);

    /** 判断供应商是否淘汰 */
    boolean getOutBySupplierId(Integer supplierId,Integer itemId);

    /** 获取主持人端展示的数据 */
    Long getCountByCompletionStatus(CompletionStatus completionStatus);

    List<ExpertOperaRecord> getExpertOperaRecordByItemId(Integer supplierId,Integer expertId,Integer itemId);

}
