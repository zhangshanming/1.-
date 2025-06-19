package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.table.DefaultTableModel;

import static org.junit.Assert.*;

public class FindBookTest {

    private static final String CATEGORY = "测试类别";
    private static final String BOOKNAME = "测试书籍";
    private static final String AUTHOR = "测试作者";
    private static final String PRESS = "测试出版社";
    private int insertedBookId;



    @After
    public void tearDown() {
        // 删除测试数据
        Book.deletebook(insertedBookId);
    }

    @Test
    public void testAllbook() {
        DefaultTableModel model = new DefaultTableModel(0, 0);
        FindBook.allbook(model);
        assertTrue("所有图书应包含测试图书", tableContains(model, BOOKNAME));
    }

    @Test
    public void testFindcategory() {
        DefaultTableModel model = new DefaultTableModel(0, 0);
        FindBook.findcategory(model, CATEGORY);
        assertTrue("按类别应能查到测试图书", tableContains(model, BOOKNAME));
    }

    @Test
    public void testFindbookname() {
        DefaultTableModel model = new DefaultTableModel(0, 0);
        FindBook.findbookname(model, BOOKNAME);
        assertTrue("按书名应能查到测试图书", tableContains(model, AUTHOR));
    }

    @Test
    public void testFindauthor() {
        DefaultTableModel model = new DefaultTableModel(0, 0);
        FindBook.findauthor(model, AUTHOR);
        assertTrue("按作者应能查到测试图书", tableContains(model, BOOKNAME));
    }

    @Test
    public void testFindbookid() {
        DefaultTableModel model = new DefaultTableModel(0, 0);
        FindBook.findbookid(model, insertedBookId);
        assertTrue("按书号应能查到测试图书", tableContains(model, CATEGORY));
    }

    // 辅助方法：从数据库获取刚刚插入的测试图书编号

    // 辅助方法：检查表格是否包含指定内容
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
