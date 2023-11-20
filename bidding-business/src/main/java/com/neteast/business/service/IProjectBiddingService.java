package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.ProjectBidding;
import com.neteast.business.domain.project.vo.ProjectBiddingVO;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 15:40
 */
public interface IProjectBiddingService extends IService<ProjectBidding> {

    List<ProjectBiddingVO> getProjectBiddingList(ProjectBiddingVO projectBiddingVO);
}
