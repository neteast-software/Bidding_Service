package com.neteast.business.service.impl;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.template.TemplateFile;
import com.neteast.business.domain.template.vo.TemplateFileVO;
import com.neteast.business.mapper.TemplateFileMapper;
import com.neteast.business.service.ITemplateFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 魔模板文件内容
 * @author lzp
 * @date 2023年12月13 18:18
 */

@Service
@Slf4j
public class TemplateFileServiceImpl extends ServiceImpl<TemplateFileMapper, TemplateFile> implements ITemplateFileService {

    @Resource
    TemplateFileMapper templateFileMapper;

    @Value("${ruoyi.templateFilePath}")
    String filePath;

    @Override
    public List<TemplateFileVO> getTemplateFileList(TemplateFileVO templateFile) {
        return templateFileMapper.getList(templateFile);
    }

    @Override
    public boolean createTemplateFile(TemplateFileVO templateFileVO) throws IOException {

        TemplateFile templateFile = TemplateFileVO.convert(templateFileVO);
        String filename = UUID.randomUUID().toString().replace("-","");
        String path = filePath+filename+".txt";
        File file = new File(path);
        if (!file.exists()){
            boolean res = file.createNewFile();
            if (!res){
                log.info("文件创建失败-{}",path);
                return false;
            }
        }
        templateFile.setFilePath(path);
        return this.save(templateFile);
    }

    @Override
    public boolean saveTemplateFile(TemplateFileVO templateFileVO) {

        TemplateFile templateFile = TemplateFileVO.convert(templateFileVO);
        TemplateFile temp = getById(templateFileVO.getId());
        templateFile.setFilePath(temp.getFilePath());
        String content = templateFileVO.getContent();
        if (content==null){
            content = "";
        }
        String path = templateFile.getFilePath();
        FileUtil.writeUtf8String(content,new File(path));
        return this.updateById(templateFile);
    }

    @Override
    public boolean delTemplateFile(Integer id) {

        TemplateFile templateFile = this.getById(id);
        if (templateFile==null){
            return false;
        }
        String path = templateFile.getFilePath();
        File file = new File(path);
        if (!file.exists()){
            log.info("该删除文件不存在-{}",path);
            return this.removeById(id);
        }
        boolean res = file.delete();
        if (!res){
            log.info("文件删除失败");
            return false;
        }
        return this.removeById(id);
    }
}
