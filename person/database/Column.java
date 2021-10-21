package person.database;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

public class Column {

	protected String name;
	protected String nameInQuery;
	protected int type;
	protected Function function;
	protected String tableAlias;
	protected String alias;
	protected List<Column> partitions = new ArrayList<Column>();
	protected Map<Column, Order> orders = new LinkedHashMap<Column, Order>();
	
	public Column(String name, int type) {
		this.name = name;
		this.type = type;
		this.nameInQuery = name;
	}
	
	public Column(String nameInQuery, int type, String alias) {
		this.nameInQuery = nameInQuery;
		this.type = type;
		this.alias = alias;
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
	
	public Column alias(String alias) {
		
		return new Column(name, alias, type, function, tableAlias, alias);
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
	
	public Column partitionBy(Column... columns) {
		
		partitions.addAll(Arrays.asList(columns));

		updateRowNumberName();
		
		return this;
	}
	
	public Column orderBy(Column... columns) {

		for(Column column : columns){
			
			orders.put(column, Order.ASC);
		}
		
		updateRowNumberName();
		
		return this;
	}
	
	public Column orderBy(Entry<Column, Order>... entries) {

		for(Entry<Column, Order> entry : entries){
			
			orders.put(entry.getKey(), entry.getValue());
		}
		
		updateRowNumberName();
		
		return this;
	}
	
	public Column orderByDesc(Column... columns) {

		for(Column column : columns){
			
			orders.put(column, Order.DESC);
		}
		
		updateRowNumberName();
		
		return this;
	}
	
	public void updateRowNumberName() {
		
		StringBuilder builder = new StringBuilder();
		
		if(!partitions.isEmpty()) {
			
			builder.append("partition by ");
			
			int index = 0;
			
			for(Column partition : partitions) {
				
				if(index != 0) {
					
					builder.append(", ");
				}
				
				builder.append(partition);
				
				index++;
			}
		}
		
		if (!partitions.isEmpty() && !orders.isEmpty()) {
			
			builder.append(" ");
		}
		
		if (!orders.isEmpty()) {

			builder.append("order by ");

			int index = 0;
			
			for(Map.Entry<Column, Order> entry : orders.entrySet()){
				
				Column column = entry.getKey();
				Order order = entry.getValue();
				
				if(index != 0){
					
					builder.append(", ");
				}
				
				builder.append(column);
				
				if(order == Order.DESC){
					
					builder.append(" ");
					builder.append(order);
				}
				
				index++;
			}
		}
		
		nameInQuery = "row_number() over(" + builder + ")";
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
