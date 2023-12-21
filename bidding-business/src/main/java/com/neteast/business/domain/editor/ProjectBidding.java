package com.neteast.business.domain.editor;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 项目标书文件
 * @author lzp
 * @date 2023年11月17 14:38
 */

@Data
@TableName("project_bidding")
public class ProjectBidding extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 项目id */
    @TableField("project_id")
    private Integer projectId;

    /** 阶段id */
    @TableField("stage_id")
    private Integer stageId;

    /** 阶段名称 */
    @TableField("stage_name")
    private String stageName;

    /** 文件名称 */
    @TableField("file_name")
    private String fileName;

    /** 文件地址 */
    @TableField("file_path")
    private String filePath;
}
