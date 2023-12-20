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

    /** key名称 */
    private String key;

    /** key的类型id */
    private Integer typeId;

    /** key的类型的值 */
    private String type;

    /** 是否通用 */
    private Boolean common;

    /** key的值 */
    private String label;

    /** 字典类型 1-单选 2-多选 3-填值等 */
    private Integer dictType;

    private List<DictValue> values;

    public static DictKeyVO convert(DictKey dictKey){
        DictKeyVO dictKeyVO = new DictKeyVO();
        BeanUtil.copyProperties(dictKey,dictKeyVO);
        return dictKeyVO;
    }

    public static DictKey convert(DictKeyVO dictKeyVO){
        DictKey dictKey = new DictKey();
        dictKey.setId(dictKeyVO.getId());
        dictKey.setTypeId(dictKeyVO.getTypeId());
        dictKey.setDictType(dictKeyVO.getDictType());
        dictKey.setCommon(dictKeyVO.getCommon());
        dictKey.setKey(dictKeyVO.getKey());
        dictKey.setLabel(dictKeyVO.getLabel());
        return dictKey;
    }
}
