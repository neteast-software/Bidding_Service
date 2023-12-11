package com.neteast.business.domain.bid;

import lombok.Data;

import java.util.Objects;

/**
 * 技术评审
 * @author lzp
 * @date 2023年12月08 17:23
 */

@Data
public class Technical {

    /** 评审项id */
    private Integer id;

    private boolean value;
}
