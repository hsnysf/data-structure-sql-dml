package person.dto;

import person.annotation.Column;

public class CompanyDTO extends CommonDTO {

	@Column("cmp_id")
	private Integer id;
	@Column("cmp_name")
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
		
		if(object != null && object instanceof CompanyDTO) {
			
			CompanyDTO company = (CompanyDTO) object;
			
			return id != null 
					&& company.id != null 
					&& company.id.equals(id);
			
		}else {
			
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		
		return id != null ? id : 0;
	}
}
