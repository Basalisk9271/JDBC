package JdbcDemo;

import java.sql.*;

public class DisplayUsers {

	public static void main(String[] args) {
		
		// Create a variable for the connection string.
		/*
		String connectionUrl = "jdbc:sqlserver://;servername=cssql\\sqldata;"
				+ "user=csc480dev;password=c4s*C0sWe;" +
			"databaseName=JLBookstore;";
		*/
		String connectionUrl = "jdbc:sqlserver://;" +
            "servername=csdata.cd4sevot432y.us-east-1.rds.amazonaws.com;"
			+ "user=csc312cloud;password=c3s!c2Cld;" +
			"databaseName=StudTest;";

		// Declare the JDBC objects.
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
        try {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
    
            // Create and execute an SQL statement that returns some data.
            String SQL = "SELECT * FROM GI27Login";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            ResultSetMetaData meta = rs.getMetaData();
            int columns = meta.getColumnCount();
            System.out.println(columns);
            int j;
            for (j=1; j<columns; j++)
                System.out.print(meta.getColumnName(j) + ", ");
                
            System.out.print(meta.getColumnName(j));
            System.out.println();
    
            // Iterate through the data in the result set and display it.
                String curRow = "";
            while (rs.next()) {
                    for (int i=1; i<=columns; i++)
                        if ((meta.getColumnName(i)).compareTo("Photo") != 0){
                            System.out.print(rs.getString(i) + 
                                    ((i==columns)?"":", "));
                        }
                //System.out.println(rs.getString(2) + " " + rs.getString(1));
                //System.out.println(rs.wasNull());
                    System.out.println();
            }
        }
        
		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (rs != null) try { rs.close(); } catch(Exception e) {}
	    		if (stmt != null) try { stmt.close(); } catch(Exception e) {}
	    		if (con != null) try { con.close(); } catch(Exception e) {}
		}
	}
}