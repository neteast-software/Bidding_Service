package com.neteast.business.domain.template.vo;

import com.neteast.business.domain.template.TemplateFile;
import lombok.Data;

/**
 * @author lzp
 * @date 2023年12月14 17:41
 */

@Data
public class TemplateFileVO {

    private Integer id;

    /** 模板类型信息id */
    private Integer extId;

    /** 文件内容分类名称 */
    private String unit;

    /** 模板的使用次数 */
    private Integer useCount = 0;

    /** 模板的名称 */
    private String name;

    /** 是否公开 */
    private boolean open;

    /** 归属 1-公司库 2-个人库 */
    private Integer belong;

    public static TemplateFile convert(TemplateFileVO templateFileVO){
        TemplateFile templateFile = new TemplateFile();
        templateFile.setId(templateFile.getId());
        templateFile.setName(templateFileVO.getName());
        templateFile.setOpen(templateFileVO.isOpen());
        templateFile.setBelong(templateFileVO.getBelong());
        templateFile.setUnit(templateFileVO.getUnit());
        templateFile.setExtId(templateFileVO.getExtId());
        return templateFile;
    }
}
