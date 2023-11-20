package com.neteast.business.domain.custom.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author lzp
 * @date 2023年11月17 17:59
 */

@Data
public class ContractMessageVO {

    /** 主键id */
    private Integer id;

    /** 联系人名称 */
    private String personName;

    /** 电话联系方式 */
    private String contractPhone;

    /** 邮箱 */
    private String mail;

    /** 单位名称 */
    private String workName;

    /** 单位地址 */
    private String workSpace;

    /** 法人 */
    private String legalPerson;

    /** 网址信息 */
    private String net;

    /** 邮编 */
    private String postcode;

    /** 单位类型(甲方、代理商、供应商) 1-甲方,2-代理商,3-供应商 */
    private Integer placeType;
}
