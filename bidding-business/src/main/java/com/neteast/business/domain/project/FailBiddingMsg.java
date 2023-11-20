package com.neteast.business.domain.project;

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

    /** 项目id */
    @TableField("ext_project_id")
    private Integer extProjectId ;

    /** 项目名称 **/
    @TableField("project_name")
    private String projectName;

    /** 采购类型 */
    @TableField("procure_type")
    private String procureType;

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
        return failBiddingMsg;
    }
}
