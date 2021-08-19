package person.database;

public class Restriction {

	public Operator operator;
	public Column column;
	public Criteria criteria;
	public Object value;
	
	public Restriction(Column column, Criteria criteria, Object value) {
		this.column = column;
		this.criteria = criteria;
		this.value = value;
	}
}
