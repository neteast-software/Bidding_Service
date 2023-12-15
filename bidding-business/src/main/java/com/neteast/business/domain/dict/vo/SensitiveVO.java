package com.neteast.business.domain.dict.vo;

import com.neteast.business.domain.dict.Sensitive;
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

    public static Sensitive convert(SensitiveVO sensitiveVO){
        Sensitive sensitive = new Sensitive();
        sensitive.setId(sensitiveVO.getId());
        sensitive.setWord(sensitiveVO.getWord());
        return sensitive;
    }
}
