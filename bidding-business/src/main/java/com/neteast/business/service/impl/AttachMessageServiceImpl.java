package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.AttachMessage;
import com.neteast.business.domain.project.vo.AttachMessageVO;
import com.neteast.business.mapper.AttachMessageMapper;
import com.neteast.business.service.IAttachMessageService;
import com.neteast.common.exception.BaseBusException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author lzp
 * @date 2023年11月17 15:43
 */

@Service
public class AttachMessageServiceImpl extends ServiceImpl<AttachMessageMapper, AttachMessage> implements IAttachMessageService {

    @Resource
    AttachMessageMapper attachMessageMapper;

    @Override
    public List<AttachMessageVO> getAttachMessageList(AttachMessageVO attachMessageVO) {
        return attachMessageMapper.getList(attachMessageVO);
    }

    @Override
    public String saveAttachMessage(Integer projectId, Integer packageId, String fileType, MultipartFile file) {

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
        save(attachMessage);
        return filename;
    }
}
