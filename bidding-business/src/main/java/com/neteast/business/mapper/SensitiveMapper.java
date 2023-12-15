package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.dict.Sensitive;
import com.neteast.business.domain.dict.vo.SensitiveVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 敏感词
 * @author lzp
 * @date 2023年12月15 10:43
 */

@Mapper
public interface SensitiveMapper extends BaseMapper<Sensitive> {

    List<SensitiveVO> getList(SensitiveVO sensitiveVO);
}
