package person.database;

public enum Table {

	DOCTOR("public.doctor"),
	PERSON("public.person"),
	UNIQUE_DATE_OF_BIRTH("public.unique_date_of_birth");

	public String name;

	private Table(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}