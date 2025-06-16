package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LandingTest {
    private static final String TEST_USER = "testUser";
    private static final String TEST_PASSWORD = "password";

    @Before
    public void setUp() {
        // 创建测试用户
        Adduser.adduser(TEST_USER, "123456", "Test User", TEST_PASSWORD);
    }

    @After
    public void tearDown() {
        // 删除测试用户
        UpdateAdmin.deleteuser(TEST_USER);
    }

    @Test
    public void testValidLogin() {
        assertTrue("有效的登录应该返回true", Landing.test(TEST_USER, TEST_PASSWORD));
    }

    @Test
    public void testInvalidPassword() {
        assertFalse("无效密码应该返回false", Landing.test(TEST_USER, "wrongpass"));
    }

    @Test
    public void testNonExistingUser() {
        assertFalse("不存在的用户应该返回false", Landing.test("nonExisting", "password"));
    }
}