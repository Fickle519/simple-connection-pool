package com.exh.myorm;

import com.exh.myorm.util.SqlPaser;
import com.exh.myorm.util.SqlPaseredObject;

import java.util.List;

/**
 *
 * To extend the JdbcUtil
 */
public class JdbcSession {

    private JdbcUtil jdbcUtil = new JdbcUtil();
    //sql中含有#{}
    public int insert(String sql,Object paramObj) throws Exception {
        SqlPaseredObject sqlPaseredObject = SqlPaser.paserSQL(sql,paramObj);
        return jdbcUtil.insert(sqlPaseredObject.getSql(),sqlPaseredObject.getParams());
    }
    public int update(String sql,Object paramObj) throws Exception {
        SqlPaseredObject sqlPaseredObject = SqlPaser.paserSQL(sql,paramObj);
        return jdbcUtil.update(sqlPaseredObject.getSql(),sqlPaseredObject.getParams());
    }
    public int delete(String sql,Object paramObj) throws Exception {
        SqlPaseredObject sqlPaseredObject = SqlPaser.paserSQL(sql,paramObj);
        return jdbcUtil.delete(sqlPaseredObject.getSql(),sqlPaseredObject.getParams());
    }

    public <T> List<T> selectList(String sql, Class<T> returnClazz, Object paramObj) throws Exception {
        SqlPaseredObject o = SqlPaser.paserSQL(sql,paramObj);
        System.out.println(o.getSql());
        return jdbcUtil.selectList(o.getSql(),returnClazz,o.getParams());
    }
    public <T>T selectOne(String sql, Class<T> returnClazz, Object paramObj) throws Exception {
        SqlPaseredObject o = SqlPaser.paserSQL(sql,paramObj);
        return jdbcUtil.selectOne(o.getSql(),returnClazz,o.getParams());
    }
}
