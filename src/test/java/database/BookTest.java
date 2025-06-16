package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    private static final String TEST_CATEGORY = "TestCategory";
    private static final String TEST_BOOK = "TestBook";
    private int testBookId;

    @Before
    public void setUp() {
        // 添加测试图书
        Book.addbook(TEST_CATEGORY, TEST_BOOK, "TestAuthor", "TestPress");
        testBookId = getTestBookId(); // 需要实现这个方法
    }

    @After
    public void tearDown() {
        // 清理测试数据
        Book.deletebook(testBookId);
    }

    @Test
    public void testBookAdd() {
        assertTrue("图书应该被添加", testBookId > 0);
    }

    @Test
    public void testBookDelete() {
        Book.deletebook(testBookId);
        assertNull("图书应该被删除", getBookById(testBookId)); // 需要实现getBookById
    }

    // 辅助方法（需要根据实际实现）
    private int getTestBookId() {
        // 实现获取测试图书ID的逻辑
        return 1; // 示例
    }

    private Object getBookById(int testBookId) {
        // 实现根据ID查询图书的逻辑
        return null; // 示例
    }
}