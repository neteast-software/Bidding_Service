package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.PackageInformation;
import com.neteast.business.domain.project.ProjectCondition;
import com.neteast.business.domain.project.vo.PackageInformationVO;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 13:55
 */
public interface IPackageInformationService extends IService<PackageInformation> {

    boolean savePackageInformation(PackageInformationVO packageInformationVO);

    boolean delPackageInformation(Integer id);

    boolean updatePackageInformation(PackageInformationVO packageInformationVO);

    List<PackageInformationVO> getPackageInformationVOList(Integer projectId);
}
