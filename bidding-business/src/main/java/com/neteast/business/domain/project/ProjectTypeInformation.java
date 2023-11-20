package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 项目类型数据
 * @author lzp
 * @date 2023年11月15 11:21
 */

@Data
public class ProjectTypeInformation extends BaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id ;

    /** 项目id */
    @TableField("project_id")
    private Integer projectId ;

    /** 甲方公司id */
    @TableField("partya_id")
    private Integer partyaId ;

    /** 项目名称 */
    @TableField("project_name")
    private String projectName ;

    /** 项目行业 */
    @TableField("project_type")
    private String projectType ;

    /** 项目编号 */
    @TableField("project_code")
    private String projectCode;

    /** 采购方式 */
    @TableField("procure_type")
    private String procureType;

    public static ProjectTypeInformation convert(ProjectInformation project){
        ProjectTypeInformation type = new ProjectTypeInformation();
        type.setProjectId(project.getId());
        type.setPartyaId(project.getPartyaId());
        type.setProjectName(project.getProjectName());
        type.setProjectType(project.getProjectType());
        type.setProjectCode(project.getProjectCode());
        type.setProcureType(project.getProcureType());
        return type;
    }

}
