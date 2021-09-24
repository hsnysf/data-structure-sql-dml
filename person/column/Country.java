package person.column;

import java.sql.Types;
import person.database.Column;

public interface Country {

	Column ID = new Column("cnt_id", Types.INTEGER);
	Column NAME = new Column("cnt_name", Types.VARCHAR);
}