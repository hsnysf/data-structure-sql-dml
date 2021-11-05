package person.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RowNumber {

	protected List<Column> partitions = new ArrayList<Column>();
	protected Map<Column, Order> orders = new LinkedHashMap<Column, Order>();
	
	protected RowNumber(List<Column> partitions) {
		this.partitions = partitions;
	}
	
	protected RowNumber(Map<Column, Order> orders) {
		this.orders = orders;
	}
	

	public RowNumber partiton_by(Column... columns) {
		
		partitions.addAll(Arrays.asList(columns));
		
		return this;
	}
	
	public RowNumber order_by(Column... columns) {

		for(Column column : columns){
			
			orders.put(column, Order.ASC);
		}
		
		return this;
	}
	
	public RowNumber order_by(Entry<Column, Order>... entries) {

		for(Entry<Column, Order> entry : entries){
			
			orders.put(entry.getKey(), entry.getValue());
		}
		
		return this;
	}
	
	public RowNumber order_by_desc(Column... columns) {

		for(Column column : columns){
			
			orders.put(column, Order.DESC);
		}
		
		return this;
	}
}
