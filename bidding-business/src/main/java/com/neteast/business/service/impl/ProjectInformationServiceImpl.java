package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.FailBiddingMsg;
import com.neteast.business.domain.project.ProjectInformation;
import com.neteast.business.domain.project.ProjectTypeInformation;
import com.neteast.business.domain.project.vo.ProjectInformationVO;
import com.neteast.business.mapper.ProjectInformationMapper;
import com.neteast.business.service.IFailBiddingMsgService;
import com.neteast.business.service.IProjectTypeInformationService;
import com.neteast.common.exception.BaseBusException;
import com.neteast.common.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import com.neteast.business.service.IProjectInformationService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月15 11:56
 */

@Service
public class ProjectInformationServiceImpl extends ServiceImpl<ProjectInformationMapper, ProjectInformation> implements IProjectInformationService {

    @Resource
    IFailBiddingMsgService failBiddingMsgService;

    @Resource
    IProjectTypeInformationService projectTypeInformationService;

    @Resource
    ProjectInformationMapper projectInformationMapper;

    @Override
    public List<ProjectInformation> getProjectInformationList(ProjectInformation projectInformation) {
        projectInformation.setProjectDel(1);
        return projectInformationMapper.getList(projectInformation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProjectInformation(ProjectInformationVO projectInformationVO) {

        ProjectInformation projectInformation = ProjectInformation.convert(projectInformationVO);
        //流标
        if (projectInformationVO.getProjectStatus()==2){
            ProjectInformation info = lambdaQuery().eq(ProjectInformation::getId,projectInformationVO.getId()).one();
            FailBiddingMsg failBiddingMsg = FailBiddingMsg.convert(projectInformationVO);
            failBiddingMsgService.addProjectBiddingMsgData(failBiddingMsg);
            int count = info.getFailBiddingCount()+1;
            projectInformation.setFailBiddingCount(count);
            this.updateById(projectInformation);
        }
        //招标成功
        else if (projectInformationVO.getProjectStatus()==3){
            this.updateById(projectInformation);
        }
        return this.updateById(projectInformation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addProjectInformation(ProjectInformation projectInformation) {

        List<ProjectInformation> list = lambdaQuery().eq(ProjectInformation::getProjectCode,projectInformation.getProjectCode()).list();
        if (list.size()==0){
            save(projectInformation);
            ProjectTypeInformation information = ProjectTypeInformation.convert(projectInformation);
            return projectTypeInformationService.save(information);
        }
        throw new BaseBusException("项目编号重复");
    }
}
