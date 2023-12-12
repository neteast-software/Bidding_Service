package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.ProjectScoreItem;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月12 14:14
 */
public interface IProjectScoreItemService extends IService<ProjectScoreItem> {

    List<ProjectScoreItem> getProjectScoreItemList(ProjectScoreItem projectScoreItem);

    boolean removeProjectScoreItem(Integer id);
}
