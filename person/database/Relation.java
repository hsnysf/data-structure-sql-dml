package person.database;

public class Relation {

	protected Join join;
	protected Table table;
	protected Restriction restriction;
	protected Query query;
	
	public Relation(Join join, Table table, Query query) {
		this.join = join;
		this.table = table;
		this.query = query;
	}
	
	public Query on(Restriction restriction){
		
		this.restriction = restriction;
		
		return query;
	}
}
