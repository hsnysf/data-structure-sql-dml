package person.database;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Column {

	public String name;
	public int type;
	
	public Column(String name, int type) {
		this.name = name;
		this.type = type;
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
}
