package com.neteast.business.domain.dict.vo;

import cn.hutool.core.bean.BeanUtil;
import com.neteast.business.domain.dict.DictKey;
import com.neteast.business.domain.dict.DictValue;
import lombok.Data;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月22 11:34
 */

@Data
public class DictKeyVO {

    private Integer id;

    private String key;

    private String common;

    private String projectType;

    private String biddingType;

    private String fileType;

    private String dictType;

    private List<DictValue> values;

    public static DictKeyVO convert(DictKey dictKey){
        DictKeyVO dictKeyVO = new DictKeyVO();
        BeanUtil.copyProperties(dictKey,dictKeyVO);
        return dictKeyVO;
    }
}
