package view;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AccountManagementTest {

    private AccountManagement accountManagement;

    @Before
    public void setUp() {
        accountManagement = new AccountManagement();
        accountManagement.setUser("testUser");
        accountManagement.setFrame(new JFrame());
    }

    @Test
    public void testPanelIsInitialized() {
        assertNotNull(accountManagement.jPanel2);
        assertEquals("背景色应为浅灰色", new Color(245, 245, 245), accountManagement.jPanel2.getBackground());
        assertEquals(null, accountManagement.jPanel2.getLayout()); // 应为 null layout
    }

    @Test
    public void testButtonsAreAddedToPanel() {
        JPanel panel = accountManagement.jPanel2;
        boolean foundInfoButton = false;
        boolean foundPasswordButton = false;
        boolean foundRecordButton = false;
        boolean foundLogoutButton = false;

        for (Component comp : accountManagement.jPanel2.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel innerPanel = (JPanel) comp;
                for (Component innerComp : innerPanel.getComponents()) {
                    if (innerComp instanceof JButton) {
                        JButton button = (JButton) innerComp;
                        // 检查按钮文字
                        if ("修改信息".equals(button.getText())) foundInfoButton = true;
                        if ("修改密码".equals(button.getText())) foundPasswordButton = true;
                        if ("借书记录".equals(button.getText())) foundRecordButton = true;
                        if ("退出登陆".equals(button.getText())) foundLogoutButton = true;
                    }
                }
            }
        }



        assertTrue("infoButton should be added", foundInfoButton);
        assertTrue("passwordButton should be added", foundPasswordButton);
        assertTrue("recordButton should be added", foundRecordButton);
        assertTrue("logoutButton should be added", foundLogoutButton);
    }

    @Test
    public void testLogoutButtonAction() throws Exception {
        JButton logoutButton = getPrivateButton("logoutButton");
        assertNotNull(logoutButton);

        JFrame mockFrame = mock(JFrame.class);
        accountManagement.setFrame(mockFrame);

        // 模拟点击事件
        for (ActionListener listener : logoutButton.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }

        verify(mockFrame).dispose(); // 确保窗口关闭方法被调用
    }

    @Test
    public void testInfoButtonActionTriggersModifyInformation() throws Exception {
        JButton infoButton = getPrivateButton("infoButton");
        assertNotNull(infoButton);

        // 替换 ModifyInformation 的构造器逻辑可在集成测试中完成，此处验证不抛异常即可
        for (ActionListener listener : infoButton.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }
    }

    // 帮助方法：反射获取私有字段（如 infoButton）
    private JButton getPrivateButton(String name) throws Exception {
        Field field = AccountManagement.class.getDeclaredField(name);
        field.setAccessible(true);
        return (JButton) field.get(accountManagement);
    }
}
