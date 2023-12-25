package com.neteast.business.domain.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 项目阶段完成情况
 * @author lzp
 * @date 2023年12月25 14:57
 */

@Data
public class ProjectStepCompletionVO {

    /** 项目类型 */
    private Integer projectTypeId;

    /** 步骤序号 */
    private Integer stepNum;

    /** 步骤名称 */
    private String stepName;

    /** 关联模板id */
    private Integer templateId;

    /** 关联模板名称 */
    private String templateName;

    /** 阶段时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stageTime;

    /** 是否已经完成 */
    private Boolean done;
}
