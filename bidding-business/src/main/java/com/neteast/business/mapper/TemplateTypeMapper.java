package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.template.TemplateType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 13:52
 */

@Mapper
public interface TemplateTypeMapper extends BaseMapper<TemplateType> {

    List<TemplateType> getList(TemplateType templateType);
}
