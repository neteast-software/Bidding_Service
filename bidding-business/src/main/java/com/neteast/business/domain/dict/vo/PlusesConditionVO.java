package com.neteast.business.domain.dict.vo;

import com.neteast.business.domain.dict.PlusesCondition;
import lombok.Data;

/**
 * @author lzp
 * @date 2023年12月18 13:57
 */

@Data
public class PlusesConditionVO {

    /** 主键id */
    private Integer id;

    /** 附件项名称 */
    private String name;

    public static PlusesCondition convert(PlusesConditionVO plusesConditionVO){
        PlusesCondition condition = new PlusesCondition();
        condition.setId(plusesConditionVO.getId());
        condition.setName(plusesConditionVO.getName());
        return condition;
    }
}
