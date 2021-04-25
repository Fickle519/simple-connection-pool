package test;

import com.exh.simulateconnpool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestConnPool {

    public static void main(String[] args) {
        TestConnRunner runner = new TestConnRunner();
        for (int i = 0; i < 6; i++) {
            new Thread(runner).start();
        }
    }
}
