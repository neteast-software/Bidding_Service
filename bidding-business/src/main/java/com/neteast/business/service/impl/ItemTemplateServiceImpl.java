package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.template.ItemTemplate;
import com.neteast.business.mapper.ItemTemplateMapper;
import com.neteast.business.service.IItemTemplateService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年12月15 18:40
 */

@Service
public class ItemTemplateServiceImpl extends ServiceImpl<ItemTemplateMapper, ItemTemplate> implements IItemTemplateService {
}
