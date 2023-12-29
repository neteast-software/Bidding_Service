package com.neteast.business.domain.bid;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 专家的如(商务性评审)的整体完成情况
 * @author lzp
 * @date 2023年12月13 11:52
 */
@Data
@TableName("completion_status")
public class CompletionStatus{

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    Integer id;

    /** 专家名称 */
    @TableField("name")
    String name;

    /** 分包id */
    @TableField("package_id")
    Integer packageId;

    /** 专家id */
    @TableField("user_id")
    Integer userId;

    /** 供应商id */
    @TableField("supplier_id")
    Integer supplierId;

    /** 评分项id */
    @TableField("item_id")
    Integer itemId;

    /** 完成题目数 */
    @TableField("num")
    Long num = 0L;

    /** 价格分 */
    @TableField("score")
    Double score = 0.0;

    /** 更新时间 */
    @TableField("update_time")
    Date updateTime;
}
