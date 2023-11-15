package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.FailBiddingMsg;
import com.neteast.business.domain.ProjectInformation;
import com.neteast.business.mapper.ProjectInformationMapper;
import com.neteast.business.service.IFailBiddingMsgService;
import org.springframework.stereotype.Service;
import com.neteast.business.service.IProjectInformationService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    @Override
    public List<ProjectInformation> getProjectInformationList(ProjectInformation projectInformation) {
        return lambdaQuery().list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProjectInformation(ProjectInformation projectInformation) {

        if (projectInformation.getProjectStatus()==2){
            FailBiddingMsg failBiddingMsg = FailBiddingMsg.convert(projectInformation);
            failBiddingMsgService.addProjectBiddingMsgData(failBiddingMsg);
            this.updateById(projectInformation);
        }else if (projectInformation.getProjectStatus()==3){
            //设置中标公司信息
            this.updateById(projectInformation);
        }
        return this.updateById(projectInformation);
    }
}
