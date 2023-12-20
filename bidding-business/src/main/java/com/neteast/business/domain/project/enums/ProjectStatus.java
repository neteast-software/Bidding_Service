package com.neteast.business.domain.project.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 项目状态
 * @author lzp
 * @date 2023年12月20 14:11
 */

@Getter
@AllArgsConstructor
public enum ProjectStatus {

    PRE_NOTICE(1,"预公告"),
    TENDER_NOTICE(2,"招标公告"),
    ANSWER_NOTICE(3,"答疑或补充通知"),
    OPEN_BIDDING(4,"开标记录"),
    EVALUATION_BIDDING(5,"评标结果公示"),
    RESULT_BIDDING(6,"中标结果公示"),
    RESULT_NOTICE(7,"结果通知书"),
    CONTRACT(8,"合同"),
    WITNESS_NOTE(9,"见证书");

    private final Integer status;

    private final String label;

}
