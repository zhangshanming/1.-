package view;

import org.junit.jupiter.api.Test;
import java.awt.HeadlessException;
import static org.junit.jupiter.api.Assertions.*;

public class LandTest {
    @Test
    public void testUILoading() {
        try {
            Land land = new Land();
            assertNotNull(land, "登陆界面应该被创建");
        } catch (HeadlessException e) {
            // 在无头环境跳过GUI测试
        }
    }
}