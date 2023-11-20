package com.neteast.business.domain.project.vo;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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

    /** 对应甲方id */
    private Integer partyaId ;

    /** 项目编号 */
    private String projectCode ;

    /** 备案编号 **/
    private String filingsNumber;

    /** 项目行业 */
    private String projectType ;

    /** 项目名称 */
    private String projectName ;

    /** 采购类型(项目类型) */
    private String procureType ;

    /** 项目状态 1-招标 2-流标 3-结束 */
    private Integer projectStatus ;

    /** 招标时间 */
    private Date inviteBidding;

    /** 开标时间 */
    private Date openBidding;

    /** 项目预算金额 */
    private Double budgetAmount ;

    /** 项目最大金额 **/
    private Double maxAmount;

    /** 流标次数 */
    private Integer failBiddingCount ;

    /** 项目删除，0-已删除,1-未删除 **/
    private Integer projectDel;

    private List<PackageInformation> packageInformationList;

    public static ProjectInformationVO convert(ProjectInformation projectInformation){
        ProjectInformationVO projectInformationVO = new ProjectInformationVO();
        BeanUtil.copyProperties(projectInformation,projectInformationVO);
        return projectInformationVO;
    }
}
