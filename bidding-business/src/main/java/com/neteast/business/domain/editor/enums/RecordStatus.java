package com.neteast.business.domain.editor.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author lzp
 * @date 2023年12月25 11:37
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum RecordStatus {

    ADD(1,"新建文件"),
    UPDATE(2,"更新文件"),
    DEL(3,"删除文件"),
    SAVE(4,"保存文件");

    private Integer id;
    private String opera;

    public static String getById(Integer id){
        switch (id){
            case 1:
                return RecordStatus.ADD.getOpera();
            case 2:
                return RecordStatus.UPDATE.getOpera();
            case 3:
                return RecordStatus.DEL.getOpera();
            case 4:
                return RecordStatus.SAVE.getOpera();
            default:
                return "未知";
        }
    }
}
