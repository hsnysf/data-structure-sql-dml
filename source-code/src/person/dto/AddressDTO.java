package person.dto;

import person.annotation.Column;

public class AddressDTO extends CommonDTO{

	@Column("addr_id")
	private Integer id;
	@Column("addr_building")
	private String building;
	@Column("addr_road")
	private String road;
	@Column("addr_block")
	private String block;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getBuilding() {
		return building;
	}
	
	public void setBuilding(String building) {
		this.building = building;
	}
	
	public String getRoad() {
		return road;
	}
	
	public void setRoad(String road) {
		this.road = road;
	}
	
	public String getBlock() {
		return block;
	}
	
	public void setBlock(String block) {
		this.block = block;
	}
}
