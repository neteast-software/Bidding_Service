package com.neteast.business.domain.custom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 单位信息
 * @author lzp
 * @date 2023年11月17 18:24
 */

@Data
@TableName("work_place_name")
public class WorkPlaceName extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 单位名称 */
    @TableField("name")
    private String name;

    /** 单位地址 */
    @TableField("address")
    private String address;

    /** 法人 */
    @TableField("legal_person")
    private String legalPerson;

    /** 网址信息 */
    @TableField("net")
    private String net;

    /** 单位类型(甲方、代理商、供应商) 1-甲方,2-代理商 */
    @TableField("place_type")
    private Integer placeType;

    /** 邮编 */
    @TableField("postcode")
    private String postcode;
}
