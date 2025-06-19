package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.table.DefaultTableModel;

import static org.junit.Assert.*;

public class FindBorrowTest {

    private static final String TEST_USER = "test_user";
    private static final String TEST_CATEGORY = "test_category";
    private static final String TEST_BOOKNAME = "test_book";
    private static final String TEST_AUTHOR = "test_author";
    private static final String TEST_PRESS = "test_press";

    private int insertedBookId;


    @After
    public void tearDown() {
        // 清理借书记录和图书
        BorrowRecords.Return(TEST_USER, insertedBookId);
        Book.deletebook(insertedBookId);
    }

    @Test
    public void testAllborrow() {
        DefaultTableModel model = new DefaultTableModel(0, 0);
        FindBorrow.allborrow(model);
        assertTrue("所有借书记录中应包含测试数据", tableContains(model, TEST_USER));
    }

    @Test
    public void testUserborrow() {
        DefaultTableModel model = new DefaultTableModel(0, 0);
        FindBorrow.userborrow(model, TEST_USER);
        assertTrue("按用户名应能查询到借书记录", tableContains(model, TEST_BOOKNAME));
    }

    @Test
    public void testBookidborrow() {
        DefaultTableModel model = new DefaultTableModel(0, 0);
        FindBorrow.bookidborrow(model, insertedBookId);
        assertTrue("按书号应能查询到借书记录", tableContains(model, TEST_USER));
    }

    // 表格中是否包含指定内容
    private boolean tableContains(DefaultTableModel model, String keyword) {
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 0; col < model.getColumnCount(); col++) {
                Object value = model.getValueAt(row, col);
                if (value != null && value.toString().contains(keyword)) {
                    return true;
                }
            }
        }
        return false;
    }
}
