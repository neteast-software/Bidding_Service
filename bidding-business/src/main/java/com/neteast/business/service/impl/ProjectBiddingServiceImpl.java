package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectBidding;
import com.neteast.business.domain.project.vo.ProjectBiddingVO;
import com.neteast.business.mapper.ProjectBiddingMapper;
import com.neteast.business.service.IProjectBiddingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 15:44
 */

@Service
public class ProjectBiddingServiceImpl extends ServiceImpl<ProjectBiddingMapper, ProjectBidding> implements IProjectBiddingService {

    @Resource
    ProjectBiddingMapper projectBiddingMapper;

    @Override
    public List<ProjectBiddingVO> getProjectBiddingList(ProjectBiddingVO projectBiddingVO) {
        return projectBiddingMapper.getList(projectBiddingVO);
    }
}
