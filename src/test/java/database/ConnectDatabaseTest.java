package database;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConnectDatabaseTest {
    private Connection con;

    @Before
    public void setUp() {
        con = ConnectDatabase.connectDB();
    }

    @After
    public void tearDown() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    @Test
    public void testConnectionNotNull() {
        assertNotNull("连接对象不应为null", con);
    }

    @Test
    public void testConnectionValid() throws SQLException {
        assertFalse("连接应为活动状态", con.isClosed());
        assertTrue("连接应有效", con.isValid(2));
    }
}