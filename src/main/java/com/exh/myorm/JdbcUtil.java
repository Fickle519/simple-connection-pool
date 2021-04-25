package com.exh.myorm;

import com.exh.myorm.exception.ResultRowsCountNotMatchedException;
import com.exh.myorm.exception.SqlFormatNotMatchedException;
import com.exh.myorm.strategy.Orm;
import com.exh.myorm.util.ResultLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JdbcUtil {
    public static final String INSERT_SQLFORMATNOTMATCHED_EXCEPTION_LOG="SqlFormatNotMatchedException:nested Exception occurred while sql equals\n";

    public List<Map<String, Object>> executeQuery(String sql,Object...params) throws Exception {
        return (List<Map<String, Object>>)new JdbcQuery().execute(sql, params);
    }


    int executeUpdate(String sql,Object...params) throws Exception {
        return (Integer)new JdbcUpdate().execute(sql, params);
    }
    //specific methods
    int insert(String sql,Object...params) throws Exception {
        if(!sql.trim().substring(0,6).equalsIgnoreCase("insert")){
            throw new SqlFormatNotMatchedException(INSERT_SQLFORMATNOTMATCHED_EXCEPTION_LOG+sql);
        }
        return executeUpdate(sql,params);
    }
    public int update(String sql,Object...params) throws Exception {
        if(!sql.trim().substring(0,6).equalsIgnoreCase("update")){
            throw new SqlFormatNotMatchedException(INSERT_SQLFORMATNOTMATCHED_EXCEPTION_LOG+sql);
        }
        return executeUpdate(sql,params);
    }
    public int delete(String sql,Object...params) throws Exception {
        if(!sql.trim().substring(0,6).equalsIgnoreCase("delete")){
            throw new SqlFormatNotMatchedException(INSERT_SQLFORMATNOTMATCHED_EXCEPTION_LOG+sql);
        }
        return executeUpdate(sql,params);
    }

    public List<Map<String,Object>> selectListMap(String sql,Object...params) throws Exception {
        if(!sql.trim().substring(0,6).equalsIgnoreCase("select")){
            throw new SqlFormatNotMatchedException(INSERT_SQLFORMATNOTMATCHED_EXCEPTION_LOG+sql);
        }
        return executeQuery(sql,params);
    }
    //---------------------------Muti-Query Method---------------------------

    public Map<String,Object> selectMap(String sql,Object...params) throws Exception {
        if(!sql.trim().substring(0,6).equalsIgnoreCase("select")){
            throw new SqlFormatNotMatchedException(INSERT_SQLFORMATNOTMATCHED_EXCEPTION_LOG+sql);
        }
        List<Map<String,Object>> mapList = this.executeQuery(sql,params);

        if(mapList!=null && mapList.size()==1){
            return mapList.get(0);
        }else if(mapList==null){
            return null;
        }else {
            throw new ResultRowsCountNotMatchedException("ResultRowsCountNotMatchedException: nexted Exception occurred while result Count equals"+mapList.size()+" result size must be 1");
        }
    }


    //-----------------------------基于策略进行结果的组装---------------------------------

    public <T>T selectOne(String sql ,Orm<T> orm,Object...params) throws  Exception{
        Map<String, Object> map = selectMap(sql, params);
        T obj=null;
        if(map!=null){
            obj = orm.orm(map);
        }
        return obj;
    }

    //-----------------------------基于策略进行结果的组装---------------------------------
    public <T>List<T> selectList(String sql, Orm<T> orm, Object...params) throws Exception {
        List<Map<String, Object>> mapList = selectListMap(sql,params);
        List<T> resultList = new ArrayList<>();
        for(Map<String, Object> rowMap:mapList){
            T obj = orm.orm(rowMap);
            resultList.add(obj);
        }
        return resultList;
    }


    //--------------通过反射进行对象的组装-------------------------

    public <T> List<T> selectList(String sql,Class<T> returnObjClazz,Object...params) throws Exception {
        List<Map<String, Object>> mapList = selectListMap(sql,params);
        List<T> tList= new ArrayList<>();
        for(Map<String,Object> map:mapList){
            T tobj = ResultLoader.assemblingObj(returnObjClazz,map);
            tList.add(tobj);
        }
        return tList;
    }

    public <T>T selectOne(String sql,Class<T> returnObjClazz,Object...params)throws Exception{
        Map<String, Object> map = this.selectMap(sql,returnObjClazz);
        if(map==null){
            return null;
        }
        T t = ResultLoader.assemblingObj(returnObjClazz,map);
        return t;
    }


}
