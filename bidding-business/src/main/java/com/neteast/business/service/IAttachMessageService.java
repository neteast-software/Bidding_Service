package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.AttachMessage;
import com.neteast.business.domain.project.vo.AttachMessageVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 15:40
 */
public interface IAttachMessageService extends IService<AttachMessage> {

    List<AttachMessageVO> getAttachMessageList(AttachMessageVO attachMessageVO);

    String saveAttachMessage(Integer projectId, Integer packageId, String fileType, MultipartFile file);
}
