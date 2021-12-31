package person.database;

public class Table {

	public final static Table ADDRESS = new Table("public.address");
	public final static Table CITY = new Table("public.city");
	public final static Table COMPANY = new Table("public.company");
	public final static Table COUNTRY = new Table("public.country");
	public final static Table DOCTOR = new Table("public.doctor");
	public final static Table GOVERNORATE = new Table("public.governorate");
	public final static Table PERSON = new Table("public.person");
	public final static Table SCHOOL = new Table("public.school");
	public final static Table STUDENT = new Table("public.student");
	public final static Table UNIQUE_DATE_OF_BIRTH = new Table("public.unique_date_of_birth");

	protected String name;
	protected String alias;

	private Table(String name) {
		this.name = name;
	}

	private Table(String name, String alias) {
		this.name = name;
		this.alias = alias;
	}

	public Table as(String alias) {
		return new Table(name, alias);
	}

	public String toString() {
		return name;
	}
}