package test;

import com.exh.myorm.JdbcUtil;
import com.exh.myorm.strategy.Orm;
import test.model.ScoreInfo;

import java.util.List;
import java.util.Map;

public class TestStrategyQuery {
    public static void main(String[] args) {
        JdbcUtil jdbcUtil = new JdbcUtil();
        String sql = "select * from score_info";
        try {
            List<ScoreInfo> scoreInfoList = jdbcUtil.selectList(sql, new Orm<ScoreInfo>() {

                public ScoreInfo orm(Map<String, Object> map) {
                    ScoreInfo scoreInfo = new ScoreInfo();
                    scoreInfo.setSid((String) map.get("SID"));
                    scoreInfo.setChinese((Float) map.get("CHINESE"));
                    scoreInfo.setMath((Float)map.get("ENGLISH"));
                    scoreInfo.setEnglish((Float)map.get("MATH"));
                    return scoreInfo;
                }
            });
            System.out.println(scoreInfoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
