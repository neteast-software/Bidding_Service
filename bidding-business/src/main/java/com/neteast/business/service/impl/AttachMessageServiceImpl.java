package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.AttachMessage;
import com.neteast.business.domain.project.vo.AttachMessageVO;
import com.neteast.business.mapper.AttachMessageMapper;
import com.neteast.business.service.IAttachMessageService;
import com.neteast.common.exception.BaseBusException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 15:43
 */

@Service
@Slf4j
public class AttachMessageServiceImpl extends ServiceImpl<AttachMessageMapper, AttachMessage> implements IAttachMessageService {

    @Resource
    AttachMessageMapper attachMessageMapper;

    @Value("${ruoyi.profile}")
    String filePath;

    @Override
    public List<AttachMessageVO> getAttachMessageList(AttachMessageVO attachMessageVO) {
        return attachMessageMapper.getList(attachMessageVO);
    }

    @Override
    public void saveAttachMessage(Integer projectId, Integer packageId, String fileType, MultipartFile file) throws IOException {

        AttachMessage attachMessage = new AttachMessage();
        attachMessage.setProjectId(projectId);
        attachMessage.setPackageId(packageId);
        attachMessage.setFileType(fileType);
        attachMessage.setSize(file.getSize());
        attachMessage.setNum(0);
        String filename = file.getOriginalFilename();
        if (filename==null){
            throw new BaseBusException("上传文件名称获取为空");
        }
        attachMessage.setAddress(filename);
        attachMessage.setName(filename);
        String[] split = filename.split("\\.");
        attachMessage.setType(split[1]);
        attachMessage.setFilePath(filePath+filename);
        save(attachMessage);
        file.transferTo(new File(filePath+filename));
    }

    @Override
    public void delAttachMessage(Integer id) {

        AttachMessage attachMessage = this.getById(id);
        String path = attachMessage.getFilePath();
        File file = new File(path);
        if (!file.exists()){
            log.info("文件不存在无法删除-{}",path);
        }
        boolean res = file.delete();
        if (!res){
            log.info("文件删除失败-{}",path);
        }
    }
}
