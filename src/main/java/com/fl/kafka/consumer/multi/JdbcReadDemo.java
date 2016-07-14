package com.fl.kafka.consumer.multi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fl.kafka.ProducerData;

public class JdbcReadDemo {
	/**
	 * 加载驱动
	 */
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 */
	public static Connection getConnection() {
		String url = "jdbc:oracle:thin:@10.100.4.100:1521:ora10";
		String username = "scm";
		String password = "yunhan_scm_yfb";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 获取statement对象，操作数据库，处理返回结果
	 */
	public static List<ProducerData> process() {
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select a.PURCHASEORDERCODE,a.COMMODITYCODE  from purchaseorderitems a where rownum<100000";
		List<ProducerData> list = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			
			if (ps.execute()) {
				rs = ps.getResultSet();
				list = printResultSet(rs);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, con);
		}
		return list;
	}
	
	/**
	 * 处理返回结果集
	 */
	public static List<ProducerData> printResultSet(ResultSet rs) {
		
		List<ProducerData> list = new ArrayList<>();
		if (rs != null) {
			
			try {
				ResultSetMetaData meta = rs.getMetaData();
				int cols = meta.getColumnCount();
				StringBuffer b = new StringBuffer();
				while (rs.next()) {
					ProducerData producerData = new ProducerData();
					// for (int i = 1; i <= cols; i++) {
					// // b.append(meta.getColumnName(i) + "=");
					// // b.append(rs.getString(i) + "/t");
					//
					// }
					producerData.setId(rs.getString("PURCHASEORDERCODE"));
					producerData.setUser(rs.getString("COMMODITYCODE"));
					// b.append("/n");
					list.add(producerData);
				}
				
				// System.out.print(b.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 关闭连接
	 */
	public static void close(ResultSet rs, Statement stm, Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (stm != null && rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (con != null && rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
