package com.neteast.business.domain.custom.vo;

import cn.hutool.core.bean.BeanUtil;
import com.neteast.business.domain.custom.BankMessage;
import com.neteast.business.domain.custom.ContractMessage;
import com.neteast.business.domain.custom.WorkPlaceName;
import lombok.Data;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月20 13:39
 */

@Data
public class WorkPlaceNameVO {

    /** 主键id */
    private Integer id;

    /** 单位名称 */
    private String name;

    /** 单位地址 */
    private String address;

    /** 法人 */
    private String legalPerson;

    /** 网址信息 */
    private String net;

    /** 单位类型(甲方、代理商、供应商) 1-甲方,2-代理商,3-供应商 */
    private Integer placeType;

    /** 邮编 */
    private String postcode;

    /** 银行信息 */
    private List<BankMessage> bankMessages;

    /** 联系信息 */
    private List<ContractMessage> contractMessages;

    public static WorkPlaceNameVO convert(WorkPlaceName workPlaceName){
        WorkPlaceNameVO workPlaceNameVO = new WorkPlaceNameVO();
        BeanUtil.copyProperties(workPlaceName,workPlaceNameVO);
        return workPlaceNameVO;
    }
}
