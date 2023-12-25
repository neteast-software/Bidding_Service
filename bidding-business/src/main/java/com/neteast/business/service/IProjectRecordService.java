package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.editor.ProjectBidding;
import com.neteast.business.domain.editor.ProjectRecord;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月25 10:47
 */

public interface IProjectRecordService extends IService<ProjectRecord>{

    List<ProjectRecord> getProjectRecordList(ProjectRecord projectRecord);

    boolean updateFileMsg(Integer fileId,String fileName);

    boolean updateUserMsg(Integer userId,String userName);
}
