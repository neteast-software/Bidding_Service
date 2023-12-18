package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.dict.PlusesCondition;
import com.neteast.business.domain.dict.vo.PlusesConditionVO;

import java.util.List;

/**
 * 项目加分项
 * @author lzp
 * @date 2023年12月15 10:44
 */
public interface IPlusesConditionService extends IService<PlusesCondition>{

    List<PlusesConditionVO> getPlusesConditionList(PlusesConditionVO plusesCondition);

    boolean delPlusesCondition(Integer id);
}
