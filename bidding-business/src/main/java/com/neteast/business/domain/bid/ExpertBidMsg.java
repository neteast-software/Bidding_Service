package com.neteast.business.domain.bid;

import lombok.Data;

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

    /** 技术评审 */
    private List<Technical> technicals;

    /** 商务评审 */
    private List<Commercial> commercials;

    /** 资格评审 */
    private List<Qualification> qualifications;

    /** 价格评审 */
    private List<Price> prices;
}
