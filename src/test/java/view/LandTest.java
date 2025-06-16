package view;

import org.junit.Test;
import static org.junit.Assert.*;

public class LandTest {
    @Test
    public void testUILoading() {
        try {
            Land land = new Land();
            assertNotNull("登陆界面应该被创建", land);
        } catch (Exception e) {
            fail("UI加载异常: " + e.getMessage());
        }
    }
}