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

    /** 项目文件类型(如招标文件) */
    @TableField("type")
    private String type;

    /** 阶段(如开标前，开标等) */
    @TableField("stage")
    private Integer stage;

    /** 文件名称 */
    @TableField("name")
    private String name;

    /** 文件类型(excel,word) */
    @TableField("file_type")
    private String fileType;

    /** 文件地址 */
    @TableField("file_path")
    private String filePath;
}
