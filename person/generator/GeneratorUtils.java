package person.generator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.LinkedHashMap;
import java.util.Map;

public class GeneratorUtils {

	public static Connection getConnection() throws Exception{
		
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
	}
	
	static DatabaseMetaData databaseMetaData;

	static {

		try {

			Connection connection = getConnection();

			databaseMetaData = connection.getMetaData();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public static Map<String, TableConfig> getTableMap() throws Exception {

		Map<String, TableConfig> tableMap = new LinkedHashMap<String, TableConfig>();
		
		ResultSet result = databaseMetaData.getTables(null, "public", null, new String[] {"TABLE"});
		
		while (result.next()) {
			
			TableConfig tableConfig = new TableConfig();
			
			tableConfig.name = result.getString("TABLE_NAME");
			tableConfig.className = prepareTableClassName(tableConfig.name);
			tableConfig.enumName = tableConfig.name.toUpperCase();
			tableConfig.columns = getTableColumnMap(tableConfig.name);
			
			tableMap.put(tableConfig.name, tableConfig);
		}
		
		return tableMap;
	}
	
	public static Map<String, ColumnConfig> getTableColumnMap(String table) throws Exception {
		
		Map<String, ColumnConfig> columnMap = new LinkedHashMap<String, ColumnConfig>();
		
		ResultSet columnSet = databaseMetaData.getColumns(null, null, table, null);
		
		while (columnSet.next()) {
			
			ColumnConfig column = new ColumnConfig();
			column.name = columnSet.getString("COLUMN_NAME");
			column.enumName = prepareColumnEnumName(column.name);
			column.type = getType(columnSet.getInt("DATA_TYPE"));

			columnMap.put(column.name, column);
		}
		
		return columnMap;
	}
	
	public static String getType(int columnType){
		
		if(columnType == Types.INTEGER){
		 
			return "INTEGER";
			 
		}else if(columnType == Types.VARCHAR){
			 
			 return "VARCHAR";
			 
		}else if(columnType == Types.BOOLEAN){
			 
			 return "BOOLEAN";
			 
		}else if(columnType == Types.DECIMAL){
			 
			 return "DECIMAL";
			 
		}else if(columnType == Types.NUMERIC){
	   		 
			return "DECIMAL";
	   	 
	   	 }else if(columnType == Types.BIGINT){
			 
			 return "BIGINT";
			 
		}else if(columnType == Types.DATE){
			 
			 return "DATE";
			 
		}else if(columnType == Types.TIMESTAMP){
			 
			 return "TIMESTAMP";
		 
		}else if(columnType == Types.TIME){
			 
			 return "TIME";
		 
		}else if(columnType == Types.FLOAT){
			 
			 return "FLOAT";
		 
		}else if(columnType == Types.DOUBLE){
			 
			 return "DOUBLE";
		 
		}else if(columnType == Types.BIT){
			 
			 return "BOOLEAN";
		 
		}else if(columnType == Types.SMALLINT){
			 
			 return "SMALLINT";
			 
		}else if(columnType == Types.CHAR){
			 
			 return "CHAR";
			 
		}else {
			
			return null;
		}
	}
	
	public static String prepareTableClassName(String name) {

		char[] chars = name.toCharArray();

		for (int i = 0; i < chars.length; i++) {

			if (i == 0 || chars[i - 1] == '_') {

				chars[i] = Character.toUpperCase(chars[i]);

			} else {

				chars[i] = Character.toLowerCase(chars[i]);
			}
		}

		name = new String(chars);

		name = name.replaceAll("_", "");

		return name;
	}
	
	public static String prepareColumnEnumName(String name) {

		int index = name.indexOf("_") + 1;

		if (index != 0) {

			name = name.substring(index);
		}

		return name.toUpperCase();
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(getTableMap());
	}
}
