package person.database.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;

import person.column.Doctor;
import person.column.Person;
import person.column.UniqueDateOfBirth;
import person.database.Query;
import person.database.Table;
import person.dto.PersonDTO;
import person.generator.GeneratorUtils;

public class TestUpdateQuery extends TestQuery {

	public TestUpdateQuery(Connection connection) {
		
		super(connection);
		
		excelUtils = new ExcelUtils("Test Update Query");
		
		excelUtils.addHeaderValue("#");
		excelUtils.addHeaderValue("Test Case");
		excelUtils.addHeaderValue("Query Correctness");
		excelUtils.addHeaderValue("Parameters Correctness");
		excelUtils.addHeaderValue("Database Values Correctness");
	}
	
	public void testNullValues() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with values method with null values");
		
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
		
		personDTO = new PersonDTO();
		personDTO.setId(null);
		personDTO.setName(null);
		personDTO.setGender(null);
		personDTO.setAge(null);
		personDTO.setCpr(null);
		personDTO.setAccountNo(null);
		personDTO.setGpa(null);
		personDTO.setSalary(null);
		personDTO.setAnnualIncome(null);
		personDTO.setDateOfBirth(null);
		personDTO.setRegistrationDateTime(null);
		personDTO.setSleepTime(null);
		personDTO.setGraduated(null);
		personDTO.setCertificates(null);
		
		update(Table.PERSON)
		.set(Person.NAME, personDTO.getName())
		.set(Person.GENDER, personDTO.getGender())
		.set(Person.AGE, personDTO.getAge())
		.set(Person.CPR, personDTO.getCpr())
		.set(Person.ACCOUNT_NO, personDTO.getAccountNo())
		.set(Person.GPA, personDTO.getGpa())
		.set(Person.SALARY, personDTO.getSalary())
		.set(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
		.set(Person.DATE_OF_BIRTH, personDTO.getDateOfBirth())
		.set(Person.REGISTRATION_DATE_TIME, personDTO.getRegistrationDateTime())
		.set(Person.SLEEP_TIME, personDTO.getSleepTime())
		.set(Person.GRADUATED, personDTO.getGraduated())
		.set(Person.CERTIFICATES, personDTO.getCertificates())
		.set(Person.ACTIVE, true)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_active = ? where prsn_id = ?");
		isParametersEquals(parameters, true, id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesNull method with null values");
		
		parameters.clear();
		
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
		
		update(Table.PERSON)
		.setNull(Person.NAME, personDTO.getName())
		.setNull(Person.GENDER, personDTO.getGender())
		.setNull(Person.AGE, personDTO.getAge())
		.setNull(Person.CPR, personDTO.getCpr())
		.setNull(Person.ACCOUNT_NO, personDTO.getAccountNo())
		.setNull(Person.GPA, personDTO.getGpa())
		.setNull(Person.SALARY, personDTO.getSalary())
		.setNull(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
		.setNull(Person.DATE_OF_BIRTH, personDTO.getDateOfBirth())
		.setNull(Person.REGISTRATION_DATE_TIME, personDTO.getRegistrationDateTime())
		.setNull(Person.SLEEP_TIME, personDTO.getSleepTime())
		.setNull(Person.GRADUATED, personDTO.getGraduated())
		.setNull(Person.CERTIFICATES, personDTO.getCertificates())
		.setNull(Person.ACTIVE, true)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_date_of_birth = ?, prsn_registration_date_time = ?, prsn_sleep_time = ?, prsn_graduated = ?, prsn_certificates = ?, prsn_active = ? where prsn_id = ?");
		isParametersEquals(parameters, null, null, null, null, null, null, null, null, null, null, null, null, null, true, id);
		isDatabaseValuesEquals(id, null, null, null, null, null, null, null, null, null, null, null, null, null);
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesZero method with null values");
		
		parameters.clear();
		
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
		
		update(Table.PERSON)
		.setZero(Person.NAME, personDTO.getName())
		.setZero(Person.AGE, personDTO.getAge())
		.setZero(Person.CPR, personDTO.getCpr())
		.setZero(Person.ACCOUNT_NO, personDTO.getAccountNo())
		.setZero(Person.GPA, personDTO.getGpa())
		.setZero(Person.SALARY, personDTO.getSalary())
		.setZero(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
		.set(Person.ACTIVE, true)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();

		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_active = ? where prsn_id = ?");
		isParametersEquals(parameters, 0, 0, 0, 0, 0, 0, 0, true, id);
		isDatabaseValuesEquals(id, "0", "M", 0, 0, 0l, 0.0f, 0.0, new BigDecimal("0"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesExact method with null values");
		
		parameters.clear();
		
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
		
		update(Table.PERSON)
		.setExact(Person.NAME, personDTO.getName())
		.setExact(Person.AGE, personDTO.getAge())
		.setExact(Person.CPR, personDTO.getCpr())
		.setExact(Person.ACCOUNT_NO, personDTO.getAccountNo())
		.setExact(Person.GPA, personDTO.getGpa())
		.setExact(Person.SALARY, personDTO.getSalary())
		.setExact(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
		.set(Person.ACTIVE, true)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_active = ? where prsn_id = ?");
		isParametersEquals(parameters, null, null, null, null, null, null, null, true, id);
		isDatabaseValuesEquals(id, null, "M", null, null, null, null, null, null, Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
	}
	
	public void testEmptyValues() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with values method with empty values");
		
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
		
		update(Table.PERSON)
		.set(Person.NAME, "")
		.set(Person.GENDER, "")
		.set(Person.AGE, "")
		.set(Person.CPR, "")
		.set(Person.ACCOUNT_NO, "")
		.set(Person.GPA, "")
		.set(Person.SALARY, "")
		.set(Person.ANNUAL_INCOME, "")
		.set(Person.DATE_OF_BIRTH, "")
		.set(Person.REGISTRATION_DATE_TIME, "")
		.set(Person.SLEEP_TIME, "")
		.set(Person.GRADUATED, "")
		.set(Person.CERTIFICATES, "")
		.set(Person.ACTIVE, true)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_active = ? where prsn_id = ?");
		isParametersEquals(parameters, true, id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesNull method with empty values");
		
		parameters.clear();
		
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
		
		update(Table.PERSON)
		.setNull(Person.NAME, "")
		.setNull(Person.GENDER, "")
		.setNull(Person.AGE, "")
		.setNull(Person.CPR, "")
		.setNull(Person.ACCOUNT_NO, "")
		.setNull(Person.GPA, "")
		.setNull(Person.SALARY, "")
		.setNull(Person.ANNUAL_INCOME, "")
		.setNull(Person.DATE_OF_BIRTH, "")
		.setNull(Person.REGISTRATION_DATE_TIME, "")
		.setNull(Person.SLEEP_TIME, "")
		.setNull(Person.GRADUATED, "")
		.setNull(Person.CERTIFICATES, "")
		.set(Person.ACTIVE, true)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_date_of_birth = ?, prsn_registration_date_time = ?, prsn_sleep_time = ?, prsn_graduated = ?, prsn_certificates = ?, prsn_active = ? where prsn_id = ?");
		isParametersEquals(parameters, null, null, null, null, null, null, null, null, null, null, null, null, null, true, id);
		isDatabaseValuesEquals(id, null, null, null, null, null, null, null, null, null, null, null, null, null);
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesZero method with empty values");
		
		parameters.clear();
		
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
		
		update(Table.PERSON)
		.setZero(Person.NAME, "")
		.setZero(Person.GENDER, "")
		.setZero(Person.AGE, "")
		.setZero(Person.CPR, "")
		.setZero(Person.ACCOUNT_NO, "")
		.setZero(Person.GPA, "")
		.setZero(Person.SALARY, "")
		.setZero(Person.ANNUAL_INCOME, "")
		.set(Person.ACTIVE, true)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();

		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_active = ? where prsn_id = ?");
		isParametersEquals(parameters, 0, 0, 0, 0, 0, 0, 0, 0, true, id);
		isDatabaseValuesEquals(id, "0", "0", 0, 0, 0l, 0.0f, 0.0, new BigDecimal("0"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesExact method with empty values");
		
		parameters.clear();
		
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
		
		update(Table.PERSON)
		.setExact(Person.NAME, "")
		.setExact(Person.GENDER, "")
		.setExact(Person.AGE, "")
		.setExact(Person.CPR, "")
		.setExact(Person.ACCOUNT_NO, "")
		.setExact(Person.GPA, "")
		.setExact(Person.SALARY, "")
		.setExact(Person.ANNUAL_INCOME, "")
		.setExact(Person.DATE_OF_BIRTH, "")
		.setExact(Person.REGISTRATION_DATE_TIME, "")
		.setExact(Person.SLEEP_TIME, "")
		.setExact(Person.GRADUATED, "")
		.setExact(Person.CERTIFICATES, "")
		.set(Person.ACTIVE, true)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_date_of_birth = ?, prsn_registration_date_time = ?, prsn_sleep_time = ?, prsn_graduated = ?, prsn_certificates = ?, prsn_active = ? where prsn_id = ?");
		isParametersEquals(parameters, null, null, null, null, null, null, null, null, null, null, null, null, null, true, id);
		isDatabaseValuesEquals(id, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	
	public void testZeroValues() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with values method with zero values");
		
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
		
		update(Table.PERSON)
		.set(Person.NAME, 0)
		.set(Person.GENDER, 0)
		.set(Person.AGE, 0)
		.set(Person.CPR, 0)
		.set(Person.ACCOUNT_NO, 0)
		.set(Person.GPA, 0)
		.set(Person.SALARY, 0)
		.set(Person.ANNUAL_INCOME, 0)
		.set(Person.ACTIVE, true)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_active = ? where prsn_id = ?");
		isParametersEquals(parameters, true, id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesNull method with zero values");
		
		parameters.clear();
		
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
		
		update(Table.PERSON)
		.setNull(Person.NAME, 0)
		.setNull(Person.GENDER, 0)
		.setNull(Person.AGE, 0)
		.setNull(Person.CPR, 0)
		.setNull(Person.ACCOUNT_NO, 0)
		.setNull(Person.GPA, 0)
		.setNull(Person.SALARY, 0)
		.setNull(Person.ANNUAL_INCOME, 0)
		.set(Person.ACTIVE, true)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_active = ? where prsn_id = ?");
		isParametersEquals(parameters, null, null, null, null, null, null, null, null, true, id);
		isDatabaseValuesEquals(id, null, null, null, null, null, null, null, null, Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesZero method with zero values");
		
		parameters.clear();
		
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
		
		update(Table.PERSON)
		.setZero(Person.NAME, 0)
		.setZero(Person.GENDER, 0)
		.setZero(Person.AGE, 0)
		.setZero(Person.CPR, 0)
		.setZero(Person.ACCOUNT_NO, 0)
		.setZero(Person.GPA, 0)
		.setZero(Person.SALARY, 0)
		.setZero(Person.ANNUAL_INCOME, 0)
		.set(Person.ACTIVE, true)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();

		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_active = ? where prsn_id = ?");
		isParametersEquals(parameters, 0, 0, 0, 0, 0, 0, 0, 0, true, id);
		isDatabaseValuesEquals(id, "0", "0", 0, 0, 0l, 0.0f, 0.0, new BigDecimal(0), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesExact method with zero values");
		
		parameters.clear();
		
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
		
		update(Table.PERSON)
		.setExact(Person.NAME, 0)
		.setExact(Person.GENDER, 0)
		.setExact(Person.AGE, 0)
		.setExact(Person.CPR, 0)
		.setExact(Person.ACCOUNT_NO, 0)
		.setExact(Person.GPA, 0)
		.setExact(Person.SALARY, 0)
		.setExact(Person.ANNUAL_INCOME, 0)
		.set(Person.ACTIVE, true)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_active = ? where prsn_id = ?");
		isParametersEquals(parameters, 0, 0, 0, 0, 0, 0, 0, 0, true, id);
		isDatabaseValuesEquals(id, "0", "0", 0, 0, 0l, 0.0f, 0.0, new BigDecimal(0), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
	}
	
	public void testStringZeroValues() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with values method with string zero values");
		
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
		
		update(Table.PERSON)
		.set(Person.NAME, "0")
		.set(Person.GENDER, "0")
		.set(Person.AGE, "0")
		.set(Person.CPR, "0")
		.set(Person.ACCOUNT_NO, "0")
		.set(Person.GPA, "0")
		.set(Person.SALARY, "0")
		.set(Person.ANNUAL_INCOME, "0")
		.set(Person.ACTIVE, true)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_active = ? where prsn_id = ?");
		isParametersEquals(parameters, true, id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesNull method with string zero values");
		
		parameters.clear();
		
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
		
		update(Table.PERSON)
		.setNull(Person.NAME, "0")
		.setNull(Person.GENDER, "0")
		.setNull(Person.AGE, "0")
		.setNull(Person.CPR, "0")
		.setNull(Person.ACCOUNT_NO, "0")
		.setNull(Person.GPA, "0")
		.setNull(Person.SALARY, "0")
		.setNull(Person.ANNUAL_INCOME, "0")
		.set(Person.ACTIVE, true)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_active = ? where prsn_id = ?");
		isParametersEquals(parameters, null, null, null, null, null, null, null, null, true, id);
		isDatabaseValuesEquals(id, null, null, null, null, null, null, null, null, Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesZero method with string zero values");
		
		parameters.clear();
		
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
		
		update(Table.PERSON)
		.setZero(Person.NAME, "0")
		.setZero(Person.GENDER, "0")
		.setZero(Person.AGE, "0")
		.setZero(Person.CPR, "0")
		.setZero(Person.ACCOUNT_NO, "0")
		.setZero(Person.GPA, "0")
		.setZero(Person.SALARY, "0")
		.setZero(Person.ANNUAL_INCOME, "0")
		.set(Person.ACTIVE, true)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();

		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_active = ? where prsn_id = ?");
		isParametersEquals(parameters, 0, 0, 0, 0, 0, 0, 0, 0, true, id);
		isDatabaseValuesEquals(id, "0", "0", 0, 0, 0l, 0.0f, 0.0, new BigDecimal(0), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesExact method with string zero values");
		
		parameters.clear();
		
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
		
		update(Table.PERSON)
		.setExact(Person.NAME, "0")
		.setExact(Person.GENDER, "0")
		.setExact(Person.AGE, "0")
		.setExact(Person.CPR, "0")
		.setExact(Person.ACCOUNT_NO, "0")
		.setExact(Person.GPA, "0")
		.setExact(Person.SALARY, "0")
		.setExact(Person.ANNUAL_INCOME, "0")
		.set(Person.ACTIVE, true)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_active = ? where prsn_id = ?");
		isParametersEquals(parameters, "0", '0', (short)0, 0, 0l, 0.0f, 0.0, new BigDecimal(0), true, id);
		isDatabaseValuesEquals(id, "0", "0", 0, 0, 0l, 0.0f, 0.0, new BigDecimal(0), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
	}
	
	public void testValidValues() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with values method with valid values");
		
		personDTO = new PersonDTO();
		personDTO.setId(null);
		personDTO.setName(null);
		personDTO.setGender(null);
		personDTO.setAge(null);
		personDTO.setCpr(null);
		personDTO.setAccountNo(null);
		personDTO.setGpa(null);
		personDTO.setSalary(null);
		personDTO.setAnnualIncome(null);
		personDTO.setDateOfBirth(null);
		personDTO.setRegistrationDateTime(null);
		personDTO.setSleepTime(null);
		personDTO.setGraduated(null);
		personDTO.setCertificates(null);
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, personDTO.getName())
			.values(Person.GENDER, personDTO.getGender())
			.values(Person.AGE, personDTO.getAge())
			.values(Person.CPR, personDTO.getCpr())
			.values(Person.ACCOUNT_NO, personDTO.getAccountNo())
			.values(Person.GPA, personDTO.getGpa())
			.values(Person.SALARY, personDTO.getSalary())
			.values(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
			.values(Person.DATE_OF_BIRTH, personDTO.getDateOfBirth())
			.values(Person.REGISTRATION_DATE_TIME, personDTO.getRegistrationDateTime())
			.values(Person.SLEEP_TIME, personDTO.getSleepTime())
			.values(Person.GRADUATED, personDTO.getGraduated())
			.values(Person.CERTIFICATES, personDTO.getCertificates())
			.values(Person.ACTIVE, true)
			.executeInsert();
		
		update(Table.PERSON)
		.set(Person.NAME, "Hasan")
		.set(Person.GENDER, 'M')
		.set(Person.AGE, (short)33)
		.set(Person.CPR, 870501236)
		.set(Person.ACCOUNT_NO, 12345678901234l)
		.set(Person.GPA, 3.12f)
		.set(Person.SALARY, 400.5)
		.set(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
		.set(Person.DATE_OF_BIRTH, java.sql.Date.valueOf("2015-10-10"))
		.set(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
		.set(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
		.set(Person.GRADUATED, true)
		.set(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_date_of_birth = ?, prsn_registration_date_time = ?, prsn_sleep_time = ?, prsn_graduated = ?, prsn_certificates = ? where prsn_id = ?");
		isParametersEquals(parameters, "Hasan", 'M', (short)33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"), id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesNull method with valid values");
		
		parameters.clear();
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, personDTO.getName())
			.values(Person.GENDER, personDTO.getGender())
			.values(Person.AGE, personDTO.getAge())
			.values(Person.CPR, personDTO.getCpr())
			.values(Person.ACCOUNT_NO, personDTO.getAccountNo())
			.values(Person.GPA, personDTO.getGpa())
			.values(Person.SALARY, personDTO.getSalary())
			.values(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
			.values(Person.DATE_OF_BIRTH, personDTO.getDateOfBirth())
			.values(Person.REGISTRATION_DATE_TIME, personDTO.getRegistrationDateTime())
			.values(Person.SLEEP_TIME, personDTO.getSleepTime())
			.values(Person.GRADUATED, personDTO.getGraduated())
			.values(Person.CERTIFICATES, personDTO.getCertificates())
			.values(Person.ACTIVE, true)
			.executeInsert();
		
		update(Table.PERSON)
		.setNull(Person.NAME, "Hasan")
		.setNull(Person.GENDER, 'M')
		.setNull(Person.AGE, 33)
		.setNull(Person.CPR, 870501236)
		.setNull(Person.ACCOUNT_NO, 12345678901234l)
		.setNull(Person.GPA, 3.12)
		.setNull(Person.SALARY, 400.5)
		.setNull(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
		.setNull(Person.DATE_OF_BIRTH, java.sql.Date.valueOf("2015-10-10"))
		.setNull(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
		.setNull(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
		.setNull(Person.GRADUATED, true)
		.setNull(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_date_of_birth = ?, prsn_registration_date_time = ?, prsn_sleep_time = ?, prsn_graduated = ?, prsn_certificates = ? where prsn_id = ?");
		isParametersEquals(parameters, "Hasan", 'M', 33, 870501236, 12345678901234l, 3.12, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"), id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesZero method with valid values");
		
		parameters.clear();
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, personDTO.getName())
			.values(Person.GENDER, personDTO.getGender())
			.values(Person.AGE, personDTO.getAge())
			.values(Person.CPR, personDTO.getCpr())
			.values(Person.ACCOUNT_NO, personDTO.getAccountNo())
			.values(Person.GPA, personDTO.getGpa())
			.values(Person.SALARY, personDTO.getSalary())
			.values(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
			.values(Person.DATE_OF_BIRTH, personDTO.getDateOfBirth())
			.values(Person.REGISTRATION_DATE_TIME, personDTO.getRegistrationDateTime())
			.values(Person.SLEEP_TIME, personDTO.getSleepTime())
			.values(Person.GRADUATED, personDTO.getGraduated())
			.values(Person.CERTIFICATES, personDTO.getCertificates())
			.values(Person.ACTIVE, true)
			.executeInsert();
		
		update(Table.PERSON)
		.setZero(Person.NAME, "Hasan")
		.setZero(Person.AGE, 33)
		.setZero(Person.CPR, 870501236)
		.setZero(Person.ACCOUNT_NO, 12345678901234l)
		.setZero(Person.GPA, 3.12)
		.setZero(Person.SALARY, 400.5)
		.setZero(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();

		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ? where prsn_id = ?");
		isParametersEquals(parameters, "Hasan", 33, 870501236, 12345678901234l, 3.12, 400.5, new BigDecimal("500.40"), id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56"), Time.valueOf("10:10:10"), true, Arrays.asList("C++","C#"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesExact method with valid values");
		
		parameters.clear();
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, personDTO.getName())
			.values(Person.GENDER, personDTO.getGender())
			.values(Person.AGE, personDTO.getAge())
			.values(Person.CPR, personDTO.getCpr())
			.values(Person.ACCOUNT_NO, personDTO.getAccountNo())
			.values(Person.GPA, personDTO.getGpa())
			.values(Person.SALARY, personDTO.getSalary())
			.values(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
			.values(Person.DATE_OF_BIRTH, personDTO.getDateOfBirth())
			.values(Person.REGISTRATION_DATE_TIME, personDTO.getRegistrationDateTime())
			.values(Person.SLEEP_TIME, personDTO.getSleepTime())
			.values(Person.GRADUATED, personDTO.getGraduated())
			.values(Person.CERTIFICATES, personDTO.getCertificates())
			.values(Person.ACTIVE, true)
			.executeInsert();
		
		update(Table.PERSON)
		.setExact(Person.NAME, "Hasan")
		.setExact(Person.AGE, 33)
		.setExact(Person.CPR, 870501236)
		.setExact(Person.ACCOUNT_NO, 12345678901234l)
		.setExact(Person.GPA, 3.12)
		.setExact(Person.SALARY, 400.5)
		.setExact(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ? where prsn_id = ?");
		isParametersEquals(parameters, "Hasan", 33, 870501236, 12345678901234l, 3.12, 400.5, new BigDecimal("500.40"), id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56"), Time.valueOf("10:10:10"), true, Arrays.asList("C++","C#"));
	}
	
	public void testValidStringValues() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with values method with valid string values");
		
		personDTO = new PersonDTO();
		personDTO.setId(null);
		personDTO.setName(null);
		personDTO.setGender(null);
		personDTO.setAge(null);
		personDTO.setCpr(null);
		personDTO.setAccountNo(null);
		personDTO.setGpa(null);
		personDTO.setSalary(null);
		personDTO.setAnnualIncome(null);
		personDTO.setDateOfBirth(null);
		personDTO.setRegistrationDateTime(null);
		personDTO.setSleepTime(null);
		personDTO.setGraduated(null);
		personDTO.setCertificates(null);
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, personDTO.getName())
			.values(Person.GENDER, personDTO.getGender())
			.values(Person.AGE, personDTO.getAge())
			.values(Person.CPR, personDTO.getCpr())
			.values(Person.ACCOUNT_NO, personDTO.getAccountNo())
			.values(Person.GPA, personDTO.getGpa())
			.values(Person.SALARY, personDTO.getSalary())
			.values(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
			.values(Person.DATE_OF_BIRTH, personDTO.getDateOfBirth())
			.values(Person.REGISTRATION_DATE_TIME, personDTO.getRegistrationDateTime())
			.values(Person.SLEEP_TIME, personDTO.getSleepTime())
			.values(Person.GRADUATED, personDTO.getGraduated())
			.values(Person.CERTIFICATES, personDTO.getCertificates())
			.values(Person.ACTIVE, true)
			.executeInsert();
		
		update(Table.PERSON)
		.set(Person.NAME, "Hasan")
		.set(Person.GENDER, "M")
		.set(Person.AGE, "33")
		.set(Person.CPR, "870501236")
		.set(Person.ACCOUNT_NO, "12345678901234")
		.set(Person.GPA, "3.12")
		.set(Person.SALARY, "400.5")
		.set(Person.ANNUAL_INCOME, "500.40")
		.set(Person.DATE_OF_BIRTH, "10-10-2015")
		.set(Person.REGISTRATION_DATE_TIME, "10-10-2000 10:10:10")
		.set(Person.SLEEP_TIME, "10:10:10")
		.set(Person.GRADUATED, "true")
		.set(Person.CERTIFICATES, "DB2,Java")
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_date_of_birth = ?, prsn_registration_date_time = ?, prsn_sleep_time = ?, prsn_graduated = ?, prsn_certificates = ? where prsn_id = ?");
		isParametersEquals(parameters, "Hasan", 'M', (short)33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"), id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesNull method with valid string values");
		
		parameters.clear();
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, personDTO.getName())
			.values(Person.GENDER, personDTO.getGender())
			.values(Person.AGE, personDTO.getAge())
			.values(Person.CPR, personDTO.getCpr())
			.values(Person.ACCOUNT_NO, personDTO.getAccountNo())
			.values(Person.GPA, personDTO.getGpa())
			.values(Person.SALARY, personDTO.getSalary())
			.values(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
			.values(Person.DATE_OF_BIRTH, personDTO.getDateOfBirth())
			.values(Person.REGISTRATION_DATE_TIME, personDTO.getRegistrationDateTime())
			.values(Person.SLEEP_TIME, personDTO.getSleepTime())
			.values(Person.GRADUATED, personDTO.getGraduated())
			.values(Person.CERTIFICATES, personDTO.getCertificates())
			.values(Person.ACTIVE, true)
			.executeInsert();
		
		update(Table.PERSON)
		.setNull(Person.NAME, "Hasan")
		.setNull(Person.GENDER, "M")
		.setNull(Person.AGE, "33")
		.setNull(Person.CPR, "870501236")
		.setNull(Person.ACCOUNT_NO, "12345678901234")
		.setNull(Person.GPA, "3.12")
		.setNull(Person.SALARY, "400.5")
		.setNull(Person.ANNUAL_INCOME, "500.40")
		.setNull(Person.DATE_OF_BIRTH, "10-10-2015")
		.setNull(Person.REGISTRATION_DATE_TIME, "10-10-2000 10:10:10")
		.setNull(Person.SLEEP_TIME, "10:10:10")
		.setNull(Person.GRADUATED, "true")
		.setNull(Person.CERTIFICATES, "DB2,Java")
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_date_of_birth = ?, prsn_registration_date_time = ?, prsn_sleep_time = ?, prsn_graduated = ?, prsn_certificates = ? where prsn_id = ?");
		isParametersEquals(parameters, "Hasan", 'M', (short)33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"), id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesZero method with valid string values");
		
		parameters.clear();
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, personDTO.getName())
			.values(Person.GENDER, personDTO.getGender())
			.values(Person.AGE, personDTO.getAge())
			.values(Person.CPR, personDTO.getCpr())
			.values(Person.ACCOUNT_NO, personDTO.getAccountNo())
			.values(Person.GPA, personDTO.getGpa())
			.values(Person.SALARY, personDTO.getSalary())
			.values(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
			.values(Person.DATE_OF_BIRTH, personDTO.getDateOfBirth())
			.values(Person.REGISTRATION_DATE_TIME, personDTO.getRegistrationDateTime())
			.values(Person.SLEEP_TIME, personDTO.getSleepTime())
			.values(Person.GRADUATED, personDTO.getGraduated())
			.values(Person.CERTIFICATES, personDTO.getCertificates())
			.values(Person.ACTIVE, true)
			.executeInsert();
		
		update(Table.PERSON)
		.setZero(Person.NAME, "Hasan")
		.setZero(Person.AGE, "33")
		.setZero(Person.CPR, "870501236")
		.setZero(Person.ACCOUNT_NO, "12345678901234")
		.setZero(Person.GPA, "3.12")
		.setZero(Person.SALARY, "400.5")
		.setZero(Person.ANNUAL_INCOME, "500.40")
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();

		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ? where prsn_id = ?");
		isParametersEquals(parameters, "Hasan", (short)33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56"), Time.valueOf("10:10:10"), true, Arrays.asList("C++","C#"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with valuesExact method with valid string values");
		
		parameters.clear();
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, personDTO.getName())
			.values(Person.GENDER, personDTO.getGender())
			.values(Person.AGE, personDTO.getAge())
			.values(Person.CPR, personDTO.getCpr())
			.values(Person.ACCOUNT_NO, personDTO.getAccountNo())
			.values(Person.GPA, personDTO.getGpa())
			.values(Person.SALARY, personDTO.getSalary())
			.values(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
			.values(Person.DATE_OF_BIRTH, personDTO.getDateOfBirth())
			.values(Person.REGISTRATION_DATE_TIME, personDTO.getRegistrationDateTime())
			.values(Person.SLEEP_TIME, personDTO.getSleepTime())
			.values(Person.GRADUATED, personDTO.getGraduated())
			.values(Person.CERTIFICATES, personDTO.getCertificates())
			.values(Person.ACTIVE, true)
			.executeInsert();
		
		update(Table.PERSON)
		.setExact(Person.NAME, "Hasan")
		.setExact(Person.AGE, "33")
		.setExact(Person.CPR, "870501236")
		.setExact(Person.ACCOUNT_NO, "12345678901234")
		.setExact(Person.GPA, "3.12")
		.setExact(Person.SALARY, "400.5")
		.setExact(Person.ANNUAL_INCOME, "500.40")
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ? where prsn_id = ?");
		isParametersEquals(parameters, "Hasan", (short)33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56"), Time.valueOf("10:10:10"), true, Arrays.asList("C++","C#"));
	}
	
	public void testUtilDateValues() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with set method with util date values");
		
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
			.values(Person.DATE_OF_BIRTH, java.sql.Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		update(Table.PERSON)
		.set(Person.DATE_OF_BIRTH, date)
		.set(Person.REGISTRATION_DATE_TIME, date)
		.set(Person.SLEEP_TIME, date)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_date_of_birth = ?, prsn_registration_date_time = ?, prsn_sleep_time = ? where prsn_id = ?");
		isParametersEquals(parameters, sqlDate, sqlTimestamp, sqlTime, id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), sqlDate, sqlTimestamp, sqlTime, true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with setNull method with util date values");
		
		parameters.clear();
		
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
		
		update(Table.PERSON)
		.setNull(Person.DATE_OF_BIRTH, date)
		.setNull(Person.REGISTRATION_DATE_TIME, date)
		.setNull(Person.SLEEP_TIME, date)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_date_of_birth = ?, prsn_registration_date_time = ?, prsn_sleep_time = ? where prsn_id = ?");
		isParametersEquals(parameters, sqlDate, sqlTimestamp, sqlTime, id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), sqlDate, sqlTimestamp, sqlTime, true, Arrays.asList("DB2","Java"));
	}
	
	public void testArrayVariableAurgementsValues() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with values method with variable aurgements array values");
		
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
		
		update(Table.PERSON)
		.set(Person.CERTIFICATES, "DB2", "Java")
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_certificates = ? where prsn_id = ?");
		isParametersEquals(parameters, Arrays.asList("DB2","Java"), id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
	}
	
	public void testUpdateColumn() throws Exception {
		
		java.util.Date date = new java.util.Date();
		
		Date sqlDate = new Date(date.getTime());
		Timestamp sqlTimestamp = new Timestamp(date.getTime());
		Time sqlTime = new Time(date.getTime());
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with set method with column");
		
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
		
		update(Table.PERSON)
		.set(Person.AGE, Person.AGE)
		.set(Person.CPR, Person.CPR)
		.set(Person.ACCOUNT_NO, Person.ACCOUNT_NO)
		.set(Person.GPA, Person.GPA)
		.set(Person.SALARY, Person.SALARY)
		.set(Person.ANNUAL_INCOME, Person.ANNUAL_INCOME)
		.set(Person.DATE_OF_BIRTH, CURRENT_DATE)
		.set(Person.REGISTRATION_DATE_TIME, CURRENT_TIMESTAMP)
		.set(Person.SLEEP_TIME, CURRENT_TIME)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_age = prsn_age, prsn_cpr = prsn_cpr, prsn_account_no = prsn_account_no, prsn_gpa = prsn_gpa, prsn_salary = prsn_salary, prsn_annual_income = prsn_annual_income, prsn_date_of_birth = current_date, prsn_registration_date_time = current_timestamp, prsn_sleep_time = current_time where prsn_id = ?");
		isParametersEquals(parameters, id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), sqlDate, sqlTimestamp, sqlTime, true, Arrays.asList("DB2","Java"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with set method with column with one date function");
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		
		sqlDate = new Date(calendar.getTimeInMillis());
		
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, 2);
		
		sqlTimestamp = new Timestamp(calendar.getTimeInMillis());
		
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, 5);
		
		sqlTime = new Time(calendar.getTimeInMillis());
		
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
		
		update(Table.PERSON)
		.set(Person.AGE, Person.AGE.plus(1))
		.set(Person.CPR, Person.CPR.minus(2))
		.set(Person.ACCOUNT_NO, Person.ACCOUNT_NO.multiply(3))
		.set(Person.GPA, Person.GPA.divide(4))
		.set(Person.SALARY, Person.SALARY.plus(Person.SALARY))
		.set(Person.ANNUAL_INCOME, Person.ANNUAL_INCOME.minus(Person.ANNUAL_INCOME))
		.set(Person.DATE_OF_BIRTH, CURRENT_DATE.plusDays(1))
		.set(Person.REGISTRATION_DATE_TIME, CURRENT_TIMESTAMP.plusHours(2))
		.set(Person.SLEEP_TIME, CURRENT_TIME.plusMinutes(5))
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_age = prsn_age + ?, prsn_cpr = prsn_cpr - ?, prsn_account_no = prsn_account_no * ?, prsn_gpa = prsn_gpa / ?, prsn_salary = prsn_salary + prsn_salary, prsn_annual_income = prsn_annual_income - prsn_annual_income, prsn_date_of_birth = current_date + interval '1 days', prsn_registration_date_time = current_timestamp + interval '2 hours', prsn_sleep_time = current_time + interval '5 minutes' where prsn_id = ?");
		isParametersEquals(parameters, 1, 2, 3, 4, id);
		isDatabaseValuesEquals(id, "Hasan", "M", 34, 870501234, 37037036703702l, 0.78f, 801.0, new BigDecimal("0.00"), sqlDate, sqlTimestamp, sqlTime, true, Arrays.asList("DB2","Java"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with set method with column with multiple date functions");
		
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		calendar.add(Calendar.MONTH, -3);
		
		sqlDate = new Date(calendar.getTimeInMillis());
		
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, 2);
		calendar.add(Calendar.DATE, -4);
		
		sqlTimestamp = new Timestamp(calendar.getTimeInMillis());
		
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, 5);
		calendar.add(Calendar.HOUR_OF_DAY, -6);
		
		sqlTime = new Time(calendar.getTimeInMillis());
		
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
		
		update(Table.PERSON)
		.set(Person.AGE, Person.AGE.plus(1).minus(1))
		.set(Person.CPR, Person.CPR.minus(2).plus(3))
		.set(Person.ACCOUNT_NO, Person.ACCOUNT_NO.multiply(3).minus(2))
		.set(Person.GPA, Person.GPA.divide(4).multiply(4))
		.set(Person.SALARY, Person.SALARY.plus(Person.SALARY).plus(Person.ANNUAL_INCOME))
		.set(Person.ANNUAL_INCOME, Person.ANNUAL_INCOME.multiply(Person.ANNUAL_INCOME).divide(Person.SALARY))
		.set(Person.DATE_OF_BIRTH, CURRENT_DATE.plusDays(1).minusMonths(3))
		.set(Person.REGISTRATION_DATE_TIME, CURRENT_TIMESTAMP.plusHours(2).minusDays(4))
		.set(Person.SLEEP_TIME, CURRENT_TIME.plusMinutes(5).minusHours(6))
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_age = prsn_age + ? - ?, prsn_cpr = prsn_cpr - ? + ?, prsn_account_no = prsn_account_no * ? - ?, prsn_gpa = prsn_gpa / ? * ?, prsn_salary = prsn_salary + prsn_salary + prsn_annual_income, prsn_annual_income = prsn_annual_income * prsn_annual_income / prsn_salary, prsn_date_of_birth = current_date + interval '1 days' - interval '3 months', prsn_registration_date_time = current_timestamp + interval '2 hours' - interval '4 days', prsn_sleep_time = current_time + interval '5 minutes' - interval '6 hours' where prsn_id = ?");
		isParametersEquals(parameters, 1, 1, 2, 3, 3, 2, 4, 4, id);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501237, 37037036703700l, 3.12f, 1301.4, new BigDecimal("625.218876404494"), sqlDate, sqlTimestamp, sqlTime, true, Arrays.asList("DB2","Java"));
	}

	public void testUpdateColumnWithDateMathFunction() throws Exception {
		
		java.util.Date date = new java.util.Date();
		
		Date sqlDate = new Date(date.getTime());
		Timestamp sqlTimestamp = new Timestamp(date.getTime());
		Time sqlTime = new Time(date.getTime());
		
		Calendar calendar = Calendar.getInstance();
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		long day = calendar.get(Calendar.DAY_OF_MONTH);
		float hour = calendar.get(Calendar.HOUR_OF_DAY);
		double minute = calendar.get(Calendar.MINUTE);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with set method with column with date part functions");
		
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
		
		update(Table.PERSON)
		.set(Person.AGE, year(CURRENT_DATE))
		.set(Person.CPR, month(CURRENT_DATE))
		.set(Person.ACCOUNT_NO, day(CURRENT_DATE))
		.set(Person.GPA, hour(CURRENT_TIMESTAMP))
		.set(Person.SALARY, minute(CURRENT_TIMESTAMP))
		.set(Person.DATE_OF_BIRTH, date(CURRENT_TIMESTAMP))
		.set(Person.REGISTRATION_DATE_TIME, CURRENT_TIMESTAMP)
		.set(Person.SLEEP_TIME, CURRENT_TIME)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_age = date_part('year', current_date), prsn_cpr = date_part('month', current_date), prsn_account_no = date_part('day', current_date), prsn_gpa = date_part('hour', current_timestamp), prsn_salary = date_part('minute', current_timestamp), prsn_date_of_birth = date(current_timestamp), prsn_registration_date_time = current_timestamp, prsn_sleep_time = current_time where prsn_id = ?");
		isParametersEquals(parameters, id);
		isDatabaseValuesEquals(id, "Hasan", "M", year, month, day, hour, minute, new BigDecimal("500.40"), sqlDate, sqlTimestamp, sqlTime, true, Arrays.asList("DB2", "Java"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with set method with column with date part & math functions");
		
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
		
		update(Table.PERSON)
		.set(Person.AGE, year(CURRENT_DATE).plus(1))
		.set(Person.CPR, month(CURRENT_DATE).plus(2))
		.set(Person.ACCOUNT_NO, day(CURRENT_DATE).plus(3))
		.set(Person.GPA, hour(CURRENT_TIMESTAMP).minus(1))
		.set(Person.SALARY, minute(CURRENT_TIMESTAMP).minus(2))
		.set(Person.DATE_OF_BIRTH, date(CURRENT_TIMESTAMP))
		.set(Person.REGISTRATION_DATE_TIME, CURRENT_TIMESTAMP)
		.set(Person.SLEEP_TIME, CURRENT_TIME)
		.where(Person.ID.equal(id));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		year+=1;
		month+=2;
		day+=3;
		hour-=1;
		minute-=2;
		
		isQueryEqual(query, "update public.person set prsn_age = date_part('year', current_date) + ?, prsn_cpr = date_part('month', current_date) + ?, prsn_account_no = date_part('day', current_date) + ?, prsn_gpa = date_part('hour', current_timestamp) - ?, prsn_salary = date_part('minute', current_timestamp) - ?, prsn_date_of_birth = date(current_timestamp), prsn_registration_date_time = current_timestamp, prsn_sleep_time = current_time where prsn_id = ?");
		isParametersEquals(parameters, 1, 2, 3, 1, 2, id);
		isDatabaseValuesEquals(id, "Hasan", "M", year, month, day, hour, minute, new BigDecimal("500.40"), sqlDate, sqlTimestamp, sqlTime, true, Arrays.asList("DB2", "Java"));
	}
	
	public void testConditions() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with multiple basic conditions");
		
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
		
		update(Table.PERSON)
		.set(Person.NAME, "Hasan")
		.set(Person.GENDER, 'M')
		.set(Person.AGE, (short)33)
		.set(Person.CPR, 870501236)
		.set(Person.ACCOUNT_NO, 12345678901234l)
		.set(Person.GPA, 3.12f)
		.set(Person.SALARY, 400.5)
		.set(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
		.set(Person.DATE_OF_BIRTH, java.sql.Date.valueOf("2015-10-10"))
		.set(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
		.set(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
		.set(Person.GRADUATED, true)
		.set(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
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
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_date_of_birth = ?, prsn_registration_date_time = ?, prsn_sleep_time = ?, prsn_graduated = ?, prsn_certificates = ? where prsn_id = ? and prsn_name ilike '%' || ? || '%' and prsn_gender = ? and prsn_age > ? and prsn_cpr >= ? and prsn_account_no < ? and prsn_gpa <= ? and prsn_salary between ? and ? and prsn_annual_income in (?, ?) and prsn_date_of_birth not in (?, ?) and prsn_city_id is null and prsn_registration_date_time is not null and prsn_date_of_birth in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) and prsn_date_of_birth not in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) and exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?) and not exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?)");
		isParametersEquals(parameters, "Hasan", 'M', (short)33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"), id, "Hasan", 'M', 32, 870501236, 12345678901235l, 3.12, 400.5, 500.0, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), java.sql.Date.valueOf("2015-12-12"), true, false, "Alkindi", "Bin Hayan");
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with multiple nested conditions");
		
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
		
		update(Table.PERSON)
		.set(Person.NAME, "Hasan")
		.set(Person.GENDER, 'M')
		.set(Person.AGE, (short)33)
		.set(Person.CPR, 870501236)
		.set(Person.ACCOUNT_NO, 12345678901234l)
		.set(Person.GPA, 3.12f)
		.set(Person.SALARY, 400.5)
		.set(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
		.set(Person.DATE_OF_BIRTH, java.sql.Date.valueOf("2015-10-10"))
		.set(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
		.set(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
		.set(Person.GRADUATED, true)
		.set(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
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
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_date_of_birth = ?, prsn_registration_date_time = ?, prsn_sleep_time = ?, prsn_graduated = ?, prsn_certificates = ? where prsn_id = ? and (prsn_name ilike '%' || ? || '%' or prsn_gender = ?) and (prsn_age > ? or prsn_cpr >= ?) and (prsn_account_no < ? or prsn_gpa <= ?) and (prsn_salary between ? and ? or prsn_annual_income in (?, ?)) and prsn_date_of_birth not in (?, ?) and (prsn_city_id is null or prsn_registration_date_time is not null) and (prsn_date_of_birth in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) or prsn_date_of_birth not in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?)) and (exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?) or not exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?))");
		isParametersEquals(parameters, "Hasan", 'M', (short)33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"), id, "Hasan", 'M', 32, 870501236, 12345678901235l, 3.12, 400.5, 500.0, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), java.sql.Date.valueOf("2015-12-12"), true, false, "Alkindi", "Bin Hayan");
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with multiple string conditions");
		
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
		
		update(Table.PERSON)
		.set(Person.NAME, "Hasan")
		.set(Person.GENDER, 'M')
		.set(Person.AGE, (short)33)
		.set(Person.CPR, 870501236)
		.set(Person.ACCOUNT_NO, 12345678901234l)
		.set(Person.GPA, 3.12f)
		.set(Person.SALARY, 400.5)
		.set(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
		.set(Person.DATE_OF_BIRTH, java.sql.Date.valueOf("2015-10-10"))
		.set(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
		.set(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
		.set(Person.GRADUATED, true)
		.set(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
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
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_date_of_birth = ?, prsn_registration_date_time = ?, prsn_sleep_time = ?, prsn_graduated = ?, prsn_certificates = ? where prsn_id = ? and prsn_name ilike '%' || ? || '%' and prsn_gender = ? and prsn_age > ? and prsn_cpr >= ? and prsn_account_no < ? and prsn_gpa <= ? and prsn_salary between ? and ? and prsn_annual_income in (?, ?) and prsn_date_of_birth not in (?, ?)");
		isParametersEquals(parameters, "Hasan", 'M', (short)33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"), id, "Hasan", 'M', (short)32, 870501236, 12345678901235l, 3.12f, 400.5, 500.0, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), java.sql.Date.valueOf("2015-12-12"));
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with multiple math/date conditions");
		
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
		
		update(Table.PERSON)
		.set(Person.NAME, "Hasan")
		.set(Person.GENDER, 'M')
		.set(Person.AGE, (short)33)
		.set(Person.CPR, 870501236)
		.set(Person.ACCOUNT_NO, 12345678901234l)
		.set(Person.GPA, 3.12f)
		.set(Person.SALARY, 400.5)
		.set(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
		.set(Person.DATE_OF_BIRTH, java.sql.Date.valueOf("2015-10-10"))
		.set(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
		.set(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
		.set(Person.GRADUATED, true)
		.set(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
		.where(Person.ID.equal(id))
		.and(Person.AGE.plus(1).minus(1).greater("32"))
		.and(Person.CPR.plus(1).minus(1).greaterEqual("870501236"))
		.and(Person.ACCOUNT_NO.plus(1).minus(1).less("12345678901235"))
		.and(Person.GPA.plus(1).minus(1).lessEqual("3.12"))
		.and(Person.SALARY.plus(1).minus(1).between("400.5", "500.0"))
		.and(Person.ANNUAL_INCOME.plus(1).minus(1).in("500.40", "600.60"))
		.and(Person.DATE_OF_BIRTH.plusYears(1).minusYears(1).notIn("11-11-2015", "12-12-2015"));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_date_of_birth = ?, prsn_registration_date_time = ?, prsn_sleep_time = ?, prsn_graduated = ?, prsn_certificates = ? where prsn_id = ? and prsn_age + ? - ? > ? and prsn_cpr + ? - ? >= ? and prsn_account_no + ? - ? < ? and prsn_gpa + ? - ? <= ? and prsn_salary + ? - ? between ? and ? and prsn_annual_income + ? - ? in (?, ?) and prsn_date_of_birth + interval '1 years' - interval '1 years' not in (?, ?)");
		isParametersEquals(parameters, "Hasan", 'M', (short)33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"), id, 1, 1, (short)32, 1, 1, 870501236, 1, 1, 12345678901235l, 1, 1, 3.12f, 1, 1, 400.5, 500.0, 1, 1, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), java.sql.Date.valueOf("2015-12-12"));
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Update Query with multiple conditions with util date");
		
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
		
		update(Table.PERSON)
		.set(Person.NAME, "Hasan")
		.set(Person.GENDER, 'M')
		.set(Person.AGE, (short)33)
		.set(Person.CPR, 870501236)
		.set(Person.ACCOUNT_NO, 12345678901234l)
		.set(Person.GPA, 3.12f)
		.set(Person.SALARY, 400.5)
		.set(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
		.set(Person.DATE_OF_BIRTH, java.sql.Date.valueOf("2015-10-10"))
		.set(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
		.set(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
		.set(Person.GRADUATED, true)
		.set(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
		.where(Person.ID.equal(id))
		.and(Person.DATE_OF_BIRTH.equal(date))
		.and(Person.REGISTRATION_DATE_TIME.equal(date))
		.and(Person.SLEEP_TIME.equal(date));
		
		query = getUpdateQuery(parameters);
		
		executeUpdate();
		
		isQueryEqual(query, "update public.person set prsn_name = ?, prsn_gender = ?, prsn_age = ?, prsn_cpr = ?, prsn_account_no = ?, prsn_gpa = ?, prsn_salary = ?, prsn_annual_income = ?, prsn_date_of_birth = ?, prsn_registration_date_time = ?, prsn_sleep_time = ?, prsn_graduated = ?, prsn_certificates = ? where prsn_id = ? and prsn_date_of_birth = ? and prsn_registration_date_time = ? and prsn_sleep_time = ?");
		isParametersEquals(parameters, "Hasan", 'M', (short)33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"), id, sqlDate, sqlTimestamp, sqlTime);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
	}
	
	public static void main(String[] args) throws Exception {
		
		try(Connection connection = GeneratorUtils.getConnection()){
			
			TestUpdateQuery testUpdateQuery = new TestUpdateQuery(connection);
			
			testUpdateQuery.testNullValues();
			
			testUpdateQuery.testEmptyValues();
			
			testUpdateQuery.testZeroValues();
			
			testUpdateQuery.testStringZeroValues();
			
			testUpdateQuery.testValidValues();
			
			testUpdateQuery.testValidStringValues();
			
			testUpdateQuery.testUtilDateValues();
			
			testUpdateQuery.testArrayVariableAurgementsValues();
			
			testUpdateQuery.testUpdateColumn();
			
			testUpdateQuery.testUpdateColumnWithDateMathFunction();
			
			testUpdateQuery.testConditions();
			
			testUpdateQuery.writeWorkbook();
		}
	}
}
