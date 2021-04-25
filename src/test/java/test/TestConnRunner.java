package test;


import com.exh.simulateconnpool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestConnRunner implements Runnable{


    @Override
    public void run() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from score_info");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.print(resultSet.getString("sid")+" ");
                System.out.print(resultSet.getString("chinese")+" ");
                System.out.print(resultSet.getString("math")+" ");
                System.out.println(resultSet.getString("english")+" ");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
