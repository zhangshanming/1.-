package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.Book;
import database.FindBook;

/**
 * 添加图书界面
 *
 * @author K.X
 */
public class AddBook extends JFrame {

	// 面板
	private JPanel jPanel = new JPanel();
	// 标签
	private JLabel jLabel = new JLabel("添加图书");
	private JLabel jLabel2 = new JLabel("类  别：");
	private JLabel jLabel3 = new JLabel("书  名：");
	private JLabel jLabel4 = new JLabel("作  者：");
	private JLabel jLabel5 = new JLabel("出版社：");
	// 文本框
	private JTextField field = new JTextField(20);
	private JTextField field2 = new JTextField(20);
	private JTextField field3 = new JTextField(20);
	// 下拉框
	private JComboBox<String> box = new JComboBox<String>();
	// 按钮
	private JButton button = new JButton("确定");
	// 字体
	private Font font = new Font("微软雅黑", Font.BOLD, 40);
	private Font font2 = new Font("微软雅黑", Font.BOLD, 25);
	private Font font3 = new Font("微软雅黑", Font.PLAIN, 20);
	// 表格   用于更新图书搜索界面的表格
	public DefaultTableModel model = new DefaultTableModel();

	private String s;

	public AddBook() {
		// 改变背景图片
		Icon i = new ImageIcon("img\\admintop.jpg");
		JLabel Label = new JLabel(i);
		Label.setBounds(0, 0, 580, 100);
		setLayout(null);
		setSize(580, 650);
		setTitle("添加图书");
		setLocationRelativeTo(null);

		// 标签
		jLabel.setFont(font);
		jLabel.setBounds(195, 80, 400, 100);
		jLabel.setForeground(new Color(70, 130, 180));

		jLabel2.setFont(font2);
		jLabel2.setBounds(125, 200, 250, 30);

		jLabel3.setFont(font2);
		jLabel3.setBounds(125, 260, 250, 30);

		jLabel4.setFont(font2);
		jLabel4.setBounds(125, 320, 250, 30);

		jLabel5.setFont(font2);
		jLabel5.setBounds(125, 380, 250, 30);

		// 下拉框
		box.addItem("请选择类别");
		Book.findcategory(box);
		box.setFont(font3);
		box.setBounds(245, 200, 200, 30);
		box.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		// 文本框
		field.setFont(font3);
		field.setBounds(245, 260, 200, 30);
		field.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		field2.setFont(font3);
		field2.setBounds(245, 320, 200, 30);
		field2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		field3.setFont(font3);
		field3.setBounds(245, 380, 200, 30);
		field3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		// 按钮
		button.setFont(font2);
		button.setBounds(120, 455, 325, 45);
		button.setBackground(new Color(70, 130, 180));
		button.setForeground(Color.WHITE);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setFocusPainted(false);

		// 添加组件
		jPanel.add(jLabel);
		jPanel.add(jLabel2);
		jPanel.add(jLabel3);
		jPanel.add(jLabel4);
		jPanel.add(jLabel5);
		jPanel.add(box);
		jPanel.add(field);
		jPanel.add(field2);
		jPanel.add(field3);
		jPanel.add(button);

		// 设置面板
		jPanel.setBounds(0, 0, 850, 650);
		jPanel.setOpaque(false);
		jPanel.setLayout(null);
		add(jPanel);
		add(Label);

		// 添加事件
		addListeners();

		// 设置窗口属性
		setResizable(false);
		setVisible(true);
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	private void addListeners() {
		// 获取下拉列表值
		s = "请选择类别";
		box.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					s = (String)e.getItem();
				}
			}
		});

		// 添加搜索按钮事件
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookname = field.getText().trim();
				String author = field2.getText().trim();
				String press = field3.getText().trim();
				Book.addbook(s, bookname, author, press);
				model.setRowCount(0);
				FindBook.allbook(model);
				JOptionPane.showMessageDialog(null, "操作成功");
			}
		});
	}
}
