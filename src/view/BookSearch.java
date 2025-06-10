package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import database.FindBook;

public class BookSearch {
	public JLayeredPane jLayeredPane = new JLayeredPane();
	private JLabel jLabel = new JLabel("图书查询");
	private JLabel jLabel2 = new JLabel("请选择查询方式：");
	private JTextField field = new JTextField(25);
	private Dimension dimension = new Dimension(220, 30);
	private JComboBox<String> box = new JComboBox<String>();
	private JButton button = new JButton("搜索");
	public DefaultTableModel model = new DefaultTableModel();
	private Font font = new Font("微软雅黑", Font.BOLD, 50);
	private Font font1 = new Font("微软雅黑", Font.BOLD, 25);
	private Font font2 = new Font("微软雅黑", Font.PLAIN, 20);
	private String s;
	private String book;
	private int id;

	public BookSearch() {
		jLayeredPane.setBackground(new Color(240, 248, 255));
		jLayeredPane.setOpaque(true);

		jLabel.setFont(font);
		jLabel.setBounds(485, 35, 800, 100);
		jLabel.setForeground(new Color(70, 130, 180));
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel2.setFont(font1);
		jLabel2.setBounds(180, 130, 250, 30);
		jLabel2.setForeground(new Color(70, 130, 180));

		box.setSize(dimension);
		box.addItem("按照类别查找");
		box.addItem("按照书名查找");
		box.addItem("按照作者查找");
		box.addItem("按照书号查找");
		box.setFont(font2);
		box.setBounds(180, 170, 220, 40);
		box.setForeground(new Color(70, 130, 180));
		box.setBackground(new Color(255, 250, 250));
		box.setOpaque(true);

		field.setFont(font2);
		field.setSize(dimension);
		field.setBounds(480, 173, 250, 35);
		field.setForeground(new Color(70, 130, 180));
		field.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 1));

		button.setFont(font1);
		button.setBounds(850, 170, 100, 40);
		button.setForeground(new Color(70, 130, 180));
		button.setBackground(new Color(255, 250, 250));
		button.setOpaque(true);
		button.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 1));

		model.addColumn("书号");
		model.addColumn("类别");
		model.addColumn("书名");
		model.addColumn("作者");
		model.addColumn("出版社");
		model.addColumn("状态");
		JTable jTable = new JTable(model);

		JScrollPane pane = new JScrollPane(jTable);
		pane.setBounds(180, 250, 800, 400);

		// 初始加载所有图书
		FindBook.allbook(model);

		JTableHeader head = jTable.getTableHeader();
		head.setPreferredSize(new Dimension(head.getWidth(), 30));
		head.setFont(new Font("微软雅黑", Font.BOLD, 20));
		head.setForeground(new Color(70, 130, 180));
		head.setBackground(new Color(255, 250, 250));

		jTable.setRowHeight(30);
		jTable.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		jTable.setForeground(new Color(70, 130, 180));
		jTable.setBackground(new Color(255, 250, 250));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jTable.setDefaultRenderer(Object.class, renderer);

		addEvent();

		jLayeredPane.add(jLabel, new Integer(100), 1);
		jLayeredPane.add(jLabel2, new Integer(100), 2);
		jLayeredPane.add(box, new Integer(100), 3);
		jLayeredPane.add(field, new Integer(100), 4);
		jLayeredPane.add(button, new Integer(100), 5);
		jLayeredPane.add(pane, new Integer(100), 6);
	}

	private void addEvent() {
		s = "按照类别查找";
		box.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					s = (String) e.getItem();
				}
			}
		});

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取搜索类型
				String searchType = s;
				String inputText = field.getText().trim();

				// 清空模型
				model.setRowCount(0);

				// 根据不同类型处理
				if (searchType.equals("按照类别查找")) {
					// 输入为空时显示所有图书
					if (inputText.isEmpty()) {
						FindBook.allbook(model);
						return;
					}

					FindBook.findcategory(model, inputText);
					if (model.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null,
								"未找到相关图书，显示所有图书",
								"搜索结果",
								JOptionPane.INFORMATION_MESSAGE);
						FindBook.allbook(model);
					}
				}
				else if (searchType.equals("按照书名查找")) {
					// 输入为空时显示所有图书
					if (inputText.isEmpty()) {
						FindBook.allbook(model);
						return;
					}

					FindBook.findbookname(model, inputText);
					if (model.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null,
								"未找到相关图书，显示所有图书",
								"搜索结果",
								JOptionPane.INFORMATION_MESSAGE);
						FindBook.allbook(model);
					}
				}
				else if (searchType.equals("按照作者查找")) {
					// 输入为空时显示所有图书
					if (inputText.isEmpty()) {
						FindBook.allbook(model);
						return;
					}

					FindBook.findauthor(model, inputText);
					if (model.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null,
								"未找到相关图书，显示所有图书",
								"搜索结果",
								JOptionPane.INFORMATION_MESSAGE);
						FindBook.allbook(model);
					}
				}
				else if (searchType.equals("按照书号查找")) {
					if (inputText.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"请输入书号！",
								"输入错误",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					try {
						id = Integer.parseInt(inputText);
						FindBook.findbookid(model, id);

						if (model.getRowCount() == 0) {
							JOptionPane.showMessageDialog(null,
									"未找到相关图书，显示所有图书",
									"搜索结果",
									JOptionPane.INFORMATION_MESSAGE);
							FindBook.allbook(model);
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null,
								"书号必须是数字！",
								"输入错误",
								JOptionPane.ERROR_MESSAGE);
						// 输入错误时显示所有图书
						FindBook.allbook(model);
					}
				}
			}
		});
	}
}