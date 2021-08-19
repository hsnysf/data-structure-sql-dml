package person.database;

public enum Criteria {

	EQUAL("="),
	LIKE("ilike"),
	NOT_EQUAL("<>"),
	GREATER(">"),
	GREATER_EQUAL(">="),
	LESS("<"),
	LESS_EQUAL("<=");
	
	String condititon;
	
	private Criteria(String condititon){
		this.condititon = condititon;
	}
	
	public String getCondititon() {
		return condititon;
	}
	
	@Override
	public String toString() {
		return condititon;
	}
}
