package domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 项目信息
 * @author lzp
 * @date 2023年11月14 10:51
 */

@Data
@TableName("project_information")
public class ProjectInformation {

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

    /** 项目状态 */
    @TableField("project_status")
    private Integer projectStatus ;

    /** 项目期限 */
    @TableField("term")
    private Date term ;

    /** 项目金额 */
    @TableField("amount")
    private Double amount ;

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
}
