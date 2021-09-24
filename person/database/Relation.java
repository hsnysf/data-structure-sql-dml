package person.database;

public class Relation {

	public Join join;
	public Table table;
	public Restriction restriction;
	public Query query;
	
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
