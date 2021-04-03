package com.exh.simulateconnpool;

import com.exh.simulateconnpool.adapter.AdapterConnection;
import com.exh.simulateconnpool.config.ConfigClass;

import java.sql.*;


public class Conn extends AdapterConnection {

    private static String driverClassName;
    private static String url;
    private static String username;
    private static String password;

    private Connection connection;
    private Boolean isUsed=false;

    static {
        driverClassName = ConfigClass.getConnConfigValue("driver-class-name");
        url = ConfigClass.getConnConfigValue("url");
        username = ConfigClass.getConnConfigValue("username");
        password = ConfigClass.getConnConfigValue("password");
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    {
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

//--------------overwrite the functions in Connection interface--------------
// --------------              static proxy strategy            --------------

    @Override
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    @Override
    public void close() throws SQLException {
        this.setUsed(false);
    }
    @Override
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {

        connection.commit();
    }

    @Override
    public void rollback() throws SQLException {
        connection.rollback();
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        connection.setReadOnly(readOnly);
    }
}
