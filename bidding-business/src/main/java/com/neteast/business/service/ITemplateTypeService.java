package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.template.TemplateType;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 13:56
 */
public interface ITemplateTypeService extends IService<TemplateType> {

    List<TemplateType> getTemplateTypeList(TemplateType templateType);
}
