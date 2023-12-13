package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.editor.ProjectBidding;
import com.neteast.business.domain.project.vo.ProjectBiddingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 15:37
 */

@Mapper
public interface ProjectBiddingMapper extends BaseMapper<ProjectBidding> {

    List<ProjectBidding> getList(ProjectBidding projectBidding);
}
