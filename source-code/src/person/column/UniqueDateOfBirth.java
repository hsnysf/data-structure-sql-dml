package person.column;

import java.sql.Types;
import person.database.Column;

public interface UniqueDateOfBirth {

	Column ID = new Column("udob_id", Types.INTEGER);
	Column DATE = new Column("udob_date", Types.DATE);
	Column LEAP_YEAR = new Column("udob_leap_year", Types.BOOLEAN);
}