package test;

import com.exh.myorm.JdbcQuery;
import com.exh.myorm.JdbcUpdate;

import java.util.List;
import java.util.Map;

public class TestORMFrameQuery {
    public static void main(String[] args) {
        JdbcQuery jdbcQuery = new JdbcQuery();
        String sql = "select * from score_info where math = ?";
        try {
            List<Map<String,Object>> mapList = (List<Map<String,Object>>)jdbcQuery.execute(sql,new Object[]{59F});
            System.out.println(mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
