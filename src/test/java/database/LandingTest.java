package database;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 测试 Login 功能模块，包括密码验证和管理员判断
 */
public class LandingTest {

    private static final String TEST_USER = "test_user_login";
    private static final String TEST_PASSWORD = "test_pass";
    private static final String TEST_NAME = "测试用户";
    private static final String TEST_STUDENTID = "999999";

    @Before
    public void setUp() {
        // 插入一个测试用户（管理员）
        try {
            Connection con = ConnectDatabase.connectDB();
            String sql = "INSERT INTO usertable(user, studentid, name, password, admin) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, TEST_USER);
            pre.setString(2, TEST_STUDENTID);
            pre.setString(3, TEST_NAME);
            pre.setString(4, TEST_PASSWORD);
            pre.setInt(5, 1); // 管理员
            pre.executeUpdate();
            con.close();
        } catch (Exception e) {
            // 可以忽略主键重复的异常
        }
    }

    @After
    public void tearDown() {
        // 删除测试用户
        try {
            Connection con = ConnectDatabase.connectDB();
            String sql = "DELETE FROM usertable WHERE user = ?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, TEST_USER);
            pre.executeUpdate();
            con.close();
        } catch (Exception e) {
        }
    }

    @Test
    public void testLoginSuccess() {
        Boolean result = Landing.test(TEST_USER, TEST_PASSWORD);
        Assert.assertEquals("用户名密码正确时应返回true", Boolean.TRUE, result);
    }

    @Test
    public void testLoginWrongPassword() {
        Boolean result = Landing.test(TEST_USER, "wrong_password");
        Assert.assertEquals("密码错误应返回false", Boolean.FALSE, result);
    }

    @Test
    public void testLoginUserNotFound() {
        Boolean result = Landing.test("nonexistent_user", "any_password");
        Assert.assertEquals("不存在的用户名应返回false", Boolean.FALSE, result);
    }

    @Test
    public void testSureadminTrue() {
        boolean result = Landing.sureadmin(TEST_USER);
        Assert.assertTrue("测试用户应为管理员", result);
    }

    @Test
    public void testSureadminFalse() throws Exception {
        // 添加一个普通用户
        try {
            Connection con = ConnectDatabase.connectDB();
            String sql = "INSERT INTO usertable(user, studentid, name, password, admin) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, "normal_user");
            pre.setString(2, "888888");
            pre.setString(3, "普通用户");
            pre.setString(4, "normal_pass");
            pre.setInt(5, 0); // 非管理员
            pre.executeUpdate();
            con.close();
        } catch (Exception e) {
        }

        boolean result = Landing.sureadmin("normal_user");
        Assert.assertFalse("普通用户不应为管理员", result);

        // 删除普通用户
        try {
            Connection con = ConnectDatabase.connectDB();
            String sql = "DELETE FROM usertable WHERE user = ?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, "normal_user");
            pre.executeUpdate();
            con.close();
        } catch (Exception e) {
        }
    }
}
