package person.database;

public enum Order {
	
	ASC("asc"),
	DESC("desc");
	
	private String order;
	
	private Order(String order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return order;
	}
}
