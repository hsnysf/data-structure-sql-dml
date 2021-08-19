package person.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

public class Query {

	private Connection connection;
	private Table table;
	private Map<Column, Object> values = new LinkedHashMap<Column, Object>();
	private List<Restriction> restrictions = new ArrayList<Restriction>();
	
	public Query(Connection connection) {
		this.connection = connection;
	}
	
	public void setObject(PreparedStatement statement, int index, int type, Object value) throws SQLException {
		
		System.out.println("Param #" + index + " :: " + value);
		
		if(type == Types.VARCHAR){
			
			if (value != null && !"".equals(value.toString().trim())) {
				statement.setString(index, value.toString().trim());
			} else {
				statement.setNull(index, Types.VARCHAR);
			}
			
		}else if(type == Types.CHAR){
			
			if (value != null && !"".equals(value.toString().trim())) {
				statement.setString(index, value.toString().trim());
			} else {
				statement.setNull(index, Types.CHAR);
			}

		}else if(type == Types.SMALLINT){
			
			if(value != null && !"".equals(value.toString().trim())) {
				statement.setShort(index, Short.parseShort(value.toString().trim()));
			}else {
				statement.setNull(index, Types.SMALLINT);
			}
			
		}else if(type == Types.INTEGER){
			
			if(value != null && !"".equals(value.toString().trim())) {
				statement.setInt(index, Integer.parseInt(value.toString().trim()));
			}else {
				statement.setNull(index, Types.INTEGER);
			}
			
		}else if(type == Types.BIGINT){
			
			if(value != null && !"".equals(value.toString().trim())) {
				statement.setLong(index, Long.parseLong(value.toString().trim()));
			}else {
				statement.setNull(index, Types.BIGINT);
			}
			
		}else if(type == Types.FLOAT){
			
			if(value != null && !"".equals(value.toString().trim())) {
				statement.setFloat(index, Float.parseFloat(value.toString().trim()));
			}else {
				statement.setNull(index, Types.FLOAT);
			}
		
		}else if(type == Types.DOUBLE){
			
			if(value != null && !"".equals(value.toString().trim())) {
				statement.setDouble(index, Double.parseDouble(value.toString().trim()));
			}else {
				statement.setNull(index, Types.DOUBLE);
			}
		
		}else if(type == Types.DECIMAL){
			
			if(value != null && !"".equals(value.toString().trim())) {
				statement.setBigDecimal(index, new BigDecimal(value.toString().trim()));
			}else {
				statement.setNull(index, Types.DECIMAL);
			}
		
		}else if(type == Types.DATE){
			
			if(value != null && !"".equals(value.toString().trim())) {
				statement.setDate(index, Date.valueOf(value.toString().trim()));
			}else {
				statement.setNull(index, Types.DATE);
			}
			
		}else if(type == Types.TIMESTAMP){
			
			if(value != null && !"".equals(value.toString().trim())) {
				statement.setTimestamp(index, Timestamp.valueOf(value.toString().trim()));
			}else {
				statement.setNull(index, Types.TIMESTAMP);
			}
		
		}else if(type == Types.TIME){
			
			if(value != null && !"".equals(value.toString().trim())) {
				statement.setTime(index, Time.valueOf(value.toString().trim()));
			}else {
				statement.setNull(index, Types.TIME);
			}
			
		}else if(type == Types.BOOLEAN){
			
			if(value != null && !"".equals(value.toString().trim())) {
				statement.setBoolean(index, Boolean.parseBoolean(value.toString().trim()));
			}else {
				statement.setNull(index, Types.BOOLEAN);
			}
		}
	}
	
	public Query insertInto(Table table) {

		this.table = table;
		
		return this;
	}
	
	public Query values(Column column, String value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query values(Column column, Character value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query values(Column column, Number value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query values(Column column, Date value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query values(Column column, Timestamp value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query values(Column column, Time value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query values(Column column, Boolean value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public int executeInsert() throws SQLException {
		
		int id = 0;
		
		StringBuilder columnList = new StringBuilder();
		
		StringBuilder valueList = new StringBuilder();
		
		int index = 0;
		
		for(Column column : values.keySet()) {
			
			if(index != 0){
				
				columnList.append(", ");
				valueList.append(", ");
			}
			
			columnList.append(column.name);
			
			valueList.append("?");
			
			index++;
		}
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("insert into ");
		builder.append(table.name);
		builder.append(" (");
		builder.append(columnList);
		builder.append(") values (");
		builder.append(valueList);
		builder.append(")");
		
		System.out.println("SQL Query :: " + builder);
		
		try(PreparedStatement statement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS)){
			
			index = 1;
			
			for(Map.Entry<Column, Object> entry : values.entrySet()){
				
				Column column = entry.getKey();
				Object value = entry.getValue();
				
				setObject(statement, index, column.type, value);
				
				index++;
			}
			
			statement.executeUpdate();
			
			try(ResultSet result = statement.getGeneratedKeys()){
				
				if(result.next()) {

					id = result.getInt(1);
				}
			}
		}
		
		System.out.println("Generated ID :: " + id);
		
		table = null;
		
		values.clear();
		
		return id;
	}
	
	public Query deleteFrom(Table table) {
		
		this.table = table;
		
		return this;
	}
	
	public Query where(Restriction restriction) {

		if(!restrictions.isEmpty()) {
			
			restriction.operator = Operator.AND;
		}
		
		restrictions.add(restriction);
		
		return this;
	}
	
	public Query and(Restriction restriction) {

		restriction.operator = Operator.AND;
		
		restrictions.add(restriction);
		
		return this;
	}
	
	public Query or(Restriction restriction) {

		restriction.operator = Operator.OR;
		
		restrictions.add(restriction);
		
		return this;
	}
	
	public StringBuilder buildRestriction(Restriction restriction, List<Entry<Column, Object>> parameters){
		
		StringBuilder builder = new StringBuilder();
		
		if(restriction.operator != null){
			
			builder.append(" ");
			builder.append(restriction.operator);
		}
		
		if(restriction.criteria == Criteria.EQUAL 
				|| restriction.criteria == Criteria.NOT_EQUAL
				|| restriction.criteria == Criteria.GREATER 
				|| restriction.criteria == Criteria.GREATER_EQUAL
				|| restriction.criteria == Criteria.LESS
				|| restriction.criteria == Criteria.LESS_EQUAL){

			builder.append(" ");
			builder.append(restriction.column.name);
			builder.append(" ");
			builder.append(restriction.criteria);
			builder.append(" ");
			builder.append("?");
			
			parameters.add(new SimpleEntry<Column, Object>(restriction.column, restriction.value));
			
		}else if(restriction.criteria == Criteria.LIKE){
			
			builder.append(" ");
			builder.append(restriction.column.name);
			builder.append(" ");
			builder.append(restriction.criteria);
			builder.append(" ");
			builder.append("'%' || ? || '%'");
			
			parameters.add(new SimpleEntry<Column, Object>(restriction.column, restriction.value));
		}
		
		return builder;
	}
	
	public int executeDelete() throws SQLException {
		
		int count = 0;
		
		List<Entry<Column, Object>> parameters = new ArrayList<Entry<Column, Object>>();
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("delete from ");
		builder.append(table.name);
		
		if(!restrictions.isEmpty()) {
			
			builder.append(" where");
			
			for(Restriction restriction : restrictions) {
				
				builder.append(buildRestriction(restriction, parameters));
			}
		}
		
		System.out.println("SQL Query :: " + builder);
		
		try(PreparedStatement statement = connection.prepareStatement(builder.toString())){
			
			int index = 1;
			
			for(Entry<Column, Object> parameter : parameters) {
				
				Column column = parameter.getKey();
				Object value = parameter.getValue();
				
				setObject(statement, index, column.type, value);
				
				index++;
			}
			
			count = statement.executeUpdate();
		}
		
		System.out.println("Affected Rows :: " + count);
		
		table = null;
		
		restrictions.clear();

		return count;
	}
}
