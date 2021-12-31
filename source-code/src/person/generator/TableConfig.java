package person.generator;

import java.util.ArrayList;
import java.util.List;

public class TableConfig {

	public String name;
	public String className;
	public String enumName;
	public List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
	
	@Override
	public String toString() {

		return name + " " + columns;
	}
}
