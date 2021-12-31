package person.database;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Column {

	protected String name;
	protected String nameInQuery;
	protected String nameInResultSet;
	protected int type;
	protected Function function;
	protected String tableAlias;
	protected String alias;
	protected List<Entry<Integer, Object>> parameters = new ArrayList<Entry<Integer, Object>>();
	
	public Column(String name, int type) {
		this.name = name;
		this.type = type;
		this.nameInQuery = name;
		this.nameInResultSet = nameInQuery;
	}
	
	protected Column(String name, int type, List<Entry<Integer, Object>> parameters) {
		this.name = name;
		this.type = type;
		this.parameters.addAll(parameters);
		this.nameInQuery = name;
		this.nameInResultSet = nameInQuery;
	}
	
	protected Column(String nameInQuery, int type, String alias) {
		this.nameInQuery = nameInQuery;
		this.type = type;
		this.alias = alias;
		this.nameInResultSet = alias;
	}
	
	private Column(String name, String nameInQuery, int type, Function function, String tableAlias, String alias, List<Entry<Integer, Object>> parameters) {
		this.name = name;
		this.nameInQuery = nameInQuery;
		this.type = type;
		this.function = function;
		this.tableAlias = tableAlias;
		this.alias = alias;
		this.nameInResultSet = alias;
		this.parameters.addAll(parameters);
	}
	
	public Column of(String tableAlias) {
		
		return new Column(name, tableAlias + "." + name, type, function, tableAlias, tableAlias + "_" + name, parameters);
	}
	
	public Column as(String alias) {
		
		return new Column(name, nameInQuery, type, function, tableAlias, alias, parameters);
	}
	
	public Column alias(String alias) {
		
		return new Column(name, alias, type, function, tableAlias, alias, parameters);
	}
	
	public Column plus(Column column) {
		
		return new Column(name, nameInQuery + " + " + column.nameInQuery, type, function, tableAlias, name + "_plus", parameters);
	}
	
	public Column plus(Number value) {
		
		Column column = new Column(name, nameInQuery + " + ?", type, function, tableAlias, name + "_plus", parameters);
		
		column.parameters.add(new SimpleEntry<Integer, Object>(type, value));
		
		return column;
	}
	
	public Column plusMinutes(Number value) {
		
		return new Column(name, nameInQuery + " + interval '" + value + " minutes'", type, function, tableAlias, name + "_plus", parameters);
	}
	
	public Column plusHours(Number value) {
		
		return new Column(name, nameInQuery + " + interval '" + value + " hours'", type, function, tableAlias, name + "_plus", parameters);
	}

	public Column plusDays(Number value) {
		
		return new Column(name, nameInQuery + " + interval '" + value + " days'", type, function, tableAlias, name + "_plus", parameters);
	}
	
	public Column plusMonths(Number value) {
		
		return new Column(name, nameInQuery + " + interval '" + value + " months'", type, function, tableAlias, name + "_plus", parameters);
	}
	
	public Column plusYears(Number value) {
		
		return new Column(name, nameInQuery + " + interval '" + value + " years'", type, function, tableAlias, name + "_plus", parameters);
	}
	
	public Column minus(Column column) {
		
		return new Column(name, nameInQuery + " - " + column.nameInQuery, type, function, tableAlias, name + "_minus", parameters);
	}
	
	public Column minus(Number value) {
		
		Column column = new Column(name, nameInQuery + " - ?", type, function, tableAlias, name + "_minus", parameters);
		
		column.parameters.add(new SimpleEntry<Integer, Object>(type, value));
		
		return column;
	}
	
	public Column minusMinutes(Number value) {
		
		return new Column(name, nameInQuery + " - interval '" + value + " minutes'", type, function, tableAlias, name + "_minus", parameters);
	}
	
	public Column minusHours(Number value) {
		
		return new Column(name, nameInQuery + " - interval '" + value + " hours'", type, function, tableAlias, name + "_minus", parameters);
	}

	public Column minusDays(Number value) {
		
		return new Column(name, nameInQuery + " - interval '" + value + " days'", type, function, tableAlias, name + "_minus", parameters);
	}
	
	public Column minusMonths(Number value) {
		
		return new Column(name, nameInQuery + " - interval '" + value + " months'", type, function, tableAlias, name + "_minus", parameters);
	}
	
	public Column minusYears(Number value) {
		
		return new Column(name, nameInQuery + " - interval '" + value + " years'", type, function, tableAlias, name + "_minus", parameters);
	}
	
	public Column multiply(Column column) {
		
		return new Column(name, nameInQuery + " * " + column.nameInQuery, type, function, tableAlias, name + "_multiply", parameters);
	}
	
	public Column multiply(Number value) {
		
		Column column = new Column(name, nameInQuery + " * ?", type, function, tableAlias, name + "_multiply", parameters);
		
		column.parameters.add(new SimpleEntry<Integer, Object>(type, value));
		
		return column;
	}
	
	public Column divide(Column column) {
		
		return new Column(name, nameInQuery + " / " + column.nameInQuery, type, function, tableAlias, name + "_divide", parameters);
	}
	
	public Column divide(Number value) {
		
		Column column = new Column(name, nameInQuery + " / ?", type, function, tableAlias, name + "_divide", parameters);
		
		column.parameters.add(new SimpleEntry<Integer, Object>(type, value));
		
		return column;
	}
	
	public Restriction equal(String value) {
		
		return new Restriction(this, Criteria.EQUAL, Query.getValue(type, value));
	}
	
	public Restriction equal(Character value) {
		
		return new Restriction(this, Criteria.EQUAL, value);
	}
	
	public Restriction equal(Number value) {
		
		return new Restriction(this, Criteria.EQUAL, value);
	}
	
	public Restriction equal(java.util.Date value) {
		
		return new Restriction(this, Criteria.EQUAL, Query.getValue(type, value));
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
		
		return new Restriction(this, Criteria.LIKE, Query.getValue(type, value));
	}
	
	public Restriction notEqual(String value) {
		
		return new Restriction(this, Criteria.NOT_EQUAL, Query.getValue(type, value));
	}
	
	public Restriction notEqual(Character value) {
		
		return new Restriction(this, Criteria.NOT_EQUAL, value);
	}
	
	public Restriction notEqual(Number value) {
		
		return new Restriction(this, Criteria.NOT_EQUAL, value);
	}
	
	public Restriction notEqual(java.util.Date value) {
		
		return new Restriction(this, Criteria.NOT_EQUAL, Query.getValue(type, value));
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
		
		return new Restriction(this, Criteria.GREATER, Query.getValue(type, value));
	}

	public Restriction greater(Number value) {
		
		return new Restriction(this, Criteria.GREATER, value);
	}
	
	public Restriction greater(java.util.Date value) {
		
		return new Restriction(this, Criteria.GREATER, Query.getValue(type, value));
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
		
		return new Restriction(this, Criteria.GREATER_EQUAL, Query.getValue(type, value));
	}
	
	public Restriction greaterEqual(Number value) {
		
		return new Restriction(this, Criteria.GREATER_EQUAL, value);
	}
	
	public Restriction greaterEqual(java.util.Date value) {
		
		return new Restriction(this, Criteria.GREATER_EQUAL, Query.getValue(type, value));
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
		
		return new Restriction(this, Criteria.LESS, Query.getValue(type, value));
	}

	public Restriction less(Number value) {
		
		return new Restriction(this, Criteria.LESS, value);
	}
	
	public Restriction less(java.util.Date value) {
		
		return new Restriction(this, Criteria.LESS, Query.getValue(type, value));
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
		
		return new Restriction(this, Criteria.LESS_EQUAL, Query.getValue(type, value));
	}

	public Restriction lessEqual(Number value) {
		
		return new Restriction(this, Criteria.LESS_EQUAL, value);
	}
	
	public Restriction lessEqual(java.util.Date value) {
		
		return new Restriction(this, Criteria.LESS_EQUAL, Query.getValue(type, value));
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
		
		return new Restriction(this, Criteria.IN, Query.getValues(type, values));
	}
	
	public Restriction in(Character ...values) {
		
		return new Restriction(this, Criteria.IN, values);
	}
	
	public Restriction in(Number ...values) {
		
		return new Restriction(this, Criteria.IN, values);
	}
	
	public Restriction in(java.util.Date ...values) {
		
		return new Restriction(this, Criteria.IN, Query.getValues(type, values));
	}
	
	public Restriction in(Date ...values) {
		
		return new Restriction(this, Criteria.IN, values);
	}
	
	public Restriction notIn(String ...values) {
		
		return new Restriction(this, Criteria.NOT_IN, Query.getValues(type, values));
	}
	
	public Restriction notIn(Character ...values) {
		
		return new Restriction(this, Criteria.NOT_IN, values);
	}
	
	public Restriction notIn(Number ...values) {
		
		return new Restriction(this, Criteria.NOT_IN, values);
	}
	
	public Restriction notIn(java.util.Date ...values) {
		
		return new Restriction(this, Criteria.NOT_IN, Query.getValues(type, values));
	}
	
	public Restriction notIn(Date ...values) {
		
		return new Restriction(this, Criteria.NOT_IN, values);
	}

	public Restriction between(String from, String to) {
		
		return new Restriction(this, Criteria.BETWEEN, Query.getValue(type, from), Query.getValue(type, to));
	}

	public Restriction between(Number from, Number to) {
		
		return new Restriction(this, Criteria.BETWEEN, from, to);
	}
	
	public Restriction between(java.util.Date from, java.util.Date to) {
		
		return new Restriction(this, Criteria.BETWEEN, Query.getValue(type, from), Query.getValue(type, to));
	}
	
	public Restriction between(Date from, Date to) {
		
		return new Restriction(this, Criteria.BETWEEN, from, to);
	}
	
	public Restriction between(Timestamp from, Timestamp to) {
		
		return new Restriction(this, Criteria.BETWEEN, from, to);
	}
	
	public Restriction between(Time from, Time to) {
		
		return new Restriction(this, Criteria.BETWEEN, from, to);
	}
	
	public Restriction in(Query query) {
		
		return new Restriction(this, Criteria.IN_QUERY, query);
	}
	
	public Restriction notIn(Query query) {
		
		return new Restriction(this, Criteria.NOT_IN_QUERY, query);
	}
	
	public Column over() {
		
		return this;
	}
	
	public Column over(RowNumber rowNumber) {
		
		StringBuilder builder = new StringBuilder();
		
		if(!rowNumber.partitions.isEmpty()) {
			
			builder.append("partition by ");
			
			int index = 0;
			
			for(Column partition : rowNumber.partitions) {
				
				if(index != 0) {
					
					builder.append(", ");
				}
				
				builder.append(partition);
				
				index++;
			}
		}
		
		if (!rowNumber.partitions.isEmpty() && !rowNumber.orders.isEmpty()) {
			
			builder.append(" ");
		}
		
		if (!rowNumber.orders.isEmpty()) {

			builder.append("order by ");

			int index = 0;
			
			for(Map.Entry<Column, Order> entry : rowNumber.orders.entrySet()){
				
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
		
		return this;
	}
	
	public Entry<Column, Order> asc() {
		
		return new SimpleEntry<Column, Order>(this, Order.ASC);
	}
	
	public Entry<Column, Order> desc() {
		
		return new SimpleEntry<Column, Order>(this, Order.DESC);
	}
	
	protected Column copyWithFunction(Function function) {
		
		return new Column(name, function + "(" + nameInQuery + ")", type, function, tableAlias, name + "_" + function, parameters);
	}
	
	protected Column copyDateWithFunction(Function function) {
		
		return new Column(name, "date_part('" + function + "', " + nameInQuery + ")", Types.INTEGER, function, tableAlias, name + "_" + function, parameters);
	}
	
	protected Column copyWithFunction(Function function, Column column) {
		
		return new Column(name, function + "(" + nameInQuery + ", " + column.nameInQuery + ")", type, function, tableAlias, name + "_" + function, parameters);
	}
	
	protected Column copyWithFunction(Function function, Object value) {
		
		Column column = new Column(name, function + "(" + nameInQuery + ", ?)", type, function, tableAlias, name + "_" + function, parameters);
		
		column.parameters.add(new SimpleEntry<Integer, Object>(type, value));
		
		return column;
	}
	
	@Override
	public boolean equals(Object object) {
		
		if(object != null && object instanceof Column) {
			
			Column column = (Column) object;
			
			return column.nameInQuery.equals(nameInQuery);
			
		}else {
			
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return nameInQuery.length();
	}
	
	@Override
	public String toString() {
		return nameInQuery;
	}
}
