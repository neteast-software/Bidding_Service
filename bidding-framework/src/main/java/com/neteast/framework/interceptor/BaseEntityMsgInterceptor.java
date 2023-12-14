package com.neteast.framework.interceptor;

import com.neteast.common.annotation.CreateBy;
import com.neteast.common.annotation.CreateTime;
import com.neteast.common.annotation.UpdateBy;
import com.neteast.common.annotation.UpdateTime;
import com.neteast.common.core.domain.BaseEntity;
import com.neteast.common.core.domain.entity.SysUser;
import com.neteast.common.utils.SecurityUtils;
import com.neteast.framework.web.domain.server.Sys;
import com.neteast.system.domain.SysLogininfor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lzp
 * @date 2023年11月20 9:43
 */

@Slf4j
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class BaseEntityMsgInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        //插入或者更新进行处理
//        if (SqlCommandType.INSERT.equals(sqlCommandType)||SqlCommandType.UPDATE.equals(sqlCommandType)){
//            Object parameter = invocation.getArgs()[1];
//            if (SysUser.class.isAssignableFrom(parameter.getClass())|| SysLogininfor.class.isAssignableFrom(parameter.getClass())){
//                return invocation.proceed();
//            }
//            if (parameter instanceof MapperMethod.ParamMap){
//                MapperMethod.ParamMap map = (MapperMethod.ParamMap)parameter;
//                Object obj = map.get("list");
//                List<?> list = (List<?>) obj;
//                if (list!=null){
//                    for (Object o:list){
//                        setParameter(o,sqlCommandType);
//                    }
//                }
//            }else {
//                setParameter(parameter,sqlCommandType);
//            }
//        }
        return invocation.proceed();
    }

    private void setParameter(Object parameter,SqlCommandType sqlCommandType) throws Throwable {
        Class<?> aClass = parameter.getClass();

        //该类继承父类
        if (BaseEntity.class.isAssignableFrom(aClass)){
            Field[] declaredFields = aClass.getSuperclass().getDeclaredFields();
            for (Field field : declaredFields) {
                if (SqlCommandType.INSERT.equals(sqlCommandType)){
                    if (field.getAnnotation(CreateBy.class)!=null){
                        field.setAccessible(true);
                        field.set(parameter, SecurityUtils.getUsername());
                    }
                    if (field.getAnnotation(CreateTime.class)!=null){
                        field.setAccessible(true);
                        field.set(parameter,new Date());
                    }
                }else {
                    if (field.getAnnotation(UpdateBy.class)!=null){
                        field.setAccessible(true);
                        field.set(parameter,SecurityUtils.getUsername());
                    }
                    if (field.getAnnotation(UpdateTime.class)!=null){
                        field.setAccessible(true);
                        field.set(parameter,new Date());
                    }
                }
            }
        }
    }
}
