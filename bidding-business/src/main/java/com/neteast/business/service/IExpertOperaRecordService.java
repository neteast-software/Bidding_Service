package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.bid.CompletionStatus;
import com.neteast.business.domain.project.ExpertOperaRecord;

/**
 * @author lzp
 * @date 2023年12月28 18:21
 */
public interface IExpertOperaRecordService extends IService<ExpertOperaRecord> {

    boolean updateRecordByItemId(ExpertOperaRecord record);

    /** 判断供应商的评分项的得分一致性 */
    boolean getConsistentBySupplierId(Integer itemId,Integer supplierId);

    /** 判断供应商是否淘汰 */
    boolean getOutBySupplierId(Integer packageId,Integer supplierId,Integer itemId);

    /** 获取主持人端展示的数据 */
    Long getCountByCompletionStatus(CompletionStatus completionStatus);

}
