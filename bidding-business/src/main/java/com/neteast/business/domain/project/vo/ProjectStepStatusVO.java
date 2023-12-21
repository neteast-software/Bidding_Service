package com.neteast.business.domain.project.vo;

import com.neteast.business.domain.project.ProjectInformation;
import com.neteast.business.domain.project.ProjectStage;
import lombok.Data;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月21 16:58
 */

@Data
public class ProjectStepStatusVO {

    /** 项目id */
    private Integer projectId;

    /** 项目名称 */
    private String projectName;

    /** 项目类型 */
    private String projectTypeName;

    /** 项目类型id */
    private Integer projectTypeId;

    /** 项目步骤状态 */
    private Integer projectStatus;

    /** 项目步骤列表 */
    private List<ProjectStage> projectStages;

    public static ProjectStepStatusVO convert(ProjectInformation information){
        ProjectStepStatusVO status = new ProjectStepStatusVO();
        status.setProjectId(information.getId());
        status.setProjectName(information.getProjectName());
        status.setProjectTypeId(information.getProjectTypeId());
        status.setProjectTypeName(information.getProjectTypeName());
        return status;
    }
}
