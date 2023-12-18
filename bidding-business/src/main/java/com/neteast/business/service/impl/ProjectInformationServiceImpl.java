package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.PackageInformation;
import com.neteast.business.domain.project.ProjectInformation;
import com.neteast.business.domain.project.vo.PackageInformationVO;
import com.neteast.business.domain.project.vo.ProjectInformationVO;
import com.neteast.business.mapper.ProjectInformationMapper;
import com.neteast.business.service.IPackageInformationService;
import com.neteast.common.exception.BaseBusException;
import org.springframework.stereotype.Service;
import com.neteast.business.service.IProjectInformationService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月15 11:56
 */

@Service
public class ProjectInformationServiceImpl extends ServiceImpl<ProjectInformationMapper, ProjectInformation> implements IProjectInformationService {

    @Resource
    ProjectInformationMapper projectInformationMapper;

    @Resource
    IPackageInformationService packageInformationService;

    @Override
    public List<ProjectInformation> getProjectInformationList(ProjectInformation projectInformation) {
        projectInformation.setProjectDel(1);
        return projectInformationMapper.getList(projectInformation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProjectInformation(ProjectInformationVO projectInformationVO) {

        ProjectInformation projectInformation = ProjectInformation.convert(projectInformationVO);
        return this.updateById(projectInformation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addProjectInformation(ProjectInformationVO projectInformationVO) {

        ProjectInformation projectInformation = ProjectInformationVO.convert(projectInformationVO);
        List<ProjectInformation> list = lambdaQuery().eq(ProjectInformation::getProjectCode,projectInformation.getProjectCode()).list();
        if (list.size()==0){
            save(projectInformation);
            List<PackageInformationVO> packageInformationList = projectInformationVO.getPackageInformationList();
            packageInformationList.forEach(p->{
                packageInformationService.savePackageInformation(p);
            });
        }
        throw new BaseBusException("项目编号重复");

    }
}
