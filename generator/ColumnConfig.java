package person.generator;

public class ColumnConfig {

	public String name;
	public String enumName;
	public String type;
	
	@Override
	public String toString() {
		return "[name=" + name + ", type=" + type + "]";
	}
}
