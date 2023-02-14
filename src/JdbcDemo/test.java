package JdbcDemo;
public class test{
    public static void main (String[] args){
        SqlServerDbAccessor DB = new SqlServerDbAccessor("csdata.cd4sevot432y.us-east-1.rds.amazonaws.com", "csc312cloud", "c3s!c2Cld", "Payable");
        DB.connectToDb();

    }
}