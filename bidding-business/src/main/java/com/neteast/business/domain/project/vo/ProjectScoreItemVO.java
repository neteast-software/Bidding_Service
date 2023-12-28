package com.neteast.business.domain.project.vo;

import com.neteast.business.domain.project.ScoreItem;
import lombok.Data;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月25 18:05
 */

@Data
public class ProjectScoreItemVO {

    List<ScoreItemVO> items;
}
