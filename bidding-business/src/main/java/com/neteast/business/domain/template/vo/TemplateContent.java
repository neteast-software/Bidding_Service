package com.neteast.business.domain.template.vo;

import lombok.Data;

/**
 * 模板内容
 * @author lzp
 * @date 2023年12月14 18:37
 */

@Data
public class TemplateContent {

    /** 模板id */
    private Integer id;

    /** 模板类型信息id */
    private Integer extId;

    /** 模板的使用次数 */
    private Integer useCount = 0;

    /** 模板的名称 */
    private String name;

    /** 归属 1-公司库 2-个人库 */
    private Integer belong;

    /** 模板内容 */
    private String content;
}
