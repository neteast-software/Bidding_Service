package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.template.TemplateFile;
import com.neteast.business.domain.template.vo.TemplateContent;
import com.neteast.business.domain.template.vo.TemplateFileVO;
import com.neteast.business.domain.template.vo.TemplateTypeVO;
import com.neteast.business.mapper.TemplateFileMapper;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月13 18:18
 */
public interface ITemplateFileService extends IService<TemplateFile> {

    List<TemplateFileVO> getTemplateFileList(TemplateFileVO templateFileVO);

    /**
     * @Description 创建模板文件
     * @author lzp
     * @Date 2023/12/14
     */
    boolean createTemplateFile(TemplateFileVO templateFile) throws IOException;

    /**
     * @Description 保存模板文件
     * @author lzp
     * @Date 2023/12/14
     */
    boolean saveTemplateFile(TemplateContent templateContent);
    
    /**
     * @Description 删除模板文件
     * @author lzp
     * @Date 2023/12/14
     */
    boolean delTemplateFile(Integer id);

    boolean delTemplateFileByExtId(Integer extId);

    boolean updateTemplateFile(TemplateFileVO templateFileVO);

}
