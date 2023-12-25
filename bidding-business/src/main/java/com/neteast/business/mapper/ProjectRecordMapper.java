package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.editor.ProjectRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目记录
 * @author lzp
 * @date 2023年12月25 10:46
 */

@Mapper
public interface ProjectRecordMapper extends BaseMapper<ProjectRecord> {
}
