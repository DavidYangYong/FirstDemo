package com.fl.kafka.consumer.multi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.fl.kafka.ProducerData;

public class JdbcWriteDemo {
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
		String url = "jdbc:oracle:thin:@10.10.10.108:1521:gdcgnew";
		String username = "gdcg";
		String password = "123456";
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
	public static void process(List<ProducerData> producerDatas) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "insert into KafkaDemo(code,name) values(?,?)";
		try {
			if (con != null) {
				con.setAutoCommit(false);
				ps = con.prepareStatement(sql);
				if (ps != null) {
					for (int i = 0; i < producerDatas.size(); i++) {
						ProducerData producerData = producerDatas.get(i);
						ps.setString(1, producerData.getId());
						ps.setString(2, producerData.getUser());
						
						ps.addBatch();
						
						// if (i % 1000 == 0) {
						//
						// ps.executeBatch();
						//
						// ps.clearBatch();
						//
						// }
						
					}
					ps.executeBatch();
					con.commit();
				}
				// if (ps.execute()) {
				// rs = ps.getResultSet();
				// } else {
				// int i = ps.getUpdateCount();
				// }
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, con);
		}
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
