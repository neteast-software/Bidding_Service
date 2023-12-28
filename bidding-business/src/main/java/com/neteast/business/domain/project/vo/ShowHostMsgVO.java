package com.neteast.business.domain.project.vo;

import lombok.Data;

import java.util.List;

/**
 * 主持人端的数据展示
 * @author lzp
 * @date 2023年12月28 15:57
 */

@Data
public class ShowHostMsgVO {

    /** 总题目数 */
    private Integer totalTitle;

    /** 供应商信息 */
    private List<HostSupplierVO> suppliers;

    /** 专家信息 */
    private List<HostExpertVO> experts;

    /** 题目信息 */
    private List<HostTitleVO> titles;

}
