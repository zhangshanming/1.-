package database;

import org.junit.*;

import java.sql.*;

import static org.junit.Assert.*;

public class BorrowRecordsTest {
    private static final String USER = "testuser";
    private static final int BOOK_ID = 9999;
    private static final String BOOK_NAME = "测试书籍";

    @BeforeClass
    public static void beforeAll() throws Exception {
        // 插入测试图书
        Connection con = ConnectDatabase.connectDB();
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO booktable (bookid, category, bookname, author, press, state) VALUES (?, ?, ?, ?, ?, ?)"
        );
        ps.setInt(1, BOOK_ID);
        ps.setString(2, "TestCategory");
        ps.setString(3, BOOK_NAME);
        ps.setString(4, "测试作者");
        ps.setString(5, "测试出版社");
        ps.setString(6, "在馆");
        ps.executeUpdate();
        con.close();
    }

    @AfterClass
    public static void afterAll() throws Exception {
        // 删除测试图书和相关借阅记录
        Connection con = ConnectDatabase.connectDB();
        con.prepareStatement("DELETE FROM borrowrecords WHERE bookid = " + BOOK_ID).executeUpdate();
        con.prepareStatement("DELETE FROM booktable WHERE bookid = " + BOOK_ID).executeUpdate();
        con.close();
    }

    @Test
    public void testBorrow() {
        BorrowRecords.Borrow(USER, BOOK_ID, BOOK_NAME);

        boolean exists = BorrowRecords.comparison(USER, BOOK_ID);
        assertTrue("借阅记录应存在", exists);

        // 检查图书状态是否已改为“外借”
        try (Connection con = ConnectDatabase.connectDB()) {
            PreparedStatement ps = con.prepareStatement("SELECT state FROM booktable WHERE bookid = ?");
            ps.setInt(1, BOOK_ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                assertEquals("外借", rs.getString("state"));
            } else {
                fail("图书未找到");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("数据库异常");
        }
    }

    @Test
    public void testReturn() {
        // 确保已借出
        BorrowRecords.Borrow(USER, BOOK_ID, BOOK_NAME);

        BorrowRecords.Return(USER, BOOK_ID);

        boolean exists = BorrowRecords.comparison(USER, BOOK_ID);
        assertFalse("借阅记录应已归还", exists);

        // 检查图书状态是否改为“在馆”
        try (Connection con = ConnectDatabase.connectDB()) {
            PreparedStatement ps = con.prepareStatement("SELECT state FROM booktable WHERE bookid = ?");
            ps.setInt(1, BOOK_ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                assertEquals("在馆", rs.getString("state"));
            } else {
                fail("图书未找到");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("数据库异常");
        }
    }
}
