package person.database;

public class Table {

	public final static Table PERSON = new Table("public.person");

	public String name;

	public Table(String name) {
		this.name = name;
	}
}