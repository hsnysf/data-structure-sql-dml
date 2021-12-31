package person.database;

public enum Function {

	MIN("min"),
	MAX("max"),
	COUNT("count"),
	SUM("sum"),
	AVERAGE("avg"),
	DISTINCT("distinct"),
	COALESCE("coalesce"),
	DATE("date"),
	TIME("time"),
	YEAR("year"),
	MONTH("month"),
	DAY("day"),
	HOUR("hour"),
	MINUTE("minute"),
	SECOND("second");
	
	private String function;
	
	private Function(String function) {
		this.function = function;
	}
	
	public String toString() {
		return function;
	}
}