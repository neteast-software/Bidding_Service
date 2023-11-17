package com.neteast.business.domain.custom.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author lzp
 * @date 2023年11月17 17:59
 */

@Data
public class ContractMessageVO {

    /** 主键id */
    private Integer id;

    /** 关联表id */
    private Integer extId;

    /** 联系所属类型;甲方、供应方、招标代理商 */
    private Integer type;

    /** 联系人名称 */
    private String personName;

    /** 电话联系方式 */
    private String contractPhone;

    /** 邮箱 */
    private String mail;


}
