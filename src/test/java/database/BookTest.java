package database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    private static final String TEST_CATEGORY = "TestCategory";
    private static final String TEST_BOOK = "TestBook";
    private int testBookId;

    @BeforeEach
    public void setUp() {
        // 添加测试数据
        Book.addbook(TEST_CATEGORY, TEST_BOOK, "TestAuthor", "TestPress");
        testBookId = getTestBookId();
    }

    @AfterEach
    public void tearDown() {
        // 清理测试数据
        Book.deletebook(testBookId);
        Category.setcategory(TEST_CATEGORY, "DeletedCategory");
    }

    @Test
    public void testAddBook() {
        assertTrue(testBookId > 0, "图书应该被添加");
    }

    @Test
    public void testDeleteBook() {
        Book.deletebook(testBookId);
        int deletedId = getTestBookId();
        assertEquals(0, deletedId, "图书应该被删除");
    }

    private int getTestBookId() {
        // 实现获取测试图书ID的逻辑
        return 1; // 简化示例，实际需从数据库查询
    }
}