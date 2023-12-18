package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.PackageInformation;
import com.neteast.business.domain.project.ProjectCondition;
import com.neteast.business.domain.project.vo.PackageInformationVO;
import com.neteast.business.mapper.PackageInformationMapper;
import com.neteast.business.service.IPackageInformationService;
import com.neteast.business.service.IProjectPlusConditionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 14:00
 */

@Service
public class PackageInformationServiceImpl extends ServiceImpl<PackageInformationMapper, PackageInformation> implements IPackageInformationService {

    @Resource
    IProjectPlusConditionService conditionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean savePackageInformation(PackageInformationVO packageInformationVO) {
        PackageInformation information = PackageInformationVO.convert(packageInformationVO);
        List<ProjectCondition> conditions = packageInformationVO.getConditions();
        this.save(information);
        conditionService.saveBatch(conditions);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delPackageInformation(Integer id) {
        PackageInformation information = getById(id);
        //包信息删除
        this.removeById(information);
        //附加项信息删除
        QueryWrapper<ProjectCondition> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("package_id",information.getId()).eq("project_id",information.getProjectId());
        conditionService.remove(queryWrapper);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePackageInformation(PackageInformationVO packageInformationVO) {
        PackageInformation information = PackageInformationVO.convert(packageInformationVO);
        //包信息更新
        this.updateById(information);
        //附件项信息更新
        QueryWrapper<ProjectCondition> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("package_id",information.getId()).eq("project_id",information.getProjectId());
        conditionService.remove(queryWrapper);
        List<ProjectCondition> conditions = packageInformationVO.getConditions();
        conditionService.saveBatch(conditions);
        return true;
    }

    @Override
    public List<PackageInformationVO> getPackageInformationVOList(Integer projectId) {
        List<PackageInformation> list = this.lambdaQuery().eq(PackageInformation::getProjectId,projectId).list();
        List<PackageInformationVO> voList = new ArrayList<>();
        list.forEach(l->{
            PackageInformationVO informationVO = PackageInformationVO.convert(l);
            ProjectCondition projectCondition = ProjectCondition.builder().projectId(l.getProjectId()).packageId(l.getId()).build();
            List<ProjectCondition> conditions = conditionService.getProjectPlusConditionList(projectCondition);
            informationVO.setConditions(conditions);
            voList.add(informationVO);
        });
        return voList;
    }
}
