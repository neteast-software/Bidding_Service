package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.editor.ProjectBidding;
import com.neteast.business.domain.editor.vo.ProjectBiddingVO;
import com.neteast.business.domain.project.vo.ProjectFileMsgVO;

import java.io.IOException;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 15:40
 */
public interface IProjectBiddingService extends IService<ProjectBidding> {

    List<ProjectBiddingVO> getProjectBiddingList(ProjectBiddingVO projectBiddingVO);

    /**
     * @Description 创建项目招标文件
     * @author lzp
     * @Date 2023/12/13
     */
    boolean creatProjectBiddingFile(ProjectBidding projectBidding) throws IOException;

    /**
     * @Description 进行项目招标文件删除
     * @author lzp
     * @Date 2023/12/13
     */
    boolean delProjectBiddingFile(Integer id);
}
