package com.neteast.business.domain.template.vo;

import com.neteast.business.domain.template.TemplateType;
import lombok.Data;

/**
 * @author lzp
 * @date 2023年12月14 18:18
 */

@Data
public class TemplateTypeVO {

    /** 主键 */
    private Integer id;

    /** 模板类型 */
    private String type;

    public static TemplateType convert(TemplateTypeVO templateTypeVO){
        TemplateType templateType = new TemplateType();
        templateType.setId(templateTypeVO.getId());
        templateType.setType(templateTypeVO.getType());
        return templateType;
    }
}
