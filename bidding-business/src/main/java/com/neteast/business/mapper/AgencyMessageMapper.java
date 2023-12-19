package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.custom.AgencyMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月19 10:42
 */

@Mapper
public interface AgencyMessageMapper extends BaseMapper<AgencyMessage> {

    List<AgencyMessage> getList(AgencyMessage agencyMessage);
}
