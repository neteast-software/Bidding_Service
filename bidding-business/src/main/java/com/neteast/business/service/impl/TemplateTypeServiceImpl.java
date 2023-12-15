package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.template.TemplateType;
import com.neteast.business.domain.template.vo.TemplateTypeVO;
import com.neteast.business.mapper.TemplateTypeMapper;
import com.neteast.business.service.ITemplateFileService;
import com.neteast.business.service.ITemplateTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 14:02
 */

@Service
public class TemplateTypeServiceImpl extends ServiceImpl<TemplateTypeMapper, TemplateType> implements ITemplateTypeService {

    @Resource
    TemplateTypeMapper templateTypeMapper;

    @Resource
    ITemplateFileService templateFileService;

    @Override
    public List<TemplateTypeVO> getTemplateTypeList(TemplateTypeVO templateType) {
        return templateTypeMapper.getList(templateType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delTemplateType(Integer id) {
        this.removeById(id);
        templateFileService.delTemplateFileByExtId(id);
        return true;
    }
}
