package util;

import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BasisTable extends JPanel {

	/**
	 * 父亲id
	 */
	JLabel fatherIdLabel = new JLabel("父号");
	JTextField fatherId = new JTextField();

	/**
	 * 母亲id
	 */
	JLabel montherIdLabel = new JLabel("母号");
	JTextField mothrerId = new JTextField();

	/**
	 * 出生日期
	 */
	JLabel birthDateLabel = new JLabel("出生日期");
	JTextField birthDate = new JTextField();

	/**
	 * 出生地点
	 */
	JLabel birthPlaceLabel = new JLabel("出生地点");
	JTextField birthPlace = new JTextField();

	/**
	 * 毛色
	 */
	JLabel colorLabel = new JLabel("毛色");
	JTextField color = new JTextField();

	/**
	 * 当前情况
	 */
	JLabel curSituationLabel = new JLabel("当前情况");
	JTextArea curSituation = new JTextArea();

	/**
	 * 停止饲养日期
	 */
	JLabel stopFeedingDateLabel = new JLabel("停止饲养日期");
	JTextField stopFeedingDate = new JTextField();

	/**
	 * 出生体重
	 */
	JLabel birthWeightLabel = new JLabel("出生体重");
	JTextField birthWeight = new JTextField();

	ResultSet rs;

	public BasisTable(String id) {

		String sql = HandleDB.selectData("basis_cattle", id);
		rs = HandleDB.queryDB(sql);
		birthDateLabel.setBounds(25, 25, 65, 20);
		birthDateLabel.setFont(new Font("宋体", Font.BOLD, 19));
		add(birthDateLabel);
		// re.getString("idOfFather");

	}
}
