package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import database.Landing;

public class MainInterface extends JFrame {
	public JTabbedPane jTabbedPane = new JTabbedPane();
	private JPanel jPanel = new JPanel();
	private JLabel jLabel = new JLabel("欢迎登陆图书管理系统");
	private Font titleFont = new Font("微软雅黑", Font.BOLD, 48);
	private Font tabFont = new Font("微软雅黑", Font.PLAIN, 18);
	private Container con = getContentPane();

	public MainInterface(String user) {
		// 设置窗口图标
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/top.jpg"));

		// 背景图设置
		ImageIcon backgroundIcon = new ImageIcon("img/Main.jpg");
		JLabel backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setBounds(0, 0, 1200, 800);

		// 设置透明面板
		jPanel.setLayout(null);
		jPanel.setOpaque(false); // 使面板透明

		// 主标签设置
		jLabel.setFont(titleFont);
		jLabel.setForeground(new Color(30, 30, 30));
		jLabel.setBounds(300, 50, 800, 100);
		jPanel.add(jLabel);

		// 添加背景图
		JPanel bgPanel = new JPanel(null);
		bgPanel.add(backgroundLabel);
		backgroundLabel.setBounds(0, 0, 1200, 800);

		bgPanel.add(jPanel);
		jPanel.setBounds(0, 0, 1200, 800);

		// 设置 Tab 字体 & 边框
		jTabbedPane.setFont(tabFont);
		jTabbedPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		// 添加 tab 页面
		jTabbedPane.addTab("主 界 面", new ImageIcon("img/home.png"), bgPanel);

		BookSearch search = new BookSearch();
		jTabbedPane.addTab("图书查询", new ImageIcon("img/search.png"), search.jLayeredPane);

		BorrowingReturning returning = new BorrowingReturning();
		returning.setUser(user);
		returning.setModel(search.model);
		jTabbedPane.addTab("图书借还", new ImageIcon("img/borrow.png"), returning.jLayeredPane);

		if (Landing.sureadmin(user)) {
			Admin admin = new Admin();
			admin.setUser(user);
			admin.setFrame(this);
			jTabbedPane.addTab("账户管理", new ImageIcon("img/user.png"), admin.jPanel2);

			BookAdmin bookAdmin = new BookAdmin();
			bookAdmin.setModel(search.model);
			jTabbedPane.addTab("图书管理", new ImageIcon("img/manage.png"), bookAdmin.jPanel2);
		} else {
			AccountManagement management = new AccountManagement();
			management.setUser(user);
			management.setFrame(this);
			jTabbedPane.addTab("账户管理", new ImageIcon("img/user.png"), management.jPanel2);
		}

		con.add(jTabbedPane);
		setResizable(false);
		setTitle("图书管理系统");
		setSize(1200, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
