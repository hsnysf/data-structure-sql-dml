package person.database;

public enum Join {

	INNER("inner join"),
	LEFT("left join"),
	RIGHT("right join"),
	FULL("full join");
	
	private String join;
	
	private Join(String join) {
		this.join = join;
	}
	
	@Override
	public String toString() {
		return join;
	}
}