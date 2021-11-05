package person.database;

public enum Operator {
	
	AND("and"),
	OR("or");
	
	private String operator;
	
	private Operator(String operator) {
		this.operator = operator;
	}
	
	@Override
	public String toString() {
		return operator;
	}
}
