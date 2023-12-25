package com.neteast.business.service.impl;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.editor.ProjectBidding;
import com.neteast.business.domain.editor.ProjectFileContent;
import com.neteast.business.domain.editor.ProjectRecord;
import com.neteast.business.domain.editor.enums.RecordStatus;
import com.neteast.business.domain.editor.vo.ProjectBiddingVO;
import com.neteast.business.domain.project.ProjectStage;
import com.neteast.business.domain.project.ProjectStatus;
import com.neteast.business.mapper.ProjectBiddingMapper;
import com.neteast.business.service.IProjectBiddingService;
import com.neteast.business.service.IProjectInformationService;
import com.neteast.business.service.IProjectRecordService;
import com.neteast.business.service.IProjectStatusService;
import com.neteast.common.exception.BaseBusException;
import com.neteast.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author lzp
 * @date 2023年11月17 15:44
 */

@Service
@Slf4j
public class ProjectBiddingServiceImpl extends ServiceImpl<ProjectBiddingMapper, ProjectBidding> implements IProjectBiddingService {

    @Resource
    ProjectBiddingMapper projectBiddingMapper;

    @Resource
    IProjectStatusService statusService;

    @Resource
    IProjectRecordService recordService;

    @Value("${ruoyi.biddingFilePath}")
    String biddingFilePath;

    @Override
    public List<ProjectBiddingVO> getProjectBiddingList(ProjectBiddingVO projectBidding) {
        return projectBiddingMapper.getList(projectBidding);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean creatProjectBiddingFile(ProjectBidding projectBidding) throws IOException {

        log.info("项目文件创建地址-{}",biddingFilePath);
        String filename = UUID.randomUUID().toString().replace("-","");
        String path = biddingFilePath+filename+".txt";
        File file = new File(path);
        if (!file.exists()){
            file.createNewFile();
        }
        projectBidding.setFilePath(path);
        //项目文件保存
        save(projectBidding);
        //项目阶段状态更新
        ProjectStatus status = getProjectStatus(projectBidding);
        statusService.saveByExtId(status);
        //项目文件记录添加
        ProjectRecord projectRecord = getProjectRecord(projectBidding,1);
        recordService.save(projectRecord);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delProjectBiddingFile(Integer id) {

        ProjectBidding projectBidding = getById(id);
        String path = projectBidding.getFilePath();
        File file = new File(path);
        if (!file.exists()){
            log.info("该文件不存在-{}",path);
            return false;
        }
        log.info("项目文件删除-{}",path);
        boolean res = file.delete();
        if (!res){
            log.info("文件删除失败-{}",path);
        }
        Integer projectId = projectBidding.getProjectId();
        Integer stageId = projectBidding.getStageId();
        //项目阶段状态删除
        statusService.removeByExtId(projectId,stageId);
        //项目记录删除
        ProjectRecord projectRecord = getProjectRecord(projectBidding,3);
        recordService.save(projectRecord);
        return removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProjectBiddingFile(ProjectBidding bidding) {
        this.updateById(bidding);
        //文件名称更新
        if (bidding.getFileName()!=null){
            recordService.updateFileMsg(bidding.getId(),bidding.getFileName());
        }
        //项目文件记录更新
        ProjectBidding temp = this.getById(bidding.getId());
        ProjectRecord projectRecord = getProjectRecord(temp,2);
        recordService.save(projectRecord);
        return true;
    }

    @Override
    public boolean saveProjectBiddingFile(ProjectFileContent fileContent) {

        //项目文件保存
        ProjectBidding projectBidding = this.getById(fileContent.getId());
        if (projectBidding==null){
            throw new BaseBusException(500,"该项目文件不存在");
        }
        String path = projectBidding.getFilePath();
        String content = fileContent.getContent();
        FileUtil.writeUtf8String(content,new File(path));
        //项目阶段状态更新
        statusService.updateTime(projectBidding.getProjectId(),projectBidding.getStageId());
        //todo 敏感词判断
        //项目文件记录保存
        ProjectRecord projectRecord = getProjectRecord(projectBidding,4);
        recordService.save(projectRecord);
        return true;
    }

    private ProjectRecord getProjectRecord(ProjectBidding bidding, Integer type){
        ProjectRecord record = new ProjectRecord();
        record.setFileId(bidding.getId());
        record.setFileName(bidding.getFileName());
        record.setOperaType(type);
        record.setOpera(RecordStatus.getById(type));
        record.setOperaTime(new Date());
        //Long userId = SecurityUtils.getUserId();
        //String username = SecurityUtils.getUsername();
        //record.setUserId(userId);
        //record.setOperaUser(username);
        //更新该操作用户信息
        //recordService.updateUserMsg(userId,username);
        return record;
    }

    private ProjectStatus getProjectStatus(ProjectBidding bidding){
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setProjectId(bidding.getProjectId());
        projectStatus.setStageId(bidding.getStageId());
        projectStatus.setStepNum(bidding.getStageNum());
        projectStatus.setStageTime(new Date());
        return projectStatus;
    }
}
