package person.database.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;

import person.column.Doctor;
import person.column.Person;
import person.column.UniqueDateOfBirth;
import person.database.Query;
import person.database.Table;
import person.generator.GeneratorUtils;

public class TestDeleteQuery extends TestQuery {

	public TestDeleteQuery(Connection connection) {
		
		super(connection);
		
		excelUtils = new ExcelUtils("Test Delete Query");
		
		excelUtils.addHeaderValue("#");
		excelUtils.addHeaderValue("Test Case");
		excelUtils.addHeaderValue("Query Correctness");
		excelUtils.addHeaderValue("Parameters Correctness");
		excelUtils.addHeaderValue("Database Values Correctness");
	}
	
	public void testSingleCondition() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Delete Query with single condition");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, java.sql.Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		deleteFrom(Table.PERSON)
			.from(Table.PERSON)
			.where(Person.ID.equal(id));
		
		query = getDeleteQuery(parameters);
		
		count = executeDelete();
		
		isQueryEqual(query, "delete from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isDatabaseValuesEquals(id);
	}
	
	public void testMultipleBasicConditions() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Delete Query with multiple basic conditions");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, java.sql.Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		deleteFrom(Table.PERSON)
			.from(Table.PERSON)
			.where(Person.ID.equal(id))
			.and(Person.NAME.like("Hasan"))
			.and(Person.GENDER.equal('M'))
			.and(Person.AGE.greater(32))
			.and(Person.CPR.greaterEqual(870501236))
			.and(Person.ACCOUNT_NO.less(12345678901235l))
			.and(Person.GPA.lessEqual(3.12))
			.and(Person.SALARY.between(400.5, 500.0))
			.and(Person.ANNUAL_INCOME.in(new BigDecimal("500.40"), new BigDecimal("600.60")))
			.and(Person.DATE_OF_BIRTH.notIn(java.sql.Date.valueOf("2015-11-11"), java.sql.Date.valueOf("2015-12-12")))
			.and(Person.CITY_ID.isNull())
			.and(Person.REGISTRATION_DATE_TIME.isNotNull())
			.and(Person.DATE_OF_BIRTH.in(new Query()
					.select(UniqueDateOfBirth.DATE)
					.from(Table.UNIQUE_DATE_OF_BIRTH)
					.where(UniqueDateOfBirth.LEAP_YEAR.equal(true))))
			.and(Person.DATE_OF_BIRTH.notIn(new Query()
					.select(UniqueDateOfBirth.DATE)
					.from(Table.UNIQUE_DATE_OF_BIRTH)
					.where(UniqueDateOfBirth.LEAP_YEAR.equal(false))))
			.and(exists(new Query().select(Doctor.ID)
					.from(Table.DOCTOR).where(Doctor.CPR.equal(Person.CPR))
					.and(Doctor.HOSPITAL.equal("Alkindi"))))
			.and(notExists(new Query().select(Doctor.ID)
					.from(Table.DOCTOR).where(Doctor.CPR.equal(Person.CPR))
					.and(Doctor.HOSPITAL.equal("Bin Hayan"))));
		
		query = getDeleteQuery(parameters);
		
		count = executeDelete();
		
		isQueryEqual(query, "delete from public.person where prsn_id = ? and prsn_name ilike '%' || ? || '%' and prsn_gender = ? and prsn_age > ? and prsn_cpr >= ? and prsn_account_no < ? and prsn_gpa <= ? and prsn_salary between ? and ? and prsn_annual_income in (?, ?) and prsn_date_of_birth not in (?, ?) and prsn_city_id is null and prsn_registration_date_time is not null and prsn_date_of_birth in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) and prsn_date_of_birth not in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) and exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?) and not exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?)");
		isParametersEquals(parameters, id, "Hasan", 'M', 32, 870501236, 12345678901235l, 3.12, 400.5, 500.0, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), java.sql.Date.valueOf("2015-12-12"), true, false, "Alkindi", "Bin Hayan");
		isDatabaseValuesEquals(id);
	}
	
	public void testMultipleNestedConditions() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Delete Query with multiple nested conditions");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, java.sql.Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		deleteFrom(Table.PERSON)
			.from(Table.PERSON)
			.where(Person.ID.equal(id))
			.and(Person.NAME.like("Hasan")
					.or(Person.GENDER.equal('M')))
			.and(Person.AGE.greater(32)
					.or(Person.CPR.greaterEqual(870501236)))
			.and(Person.ACCOUNT_NO.less(12345678901235l)
					.or(Person.GPA.lessEqual(3.12)))
			.and(Person.SALARY.between(400.5, 500.0)
					.or(Person.ANNUAL_INCOME.in(new BigDecimal("500.40"), new BigDecimal("600.60"))))
			.and(Person.DATE_OF_BIRTH.notIn(java.sql.Date.valueOf("2015-11-11"), java.sql.Date.valueOf("2015-12-12")))
			.and(Person.CITY_ID.isNull()
					.or(Person.REGISTRATION_DATE_TIME.isNotNull()))
			.and(Person.DATE_OF_BIRTH.in(new Query()
					.select(UniqueDateOfBirth.DATE)
					.from(Table.UNIQUE_DATE_OF_BIRTH)
					.where(UniqueDateOfBirth.LEAP_YEAR.equal(true)))
				.or(Person.DATE_OF_BIRTH.notIn(new Query()
						.select(UniqueDateOfBirth.DATE)
					.from(Table.UNIQUE_DATE_OF_BIRTH)
					.where(UniqueDateOfBirth.LEAP_YEAR.equal(false)))))
			.and(exists(new Query().select(Doctor.ID)
					.from(Table.DOCTOR).where(Doctor.CPR.equal(Person.CPR))
					.and(Doctor.HOSPITAL.equal("Alkindi")))
				.or(notExists(new Query().select(Doctor.ID)
					.from(Table.DOCTOR).where(Doctor.CPR.equal(Person.CPR))
					.and(Doctor.HOSPITAL.equal("Bin Hayan")))));
		
		query = getDeleteQuery(parameters);
		
		count = executeDelete();
		
		isQueryEqual(query, "delete from public.person where prsn_id = ? and (prsn_name ilike '%' || ? || '%' or prsn_gender = ?) and (prsn_age > ? or prsn_cpr >= ?) and (prsn_account_no < ? or prsn_gpa <= ?) and (prsn_salary between ? and ? or prsn_annual_income in (?, ?)) and prsn_date_of_birth not in (?, ?) and (prsn_city_id is null or prsn_registration_date_time is not null) and (prsn_date_of_birth in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) or prsn_date_of_birth not in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?)) and (exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?) or not exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?))");
		isParametersEquals(parameters, id, "Hasan", 'M', 32, 870501236, 12345678901235l, 3.12, 400.5, 500.0, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), java.sql.Date.valueOf("2015-12-12"), true, false, "Alkindi", "Bin Hayan");
		isDatabaseValuesEquals(id);
	}
	
	public void testMultipleStringConditions() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Delete Query with multiple string conditions");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, java.sql.Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		deleteFrom(Table.PERSON)
			.from(Table.PERSON)
			.where(Person.ID.equal(id))
			.and(Person.NAME.like("Hasan"))
			.and(Person.GENDER.equal("M"))
			.and(Person.AGE.greater("32"))
			.and(Person.CPR.greaterEqual("870501236"))
			.and(Person.ACCOUNT_NO.less("12345678901235"))
			.and(Person.GPA.lessEqual("3.12"))
			.and(Person.SALARY.between("400.5", "500.0"))
			.and(Person.ANNUAL_INCOME.in("500.40", "600.60"))
			.and(Person.DATE_OF_BIRTH.notIn("11-11-2015", "12-12-2015"));
		
		query = getDeleteQuery(parameters);
		
		count = executeDelete();
		
		isQueryEqual(query, "delete from public.person where prsn_id = ? and prsn_name ilike '%' || ? || '%' and prsn_gender = ? and prsn_age > ? and prsn_cpr >= ? and prsn_account_no < ? and prsn_gpa <= ? and prsn_salary between ? and ? and prsn_annual_income in (?, ?) and prsn_date_of_birth not in (?, ?)");
		isParametersEquals(parameters, id, "Hasan", 'M', (short)32, 870501236, 12345678901235l, 3.12f, 400.5, 500.0, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), java.sql.Date.valueOf("2015-12-12"));
		isDatabaseValuesEquals(id);
	}
	
	public void testMultipleConditionsWithMathDate() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Delete Query with multiple conditions with math/date");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, java.sql.Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		deleteFrom(Table.PERSON)
			.from(Table.PERSON)
			.where(Person.ID.equal(id))
			.and(Person.AGE.plus(1).minus(1).greater("32"))
			.and(Person.CPR.plus(1).minus(1).greaterEqual("870501236"))
			.and(Person.ACCOUNT_NO.plus(1).minus(1).less("12345678901235"))
			.and(Person.GPA.plus(1).minus(1).lessEqual("3.12"))
			.and(Person.SALARY.plus(1).minus(1).between("400.5", "500.0"))
			.and(Person.ANNUAL_INCOME.plus(1).minus(1).in("500.40", "600.60"))
			.and(Person.DATE_OF_BIRTH.plusYears(1).minusYears(1).notIn("11-11-2015", "12-12-2015"));
		
		query = getDeleteQuery(parameters);
		
		count = executeDelete();
		
		isQueryEqual(query, "delete from public.person where prsn_id = ? and prsn_age + ? - ? > ? and prsn_cpr + ? - ? >= ? and prsn_account_no + ? - ? < ? and prsn_gpa + ? - ? <= ? and prsn_salary + ? - ? between ? and ? and prsn_annual_income + ? - ? in (?, ?) and prsn_date_of_birth + interval '1 years' - interval '1 years' not in (?, ?)");
		isParametersEquals(parameters, id, 1, 1, (short)32, 1, 1, 870501236, 1, 1, 12345678901235l, 1, 1, 3.12f, 1, 1, 400.5, 500.0, 1, 1, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), java.sql.Date.valueOf("2015-12-12"));
		isDatabaseValuesEquals(id);
	}
	
	public void testMultipleConditionsWithUtilDate() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Delete Query with multiple conditions with util date");
		
		java.util.Date date = new java.util.Date();
		
		Date sqlDate = new Date(date.getTime());
		Timestamp sqlTimestamp = new Timestamp(date.getTime());
		Time sqlTime = new Time(date.getTime());
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, date)
			.values(Person.REGISTRATION_DATE_TIME, date)
			.values(Person.SLEEP_TIME, date)
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		deleteFrom(Table.PERSON)
			.from(Table.PERSON)
			.where(Person.ID.equal(id))
			.and(Person.DATE_OF_BIRTH.equal(date))
			.and(Person.REGISTRATION_DATE_TIME.equal(date))
			.and(Person.SLEEP_TIME.equal(date));
		
		query = getDeleteQuery(parameters);
		
		count = executeDelete();
		
		isQueryEqual(query, "delete from public.person where prsn_id = ? and prsn_date_of_birth = ? and prsn_registration_date_time = ? and prsn_sleep_time = ?");
		isParametersEquals(parameters, id, sqlDate, sqlTimestamp, sqlTime);
		isDatabaseValuesEquals(id);
	}
	
	public static void main(String[] args) throws Exception {
		
		try(Connection connection = GeneratorUtils.getConnection()){
			
			TestDeleteQuery testDeleteQuery = new TestDeleteQuery(connection);
			
			testDeleteQuery.testSingleCondition();
			
			testDeleteQuery.testMultipleBasicConditions();
			
			testDeleteQuery.testMultipleNestedConditions();
			
			testDeleteQuery.testMultipleStringConditions();
			
			testDeleteQuery.testMultipleConditionsWithMathDate();
			
			testDeleteQuery.testMultipleConditionsWithUtilDate();
			
			testDeleteQuery.writeWorkbook();
		}
	}
}
