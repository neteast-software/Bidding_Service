package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.template.ItemTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月15 18:37
 */

@Mapper
public interface ItemTemplateMapper extends BaseMapper<ItemTemplate> {

    List<ItemTemplate> getList(ItemTemplate itemTemplate);
}
