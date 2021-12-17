package person.column;

import java.sql.Types;
import person.database.Column;

public interface Student {

	Column ID = new Column("std_id", Types.INTEGER);
	Column COLLEGE_NAME = new Column("std_college_name", Types.VARCHAR);
	Column CURRENT_YEAR = new Column("std_current_year", Types.INTEGER);
}