package person.column;

import java.sql.Types;
import person.database.Column;

public interface School {

	Column ID = new Column("sch_id", Types.INTEGER);
	Column NAME = new Column("sch_name", Types.VARCHAR);
}