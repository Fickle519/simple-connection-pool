package com.exh.myorm.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlPaser {
    public static SqlPaseredObject paserSQL(String sql,Object paramObject) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> paramList = new ArrayList<>();
        sql = sql.trim();
        while (true){
            int i1 = sql.indexOf("#{");
            int i2 = sql.indexOf("}");
            if(i1!=-1 && i2!=-1 && i1<i2){
                String key = sql.substring(i1+2,i2).trim();
                paramList.add(key);
                sql = sql.substring(0, i1) + "?" + sql.substring(i2 + 1);
            }else {
                break;
            }
        }

        List<Object> valueList = new ArrayList<>();
        if(paramObject!=null){
            Class paramType = paramObject.getClass();
            if(paramType == Integer.class || paramType == int.class
                    || paramType == Double.class || paramType == double.class
                    || paramType == String.class
            ) {
                valueList.add(paramObject);
            }else if(paramObject instanceof Map){
                Map map = (Map) paramObject;
                for(String param:paramList){
                    valueList.add(map.get(param));
                }
            }else {
                for(String fieldName : paramList){
                    String methodName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                    Method method = paramType.getMethod(methodName);
                    valueList.add(method.invoke(paramObject));
                }
            }
        }
        return new SqlPaseredObject(sql,valueList.toArray());

    }
}
