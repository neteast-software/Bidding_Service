package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.dict.DictValue;
import com.neteast.business.mapper.DictValueMapper;
import com.neteast.business.service.IDictValueService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年11月22 10:43
 */

@Service
public class IDictValueServiceImpl extends ServiceImpl<DictValueMapper, DictValue> implements IDictValueService {
}
