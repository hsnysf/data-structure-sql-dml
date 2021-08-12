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
		
		return id;
	}
	
	public void setObject(PreparedStatement statement, int index, int type, Object value) throws SQLException {
		
		System.out.println("Param #" + index + " :: " + value);
		
		if(type == Types.VARCHAR){
			
			if (value != null && !"".equals(value.toString().trim())) {
				statement.setString(index, (String)value);
			} else {
				statement.setNull(index, Types.VARCHAR);
			}
			
		}else if(type == Types.CHAR){
			
			if (value != null) {
				statement.setString(index, String.valueOf(value));
			} else {
				statement.setNull(index, Types.CHAR);
			}

		}else if(type == Types.SMALLINT){
			
			if(value != null && (short)value != 0) {
				statement.setShort(index, (short)value);
			}else {
				statement.setNull(index, Types.SMALLINT);
			}
			
		}else if(type == Types.INTEGER){
			
			if(value != null && (int)value != 0) {
				statement.setInt(index, (int)value);
			}else {
				statement.setNull(index, Types.INTEGER);
			}
			
		}else if(type == Types.BIGINT){
			
			if(value != null && (long)value != 0) {
				statement.setLong(index, (long)value);
			}else {
				statement.setNull(index, Types.BIGINT);
			}
			
		}else if(type == Types.FLOAT){
			
			if(value != null && (float)value != 0) {
				statement.setFloat(index, (float)value);
			}else {
				statement.setNull(index, Types.FLOAT);
			}
		
		}else if(type == Types.DOUBLE){
			
			if(value != null && (double)value != 0) {
				statement.setDouble(index, (double)value);
			}else {
				statement.setNull(index, Types.DOUBLE);
			}
		
		}else if(type == Types.DECIMAL){
			
			if(value != null && !((BigDecimal)value).equals(0)) {
				statement.setBigDecimal(index, (BigDecimal)value);
			}else {
				statement.setNull(index, Types.DECIMAL);
			}
		
		}else if(type == Types.DATE){
			
			if(value != null) {
				statement.setDate(index, (Date)value);
			}else {
				statement.setNull(index, Types.DATE);
			}
			
		}else if(type == Types.TIMESTAMP){
			
			if(value != null) {
				statement.setTimestamp(index, (Timestamp)value);
			}else {
				statement.setNull(index, Types.TIMESTAMP);
			}
		
		}else if(type == Types.TIME){
			
			if(value != null) {
				statement.setTime(index, (Time)value);
			}else {
				statement.setNull(index, Types.TIME);
			}
			
		}else if(type == Types.BOOLEAN){
			
			if(value != null) {
				statement.setBoolean(index, (boolean)value);
			}else {
				statement.setNull(index, Types.BOOLEAN);
			}
		}
	}
}
