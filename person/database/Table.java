package person.database;

public enum Table {

	CITY("public.city"),
	COMPANY("public.company"),
	COUNTRY("public.country"),
	DOCTOR("public.doctor"),
	PERSON("public.person"),
	SCHOOL("public.school"),
	UNIQUE_DATE_OF_BIRTH("public.unique_date_of_birth");

	private String name;

	private Table(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}