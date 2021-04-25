package com.exh.myorm;

import java.sql.SQLException;

public class JdbcUpdate extends JdbcTemplate{

    public Object executeSQL() throws SQLException {
        return pstat.executeUpdate();
    }
}
