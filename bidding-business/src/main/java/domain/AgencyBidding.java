package domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 招标公司
 * @author lzp
 * @date 2023年11月14 10:48
 */

@Data
@TableName("agency_bidding")
public class AgencyBidding extends BaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("company_name")
    private String companyName;

    @TableField("company_address")
    private String companyAddress;

    @TableField("company_contact")
    private String companyContact;

    @TableField("legal_person")
    private String legalPerson;

    @TableField("bank_card")
    private String bankCard;

    @TableField("bank_name")
    private String bankName;

    @TableField("enpower_message")
    private String enPowerMsg;

}
