package com.neteast.business.domain.project.vo;

import com.neteast.business.domain.project.ProjectType;
import lombok.Data;

/**
 * @author lzp
 * @date 2023年12月18 17:23
 */

@Data
public class ProjectTypeVO {

    /** 主键id */
    private Integer id;

    /** 项目行业名称 */
    private String name;

    /** 项目数量 */
    private Integer num;

    public static ProjectType convert(ProjectTypeVO projectTypeVO){
        ProjectType projectType = new ProjectType();
        projectType.setId(projectTypeVO.getId());
        projectType.setName(projectTypeVO.getName());
        return projectType;
    }
}
