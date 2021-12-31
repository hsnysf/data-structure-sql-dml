package person.dto;

import person.annotation.Column;

public class CountryDTO extends CommonDTO {

	@Column("cnt_id")
	private Integer id;
	@Column("cnt_name")
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
		
		if(object != null && object instanceof CountryDTO) {
			
			CountryDTO country = (CountryDTO) object;
			
			return id != null 
					&& country.id != null 
					&& country.id.equals(id);
			
		}else {
			
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		
		return id != null ? id : 0;
	}
}
