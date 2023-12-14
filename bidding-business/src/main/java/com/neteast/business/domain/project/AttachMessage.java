package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 项目关联附件信息
 * @author lzp
 * @date 2023年11月17 11:22
 */

@Data
@TableName("attach_message")
public class AttachMessage extends BaseEntity{

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 附件名称 */
    @TableField("name")
    private String name;

    /** 附件地址url */
    @TableField("address")
    private String address;

    /** 关联项目id */
    @TableField("project_id")
    private Integer projectId;

    /** 关联分包id */
    @TableField("package_id")
    private Integer packageId ;

    /** 文件类型(如开标文件) */
    @TableField("file_type")
    private String fileType;

    /** 附件类型 */
    @TableField("type")
    private String type;

    /** 附件大小 */
    @TableField("size")
    private Long size;

    /** 下载次数 */
    @TableField("num")
    private Integer num;

    /** 文件的存储路径 */
    @TableField("file_path")
    private String filePath;

}
