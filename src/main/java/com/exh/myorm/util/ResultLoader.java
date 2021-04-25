package com.exh.myorm.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ResultLoader {


    public static <T>T assemblingObj(Class<T> clazz, Map<String,Object> rowMap) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if(clazz==Integer.class || clazz==int.class
        ||  clazz==Float.class  || clazz==float.class
        ||  clazz==Double.class || clazz==double.class
        ||  clazz==String.class){
            for(Object obj:rowMap.values()){
                return (T)obj;
            }
        }
            T t = clazz.newInstance();
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if(methodName.startsWith("set")){
                    String key = methodName.substring(3).toUpperCase();
                    Object val = rowMap.get(key);
                    if(val==null){
                        continue;
                    }
                    method.invoke(t, val);
                }
            }
            return t;
    }
}
