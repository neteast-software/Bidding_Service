package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.PackageInformation;
import com.neteast.business.domain.project.ProjectCondition;
import com.neteast.business.domain.project.ProjectScoreItem;
import com.neteast.business.domain.project.vo.PackageInformationVO;
import com.neteast.business.domain.template.ItemTemplate;
import com.neteast.business.mapper.PackageInformationMapper;
import com.neteast.business.service.IItemTemplateService;
import com.neteast.business.service.IPackageInformationService;
import com.neteast.business.service.IProjectPlusConditionService;
import com.neteast.business.service.IProjectScoreItemService;
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

    @Resource
    PackageInformationMapper packageInformationMapper;

    @Resource
    IItemTemplateService itemTemplateService;

    @Resource
    IProjectScoreItemService scoreItemService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean savePackageInformation(PackageInformationVO packageInformationVO) {
        PackageInformation information = PackageInformationVO.convert(packageInformationVO);
        List<ProjectCondition> conditions = packageInformationVO.getConditions();
        //this.save(information);
        packageInformationMapper.insert(information);
        //附加项
        Integer packageId = information.getId();
        Integer projectId = information.getProjectId();
        conditions.forEach(c->{
            c.setProjectId(projectId);
            c.setPackageId(packageId);
        });
        conditionService.saveBatch(conditions);
        //该评分类型下的评分项
        setPackageScoreItem(information.getScoreId(),projectId,packageId);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delPackageInformation(Integer id) {
        PackageInformation information = getById(id);
        Integer packageId = information.getId();
        Integer projectId = information.getProjectId();
        //包信息删除
        this.removeById(information);
        //附加项信息删除
        QueryWrapper<ProjectCondition> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("package_id",packageId).eq("project_id",projectId);
        conditionService.remove(queryWrapper);
        //评分项信息删除
        delPackageScoreItem(projectId,packageId);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePackageInformation(PackageInformationVO packageInformationVO) {
        PackageInformation after = PackageInformationVO.convert(packageInformationVO);
        PackageInformation before = getById(after.getId());
        Integer packageId = after.getId();
        Integer projectId = after.getProjectId();
        //评分项信息更新
        if (after.getScoreId().compareTo(before.getScoreId())!=0){
            delPackageScoreItem(packageId,projectId);
        }
        setPackageScoreItem(after.getScoreId(),projectId,packageId);
        //包信息更新
        this.updateById(after);
        //附件项信息更新
        QueryWrapper<ProjectCondition> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("package_id",after.getId()).eq("project_id",after.getProjectId());
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

    private void delPackageScoreItem(Integer projectId,Integer packageId){
        List<ProjectScoreItem> scoreItems = scoreItemService.getProjectScoreItemList(projectId,packageId);
        scoreItems.forEach(item->{
            scoreItemService.removeProjectScoreItem(item.getId());
        });
    }

    private void setPackageScoreItem(Integer scoreId,Integer projectId,Integer packageId){

        List<ItemTemplate> itemTemplate = itemTemplateService.getItemTemplateListById(scoreId);
        List<ProjectScoreItem> items = new ArrayList<>();
        itemTemplate.forEach(item->{
            ProjectScoreItem projectScoreItem = ItemTemplate.covert(item);
            projectScoreItem.setProjectId(projectId);
            projectScoreItem.setPackageId(packageId);
            items.add(projectScoreItem);
        });
        scoreItemService.saveBatch(items);
    }
}
