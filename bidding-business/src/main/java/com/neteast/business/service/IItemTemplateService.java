package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.template.ItemTemplate;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月15 18:39
 */
public interface IItemTemplateService extends IService<ItemTemplate> {

    List<ItemTemplate> getItemTemplateList(ItemTemplate itemTemplate);
}
