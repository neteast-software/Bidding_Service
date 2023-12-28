package com.neteast.business.domain.bid;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 单个供应商的每个评分项的几个专家的做题情况
 * @author lzp
 * @date 2023年12月13 9:38
 */

@Data
public class ReviewStatus {

    /** 评分项类型 */
    private String itemType;

    /**专家完成情况*/
    private List<CompletionStatus> completionStatuses = new ArrayList<>();

    /** 供应商该评分项完成情况 */
    private Integer num;

    public void setCompletionStatus(CompletionStatus completionStatus){

        for (CompletionStatus c:completionStatuses){
            if (completionStatus.getUserId().compareTo(c.getUserId())==0){
                c.setName(completionStatus.getName());
                num = num - c.getNum() + completionStatus.getNum();
                c.setNum(completionStatus.getNum());
                return;
            }
        }
        completionStatuses.add(completionStatus);
        num = num + completionStatus.getNum();
    }
}
