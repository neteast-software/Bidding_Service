package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.template.TemplateFile;
import com.neteast.business.mapper.TemplateFileMapper;
import com.neteast.business.service.ITemplateFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 魔模板文件内容
 * @author lzp
 * @date 2023年12月13 18:18
 */

@Service
public class TemplateFileServiceImpl extends ServiceImpl<TemplateFileMapper, TemplateFile> implements ITemplateFileService {

    @Resource
    TemplateFileMapper templateFileMapper;

    @Override
    public List<TemplateFile> getTemplateFileList(TemplateFile templateFile) {
        return templateFileMapper.getList(templateFile);
    }
}
