package com.neteast.business.domain.template;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 模板文件内容 以文件内内容为单位
 * @author lzp
 * @date 2023年12月13 17:51
 */

@Data
@TableName("template_file")
public class TemplateFile extends BaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 模板类型信息id */
    @TableField("ext_id")
    private Integer extId;

    /** 文件内容分类名称 */
    @TableField("unit")
    private String unit;

    /** 模板的使用次数 */
    @TableField("use_count")
    private Integer useCount = 0;

    /** 模板的名称 */
    @TableField("name")
    private String name;

    /** 是否公开 */
    @TableField("open")
    private boolean open;

    /** 归属 1-公司库 2-个人库 */
    @TableField("belong")
    private Integer belong;

    /** 文件路径 */
    @TableField("file_path")
    private String filePath;

}
