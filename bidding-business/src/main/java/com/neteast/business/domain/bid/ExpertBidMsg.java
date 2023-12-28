package com.neteast.business.domain.bid;

import com.neteast.business.domain.bid.score.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 专家评标记录
 * @author lzp
 * @date 2023年12月08 18:19
 */

@Data
public class ExpertBidMsg {

    /** 专家id */
    private Integer id;

    /** 专家名称 */
    private String name;

    /** 供应商id */
    private Integer supplierId;

    /** 分包id */
    private Integer packageId;

    /** 资格审查得分情况 */
    QualificationScore qualificationScore = new QualificationScore();

    /** 符合性得分情况 */
    ConformScore conformScore = new ConformScore();

    /** 商务分得分情况 */
    BusinessScore businessScore = new BusinessScore();

    /** 技术分得分情况 */
    TechScore techScore = new TechScore();

    /** 价格分得分情况 */
    PriceScore priceScore = new PriceScore();
}
