package JdbcDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatePhotoTest {
	private PreparedStatement stmt;
	private SqlServerDbAccessor dba;
	public Connection con;
	
	public UpdatePhotoTest() {
		dba = new SqlServerDbAccessor();
		dba.setDbName("StudTest");
		dba.connectToDb();
		con = dba.getConnection();
		
	}

	public void addTables(String table, String column, 
			String imageFile, String condition) {
		FileInputStream imageInputStream = null;
		String updateSql = "UPDATE " + table + " SET " + column + " = ? " +
							condition;
		try {
			stmt = dba.getConnection().prepareStatement(updateSql);
			imageInputStream = new FileInputStream(new File(imageFile));
			stmt.setBinaryStream(1, imageInputStream);
			int success = stmt.executeUpdate();
			
			if (success == 1)
				System.out.println("One photo loaded successfully!");
			else
				System.out.println("Somehow it didn't go through...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateImageInColumn(String table, String column, 
			String imageFile, String condition) {
		FileInputStream imageInputStream = null;
		String updateSql = "UPDATE " + table + " SET " + column + " = ? " +
							condition;
		try {
			stmt = dba.getConnection().prepareStatement(updateSql);
			imageInputStream = new FileInputStream(new File(imageFile));
			stmt.setBinaryStream(1, imageInputStream);
			int success = stmt.executeUpdate();
			
			if (success == 1)
				System.out.println("One photo loaded successfully!");
			else
				System.out.println("Somehow it didn't go through...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertUsers(String table, String ID, String userName, String PW, String CustID) {
		UpdatePhotoTest upt = new UpdatePhotoTest();
		String sql = "INSERT INTO " + table + " VALUES (" + ID + ", '" + userName+"', '"+PW+"', "+CustID+", null, null, 'no photo for now')";
		//System.out.println("Query: " + sql);
		try {
			Statement stmt = upt.con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Query Passed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Query Failed\n");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UpdatePhotoTest test = new UpdatePhotoTest();
		test.insertUsers("GI27Login", "2", "cust_user_02", "cu02", "1002");
		test.insertUsers("GI27Login", "3", "cust_user_03", "cu03", "1003");
		test.updateImageInColumn("GI27Login", "Photo", "/Users/gabe_imlay/Documents/JdbcDemo/src/JdbcDemo/user_cu_02.png",
				"WHERE ID = 2");
		test.updateImageInColumn("GI27Login", "Photo", "/Users/gabe_imlay/Documents/JdbcDemo/src/JdbcDemo/user_cu_03.png",
		"WHERE ID = 3");

	}

}
