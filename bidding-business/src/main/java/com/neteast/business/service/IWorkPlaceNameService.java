package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.custom.WorkPlaceName;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 18:28
 */
public interface IWorkPlaceNameService extends IService<WorkPlaceName> {

    List<WorkPlaceName> getWorkPlaceNameList(WorkPlaceName workPlaceName);
}
