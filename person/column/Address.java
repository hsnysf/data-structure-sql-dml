package person.column;

import java.sql.Types;
import person.database.Column;

public interface Address {

	Column ID = new Column("addr_id", Types.INTEGER);
	Column BUILDING = new Column("addr_building", Types.VARCHAR);
	Column ROAD = new Column("addr_road", Types.VARCHAR);
	Column BLOCK = new Column("addr_block", Types.VARCHAR);
}