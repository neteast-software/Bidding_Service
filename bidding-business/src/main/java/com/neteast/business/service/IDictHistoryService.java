package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.dict.DictHistory;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月22 10:39
 */
public interface IDictHistoryService extends IService<DictHistory> {

    boolean delByKeyId(Integer keyId);

    boolean delByValueId(Integer valueId);

    List<DictHistory> getDictHistoryList(DictHistory dictHistory);
}
