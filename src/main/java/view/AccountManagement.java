package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AccountManagement {
	public JPanel jPanel2 = new JPanel();
	private JLabel titleLabel = new JLabel("账户管理");
	private JButton infoButton = new JButton("修改信息");
	private JButton passwordButton = new JButton("修改密码");
	private JButton recordButton = new JButton("借书记录");
	private JButton logoutButton = new JButton("退出登陆");

	// 现代字体
	private Font titleFont = new Font("微软雅黑", Font.BOLD, 48);
	private Font buttonFont = new Font("微软雅黑", Font.BOLD, 24);

	// 配色方案
	private Color primaryColor = new Color(41, 128, 185);    // 主色调
	private Color secondaryColor = new Color(52, 152, 219);  // 次色调
	private Color accentColor = new Color(231, 76, 60);       // 强调色
	private Color backgroundColor = new Color(245, 245, 245); // 背景色
	private Color cardColor = Color.WHITE;                    // 卡片颜色

	private String user;
	private JFrame frame;

	public AccountManagement() {
		// 设置现代感背景
		jPanel2.setBackground(backgroundColor);
		jPanel2.setLayout(null);

		// 创建卡片容器，使其悬浮在背景上
		RoundedPanel cardPanel = new RoundedPanel();
		cardPanel.setBackground(cardColor);
		cardPanel.setBounds(350, 100, 500, 500);
		cardPanel.setLayout(null);
		cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// 标题样式
		titleLabel.setFont(titleFont);
		titleLabel.setForeground(primaryColor);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 30, 460, 60);
		cardPanel.add(titleLabel);

		// 设置按钮样式
		setupButton(infoButton, primaryColor, 110);
		setupButton(passwordButton, primaryColor, 190);
		setupButton(recordButton, primaryColor, 270);
		setupButton(logoutButton, accentColor, 350);

		// 设置按钮悬停效果
		for (JButton button : new JButton[]{infoButton, passwordButton, recordButton, logoutButton}) {
			button.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					button.setBackground(button.getBackground().darker());
				}
				public void mouseExited(java.awt.event.MouseEvent evt) {
					if (button == logoutButton) {
						button.setBackground(accentColor);
					} else {
						button.setBackground(primaryColor);
					}
				}
			});
		}

		// 添加到卡片面板
		cardPanel.add(infoButton);
		cardPanel.add(passwordButton);
		cardPanel.add(recordButton);
		cardPanel.add(logoutButton);

		jPanel2.add(cardPanel);

		/*// 添加底部信息
		JLabel footerLabel = new JLabel("图书管理系统 | v2.0");
		footerLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		footerLabel.setForeground(Color.GRAY);
		footerLabel.setBounds(500, 630, 200, 30);
		jPanel2.add(footerLabel);*/

		// 添加事件
		addEventListeners();
	}

	// 自定义圆角面板类
	class RoundedPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// 绘制背景（含阴影）
			g2d.setColor(new Color(0, 0, 0, 30));
			g2d.fillRoundRect(4, 4, getWidth() - 8, getHeight() - 8, 20, 20);

			g2d.setColor(getBackground());
			g2d.fillRoundRect(0, 0, getWidth() - 4, getHeight() - 4, 20, 20);
		}
	}

	// 设置按钮样式
	private void setupButton(JButton button, Color bgColor, int yPos) {
		button.setFont(buttonFont);
		button.setBounds(30, yPos, 400, 50);
		button.setBackground(bgColor);
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		button.setBorderPainted(false);
		button.setOpaque(true);

		// 添加圆角
		button.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(bgColor.darker(), 1),
				BorderFactory.createEmptyBorder(10, 25, 10, 25)
		));
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	private void addEventListeners() {
		// 修改信息
		infoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ModifyInformation(user);
			}
		});

		// 修改密码
		passwordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ModifyPassword password = new ModifyPassword(user);
				password.setFrame(frame);
			}
		});

		// 借书记录
		recordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new BorrowRecords(user);
			}
		});

		// 退出登陆
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Land();
			}
		});
	}
}