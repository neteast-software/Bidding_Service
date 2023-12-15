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

    /** 主键id */
    private Integer id;

    /** key值 */
    private String key;

    /** 是否通用 */
    private Boolean common;

    /** 字典类型 单选/多选 */
    private String dictType;

    public static DictKeyVO convert(DictKey dictKey){
        DictKeyVO dictKeyVO = new DictKeyVO();
        BeanUtil.copyProperties(dictKey,dictKeyVO);
        return dictKeyVO;
    }

    public static DictKey convert(DictKeyVO dictKeyVO){
        DictKey dictKey = new DictKey();
        dictKey.setId(dictKeyVO.getId());
        dictKey.setDictType(dictKeyVO.getDictType());
        dictKey.setCommon(dictKeyVO.getCommon());
        dictKey.setKey(dictKeyVO.getKey());
        return dictKey;
    }
}
