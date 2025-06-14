package database;

import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectDatabaseTest {
    @Test
    public void testConnection() {
        Connection con = ConnectDatabase.connectDB();
        assertNotNull(String.valueOf(con), "连接不能为null");
        try {
            assertFalse(con.isClosed(), "连接应该是活动的");
        } catch (Exception e) {
            fail("连接检查异常: " + e.getMessage());
        }
    }
}