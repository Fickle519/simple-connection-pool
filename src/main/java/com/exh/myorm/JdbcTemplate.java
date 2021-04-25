package com.exh.myorm;

import com.exh.simulateconnpool.ConnectionPool;

import java.sql.*;

public abstract class JdbcTemplate {
    protected Connection connection;
    protected PreparedStatement pstat;
    protected ResultSet rs;


    public Object execute(String sql, Object[] params) throws Exception{
        getConnection();
        getStatement(sql, params);
        Object result = executeSQL();
        close();
        return result;
    }

    public void getConnection() throws Exception {
        connection = ConnectionPool.getInstance().getConnection();
    }

    public void getStatement(String sql,Object...params) throws SQLException {
        pstat = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstat.setObject(i+1,params[i]);
        }
    }

    public abstract Object executeSQL() throws SQLException;

    public void close() throws Exception{
        if(rs!=null){
            rs.close();
        }

        if(pstat!=null){
            pstat.close();
        }

        if(connection!=null){
            connection.close();
        }
    }

}