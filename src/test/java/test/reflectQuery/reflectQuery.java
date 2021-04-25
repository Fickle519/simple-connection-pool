package test.reflectQuery;

import com.exh.myorm.JdbcUtil;
import test.model.ScoreInfo;

import java.util.List;

public class reflectQuery {
    public static void main(String[] args) throws Exception {
        JdbcUtil jdbcUtil = new JdbcUtil();
        String sql = "select * from score_info";
        List<ScoreInfo> scoreInfoList = jdbcUtil.selectList(sql, ScoreInfo.class);
        System.out.println(scoreInfoList);
    }
}
