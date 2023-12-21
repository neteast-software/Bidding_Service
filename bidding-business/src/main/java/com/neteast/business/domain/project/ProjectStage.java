package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目阶段管理
 * @author lzp
 * @date 2023年12月21 15:27
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("project_stage")
public class ProjectStage extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 项目类型 */
    @TableField("project_type_id")
    private Integer projectTypeId;

    /** 步骤序号 */
    @TableField("step_num")
    private Integer stepNum;

    /** 步骤名称 */
    @TableField("step_name")
    private String stepName;

    /** 关联模板id */
    @TableField("template_id")
    private Integer templateId;

    /** 关联模板名称 */
    @TableField("template_name")
    private String templateName;
}
