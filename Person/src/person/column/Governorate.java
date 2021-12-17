package person.column;

import java.sql.Types;
import person.database.Column;

public interface Governorate {

	Column ID = new Column("gvn_id", Types.INTEGER);
	Column NAME = new Column("gvn_name", Types.VARCHAR);
}