package JdbcDemo;

public interface DataObjectAccessor {
	String[] getColNamesForTable(String tableName);
	
	Object[][] getData(String tableName);
}
