package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.PackageInformation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lzp
 * @date 2023年11月17 13:51
 */

@Mapper
public interface PackageInformationMapper extends BaseMapper<PackageInformation> {
}
