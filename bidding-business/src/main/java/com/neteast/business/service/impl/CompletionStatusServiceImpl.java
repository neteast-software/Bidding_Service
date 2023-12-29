package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.bid.CompletionStatus;
import com.neteast.business.mapper.CompletionStatusMapper;
import com.neteast.business.service.ICompletionStatusService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年12月29 13:34
 */

@Service
public class CompletionStatusServiceImpl extends ServiceImpl<CompletionStatusMapper, CompletionStatus> implements ICompletionStatusService {
}
