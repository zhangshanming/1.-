package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import database.Landing;

/**
 * 现代化登陆界面
 */
public class Land {
	// 使用更现代的配色方案
	private static final Color PRIMARY_COLOR = new Color(30, 136, 229);
	private static final Color SECONDARY_COLOR = new Color(255, 255, 255);
	private static final Color ACCENT_COLOR = new Color(255, 152, 0);
	private static final Color BACKGROUND_COLOR = new Color(245, 248, 250);

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;

	public Land() {
		createUI();
	}

	private void createUI() {
		// 创建主框架
		frame = new JFrame("图书管理系统");
		frame.setSize(420, 500);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 设置更现代的最小图标
		ImageIcon icon = new ImageIcon("img/logo.png");
		frame.setIconImage(icon.getImage());

		// 创建主面板
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(BACKGROUND_COLOR);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

		// 添加标题
		JLabel titleLabel = new JLabel("欢迎回来");
		titleLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 28));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		mainPanel.add(titleLabel);

		// 添加副标题
		JLabel subtitleLabel = new JLabel("请输入您的账号密码");
		subtitleLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		subtitleLabel.setForeground(new Color(120, 144, 156));
		subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		subtitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
		mainPanel.add(subtitleLabel);

		// 创建表单面板
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBackground(BACKGROUND_COLOR);
		formPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

		// 用户名输入框
		JLabel userLabel = new JLabel("用户名");
		userLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		formPanel.add(userLabel);
		formPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		usernameField = new JTextField();
		stylizeTextField(usernameField, "请输入用户名");
		formPanel.add(usernameField);
		formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// 密码输入框
		JLabel passLabel = new JLabel("密码");
		passLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		formPanel.add(passLabel);
		formPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		passwordField = new JPasswordField();
		stylizeTextField(passwordField, "请输入密码");
		formPanel.add(passwordField);

		mainPanel.add(formPanel);

		// 创建按钮面板
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 1, 0, 10));
		buttonPanel.setBackground(BACKGROUND_COLOR);

		// 登录按钮
		JButton loginButton = new JButton("登 录");
		stylizeButton(loginButton, PRIMARY_COLOR);
		loginButton.addActionListener(this::loginAction);

		// 注册按钮
		JButton registerButton = new JButton("注 册");
		stylizeButton(registerButton, SECONDARY_COLOR, PRIMARY_COLOR);
		registerButton.addActionListener(e -> {
			frame.dispose();
			new Register();
		});

		buttonPanel.add(loginButton);
		buttonPanel.add(registerButton);
		mainPanel.add(buttonPanel);

		// 添加忘记密码链接
		JLabel forgotPassword = new JLabel("<html><u>忘记密码?</u></html>");
		forgotPassword.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		forgotPassword.setForeground(new Color(120, 144, 156));
		forgotPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		forgotPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		forgotPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(frame, "请联系系统管理员重置密码");
			}
		});
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(forgotPassword);

		frame.add(mainPanel);
		frame.setVisible(true);
	}

	// 样式化文本框
	private void stylizeTextField(JTextField field, String placeholder) {
		field.setMaximumSize(new Dimension(Short.MAX_VALUE, 45));
		field.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(207, 216, 220), 1),
				BorderFactory.createEmptyBorder(10, 15, 10, 15)
		));
		field.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		// 设置占位符文本
		field.setText(placeholder);
		field.setForeground(Color.GRAY);

		field.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (field.getText().equals(placeholder)) {
					field.setText("");
					field.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (field.getText().isEmpty()) {
					field.setForeground(Color.GRAY);
					field.setText(placeholder);
				}
			}
		});
	}

	// 样式化按钮 (默认样式)
	private void stylizeButton(JButton button, Color bgColor) {
		stylizeButton(button, bgColor, Color.WHITE);
	}

	// 样式化按钮 (可指定背景和文字颜色)
	private void stylizeButton(JButton button, Color bgColor, Color textColor) {
		button.setBackground(bgColor);
		button.setForeground(textColor);
		button.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
		button.setFocusPainted(false);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setContentAreaFilled(false);
		button.setOpaque(true);

		// 添加鼠标悬停效果
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(bgColor.darker());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(bgColor);
			}
		});
	}

	// 登录动作处理
	private void loginAction(ActionEvent e) {
		String user = usernameField.getText().trim();
		String password = new String(passwordField.getPassword()).trim();

		// 检查占位符文本
		if (user.equals("请输入用户名") || password.equals("请输入密码")) {
			JOptionPane.showMessageDialog(frame, "请输入有效的用户名和密码");
			return;
		}

		if (Landing.test(user, password)) {
			frame.dispose();
			new MainInterface(user);
		} else {
			JOptionPane.showMessageDialog(frame, "用户名或密码错误", "登录失败", JOptionPane.ERROR_MESSAGE);
			passwordField.setText("");
		}
	}

	public static void main(String[] args) {
		// 使用系统UI风格使界面更现代
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// 在主线程中创建UI
		SwingUtilities.invokeLater(() -> new Land());
	}
}