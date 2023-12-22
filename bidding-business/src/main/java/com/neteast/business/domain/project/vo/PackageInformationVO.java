package com.neteast.business.domain.project.vo;

import cn.hutool.core.bean.BeanUtil;
import com.neteast.business.domain.project.PackageInformation;
import com.neteast.business.domain.project.ProjectCondition;
import com.neteast.business.domain.project.ProjectScoreItem;
import lombok.Data;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月18 14:20
 */

@Data
public class PackageInformationVO {

    private Integer id;

    /** 对应甲方项目id */
    private Integer projectId;

    /** 评分方式id */
    private Integer scoreId;

    /** 分包名称 */
    private String packageName;

    /** 分包预算金额 */
    private Double budgetAmount;

    /** 分包最大金额限额 */
    private Double maxAmount;

    /** 合同号 */
    private String contractPackage;

    /** 品目号 */
    private String packageNum;

    /** 包单位;包需要完成内容的数量,如项,个等单位等 */
    private String packageUnit;

    /** 流标次数 */
    private Integer failBiddingCount;

    /** 采购标的 */
    private String biddingSubject;

    /** 保证金额 */
    private Double earnest;

    /** 分包附加项 */
    private List<ProjectCondition> conditions;

    private List<ProjectScoreItem> scoreItems;

    public static PackageInformation convert(PackageInformationVO packageInformationVO){
        PackageInformation packageInformation = new PackageInformation();
        BeanUtil.copyProperties(packageInformationVO,packageInformation);
        return packageInformation;
    }

    public static PackageInformationVO convert(PackageInformation packageInformation){
        PackageInformationVO informationVO = new PackageInformationVO();
        BeanUtil.copyProperties(packageInformation,informationVO);
        return informationVO;
    }

}
