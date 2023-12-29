package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.bid.CompletionStatus;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月29 13:33
 */
public interface ICompletionStatusService extends IService<CompletionStatus> {

    boolean updateByCompletionStatus(CompletionStatus completionStatus);

    List<CompletionStatus> getListByPackageId(Integer packageId);
}
