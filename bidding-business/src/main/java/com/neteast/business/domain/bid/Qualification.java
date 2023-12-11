package com.neteast.business.domain.bid;

import lombok.Data;

/**
 * 质量评审
 * @author lzp
 * @date 2023年12月08 17:23
 */

@Data
public class Qualification {

    /** 评审项id */
    private Integer id;

    private boolean value;
}
