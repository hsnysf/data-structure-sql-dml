package person.generator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TableClassGenerator {

	public static void main(String[] args) throws Exception {
		
		List<TableConfig> tableMap = GeneratorUtils.getTableMap();
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("package person.database;");
		builder.append("\r\n\r\n");
		builder.append("public class Table {");
		builder.append("\r\n\r\n");
		
		for(TableConfig tableConfig : tableMap){
			
			builder.append("	public final static Table " + tableConfig.enumName + " = new Table(\"public." + tableConfig.name + "\");");
		    builder.append("\r\n");
		}
		
		builder.append("\r\n");
		builder.append("	protected String name;");
		builder.append("\r\n");
		builder.append("	protected String alias;");
		
		builder.append("\r\n\r\n");
		builder.append("	private Table(String name) {");
		builder.append("\r\n");
		builder.append("		this.name = name;");
		builder.append("\r\n");
		builder.append("	}");
		
		builder.append("\r\n\r\n");
		builder.append("	private Table(String name, String alias) {");
		builder.append("\r\n");
		builder.append("		this.name = name;");
		builder.append("\r\n");
		builder.append("		this.alias = alias;");
		builder.append("\r\n");
		builder.append("	}");
		
		builder.append("\r\n\r\n");
		builder.append("	public Table as(String alias) {");
		builder.append("\r\n");
		builder.append("		return new Table(name, alias);");
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