package person.column;

import java.sql.Types;
import person.database.Column;

public interface City {

	Column ID = new Column("ct_id", Types.INTEGER);
	Column NAME = new Column("ct_name", Types.VARCHAR);
	Column GOVERNORATE_ID = new Column("ct_governorate_id", Types.INTEGER);
}