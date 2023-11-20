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
public class BankMessageVO {

    /** 主键id */
    private Integer id;

    /** 银行名称 */
    private String name;

    /** 银行地址 */
    private String address;

    /** 银行卡号 */
    private String number;

    /** 银行卡账户名称 */
    private String cardName;

    /** 用途(如保证金账户等) */
    private String use;

    /** 单位名称 */
    private String workName;

    /** 单位地址 */
    private String workPlace;

    /** 法人 */
    private String legalPerson;

    /** 网址信息 */
    private String net;

    /** 邮编 */
    private String postcode;

    /** 单位类型(甲方、代理商、供应商) 1-甲方,2-代理商,3-供应商 */
    private Integer placeType;
}
