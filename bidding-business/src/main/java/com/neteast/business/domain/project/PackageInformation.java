package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 项目分包信息
 * @author lzp
 * @date 2023年11月17 11:16
 */

@Data
@TableName("package_information")
public class PackageInformation extends BaseEntity {


    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 对应甲方项目id */
    @TableField("project_id")
    private Integer projectId;

    /** 分包名称 */
    @TableField("package_name")
    private String packageName;

    /** 评分方式id 方式有(综合评标法,最低评标价法等) */
    @TableField("score_id")
    private Integer scoreId;

    /** 分包预算金额 */
    @TableField("budget_amount")
    private Double budgetAmount;

    /** 分包最大金额限额 */
    @TableField("max_amount")
    private Double maxAmount;

    /** 合同号 */
    @TableField("contract_package")
    private String contractPackage;

    /** 品目号 */
    @TableField("package_num")
    private String packageNum;

    /** 包单位;包需要完成内容的数量,如项,个等单位等 */
    @TableField("package_unit")
    private String packageUnit;

    /** 流标次数 */
    @TableField("fail_bidding_count")
    private Integer failBiddingCount;

    /** 采购标的 */
    @TableField("bidding_subject")
    private String biddingSubject;

    /** 保证金额 */
    @TableField("earnest")
    private Double earnest;
}
