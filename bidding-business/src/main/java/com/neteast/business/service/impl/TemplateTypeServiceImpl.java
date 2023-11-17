package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.template.TemplateType;
import com.neteast.business.mapper.TemplateTypeMapper;
import com.neteast.business.service.ITemplateTypeService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年11月17 14:02
 */

@Service
public class TemplateTypeServiceImpl extends ServiceImpl<TemplateTypeMapper, TemplateType> implements ITemplateTypeService {
}
