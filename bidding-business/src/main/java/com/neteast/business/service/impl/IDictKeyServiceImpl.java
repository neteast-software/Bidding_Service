package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.dict.DictKey;
import com.neteast.business.mapper.DictKeyMapper;
import com.neteast.business.service.IDictKeyService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年11月22 10:42
 */

@Service
public class IDictKeyServiceImpl extends ServiceImpl<DictKeyMapper, DictKey> implements IDictKeyService {
}
