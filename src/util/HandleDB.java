package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HandleDB { // 操作数据库的语句

	public static ResultSet rs;
	public static Connection conn;
	/**
	 * 从数据库中查找对应的数据
	 */
	public static final String SELECT_BASIS_CATTLE = "SELECT * FROM basis_cattle";

	public static final String SELECT_MILK_CROP_RECORD = "SELECT * FROM milk_crop_record";

	public static final String SELECT_COPULATION_RECORD1 = "SELECT * FROM copulation_record";

	public static final String SELECT_PRO_PROPERTY = "SELECT * FROM pro_property";

	public static final String SELECT_INSPECTION_RECORD = "SELECT * FROM inspection_record";

	public static final String SELECT_APPEARANCE_RECORD = "SELECT * FROM appearance_record";

	public static final String SELECT_DISEASE_RECORD = "SELECT * FROM disease_record";

	public static final String SELECT_SIZE_WEIGHT = "SELECT * FROM size_weight";

	/**
	 * 连接数据库
	 */
	public static void connectDB() {
		// 驱动程序名
		String driver = "com.mysql.jdbc.Driver";
		// URL指向要访问的数据库名
		String url = "jdbc:mysql://192.168.2.24:3306/cattlefarm";
		// MySQL配置时的用户名
		String user = "hong2";
		// MySQL配置时的密码
		String password = "123456";
		try {
			// 加载驱动程序
			Class.forName(driver);
			// 连接数据库
			conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	/**
	 * 获取操作数据库对象
	 */
	public static ResultSet queryDB(String sql) {
		try {
			// statement用来执行SQL语句
			Statement statement = conn.createStatement();
			// 要执行的SQL语句
			// 结果集
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 操作数据库对象
	 */
	public static int updateDB(String sql) {
		int rs = 0;
		try {
			Statement statement = conn.createStatement();
			rs = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 在数据库中查找指定id牛
	 */
	public static String selectData(String tableName, String id) {
		String handleString = "SELECT* FROM " + tableName + " WHERE "
				+ "idOfCattle =" + id;
		return handleString;
	}

	/**
	 * 在数据库中插入数据
	 */
	public static String insertData(String tableName, String[] properties) {
		String handleString = "INSERT　INTO " + tableName + " VALUES " + '(';
		for (int i = 0; i < properties.length; i++) {
			if (i != properties.length - 1) {
				handleString = handleString + properties[i] + ',';
			} else {
				handleString = handleString + properties[i];
			}
		}
		handleString = handleString + ')';
		return handleString;
	}

	/**
	 * 在数据库中更新数据
	 */
	public static String updateData(String tableName, String setString,
			String whereString) {
		String handleString = "UPDATE " + tableName + " SET " + setString
				+ " WHERE ";
		return handleString;
	}

	/**
	 * 在数据库中删除数据
	 */
	public static String deleteData(String tableName, String whereString) {
		String handleString = "DELETE FROM " + tableName + " WHERE "
				+ whereString;
		return handleString;
	}
}
