package com.neteast.business.domain;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 流标记录标
 * @author lzp
 * @date 2023年11月14 10:52
 */

@Data
@TableName("fail_bidding_message")
public class FailBiddingMsg extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id ;

    /** 招标开始时间 */
    @TableField("bidding_start_time")
    private Date biddingStartTime ;

    /** 招标结束时间 */
    @TableField("bidding_end_time")
    private Date biddingEndTime ;

    /** 招标类型 */
    @TableField("bidding_type")
    private String biddingType ;

    /** 开标时间 */
    @TableField("bidding_open_time")
    private Date biddingOpenTime ;

    /** 开标地址 */
    @TableField("bidding_open_address")
    private String biddingOpenAddress ;

    /** 项目id */
    @TableField("ext_project_id")
    private Integer extProjectId ;

    /** 项目名称 */
    @TableField("project_name")
    private String projectName;

    /** 公司名称 */
    @TableField("company_name")
    private String companyName;

    /** 流标原因 **/
    @TableField("fail_reason")
    private String failReason;

    public static FailBiddingMsg convert(ProjectInformation projectInformation){
        FailBiddingMsg failBiddingMsg = new FailBiddingMsg();
        BeanUtil.copyProperties(projectInformation,failBiddingMsg);
        failBiddingMsg.setId(null);
        failBiddingMsg.setExtProjectId(projectInformation.getId());
        failBiddingMsg.setCompanyName(projectInformation.getPartyaName());
        failBiddingMsg.setCreateTime(new Date());
        failBiddingMsg.setCreateBy(projectInformation.getUpdateBy());
        failBiddingMsg.setUpdateTime(null);
        failBiddingMsg.setUpdateBy(null);
        return failBiddingMsg;
    }
}
