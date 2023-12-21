package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.custom.AgencyMessage;
import com.neteast.business.domain.custom.PurchaserMessage;
import com.neteast.business.domain.project.PackageInformation;
import com.neteast.business.domain.project.ProjectInformation;
import com.neteast.business.domain.project.ProjectType;
import com.neteast.business.domain.project.vo.PackageInformationVO;
import com.neteast.business.domain.project.vo.ProjectInformationVO;
import com.neteast.business.mapper.AgencyMessageMapper;
import com.neteast.business.mapper.ProjectInformationMapper;
import com.neteast.business.mapper.PurchaserMessageMapper;
import com.neteast.business.service.*;
import com.neteast.common.exception.BaseBusException;
import org.springframework.stereotype.Service;
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
    ProjectInformationMapper projectInformationMapper;

    @Resource
    IPackageInformationService packageInformationService;

    @Resource
    IProjectTypeService projectTypeService;

    @Resource
    AgencyMessageMapper agencyMessageMapper;

    @Resource
    PurchaserMessageMapper purchaserMessageMapper;

    @Resource
    IRoomStatusService roomStatusService;

    @Override
    public List<ProjectInformation> getProjectInformationList(ProjectInformation projectInformation) {
        projectInformation.setProjectDel(1);
        return projectInformationMapper.getList(projectInformation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProjectInformation(ProjectInformationVO projectInformationVO) {

        ProjectInformation after = ProjectInformation.convert(projectInformationVO);
        ProjectInformation before = getById(after.getId());
        if (before.getProjectTypeId().compareTo(after.getProjectTypeId())!=0){
            ProjectType typeAfter = projectTypeService.getById(after.getProjectTypeId());
            ProjectType typeBefore = projectTypeService.getById(before.getProjectTypeId());
            typeAfter.changeNum(1);
            typeBefore.changeNum(-1);
            projectTypeService.updateById(typeBefore);
            projectTypeService.updateById(typeAfter);
            after.setProjectTypeName(typeAfter.getName());
        }
        return this.updateById(after);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addProjectInformation(ProjectInformationVO projectInformationVO) {

        ProjectInformation projectInformation = ProjectInformationVO.convert(projectInformationVO);
        List<ProjectInformation> list = lambdaQuery().eq(ProjectInformation::getProjectCode,projectInformation.getProjectCode()).list();
        if (list.size()==0){
            //代理商信息
            AgencyMessage agencyMessage = projectInformationVO.getAgencyMessage();
            setAgencyMessage(agencyMessage,projectInformation);
            //甲方信息
            PurchaserMessage purchaserMessage = projectInformationVO.getPurchaserMessage();
            setPurchaserMessage(purchaserMessage,projectInformation);
            ProjectType projectType = projectTypeService.getById(projectInformation.getProjectTypeId());
            if (projectType==null){
                throw new BaseBusException("无该招标类型");
            }
            projectInformation.setProjectTypeName(projectType.getName());
            projectInformation.setStatusTime(new Date());
            //save(projectInformation);
            projectInformationMapper.insert(projectInformation);
            Integer projectId = projectInformation.getId();
            //该项目类型num添加
            projectType.changeNum(1);
            projectTypeService.updateById(projectType);
            List<PackageInformationVO> packageInformationList = projectInformationVO.getPackageInformationList();
            if (packageInformationList!=null){
                packageInformationList.forEach(p->{
                    p.setProjectId(projectId);
                    packageInformationService.savePackageInformation(p);
                });
            }
            return true;
        }
        throw new BaseBusException("项目编号重复");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delProjectInformation(Integer id) {

        //项目删除
        ProjectInformation projectInformation = this.getById(id);
        projectInformation.setProjectDel(0);
        this.updateById(projectInformation);
        //更新项目类型
        ProjectType projectType = projectTypeService.getById(projectInformation.getProjectTypeId());
        projectType.changeNum(-1);
        projectTypeService.updateById(projectType);
        //删除项目会议室信息
        roomStatusService.removeRoomStatusByProjectId(id);
        return true;
    }

    private void setPurchaserMessage(PurchaserMessage purchaserMessage,ProjectInformation projectInformation){

        if (purchaserMessage==null){
            return;
        }
        if (purchaserMessage.getId()!=null){
            projectInformation.setPartyaId(purchaserMessage.getId());
            PurchaserMessage before = purchaserMessageMapper.selectById(purchaserMessage.getId());
            if (purchaserMessage.compare(before)){
                purchaserMessageMapper.updateById(purchaserMessage);
            }
        }else {
            //添加甲方信息
            purchaserMessageMapper.insert(purchaserMessage);
            Integer id = purchaserMessage.getId();
            projectInformation.setPartyaId(id);
        }
    }

    private void setAgencyMessage(AgencyMessage agencyMessage,ProjectInformation projectInformation){

        if (agencyMessage==null){
            return;
        }
        if (agencyMessage.getId()!=null){
            projectInformation.setAgencyId(agencyMessage.getId());
            AgencyMessage before = agencyMessageMapper.selectById(agencyMessage.getId());
            if (agencyMessage.compare(before)){
                agencyMessageMapper.updateById(agencyMessage);
            }
        }else {
            //添加代理商信息返回id
            agencyMessageMapper.insert(agencyMessage);
            Integer id = agencyMessage.getId();
            projectInformation.setAgencyId(id);
        }
    }
}
