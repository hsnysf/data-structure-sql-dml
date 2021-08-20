package person.database;

public enum Table {

	PERSON("public.person");

	private String name;

	private Table(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}