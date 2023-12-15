package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.dict.Sensitive;
import com.neteast.business.domain.dict.vo.SensitiveVO;

import java.util.List;

/**
 * 敏感词
 * @author lzp
 * @date 2023年12月15 10:45
 */
public interface ISensitiveService extends IService<Sensitive> {

    List<SensitiveVO> getSensitiveList(SensitiveVO sensitiveVO);
}
