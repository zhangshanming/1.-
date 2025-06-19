package database;

import org.junit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class CategoryTest {

    private static final String ORIGINAL_CATEGORY = "TestCategory";
    private static final String UPDATED_CATEGORY = "UpdatedCategory";

    @Before
    public void setUp() {
        // 确保测试开始前表中无同名记录
        deleteCategoryIfExists(ORIGINAL_CATEGORY);
        deleteCategoryIfExists(UPDATED_CATEGORY);
    }

    @After
    public void tearDown() {
        // 清理测试数据
        deleteCategoryIfExists(ORIGINAL_CATEGORY);
        deleteCategoryIfExists(UPDATED_CATEGORY);
    }

    @Test
    public void testAddcategory() {
        boolean result = Category.addcategory(ORIGINAL_CATEGORY);
        assertTrue("添加类别应成功", result);

        // 验证是否写入数据库
        assertTrue("数据库应包含新类别", categoryExists(ORIGINAL_CATEGORY));
    }

    @Test
    public void testSetcategory() {
        // 添加初始分类
        assertTrue(Category.addcategory(ORIGINAL_CATEGORY));

        // 修改分类名
        boolean result = Category.setcategory(ORIGINAL_CATEGORY, UPDATED_CATEGORY);
        assertTrue("修改类别应成功", result);

        // 验证旧分类消失，新分类存在
        assertFalse("旧类别应不存在", categoryExists(ORIGINAL_CATEGORY));
        assertTrue("新类别应存在", categoryExists(UPDATED_CATEGORY));
    }

    @Test
    public void testAddDuplicateCategory() {
        Category.addcategory(ORIGINAL_CATEGORY);
        boolean secondAdd = Category.addcategory(ORIGINAL_CATEGORY);
        assertFalse("重复添加应失败", secondAdd);
    }

    @Test
    public void testUpdateNonExistentCategory() {
        boolean result = Category.setcategory("NonExistent", "AnotherCategory");
        assertFalse("修改不存在的类别应失败", result);
    }

    // ---------- 工具方法 ----------

    private boolean categoryExists(String category) {
        try (Connection con = ConnectDatabase.connectDB()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM bookcategory WHERE Category = ?");
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    private void deleteCategoryIfExists(String category) {
        try (Connection con = ConnectDatabase.connectDB()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM bookcategory WHERE Category = ?");
            ps.setString(1, category);
            ps.executeUpdate();
        } catch (Exception ignored) {
        }
    }
}
