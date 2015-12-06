package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class OneMenuBar extends JMenuBar { // 菜单栏

	private static JMenu mFile; // "文件"菜单

	private static JMenuItem miNew;

	private static JMenuItem miQuit;

	private static JMenuItem miAdd;

	private static boolean isOnlyRead = true;

	OneMenuBar() {
		mFile = new JMenu("文件");
		miNew = new JMenuItem("退出");
		miQuit = new JMenuItem("修改模式");
		miAdd = new JMenuItem("增加奶牛");

		miAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame();

			}
		});
		mFile.add(miNew);
		mFile.add(miQuit);
		mFile.add(miAdd);

		add(mFile);
	}
}
