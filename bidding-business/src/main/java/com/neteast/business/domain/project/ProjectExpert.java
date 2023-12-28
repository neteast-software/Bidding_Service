package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 项目专家信息
 * @author lzp
 * @date 2023年11月17 14:47
 */

@Data
@TableName("project_expert")
public class ProjectExpert extends BaseEntity{

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 项目id */
    @TableField("project_id")
    private Integer projectId;

    /** 分包id */
    @TableField("package_id")
    private Integer packageId;

    /** 专家id */
    @TableField("expert_id")
    private Integer expertId;

    /** 专家操作当前阶段id */
    @TableField("step_id")
    private Integer stepId;

    /** 是否是组长 true表示是，false表示否 */
    @TableField("leader")
    private Boolean leader;
}
