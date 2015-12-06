package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import util.Cattle;
import util.HandleDB;

public class MainFrame extends JFrame { // 主界面

	private static OneMenuBar menuBar = new OneMenuBar();
	private Container container;

	/**
	 * JList中显示
	 */
	public Vector<Cattle> cattleVector = new Vector<Cattle>();
	private JList<Cattle> cattleList = new JList<Cattle>(cattleVector);

	private JPanel centerPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();

	/**
	 * 前向牛，后向牛存储列表 当前牛
	 */
	public Cattle currentCattle;
	public List<Cattle> advenceList;
	public List<Cattle> retreatList;

	/**
	 * 前向 后向 搜索框
	 */
	public JButton retreatButton = new JButton();
	public JButton advanceButton = new JButton();
	public JButton goTo = new JButton();
	public JTextField idBar = new JTextField(35);

	/**
	 * 左部 中部界面
	 */
	public JScrollPane leftScrollPane = new JScrollPane(cattleList);
	public JScrollPane centerScrollPane = new JScrollPane();

	/**
	 * 左下 添加 删除
	 */
	public JButton deleteButton = new JButton("删除");
	public JButton addButton = new JButton("添加");

	MainFrame() {
		super("牛场管理系统");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setJMenuBar(menuBar);
		setSize(1230, 850);
		setLayout(new BorderLayout());

		// 连接数据库
		HandleDB.connectDB();

		// 开始组装控件
		container = getContentPane();

		container.add(BorderLayout.NORTH, menuBar);
		container.add(BorderLayout.CENTER, centerPanel);

		// 设置中间控件
		setCenterPanel();

		// 设置底部控件
		setBottomPanel();

		setVisible(true);
	}

	private void setCenterPanel() {
		// 前向 后向 搜索框
		advanceButton.setIcon(new ImageIcon("res\\retreatSolid.png"));
		advanceButton.setEnabled(false);
		advanceButton.setBounds(275, 25, 25, 25);
		retreatButton.setIcon(new ImageIcon("res\\advanceSolid.png"));
		retreatButton.setEnabled(false);
		retreatButton.setBounds(300, 25, 25, 25);
		idBar.setBounds(400, 25, 400, 25);
		idBar.setBorder(BorderFactory
				.createLineBorder(new Color(144, 144, 144)));
		goTo.setBounds(800, 25, 20, 25);
		goTo.setIcon(new ImageIcon("res\\goTo.png"));
		centerPanel.add(advanceButton);
		centerPanel.add(retreatButton);
		centerPanel.add(idBar);
		centerPanel.add(goTo);

		// 左侧 中间界面构建
		centerPanel.setLayout(null);
		leftScrollPane.setBounds(25, 80, 200, 610);
		centerScrollPane.setBounds(230, 80, 950, 650);
		centerPanel.add(leftScrollPane);
		centerPanel.add(centerScrollPane);

		// 左下按钮
		deleteButton.setBounds(30, 695, 80, 35);
		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				delete();
				initListData();
				cattleList.setSelectedIndex(0);
			}
		});
		addButton.setBounds(140, 695, 80, 35);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s=JOptionPane.showInputDialog("添加牛数(1~10)：");
				int i= Integer.parseInt(s);
				JPanel fpan = new JPanel(null);
				JPanel pan = new JPanel();
				pan.setSize(250, 100*(i+1));
				JTextField [] field = new JTextField[i];
				for(int j=0;j<i;j++){
					field[j] = new JTextField(10);
					pan.add(new JLabel("第"+(j+1) + "头牛ID："));
					pan.add(field[j]);
				}
				
				JButton okButton = new JButton("确定");
				JButton cancelButton = new JButton("取消");
				JPanel okOrCancel = new JPanel();
				okOrCancel.add(okButton);
				okOrCancel.add(cancelButton);
				pan.add(okOrCancel);
				fpan.add(pan);
				
				centerScrollPane.setViewportView(fpan);
				
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						centerScrollPane.setViewportView(null);
					}
				});
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int count = 0;
						for(int k=0;k<i;k++)
						{
							if (!field[k].getText().equals("")){
								String [] str= {"'"+field[k].getText()+"'",null,null,null,null,null,null,null,null,null};
								HandleDB.updateDB(HandleDB.insertData("basis_cattle", str));
								count++;
							}
						}
						JOptionPane.showMessageDialog(null, count + "个添加成功，"+(i-count)+"个添加失败。");
						centerScrollPane.setViewportView(null);
					}
				});
			}
		});
		centerPanel.add(deleteButton);
		centerPanel.add(addButton);

		// cattleList增加事件监听
		cattleList.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int pos = ((JList) e.getSource()).getSelectedIndex();
				Cattle cattle = cattleVector.get(pos);
				CattleInfoPanel cattlePanel = new CattleInfoPanel(cattle);
				centerScrollPane.setViewportView(cattlePanel);
			}
		});

		// 增加牛List数据
		initListData();
	}

	private void setBottomPanel() {
		bottomPanel.setLayout(new FlowLayout());
	}

	/**
	 * 从数据库中获取数据，初始化JList中的数据
	 */
	private void initListData() {
		// 获取基本表
		ResultSet rs = HandleDB.queryDB(HandleDB.SELECT_BASIS_CATTLE);
		try {
			System.out.println("this");
			while (rs.next()) {
				String id = rs.getString("idOfCattle");
				System.out.println(id);
				String features = rs.getString("feature");
				Cattle cattle = new Cattle(id, features);
				cattleVector.add(cattle);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void delete() {
		int selectedCattle[] = cattleList.getSelectedIndices();
		for (int counter = 0; counter < selectedCattle.length; counter++) {
			HandleDB.updateDB(HandleDB.deleteData("basis_cattle",
					"idOfCattle = "
							+ cattleVector.get(selectedCattle[counter])
									.getIdOfCattle()));
			System.out
					.println("delete:"
							+ "idOfCattle = "
							+ cattleVector.get(selectedCattle[counter])
									.getIdOfCattle());
		}
	}
}
