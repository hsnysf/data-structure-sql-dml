package person.database;

public enum Function {

	MIN("min"),
	MAX("max"),
	COUNT("count"),
	SUM("sum"),
	AVERAGE("avg"),
	DISTINCT("distinct"),
	COALESCE("coalesce");
	
	private String function;
	
	private Function(String function) {
		this.function = function;
	}
	
	public String toString() {
		return function;
	}
}