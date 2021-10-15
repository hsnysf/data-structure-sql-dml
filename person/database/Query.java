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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

public class Query {

	protected Connection connection;
	protected List<Column> columns = new ArrayList<Column>();
	protected Table table;
	protected List<Relation> relations = new ArrayList<Relation>();
	protected Map<Column, Object> values = new LinkedHashMap<Column, Object>();
	protected List<Restriction> restrictions = new ArrayList<Restriction>();
	protected List<Column> groups = new ArrayList<Column>();
	protected Restriction having;
	protected Map<Column, Order> orders = new LinkedHashMap<Column, Order>();
	protected Integer limit;
	protected Integer offset;
	protected List<Entry<CombineOperator, Query>> combineQueries = new ArrayList<Map.Entry<CombineOperator,Query>>();
	
	
	public Query() {
		
	}
	
	public Query(Connection connection) {
		this.connection = connection;
	}
	
	private void setObject(PreparedStatement statement, int index, int type, Object value) throws SQLException {
		
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
			
			columnList.append(column);
			
			valueList.append("?");
			
			index++;
		}
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("insert into ");
		builder.append(table);
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
	
	private StringBuilder buildRestriction(Restriction restriction, List<Entry<Column, Object>> parameters){
		
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
			
			parameters.add(new SimpleEntry<Column, Object>(restriction.column, restriction.value));
			
		}else if(restriction.criteria == Criteria.LIKE){
			
			builder.append(restriction.column);
			builder.append(" ");
			builder.append(restriction.criteria);
			builder.append(" ");
			builder.append("'%' || ? || '%'");
			
			parameters.add(new SimpleEntry<Column, Object>(restriction.column, restriction.value));
		
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
			
		}else if(restriction.criteria == Criteria.IS_NULL 
				|| restriction.criteria == Criteria.IS_NOT_NULL){
			
			builder.append(restriction.column);
			builder.append(" ");
			builder.append(restriction.criteria);
			
		}else if(restriction.criteria == Criteria.IN
					|| restriction.criteria == Criteria.NOT_IN){
			
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
				
				parameters.add(new SimpleEntry<Column, Object>(restriction.column, value));
				
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
			
			parameters.add(new SimpleEntry<Column, Object>(restriction.column, restriction.value));
			parameters.add(new SimpleEntry<Column, Object>(restriction.column, restriction.to));
		
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
		
		}else if(restriction.criteria == Criteria.IN_QUERY
					|| restriction.criteria == Criteria.NOT_IN_QUERY){
			
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
		
		List<Entry<Column, Object>> parameters = new ArrayList<Entry<Column, Object>>();
		
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
	
	public Query update(Table table) {
		
		this.table = table;
		
		return this;
	}
	
	public Query set(Column column, String value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query set(Column column, Character value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query set(Column column, Number value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query set(Column column, Date value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query set(Column column, Timestamp value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query set(Column column, Time value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public Query set(Column column, Boolean value) {
		
		values.put(column, value);
		
		return this;
	}
	
	public int executeUpdate() throws SQLException {
		
		int count = 0;
		
		List<Entry<Column, Object>> parameters = new ArrayList<Entry<Column, Object>>();
		
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
			builder.append(" = ?");
			
			parameters.add(new SimpleEntry<Column, Object>(column, value));
			
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
		
		values.clear();
		
		restrictions.clear();
		
		return count;
	}
	
	public Query select(Column... column) {

		columns.addAll(Arrays.asList(column));
		
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
	
	public static Column coalesce(Column column, String value) {
		
		return column.copyWithFunction(Function.COALESCE, value);
	}
	
	public Query from(Table table) {

		this.table = table;
		
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
	
	public static Restriction exists(Query query) {
		
		return new Restriction(Criteria.EXISTS, query);
	}
	
	public static Restriction notExists(Query query) {
		
		return new Restriction(Criteria.NOT_EXISTS, query);
	}
	
	public Relation join(Table table){
		
		return innerJoin(table);
	}
	
	public Relation innerJoin(Table table){
		
		Relation relation = new Relation(Join.INNER, table, this);
		
		relations.add(relation);
		
		return relation;
	}
	
	public Relation rightJoin(Table table){
		
		Relation relation = new Relation(Join.RIGHT, table, this);
				
		relations.add(relation);
		
		return relation;
	}
	
	public Relation leftJoin(Table table){
		
		Relation relation = new Relation(Join.LEFT, table, this);
		
		relations.add(relation);
		
		return relation;
	}
	
	public Relation fullJoin(Table table){
		
		Relation relation = new Relation(Join.FULL, table, this);
		
		relations.add(relation);
		
		return relation;
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
	
	private StringBuilder getSelectQuery(List<Entry<Column, Object>> parameters) {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("select ");
		
		int index = 0;
		
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
		}
		
		builder.append(" from ");
		builder.append(table);
		
		if(table.alias != null) {
			
			builder.append(" as ");
			builder.append(table.alias);
		}
		
		if(!relations.isEmpty()) {
			
			for(Relation relation : relations) {
				
				builder.append(" ");
				builder.append(relation.join);
				builder.append(" ");
				builder.append(relation.table);
				
				if(relation.table.alias != null) {
					
					builder.append(" as ");
					builder.append(relation.table.alias);
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

			parameters.add(new SimpleEntry<Column, Object>(new Column("limit", Types.INTEGER), limit));
		}
		
		if(offset != null && offset != 0) {
			
			builder.append(" offset ?");
			
			parameters.add(new SimpleEntry<Column, Object>(new Column("offset", Types.INTEGER), offset));
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
		
		int count = 0;
		
		ResultSet result = null;
		
		List<Entry<Column, Object>> parameters = new ArrayList<Entry<Column, Object>>();
		
		StringBuilder builder = getSelectQuery(parameters);
		
		System.out.println("SQL Query :: " + builder);
		
		try(PreparedStatement statement = connection.prepareStatement(builder.toString())){
			
			int index = 1;
			
			for(Entry<Column, Object> parameter : parameters) {
				
				Column column = parameter.getKey();
				Object value = parameter.getValue();
				
				setObject(statement, index, column.type, value);
				
				index++;
			}
			
			result = statement.executeQuery();
			
			while(result.next()) {
				
				count++;
			}
		}
		
		System.out.println("Matched Rows :: " + count);
		
		columns.clear();
		
		table = null;
		
		restrictions.clear();
		
		groups.clear();
		
		having = null;
		
		orders.clear();
		
		limit = null;
		
		offset = null;
		
		combineQueries.clear();
		;
		return result;
	}
}
