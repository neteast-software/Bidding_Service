package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.dict.DictHistory;
import com.neteast.business.mapper.DictHistoryMapper;
import com.neteast.business.service.IDictHistoryService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年11月22 10:41
 */

@Service
public class IDictHistoryServiceImpl extends ServiceImpl<DictHistoryMapper, DictHistory> implements IDictHistoryService {
}
