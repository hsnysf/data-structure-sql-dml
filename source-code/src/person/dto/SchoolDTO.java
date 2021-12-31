package person.dto;

import person.annotation.Column;

public class SchoolDTO extends CommonDTO {

	@Column("sch_id")
	private Integer id;
	@Column("sch_name")
	private String name;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object object) {
		
		if(object != null && object instanceof SchoolDTO) {
			
			SchoolDTO school = (SchoolDTO) object;
			
			return id != null 
					&& school.id != null 
					&& school.id.equals(id);
			
		}else {
			
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		
		return id != null ? id : 0;
	}
}
