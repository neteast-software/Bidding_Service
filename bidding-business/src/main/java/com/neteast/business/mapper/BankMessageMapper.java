package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.custom.BankMessage;
import com.neteast.business.domain.custom.vo.BankMessageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 13:48
 */
@Mapper
public interface BankMessageMapper extends BaseMapper<BankMessage> {

    List<BankMessageVO> getList(BankMessageVO bankMessageVO);
}
