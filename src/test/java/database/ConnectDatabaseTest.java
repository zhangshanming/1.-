package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import static org.junit.Assert.*;

public class ConnectDatabaseTest {
    private Connection con;

    @Before
    public void setUp() {
        con = ConnectDatabase.connectDB();
    }

    @After
    public void tearDown() throws Exception {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    @Test
    public void testConnectionNotNull() {
        assertNotNull("连接对象不应为null", con);
    }

    @Test
    public void testConnectionValid() throws Exception {
        assertFalse("连接应为活动状态", con.isClosed());
        assertTrue("连接应有效", con.isValid(2));
    }
}