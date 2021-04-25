package com.exh.simulateconnpool;

import com.exh.simulateconnpool.config.ConfigClass;
import com.exh.simulateconnpool.exception.ConnectionPoolBusyException;
import com.exh.simulateconnpool.exception.InitConnectionPoolException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private List<Connection> pool;

    private volatile static ConnectionPool connectionPool;////avoiding Instruction reordering and let the other threads see changes immediately.
    private ConnectionPool(){}

    public static ConnectionPool getInstance() throws Exception {
        if(connectionPool==null){
            synchronized (ConnectionPool.class){
                if(connectionPool==null){
                   connectionPool = (ConnectionPool) Class.forName("com.exh.simulateconnpool.ConnectionPool").newInstance();
                }
            }
        }
        return connectionPool;
    }


    private void initPoolConnection(){
        Integer minCount = Integer.parseInt(ConfigClass.getConnConfigValue("minConnection"));
        if(pool!=null && pool.size()!=0){
            throw new InitConnectionPoolException("initilize ConnectionPool occurred nested Exception:the pool contains available connections");
        }else {
            pool = new ArrayList<>();
        }
        for (int i = 0; i < minCount; i++) {
            pool.add(new Conn());
        }
    }

    private synchronized Connection getResultConn(){
        if(pool==null || pool.size()==0){//lazy init
            initPoolConnection();
        }
        Connection resultConn=null;
        for(Connection connection:pool){
            Conn conn = (Conn) connection;
            if(conn.getUsed()==false){
                conn.setUsed(true);
                resultConn = conn;
                break;
            }
        }
        return resultConn;


    }
    public Connection getConnection() throws Exception {

        Connection connection = this.getResultConn();
        int count=0;
        Integer waitSeconds = Integer.parseInt(ConfigClass.getConnConfigValue("waitSeconds"));
        while (connection==null && count<waitSeconds*10){
            Thread.sleep(100);
            connection = this.getResultConn();
            count++;
        }
        if(connection==null){
            throw new ConnectionPoolBusyException("no available Connection could be used");
        }
        return connection;
    }



}
