package person.database;

public class Relation {

	protected Join join;
	protected Table table;
	protected Query subQuery;
	protected Restriction restriction;
	protected Query mainQuery;
	
	protected Relation(Join join, Table table, Query mainQuery) {
		this.join = join;
		this.table = table;
		this.mainQuery = mainQuery;
	}
	
	protected Relation(Join join, Query subQuery, Query mainQuery) {
		this.join = join;
		this.subQuery = subQuery;
		this.mainQuery = mainQuery;
	}
	
	public Relation as(String alias){
		
		if(subQuery != null) {
			
			subQuery.alias = alias;
		}
		
		return this;
	}
	
	public Query on(Restriction restriction){
		
		this.restriction = restriction;
		
		return mainQuery;
	}
}
