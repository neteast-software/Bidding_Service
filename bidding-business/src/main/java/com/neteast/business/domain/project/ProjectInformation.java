package com.neteast.business.domain.project;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.neteast.business.domain.dict.PlusesCondition;
import com.neteast.business.domain.project.vo.ProjectInformationVO;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 甲方项目信息
 * @author lzp
 * @date 2023年11月14 10:51
 */

@Data
@ToString
@TableName("project_information")
public class ProjectInformation extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id ;

    /** 对应甲方id */
    @TableField("partya_id")
    private Integer partyaId ;

    /** 项目编号 */
    @TableField("project_code")
    private String projectCode ;

    /** 备案编号 **/
    @TableField("filings_number")
    private String filingsNumber;

    /** 项目行业 */
    @TableField("project_type")
    private Integer projectType ;

    /** 项目名称 */
    @TableField("project_name")
    private String projectName ;

    /** 采购类型(项目类型) */
    @TableField("procure_type")
    private String procureType ;

    /** 项目状态 1-招标 2-流标 3-结束 */
    @TableField("project_status")
    private Integer projectStatus ;

    /** 招标时间 */
    @TableField("invite_bidding")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inviteBidding;

    /** 开标时间 */
    @TableField("open_bidding")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date openBidding;

    /** 项目预算金额 */
    @TableField("budget_amount")
    private Double budgetAmount ;

    /** 项目最大金额 **/
    @TableField("max_amount")
    private Double maxAmount;

    /** 成交金额 */
    @TableField("trade_money")
    private Double tradeMoney;

    /** 流标次数 */
    @TableField("fail_bidding_count")
    private Integer failBiddingCount ;

    /** 代理费用 */
    @TableField("agent_money")
    private Double agentMoney;

    /** 保证金金额 */
    @TableField("earnest")
    private Double earnest;

    /** 项目删除，0-已删除,1-未删除 **/
    @TableField("project_del")
    private Integer projectDel;

    public static ProjectInformation convert(ProjectInformationVO projectInformationVO){
        ProjectInformation projectInformation = new ProjectInformation();
        BeanUtil.copyProperties(projectInformationVO,projectInformation);
        return projectInformation;
    }
}
