package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.template.TemplateFile;
import com.neteast.business.domain.template.vo.TemplateFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月13 18:17
 */

@Mapper
public interface TemplateFileMapper extends BaseMapper<TemplateFile> {

    List<TemplateFileVO> getList(TemplateFileVO templateFile);
}
