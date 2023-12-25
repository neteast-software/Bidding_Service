package com.neteast.business.domain.project.vo;

import com.neteast.business.domain.project.ProjectStage;
import lombok.Data;

/**
 * 项目阶段信息
 * @author lzp
 * @date 2023年12月25 15:52
 */

@Data
public class ProjectStageVO {

    /** 主键id */
    private Integer id;

    /** 项目类型 */
    private Integer projectTypeId;

    /** 步骤序号 */
    private Integer stepNum;

    /** 步骤名称 */
    private String stepName;

    /** 文件数量 */
    private Integer num = 0;

    public static ProjectStageVO convert(ProjectStage stage){
        ProjectStageVO vo = new ProjectStageVO();
        vo.setId(stage.getId());
        vo.setProjectTypeId(stage.getProjectTypeId());
        vo.setStepNum(stage.getStepNum());
        vo.setStepName(stage.getStepName());
        return vo;
    }
}
