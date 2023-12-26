package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.template.ItemTemplate;
import com.neteast.business.mapper.ItemTemplateMapper;
import com.neteast.business.service.IItemTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月15 18:40
 */

@Service
public class ItemTemplateServiceImpl extends ServiceImpl<ItemTemplateMapper, ItemTemplate> implements IItemTemplateService {

    @Resource
    ItemTemplateMapper templateMapper;


    @Override
    public List<ItemTemplate> getItemTemplateList(ItemTemplate itemTemplate) {
        return templateMapper.getList(itemTemplate);
    }

    @Override
    public boolean removeItemTemplate(Integer id) {

        this.removeById(id);
        return true;
    }

    @Override
    public List<ItemTemplate> getItemTemplateListById(Integer scoreId) {
        return this.lambdaQuery().eq(ItemTemplate::getScoreId,scoreId).list();
    }
}
