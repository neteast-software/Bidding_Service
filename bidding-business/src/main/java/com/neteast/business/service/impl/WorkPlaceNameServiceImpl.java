package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.custom.WorkPlaceName;
import com.neteast.business.mapper.WorkPlaceNameMapper;
import com.neteast.business.service.IWorkPlaceNameService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年11月17 18:28
 */

@Service
public class WorkPlaceNameServiceImpl extends ServiceImpl<WorkPlaceNameMapper, WorkPlaceName> implements IWorkPlaceNameService {
}
