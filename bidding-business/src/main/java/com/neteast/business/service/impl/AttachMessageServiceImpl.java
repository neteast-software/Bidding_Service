package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.AttachMessage;
import com.neteast.business.mapper.AttachMessageMapper;
import com.neteast.business.service.IAttachMessageService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年11月17 15:43
 */

@Service
public class AttachMessageServiceImpl extends ServiceImpl<AttachMessageMapper, AttachMessage> implements IAttachMessageService {
}
