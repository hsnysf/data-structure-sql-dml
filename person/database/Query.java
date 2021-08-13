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
import java.util.LinkedHashMap;
import java.util.Map;

public class Query {

	private Connection connection;
	private Table table;
	private Map<Column, Object> values = new LinkedHashMap<Column, Object>();
	
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
			
			if (value != null) {
				statement.setString(index, String.valueOf(value.toString().trim()));
			} else {
				statement.setNull(index, Types.CHAR);
			}

		}else if(type == Types.SMALLINT){
			
			if(value != null && !value.equals(0)) {
				statement.setShort(index, Short.parseShort(value.toString().trim()));
			}else {
				statement.setNull(index, Types.SMALLINT);
			}
			
		}else if(type == Types.INTEGER){
			
			if(value != null && !value.equals(0)) {
				statement.setInt(index, Integer.parseInt(value.toString().trim()));
			}else {
				statement.setNull(index, Types.INTEGER);
			}
			
		}else if(type == Types.BIGINT){
			
			if(value != null && !value.equals(0)) {
				statement.setLong(index, Long.parseLong(value.toString().trim()));
			}else {
				statement.setNull(index, Types.BIGINT);
			}
			
		}else if(type == Types.FLOAT){
			
			if(value != null && !value.equals(0)) {
				statement.setFloat(index, Float.parseFloat(value.toString().trim()));
			}else {
				statement.setNull(index, Types.FLOAT);
			}
		
		}else if(type == Types.DOUBLE){
			
			if(value != null && !value.equals(0)) {
				statement.setDouble(index, Double.parseDouble(value.toString().trim()));
			}else {
				statement.setNull(index, Types.DOUBLE);
			}
		
		}else if(type == Types.DECIMAL){
			
			if(value != null && !value.equals(0)) {
				statement.setBigDecimal(index, new BigDecimal(value.toString().trim()));
			}else {
				statement.setNull(index, Types.DECIMAL);
			}
		
		}else if(type == Types.DATE){
			
			if(value != null) {
				statement.setDate(index, Date.valueOf(value.toString().trim()));
			}else {
				statement.setNull(index, Types.DATE);
			}
			
		}else if(type == Types.TIMESTAMP){
			
			if(value != null) {
				statement.setTimestamp(index, Timestamp.valueOf(value.toString().trim()));
			}else {
				statement.setNull(index, Types.TIMESTAMP);
			}
		
		}else if(type == Types.TIME){
			
			if(value != null) {
				statement.setTime(index, Time.valueOf(value.toString().trim()));
			}else {
				statement.setNull(index, Types.TIME);
			}
			
		}else if(type == Types.BOOLEAN){
			
			if(value != null) {
				statement.setBoolean(index, Boolean.parseBoolean(value.toString().trim()));
			}else {
				statement.setNull(index, Types.BOOLEAN);
			}
		}
	}
	
	public Object getValue(String value, int type) {
		
		Object result = null;
		
		if(value != null) {
			
			if(type == Types.VARCHAR){
				
				result = value.trim();
				
			}else if(type == Types.CHAR){
				
				result = value.trim().charAt(0);

			}else if(type == Types.SMALLINT){
				
				result = Short.parseShort(value.trim());
				
			}else if(type == Types.INTEGER){
				
				result = Integer.parseInt(value.trim());
				
			}else if(type == Types.BIGINT){
				
				result = Long.parseLong(value.trim());
				
			}else if(type == Types.FLOAT){
				
				result = Float.parseFloat(value.trim());
			
			}else if(type == Types.DOUBLE){
				
				result = Double.parseDouble(value.trim());
			
			}else if(type == Types.DECIMAL){
				
				result = new BigDecimal(value.trim());
			
			}else if(type == Types.DATE){
				
				result = Date.valueOf(value.trim());
				
			}else if(type == Types.TIMESTAMP){
				
				result = Timestamp.valueOf(value.trim());
			
			}else if(type == Types.TIME){
				
				result = Time.valueOf(value.trim());
				
			}else if(type == Types.BOOLEAN){
				
				result = Boolean.parseBoolean(value.trim());
			}
		}
		
		return result;
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
		
		String columnList = "";
		
		String valueList = "";
		
		int index = 0;
		
		for(Map.Entry<Column, Object> entry : values.entrySet()) {
			
			Column column = entry.getKey();
			
			if(index != 0){
				
				columnList += ", ";
				valueList += ", ";
			}
			
			columnList += column.name;
			
			valueList += "?";
			
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
		
		return id;
	}
}
