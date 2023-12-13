package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.ScoreItem;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月12 14:15
 */
public interface IScoreItemService extends IService<ScoreItem> {

    List<ScoreItem> getListByExtId(Integer id);

    boolean addScoreItem(ScoreItem scoreItem);
}
