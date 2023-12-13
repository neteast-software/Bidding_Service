package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.template.TemplateFile;
import com.neteast.business.mapper.TemplateFileMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月13 18:18
 */
public interface ITemplateFileService extends IService<TemplateFile> {

    List<TemplateFile> getTemplateFileList(TemplateFile templateFile);
}
