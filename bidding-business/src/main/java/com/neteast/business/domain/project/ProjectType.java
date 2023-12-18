package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 项目类型
 * @author lzp
 * @date 2023年12月18 16:15
 */

@Data
@TableName("project_type")
public class ProjectType {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 项目行业名称 */
    @TableField("name")
    private String name;

    /** 项目数量 */
    @TableField("num")
    private Integer num;

    /** 是否删除 0-删除 1-未删除 */
    @TableField("del")
    private Integer del;

    public void changeNum(Integer change){
        this.num = this.num + change;
    }
}
