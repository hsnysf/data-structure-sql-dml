package person.database;

import java.util.Date;

public class Case {

	protected Restriction restriction;
	protected Column thenColumn;
	protected Object thenValue;
	protected Object whenValue;
	
	protected Case() {
		
	}
	
	protected Case(Restriction restriction) {

		this.restriction = restriction;
	}
	
	protected Case(Column thenColumn) {

		this.thenColumn = thenColumn;
	}
	
	protected Case(Object thenValue) {

		this.thenValue = thenValue;
	}
	
	public Case then(Column thenColumn) {

		this.thenColumn = thenColumn;
		
		return this;
	}
	
	public Case then(String thenValue) {

		this.thenValue = thenValue;
		
		return this;
	}
	
	public Case then(Character thenValue) {

		this.thenValue = thenValue;
		
		return this;
	}
	
	public Case then(Number thenValue) {

		this.thenValue = thenValue;
		
		return this;
	}
	
	public Case then(Date thenValue) {

		this.thenValue = thenValue;
		
		return this;
	}
		
	public Case then(Boolean thenValue) {

		this.thenValue = thenValue;
		
		return this;
	}
}
