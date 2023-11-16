package com.neteast.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 项目信息
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

    /** 项目编号 */
    @TableField("project_code")
    private String projectCode ;

    /** 项目名称 */
    @TableField("project_name")
    private String projectName ;

    /** 项目类型 */
    @TableField("project_type")
    private String projectType ;

    /** 项目状态 1-招标 2-流标 3-结束 */
    @TableField("project_status")
    private Integer projectStatus ;

    /** 项目期限 */
    @TableField("term")
    private Date term ;

    /** 项目金额 */
    @TableField("amount")
    private Double amount ;

    @TableField("partya_name")
    private String partyaName;

    /** 对应甲方id */
    @TableField("partya_id")
    private Integer partyaId ;

    /** 父项目id;包与项目 */
    @TableField("parent_id")
    private Integer parentId ;

    /** 流标次数 */
    @TableField("fail_bidding_count")
    private Integer failBiddingCount ;

    /** 分包号 */
    @TableField("package_num")
    private String packageNum ;

    /** 招标开始时间 */
    @TableField("bidding_start_time")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date biddingStartTime ;

    /** 招标结束时间 */
    @TableField("bidding_end_time")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date biddingEndTime ;

    /** 开标时间 */
    @TableField("bidding_open_time")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date biddingOpenTime ;

    /** 开标地址 */
    @TableField("bidding_open_address")
    private String biddingOpenAddress ;

    /** 招标类型 */
    @TableField("bidding_type")
    private String biddingType ;

    /** 项目删除，0-已删除,1-未删除 **/
    @TableField("project_del")
    private Integer projectDel;

    @TableField(exist = false)
    private String failReason;
}
