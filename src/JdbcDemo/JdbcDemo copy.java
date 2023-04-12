package JdbcDemo;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
public class JdbcDemo {
    public static void main(String[] args) {
// TODO Auto-generated method stub
        Connection con;
        Statement stmt;
        //PreparedStatement prepStmt;
        //ResultSet rs;
        String connectionUrl = "jdbc:sqlserver://;" +
                "servername=csdata.cd4sevot432y.us-east-1.rds.amazonaws.com;"
                + "user=csc312cloud;password=c3s!c2Cld;";
        String dbName = "JLBookstore";
        connectionUrl += "databaseName=" + dbName + ";";
        String query = "SELECT b.Title, CONCAT(a.Fname,' ', a.Lname) as [Author Name], p.Name as [Publisher Name]"+
        "\nFROM [JLBookstore].[dbo].[Books] b, [JLBookstore].[dbo].[Author] a, [JLBookstore].[dbo].[BOOKAUTHOR] BA, [JLBookstore].[dbo].[Publisher] p"+
        "\nWHERE (b.ISBN = BA.ISBN) and (a.AuthorID = BA.AuthorID) AND (b.PubID = p.PubID) AND b.category = 'COMPUTER'"+
        "\nORDER BY b.Title, a.Lname";
  
  
        ResultSet result = null;
        try {
            con = DriverManager.getConnection(connectionUrl);
            stmt = con.createStatement();
            result = stmt.executeQuery(query);
            // get meta data from result and print column labels
            ResultSetMetaData meta = result.getMetaData();
            int columns = meta.getColumnCount();
            //System.out.println(columns);
            int j;
            for (j=1; j<=columns; j++){
                System.out.print(meta.getColumnName(j)+"    \t\t\t");
            }
            System.out.println("\n");
            
            while (result.next()) {
                for (int i=1; i<=columns; i++)
                    System.out.print(result.getString(i) +
                            ((i==columns)?"":"\t\t"));
                System.out.println("\n");
            }
            
        } catch (SQLException e) {
        
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
