package view;

/**
 * 用户账户管理界面
 *
 * @author K.X
 *
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AccountManagement {
	/*
	 * 标签
	 *
	 * 四个按钮 修改账号 修改密码 借书记录 退出程序
	 *
	 */
	// 面板
	public JPanel jPanel2 = new JPanel();
	// 标签
	private JLabel jLabel = new JLabel("账户管理");
	// 按钮
	private JButton button = new JButton("修改信息");
	private JButton button2 = new JButton("修改密码");
	private JButton button3 = new JButton("借书记录");
	private JButton button4 = new JButton("退出登陆");
	// 字体
	private Font font = new Font("微软雅黑", Font.BOLD, 50);
	private Font font1 = new Font("微软雅黑", Font.BOLD, 25);

	private String user;
	private JFrame frame;

	public AccountManagement() {
		// 设置背景颜色
		jPanel2.setBackground(new Color(245, 245, 245));
		jPanel2.setLayout(null);

		// 改变背景图片
		Icon i = new ImageIcon("img\\account.jpg");
		JLabel Label = new JLabel(i);
		Label.setBounds(0, 0, 1200, 800);
		Label.setOpaque(true);

		// 标签
		jLabel.setFont(font);
		jLabel.setBounds(460, 50, 800, 70);
		jLabel.setForeground(new Color(70, 130, 180));
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// 按钮
		button.setFont(font1);
		button2.setFont(font1);
		button3.setFont(font1);
		button4.setFont(font1);
		button.setBounds(450, 250, 250, 50);
		button2.setBounds(450, 310, 250, 50);
		button3.setBounds(450, 370, 250, 50);
		button4.setBounds(450, 430, 250, 50);

		button.setBackground(new Color(70, 130, 180));
		button2.setBackground(new Color(70, 130, 180));
		button3.setBackground(new Color(70, 130, 180));
		button4.setBackground(new Color(70, 130, 180));

		button.setForeground(Color.white);
		button2.setForeground(Color.white);
		button3.setForeground(Color.white);
		button4.setForeground(Color.white);

		button.setOpaque(true);
		button2.setOpaque(true);
		button3.setOpaque(true);
		button4.setOpaque(true);

		// 添加边框
		button.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)));
		button2.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)));
		button3.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)));
		button4.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)));

		// 添加事件
		add();

		jPanel2.add(button);
		jPanel2.add(button2);
		jPanel2.add(button3);
		jPanel2.add(button4);
		jPanel2.add(jLabel);
		jPanel2.add(Label);
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	// 添加事件
	private void add() {
		// 修改信息
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ModifyInformation(user);
			}
		});

		// 修改密码
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ModifyPassword password = new ModifyPassword(user);
				password.setFrame(frame);
			}
		});

		// 借书记录
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new BorrowRecords(user);
			}
		});

		// 退出登陆
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Land();
			}
		});
	}
}
