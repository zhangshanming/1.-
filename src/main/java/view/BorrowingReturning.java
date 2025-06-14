package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import database.BorrowRecords;
import database.ConnectDatabase;
import database.FindBook;

/**
 * 简约大气版图书借还界面
 */
public class BorrowingReturning {
	public JLayeredPane jLayeredPane = new JLayeredPane();

	private JLabel jLabel = new JLabel("图书借还");
	private JLabel jLabel2 = new JLabel("请输入书号：");

	private JLabel jLabel3 = new JLabel("书号：");
	private JLabel jLabel4 = new JLabel();
	private JLabel jLabel5 = new JLabel("书名：");
	private JLabel jLabel6 = new JLabel();
	private JLabel jLabel7 = new JLabel("作者：");
	private JLabel jLabel8 = new JLabel();
	private JLabel jLabel9 = new JLabel("状态：");
	private JLabel jLabel10 = new JLabel();

	private JTextField field = new JTextField(20);
	private JButton button = new JButton("检索");
	private JButton buttonBorrow = new JButton("借阅");
	private JButton buttonReturn = new JButton("还书");

	private JPanel infoPanel = new JPanel();

	private Font fontTitle = new Font("微软雅黑", Font.BOLD, 48);
	private Font fontLabel = new Font("微软雅黑", Font.PLAIN, 20);
	private Font fontButton = new Font("微软雅黑", Font.PLAIN, 20);

	private String user;
	private DefaultTableModel model = new DefaultTableModel();
	int id;

	public BorrowingReturning() {
		jLayeredPane.setBackground(new Color(245, 245, 245));
		jLayeredPane.setOpaque(true);

		// Title
		jLabel.setFont(fontTitle);
		jLabel.setBounds(400, 40, 400, 60);
		jLabel.setForeground(new Color(50, 50, 50));
		jLabel.setHorizontalAlignment(JLabel.CENTER);

		// Input label
		jLabel2.setFont(fontLabel);
		jLabel2.setBounds(300, 130, 150, 30);
		jLabel2.setForeground(new Color(60, 60, 60));

		// Input field
		field.setFont(fontLabel);
		field.setBounds(450, 130, 250, 35);
		field.setForeground(Color.DARK_GRAY);
		field.setBackground(Color.WHITE);

		// Search button
		button.setFont(fontButton);
		button.setBounds(730, 130, 100, 35);
		button.setBackground(new Color(100, 149, 237)); // 淡蓝色
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);

		// Info Panel
		infoPanel.setBounds(250, 200, 700, 400);
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setLayout(null);
		infoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

		// Labels inside Panel
		jLabel3.setFont(fontLabel);
		jLabel4.setFont(fontLabel);
		jLabel5.setFont(fontLabel);
		jLabel6.setFont(fontLabel);
		jLabel7.setFont(fontLabel);
		jLabel8.setFont(fontLabel);
		jLabel9.setFont(fontLabel);
		jLabel10.setFont(fontLabel);

		jLabel3.setBounds(50, 40, 100, 30);
		jLabel4.setBounds(160, 40, 500, 30);

		jLabel5.setBounds(50, 90, 100, 30);
		jLabel6.setBounds(160, 90, 500, 30);

		jLabel7.setBounds(50, 140, 100, 30);
		jLabel8.setBounds(160, 140, 500, 30);

		jLabel9.setBounds(50, 190, 100, 30);
		jLabel10.setBounds(160, 190, 500, 30);

		// Borrow button
		buttonBorrow.setFont(fontButton);
		buttonBorrow.setBounds(120, 300, 150, 45);
		buttonBorrow.setBackground(new Color(60, 179, 113)); // 绿色
		buttonBorrow.setForeground(Color.WHITE);
		buttonBorrow.setFocusPainted(false);

		// Return button
		buttonReturn.setFont(fontButton);
		buttonReturn.setBounds(420, 300, 150, 45);
		buttonReturn.setBackground(new Color(220, 20, 60)); // 红色
		buttonReturn.setForeground(Color.WHITE);
		buttonReturn.setFocusPainted(false);

		// Add event handlers
		addEvent();

		// Add components to panel
		infoPanel.add(jLabel3);
		infoPanel.add(jLabel4);
		infoPanel.add(jLabel5);
		infoPanel.add(jLabel6);
		infoPanel.add(jLabel7);
		infoPanel.add(jLabel8);
		infoPanel.add(jLabel9);
		infoPanel.add(jLabel10);
		infoPanel.add(buttonBorrow);
		infoPanel.add(buttonReturn);

		// Add all to layeredPane
		jLayeredPane.add(jLabel, new Integer(100));
		jLayeredPane.add(jLabel2, new Integer(100));
		jLayeredPane.add(field, new Integer(100));
		jLayeredPane.add(button, new Integer(100));
		jLayeredPane.add(infoPanel, new Integer(100));
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	private void addEvent() {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					id = Integer.parseInt(field.getText().trim());
					field.setText("");
					Connection con = ConnectDatabase.connectDB();
					PreparedStatement preSql;
					ResultSet rs;
					String sqlStr = "select * from booktable where bookid = ?";
					preSql = con.prepareStatement(sqlStr);
					preSql.setInt(1, id);
					rs = preSql.executeQuery();
					boolean found = false;
					while (rs.next()) {
						found = true;
						jLabel4.setText(rs.getString(1)); // 书号
						jLabel6.setText(rs.getString(3)); // 书名
						jLabel8.setText(rs.getString(4)); // 作者
						jLabel10.setText(rs.getString(6)); // 状态
					}
					if (!found) {
						JOptionPane.showMessageDialog(null, "图书不存在", "提示", JOptionPane.INFORMATION_MESSAGE);
						empty();
					}
					con.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "请输入正确的书号", "错误", JOptionPane.ERROR_MESSAGE);
					field.setText("");
				}
			}
		});

		buttonBorrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jLabel10.getText().equals("在馆")) {
					id = Integer.parseInt(jLabel4.getText().trim());
					BorrowRecords.Borrow(user, id, jLabel6.getText().trim());
					JOptionPane.showMessageDialog(null, "借阅成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					empty();
					model.setRowCount(0);
					FindBook.allbook(model);
				} else if (jLabel10.getText().equals("外借")) {
					JOptionPane.showMessageDialog(null, "此书已外借！", "提示", JOptionPane.WARNING_MESSAGE);
					empty();
				} else {
					JOptionPane.showMessageDialog(null, "未检索图书", "提示", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		buttonReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jLabel10.getText().equals("外借")) {
					if (BorrowRecords.comparison(user, id)) {
						id = Integer.parseInt(jLabel4.getText().trim());
						BorrowRecords.Return(user, id);
						JOptionPane.showMessageDialog(null, "还书成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
						empty();
						model.setRowCount(0);
						FindBook.allbook(model);
					} else {
						JOptionPane.showMessageDialog(null, "该书不是您借的", "提示", JOptionPane.WARNING_MESSAGE);
					}
				} else if (jLabel10.getText().equals("在馆")) {
					JOptionPane.showMessageDialog(null, "该书已在馆", "提示", JOptionPane.WARNING_MESSAGE);
					empty();
				} else {
					JOptionPane.showMessageDialog(null, "未检索图书", "提示", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}

	public void empty() {
		jLabel4.setText("");
		jLabel6.setText("");
		jLabel8.setText("");
		jLabel10.setText("");
	}
}
