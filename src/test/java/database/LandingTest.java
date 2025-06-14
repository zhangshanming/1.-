package database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LandingTest {
    @BeforeEach
    public void setUp() {
        // 准备测试数据
        Adduser.adduser("testUser", "123456", "Test User", "password");
    }

    @Test
    public void testValidLogin() {
        assertTrue(Landing.test("testUser", "password"), "有效的登录应该返回true");
    }

    @Test
    public void testInvalidPassword() {
        assertFalse(Landing.test("testUser", "wrongpass"), "无效密码应该返回false");
    }

    @Test
    public void testNonExistingUser() {
        assertFalse(Landing.test("nonExisting", "password"), "不存在的用户应该返回false");
    }

    @Test
    public void testAdminCheck() {
        assertTrue(Landing.sureadmin("adminUser"), "管理员检查应该正确");
    }
}