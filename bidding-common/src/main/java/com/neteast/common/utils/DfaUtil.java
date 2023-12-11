package com.neteast.common.utils;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 敏感词过滤
 * @author lzp
 * @date 2023年12月11 10:41
 */

@Slf4j
public class DfaUtil {

    private static HashMap<Object,Object> dfaMap = null;

    private static final String isEnd = "isEnd";

    /**
     * @Description 初始化敏感词库
     * @author lzp
     * @Date 2023/12/11
     */
    public static void initWordMap(Collection<String> collection){

        if (MapUtil.isNotEmpty(dfaMap)){
            return;
        }
        long start = System.currentTimeMillis();
        dfaMap = new HashMap<>(collection.size());
        for (String str: collection) {
            if (StringUtils.isEmpty(str)){
                continue;
            }
            char[] chars = str.toCharArray();
            final int size = chars.length;
            Map<Object, Object> map = dfaMap;
            for (int i=0;i<size;i++){
                char key = chars[i];
                Object wordMap = map.get(key);
                if (ObjectUtil.isNotEmpty(wordMap)){
                    map = (Map<Object, Object>) wordMap;
                }else {
                    Map<Object,Object> newWordMap = new HashMap<>(8);
                    newWordMap.put(isEnd,false);
                    map.put(key,newWordMap);
                    map = newWordMap;
                }
                if (i == size -1){
                    map.put(isEnd,true);
                }
            }
        }
        long end = System.currentTimeMillis();
        log.info("敏感词库初始化时间-{}ms",end-start);
    }

    /**
     * @Description 刷新敏感词库
     * @author lzp
     * @Date 2023/12/11
     */
    public static void refreshMap(Collection<String> collection){
        dfaMap = null;
        initWordMap(collection);
    }

    /**
     * @Description 敏感词检测
     * @author lzp
     * @Date 2023/12/11
     */
    public static List<String> checkSensitiveWord(String content){

        char[] chars = content.toCharArray();
        List<String> words = new ArrayList<>();
        HashMap<Object,Object> map = dfaMap;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char k = chars[i];
            map = (HashMap<Object, Object>)map.get(k);
            if (map!=null){
                s.append(k);
                Boolean res = (Boolean) map.get(isEnd);
                if (res!=null&&res){
                    words.add(s.toString());
                    s = new StringBuilder();
                    map = dfaMap;
                }
            }else {
                map = dfaMap;
                s = new StringBuilder();
            }
        }
        return words;
    }

}
