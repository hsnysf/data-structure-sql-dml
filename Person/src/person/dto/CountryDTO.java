package person.dto;

import person.annotation.Column;

public class CountryDTO {

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
	public String toString() {
		return "[id=" + id + ", name=" + name + "]";
	}
}
