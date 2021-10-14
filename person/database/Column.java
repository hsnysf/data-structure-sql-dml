package person.database;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

public class Column {

	protected String name;
	protected String nameInQuery;
	protected int type;
	protected Function function;
	protected String tableAlias;
	protected String alias;
	
	public Column(String name, int type) {
		this.name = name;
		this.type = type;
		this.nameInQuery = name;
	}
	
	private Column(String name, String nameInQuery, int type, Function function, String tableAlias, String alias) {
		this.name = name;
		this.nameInQuery = nameInQuery;
		this.type = type;
		this.function = function;
		this.tableAlias = tableAlias;
		this.alias = alias;
	}
	
	public Column of(String tableAlias) {
		
		return new Column(name, tableAlias + "." + name, type, function, tableAlias, tableAlias + "_" + name);
	}
	
	public Column as(String alias) {
		
		return new Column(name, nameInQuery, type, function, tableAlias, alias);
	}
	
	public Restriction equal(String value) {
		
		return new Restriction(this, Criteria.EQUAL, value);
	}
	
	public Restriction equal(Character value) {
		
		return new Restriction(this, Criteria.EQUAL, value);
	}
	
	public Restriction equal(Number value) {
		
		return new Restriction(this, Criteria.EQUAL, value);
	}
	
	public Restriction equal(Date value) {
		
		return new Restriction(this, Criteria.EQUAL, value);
	}
	
	public Restriction equal(Timestamp value) {
		
		return new Restriction(this, Criteria.EQUAL, value);
	}
	
	public Restriction equal(Time value) {
		
		return new Restriction(this, Criteria.EQUAL, value);
	}
	
	public Restriction equal(Boolean value) {
		
		return new Restriction(this, Criteria.EQUAL, value);
	}
	
	public Restriction equal(Column column) {
		
		return new Restriction(this, Criteria.EQUAL_COLUMN, column);
	}
	
	public Restriction like(String value) {
		
		return new Restriction(this, Criteria.LIKE, value);
	}
	
	public Restriction notEqual(String value) {
		
		return new Restriction(this, Criteria.NOT_EQUAL, value);
	}
	
	public Restriction notEqual(Character value) {
		
		return new Restriction(this, Criteria.NOT_EQUAL, value);
	}
	
	public Restriction notEqual(Number value) {
		
		return new Restriction(this, Criteria.NOT_EQUAL, value);
	}
	
	public Restriction notEqual(Date value) {
		
		return new Restriction(this, Criteria.NOT_EQUAL, value);
	}
	
	public Restriction notEqual(Timestamp value) {
		
		return new Restriction(this, Criteria.NOT_EQUAL, value);
	}
	
	public Restriction notEqual(Time value) {
		
		return new Restriction(this, Criteria.NOT_EQUAL, value);
	}
	
	public Restriction notEqual(Boolean value) {
		
		return new Restriction(this, Criteria.NOT_EQUAL, value);
	}
	
	public Restriction notEqual(Column column) {
		
		return new Restriction(this, Criteria.NOT_EQUAL_COLUMN, column);
	}
	
	public Restriction greater(String value) {
		
		return new Restriction(this, Criteria.GREATER, value);
	}

	public Restriction greater(Number value) {
		
		return new Restriction(this, Criteria.GREATER, value);
	}
	
	public Restriction greater(Date value) {
		
		return new Restriction(this, Criteria.GREATER, value);
	}
	
	public Restriction greater(Timestamp value) {
		
		return new Restriction(this, Criteria.GREATER, value);
	}
	
	public Restriction greater(Time value) {
		
		return new Restriction(this, Criteria.GREATER, value);
	}
	
	public Restriction greater(Column column) {
		
		return new Restriction(this, Criteria.GREATER_COLUMN, column);
	}
	
	public Restriction greaterEqual(String value) {
		
		return new Restriction(this, Criteria.GREATER_EQUAL, value);
	}
	
	public Restriction greaterEqual(Number value) {
		
		return new Restriction(this, Criteria.GREATER_EQUAL, value);
	}
	
	public Restriction greaterEqual(Date value) {
		
		return new Restriction(this, Criteria.GREATER_EQUAL, value);
	}
	
	public Restriction greaterEqual(Timestamp value) {
		
		return new Restriction(this, Criteria.GREATER_EQUAL, value);
	}
	
	public Restriction greaterEqual(Time value) {
		
		return new Restriction(this, Criteria.GREATER_EQUAL, value);
	}
	
	public Restriction greaterEqual(Column column) {
		
		return new Restriction(this, Criteria.GREATER_EQUAL_COLUMN, column);
	}
	
	public Restriction less(String value) {
		
		return new Restriction(this, Criteria.LESS, value);
	}

	public Restriction less(Number value) {
		
		return new Restriction(this, Criteria.LESS, value);
	}
	
	public Restriction less(Date value) {
		
		return new Restriction(this, Criteria.LESS, value);
	}
	
	public Restriction less(Timestamp value) {
		
		return new Restriction(this, Criteria.LESS, value);
	}
	
	public Restriction less(Time value) {
		
		return new Restriction(this, Criteria.LESS, value);
	}
	
	public Restriction less(Column column) {
		
		return new Restriction(this, Criteria.LESS_COLUMN, column);
	}
	
	public Restriction lessEqual(String value) {
		
		return new Restriction(this, Criteria.LESS_EQUAL, value);
	}

	public Restriction lessEqual(Number value) {
		
		return new Restriction(this, Criteria.LESS_EQUAL, value);
	}
	
	public Restriction lessEqual(Date value) {
		
		return new Restriction(this, Criteria.LESS_EQUAL, value);
	}
	
	public Restriction lessEqual(Timestamp value) {
		
		return new Restriction(this, Criteria.LESS_EQUAL, value);
	}
	
	public Restriction lessEqual(Time value) {
		
		return new Restriction(this, Criteria.LESS_EQUAL, value);
	}
	
	public Restriction lessEqual(Column column) {
		
		return new Restriction(this, Criteria.LESS_EQUAL_COLUMN, column);
	}
	
	public Restriction isNull() {
		
		return new Restriction(this, Criteria.IS_NULL);
	}
	
	public Restriction isNotNull() {
		
		return new Restriction(this, Criteria.IS_NOT_NULL);
	}
	
	public Restriction in(String ...values) {
		
		return new Restriction(this, Criteria.IN, values);
	}
	
	public Restriction in(Character ...values) {
		
		return new Restriction(this, Criteria.IN, values);
	}
	
	public Restriction in(Number ...values) {
		
		return new Restriction(this, Criteria.IN, values);
	}
	
	public Restriction in(Date ...values) {
		
		return new Restriction(this, Criteria.IN, values);
	}
	
	public Restriction notIn(String ...values) {
		
		return new Restriction(this, Criteria.NOT_IN, values);
	}
	
	public Restriction notIn(Character ...values) {
		
		return new Restriction(this, Criteria.NOT_IN, values);
	}
	
	public Restriction notIn(Number ...values) {
		
		return new Restriction(this, Criteria.NOT_IN, values);
	}
	
	public Restriction notIn(Date ...values) {
		
		return new Restriction(this, Criteria.NOT_IN, values);
	}
	
	public Restriction between(String value, String to) {
		
		return new Restriction(this, Criteria.BETWEEN, value, to);
	}

	public Restriction between(Number value, Number to) {
		
		return new Restriction(this, Criteria.BETWEEN, value, to);
	}
	
	public Restriction between(Date value, Date to) {
		
		return new Restriction(this, Criteria.BETWEEN, value, to);
	}
	
	public Restriction between(Timestamp value, Timestamp to) {
		
		return new Restriction(this, Criteria.BETWEEN, value, to);
	}
	
	public Restriction between(Time value, Time to) {
		
		return new Restriction(this, Criteria.BETWEEN, value, to);
	}
	
	public Restriction in(Query query) {
		
		return new Restriction(this, Criteria.IN_QUERY, query);
	}
	
	public Restriction notIn(Query query) {
		
		return new Restriction(this, Criteria.NOT_IN_QUERY, query);
	}
	
	public Entry<Column, Order> asc() {
		
		return new SimpleEntry<Column, Order>(this, Order.ASC);
	}
	
	public Entry<Column, Order> desc() {
		
		return new SimpleEntry<Column, Order>(this, Order.DESC);
	}
	
	public Column copyWithFunction(Function function) {
		
		return new Column(name, function + "(" + name + ")", type, function, tableAlias, name + "_" + function);
	}
	
	public Column copyWithFunction(Function function, String value) {
		
		return new Column(name, function + "(" + name + ", '" + value + "')", type, function, tableAlias, name + "_" + function);
	}
	
	@Override
	public String toString() {
		return nameInQuery;
	}
}
