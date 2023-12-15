package com.neteast.business.domain.editor.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 项目标书文件
 * @author lzp
 * @date 2023年12月15 17:43
 */

@Data
public class ProjectBiddingVO {

    /** 主键id */
    private Integer id;

    /** 项目id */
    private Integer projectId;

    /** 项目文件类型(如招标文件) */
    private String type;

    /** 文件名称 */
    private String name;

    /** 文件类型(excel,word) */
    private String fileType;
}
