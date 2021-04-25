package test;

import com.exh.myorm.JdbcUpdate;

public class TestORMFrameUpdate {
    public static void main(String[] args) {
        JdbcUpdate jdbcUpdate = new JdbcUpdate();
        String sql = "insert into score_info values(?,?,?,?)";
        try {
            Object result = jdbcUpdate.execute(sql,new Object[]{"2018083068",99,98,99});
            System.out.println((Integer) result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
