package person.database;

public enum Operator {
	
	AND("and"),
	OR("or");
	
	String operator;
	
	private Operator(String operator) {
		this.operator = operator;
	}
	
	public String getOperator() {
		return operator;
	}
	
	@Override
	public String toString() {
		return operator;
	}
}
