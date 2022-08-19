package person.database;

public enum Criteria {

	EQUAL("="),
	LIKE("ilike"),
	NOT_LIKE("not ilike"),
	NOT_EQUAL("<>"),
	GREATER(">"),
	GREATER_EQUAL(">="),
	LESS("<"),
	LESS_EQUAL("<="),
	IS_NULL("is null"),
	IS_NOT_NULL("is not null"),
	IN("in"),
	NOT_IN("not in"),
	BETWEEN("between"),
	EXISTS("exists"),
	NOT_EXISTS("not exists");
	
	private String criteria;
	
	private Criteria(String criteria){
		this.criteria = criteria;
	}
	
	@Override
	public String toString() {
		return criteria;
	}
}
