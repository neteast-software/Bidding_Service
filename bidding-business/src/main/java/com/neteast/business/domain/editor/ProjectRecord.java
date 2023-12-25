package com.neteast.business.domain.editor;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

/**
 * 项目编辑修改记录
 * @author lzp
 * @date 2023年12月25 10:29
 */

@Data
@TableName("project_record")
public class ProjectRecord {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 项目id */
    @TableField("project_id")
    private Integer projectId;

    /** 文件id */
    @TableField("file_id")
    private Integer fileId;

    /** 文件名称 */
    @TableField("file_name")
    private String fileName;

    /** 操作说明 */
    @TableField("opera")
    private String opera;

    /** 操作类型(1-新增,2-更新,3-删除,4-保存) */
    @TableField("opera_type")
    private Integer operaType;

    /** 操作时间 */
    @TableField("opera_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operaTime;

    /** 操作人id */
    @TableField("user_id")
    private Long userId;

    /** 操作人名称 */
    @TableField("opera_user")
    private String operaUser;
}
