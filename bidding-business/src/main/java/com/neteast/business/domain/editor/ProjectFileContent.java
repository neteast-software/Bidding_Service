package com.neteast.business.domain.editor;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 文件内容
 * @author lzp
 * @date 2023年12月15 17:32
 */

@Data
public class ProjectFileContent {

    /** 项目文件id */
    private Integer id;

    /** 项目id */
    private Integer projectId;

    /** 文件内容 */
    private String content;

    /** 文件名称 */
    private String name;

    /** 文件类型(excel,word) */
    private String fileType;

    /** 项目文件类型(如招标文件) */
    private String type;

    public static ProjectFileContent convert(ProjectBidding projectBidding){
        ProjectFileContent fileContent = new ProjectFileContent();
        fileContent.setId(projectBidding.getId());
        fileContent.setProjectId(projectBidding.getProjectId());
        fileContent.setName(projectBidding.getFileName());
        return fileContent;
    }
}
