package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.editor.ProjectBidding;
import com.neteast.business.mapper.ProjectBiddingMapper;
import com.neteast.business.service.IProjectBiddingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
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

    @Value("${ruoyi.biddingFilePath}")
    String biddingFilePath;

    @Override
    public List<ProjectBidding> getProjectBiddingList(ProjectBidding projectBidding) {
        return projectBiddingMapper.getList(projectBidding);
    }

    @Override
    public boolean creatProjectBiddingFile(ProjectBidding projectBidding) throws IOException {

        log.info("项目文件创建地址-{}",biddingFilePath);
        String filename = UUID.randomUUID().toString().replace("-","");
        String path = biddingFilePath+filename+".txt";
        File file = new File(path);
        if (!file.exists()){
            file.createNewFile();
        }
        projectBidding.setFilePath(path);
        save(projectBidding);
        return true;
    }

    @Override
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
        return removeById(id);
    }
}
