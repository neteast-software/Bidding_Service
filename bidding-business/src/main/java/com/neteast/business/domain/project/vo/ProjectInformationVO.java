package com.neteast.business.domain.project.vo;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.neteast.business.domain.custom.AgencyMessage;
import com.neteast.business.domain.custom.PurchaserMessage;
import com.neteast.business.domain.dict.PlusesCondition;
import com.neteast.business.domain.project.PackageInformation;
import com.neteast.business.domain.project.ProjectInformation;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月20 16:06
 */

@Data
public class ProjectInformationVO {

    /** 主键id */
    private Integer id ;

    /** 对应甲方信息 */
    private PurchaserMessage purchaserMessage;

    /** 对应代理商信息 */
    private AgencyMessage agencyMessage;

    /** 项目编号 */
    private String projectCode ;

    /** 备案编号 **/
    private String filingsNumber;

    /** 项目行业 */
    private String projectIndustry ;

    /** 项目名称 */
    private String projectName ;

    /** 项目类型 */
    private Integer projectTypeId;

    /** 项目类型名称 */
    private String projectTypeName ;

    /** 项目状态(项目阶段管理状态) */
    private Integer projectStatus ;

    /** 招标时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inviteBidding;

    /** 开标时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date openBidding;

    /** 项目预算金额 */
    private Double budgetAmount ;

    /** 项目最大金额 **/
    private Double maxAmount;

    /** 流标次数 */
    private Integer failBiddingCount ;

    /** 项目删除，0-已删除,1-未删除 **/
    private Integer projectDel;

    /** 流标原因 */
    private String failReason;

    /** 保证金额 */
    private Double earnest;

    private List<PackageInformationVO> packageInformationList;

    public static ProjectInformationVO convert(ProjectInformation projectInformation){
        ProjectInformationVO projectInformationVO = new ProjectInformationVO();
        BeanUtil.copyProperties(projectInformation,projectInformationVO);
        return projectInformationVO;
    }

    public static ProjectInformation convert(ProjectInformationVO projectInformationVO){
        ProjectInformation projectInformation = new ProjectInformation();
        BeanUtil.copyProperties(projectInformationVO,projectInformation);
        return projectInformation;
    }
}
