package person.database;

public enum Criteria {

	EQUAL("="),
	LIKE("ilike"),
	NOT_EQUAL("<>"),
	GREATER(">"),
	GREATER_EQUAL(">="),
	LESS("<"),
	LESS_EQUAL("<=");
	
	private String criteria;
	
	private Criteria(String criteria){
		this.criteria = criteria;
	}
	
	@Override
	public String toString() {
		return criteria;
	}
}
