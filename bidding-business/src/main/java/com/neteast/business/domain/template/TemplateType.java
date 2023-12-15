package com.neteast.business.domain.template;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 模板类型 文件为单位
 * @author lzp
 * @date 2023年11月17 13:37
 */

@Data
@TableName("template_type")
public class TemplateType extends BaseEntity {

    /** 主键 */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 模板类型 */
    @TableField("type")
    private String type;

    /** 模板数量 */
    @TableField("num")
    private Integer num;

    /** 模板招标类型 */
    @TableField("bidding_type")
    private String biddingType;

    /** 模板的所属项目类型 */
    @TableField("project_type")
    private String projectType;

    public void changeNum(int change){
        this.num = this.num + change;
    }
}
