package com.neteast.framework.aspectj;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.neteast.common.annotation.DictData;
import com.neteast.common.annotation.DictDataClass;
import com.neteast.common.core.domain.entity.SysDictData;
import com.neteast.common.utils.DictUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * 说明：数据字典切面类
 * @author hj
 * @date 2022/1/13 15:21
 */
@Aspect
@Component
public class DictDataAspect {

    @Pointcut("@annotation(dictDataClass)")
    public void doDictDataClass(DictDataClass dictDataClass) {
    }

    @Around("@annotation(dictDataClass)")
    public Object translation(final ProceedingJoinPoint pjp, DictDataClass dictDataClass) throws Throwable {
        return doTranslation(pjp);
    }

    private Map<String, List<SysDictData>> dictListMap;

    public Object doTranslation(ProceedingJoinPoint pjp) throws Throwable {
        dictListMap = new HashMap<>();
        Object result = pjp.proceed();
        if (result == null) {
            return result;
        }
        Object obj;
        if (result instanceof List || result instanceof ArrayList) {
            List olist = ((List) result);
            if (olist.size() == 0) {
                return result;
            }
            obj = olist.get(0);
        } else {
            obj = result;
        }
        List<Map<String, Object>> dictParams = boDict(obj.getClass());
        if (dictParams.size() == 0) {
            return result;
        }
        if (result instanceof List || result instanceof ArrayList) {
            for (Object o : (List) result) {
                sign(o, dictParams);
            }
        }else {
            sign(result, dictParams);
        }
        return result;
    }

    /**
     * 单个设置值
     *
     * @param result
     * @param dictParams
     */
    public void sign(Object result, List<Map<String, Object>> dictParams) throws Exception {
        Map<String, String> dictLabelMap = new HashMap<>();
        for (Map<String, Object> dictParam : dictParams) {
            String changeType = (String)dictParam.get("changeType");
            String tableName = (String)dictParam.get("tableName");
            String tableId = (String)dictParam.get("tableId");
            String columnName = (String)dictParam.get("columnName");
            String[] dictType = (String[])dictParam.get("dictType");
            String dictFieldName = (String)dictParam.get("dictFieldName");
            String valueField = (String)dictParam.get("valueField");
            Boolean isReplaceSelf = (Boolean)dictParam.get("isReplaceSelf");
            String targetField = (String)dictParam.get("targetField");
            String sourceValue = StrUtil.toString(getGetMethod(result,StringUtils.isNotBlank(valueField)?valueField:dictFieldName)) ;

            for(String o : dictType){
                List<SysDictData> sysDictDataList = dictListMap.get(o);
                if(ObjectUtil.isNull(sysDictDataList)){
                    dictListMap.put(o, DictUtils.getDictCache(o));
                }
            }
            if("table".equals(changeType)){

            }else{
                String dictLabel = "";
                for (String o : dictType) {
                    dictLabel = DictUtils.getDictDataLabel(dictListMap.get(o), sourceValue);
                    if (StringUtils.isNotEmpty(dictLabel)) {
                        break;
                    }
                }
                dictLabelMap.put(dictFieldName+"Label",StringUtils.isBlank(dictLabel)?"":dictLabel);
                if(StringUtils.isNotEmpty(targetField)){
                    setValue(result, result.getClass(), targetField, String.class, dictLabel);
                }else{
                    setValue(result, result.getClass(), dictFieldName, String.class, dictLabel);
                }
            }
        }
    }

    /**
     * 获取bo中属性值
     *
     * @param cla
     * @return
     */
    private List<Map<String, Object>> boDict(Class cla) {
        //获取自己私有的属性
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for(Class<?> clazz = cla ; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                Field[] fields = clazz.getDeclaredFields();
                Map<String, Object> map;
                DictData dataDict;
                for (Field field : fields) {
                    if (field.isAnnotationPresent(DictData.class)) {
                        map = new HashMap<String, Object>();
                        dataDict = field.getAnnotation(DictData.class);
                        map.put("changeType","dict");
                        map.put("dictType", dataDict.dictType());
                        map.put("valueField", dataDict.valueField());
                        map.put("dictFieldName", field.getName());
                        map.put("isReplaceSelf", dataDict.isReplaceSelf());
                        map.put("targetField", dataDict.targetField());
                        list.add(map);
                    }
                }
            } catch (Exception e) {

            }
        }
        return list;
    }


    /**
     * 根据属性，获取get方法
     * @param ob 对象
     * @param name 属性名
     * @return
     * @throws Exception
     */
    public static Object getGetMethod(Object ob , String name)throws Exception{
        Method[] m = ob.getClass().getMethods();
        for(int i = 0;i < m.length;i++){
            if(("get"+name).toLowerCase().equals(m[i].getName().toLowerCase())){
                return m[i].invoke(ob);
            }
        }
        return null;
    }

    /**
     * 根据属性，拿到set方法，并把值set到对象中
     * @param obj 对象
     * @param clazz 对象的class
     * @param filedName 需要设置值得属性
     * @param typeClass
     * @param value
     */
    public static void setValue(Object obj,Class<?> clazz,String filedName,Class<?> typeClass,Object value){
        filedName = removeLine(filedName);
        String methodName = "set" + filedName.substring(0,1).toUpperCase()+filedName.substring(1);
        try{
            Method method =  getDeclaredMethod(obj,methodName,typeClass);
            if(method != null){
                method.invoke(obj, new Object[]{getClassTypeValue(typeClass, value)});
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


    public static Method getDeclaredMethod(Object object, String methodName, Class<?> ... parameterTypes){
        Method method = null ;
        for(Class<?> clazz = object.getClass() ; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes) ;
                return method ;
            } catch (Exception e) {
            }
        }
        return null;
    }


    /**
     * 通过class类型获取获取对应类型的值
     * @param typeClass class类型
     * @param value 值
     * @return Object
     */
    private static Object getClassTypeValue(Class<?> typeClass, Object value){
        if(typeClass == int.class  || value instanceof Integer){
            if(null == value){
                return 0;
            }
            return value;
        }else if(typeClass == short.class){
            if(null == value){
                return 0;
            }
            return value;
        }else if(typeClass == byte.class){
            if(null == value){
                return 0;
            }
            return value;
        }else if(typeClass == double.class){
            if(null == value){
                return 0;
            }
            return value;
        }else if(typeClass == long.class){
            if(null == value){
                return 0;
            }
            return value;
        }else if(typeClass == String.class){
            if(null == value){
                return "";
            }
            return value;
        }else if(typeClass == boolean.class){
            if(null == value){
                return true;
            }
            return value;
        }else if(typeClass == BigDecimal.class){
            if(null == value){
                return new BigDecimal(0);
            }
            return new BigDecimal(value+"");
        }else {
            return typeClass.cast(value);
        }
    }
    /**
     * 处理字符串  如：  abc_dex ---> abcDex
     * @param str
     * @return
     */
    public static  String removeLine(String str){
        if(null != str && str.contains("_")){
            int i = str.indexOf("_");
            char ch = str.charAt(i+1);
            char newCh = (ch+"").substring(0, 1).toUpperCase().toCharArray()[0];
            String newStr = str.replace(str.charAt(i+1), newCh);
            String newStr2 = newStr.replace("_", "");
            return newStr2;
        }
        return str;
    }


}
