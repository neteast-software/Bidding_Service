package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.AttachMessage;
import com.neteast.business.domain.project.vo.AttachMessageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 15:38
 */

@Mapper
public interface AttachMessageMapper extends BaseMapper<AttachMessage> {

    List<AttachMessageVO> getList(AttachMessageVO attachMessageVO);
}
