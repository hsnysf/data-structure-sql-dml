package person.database;

public enum Criteria {

	EQUAL("="),
	EQUAL_COLUMN("="),
	LIKE("ilike"),
	NOT_EQUAL("<>"),
	NOT_EQUAL_COLUMN("<>"),
	GREATER(">"),
	GREATER_COLUMN(">"),
	GREATER_EQUAL(">="),
	GREATER_EQUAL_COLUMN(">="),
	LESS("<"),
	LESS_COLUMN("<"),
	LESS_EQUAL("<="),
	LESS_EQUAL_COLUMN("<="),
	IS_NULL("is null"),
	IS_NOT_NULL("is not null"),
	IN("in"),
	NOT_IN("not in"),
	BETWEEN("between"),
	BETWEEN_COLUMNS("between"),
	IN_QUERY("in"),
	NOT_IN_QUERY("not in"),
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
