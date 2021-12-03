package person.column;

import java.sql.Types;
import person.database.Column;

public interface Company {

	Column ID = new Column("cmp_id", Types.INTEGER);
	Column NAME = new Column("cmp_name", Types.VARCHAR);
}