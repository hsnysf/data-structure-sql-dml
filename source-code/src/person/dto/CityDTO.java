package person.dto;

import person.annotation.Column;
import person.annotation.JoinColumn;

public class CityDTO extends CommonDTO {

	@Column("ct_id")
	private Integer id;
	@Column("ct_name")
	private String name;
	@JoinColumn(name = "ct_governorate_id", on = "gvn_id")
	private GovernorateDTO governorate;
	
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
	
	public GovernorateDTO getGovernorate() {
		return governorate;
	}
	
	public void setGovernorate(GovernorateDTO governorate) {
		this.governorate = governorate;
	}
	
	@Override
	public boolean equals(Object object) {
		
		if(object != null && object instanceof CityDTO) {
			
			CityDTO city = (CityDTO) object;
			
			return id != null 
					&& city.id != null 
					&& city.id.equals(id);
			
		}else {
			
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		
		return id != null ? id : 0;
	}
}
