package person.database;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Case {

	protected Restriction restriction;
	protected Column column;
	protected Object value;
	protected Object columnValue;
	
	public Case() {
		
	}
	
	protected Case(Restriction restriction) {

		this.restriction = restriction;
	}
	
	protected Case(Column column) {

		this.column = column;
	}
	
	protected Case(Object value) {

		this.value = value;
	}
	
	public Case then(Column column) {

		this.column = column;
		
		return this;
	}
	
	public Case then(String value) {

		this.value = value;
		
		return this;
	}
	
	public Case then(Character value) {

		this.value = value;
		
		return this;
	}
	
	public Case then(Number value) {

		this.value = value;
		
		return this;
	}
	
	public Case then(Date value) {

		this.value = value;
		
		return this;
	}
	
	public Case then(Timestamp value) {

		this.value = value;
		
		return this;
	}
	
	public Case then(Time value) {

		this.value = value;
		
		return this;
	}
	
	public Case then(Boolean value) {

		this.value = value;
		
		return this;
	}
}
