package com.neteast.business.domain.bid.score;

import lombok.Data;

/**
 * 供应商价格分得分情况
 * @author lzp
 * @date 2023年12月27 13:42
 */

@Data
public class SupplierPrice {

    private String userId;

    private String expertName;

    private Double value;

}
