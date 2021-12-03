package person.column;

import java.sql.Types;
import person.database.Column;

public interface Doctor {

	Column ID = new Column("dctr_id", Types.INTEGER);
	Column CPR = new Column("dctr_cpr", Types.INTEGER);
	Column HOSPITAL = new Column("dctr_hospital", Types.VARCHAR);
}