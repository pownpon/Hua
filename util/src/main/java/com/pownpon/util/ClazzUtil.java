package com.pownpon.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: ClazzUtil
 * Author: HUA
 * Date: 2021/5/5 14:22
 * Description:
 * History:
 */
public class ClazzUtil {

    /**
     * 获取指定对象的泛型Type
     * @param object
     * @return
     */
    public static Type getTClazzType(Object object){
        if(null == object){
            return null;
        }
        Type objectType = object.getClass().getGenericSuperclass();
        if(!(objectType instanceof ParameterizedType)){
            return null;
        }
        ParameterizedType parameterizedType = (ParameterizedType)objectType;
        Type[] targetArray =  parameterizedType.getActualTypeArguments();
        if(null == targetArray || targetArray.length<0){
            return null;
        }
        return targetArray[0];
    }
}
