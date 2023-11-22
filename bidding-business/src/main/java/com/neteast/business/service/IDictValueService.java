package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.dict.DictKey;
import com.neteast.business.domain.dict.DictValue;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月22 10:40
 */
public interface IDictValueService extends IService<DictValue> {

    List<DictValue> getKeyValue(Integer keyId);

    List<DictValue> getDictValueList(DictValue dictValue);

    boolean delDictValueData(Integer valueId);

    boolean delDictValueByKeyId(Integer keyId);

    boolean updateDictValue(DictValue dictValue);

}
