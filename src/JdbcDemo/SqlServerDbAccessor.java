package JdbcDemo;

import JdbcDemo.DataObjectAccessor;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class SqlServerDbAccessor implements DataObjectAccessor {
	private String[] columnNamesToReturn;
	private Object[][] dataToReturn;
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public SqlServerDbAccessor() {
		/*
		String connectionUrl = "jdbc:sqlserver://;servername=cssql\\sqldata;"
				+ "user=csc480dev;password=c4s*C0sWe;" +
			"databaseName=JLBookstore;";
		*/
		String connectionUrl = "jdbc:sqlserver://;" +
	            "servername=csdata.cd4sevot432y.us-east-1.rds.amazonaws.com;"
	            + "user=csc312cloud;password=c3s!c2Cld;"; 

		String dbName = "JLBookstore";
		connectionUrl += "databaseName=" + dbName + ";"; 

		// Declare the JDBC objects.
		
    	try {
    		// Establish the connection.
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        	con = DriverManager.getConnection(connectionUrl);
    	} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public String[] getColNamesForTable(String tableName) {
		// TODO Auto-generated method stub
		String SQL = "SELECT * FROM ";

		try {
			DatabaseMetaData databaseMetaData = con.getMetaData();
			/*
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL + tableName);
	        ResultSetMetaData meta = rs.getMetaData();
	        System.out.println(columns);
	        columnNamesToReturn = new String[columns];
	        for (int i=1; i<=columns; i++) {
	        	columnNamesToReturn[i-1] = meta.getColumnName(i);
	            System.out.print(meta.getColumnName(i) + ", ");
	        }
	        	
	        System.out.println();
			*/
	        ResultSet columns = databaseMetaData.getColumns(null,null, tableName, null);
	        LinkedList<String> columnNames = new LinkedList<String>();
	        while (columns.next()) {
	        	columnNames.add(columns.getString("COLUMN_NAME"));
	        }
	        columnNamesToReturn = columnNames.toArray(new String[columnNames.size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return columnNamesToReturn;
	}

	@Override
	public Object[][] getData(String tableName) {
		// TODO Auto-generated method stub
		String SQL = "SELECT * FROM ";
		String[] row; 
		LinkedList<String[]> contents = new LinkedList<String[]>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL + tableName);
	        ResultSetMetaData meta = rs.getMetaData();
	        int columns = meta.getColumnCount();
	        System.out.println(columns);
	        row = new String[columns];
	        
    		while (rs.next()) {
                for (int i=1; i<=columns; i++) {
                    System.out.print(rs.getString(i) + 
                            ((i==columns)?"":", "));
                	row[i-1] = rs.getString(i);
                }
                System.out.println();
                contents.add(row);
                row = new String[columns];
    		}
	        System.out.println();
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(contents.size() + "," + contents.get(0).length);
		
		dataToReturn = new Object[contents.size()][contents.get(0).length];
		for (int i=0; i<contents.size(); i++)
			dataToReturn[i] = contents.get(i);
		return dataToReturn;
	}

}
