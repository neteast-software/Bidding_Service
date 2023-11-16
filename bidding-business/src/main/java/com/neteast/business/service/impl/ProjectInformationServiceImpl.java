package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.FailBiddingMsg;
import com.neteast.business.domain.ProjectInformation;
import com.neteast.business.domain.ProjectTypeInformation;
import com.neteast.business.mapper.ProjectInformationMapper;
import com.neteast.business.service.IFailBiddingMsgService;
import com.neteast.business.service.IProjectTypeInformationService;
import org.springframework.stereotype.Service;
import com.neteast.business.service.IProjectInformationService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Override
    public List<ProjectInformation> getProjectInformationList(ProjectInformation projectInformation) {
        return lambdaQuery().list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProjectInformation(ProjectInformation projectInformation) {

        projectInformation.setUpdateTime(new Date());
        if (projectInformation.getProjectStatus()==2){
            ProjectInformation info = lambdaQuery().eq(ProjectInformation::getId,projectInformation.getId()).one();
            info.setFailReason(projectInformation.getFailReason());
            FailBiddingMsg failBiddingMsg = FailBiddingMsg.convert(info);
            failBiddingMsgService.addProjectBiddingMsgData(failBiddingMsg);
            int count = info.getFailBiddingCount()+1;
            projectInformation.setFailBiddingCount(count);
            this.updateById(projectInformation);
        }else if (projectInformation.getProjectStatus()==3){
            //设置中标公司信息
            this.updateById(projectInformation);
        }
        return this.updateById(projectInformation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addProjectInformation(ProjectInformation projectInformation) {

        List<ProjectInformation> list = lambdaQuery().eq(ProjectInformation::getProjectCode,projectInformation.getProjectCode()).list();
        if (list.size()==0){
            projectInformation.setCreateTime(new Date());
            save(projectInformation);
            List<ProjectInformation> temp = this.lambdaQuery().eq(ProjectInformation::getProjectCode,projectInformation.getProjectCode()).list();
            if (projectInformation.getParentId()==null&&temp.size()==1){
                ProjectTypeInformation information = ProjectTypeInformation.convert(projectInformation);
                projectTypeInformationService.save(information);
            }
        }
        return true;
    }
}
