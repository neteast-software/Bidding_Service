package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.dict.DictKey;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月22 10:40
 */
public interface IDictKeyService extends IService<DictKey> {

    List<DictKey> getDictKeyList(DictKey dictKey);

    boolean delDictKey(Integer keyId);
}
