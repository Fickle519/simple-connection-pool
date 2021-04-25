package com.exh.myorm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcQuery extends JdbcTemplate {


    @Override
    public Object executeSQL() throws SQLException {
        ResultSet rs =  pstat.executeQuery();
        List<Map<String, Object>> resultList = new ArrayList<>();
        while (rs.next()){
            Map<String,Object> map = new HashMap<>();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                String columnName = rs.getMetaData().getColumnName(i);
                Object value = rs.getObject(columnName);
                map.put(columnName.toUpperCase(),value);
            }
            resultList.add(map);
        }
        return resultList;
    }
}
