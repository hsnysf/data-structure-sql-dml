package person.database;

import java.util.ArrayList;
import java.util.List;

public class Restriction {

	protected Operator operator;
	protected Column column;
	protected Criteria criteria;
	protected Object value;
	protected Object values[];
	protected Object to;
	protected Column column2;
	protected Column column3;
	protected Query query;
	protected List<Restriction> restrictions = new ArrayList<Restriction>();
	
	protected Restriction(Column column, Criteria criteria, Object value) {
		this.column = column;
		this.criteria = criteria;
		this.value = value;
	}
	
	protected Restriction(Column column, Criteria criteria, Object values[]) {
		this.column = column;
		this.criteria = criteria;
		this.values = values;
	}
	
	protected Restriction(Column column, Criteria criteria, Column column2) {
		this.column = column;
		this.criteria = criteria;
		this.column2 = column2;
	}
	
	protected Restriction(Column column, Criteria criteria, Object value, Object to) {
		this.column = column;
		this.criteria = criteria;
		this.value = value;
		this.to = to;
	}
	
	protected Restriction(Column column, Criteria criteria, Column column2, Column column3) {
		this.column = column;
		this.criteria = criteria;
		this.column2 = column2;
		this.column3 = column3;
	}
	
	protected Restriction(Column column, Criteria criteria) {
		this.column = column;
		this.criteria = criteria;
	}
	
	protected Restriction(Criteria criteria, Query query) {
		this.criteria = criteria;
		this.query = query;
	}
	
	protected Restriction(Column column, Criteria criteria, Query query) {
		this.column = column;
		this.criteria = criteria;
		this.query = query;
	}
	
	public Restriction and(Restriction restriction) {

		restriction.operator = Operator.AND;
		
		restrictions.add(restriction);
		
		return this;
	}
	
	public Restriction or(Restriction restriction) {

		restriction.operator = Operator.OR;
		
		restrictions.add(restriction);
		
		return this;
	}
}
