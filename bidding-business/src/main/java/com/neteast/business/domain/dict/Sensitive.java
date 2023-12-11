package com.neteast.business.domain.dict;

import lombok.Data;

import java.util.Date;

/**
 * 敏感词
 * @author lzp
 * @date 2023年12月11 10:31
 */

@Data
public class Sensitive {

    /** 主键id */
    private Integer id;

    /** 敏感词 */
    private String word;

    /** 创建时间 */
    private Date createTime;
}