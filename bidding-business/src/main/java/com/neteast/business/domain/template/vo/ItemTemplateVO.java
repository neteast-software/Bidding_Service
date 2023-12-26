package com.neteast.business.domain.template.vo;

import cn.hutool.core.bean.BeanUtil;
import com.neteast.business.domain.template.ItemTemplate;
import lombok.Data;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月19 17:29
 */

@Data
public class ItemTemplateVO {

    /** 主键id */
    private Integer id;

    /** 评分项名称 */
    private String itemName;

    /** 评分项类型(价格性,商务性等) */
    private String itemType;

    /** 值类型(1单选,2分数) */
    private Integer valueType;

    /** 该项的总分值 */
    private Double value;

    public static ItemTemplateVO convert(ItemTemplate template){
        ItemTemplateVO vo = new ItemTemplateVO();
        BeanUtil.copyProperties(template,vo);
        return vo;
    }
}
