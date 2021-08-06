package person.generator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class TableColumnGenerator {

	public static void main(String[] args) throws Exception {
		
		Map<String, TableConfig> tableMap = GeneratorUtils.getTableMap();
		
		for (TableConfig table : tableMap.values()) {
			
			StringBuilder builder = new StringBuilder();
			
			builder.append("package person.column;");
			builder.append("\r\n\r\n");
			builder.append("import java.sql.Types;");
			builder.append("\r\n");
			builder.append("import person.database.Column;");
			builder.append("\r\n\r\n");
			builder.append("public interface " + table.className + " {");
		    builder.append("\r\n\r\n");
		    
		    for(ColumnConfig column : table.columns.values()){
		    	
		    	builder.append("	Column " + column.enumName + " = new Column(\"" + column.name + "\", Types." + column.type + ");");
		    	builder.append("\r\n");
		    }
		    
		    builder.append("}");
		    
		    Files.write(Paths.get("./src/person/column/" + table.className + ".java"), builder.toString().getBytes());
		}
	}
}
