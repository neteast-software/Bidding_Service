package com.neteast.business.domain.editor.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 标书的区分
 * @author lzp
 * @date 2023年12月15 17:15
 */

@AllArgsConstructor
@Getter
public enum BiddingStatus {

    /** 开标前 */
    BEFORE(1,"开标前"),
    /** 开标 */
    OPEN(2,"开标"),
    /** 评标 */
    EVALUATION(3,"评标"),
    /** 评标后 */
    AFTER(4,"评标后");
    private final Integer status;

    private final String label;

}
