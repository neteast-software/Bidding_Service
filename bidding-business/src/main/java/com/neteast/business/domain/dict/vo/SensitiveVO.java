package com.neteast.business.domain.dict.vo;

import lombok.Data;

/**
 * 敏感词
 * @author lzp
 * @date 2023年12月15 10:38
 */

@Data
public class SensitiveVO {

    /** 主键id */
    private Integer id;

    /** 敏感词 */
    private String word;
}
