package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.dict.Sensitive;
import com.neteast.business.domain.dict.vo.SensitiveVO;
import com.neteast.business.mapper.SensitiveMapper;
import com.neteast.business.service.ISensitiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月15 10:46
 */

@Service
public class SensitiveServiceImpl extends ServiceImpl<SensitiveMapper, Sensitive> implements ISensitiveService {

    @Resource
    SensitiveMapper sensitiveMapper;

    @Override
    public List<SensitiveVO> getSensitiveList(SensitiveVO sensitiveVO) {
        return sensitiveMapper.getList(sensitiveVO);
    }
}
