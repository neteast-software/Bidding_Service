package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.custom.ContractMessage;
import com.neteast.business.domain.custom.vo.ContractMessageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 13:48
 */

@Mapper
public interface ContractMessageMapper extends BaseMapper<ContractMessage> {

    List<ContractMessageVO> getList(ContractMessageVO contractMessageVO);
}
