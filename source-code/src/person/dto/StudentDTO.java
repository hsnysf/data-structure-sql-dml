package person.dto;

import person.annotation.Column;
import person.annotation.ExtendJoinColumns;

@ExtendJoinColumns(name = "std_id", on = "prsn_id")
public class StudentDTO extends PersonDTO {

	@Column("std_college_name")
	private String collegeName;
	@Column("std_current_year")
	private String currentYear;
	
	public String getCollegeName() {
		return collegeName;
	}
	
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	
	public String getCurrentYear() {
		return currentYear;
	}
	
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}
}
