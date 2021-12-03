package person.database;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class Query {
	
	public final static Column _1 = new Column("1", Types.INTEGER);
	public final static Column CURRENT_DATE = new Column("current_date", Types.DATE);
	public final static Column CURRENT_TIMESTAMP = new Column("current_timestamp", Types.TIMESTAMP);
	public final static Column NOW = new Column("now()", Types.TIMESTAMP);
	public final static Column CURRENT_TIME = new Column("current_time", Types.TIME);

	protected Connection connection;
	protected List<Column> columns = new ArrayList<Column>();
	protected Table table;
	protected Query fromQuery;
	protected List<Relation> relations = new ArrayList<Relation>();
	protected Map<Column, Object> values = new LinkedHashMap<Column, Object>();
	protected List<Restriction> restrictions = new ArrayList<Restriction>();
	protected List<Column> groups = new ArrayList<Column>();
	protected Restriction having;
	protected Map<Column, Order> orders = new LinkedHashMap<Column, Order>();
	protected Integer limit;
	protected Integer offset;
	protected List<Entry<CombineOperator, Query>> combineQueries = new ArrayList<Map.Entry<CombineOperator,Query>>();
	protected String alias;
	
	public Query() {
		
	}
	
	public Query(Connection connection) {
		this.connection = connection;
	}
	
	private static void setObject(PreparedStatement statement, int index, int type, Object value) throws SQLException {
		
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
		
		}else if(type == Types.ARRAY){
			
			if(value != null) {
				statement.setArray(index, (Array)value);
			}else {
				statement.setNull(index, Types.ARRAY);
			}
		}
	}
	
	private void clean() {
		
		columns.clear();
		
		table = null;
		
		fromQuery = null;
		
		relations.clear();
		
		values.clear();
		
		restrictions.clear();
		
		groups.clear();
		
		having = null;
		
		orders.clear();
		
		limit = null;
		
		offset = null;
		
		combineQueries.clear();
		
		alias = null;
	}
	
	protected static Object getValue(Integer type, String value) {
		
		if(value == null || "".equals(value.trim())) {
			
			return null;
			
		}else if(type == Types.VARCHAR){
			
			return value.trim();
			
		}else if(type == Types.CHAR){
			
			return value.trim().charAt(0);
			
		}else if(type == Types.SMALLINT){
			
			return Short.parseShort(value.trim());
			
		}else if(type == Types.INTEGER){
			
			return Integer.parseInt(value.trim());
			
		}else if(type == Types.BIGINT){
			
			return Long.parseLong(value.trim());
			
		}else if(type == Types.FLOAT){
			
			return Float.parseFloat(value.trim());
			
		}else if(type == Types.DOUBLE){
			
			return Double.parseDouble(value.trim());
			
		}else if(type == Types.DECIMAL){
			
			return new BigDecimal(value.trim());
			
		}else if(type == Types.DATE){
			
			try {
				return new Date(new SimpleDateFormat("dd-MM-yyyy").parse(value.trim()).getTime());
			} catch (Exception e) {
				return null;
			}

  		}else if(type == Types.TIMESTAMP){
			
 			try {
				return new Timestamp(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(value.trim()).getTime());
			} catch (Exception e) {
				return null;
			}
 			
		}else if(type == Types.TIME){
			
			return Time.valueOf(value.trim());
 			
		}else if(type == Types.BOOLEAN){
			
			return Boolean.parseBoolean(value.trim());
			
		}else {
			
			return value;
		}
	}
	
	protected static Object[] getValues(Integer type, String[] values) {
		
		Object[] newValues = new Object[values.length];
		
		for(int i=0; i<values.length; i++) {
			
			newValues[i] = Query.getValue(type, values[i]);
		}
		
		return newValues;
	}
	
	protected static Object getValue(Integer type, java.util.Date value) {
		
		if(value == null) {
			
			return null;
			
		}else if(type == Types.DATE){
			
			return new Date(value.getTime());

  		}else if(type == Types.TIMESTAMP){
			
 			return new Timestamp(value.getTime());
 			
		}else if(type == Types.TIME){
			
			return new Time(value.getTime());
 			
		}else {
			
			return value;
		}
	}
	
	protected static Object[] getValues(Integer type, java.util.Date[] values) {
		
		Object[] newValues = new Object[values.length];
		
		for(int i=0; i<values.length; i++) {
			
			newValues[i] = Query.getValue(type, values[i]);
		}
		
		return newValues;
	}
	
	public Query insertInto(Table table) {

		this.table = table;
		
		return this;
	}
	
	public Query insertInto(Table table, Column... column) {

		this.table = table;
		
		columns.addAll(Arrays.asList(column));
		
		return this;
	}
	
	public Query values(Column column, Column columnValue) {
		
		values.put(column, columnValue);
		
		return this;
	}
	
	public Query values(Column column, String value) {
		
		if(value != null 
				&& !"".equals(value.trim()) 
				&& !"0".equals(value.trim())
				&& !"0.0".equals(value.trim())) {
			
			values.put(column, getValue(column.type, value));
		}
		
		return this;
	}
	
	public Query valuesNull(Column column, String value) {
		
		if(value != null 
				&& !"".equals(value.trim()) 
				&& !"0".equals(value.trim())
				&& !"0.0".equals(value.trim())) {
			
			values.put(column, getValue(column.type, value));
			
		}else {
			
			values.put(column, null);
		}
		
		return this;
	}
	
	public Query valuesZero(Column column, String value) {
		
		if(value != null 
				&& !"".equals(value.trim()) 
				&& !"0".equals(value.trim())
				&& !"0.0".equals(value.trim())) {
			
			values.put(column, getValue(column.type, value));
			
		}else {
			
			values.put(column, 0);
		}
		
		return this;
	}
	
	public Query valuesExact(Column column, String value) {
		
		values.put(column, getValue(column.type, value));
		
		return this;
	}
	
	public Query values(Column column, Character value) {
		
		if(value != null) {
			
			values.put(column, value);
		}
		
		return this;
	}
	
	public Query valuesNull(Column column, Character value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query values(Column column, Number value) {
		
		if(value != null
				&& !"0".equals(value.toString())
				&& !"0.0".equals(value.toString())) {
			
			values.put(column, value);
		}
		
		return this;
	}
	
	public Query valuesNull(Column column, Number value) {
		
		if(value != null
				&& !"0".equals(value.toString())
				&& !"0.0".equals(value.toString())) {
			
			values.put(column, value);
			
		}else {
			
			values.put(column, null);
		}
		
		return this;
	}
	
	public Query valuesZero(Column column, Number value) {
		
		if(value != null) {
			
			values.put(column, value);
			
		}else {
			
			values.put(column, 0);
		}
		
		return this;
	}
	
	public Query valuesExact(Column column, Number value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query values(Column column, java.util.Date value) {
		
		if(value != null) {
			
			values.put(column, getValue(column.type, value));
		}
		
		return this;
	}
	
	public Query valuesNull(Column column, java.util.Date value) {
		
		values.put(column, getValue(column.type, value));
		
		return this;
	}
	
	public Query values(Column column, Date value) {
		
		if(value != null) {
			
			values.put(column, value);
		}
		
		return this;
	}
	
	public Query valuesNull(Column column, Date value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query values(Column column, Timestamp value) {
		
		if(value != null) {
			
			values.put(column, value);
		}
		
		return this;
	}
	
	public Query valuesNull(Column column, Timestamp value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query values(Column column, Time value) {
		
		if(value != null) {
			
			values.put(column, value);
		}
		
		return this;
	}
	
	public Query valuesNull(Column column, Time value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query values(Column column, Boolean value) {
		
		if(value != null) {
			
			values.put(column, value);
		}
		
		return this;
	}
	
	public Query valuesNull(Column column, Boolean value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query values(Column column, List<String> value) {
		
		if(value != null && !value.isEmpty()) {
			
			try {
				
				values.put(column, connection.createArrayOf("varchar", value.toArray()));
			
			} catch (SQLException e) {
			}
		}
		
		return this;
	}

	public Query valuesNull(Column column, List<String> value) {
		
		if(value != null && !value.isEmpty()) {
			
			try {
				
				values.put(column, connection.createArrayOf("varchar", value.toArray()));
			
			} catch (SQLException e) {
			}
			
		}else {
			
			values.put(column, null);
		}
		
		return this;
	}
	
	public Query values(Column column, String... value) {
		
		try {
			
			values.put(column, connection.createArrayOf("varchar", value));
		
		} catch (SQLException e) {
		}
		
		return this;
	}
	
	public int executeInsert() throws SQLException {
		
		int id = 0;
		
		StringBuilder builder = new StringBuilder();
		
		List<Entry<Integer, Object>> parameters = new ArrayList<Entry<Integer, Object>>();
		
		StringBuilder columnList = new StringBuilder();
		
		if(!values.isEmpty()) {
			
			int index = 0;
			
			StringBuilder valueList = new StringBuilder();
			
			for(Map.Entry<Column, Object> entry : values.entrySet()) {
				
				Column column = entry.getKey();
				Object value = entry.getValue();
				
				if(index != 0){
					
					columnList.append(", ");
					valueList.append(", ");
				}
				
				columnList.append(column);
				
				if(value instanceof Column) {
					
					Column columnValue = (Column) value;
					
					valueList.append(columnValue);
					
					parameters.addAll(columnValue.parameters);
					
				}else {
					
					valueList.append("?");
					
					parameters.add(new SimpleEntry<Integer, Object>(column.type, value));
				}
				
				index++;
			}
			
			builder.append("insert into ");
			builder.append(table);
			builder.append(" (");
			builder.append(columnList);
			builder.append(") values (");
			builder.append(valueList);
			builder.append(")");
			
		}else if(!columns.isEmpty() && fromQuery != null) {
			
			int index = 0;
			
			for(Column column : columns) {
				
				if(index != 0){
					
					columnList.append(", ");
				}
				
				columnList.append(column);
				
				index++;
			}

			builder.append("insert into ");
			builder.append(table);
			builder.append(" (");
			builder.append(columnList);
			builder.append(") ");
			builder.append(fromQuery.getSelectQuery(parameters));
		}
		
		System.out.println("SQL Query :: " + builder);
		
		try(PreparedStatement statement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS)){
			
			int index = 1;
			
			for(Entry<Integer, Object> parameter : parameters) {
				
				Integer type = parameter.getKey();
				Object value = parameter.getValue();
				
				setObject(statement, index, type, value);
				
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
		
		clean();
		
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
	
	public Query search(Restriction restriction) {

		if(!restrictions.isEmpty()) {
			
			restriction.operator = Operator.AND;
		}
		
		if(restriction.value != null 
				&& !"".equals(restriction.value.toString().trim())
				&& !"0".equals(restriction.value.toString().trim())
				&& !"0.0".equals(restriction.value.toString().trim())) {
			
			restrictions.add(restriction);
		}
		
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
	
	public static Restriction exists(Query query) {
		
		return new Restriction(Criteria.EXISTS, query);
	}
	
	public static Restriction notExists(Query query) {
		
		return new Restriction(Criteria.NOT_EXISTS, query);
	}
	
	private static StringBuilder buildRestriction(Restriction restriction, List<Entry<Integer, Object>> parameters){
		
		StringBuilder builder = new StringBuilder();
		
		if(restriction.operator != null){
			
			builder.append(" ");
			builder.append(restriction.operator);
		}

		builder.append(" ");
		
		if(!restriction.restrictions.isEmpty()){
			
			builder.append("(");
		}
		
		if(restriction.criteria == Criteria.EQUAL 
				|| restriction.criteria == Criteria.NOT_EQUAL
				|| restriction.criteria == Criteria.GREATER 
				|| restriction.criteria == Criteria.GREATER_EQUAL
				|| restriction.criteria == Criteria.LESS
				|| restriction.criteria == Criteria.LESS_EQUAL){

			builder.append(restriction.column);
			builder.append(" ");
			builder.append(restriction.criteria);
			builder.append(" ");
			builder.append("?");
			
			parameters.addAll(restriction.column.parameters);
			parameters.add(new SimpleEntry<Integer, Object>(restriction.column.type, restriction.value));
			
		}else if(restriction.criteria == Criteria.LIKE){
			
			builder.append(restriction.column);
			builder.append(" ");
			builder.append(restriction.criteria);
			builder.append(" ");
			builder.append("'%' || ? || '%'");
			
			parameters.addAll(restriction.column.parameters);
			parameters.add(new SimpleEntry<Integer, Object>(restriction.column.type, restriction.value));
		
		}else if(restriction.criteria == Criteria.EQUAL_COLUMN 
				|| restriction.criteria == Criteria.NOT_EQUAL_COLUMN 
				|| restriction.criteria == Criteria.GREATER_COLUMN 
				|| restriction.criteria == Criteria.GREATER_EQUAL_COLUMN 
				|| restriction.criteria == Criteria.LESS_COLUMN 
				|| restriction.criteria == Criteria.LESS_EQUAL_COLUMN){

			builder.append(restriction.column);
			builder.append(" ");
			builder.append(restriction.criteria);
			builder.append(" ");
			builder.append(restriction.column2);
			
			parameters.addAll(restriction.column.parameters);
			parameters.addAll(restriction.column2.parameters);
			
		}else if(restriction.criteria == Criteria.IS_NULL 
				|| restriction.criteria == Criteria.IS_NOT_NULL){
			
			builder.append(restriction.column);
			builder.append(" ");
			builder.append(restriction.criteria);
			
			parameters.addAll(restriction.column.parameters);
			
		}else if(restriction.criteria == Criteria.IN
					|| restriction.criteria == Criteria.NOT_IN){
			
			parameters.addAll(restriction.column.parameters);
			
			builder.append(restriction.column);
			builder.append(" ");
			builder.append(restriction.criteria);
			builder.append(" (");
			
			int index = 0;
			
			for(Object value : restriction.values){
				
				if(index != 0){
					
					builder.append(", ");
				}
				
				builder.append("?");
				
				parameters.add(new SimpleEntry<Integer, Object>(restriction.column.type, value));
				
				index++;
			}
			
			builder.append(")");
		
		}else if(restriction.criteria == Criteria.BETWEEN){
			
			builder.append(restriction.column);
			builder.append(" ");
			builder.append(restriction.criteria);
			builder.append(" ? ");
			builder.append(Operator.AND);
			builder.append(" ?");
			
			parameters.addAll(restriction.column.parameters);
			parameters.add(new SimpleEntry<Integer, Object>(restriction.column.type, restriction.value));
			parameters.add(new SimpleEntry<Integer, Object>(restriction.column.type, restriction.to));
		
		}else if(restriction.criteria == Criteria.BETWEEN_COLUMNS){
			
			builder.append(restriction.column);
			builder.append(" ");
			builder.append(restriction.criteria);
			builder.append(" ");
			builder.append(restriction.column2);
			builder.append(" ");
			builder.append(Operator.AND);
			builder.append(" ");
			builder.append(restriction.column3);
			
			parameters.addAll(restriction.column.parameters);
			parameters.addAll(restriction.column2.parameters);
			parameters.addAll(restriction.column3.parameters);
		
		}else if(restriction.criteria == Criteria.IN_QUERY
					|| restriction.criteria == Criteria.NOT_IN_QUERY){
			
			parameters.addAll(restriction.column.parameters);
			
			builder.append(restriction.column);
			builder.append(" ");
			builder.append(restriction.criteria);
			builder.append(" (");
			builder.append(restriction.query.getSelectQuery(parameters));
			builder.append(")");
		
		}else if(restriction.criteria == Criteria.EXISTS
				|| restriction.criteria == Criteria.NOT_EXISTS){
			
			builder.append(restriction.criteria);
			builder.append(" (");
			builder.append(restriction.query.getSelectQuery(parameters));
			builder.append(")");
		}
		
		if(!restriction.restrictions.isEmpty()){
			
			for(Restriction nestedRestriction : restriction.restrictions) {
				
				builder.append(buildRestriction(nestedRestriction, parameters));
			}

			builder.append(")");
		}
		
		return builder;
	}
	
	public int executeDelete() throws SQLException {
		
		int count = 0;
		
		List<Entry<Integer, Object>> parameters = new ArrayList<Entry<Integer, Object>>();
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("delete from ");
		builder.append(table);
		
		if(!restrictions.isEmpty()) {
			
			builder.append(" where");
			
			for(Restriction restriction : restrictions) {
				
				builder.append(buildRestriction(restriction, parameters));
			}
		}
		
		System.out.println("SQL Query :: " + builder);
		
		try(PreparedStatement statement = connection.prepareStatement(builder.toString())){
			
			int index = 1;
			
			for(Entry<Integer, Object> parameter : parameters) {
				
				Integer type = parameter.getKey();
				Object value = parameter.getValue();
				
				setObject(statement, index, type, value);
				
				index++;
			}
			
			count = statement.executeUpdate();
		}
		
		System.out.println("Affected Rows :: " + count);
		
		clean();

		return count;
	}
	
	public Query update(Table table) {
		
		this.table = table;
		
		return this;
	}
	
	public Query set(Column column, Column columnValue) {
		
		values.put(column, columnValue);
		
		return this;
	}
	
	public Query set(Column column, String value) {
		
		if(value != null 
				&& !"".equals(value.trim()) 
				&& !"0".equals(value.trim())
				&& !"0.0".equals(value.trim())) {
			
			values.put(column, getValue(column.type, value));
		}
		
		return this;
	}
	
	public Query setNull(Column column, String value) {
		
		if(value != null 
				&& !"".equals(value.trim()) 
				&& !"0".equals(value.trim())
				&& !"0.0".equals(value.trim())) {
			
			values.put(column, getValue(column.type, value));
			
		}else {
			
			values.put(column, null);
		}
		
		return this;
	}
	
	public Query setZero(Column column, String value) {
		
		if(value != null 
				&& !"".equals(value.trim()) 
				&& !"0".equals(value.trim())
				&& !"0.0".equals(value.trim())) {
			
			values.put(column, getValue(column.type, value));
			
		}else {
			
			values.put(column, 0);
		}
		
		return this;
	}
	
	public Query setExact(Column column, String value) {
		
		values.put(column, getValue(column.type, value));
		
		return this;
	}
	
	public Query set(Column column, Character value) {
		
		if(value != null) {
			
			values.put(column, value);
		}
		
		return this;
	}
	
	public Query setNull(Column column, Character value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query set(Column column, Number value) {
		
		if(value != null
				&& !"0".equals(value.toString())
				&& !"0.0".equals(value.toString())) {
			
			values.put(column, value);
		}
		
		return this;
	}
	
	public Query setNull(Column column, Number value) {
		
		if(value != null
				&& !"0".equals(value.toString())
				&& !"0.0".equals(value.toString())) {
			
			values.put(column, value);
			
		}else {
			
			values.put(column, null);
		}
		
		return this;
	}
	
	public Query setZero(Column column, Number value) {
		
		if(value != null) {
			
			values.put(column, value);
			
		}else {
			
			values.put(column, 0);
		}
		
		return this;
	}
	
	public Query setExact(Column column, Number value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query set(Column column, java.util.Date value) {
		
		if(value != null) {
			
			values.put(column, getValue(column.type, value));
		}
		
		return this;
	}
	
	public Query setNull(Column column, java.util.Date value) {
		
		values.put(column, getValue(column.type, value));
		
		return this;
	}
	
	public Query set(Column column, Date value) {
		
		if(value != null) {
			
			values.put(column, value);
		}
		
		return this;
	}
	
	public Query setNull(Column column, Date value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query set(Column column, Timestamp value) {
		
		if(value != null) {
			
			values.put(column, value);
		}
		
		return this;
	}
	
	public Query setNull(Column column, Timestamp value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query set(Column column, Time value) {
		
		if(value != null) {
			
			values.put(column, value);
		}
		
		return this;
	}
	
	public Query setNull(Column column, Time value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query set(Column column, Boolean value) {
		
		if(value != null) {
			
			values.put(column, value);
		}
		
		return this;
	}
	
	public Query setNull(Column column, Boolean value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query set(Column column, List<String> value) {
		
		if(value != null && !value.isEmpty()) {
			
			try {
				
				values.put(column, connection.createArrayOf("varchar", value.toArray()));
			
			} catch (SQLException e) {
			}
		}
		
		return this;
	}

	public Query setNull(Column column, List<String> value) {
		
		if(value != null && !value.isEmpty()) {
			
			try {
				
				values.put(column, connection.createArrayOf("varchar", value.toArray()));
			
			} catch (SQLException e) {
			}
			
		}else {
			
			values.put(column, null);
		}
		
		return this;
	}
	
	public Query set(Column column, String... value) {
		
		try {
			
			values.put(column, connection.createArrayOf("varchar", value));
		
		} catch (SQLException e) {
		}
		
		return this;
	}
	
	public int executeUpdate() throws SQLException {
		
		int count = 0;
		
		List<Entry<Integer, Object>> parameters = new ArrayList<Entry<Integer, Object>>();
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("update ");
		builder.append(table);
		builder.append(" set");
		
		int index = 0;
		
		for(Map.Entry<Column, Object> entry : values.entrySet()){
			
			Column column = entry.getKey();
			Object value = entry.getValue();
			
			if(index != 0) {
				
				builder.append(",");
			}
			
			builder.append(" ");
			builder.append(column);
			builder.append(" = ");
			
			if(value instanceof Column) {
				
				Column columnValue = (Column) value;
				
				builder.append(columnValue);
				
				parameters.addAll(columnValue.parameters);
				
			}else {
				
				builder.append("?");
				
				parameters.add(new SimpleEntry<Integer, Object>(column.type, value));
			}
			
			index++;
		}
		
		if(!restrictions.isEmpty()) {
			
			builder.append(" where");
			
			for(Restriction restriction : restrictions) {
				
				builder.append(buildRestriction(restriction, parameters));
			}
		}
		
		System.out.println("SQL Query :: " + builder);
		
		try(PreparedStatement statement = connection.prepareStatement(builder.toString())){
			
			index = 1;
			
			for(Entry<Integer, Object> parameter : parameters) {
				
				Integer type = parameter.getKey();
				Object value = parameter.getValue();
				
				setObject(statement, index, type, value);
				
				index++;
			}
			
			count = statement.executeUpdate();
		}
		
		System.out.println("Affected Rows :: " + count);
		
		clean();
		
		return count;
	}
	
	public Query select(Column... columns) {

		this.columns.addAll(Arrays.asList(columns));
		
		return this;
	}
	
	public static Column count(Column column){
		
		return column.copyWithFunction(Function.COUNT);
	}
	
	public static Column distinct(Column column){
		
		return column.copyWithFunction(Function.DISTINCT);
	}
	
	public static Column sum(Column column){
		
		return column.copyWithFunction(Function.SUM);
	}
	
	public static Column average(Column column){
		
		return column.copyWithFunction(Function.AVERAGE);
	}
	
	public static Column min(Column column){
		
		return column.copyWithFunction(Function.MIN);
	}
	
	public static Column max(Column column){
		
		return column.copyWithFunction(Function.MAX);
	}
	
	public static Column coalesce(Column column1, Column column2) {
		
		return column1.copyWithFunction(Function.COALESCE, column2);
	}
	
	public static Column coalesce(Column column, String value) {
		
		return column.copyWithFunction(Function.COALESCE, value);
	}
	
	public static Column coalesce(Column column, Character value) {
		
		return column.copyWithFunction(Function.COALESCE, value);
	}
	
	public static Column coalesce(Column column, Number value) {
		
		return column.copyWithFunction(Function.COALESCE, value);
	}
	
	public static Column coalesce(Column column, Date value) {
		
		return column.copyWithFunction(Function.COALESCE, value);
	}
	
	public static Column coalesce(Column column, Timestamp value) {
		
		return column.copyWithFunction(Function.COALESCE, value);
	}
	
	public static Column coalesce(Column column, Time value) {
		
		return column.copyWithFunction(Function.COALESCE, value);
	}
	
	public static Column coalesce(Column column, Boolean value) {
		
		return column.copyWithFunction(Function.COALESCE, value);
	}
	
	public static Column row_number() {
		
		return new Column("row_number() over()", Types.INTEGER, "row_num");
	}
	
	public static RowNumber partiton_by(Column... columns) {
		
		return new RowNumber(Arrays.asList(columns));
	}
	
	public static RowNumber order_by(Column... columns) {

		Map<Column, Order> orders = new LinkedHashMap<Column, Order>();
		
		for(Column column : columns){
			
			orders.put(column, Order.ASC);
		}
		
		return new RowNumber(orders);
	}
	
	public static RowNumber order_by(Entry<Column, Order>... entries) {

		Map<Column, Order> orders = new LinkedHashMap<Column, Order>();
		
		for(Entry<Column, Order> entry : entries){
			
			orders.put(entry.getKey(), entry.getValue());
		}
		
		return new RowNumber(orders);
	}
	
	public static RowNumber order_by_desc(Column... columns) {

		Map<Column, Order> orders = new LinkedHashMap<Column, Order>();
		
		for(Column column : columns){
			
			orders.put(column, Order.DESC);
		}
		
		return new RowNumber(orders);
	}
	
	private static int getObjectType(Object value) {
		
		if(value instanceof String) {
			return Types.VARCHAR;
		}else if(value instanceof Character) {
			return Types.CHAR;
		}else if(value instanceof Short) {
			return Types.SMALLINT;
		}else if(value instanceof Integer) {
			return Types.INTEGER;
		}else if(value instanceof Long) {
			return Types.BIGINT;
		}else if(value instanceof Float) {
			return Types.FLOAT;
		}else if(value instanceof Double) {
			return Types.DOUBLE;
		}else if(value instanceof BigDecimal) {
			return Types.DECIMAL;
		}else if(value instanceof Date) {
			return Types.DATE;
		}else if(value instanceof Timestamp) {
			return Types.TIMESTAMP;
		}else if(value instanceof Time) {
			return Types.TIME;
		}else if(value instanceof Boolean) {
			return Types.BOOLEAN;
		}else {
			return Types.VARCHAR;
		}
	}

	public static Column case_(Case... cases) {
		
		int type = -1;
		
		List<Entry<Integer, Object>> parameters = new ArrayList<Entry<Integer, Object>>();
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("case");
		
		for(Case case_ : cases) {
			
			if(case_.restriction != null) {
				
				builder.append(" when");
				builder.append(buildRestriction(case_.restriction, parameters));
				builder.append(" then ");
				
				if(case_.column != null) {
					
					builder.append(case_.column);
					
					type = case_.column.type;
					
				}else if(case_.value != null) {
					
					builder.append("?");
					
					type = getObjectType(case_.value);
					
					parameters.add(new SimpleEntry<Integer, Object>(type, case_.value));
				}
				
			}else {
				
				builder.append(" else ");
				
				if(case_.column != null) {
					
					builder.append(case_.column);
					
					type = case_.column.type;
					
				}else if(case_.value != null) {
					
					builder.append("?");
					
					type = getObjectType(case_.value);
					
					parameters.add(new SimpleEntry<Integer, Object>(type, case_.value));
				}
			}
		}
		
		builder.append(" end");
		
		return new Column(builder.toString(), type, parameters);
	}
	
	public static Column case_(Column column, Case... cases) {
		
		int type = -1;
		
		List<Entry<Integer, Object>> parameters = new ArrayList<Entry<Integer, Object>>();
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("case ");
		builder.append(column);
		
		for(Case case_ : cases) {
			
			if(case_.columnValue != null) {
				
				builder.append(" when ? then ");
				
				parameters.add(new SimpleEntry<Integer, Object>(getObjectType(case_.columnValue), case_.columnValue));
				
				if(case_.column != null) {
					
					builder.append(case_.column);
					
					type = case_.column.type;
					
				}else if(case_.value != null) {
					
					builder.append("?");
					
					type = getObjectType(case_.value);
					
					parameters.add(new SimpleEntry<Integer, Object>(type, case_.value));
				}
				
			}else {
				
				builder.append(" else ");
				
				if(case_.column != null) {
					
					builder.append(case_.column);
					
					type = case_.column.type;
					
				}else if(case_.value != null) {
					
					builder.append("?");
					
					type = getObjectType(case_.value);
					
					parameters.add(new SimpleEntry<Integer, Object>(type, case_.value));
				}
			}
		}
		
		builder.append(" end");
		
		return new Column(builder.toString(), type, parameters);
	}
	
	public Case when(Restriction restriction) {
		
		return new Case(restriction);
	}
	
	public Case when(String columnValue) {
		
		Case case_ = new Case();
		case_.columnValue = columnValue;
		return case_;
	}
	
	public Case when(Character columnValue) {
		
		Case case_ = new Case();
		case_.columnValue = columnValue;
		return case_;
	}
	
	public Case when(Number columnValue) {
		
		Case case_ = new Case();
		case_.columnValue = columnValue;
		return case_;
	}
	
	public Case when(Date columnValue) {
		
		Case case_ = new Case();
		case_.columnValue = columnValue;
		return case_;
	}
	
	public Case when(Timestamp columnValue) {
		
		Case case_ = new Case();
		case_.columnValue = columnValue;
		return case_;
	}
	
	public Case when(Time columnValue) {
		
		Case case_ = new Case();
		case_.columnValue = columnValue;
		return case_;
	}
	
	public Case when(Boolean columnValue) {
		
		Case case_ = new Case();
		case_.columnValue = columnValue;
		return case_;
	}
	
	public Case else_(Column column) {
		
		return new Case(column);
	}
	
	public Case else_(String value) {
		
		return new Case(value);
	}
	
	public Case else_(Character value) {
		
		return new Case(value);
	}
	
	public Case else_(Number value) {
		
		return new Case(value);
	}
	
	public Case else_(Date value) {
		
		return new Case(value);
	}
	
	public Case else_(Timestamp value) {
		
		return new Case(value);
	}
	
	public Case else_(Time value) {
		
		return new Case(value);
	}
	
	public Case else_(Boolean value) {
		
		return new Case(value);
	}
	
	public Query from(Table table) {

		this.table = table;
		
		return this;
	}
	
	public Query from(Query fromQuery) {

		this.fromQuery = fromQuery;
		
		return this;
	}
	
	public Relation join(Table table){
		
		return innerJoin(table);
	}
	
	public Relation join(Query query){
		
		return innerJoin(query);
	}
	
	public Relation innerJoin(Table table){
		
		Relation relation = new Relation(Join.INNER, table, this);
		
		relations.add(relation);
		
		return relation;
	}
	
	public Relation innerJoin(Query query){
		
		Relation relation = new Relation(Join.INNER, query, this);
		
		relations.add(relation);
		
		return relation;
	}
	
	public Relation rightJoin(Table table){
		
		Relation relation = new Relation(Join.RIGHT, table, this);
				
		relations.add(relation);
		
		return relation;
	}
	
	public Relation rightJoin(Query query){
		
		Relation relation = new Relation(Join.RIGHT, query, this);
		
		relations.add(relation);
		
		return relation;
	}
	
	public Relation leftJoin(Table table){
		
		Relation relation = new Relation(Join.LEFT, table, this);
		
		relations.add(relation);
		
		return relation;
	}
	
	public Relation leftJoin(Query query){
		
		Relation relation = new Relation(Join.LEFT, query, this);
		
		relations.add(relation);
		
		return relation;
	}
	
	public Relation fullJoin(Table table){
		
		Relation relation = new Relation(Join.FULL, table, this);
		
		relations.add(relation);
		
		return relation;
	}
	
	public Relation fullJoin(Query query){
		
		Relation relation = new Relation(Join.FULL, query, this);
		
		relations.add(relation);
		
		return relation;
	}
	
	public Query as(String alias) {

		if(fromQuery != null) {
			
			fromQuery.alias = alias;
		}
		
		return this;
	}
	
	public Query groupBy(Column... column) {

		groups.addAll(Arrays.asList(column));
		
		return this;
	}
	
	public Query having(Restriction having) {
		
		this.having = having;
		
		return this;
	}
	
	public Query orderBy(Column... columns) {

		for(Column column : columns){
			
			orders.put(column, Order.ASC);
		}
		
		return this;
	}
	
	public Query orderBy(Entry<Column, Order>... entries) {

		for(Entry<Column, Order> entry : entries){
			
			orders.put(entry.getKey(), entry.getValue());
		}
		
		return this;
	}
	
	public Query orderByDesc(Column... columns) {

		for(Column column : columns){
			
			orders.put(column, Order.DESC);
		}
		
		return this;
	}
	
	public Query union(Query query) {
		
		combineQueries.add(new SimpleEntry<CombineOperator, Query>(CombineOperator.UNION, query));
		
		return this;
	}
	
	public Query unionAll(Query query) {
		
		combineQueries.add(new SimpleEntry<CombineOperator, Query>(CombineOperator.UNION_ALL, query));
		
		return this;
	}
	
	public Query intersect(Query query) {
		
		combineQueries.add(new SimpleEntry<CombineOperator, Query>(CombineOperator.INTERSECT, query));
		
		return this;
	}
	
	public Query intersectAll(Query query) {
		
		combineQueries.add(new SimpleEntry<CombineOperator, Query>(CombineOperator.INTERSECT_ALL, query));
		
		return this;
	}
	
	public Query except(Query query) {
		
		combineQueries.add(new SimpleEntry<CombineOperator, Query>(CombineOperator.EXCEPT, query));
		
		return this;
	}
	
	public Query exceptAll(Query query) {
		
		combineQueries.add(new SimpleEntry<CombineOperator, Query>(CombineOperator.EXPECT_ALL, query));
		
		return this;
	}
	
	public Query limit(Integer limit) {
		
		this.limit = limit;
		
		return this;
	}

	public Query offset(Integer offset) {
		
		this.offset = offset;
		
		return this;
	}
	
	private StringBuilder getSelectQuery(List<Entry<Integer, Object>> parameters) {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("select ");
		
		int index = 0;
		
		if(!columns.isEmpty()) {
			
			for(Column column : columns) {
				
				if(index != 0) {
					
					builder.append(", ");
				}
				
				builder.append(column);
				
				if(column.alias != null) {
					
					builder.append(" as ");
					builder.append(column.alias);
				}
				
				index++;
				
				parameters.addAll(column.parameters);
			}
			
		}else {
			
			builder.append("*");
		}
		
		builder.append(" from ");
		
		if(table != null) {
			
			builder.append(table);
			
			if(table.alias != null) {
				
				builder.append(" as ");
				builder.append(table.alias);
			}
			
		}else if(fromQuery != null) {
			
			builder.append("(");
			builder.append(fromQuery.getSelectQuery(parameters));
			builder.append(")");
			
			if(fromQuery.alias != null) {
				
				builder.append(" as ");
				builder.append(fromQuery.alias);
			}
		}
		
		if(!relations.isEmpty()) {
			
			for(Relation relation : relations) {
				
				builder.append(" ");
				builder.append(relation.join);
				builder.append(" ");
				
				if(relation.table != null) {
					
					builder.append(relation.table);
					
					if(relation.table.alias != null) {
						
						builder.append(" as ");
						builder.append(relation.table.alias);
					}
					
				}else if(relation.subQuery != null){
					
					builder.append("(");
					builder.append(relation.subQuery.getSelectQuery(parameters));
					builder.append(")");
					
					if(relation.subQuery.alias != null) {
						
						builder.append(" as ");
						builder.append(relation.subQuery.alias);
					}
				}
				
				builder.append(" on");
				
				builder.append(buildRestriction(relation.restriction, parameters));
			}
		}
		
		if(!restrictions.isEmpty()) {
			
			builder.append(" where");
			
			for(Restriction restriction : restrictions) {
				
				builder.append(buildRestriction(restriction, parameters));
			}
		}
		
		if (!groups.isEmpty()) {

			builder.append(" group by ");

			index = 0;
			
			for(Column column : groups){
				
				if(index != 0){
					
					builder.append(", ");
				}
				
				builder.append(column);
				
				index++;
			}
		}
		
		if (having != null) {

			builder.append(" having");

			builder.append(buildRestriction(having, parameters));
		}
		
		if (!orders.isEmpty()) {

			builder.append(" order by ");

			index = 0;
			
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
		
		if(limit != null && limit != 0) {
			
			builder.append(" limit ?");

			parameters.add(new SimpleEntry<Integer, Object>(Types.INTEGER, limit));
		}
		
		if(offset != null && offset != 0) {
			
			builder.append(" offset ?");
			
			parameters.add(new SimpleEntry<Integer, Object>(Types.INTEGER, offset));
		}
		
		if(!combineQueries.isEmpty()) {
			
			for(Entry<CombineOperator, Query> combineQuery : combineQueries) {
				
				CombineOperator operator = combineQuery.getKey();
				Query query = combineQuery.getValue();
				
				builder.append(" ");
				builder.append(operator);
				builder.append(" ");
				builder.append(query.getSelectQuery(parameters));
			}
		}
		
		return builder;
	}
	
	public ResultSet executeSelect() throws SQLException {
		
		List<Entry<Integer, Object>> parameters = new ArrayList<Entry<Integer, Object>>();
		
		StringBuilder builder = getSelectQuery(parameters);
		
		System.out.println("SQL Query :: " + builder);
		
		PreparedStatement statement = connection.prepareStatement(builder.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		int index = 1;
		
		for(Entry<Integer, Object> parameter : parameters) {
			
			Integer type = parameter.getKey();
			Object value = parameter.getValue();
			
			setObject(statement, index, type, value);
			
			index++;
		}

		ResultSet result = statement.executeQuery();
		
		int count = result.last() ? result.getRow() : 0;
		
		System.out.println("Matched Rows :: " + count);
		
		result.beforeFirst();
		
		return result;
	}
	
	private String getString(ResultSet result, Column column) throws SQLException {
		
		String value = null;
		
		if(column.type == Types.VARCHAR) {
			
			value = result.getString(column.nameInResultSet);
			
		}else if(column.type == Types.CHAR) {
			
			value = result.getString(column.nameInResultSet);
			
		}else if(column.type == Types.SMALLINT) {
			
			Short number = getShort(result, column);
			
			value = number != null ? Short.toString(number) : null;
			
		}else if(column.type == Types.INTEGER) {
			
			Integer number = getInt(result, column);
			
			value = number != null ? Integer.toString(number) : null;
			
		}else if(column.type == Types.BIGINT) {
			
			Long number = getLong(result, column);
			
			value = number != null ? Long.toString(number) : null;
			
		}else if(column.type == Types.FLOAT) {
			
			Float number = getFloat(result, column);
			
			value = number != null ? Float.toString(number) : null;
			
		}else if(column.type == Types.DOUBLE) {
			
			Double number = getDouble(result, column);
			
			value = number != null ? Double.toString(number) : null;
			
		}else if(column.type == Types.DECIMAL) {
			
			BigDecimal number = getDecimal(result, column);
			
			value = number != null ? String.valueOf(number) : null;
			
		}else if(column.type == Types.DATE) {
			
			Date date = getDate(result, column);
			
			value = date != null ? new SimpleDateFormat("dd-MM-yyyy").format(date) : null;
			
		}else if(column.type == Types.TIMESTAMP) {
			
			Timestamp timestamp = getTimestamp(result, column);
			
			value = timestamp != null ? new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(timestamp) : null;
			
		}else if(column.type == Types.TIME) {
			
			Time time = getTime(result, column);
			
			value = time != null ? String.valueOf(time) : null;
			
		}else if(column.type == Types.BOOLEAN) {
			
			Boolean bool = getBoolean(result, column);
			
			value = bool != null ? Boolean.toString(bool) : null;
		
		}else if(column.type == Types.ARRAY) {
			
			List<String> values = getArray(result, column);
			
			value = values != null ? String.join(",", values) : null;
		}

		return value != null ? value.trim() : null;
	}
	
	private Character getCharacter(ResultSet result, Column column) throws SQLException {
		
		String value = result.getString(column.nameInResultSet);
		
		return value != null ? value.trim().charAt(0) : null;
	}
	
	private Short getShort(ResultSet result, Column column) throws SQLException {
		
		short value = result.getShort(column.nameInResultSet);
		
		return result.wasNull() ? null : value;
	}
	
	private Integer getInt(ResultSet result, Column column) throws SQLException {
		
		int value = result.getInt(column.nameInResultSet);
		
		return result.wasNull() ? null : value;
	}
	
	private Long getLong(ResultSet result, Column column) throws SQLException {
		
		long value = result.getLong(column.nameInResultSet);
		
		return result.wasNull() ? null : value;
	}
	
	private Float getFloat(ResultSet result, Column column) throws SQLException {
		
		float value = result.getFloat(column.nameInResultSet);
		
		return result.wasNull() ? null : value;
	}
	
	private Double getDouble(ResultSet result, Column column) throws SQLException {
		
		double value = result.getDouble(column.nameInResultSet);
		
		return result.wasNull() ? null : value;
	}
	
	private BigDecimal getDecimal(ResultSet result, Column column) throws SQLException {
		
		return result.getBigDecimal(column.nameInResultSet);
	}
	
	private Date getDate(ResultSet result, Column column) throws SQLException {
		
		return result.getDate(column.nameInResultSet);
	}
	
	private Timestamp getTimestamp(ResultSet result, Column column) throws SQLException {
		
		return result.getTimestamp(column.nameInResultSet);
	}
	
	private Time getTime(ResultSet result, Column column) throws SQLException {
		
		return result.getTime(column.nameInResultSet);
	}
	
	private Boolean getBoolean(ResultSet result, Column column) throws SQLException {
		
		boolean value = result.getBoolean(column.nameInResultSet);
		
		return result.wasNull() ? null : value;
	}
	
	private List<String> getArray(ResultSet result, Column column) throws SQLException {
		
		Array value = result.getArray(column.nameInResultSet);
		
		return value != null ? Arrays.asList((String[])value.getArray()) : null;
	}
	
	private Object getObject(ResultSet result, Column column) throws SQLException {
		
		if(column.type == Types.VARCHAR){
			
			return getString(result, column);
			
		}else if(column.type == Types.CHAR){
			
			return getCharacter(result, column);
		
		}else if(column.type == Types.SMALLINT){
			
			return getShort(result, column);
			
		}else if(column.type == Types.INTEGER){
			
			return getInt(result, column);
			
		}else if(column.type == Types.BIGINT){
			
			return getLong(result, column);
			
		}else if(column.type == Types.FLOAT){
			
			return getFloat(result, column);
		
		}else if(column.type == Types.DOUBLE){
			
			return getDouble(result, column);
			
		}else if(column.type == Types.DECIMAL){
			
			return getDecimal(result, column);
		
		}else if(column.type == Types.DATE){
			
			return getDate(result, column);
		
		}else if(column.type == Types.TIMESTAMP){
			
			return getTimestamp(result, column);
		
		}else if(column.type == Types.TIME){
			
			return getTime(result, column);
			
		}else if(column.type == Types.BOOLEAN){
			
			return getBoolean(result, column);
		
		}else if(column.type == Types.ARRAY){
			
			return getArray(result, column);
		
		}else{
			
			return null;
		}
	}
	
	private <T> T getObject(ResultSet result, Column column, Class<T> className) throws SQLException {
		
		if(className == Object.class) {
			
			return className.cast(getObject(result, column));
		
		}else if(className == String.class){
			
			return className.cast(getString(result, column));
			
		}else if(className == Character.class){
			
			return className.cast(getCharacter(result, column));
		
		}else if(className == char.class){
			
			Character value = getCharacter(result, column);
			
			return className.cast(value != null ? value : ' ');
		
		}else if(className == Short.class){
			
			return className.cast(getShort(result, column));
			
		}else if(className == short.class){
			
			Short value = getShort(result, column);
			
			return className.cast(value != null ? value : 0);
		
		}else if(className == Integer.class){
			
			return className.cast(getInt(result, column));
			
		}else if(className == int.class){
			
			Integer value = getInt(result, column);
			
			return className.cast(value != null ? value : 0);
		
		}else if(className == Long.class){
			
			return className.cast(getLong(result, column));
			
		}else if(className == long.class){
			
			Long value = getLong(result, column);
			
			return className.cast(value != null ? value : 0);
		
		}else if(className == Float.class){
			
			return className.cast(getFloat(result, column));
		
		}else if(className == float.class){
			
			Float value = getFloat(result, column);
			
			return className.cast(value != null ? value : 0);
		
		}else if(className == Double.class){
			
			return className.cast(getDouble(result, column));
			
		}else if(className == double.class){
			
			Double value = getDouble(result, column);
			
			return className.cast(value != null ? value : 0);
		
		}else if(className == BigDecimal.class){
			
			return className.cast(getDecimal(result, column));
		
		}else if(className == java.util.Date.class){
			
			return className.cast(getObject(result, column));
		
		}else if(className == Date.class){
			
			return className.cast(getDate(result, column));
		
		}else if(className == Timestamp.class){
			
			return className.cast(getTimestamp(result, column));
		
		}else if(className == Time.class){
			
			return className.cast(getTime(result, column));
			
		}else if(className == Boolean.class){
			
			return className.cast(getBoolean(result, column));
		
		}else if(className == boolean.class){
			
			Boolean value = getBoolean(result, column);
			
			return className.cast(value != null ? value : false);
		
		}else if(className == List.class){
			
			return className.cast(getArray(result, column));
		
		}else if(className == String[].class){
			
			List<String> value = getArray(result, column);
			
			return value != null ? className.cast(value.toArray()) : null;
		
		}else{
			
			return null;
		}
	}
	
	public boolean isExist() throws SQLException {
		
		try(ResultSet result = executeSelect()){
			
			try(Statement statement = result.getStatement()){
				
				return result.next();
			}
		
		}finally {
			
			clean();
		}
	}

	private <T> T getObjectResult(Class<T> className) throws SQLException {
		
		try(ResultSet result = executeSelect()){
			
			try(Statement statement = result.getStatement()){
				
				if(result.next() && columns.size() >= 1) {
					
					Column column = columns.get(0);
					
					return getObject(result, column, className);
				}
			}
			
		}finally {
			
			clean();
		}
		
		return null;
	}
	
	private <T> List<T> getObjectList(Class<T> className) throws SQLException {
		
		List<T> list = new ArrayList<T>();
		
		try(ResultSet result = executeSelect()){
			
			try(Statement statement = result.getStatement()){
				
				while(result.next() && columns.size() >= 1) {
					
					Column column = columns.get(0);
					
					list.add(getObject(result, column, className));
				}
			}
			
		}finally {
			
			clean();
		}
		
		return list;
	}
	
	public String getStringResult() throws SQLException {
		
		return getObjectResult(String.class);
	}
	
	public List<String> getStringList() throws SQLException {
		
		return getObjectList(String.class);
	}
	
	public Character getCharacterResult() throws SQLException {
		
		return getObjectResult(Character.class);
	}
	
	public List<Character> getCharacterList() throws SQLException {
		
		return getObjectList(Character.class);
	}
	
	public Short getShortResult() throws SQLException {
		
		return getObjectResult(Short.class);
	}
	
	public List<Short> getShortList() throws SQLException {
		
		return getObjectList(Short.class);
	}
	
	public Integer getIntegerResult() throws SQLException {
		
		return getObjectResult(Integer.class);
	}
	
	public List<Integer> getIntegerList() throws SQLException {
		
		return getObjectList(Integer.class);
	}
	
	public Long getLongResult() throws SQLException {
		
		return getObjectResult(Long.class);
	}
	
	public List<Long> getLongList() throws SQLException {
		
		return getObjectList(Long.class);
	}
	
	public Float getFloatResult() throws SQLException {
		
		return getObjectResult(Float.class);
	}
	
	public List<Float> getFloatList() throws SQLException {
		
		return getObjectList(Float.class);
	}
	
	public Double getDoubleResult() throws SQLException {
		
		return getObjectResult(Double.class);
	}
	
	public List<Double> getDoubleList() throws SQLException {
		
		return getObjectList(Double.class);
	}
	
	public BigDecimal getDecimalResult() throws SQLException {
		
		return getObjectResult(BigDecimal.class);
	}
	
	public List<BigDecimal> getDecimalList() throws SQLException {
		
		return getObjectList(BigDecimal.class);
	}
	
	public Date getDateResult() throws SQLException {
		
		return getObjectResult(Date.class);
	}
	
	public List<Date> getDateList() throws SQLException {
		
		return getObjectList(Date.class);
	}
	
	public Timestamp getTimestampResult() throws SQLException {
		
		return getObjectResult(Timestamp.class);
	}
	
	public List<Timestamp> getTimestampList() throws SQLException {
		
		return getObjectList(Timestamp.class);
	}
	
	public Time getTimeResult() throws SQLException {
		
		return getObjectResult(Time.class);
	}
	
	public List<Time> getTimeList() throws SQLException {
		
		return getObjectList(Time.class);
	}
	
	public Boolean getBooleanResult() throws SQLException {
		
		return getObjectResult(Boolean.class);
	}
	
	public List<Boolean> getBooleanList() throws SQLException {
		
		return getObjectList(Boolean.class);
	}
	
	public List<String> getArrayResult() throws SQLException {
		
		return getObjectResult(List.class);
	}
	
	public List<List> getArrayList() throws SQLException {
		
		return getObjectList(List.class);
	}
	
	public <K, V> Entry<K, V> getEntryResult(Class<K> keyClass, Class<V> valueClass) throws SQLException{
		
		try(ResultSet result = executeSelect()){
			
			try(Statement statement = result.getStatement()){
				
				if(result.next() && columns.size() >= 2) {
					
					Column keyColumn = columns.get(0);
					Column valueColumn = columns.get(1);
					
					K key = getObject(result, keyColumn, keyClass);
					V value = getObject(result, valueColumn, valueClass);
					
					return new SimpleEntry<K, V>(key, value);
				}
			}
			
		}finally {
			
			clean();
		}

		return null;
	}
	
	public <K, V> List<Entry<K, V>> getEntryList(Class<K> keyClass, Class<V> valueClass) throws SQLException{
		
		List<Entry<K, V>> list = new ArrayList<Map.Entry<K, V>>();
		
		try(ResultSet result = executeSelect()){
			
			try(Statement statement = result.getStatement()){
				
				while(result.next() && columns.size() >= 2) {
				
					Column keyColumn = columns.get(0);
					Column valueColumn = columns.get(1);
					
					K key = getObject(result, keyColumn, keyClass);
					V value = getObject(result, valueColumn, valueClass);
					
					list.add(new SimpleEntry<K, V>(key, value));
				}
			}

		}finally {
			
			clean();
		}

		return list;
	}
	
	public <K, V> Map<K, V> getMapResult(Class<K> keyClass, Class<V> valueClass) throws SQLException{
		
		Map<K, V> map = new LinkedHashMap<K, V>();
		
		try(ResultSet result = executeSelect()){
			
			try(Statement statement = result.getStatement()){
				
				while(result.next() && columns.size() >= 2) {
					
					Column keyColumn = columns.get(0);
					Column valueColumn = columns.get(1);
					
					K key = getObject(result, keyColumn, keyClass);
					V value = getObject(result, valueColumn, valueClass);
					
					map.put(key, value);
				}
			}

		}finally {
			
			clean();
		}

		return map;
	}
	
	public Entry<Object, Object> getEntryResult() throws SQLException{
		
		return getEntryResult(Object.class, Object.class);
	}
	
	public List<Entry<Object, Object>> getEntryList() throws SQLException{
		
		return getEntryList(Object.class, Object.class);
	}
	
	public Map<Object, Object> getMapResult() throws SQLException{
		
		return getMapResult(Object.class, Object.class);
	}
	
	public int getRecordCount() throws SQLException {
		
		int count = 0;
		
		try(ResultSet result = executeSelect()){
			
			try(Statement statement = result.getStatement()){
				
				while(result.next()) {
					
					count++;
				}
			}
			
		}finally {
			
			clean();
		}
		
		return count;
	}
	
	public Map<Column, Object> getRecord() throws SQLException{
		
		Map<Column, Object> record = new LinkedHashMap<Column, Object>();
		
		try(ResultSet result = executeSelect()){
			
			try(Statement statement = result.getStatement()){
				
				if(result.next() && !columns.isEmpty()) {
					
					for(Column column : columns) {
						
						record.put(column, getObject(result, column));
					}
				}
			}

		}finally {
			
			clean();
		}
		
		return record;
	}
	
	public List<Map<Column, Object>> getRecordList() throws SQLException{
		
		List<Map<Column, Object>> list = new ArrayList<Map<Column, Object>>();
		
		try(ResultSet result = executeSelect()){
			
			try(Statement statement = result.getStatement()){
				
				while(result.next() && !columns.isEmpty()) {
					
					Map<Column, Object> record = new LinkedHashMap<Column, Object>();
					
					for(Column column : columns) {
						
						record.put(column, getObject(result, column));
					}
					
					list.add(record);
				}
			}

		}finally {
			
			clean();
		}

		return list;
	}

	public Map<Object, Map<Column, Object>> getRecordMap() throws SQLException{
		
		Map<Object, Map<Column, Object>> map = new LinkedHashMap<Object, Map<Column, Object>>();
		
		try(ResultSet result = executeSelect()){
			
			try(Statement statement = result.getStatement()){
				
				while(result.next() && !columns.isEmpty()) {
					
					Map<Column, Object> record = new LinkedHashMap<Column, Object>();
					
					for(Column column : columns) {
						
						record.put(column, getObject(result, column));
					}
					
					Column column = columns.get(0);
					
					map.put(getObject(result, column), record);
				}
			}

		}finally {
			
			clean();
		}

		return map;
	}
	
	private static Map<String, String> getClassPropertyColumn(Class className) throws SQLException {
		
		Map<String, String> properties = new LinkedHashMap<String, String>();
		
		Field[] fields = className.getDeclaredFields();
		
		for(Field field : fields) {
			
			person.annotation.Column column = field.getAnnotation(person.annotation.Column.class);
			
			if(column != null) {
				
				properties.put(column.value(), field.getName());
			}
		}
		
		return properties;
	}
	
	private void populateDTO(ResultSet result, Object dto) throws Exception {
		
		Map<String, String> properties = getClassPropertyColumn(dto.getClass());
		
		for(Column column : columns) {
			
			String property = properties.get(column.nameInResultSet);
			Class type = PropertyUtils.getPropertyType(dto, property);
			Object value = getObject(result, column, type);
			
			BeanUtils.setProperty(dto, property, value);
		}
	}
	
	private <T> T populateDTO(ResultSet result, Class<T> className) throws Exception {
		
		T dto = className.newInstance();
		
		populateDTO(result, dto);
		
		return dto;
	}
	
	public <T> T getRecord(Class<T> className) throws Exception{
		
		T dto = null;
		
		try(ResultSet result = executeSelect()){
			
			try(Statement statement = result.getStatement()){
				
				if(result.next() && !columns.isEmpty()) {
					
					dto = populateDTO(result, className);
				}
			}

		}finally {
			
			clean();
		}

		return dto;
	}
	
	public <T> T getRecord(T dto) throws Exception{
		
		try(ResultSet result = executeSelect()){
			
			try(Statement statement = result.getStatement()){
				
				if(result.next() && !columns.isEmpty()) {
					
					populateDTO(result, dto);
				}
			}

		}finally {
			
			clean();
		}

		return dto;
	}
	
	public <T> List<T> getRecordList(Class<T> className) throws Exception{
		
		List<T> list = new ArrayList<T>();
		
		try(ResultSet result = executeSelect()){
			
			try(Statement statement = result.getStatement()){
				
				while(result.next() && !columns.isEmpty()) {
					
					T dto = populateDTO(result, className);
					
					list.add(dto);
				}
			}
			
		}finally {
			
			clean();
		}

		return list;
	}
	
	public <T> Map<Integer, T> getRecordMap(Class<T> className) throws Exception{
		
		Map<Integer, T> map = new LinkedHashMap<Integer, T>();
		
		try(ResultSet result = executeSelect()){
			
			try(Statement statement = result.getStatement()){
				
				while(result.next() && !columns.isEmpty()) {
					
					T dto = populateDTO(result, className);
					
					int id = Integer.parseInt(BeanUtils.getProperty(dto, "id"));
					
					map.put(id, dto);
				}
			}

		}finally {
			
			clean();
		}
		
		return map;
	}
}