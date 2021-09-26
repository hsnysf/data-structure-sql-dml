package person.database;

public enum Aggregation {

	DISTINCT("distinct"),
	MIN("min"),
	MAX("max"),
	COUNT("count"),
	SUM("sum"),
	AVG("avg");
	
	private String aggregation;
	
	private Aggregation(String aggregation) {
		this.aggregation = aggregation;
	}
	
	public String toString() {
		return aggregation;
	}
}