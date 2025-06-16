package view;

import org.junit.Test;  // 改为 JUnit 4

import java.awt.*;

import static org.junit.Assert.*;  // 改为 JUnit 4

public class RegisterTest {
    @Test
    public void testRegistrationUI() {
        try {
            Register register = new Register();
            assertNotNull("注册界面应该被创建", register);  // 调整断言参数顺序
        } catch (HeadlessException e) {
            // 在无头环境跳过GUI测试
        }
    }
}