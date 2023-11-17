package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.custom.ExpertMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 13:49
 */

@Mapper
public interface ExpertMessageMapper extends BaseMapper<ExpertMessage> {

    List<ExpertMessage> getList(ExpertMessage expertMessage);
}
