package com.neteast.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * @author lzp
 * @date 2023年11月15 11:21
 */

@Data
public class ProjectTypeInformation extends BaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id ;

    /** 项目类型 */
    @TableField("project_type")
    private String projectType ;

    /** 甲方公司id */
    @TableField("partya_id")
    private Integer partyaId ;

    /** 项目id */
    @TableField("project_id")
    private Integer projectId ;

    /** 甲方公司名称 */
    @TableField("partya_name")
    private String partyaName ;

    /** 项目名称 */
    @TableField("project_name")
    private String projectName ;

    public static ProjectTypeInformation convert(ProjectInformation project){
        ProjectTypeInformation type = new ProjectTypeInformation();
        type.setProjectType(project.getProjectType());
        type.setPartyaId(project.getPartyaId());
        type.setProjectId(project.getId());
        type.setPartyaName(project.getCompanyName());
        type.setProjectName(project.getProjectName());
        return type;
    }

}
