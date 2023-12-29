package com.neteast.business.domain.project.vo;

import lombok.Data;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月29 16:45
 */

@Data
public class ScoreRule {

    /** 1-表示所有*，2-表示排除/，3-表示包含() */
    private Integer type;

    /** 排除/包含的内容 */
    private List<String> content;

}
