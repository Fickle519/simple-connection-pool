package com.exh.myorm.util;

import java.util.Arrays;

public class SqlPaseredObject {
    private String sql;
    private Object[] params;

    public SqlPaseredObject() {
    }

    public SqlPaseredObject(String sql, Object[] params) {
        this.sql = sql;
        this.params = params;
    }

    @Override
    public String toString() {
        return "SqlPaseredObject{" +
                "sql='" + sql + '\'' +
                ", params=" + Arrays.toString(params) +
                '}';
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
