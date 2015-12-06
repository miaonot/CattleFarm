package windows;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.BasisTable;
import util.Cattle;

public class CattleInfoPanel extends JPanel {

	/**
	 * 牛的编号
	 */
	private static JLabel idLabel = new JLabel("编号：");
	private static JTextField idText = new JTextField(20);

	/**
	 * 牛的品种
	 */
	private static JLabel featureLabel = new JLabel("品种：");
	private static JTextField featureText = new JTextField();

	/**
	 * 下拉框选择表格
	 */
	private static JLabel seeTable = new JLabel("查看信息：");
	private static String tableName[] = { "基本信息", "产奶记录", "配胎记录", "生产性能",
			"检疫反应记录", "外貌鉴定", "预防注射及主要疾病", "体尺体重" };
	private static JComboBox comboBox = new JComboBox(tableName);

	/**
	 * table的JPanel
	 */
	JScrollPane tableScrollPane = new JScrollPane();

	public CattleInfoPanel(Cattle cattle) {
		setLayout(null);
		setBackground(new Color(255, 255, 255));

		// 设置编号标签
		idLabel.setFont(new Font("宋体", Font.BOLD, 19));
		idLabel.setBounds(40, 20, 65, 20);
		add(idLabel);
		// 显示编号信息
		String idOfCattle = cattle.getIdOfCattle();
		idText.setFont(new Font("宋体", Font.BOLD, 18));
		idText.setText(idOfCattle);
		idText.setBounds(95, 20, 80, 20);
		idText.setEditable(false);
		idText.setBorder(BorderFactory
				.createLineBorder(new Color(238, 238, 238)));
		idText.setBackground(new Color(255, 255, 255));
		add(idText);

		// 设置品种标签
		featureLabel.setFont(new Font("宋体", Font.BOLD, 19));
		featureLabel.setBounds(180, 20, 65, 20);
		add(featureLabel);
		// 显示品种信息
		featureText.setFont(new Font("宋体", Font.BOLD, 18));
		featureText.setText("sss");
		featureText.setBounds(235, 20, 80, 20);
		featureText.setEditable(false);
		featureText.setBorder(BorderFactory.createLineBorder(new Color(238,
				238, 238)));
		featureText.setBackground(new Color(255, 255, 255));
		add(featureText);

		// 设置下拉框标签
		seeTable.setFont(new Font("宋体", Font.BOLD, 19));
		seeTable.setBounds(400, 20, 100, 20);
		add(seeTable);
		// 设置下拉框
		comboBox.setBounds(500, 19, 180, 23);
		comboBox.setFont(new Font("宋体", Font.BOLD, 15));
		add(comboBox);

		tableScrollPane.setBounds(25, 60, 900, 555);
		tableScrollPane.setViewportView(new BasisTable(idOfCattle));
		add(tableScrollPane);

	}

}
