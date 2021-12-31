package person.dto;

import person.annotation.Column;

public class GovernorateDTO extends CommonDTO {

	@Column("gvn_id")
	private Integer id;
	@Column("gvn_name")
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
		
		if(object != null && object instanceof GovernorateDTO) {
			
			GovernorateDTO governorate = (GovernorateDTO) object;
			
			return id != null 
					&& governorate.id != null 
					&& governorate.id.equals(id);
			
		}else {
			
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		
		return id != null ? id : 0;
	}
}
