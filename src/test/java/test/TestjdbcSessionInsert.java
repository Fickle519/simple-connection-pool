package test;

import com.exh.myorm.JdbcSession;

public class TestjdbcSessionInsert {
    public static void main(String[] args) {
        JdbcSession jdbcSession = new JdbcSession();
//        try {
//            int updateCount = jdbcSession.insert("insert into score_info values(#{sid},#{chinese},#{english},#{math})",new ScoreInfo("990101",90F,89F,88F));
//            System.out.println(updateCount);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try{
//            jdbcSession.update("update score_info set math=#{math},chinese=#{chinese},english=#{english} where sid=#{sid}", new ScoreInfo("990101", 10.1F,10.2F,100F));
//        }catch(Exception e){
//            e.printStackTrace();
//         try{
//            int i = jdbcSession.delete("delete from score_info where sid=#{sid}", new ScoreInfo("990101", 10.1F,10.2F,100F));
//             System.out.println(i);
//        }catch(Exception e){
//            e.printStackTrace();
//        }

//        try {
//            Map<String,Object> map = new HashMap<>();
//            map.put("sid", "2018083068");
//            List< ScoreInfo> scoreInfoList = jdbcSession.selectList("select * from score_info where sid=#{sid}",ScoreInfo.class, "2018083068");
//            System.out.println(scoreInfoList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
