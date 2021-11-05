package person.generator;

import java.util.LinkedHashMap;
import java.util.Map;

public class TableConfig {

	public String name;
	public String className;
	public String enumName;
	
	public Map<String, ColumnConfig> columns = new LinkedHashMap<String, ColumnConfig>();
	
	@Override
	public String toString() {

		return name + " " + columns;
	}
}
