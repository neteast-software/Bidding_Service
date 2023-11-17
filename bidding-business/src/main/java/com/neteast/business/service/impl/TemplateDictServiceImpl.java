package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.template.TemplateDict;
import com.neteast.business.mapper.TemplateDictMapper;
import com.neteast.business.service.ITemplateDictService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年11月17 15:47
 */

@Service
public class TemplateDictServiceImpl extends ServiceImpl<TemplateDictMapper, TemplateDict> implements ITemplateDictService {
}
