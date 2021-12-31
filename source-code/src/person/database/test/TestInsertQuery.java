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

public class TestInsertQuery extends TestQuery {

	public TestInsertQuery(Connection connection) {
		
		super(connection);
		
		excelUtils = new ExcelUtils("Test Insert Query");
		
		excelUtils.addHeaderValue("#");
		excelUtils.addHeaderValue("Test Case");
		excelUtils.addHeaderValue("Query Correctness");
		excelUtils.addHeaderValue("Parameters Correctness");
		excelUtils.addHeaderValue("Database Values Correctness");
	}
	
	public void testNullValues() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with values method with null values");
		
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
		
		insertInto(Table.PERSON)
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
		.values(Person.ACTIVE, true);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_active) values (?)");
		isParametersEquals(parameters, true);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 40100162, 12345678912345l, 3.14f, 666.55, new BigDecimal("9000.55"), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56.0"), Time.valueOf("10:10:10"), true, Arrays.asList("C++", "C#"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesNull method with null values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesNull(Person.NAME, personDTO.getName())
		.valuesNull(Person.GENDER, personDTO.getGender())
		.valuesNull(Person.AGE, personDTO.getAge())
		.valuesNull(Person.CPR, personDTO.getCpr())
		.valuesNull(Person.ACCOUNT_NO, personDTO.getAccountNo())
		.valuesNull(Person.GPA, personDTO.getGpa())
		.valuesNull(Person.SALARY, personDTO.getSalary())
		.valuesNull(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
		.valuesNull(Person.DATE_OF_BIRTH, personDTO.getDateOfBirth())
		.valuesNull(Person.REGISTRATION_DATE_TIME, personDTO.getRegistrationDateTime())
		.valuesNull(Person.SLEEP_TIME, personDTO.getSleepTime())
		.valuesNull(Person.GRADUATED, personDTO.getGraduated())
		.valuesNull(Person.CERTIFICATES, personDTO.getCertificates())
		.valuesNull(Person.ACTIVE, true);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, prsn_active) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, null, null, null, null, null, null, null, null, null, null, null, null, null, true);
		isDatabaseValuesEquals(id, null, null, null, null, null, null, null, null, null, null, null, null, null);
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesZero method with null values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesZero(Person.NAME, personDTO.getName())
		.valuesZero(Person.AGE, personDTO.getAge())
		.valuesZero(Person.CPR, personDTO.getCpr())
		.valuesZero(Person.ACCOUNT_NO, personDTO.getAccountNo())
		.valuesZero(Person.GPA, personDTO.getGpa())
		.valuesZero(Person.SALARY, personDTO.getSalary())
		.valuesZero(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
		.values(Person.ACTIVE, true);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();

		isQueryEqual(query, "insert into public.person (prsn_name, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_active) values (?, ?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, 0, 0, 0, 0, 0, 0, 0, true);
		isDatabaseValuesEquals(id, "0", "M", 0, 0, 0l, 0.0f, 0.0, new BigDecimal(0), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56.0"), Time.valueOf("10:10:10"), true, Arrays.asList("C++", "C#"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesExact method with null values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesExact(Person.NAME, personDTO.getName())
		.valuesExact(Person.AGE, personDTO.getAge())
		.valuesExact(Person.CPR, personDTO.getCpr())
		.valuesExact(Person.ACCOUNT_NO, personDTO.getAccountNo())
		.valuesExact(Person.GPA, personDTO.getGpa())
		.valuesExact(Person.SALARY, personDTO.getSalary())
		.valuesExact(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
		.values(Person.ACTIVE, true);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_active) values (?, ?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, null, null, null, null, null, null, null, true);
		isDatabaseValuesEquals(id, null, "M", null, null, null, null, null, null, Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56.0"), Time.valueOf("10:10:10"), true, Arrays.asList("C++", "C#"));
	}
	
	public void testEmptyValues() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with values method with empty values");
		
		insertInto(Table.PERSON)
		.values(Person.NAME, "")
		.values(Person.GENDER, "")
		.values(Person.AGE, "")
		.values(Person.CPR, "")
		.values(Person.ACCOUNT_NO, "")
		.values(Person.GPA, "")
		.values(Person.SALARY, "")
		.values(Person.ANNUAL_INCOME, "")
		.values(Person.DATE_OF_BIRTH, "")
		.values(Person.REGISTRATION_DATE_TIME, "")
		.values(Person.SLEEP_TIME, "")
		.values(Person.GRADUATED, "")
		.values(Person.CERTIFICATES, "")
		.values(Person.ACTIVE, true);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_active) values (?)");
		isParametersEquals(parameters, true);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 40100162, 12345678912345l, 3.14f, 666.55, new BigDecimal("9000.55"), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56.0"), Time.valueOf("10:10:10"), true, Arrays.asList("C++", "C#"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesNull method with empty values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesNull(Person.NAME, "")
		.valuesNull(Person.GENDER, "")
		.valuesNull(Person.AGE, "")
		.valuesNull(Person.CPR, "")
		.valuesNull(Person.ACCOUNT_NO, "")
		.valuesNull(Person.GPA, "")
		.valuesNull(Person.SALARY, "")
		.valuesNull(Person.ANNUAL_INCOME, "")
		.valuesNull(Person.DATE_OF_BIRTH, "")
		.valuesNull(Person.REGISTRATION_DATE_TIME, "")
		.valuesNull(Person.SLEEP_TIME, "")
		.valuesNull(Person.GRADUATED, "")
		.valuesNull(Person.CERTIFICATES, "")
		.values(Person.ACTIVE, true);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, prsn_active) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, null, null, null, null, null, null, null, null, null, null, null, null, null, true);
		isDatabaseValuesEquals(id, null, null, null, null, null, null, null, null, null, null, null, null, null);
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesZero method with empty values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesZero(Person.NAME, "")
		.valuesZero(Person.GENDER, "")
		.valuesZero(Person.AGE, "")
		.valuesZero(Person.CPR, "")
		.valuesZero(Person.ACCOUNT_NO, "")
		.valuesZero(Person.GPA, "")
		.valuesZero(Person.SALARY, "")
		.valuesZero(Person.ANNUAL_INCOME, "")
		.values(Person.ACTIVE, true);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();

		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_active) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, 0, 0, 0, 0, 0, 0, 0, 0, true);
		isDatabaseValuesEquals(id, "0", "0", 0, 0, 0l, 0.0f, 0.0, new BigDecimal(0), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56.0"), Time.valueOf("10:10:10"), true, Arrays.asList("C++", "C#"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesExact method with empty values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesExact(Person.NAME, "")
		.valuesExact(Person.GENDER, "")
		.valuesExact(Person.AGE, "")
		.valuesExact(Person.CPR, "")
		.valuesExact(Person.ACCOUNT_NO, "")
		.valuesExact(Person.GPA, "")
		.valuesExact(Person.SALARY, "")
		.valuesExact(Person.ANNUAL_INCOME, "")
		.valuesExact(Person.DATE_OF_BIRTH, "")
		.valuesExact(Person.REGISTRATION_DATE_TIME, "")
		.valuesExact(Person.SLEEP_TIME, "")
		.valuesExact(Person.GRADUATED, "")
		.valuesExact(Person.CERTIFICATES, "")
		.values(Person.ACTIVE, true);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, prsn_active) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, null, null, null, null, null, null, null, null, null, null, null, null, null, true);
		isDatabaseValuesEquals(id, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	
	public void testZeroValues() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with values method with zero values");
		
		insertInto(Table.PERSON)
		.values(Person.NAME, 0)
		.values(Person.GENDER, 0)
		.values(Person.AGE, 0)
		.values(Person.CPR, 0)
		.values(Person.ACCOUNT_NO, 0)
		.values(Person.GPA, 0)
		.values(Person.SALARY, 0)
		.values(Person.ANNUAL_INCOME, 0)
		.values(Person.ACTIVE, true);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_active) values (?)");
		isParametersEquals(parameters, true);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 40100162, 12345678912345l, 3.14f, 666.55, new BigDecimal("9000.55"), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56.0"), Time.valueOf("10:10:10"), true, Arrays.asList("C++", "C#"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesNull method with zero values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesNull(Person.NAME, 0)
		.valuesNull(Person.GENDER, 0)
		.valuesNull(Person.AGE, 0)
		.valuesNull(Person.CPR, 0)
		.valuesNull(Person.ACCOUNT_NO, 0)
		.valuesNull(Person.GPA, 0)
		.valuesNull(Person.SALARY, 0)
		.valuesNull(Person.ANNUAL_INCOME, 0)
		.values(Person.ACTIVE, true);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_active) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, null, null, null, null, null, null, null, null, true);
		isDatabaseValuesEquals(id, null, null, null, null, null, null, null, null, Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56.0"), Time.valueOf("10:10:10"), true, Arrays.asList("C++", "C#"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesZero method with zero values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesZero(Person.NAME, 0)
		.valuesZero(Person.GENDER, 0)
		.valuesZero(Person.AGE, 0)
		.valuesZero(Person.CPR, 0)
		.valuesZero(Person.ACCOUNT_NO, 0)
		.valuesZero(Person.GPA, 0)
		.valuesZero(Person.SALARY, 0)
		.valuesZero(Person.ANNUAL_INCOME, 0)
		.values(Person.ACTIVE, true);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();

		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_active) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, 0, 0, 0, 0, 0, 0, 0, 0, true);
		isDatabaseValuesEquals(id, "0", "0", 0, 0, 0l, 0.0f, 0.0, new BigDecimal(0), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56.0"), Time.valueOf("10:10:10"), true, Arrays.asList("C++", "C#"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesExact method with zero values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesExact(Person.NAME, 0)
		.valuesExact(Person.GENDER, 0)
		.valuesExact(Person.AGE, 0)
		.valuesExact(Person.CPR, 0)
		.valuesExact(Person.ACCOUNT_NO, 0)
		.valuesExact(Person.GPA, 0)
		.valuesExact(Person.SALARY, 0)
		.valuesExact(Person.ANNUAL_INCOME, 0)
		.values(Person.ACTIVE, true);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_active) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, 0, 0, 0, 0, 0, 0, 0, 0, true);
		isDatabaseValuesEquals(id, "0", "0", 0, 0, 0l, 0.0f, 0.0, new BigDecimal(0), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56.0"), Time.valueOf("10:10:10"), true, Arrays.asList("C++", "C#"));
	}
	
	public void testStringZeroValues() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with values method with string zero values");
		
		insertInto(Table.PERSON)
		.values(Person.NAME, "0")
		.values(Person.GENDER, "0")
		.values(Person.AGE, "0")
		.values(Person.CPR, "0")
		.values(Person.ACCOUNT_NO, "0")
		.values(Person.GPA, "0")
		.values(Person.SALARY, "0")
		.values(Person.ANNUAL_INCOME, "0")
		.values(Person.ACTIVE, true);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_active) values (?)");
		isParametersEquals(parameters, true);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 40100162, 12345678912345l, 3.14f, 666.55, new BigDecimal("9000.55"), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56.0"), Time.valueOf("10:10:10"), true, Arrays.asList("C++", "C#"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesNull method with string zero values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesNull(Person.NAME, "0")
		.valuesNull(Person.GENDER, "0")
		.valuesNull(Person.AGE, "0")
		.valuesNull(Person.CPR, "0")
		.valuesNull(Person.ACCOUNT_NO, "0")
		.valuesNull(Person.GPA, "0")
		.valuesNull(Person.SALARY, "0")
		.valuesNull(Person.ANNUAL_INCOME, "0")
		.values(Person.ACTIVE, true);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_active) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, null, null, null, null, null, null, null, null, true);
		isDatabaseValuesEquals(id, null, null, null, null, null, null, null, null, Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56.0"), Time.valueOf("10:10:10"), true, Arrays.asList("C++", "C#"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesZero method with string zero values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesZero(Person.NAME, "0")
		.valuesZero(Person.GENDER, "0")
		.valuesZero(Person.AGE, "0")
		.valuesZero(Person.CPR, "0")
		.valuesZero(Person.ACCOUNT_NO, "0")
		.valuesZero(Person.GPA, "0")
		.valuesZero(Person.SALARY, "0")
		.valuesZero(Person.ANNUAL_INCOME, "0")
		.values(Person.ACTIVE, true);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();

		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_active) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, 0, 0, 0, 0, 0, 0, 0, 0, true);
		isDatabaseValuesEquals(id, "0", "0", 0, 0, 0l, 0.0f, 0.0, new BigDecimal(0), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56.0"), Time.valueOf("10:10:10"), true, Arrays.asList("C++", "C#"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesExact method with string zero values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesExact(Person.NAME, "0")
		.valuesExact(Person.GENDER, "0")
		.valuesExact(Person.AGE, "0")
		.valuesExact(Person.CPR, "0")
		.valuesExact(Person.ACCOUNT_NO, "0")
		.valuesExact(Person.GPA, "0")
		.valuesExact(Person.SALARY, "0")
		.valuesExact(Person.ANNUAL_INCOME, "0")
		.values(Person.ACTIVE, true);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_active) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, "0", '0', (short)0, 0, 0l, 0.0f, 0.0, new BigDecimal(0), true);
		isDatabaseValuesEquals(id, "0", "0", 0, 0, 0l, 0.0f, 0.0, new BigDecimal(0), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56.0"), Time.valueOf("10:10:10"), true, Arrays.asList("C++", "C#"));
	}
	
	public void testValidValues() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with values method with valid values");
		
		insertInto(Table.PERSON)
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
		.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"));
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, "Hasan", 'M', (short)33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesNull method with valid values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesNull(Person.NAME, "Hasan")
		.valuesNull(Person.GENDER, 'M')
		.valuesNull(Person.AGE, 33)
		.valuesNull(Person.CPR, 870501236)
		.valuesNull(Person.ACCOUNT_NO, 12345678901234l)
		.valuesNull(Person.GPA, 3.12)
		.valuesNull(Person.SALARY, 400.5)
		.valuesNull(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
		.valuesNull(Person.DATE_OF_BIRTH, java.sql.Date.valueOf("2015-10-10"))
		.valuesNull(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
		.valuesNull(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
		.valuesNull(Person.GRADUATED, true)
		.valuesNull(Person.CERTIFICATES, Arrays.asList("DB2", "Java"));
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, "Hasan", 'M', 33, 870501236, 12345678901234l, 3.12, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesZero method with valid values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesZero(Person.NAME, "Hasan")
		.valuesZero(Person.AGE, 33)
		.valuesZero(Person.CPR, 870501236)
		.valuesZero(Person.ACCOUNT_NO, 12345678901234l)
		.valuesZero(Person.GPA, 3.12)
		.valuesZero(Person.SALARY, 400.5)
		.valuesZero(Person.ANNUAL_INCOME, new BigDecimal("500.40"));
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();

		isQueryEqual(query, "insert into public.person (prsn_name, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income) values (?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, "Hasan", 33, 870501236, 12345678901234l, 3.12, 400.5, new BigDecimal("500.40"));
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56"), Time.valueOf("10:10:10"), true, Arrays.asList("C++","C#"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesExact method with valid values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesExact(Person.NAME, "Hasan")
		.valuesExact(Person.AGE, 33)
		.valuesExact(Person.CPR, 870501236)
		.valuesExact(Person.ACCOUNT_NO, 12345678901234l)
		.valuesExact(Person.GPA, 3.12)
		.valuesExact(Person.SALARY, 400.5)
		.valuesExact(Person.ANNUAL_INCOME, new BigDecimal("500.40"));
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income) values (?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, "Hasan", 33, 870501236, 12345678901234l, 3.12, 400.5, new BigDecimal("500.40"));
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56"), Time.valueOf("10:10:10"), true, Arrays.asList("C++","C#"));
	}
	
	public void testValidStringValues() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with values method with valid string values");
		
		insertInto(Table.PERSON)
		.values(Person.NAME, "Hasan")
		.values(Person.GENDER, "M")
		.values(Person.AGE, "33")
		.values(Person.CPR, "870501236")
		.values(Person.ACCOUNT_NO, "12345678901234")
		.values(Person.GPA, "3.12")
		.values(Person.SALARY, "400.5")
		.values(Person.ANNUAL_INCOME, "500.40")
		.values(Person.DATE_OF_BIRTH, "10-10-2015")
		.values(Person.REGISTRATION_DATE_TIME, "10-10-2000 10:10:10")
		.values(Person.SLEEP_TIME, "10:10:10")
		.values(Person.GRADUATED, "true")
		.values(Person.CERTIFICATES, "DB2,Java");
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, "Hasan", 'M', (short)33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesNull method with valid string values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesNull(Person.NAME, "Hasan")
		.valuesNull(Person.GENDER, "M")
		.valuesNull(Person.AGE, "33")
		.valuesNull(Person.CPR, "870501236")
		.valuesNull(Person.ACCOUNT_NO, "12345678901234")
		.valuesNull(Person.GPA, "3.12")
		.valuesNull(Person.SALARY, "400.5")
		.valuesNull(Person.ANNUAL_INCOME, "500.40")
		.valuesNull(Person.DATE_OF_BIRTH, "10-10-2015")
		.valuesNull(Person.REGISTRATION_DATE_TIME, "10-10-2000 10:10:10")
		.valuesNull(Person.SLEEP_TIME, "10:10:10")
		.valuesNull(Person.GRADUATED, "true")
		.valuesNull(Person.CERTIFICATES, "DB2,Java");
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, "Hasan", 'M', (short)33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesZero method with valid string values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesZero(Person.NAME, "Hasan")
		.valuesZero(Person.AGE, "33")
		.valuesZero(Person.CPR, "870501236")
		.valuesZero(Person.ACCOUNT_NO, "12345678901234")
		.valuesZero(Person.GPA, "3.12")
		.valuesZero(Person.SALARY, "400.5")
		.valuesZero(Person.ANNUAL_INCOME, "500.40");
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();

		isQueryEqual(query, "insert into public.person (prsn_name, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income) values (?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, "Hasan", (short)33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"));
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56"), Time.valueOf("10:10:10"), true, Arrays.asList("C++","C#"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesExact method with valid string values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesExact(Person.NAME, "Hasan")
		.valuesExact(Person.AGE, "33")
		.valuesExact(Person.CPR, "870501236")
		.valuesExact(Person.ACCOUNT_NO, "12345678901234")
		.valuesExact(Person.GPA, "3.12")
		.valuesExact(Person.SALARY, "400.5")
		.valuesExact(Person.ANNUAL_INCOME, "500.40");
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income) values (?, ?, ?, ?, ?, ?, ?)");
		isParametersEquals(parameters, "Hasan", (short)33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"));
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56"), Time.valueOf("10:10:10"), true, Arrays.asList("C++","C#"));
	}
	
	public void testUtilDateValues() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with values method with util date values");
		
		java.util.Date date = new java.util.Date();
		
		Date sqlDate = new Date(date.getTime());
		Timestamp sqlTimestamp = new Timestamp(date.getTime());
		Time sqlTime = new Time(date.getTime());
		
		insertInto(Table.PERSON)
		.values(Person.DATE_OF_BIRTH, date)
		.values(Person.REGISTRATION_DATE_TIME, date)
		.values(Person.SLEEP_TIME, date);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time) values (?, ?, ?)");
		isParametersEquals(parameters, sqlDate, sqlTimestamp, sqlTime);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 40100162, 12345678912345l, 3.14f, 666.55, new BigDecimal("9000.55"), sqlDate, sqlTimestamp, sqlTime, true, Arrays.asList("C++", "C#"));
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with valuesNull method with util date values");
		
		parameters.clear();
		
		insertInto(Table.PERSON)
		.valuesNull(Person.DATE_OF_BIRTH, date)
		.valuesNull(Person.REGISTRATION_DATE_TIME, date)
		.valuesNull(Person.SLEEP_TIME, date);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time) values (?, ?, ?)");
		isParametersEquals(parameters, sqlDate, sqlTimestamp, sqlTime);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 40100162, 12345678912345l, 3.14f, 666.55, new BigDecimal("9000.55"), sqlDate, sqlTimestamp, sqlTime, true, Arrays.asList("C++", "C#"));
	}
	
	public void testArrayVariableAurgementsValues() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with values method with variable aurgements array values");
		
		insertInto(Table.PERSON)
		.values(Person.CERTIFICATES, "DB2", "Java");
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_certificates) values (?)");
		isParametersEquals(parameters, Arrays.asList("DB2","Java"));
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 40100162, 12345678912345l, 3.14f, 666.55, new BigDecimal("9000.55"), Date.valueOf("2010-10-10"), Timestamp.valueOf("2010-10-10 05:05:56.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2", "Java"));
	}
	
	public void testInsertColumn() throws Exception {
		
		java.util.Date date = new java.util.Date();
		
		Date sqlDate = new Date(date.getTime());
		Timestamp sqlTimestamp = new Timestamp(date.getTime());
		Time sqlTime = new Time(date.getTime());
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with values method with column");
		
		insertInto(Table.PERSON)
		.values(Person.DATE_OF_BIRTH, CURRENT_DATE)
		.values(Person.REGISTRATION_DATE_TIME, CURRENT_TIMESTAMP)
		.values(Person.SLEEP_TIME, CURRENT_TIME);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time) values (current_date, current_timestamp, current_time)");
		isParametersEquals(parameters);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 40100162, 12345678912345l, 3.14f, 666.55, new BigDecimal("9000.55"), sqlDate, sqlTimestamp, sqlTime, true, Arrays.asList("C++", "C#"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with values method with column with one date function");
		
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
		
		insertInto(Table.PERSON)
		.values(Person.DATE_OF_BIRTH, CURRENT_DATE.plusDays(1))
		.values(Person.REGISTRATION_DATE_TIME, CURRENT_TIMESTAMP.plusHours(2))
		.values(Person.SLEEP_TIME, CURRENT_TIME.plusMinutes(5));
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time) values (current_date + interval '1 days', current_timestamp + interval '2 hours', current_time + interval '5 minutes')");
		isParametersEquals(parameters);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 40100162, 12345678912345l, 3.14f, 666.55, new BigDecimal("9000.55"), sqlDate, sqlTimestamp, sqlTime, true, Arrays.asList("C++", "C#"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with values method with column with multiple date functions");
		
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
		
		insertInto(Table.PERSON)
		.values(Person.DATE_OF_BIRTH, CURRENT_DATE.plusDays(1).minusMonths(3))
		.values(Person.REGISTRATION_DATE_TIME, CURRENT_TIMESTAMP.plusHours(2).minusDays(4))
		.values(Person.SLEEP_TIME, CURRENT_TIME.plusMinutes(5).minusHours(6));
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time) values (current_date + interval '1 days' - interval '3 months', current_timestamp + interval '2 hours' - interval '4 days', current_time + interval '5 minutes' - interval '6 hours')");
		isParametersEquals(parameters);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 40100162, 12345678912345l, 3.14f, 666.55, new BigDecimal("9000.55"), sqlDate, sqlTimestamp, sqlTime, true, Arrays.asList("C++", "C#"));
	}
	
	public void testInsertColumnWithDateMathFunction() throws Exception {
		
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
		excelUtils.addRecordValue("Test Insert Query with values method with column with date part functions");
		
		insertInto(Table.PERSON)
		.values(Person.AGE, year(CURRENT_DATE))
		.values(Person.CPR, month(CURRENT_DATE))
		.values(Person.ACCOUNT_NO, day(CURRENT_DATE))
		.values(Person.GPA, hour(CURRENT_TIMESTAMP))
		.values(Person.SALARY, minute(CURRENT_TIMESTAMP))
		.values(Person.DATE_OF_BIRTH, date(CURRENT_TIMESTAMP))
		.values(Person.REGISTRATION_DATE_TIME, CURRENT_TIMESTAMP)
		.values(Person.SLEEP_TIME, CURRENT_TIME);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time) values (date_part('year', current_date), date_part('month', current_date), date_part('day', current_date), date_part('hour', current_timestamp), date_part('minute', current_timestamp), date(current_timestamp), current_timestamp, current_time)");
		isParametersEquals(parameters);
		isDatabaseValuesEquals(id, "Hasan", "M", year, month, day, hour, minute, new BigDecimal("9000.55"), sqlDate, sqlTimestamp, sqlTime, true, Arrays.asList("C++", "C#"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query with values method with column with date part/math functions");
		
		insertInto(Table.PERSON)
		.values(Person.AGE, year(CURRENT_DATE).plus(1))
		.values(Person.CPR, month(CURRENT_DATE).plus(2))
		.values(Person.ACCOUNT_NO, day(CURRENT_DATE).plus(3))
		.values(Person.GPA, hour(CURRENT_TIMESTAMP).minus(1))
		.values(Person.SALARY, minute(CURRENT_TIMESTAMP).minus(2))
		.values(Person.DATE_OF_BIRTH, date(CURRENT_TIMESTAMP))
		.values(Person.REGISTRATION_DATE_TIME, CURRENT_TIMESTAMP)
		.values(Person.SLEEP_TIME, CURRENT_TIME);
		
		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		year+=1;
		month+=2;
		day+=3;
		hour-=1;
		minute-=2;
		
		isQueryEqual(query, "insert into public.person (prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time) values (date_part('year', current_date) + ?, date_part('month', current_date) + ?, date_part('day', current_date) + ?, date_part('hour', current_timestamp) - ?, date_part('minute', current_timestamp) - ?, date(current_timestamp), current_timestamp, current_time)");
		isParametersEquals(parameters, 1, 2, 3, 1, 2);
		isDatabaseValuesEquals(id, "Hasan", "M", year, month, day, hour, minute, new BigDecimal("9000.55"), sqlDate, sqlTimestamp, sqlTime, true, Arrays.asList("C++", "C#"));
	}
	
	public void testInsertFromSelect() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query from Select Query with one condition");
		
		int sourceId = insertInto(Table.PERSON)
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
		
		insertInto(Table.PERSON, Person.NAME, Person.GENDER, Person.AGE,
							Person.CPR, Person.ACCOUNT_NO, Person.GPA,
							Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH,
							Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME, 
							Person.GRADUATED, Person.CERTIFICATES)
		.from(new Query().select(Person.NAME, Person.GENDER, Person.AGE)
						.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
						.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
						.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
						.select(Person.GRADUATED, Person.CERTIFICATES)
						.from(Table.PERSON).where(Person.ID.equal(sourceId)));

		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates) select prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ?");
		isParametersEquals(parameters, sourceId);
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query from Select Query with multiple basic conditions");
		
		insertInto(Table.PERSON, Person.NAME, Person.GENDER, Person.AGE,
							Person.CPR, Person.ACCOUNT_NO, Person.GPA,
							Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH,
							Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME, 
							Person.GRADUATED, Person.CERTIFICATES)
		.from(new Query().select(Person.NAME, Person.GENDER, Person.AGE)
						.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
						.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
						.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
						.select(Person.GRADUATED, Person.CERTIFICATES)
						.from(Table.PERSON)
						.where(Person.ID.equal(sourceId))
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
									.and(Doctor.HOSPITAL.equal("Bin Hayan"))))
							);

		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates) select prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? and prsn_name ilike '%' || ? || '%' and prsn_gender = ? and prsn_age > ? and prsn_cpr >= ? and prsn_account_no < ? and prsn_gpa <= ? and prsn_salary between ? and ? and prsn_annual_income in (?, ?) and prsn_date_of_birth not in (?, ?) and prsn_city_id is null and prsn_registration_date_time is not null and prsn_date_of_birth in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) and prsn_date_of_birth not in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) and exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?) and not exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?)");
		isParametersEquals(parameters, sourceId, "Hasan", 'M', 32, 870501236, 12345678901235l, 3.12, 400.5, 500.0, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), java.sql.Date.valueOf("2015-12-12"), true, false, "Alkindi", "Bin Hayan");
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Insert Query from Select Query with multiple nested conditions");
		
		insertInto(Table.PERSON, Person.NAME, Person.GENDER, Person.AGE,
							Person.CPR, Person.ACCOUNT_NO, Person.GPA,
							Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH,
							Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME, 
							Person.GRADUATED, Person.CERTIFICATES)
		.from(new Query().select(Person.NAME, Person.GENDER, Person.AGE)
						.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
						.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
						.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
						.select(Person.GRADUATED, Person.CERTIFICATES)
						.from(Table.PERSON)
						.where(Person.ID.equal(sourceId))
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
									.and(Doctor.HOSPITAL.equal("Bin Hayan")))))
							);

		query = getInsertQuery(parameters);
		
		id = executeInsert();
		
		isQueryEqual(query, "insert into public.person (prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates) select prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? and (prsn_name ilike '%' || ? || '%' or prsn_gender = ?) and (prsn_age > ? or prsn_cpr >= ?) and (prsn_account_no < ? or prsn_gpa <= ?) and (prsn_salary between ? and ? or prsn_annual_income in (?, ?)) and prsn_date_of_birth not in (?, ?) and (prsn_city_id is null or prsn_registration_date_time is not null) and (prsn_date_of_birth in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) or prsn_date_of_birth not in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?)) and (exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?) or not exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?))");
		isParametersEquals(parameters, sourceId, "Hasan", 'M', 32, 870501236, 12345678901235l, 3.12, 400.5, 500.0, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), java.sql.Date.valueOf("2015-12-12"), true, false, "Alkindi", "Bin Hayan");
		isDatabaseValuesEquals(id, "Hasan", "M", 33, 870501236, 12345678901234l, 3.12f, 400.5, new BigDecimal("500.40"), Date.valueOf("2015-10-10"), Timestamp.valueOf("2000-10-10 10:10:10.0"), Time.valueOf("10:10:10"), true, Arrays.asList("DB2","Java"));
	}
	
	public static void main(String[] args) throws Exception {
		
		try(Connection connection = GeneratorUtils.getConnection()){
			
			TestInsertQuery testInsertQuery = new TestInsertQuery(connection);
			
			testInsertQuery.testNullValues();
			
			testInsertQuery.testEmptyValues();
			
			testInsertQuery.testZeroValues();
			
			testInsertQuery.testStringZeroValues();
			
			testInsertQuery.testValidValues();
			
			testInsertQuery.testValidStringValues();
			
			testInsertQuery.testUtilDateValues();
			
			testInsertQuery.testArrayVariableAurgementsValues();
			
			testInsertQuery.testInsertColumn();
			
			testInsertQuery.testInsertColumnWithDateMathFunction();
			
			testInsertQuery.testInsertFromSelect();
			
			testInsertQuery.writeWorkbook();
		}
	}
}
