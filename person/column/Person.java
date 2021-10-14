package person.column;

import java.sql.Types;
import person.database.Column;

public interface Person {

	Column ID = new Column("prsn_id", Types.INTEGER);
	Column NAME = new Column("prsn_name", Types.VARCHAR);
	Column GENDER = new Column("prsn_gender", Types.CHAR);
	Column AGE = new Column("prsn_age", Types.SMALLINT);
	Column CPR = new Column("prsn_cpr", Types.INTEGER);
	Column ACCOUNT_NO = new Column("prsn_account_no", Types.BIGINT);
	Column GPA = new Column("prsn_gpa", Types.DOUBLE);
	Column SALARY = new Column("prsn_salary", Types.DOUBLE);
	Column ANNUAL_INCOME = new Column("prsn_annual_income", Types.DECIMAL);
	Column DATE_OF_BIRTH = new Column("prsn_date_of_birth", Types.DATE);
	Column REGISTRATION_DATE_TIME = new Column("prsn_registration_date_time", Types.TIMESTAMP);
	Column SLEEP_TIME = new Column("prsn_sleep_time", Types.TIME);
	Column GRADUATED = new Column("prsn_graduated", Types.BOOLEAN);
	Column CITY_ID = new Column("prsn_city_id", Types.INTEGER);
	Column SCHOOL_ID = new Column("prsn_school_id", Types.INTEGER);
	Column COUNTRY_ID = new Column("prsn_country_id", Types.INTEGER);
	Column COMPANY_ID = new Column("prsn_company_id", Types.INTEGER);
	Column HOME_ADDRESS_ID = new Column("prsn_home_address_id", Types.INTEGER);
	Column WORK_ADDRESS_ID = new Column("prsn_work_address_id", Types.INTEGER);
}