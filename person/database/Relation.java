package person.database;

public class Relation {

	protected Join join;
	protected Table table;
	protected Restriction restriction;
	protected Query mainQuery;
	
	public Relation(Join join, Table table, Query mainQuery) {
		this.join = join;
		this.table = table;
		this.mainQuery = mainQuery;
	}
	
	public Query on(Restriction restriction){
		
		this.restriction = restriction;
		
		return mainQuery;
	}
}
