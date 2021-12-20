package person.database;

public enum CombineOperator {

	UNION("union"),
	UNION_ALL("union all"),
	INTERSECT("intersect"),
	INTERSECT_ALL("intersect all"),
	EXCEPT("except"),
	EXPECT_ALL("except all");
	
	private String operator;
	
	private CombineOperator(String operator) {
		this.operator = operator;
	}
	
	@Override
	public String toString() {
		return operator;
	}
}
