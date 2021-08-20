package person.generator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class TableClassGenerator {

	public static void main(String[] args) throws Exception {
		
		Map<String, TableConfig> tableMap = GeneratorUtils.getTableMap();
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("package person.database;");
		builder.append("\r\n\r\n");
		builder.append("public enum Table {");
		builder.append("\r\n\r\n");
		
		int index = 1;
		
		for(TableConfig tableConfig : tableMap.values()){
			
			builder.append("	" + tableConfig.enumName + "(\"public." + tableConfig.name + "\")");
			builder.append(index == tableMap.size() ? ";" : ",");
		    builder.append("\r\n");
		    
		    index++;
		}
		
		builder.append("\r\n");
		builder.append("	public String name;");
		
		builder.append("\r\n\r\n");
		builder.append("	private Table(String name) {");
		builder.append("\r\n");
		builder.append("		this.name = name;");
		builder.append("\r\n");
		builder.append("	}");
		
		builder.append("\r\n\r\n");
		builder.append("	public String toString() {");
		builder.append("\r\n");
		builder.append("		return name;");
		builder.append("\r\n");
		builder.append("	}");
		
		builder.append("\r\n");
		builder.append("}");
		
		Files.write(Paths.get("./src/person/database/Table.java"), builder.toString().getBytes());
	}
}
