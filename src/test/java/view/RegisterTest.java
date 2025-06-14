package view;

import org.junit.jupiter.api.Test;
import java.awt.HeadlessException;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest {
    @Test
    public void testRegistrationUI() {
        try {
            Register register = new Register();
            assertNotNull(register, "注册界面应该被创建");
        } catch (HeadlessException e) {
            // 在无头环境跳过GUI测试
        }
    }
}