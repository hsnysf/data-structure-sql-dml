package person.database.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import person.column.Address;
import person.column.City;
import person.column.Company;
import person.column.Country;
import person.column.Doctor;
import person.column.Person;
import person.column.School;
import person.column.UniqueDateOfBirth;
import person.database.Query;
import person.database.Table;
import person.dto.AddressDTO;
import person.dto.CityDTO;
import person.dto.CompanyDTO;
import person.dto.CountryDTO;
import person.dto.PersonDTO;
import person.dto.SchoolDTO;
import person.generator.GeneratorUtils;

public class TestSelectQuery extends TestQuery {

	public TestSelectQuery(Connection connection) {
		
		super(connection);
		
		excelUtils = new ExcelUtils("Test Select Query");
		
		excelUtils.addHeaderValue("#");
		excelUtils.addHeaderValue("Test Case");
		excelUtils.addHeaderValue("Query Correctness");
		excelUtils.addHeaderValue("Parameters Correctness");
		excelUtils.addHeaderValue("Database Values Correctness");
	}
	
	public void testSelectClause() throws Exception {

		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query basic select clause");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.values(Person.CITY_ID, 1)
			.values(Person.SCHOOL_ID, 1)
			.values(Person.COMPANY_ID, 1)
			.values(Person.COUNTRY_ID, 1)
			.values(Person.HOME_ADDRESS_ID, 1)
			.values(Person.WORK_ADDRESS_ID, 2)
			.executeInsert();
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(Person.CITY_ID, Person.SCHOOL_ID)
		.select(Person.COMPANY_ID, Person.COUNTRY_ID)
		.select(Person.HOME_ADDRESS_ID, Person.WORK_ADDRESS_ID)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, prsn_city_id, prsn_school_id, prsn_company_id, prsn_country_id, prsn_home_address_id, prsn_work_address_id from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(record, 
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")),
						entry(Person.CITY_ID, 1),
						entry(Person.SCHOOL_ID, 1),
						entry(Person.COMPANY_ID, 1),
						entry(Person.COUNTRY_ID, 1),
						entry(Person.HOME_ADDRESS_ID, 1),
						entry(Person.WORK_ADDRESS_ID, 2));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query select clause with alias");
		
		select(Person.ID.as("person_id"), Person.NAME.as("person_name"), 
				Person.GENDER.as("person_gender"), Person.AGE.as("person_age"))
		.select(Person.CPR.as("person_cpr"), Person.ACCOUNT_NO.as("person_account_no"), 
				Person.GPA.as("person_gpa"))
		.select(Person.SALARY.as("person_salary"), Person.ANNUAL_INCOME.as("person_annual_income"), 
				Person.DATE_OF_BIRTH.as("person_date_of_birth"))
		.select(Person.REGISTRATION_DATE_TIME.as("person_registration_date_time"), Person.SLEEP_TIME.as("person_sleep_time"))
		.select(Person.GRADUATED.as("person_graduated"), Person.CERTIFICATES.as("person_certificates"))
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id as person_id, prsn_name as person_name, prsn_gender as person_gender, prsn_age as person_age, prsn_cpr as person_cpr, prsn_account_no as person_account_no, prsn_gpa as person_gpa, prsn_salary as person_salary, prsn_annual_income as person_annual_income, prsn_date_of_birth as person_date_of_birth, prsn_registration_date_time as person_registration_date_time, prsn_sleep_time as person_sleep_time, prsn_graduated as person_graduated, prsn_certificates as person_certificates from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(record, 
				entry(Person.ID, id),
				entry(Person.NAME, "Hasan"),
				entry(Person.GENDER, 'M'),
				entry(Person.AGE, (short)33),
				entry(Person.CPR, 870501236),
				entry(Person.ACCOUNT_NO, 12345678901234l),
				entry(Person.GPA, 3.12f),
				entry(Person.SALARY, 400.5),
				entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
				entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
				entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
				entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
				entry(Person.GRADUATED, true),
				entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query select clause with one math/date");
		
		select(Person.AGE.plus(1))
		.select(Person.CPR.minus(2), 
				Person.ACCOUNT_NO.divide(Person.ACCOUNT_NO), 
				Person.GPA.multiply(Person.GPA))
		.select(Person.SALARY.plus(Person.SALARY), 
				Person.ANNUAL_INCOME.divide(Person.ANNUAL_INCOME), 
				Person.DATE_OF_BIRTH.plusMinutes(1))
		.select(Person.REGISTRATION_DATE_TIME.minusMinutes(3), 
				Person.SLEEP_TIME.minusHours(3))
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_age + ? as prsn_age_plus, prsn_cpr - ? as prsn_cpr_minus, prsn_account_no / prsn_account_no as prsn_account_no_divide, prsn_gpa * prsn_gpa as prsn_gpa_multiply, prsn_salary + prsn_salary as prsn_salary_plus, prsn_annual_income / prsn_annual_income as prsn_annual_income_divide, prsn_date_of_birth + interval '1 minutes' as prsn_date_of_birth_plus, prsn_registration_date_time - interval '3 minutes' as prsn_registration_date_time_minus, prsn_sleep_time - interval '3 hours' as prsn_sleep_time_minus from public.person where prsn_id = ?");
		isParametersEquals(parameters, 1, 2, id);
		isRecordEqual(record, 
				entry(Person.AGE.plus(1), (short)34),
				entry(Person.CPR.minus(2), 870501234),
				entry(Person.ACCOUNT_NO.divide(Person.ACCOUNT_NO), 1l),
				entry(Person.GPA.multiply(Person.GPA), 9.734399f),
				entry(Person.SALARY.plus(Person.SALARY), 801.0),
				entry(Person.ANNUAL_INCOME.divide(Person.ANNUAL_INCOME), new BigDecimal("1.00000000000000000000")),
				entry(Person.DATE_OF_BIRTH.plusMinutes(1), Date.valueOf("2015-10-10")),
				entry(Person.REGISTRATION_DATE_TIME.minusMinutes(3), Timestamp.valueOf("2000-10-10 10:07:10.0")),
				entry(Person.SLEEP_TIME.minusHours(3), Time.valueOf("07:10:10")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query select clause with multiple math/date");
		
		select(Person.AGE.plus(1).minus(2))
		.select(Person.CPR.minus(2).plus(2), 
				Person.ACCOUNT_NO.divide(Person.ACCOUNT_NO).multiply(Person.ACCOUNT_NO), 
				Person.GPA.multiply(Person.GPA).divide(Person.GPA))
		.select(Person.SALARY.plus(Person.SALARY).minus(Person.SALARY), 
				Person.ANNUAL_INCOME.divide(Person.ANNUAL_INCOME).multiply(Person.ANNUAL_INCOME), 
				Person.DATE_OF_BIRTH.plusYears(1).minusMinutes(2))
		.select(Person.REGISTRATION_DATE_TIME.minusMinutes(3).plusHours(2), 
				Person.SLEEP_TIME.minusHours(3).plusMinutes(2))
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_age + ? - ? as prsn_age_minus, prsn_cpr - ? + ? as prsn_cpr_plus, prsn_account_no / prsn_account_no * prsn_account_no as prsn_account_no_multiply, prsn_gpa * prsn_gpa / prsn_gpa as prsn_gpa_divide, prsn_salary + prsn_salary - prsn_salary as prsn_salary_minus, prsn_annual_income / prsn_annual_income * prsn_annual_income as prsn_annual_income_multiply, prsn_date_of_birth + interval '1 years' - interval '2 minutes' as prsn_date_of_birth_minus, prsn_registration_date_time - interval '3 minutes' + interval '2 hours' as prsn_registration_date_time_plus, prsn_sleep_time - interval '3 hours' + interval '2 minutes' as prsn_sleep_time_plus from public.person where prsn_id = ?");
		isParametersEquals(parameters, 1, 2, 2, 2, id);
		isRecordEqual(record, 
				entry(Person.AGE.plus(1).minus(2), (short)32),
				entry(Person.CPR.minus(2).plus(2), 870501236),
				entry(Person.ACCOUNT_NO.divide(Person.ACCOUNT_NO).multiply(Person.ACCOUNT_NO), 12345678901234l),
				entry(Person.GPA.multiply(Person.GPA).divide(Person.GPA), 3.1199996f),
				entry(Person.SALARY.plus(Person.SALARY).minus(Person.SALARY), 400.5),
				entry(Person.ANNUAL_INCOME.divide(Person.ANNUAL_INCOME).multiply(Person.ANNUAL_INCOME), new BigDecimal("500.4000000000000000000000")),
				entry(Person.DATE_OF_BIRTH.plusYears(1).minusMinutes(2), Date.valueOf("2016-10-09")),
				entry(Person.REGISTRATION_DATE_TIME.minusMinutes(3).plusHours(2), Timestamp.valueOf("2000-10-10 12:07:10.0")),
				entry(Person.SLEEP_TIME.minusHours(3).plusMinutes(2), Time.valueOf("07:12:10")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query select clause with aggregation function");
		
		select(sum(Person.AGE))
		.select(count(Person.CPR), 
				average(Person.ACCOUNT_NO), 
				min(Person.GPA))
		.select(max(Person.SALARY))
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select sum(prsn_age) as prsn_age_sum, count(prsn_cpr) as prsn_cpr_count, avg(prsn_account_no) as prsn_account_no_avg, min(prsn_gpa) as prsn_gpa_min, max(prsn_salary) as prsn_salary_max from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(record, 
				entry(sum(Person.AGE), (short)33),
				entry(count(Person.CPR), 1),
				entry(average(Person.ACCOUNT_NO), 12345678901234l),
				entry(min(Person.GPA), 3.12f),
				entry(max(Person.SALARY), 400.5));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query select clause with alias table");
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD.of("home_address").as("home_address_road")
				, Address.BLOCK.of("home_address"))
		.select(Address.ROAD.of("work_address"), Address.BLOCK.of("work_address"))
		.from(Table.PERSON.as("person"))
		.join(Table.ADDRESS.as("home_address"))
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID.of("home_address")))
		.join(Table.ADDRESS.as("work_address"))
			.on(Person.WORK_ADDRESS_ID.equal(Address.ID.of("work_address")))
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_name, prsn_gender, prsn_age, home_address.addr_road as home_address_road, home_address.addr_block as home_address_addr_block, work_address.addr_road as work_address_addr_road, work_address.addr_block as work_address_addr_block from public.person as person inner join public.address as home_address on prsn_home_address_id = home_address.addr_id inner join public.address as work_address on prsn_work_address_id = work_address.addr_id where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(record,
				entry(Person.NAME, "Hasan"),
				entry(Person.GENDER, 'M'),
				entry(Person.AGE, (short)33),
				entry(Address.ROAD.of("home_address"), "Road 1"),
				entry(Address.BLOCK.of("home_address"), "Block 1"),
				entry(Address.ROAD.of("work_address"), "Road 2"),
				entry(Address.BLOCK.of("work_address"), "Block 2"));
	}
	
	public void testFromClause() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with basic table from");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.values(Person.CITY_ID, 1)
			.values(Person.SCHOOL_ID, 1)
			.values(Person.COMPANY_ID, 1)
			.values(Person.COUNTRY_ID, 1)
			.executeInsert();
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(Person.CITY_ID, Person.SCHOOL_ID)
		.select(Person.COMPANY_ID, Person.COUNTRY_ID)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, prsn_city_id, prsn_school_id, prsn_company_id, prsn_country_id from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(record, 
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")),
						entry(Person.CITY_ID, 1),
						entry(Person.SCHOOL_ID, 1),
						entry(Person.COMPANY_ID, 1),
						entry(Person.COUNTRY_ID, 1));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with basic from table with alias");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.from(Table.PERSON.as("person_details"))
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person as person_details where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(record, 
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with basic from query with specific columns");
		
		select(Person.CPR, Person.NAME)
		.from(new Query().select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
				.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
				.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
				.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
				.select(Person.GRADUATED, Person.CERTIFICATES)
				.from(Table.PERSON)
				.where(Person.ID.equal(id)))
		.as("person_details");
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from (select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ?) as person_details");
		isParametersEquals(parameters, id);
		isRecordEqual(record, 
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with basic from query with all columns");
		
		select(Person.CPR, Person.NAME)
		.from(new Query().select()
				.from(Table.PERSON)
				.where(Person.ID.equal(id)))
		.as("person_details");
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from (select * from public.person where prsn_id = ?) as person_details");
		isParametersEquals(parameters, id);
		isRecordEqual(record, 
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with nested from query");
		
		select(Person.CPR, Person.NAME)
		.from(new Query().select(Person.CPR, Person.NAME)
				.from(new Query().select().from(Table.PERSON))
				.as("sub_person_details")
				.where(Person.ID.equal(id)))
		.as("person_details");
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from (select prsn_cpr, prsn_name from (select * from public.person) as sub_person_details where prsn_id = ?) as person_details");
		isParametersEquals(parameters, id);
		isRecordEqual(record, 
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with basic from query with joins");
		
		select(Person.CPR, Person.NAME)
		.from(new Query().select(Person.CPR, Person.NAME)
				.select(City.ID, City.NAME)
				.select(Company.ID, Company.NAME)
				.select(Country.ID, Country.NAME)
				.select(School.ID, School.NAME)
				.from(Table.PERSON)
				.innerJoin(Table.CITY).on(Person.CITY_ID.equal(City.ID))
				.rightJoin(Table.COMPANY).on(Person.COMPANY_ID.equal(Company.ID))
				.leftJoin(Table.COUNTRY).on(Person.COUNTRY_ID.equal(Country.ID))
				.fullJoin(Table.SCHOOL).on(Person.SCHOOL_ID.equal(School.ID))
				.where(Person.ID.equal(id)))
		.as("person_details");
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from (select prsn_cpr, prsn_name, ct_id, ct_name, cmp_id, cmp_name, cnt_id, cnt_name, sch_id, sch_name from public.person inner join public.city on prsn_city_id = ct_id right join public.company on prsn_company_id = cmp_id left join public.country on prsn_country_id = cnt_id full join public.school on prsn_school_id = sch_id where prsn_id = ?) as person_details");
		isParametersEquals(parameters, id);
		isRecordEqual(record, 
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with basic query from with joins with parameters");
		
		select(Person.CPR, Person.NAME)
		.from(new Query().select(Person.CPR, Person.NAME)
				.select(City.ID, City.NAME)
				.select(Company.ID, Company.NAME)
				.select(Country.ID, Country.NAME)
				.select(School.ID, School.NAME)
				.from(Table.PERSON)
				.innerJoin(Table.CITY).on(Person.CITY_ID.equal(City.ID))
				.rightJoin(Table.COMPANY).on(Person.COMPANY_ID.equal(Company.ID).and(Company.NAME.like("Maraei")))
				.leftJoin(Table.COUNTRY).on(Person.COUNTRY_ID.equal(Country.ID))
				.fullJoin(Table.SCHOOL).on(Person.SCHOOL_ID.equal(School.ID).and(School.NAME.equal("Bin Khuldon")))
				.where(Person.ID.equal(id)))
		.as("person_details");
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from (select prsn_cpr, prsn_name, ct_id, ct_name, cmp_id, cmp_name, cnt_id, cnt_name, sch_id, sch_name from public.person inner join public.city on prsn_city_id = ct_id right join public.company on (prsn_company_id = cmp_id and cmp_name ilike '%' || ? || '%') left join public.country on prsn_country_id = cnt_id full join public.school on (prsn_school_id = sch_id and sch_name = ?) where prsn_id = ?) as person_details");
		isParametersEquals(parameters, "Maraei", "Bin Khuldon", id);
		isRecordEqual(record, 
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with basic from query with joins with parameters with basic conditions");
		
		select(Person.CPR, Person.NAME)
		.from(new Query().select(Person.CPR, Person.NAME)
				.select(City.ID, City.NAME)
				.select(Company.ID, Company.NAME)
				.select(Country.ID, Country.NAME)
				.select(School.ID, School.NAME)
				.from(Table.PERSON)
				.innerJoin(Table.CITY).on(Person.CITY_ID.equal(City.ID))
				.rightJoin(Table.COMPANY).on(Person.COMPANY_ID.equal(Company.ID).and(Company.NAME.like("Maraei")))
				.leftJoin(Table.COUNTRY).on(Person.COUNTRY_ID.equal(Country.ID))
				.fullJoin(Table.SCHOOL).on(Person.SCHOOL_ID.equal(School.ID).and(School.NAME.equal("Bin Khuldon")))
				.where(Person.ID.equal(id))
				.and(Person.NAME.like("Hasan"))
				.and(Person.GENDER.equal('M'))
				.and(Person.AGE.greater(32))
				.and(Person.CPR.greaterEqual(870501236))
				.and(Person.ACCOUNT_NO.less(12345678901235l))
				.and(Person.GPA.lessEqual(3.12))
				.and(Person.SALARY.between(400.5, 500.0))
				.and(Person.ANNUAL_INCOME.in(new BigDecimal("500.40"), new BigDecimal("600.60")))
				.and(Person.DATE_OF_BIRTH.notIn(Date.valueOf("2015-11-11"), Date.valueOf("2015-12-12")))
				.and(Person.HOME_ADDRESS_ID.isNull())
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
						.and(Doctor.HOSPITAL.equal("Bin Hayan")))))
		.as("person_details");
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from (select prsn_cpr, prsn_name, ct_id, ct_name, cmp_id, cmp_name, cnt_id, cnt_name, sch_id, sch_name from public.person inner join public.city on prsn_city_id = ct_id right join public.company on (prsn_company_id = cmp_id and cmp_name ilike '%' || ? || '%') left join public.country on prsn_country_id = cnt_id full join public.school on (prsn_school_id = sch_id and sch_name = ?) where prsn_id = ? and prsn_name ilike '%' || ? || '%' and prsn_gender = ? and prsn_age > ? and prsn_cpr >= ? and prsn_account_no < ? and prsn_gpa <= ? and prsn_salary between ? and ? and prsn_annual_income in (?, ?) and prsn_date_of_birth not in (?, ?) and prsn_home_address_id is null and prsn_registration_date_time is not null and prsn_date_of_birth in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) and prsn_date_of_birth not in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) and exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?) and not exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?)) as person_details");
		isParametersEquals(parameters, "Maraei", "Bin Khuldon", id, "Hasan", 'M', 32, 870501236, 12345678901235l, 3.12, 400.5, 500.0, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), Date.valueOf("2015-12-12"), true, false, "Alkindi", "Bin Hayan");
		isRecordEqual(record,
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"));
		

		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with basic from query with joins with parameters with nested conditions");
		
		select(Person.CPR, Person.NAME)
		.from(new Query().select(Person.CPR, Person.NAME)
				.select(City.ID, City.NAME)
				.select(Company.ID, Company.NAME)
				.select(Country.ID, Country.NAME)
				.select(School.ID, School.NAME)
				.from(Table.PERSON)
				.innerJoin(Table.CITY).on(Person.CITY_ID.equal(City.ID))
				.rightJoin(Table.COMPANY).on(Person.COMPANY_ID.equal(Company.ID).and(Company.NAME.like("Maraei")))
				.leftJoin(Table.COUNTRY).on(Person.COUNTRY_ID.equal(Country.ID))
				.fullJoin(Table.SCHOOL).on(Person.SCHOOL_ID.equal(School.ID).and(School.NAME.equal("Bin Khuldon")))
				.where(Person.ID.equal(id))
				.and(Person.NAME.like("Hasan")
						.or(Person.GENDER.equal('M')))
				.and(Person.AGE.greater(32)
						.or(Person.CPR.greaterEqual(870501236)))
				.and(Person.ACCOUNT_NO.less(12345678901235l)
						.or(Person.GPA.lessEqual(3.12)))
				.and(Person.SALARY.between(400.5, 500.0)
						.or(Person.ANNUAL_INCOME.in(new BigDecimal("500.40"), new BigDecimal("600.60"))))
				.and(Person.DATE_OF_BIRTH.notIn(Date.valueOf("2015-11-11"), Date.valueOf("2015-12-12")))
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
						.and(Doctor.HOSPITAL.equal("Bin Hayan"))))))
		.as("person_details");
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from (select prsn_cpr, prsn_name, ct_id, ct_name, cmp_id, cmp_name, cnt_id, cnt_name, sch_id, sch_name from public.person inner join public.city on prsn_city_id = ct_id right join public.company on (prsn_company_id = cmp_id and cmp_name ilike '%' || ? || '%') left join public.country on prsn_country_id = cnt_id full join public.school on (prsn_school_id = sch_id and sch_name = ?) where prsn_id = ? and (prsn_name ilike '%' || ? || '%' or prsn_gender = ?) and (prsn_age > ? or prsn_cpr >= ?) and (prsn_account_no < ? or prsn_gpa <= ?) and (prsn_salary between ? and ? or prsn_annual_income in (?, ?)) and prsn_date_of_birth not in (?, ?) and (prsn_city_id is null or prsn_registration_date_time is not null) and (prsn_date_of_birth in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) or prsn_date_of_birth not in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?)) and (exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?) or not exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?))) as person_details");
		isParametersEquals(parameters, "Maraei", "Bin Khuldon", id, "Hasan", 'M', 32, 870501236, 12345678901235l, 3.12, 400.5, 500.0, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), Date.valueOf("2015-12-12"), true, false, "Alkindi", "Bin Hayan");
		isRecordEqual(record,
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"));
	}
	
	public void testJoinClause() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with join tables");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.values(Person.CITY_ID, 1)
			.values(Person.SCHOOL_ID, 1)
			.values(Person.COMPANY_ID, 1)
			.values(Person.COUNTRY_ID, 1)
			.executeInsert();
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(City.ID, City.NAME)
		.select(Company.ID, Company.NAME)
		.select(Country.ID, Country.NAME)
		.select(School.ID, School.NAME)
		.from(Table.PERSON)
		.innerJoin(Table.CITY).on(Person.CITY_ID.equal(City.ID))
		.rightJoin(Table.COMPANY).on(Person.COMPANY_ID.equal(Company.ID))
		.leftJoin(Table.COUNTRY).on(Person.COUNTRY_ID.equal(Country.ID))
		.fullJoin(Table.SCHOOL).on(Person.SCHOOL_ID.equal(School.ID))
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, ct_id, ct_name, cmp_id, cmp_name, cnt_id, cnt_name, sch_id, sch_name from public.person inner join public.city on prsn_city_id = ct_id right join public.company on prsn_company_id = cmp_id left join public.country on prsn_country_id = cnt_id full join public.school on prsn_school_id = sch_id where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(record, 
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")),
						entry(City.ID, 1),
						entry(City.NAME, "Manama"),
						entry(Company.ID, 1),
						entry(Company.NAME, "Maraei"),
						entry(Country.ID, 1),
						entry(Country.NAME, "Kuwait"),
						entry(School.ID, 1),
						entry(School.NAME, "Bin Khuldon")
						);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with join tables with nested conditions");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(City.ID, City.NAME)
		.select(Company.ID, Company.NAME)
		.select(Country.ID, Country.NAME)
		.select(School.ID, School.NAME)
		.from(Table.PERSON)
		.innerJoin(Table.CITY).on(Person.CITY_ID.equal(City.ID))
		.rightJoin(Table.COMPANY).on(Person.COMPANY_ID.equal(Company.ID).and(Company.NAME.like("Maraei")))
		.leftJoin(Table.COUNTRY).on(Person.COUNTRY_ID.equal(Country.ID))
		.fullJoin(Table.SCHOOL).on(Person.SCHOOL_ID.equal(School.ID).and(School.NAME.equal("Bin Khuldon")))
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, ct_id, ct_name, cmp_id, cmp_name, cnt_id, cnt_name, sch_id, sch_name from public.person inner join public.city on prsn_city_id = ct_id right join public.company on (prsn_company_id = cmp_id and cmp_name ilike '%' || ? || '%') left join public.country on prsn_country_id = cnt_id full join public.school on (prsn_school_id = sch_id and sch_name = ?) where prsn_id = ?");
		isParametersEquals(parameters, "Maraei", "Bin Khuldon", id);
		isRecordEqual(record, 
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")),
						entry(City.ID, 1),
						entry(City.NAME, "Manama"),
						entry(Company.ID, 1),
						entry(Company.NAME, "Maraei"),
						entry(Country.ID, 1),
						entry(Country.NAME, "Kuwait"),
						entry(School.ID, 1),
						entry(School.NAME, "Bin Khuldon"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with join queries");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(City.ID, City.NAME)
		.select(Company.ID, Company.NAME)
		.select(Country.ID, Country.NAME)
		.select(School.ID, School.NAME)
		.from(Table.PERSON)
		.innerJoin(new Query().select().from(Table.CITY)
					.where(City.NAME.equal("Manama")))
					.as("person_city")
					.on(Person.CITY_ID.equal(City.ID))
		.rightJoin(new Query().select().from(Table.COMPANY)
					.where(Company.NAME.equal("Maraei")))
					.as("person_company")
					.on(Person.COMPANY_ID.equal(Company.ID))
		.leftJoin(new Query().select().from(Table.COUNTRY)
					.where(Country.NAME.equal("Kuwait")))
					.as("person_country")
					.on(Person.COUNTRY_ID.equal(Country.ID))
		.fullJoin(new Query().select().from(Table.SCHOOL)
					.where(School.NAME.equal("Bin Khuldon")))
					.as("person_school")
					.on(Person.SCHOOL_ID.equal(School.ID))
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, ct_id, ct_name, cmp_id, cmp_name, cnt_id, cnt_name, sch_id, sch_name from public.person inner join (select * from public.city where ct_name = ?) as person_city on prsn_city_id = ct_id right join (select * from public.company where cmp_name = ?) as person_company on prsn_company_id = cmp_id left join (select * from public.country where cnt_name = ?) as person_country on prsn_country_id = cnt_id full join (select * from public.school where sch_name = ?) as person_school on prsn_school_id = sch_id where prsn_id = ?");
		isParametersEquals(parameters, "Manama", "Maraei", "Kuwait", "Bin Khuldon", id);
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")),
						entry(City.ID, 1),
						entry(City.NAME, "Manama"),
						entry(Company.ID, 1),
						entry(Company.NAME, "Maraei"),
						entry(Country.ID, 1),
						entry(Country.NAME, "Kuwait"),
						entry(School.ID, 1),
						entry(School.NAME, "Bin Khuldon"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with nesetd join queries");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(City.ID, City.NAME)
		.select(Company.ID, Company.NAME)
		.select(Country.ID, Country.NAME)
		.select(School.ID, School.NAME)
		.from(Table.PERSON)
		.innerJoin(new Query().select().from(new Query().select()
											.from(Table.CITY))
											.as("cities")
					.where(City.NAME.equal("Manama")))
					.as("person_city")
					.on(Person.CITY_ID.equal(City.ID))
		.rightJoin(new Query().select().from(new Query().select()
											.from(Table.COMPANY))
											.as("companies")
					.where(Company.NAME.equal("Maraei")))
					.as("person_company")
					.on(Person.COMPANY_ID.equal(Company.ID))
		.leftJoin(new Query().select().from(new Query().select()
											.from(Table.COUNTRY))
											.as("countries")
					.where(Country.NAME.equal("Kuwait")))
					.as("person_country")
					.on(Person.COUNTRY_ID.equal(Country.ID))
		.fullJoin(new Query().select().from(new Query().select()
											.from(Table.SCHOOL))
											.as("schools")
					.where(School.NAME.equal("Bin Khuldon")))
					.as("person_school")
					.on(Person.SCHOOL_ID.equal(School.ID))
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, ct_id, ct_name, cmp_id, cmp_name, cnt_id, cnt_name, sch_id, sch_name from public.person inner join (select * from (select * from public.city) as cities where ct_name = ?) as person_city on prsn_city_id = ct_id right join (select * from (select * from public.company) as companies where cmp_name = ?) as person_company on prsn_company_id = cmp_id left join (select * from (select * from public.country) as countries where cnt_name = ?) as person_country on prsn_country_id = cnt_id full join (select * from (select * from public.school) as schools where sch_name = ?) as person_school on prsn_school_id = sch_id where prsn_id = ?");
		isParametersEquals(parameters, "Manama", "Maraei", "Kuwait", "Bin Khuldon", id);
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")),
						entry(City.ID, 1),
						entry(City.NAME, "Manama"),
						entry(Company.ID, 1),
						entry(Company.NAME, "Maraei"),
						entry(Country.ID, 1),
						entry(Country.NAME, "Kuwait"),
						entry(School.ID, 1),
						entry(School.NAME, "Bin Khuldon"));
	}
	
	public void testWhereClause() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with basic conditions");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
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
		.and(Person.DATE_OF_BIRTH.notIn(Date.valueOf("2015-11-11"), Date.valueOf("2015-12-12")))
		.and(Person.HOME_ADDRESS_ID.isNull())
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
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? and prsn_name ilike '%' || ? || '%' and prsn_gender = ? and prsn_age > ? and prsn_cpr >= ? and prsn_account_no < ? and prsn_gpa <= ? and prsn_salary between ? and ? and prsn_annual_income in (?, ?) and prsn_date_of_birth not in (?, ?) and prsn_home_address_id is null and prsn_registration_date_time is not null and prsn_date_of_birth in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) and prsn_date_of_birth not in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) and exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?) and not exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?)");
		isParametersEquals(parameters, id, "Hasan", 'M', 32, 870501236, 12345678901235l, 3.12, 400.5, 500.0, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), Date.valueOf("2015-12-12"), true, false, "Alkindi", "Bin Hayan");
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with nested conditions");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
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
		.and(Person.DATE_OF_BIRTH.notIn(Date.valueOf("2015-11-11"), Date.valueOf("2015-12-12")))
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
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? and (prsn_name ilike '%' || ? || '%' or prsn_gender = ?) and (prsn_age > ? or prsn_cpr >= ?) and (prsn_account_no < ? or prsn_gpa <= ?) and (prsn_salary between ? and ? or prsn_annual_income in (?, ?)) and prsn_date_of_birth not in (?, ?) and (prsn_city_id is null or prsn_registration_date_time is not null) and (prsn_date_of_birth in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) or prsn_date_of_birth not in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?)) and (exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?) or not exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?))");
		isParametersEquals(parameters, id, "Hasan", 'M', 32, 870501236, 12345678901235l, 3.12, 400.5, 500.0, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), Date.valueOf("2015-12-12"), true, false, "Alkindi", "Bin Hayan");
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with basic string conditions");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
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
		.and(Person.DATE_OF_BIRTH.notIn("11-11-2015", "12-12-2015"))
		.and(Person.HOME_ADDRESS_ID.isNull())
		.and(Person.REGISTRATION_DATE_TIME.isNotNull())
		.and(Person.DATE_OF_BIRTH.in(new Query()
				.select(UniqueDateOfBirth.DATE)
				.from(Table.UNIQUE_DATE_OF_BIRTH)
				.where(UniqueDateOfBirth.LEAP_YEAR.equal("true"))))
		.and(Person.DATE_OF_BIRTH.notIn(new Query()
				.select(UniqueDateOfBirth.DATE)
				.from(Table.UNIQUE_DATE_OF_BIRTH)
				.where(UniqueDateOfBirth.LEAP_YEAR.equal("false"))))
		.and(exists(new Query().select(Doctor.ID)
				.from(Table.DOCTOR).where(Doctor.CPR.equal(Person.CPR))
				.and(Doctor.HOSPITAL.equal("Alkindi"))))
		.and(notExists(new Query().select(Doctor.ID)
				.from(Table.DOCTOR).where(Doctor.CPR.equal(Person.CPR))
				.and(Doctor.HOSPITAL.equal("Bin Hayan"))));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? and prsn_name ilike '%' || ? || '%' and prsn_gender = ? and prsn_age > ? and prsn_cpr >= ? and prsn_account_no < ? and prsn_gpa <= ? and prsn_salary between ? and ? and prsn_annual_income in (?, ?) and prsn_date_of_birth not in (?, ?) and prsn_home_address_id is null and prsn_registration_date_time is not null and prsn_date_of_birth in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) and prsn_date_of_birth not in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) and exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?) and not exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?)");
		isParametersEquals(parameters, id, "Hasan", 'M', (short)32, 870501236, 12345678901235l, 3.12f, 400.5, 500.0, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), Date.valueOf("2015-12-12"), true, false, "Alkindi", "Bin Hayan");
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with nested string conditions");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.and(Person.NAME.like("Hasan")
				.or(Person.GENDER.equal("M")))
		.and(Person.AGE.greater("32")
				.or(Person.CPR.greaterEqual("870501236")))
		.and(Person.ACCOUNT_NO.less("12345678901235")
				.or(Person.GPA.lessEqual("3.12")))
		.and(Person.SALARY.between("400.5", "500.0")
				.or(Person.ANNUAL_INCOME.in("500.40", "600.60")))
		.and(Person.DATE_OF_BIRTH.notIn("11-11-2015", "12-12-2015"))
		.and(Person.CITY_ID.isNull()
				.or(Person.REGISTRATION_DATE_TIME.isNotNull()))
		.and(Person.DATE_OF_BIRTH.in(new Query()
				.select(UniqueDateOfBirth.DATE)
				.from(Table.UNIQUE_DATE_OF_BIRTH)
				.where(UniqueDateOfBirth.LEAP_YEAR.equal("true")))
			.or(Person.DATE_OF_BIRTH.notIn(new Query()
					.select(UniqueDateOfBirth.DATE)
				.from(Table.UNIQUE_DATE_OF_BIRTH)
				.where(UniqueDateOfBirth.LEAP_YEAR.equal("false")))))
		.and(exists(new Query().select(Doctor.ID)
				.from(Table.DOCTOR).where(Doctor.CPR.equal(Person.CPR))
				.and(Doctor.HOSPITAL.equal("Alkindi")))
			.or(notExists(new Query().select(Doctor.ID)
				.from(Table.DOCTOR).where(Doctor.CPR.equal(Person.CPR))
				.and(Doctor.HOSPITAL.equal("Bin Hayan")))));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? and (prsn_name ilike '%' || ? || '%' or prsn_gender = ?) and (prsn_age > ? or prsn_cpr >= ?) and (prsn_account_no < ? or prsn_gpa <= ?) and (prsn_salary between ? and ? or prsn_annual_income in (?, ?)) and prsn_date_of_birth not in (?, ?) and (prsn_city_id is null or prsn_registration_date_time is not null) and (prsn_date_of_birth in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?) or prsn_date_of_birth not in (select udob_date from public.unique_date_of_birth where udob_leap_year = ?)) and (exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?) or not exists (select dctr_id from public.doctor where dctr_cpr = prsn_cpr and dctr_hospital = ?))");
		isParametersEquals(parameters, id, "Hasan", 'M', (short)32, 870501236, 12345678901235l, 3.12f, 400.5, 500.0, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), Date.valueOf("2015-12-12"), true, false, "Alkindi", "Bin Hayan");
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with basic util date conditions");
		
		java.util.Date date = new java.util.Date();
		
		Date sqlDate = new Date(date.getTime());
		Timestamp sqlTimestamp = new Timestamp(date.getTime());
		Time sqlTime = new Time(date.getTime());
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.and(Person.DATE_OF_BIRTH.equal(date))
		.and(Person.REGISTRATION_DATE_TIME.notEqual(date))
		.and(Person.SLEEP_TIME.greater(date))
		.and(Person.DATE_OF_BIRTH.less(date))
		.and(Person.REGISTRATION_DATE_TIME.lessEqual(date))
		.and(Person.SLEEP_TIME.greaterEqual(date))
		.and(Person.DATE_OF_BIRTH.in(date, date))
		.and(Person.REGISTRATION_DATE_TIME.notIn(date, date))
		.and(Person.SLEEP_TIME.between(date, date));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? and prsn_date_of_birth = ? and prsn_registration_date_time <> ? and prsn_sleep_time > ? and prsn_date_of_birth < ? and prsn_registration_date_time <= ? and prsn_sleep_time >= ? and prsn_date_of_birth in (?, ?) and prsn_registration_date_time not in (?, ?) and prsn_sleep_time between ? and ?");
		isParametersEquals(parameters, id, sqlDate, sqlTimestamp, sqlTime, sqlDate, sqlTimestamp, sqlTime, sqlDate, sqlDate, sqlTimestamp, sqlTimestamp, sqlTime, sqlTime);
		isRecordEqual(record);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with one math/date conditions");
		
		select(Person.CPR, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.and(Person.AGE.plus(1).greater("32"))
		.and(Person.CPR.minus(2).greaterEqual("870501236"))
		.and(Person.ACCOUNT_NO.multiply(3).less("12345678901235"))
		.and(Person.GPA.divide(4).lessEqual("3.12"))
		.and(Person.SALARY.plus(5).between("400.5", "500.0"))
		.and(Person.ANNUAL_INCOME.minus(1).in("500.40", "600.60"))
		.and(Person.DATE_OF_BIRTH.plusHours(2).between("11-11-2015", "12-12-2015"))
		.and(Person.REGISTRATION_DATE_TIME.plusDays(3).between("11-11-2015 11:11:11", "12-12-2015 10:10:10"))
		.and(Person.SLEEP_TIME.plusMinutes(4).between("04:04:04", "05:05:05"));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from public.person where prsn_id = ? and prsn_age + ? > ? and prsn_cpr - ? >= ? and prsn_account_no * ? < ? and prsn_gpa / ? <= ? and prsn_salary + ? between ? and ? and prsn_annual_income - ? in (?, ?) and prsn_date_of_birth + interval '2 hours' between ? and ? and prsn_registration_date_time + interval '3 days' between ? and ? and prsn_sleep_time + interval '4 minutes' between ? and ?");
		isParametersEquals(parameters, id, 1, (short)32, 2, 870501236, 3, 12345678901235l, 4, 3.12f, 5, 400.5, 500.0, 1, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), Date.valueOf("2015-12-12"), Timestamp.valueOf("2015-11-11 11:11:11.0"), Timestamp.valueOf("2015-12-12 10:10:10.0"), Time.valueOf("04:04:04"), Time.valueOf("05:05:05"));
		isRecordEqual(record);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with multiple math/date conditions");
		
		select(Person.CPR, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.and(Person.AGE.plus(1).minus(2).greater("32"))
		.and(Person.CPR.minus(2).multiply(3).greaterEqual("870501236"))
		.and(Person.ACCOUNT_NO.multiply(3).divide(4).less("12345678901235"))
		.and(Person.GPA.divide(4).multiply(5).lessEqual("3.12"))
		.and(Person.SALARY.plus(5).minus(2).between("400.5", "500.0"))
		.and(Person.ANNUAL_INCOME.minus(1).plus(1).in("500.40", "600.60"))
		.and(Person.DATE_OF_BIRTH.plusHours(2).minusMinutes(3).between("11-11-2015", "12-12-2015"))
		.and(Person.REGISTRATION_DATE_TIME.plusDays(3).minusHours(4).between("11-11-2015 11:11:11", "12-12-2015 10:10:10"))
		.and(Person.SLEEP_TIME.plusMinutes(4).minusHours(5).between("04:04:04", "05:05:05"));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from public.person where prsn_id = ? and prsn_age + ? - ? > ? and prsn_cpr - ? * ? >= ? and prsn_account_no * ? / ? < ? and prsn_gpa / ? * ? <= ? and prsn_salary + ? - ? between ? and ? and prsn_annual_income - ? + ? in (?, ?) and prsn_date_of_birth + interval '2 hours' - interval '3 minutes' between ? and ? and prsn_registration_date_time + interval '3 days' - interval '4 hours' between ? and ? and prsn_sleep_time + interval '4 minutes' - interval '5 hours' between ? and ?");
		isParametersEquals(parameters, id, 1, 2, (short)32, 2, 3, 870501236, 3, 4, 12345678901235l, 4, 5, 3.12f, 5, 2, 400.5, 500.0, 1, 1, new BigDecimal("500.40"), new BigDecimal("600.60"), Date.valueOf("2015-11-11"), Date.valueOf("2015-12-12"), Timestamp.valueOf("2015-11-11 11:11:11.0"), Timestamp.valueOf("2015-12-12 10:10:10.0"), Time.valueOf("04:04:04"), Time.valueOf("05:05:05"));
		isRecordEqual(record);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with multiple math conditions with column");
		
		select(Person.CPR, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.and(Person.AGE.plus(Person.AGE).minus(Person.AGE).greater("32"))
		.and(Person.CPR.minus(Person.CPR).plus(Person.CPR).greaterEqual("870501236"))
		.and(Person.ACCOUNT_NO.plus(Person.ACCOUNT_NO).divide(Person.ACCOUNT_NO).less("12345678901235"))
		.and(Person.GPA.divide(Person.GPA).plus(Person.GPA).lessEqual("3.12"))
		.and(Person.SALARY.plus(Person.SALARY).minus(Person.SALARY).between("400.5", "500.0"))
		.and(Person.ANNUAL_INCOME.minus(Person.ANNUAL_INCOME).plus(Person.ANNUAL_INCOME).in("500.40", "600.60"));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from public.person where prsn_id = ? and prsn_age + prsn_age - prsn_age > ? and prsn_cpr - prsn_cpr + prsn_cpr >= ? and prsn_account_no + prsn_account_no / prsn_account_no < ? and prsn_gpa / prsn_gpa + prsn_gpa <= ? and prsn_salary + prsn_salary - prsn_salary between ? and ? and prsn_annual_income - prsn_annual_income + prsn_annual_income in (?, ?)");
		isParametersEquals(parameters, id, (short)32, 870501236, 12345678901235l, 3.12f, 400.5, 500.0, new BigDecimal("500.40"), new BigDecimal("600.60"));
		isRecordEqual(record);
	}
	
	public void testOrderByClause() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single order ascending");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.orderBy(Person.ID);
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? order by prsn_id");
		isParametersEquals(parameters, id);
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with multiple order ascending");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.orderBy(Person.ID, Person.CPR);
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? order by prsn_id, prsn_cpr");
		isParametersEquals(parameters, id);
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single order descending");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.orderByDesc(Person.ID);
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? order by prsn_id desc");
		isParametersEquals(parameters, id);
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with multiple order descending");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.orderByDesc(Person.ID, Person.CPR);
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? order by prsn_id desc, prsn_cpr desc");
		isParametersEquals(parameters, id);
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single order ascending & single order descending");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.orderBy(Person.ID)
		.orderByDesc(Person.CPR);
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? order by prsn_id, prsn_cpr desc");
		isParametersEquals(parameters, id);
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));

		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with multiple order ascending & multiple order descending");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.orderBy(Person.ID, Person.CPR)
		.orderByDesc(Person.AGE, Person.GPA);
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? order by prsn_id, prsn_cpr, prsn_age desc, prsn_gpa desc");
		isParametersEquals(parameters, id);
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));

		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single order using column.asc");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.orderBy(Person.ID.asc());
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? order by prsn_id");
		isParametersEquals(parameters, id);
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single order using column.desc");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.orderBy(Person.ID.desc());
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? order by prsn_id desc");
		isParametersEquals(parameters, id);
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with multiple orders Column asc/desc");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.orderBy(Person.ID.asc(), Person.CPR.asc(), Person.AGE.desc(), Person.GPA.desc());
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? order by prsn_id, prsn_cpr, prsn_age desc, prsn_gpa desc");
		isParametersEquals(parameters, id);
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
	}
	
	public void testGroupByHavingClause() throws Exception {
		
		deleteFrom(Table.STUDENT).executeDelete();
		deleteFrom(Table.PERSON).executeDelete();
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single group by with count");
		
		insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.GENDER, count(Person.ID))
		.from(Table.PERSON)
		.groupBy(Person.GENDER);
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_gender, count(prsn_id) as prsn_id_count from public.person group by prsn_gender");
		isParametersEquals(parameters);
		isRecordEqual(record,
						entry(Person.GENDER, 'M'),
						entry(count(Person.ID), 1));
		
		deleteFrom(Table.STUDENT).executeDelete();
		deleteFrom(Table.PERSON).executeDelete();
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single group by with sum");
		
		insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.GENDER, sum(Person.ANNUAL_INCOME))
		.from(Table.PERSON)
		.groupBy(Person.GENDER);
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_gender, sum(prsn_annual_income) as prsn_annual_income_sum from public.person group by prsn_gender");
		isParametersEquals(parameters);
		isRecordEqual(record,
						entry(Person.GENDER, 'M'),
						entry(sum(Person.ANNUAL_INCOME), new BigDecimal("500.40")));
		
		deleteFrom(Table.STUDENT).executeDelete();
		deleteFrom(Table.PERSON).executeDelete();
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single group by with avg");
		
		insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.GENDER, average(Person.SALARY))
		.from(Table.PERSON)
		.groupBy(Person.GENDER);
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_gender, avg(prsn_salary) as prsn_salary_avg from public.person group by prsn_gender");
		isParametersEquals(parameters);
		isRecordEqual(record,
						entry(Person.GENDER, 'M'),
						entry(average(Person.SALARY), 400.5));
		
		deleteFrom(Table.STUDENT).executeDelete();
		deleteFrom(Table.PERSON).executeDelete();
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single group by with min");
		
		insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.GENDER, min(Person.SALARY))
		.from(Table.PERSON)
		.groupBy(Person.GENDER);
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_gender, min(prsn_salary) as prsn_salary_min from public.person group by prsn_gender");
		isParametersEquals(parameters);
		isRecordEqual(record,
						entry(Person.GENDER, 'M'),
						entry(min(Person.SALARY), 400.5));
		
		deleteFrom(Table.STUDENT).executeDelete();
		deleteFrom(Table.PERSON).executeDelete();
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single group by with max");
		
		insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.GENDER, max(Person.SALARY))
		.from(Table.PERSON)
		.groupBy(Person.GENDER);
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_gender, max(prsn_salary) as prsn_salary_max from public.person group by prsn_gender");
		isParametersEquals(parameters);
		isRecordEqual(record,
						entry(Person.GENDER, 'M'),
						entry(max(Person.SALARY), 400.5));
		
		deleteFrom(Table.STUDENT).executeDelete();
		deleteFrom(Table.PERSON).executeDelete();
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single group by with multiple aggregations");
		
		insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.GENDER)
		.select(count(Person.ID))
		.select(sum(Person.SALARY))
		.select(average(Person.SALARY))
		.select(min(Person.SALARY))
		.select(max(Person.SALARY))
		.from(Table.PERSON)
		.groupBy(Person.GENDER);
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_gender, count(prsn_id) as prsn_id_count, sum(prsn_salary) as prsn_salary_sum, avg(prsn_salary) as prsn_salary_avg, min(prsn_salary) as prsn_salary_min, max(prsn_salary) as prsn_salary_max from public.person group by prsn_gender");
		isParametersEquals(parameters);
		isRecordEqual(record,
						entry(Person.GENDER, 'M'),
						entry(count(Person.ID), 1),
						entry(sum(Person.SALARY), 400.5),
						entry(average(Person.SALARY), 400.5),
						entry(min(Person.SALARY), 400.5),
						entry(max(Person.SALARY), 400.5));
		
		deleteFrom(Table.STUDENT).executeDelete();
		deleteFrom(Table.PERSON).executeDelete();
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with multiple group by with multiple aggregations");
		
		insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.GENDER, Person.DATE_OF_BIRTH)
		.select(count(Person.ID))
		.select(sum(Person.SALARY))
		.select(average(Person.SALARY))
		.select(min(Person.SALARY))
		.select(max(Person.SALARY))
		.from(Table.PERSON)
		.groupBy(Person.GENDER, Person.DATE_OF_BIRTH);
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_gender, prsn_date_of_birth, count(prsn_id) as prsn_id_count, sum(prsn_salary) as prsn_salary_sum, avg(prsn_salary) as prsn_salary_avg, min(prsn_salary) as prsn_salary_min, max(prsn_salary) as prsn_salary_max from public.person group by prsn_gender, prsn_date_of_birth");
		isParametersEquals(parameters);
		isRecordEqual(record,
						entry(Person.GENDER, 'M'),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(count(Person.ID), 1),
						entry(sum(Person.SALARY), 400.5),
						entry(average(Person.SALARY), 400.5),
						entry(min(Person.SALARY), 400.5),
						entry(max(Person.SALARY), 400.5));
		
		deleteFrom(Table.STUDENT).executeDelete();
		deleteFrom(Table.PERSON).executeDelete();
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with group by with single having condition");
		
		insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.GENDER, Person.DATE_OF_BIRTH)
		.select(count(Person.ID))
		.select(sum(Person.SALARY))
		.select(average(Person.SALARY))
		.select(min(Person.SALARY))
		.select(max(Person.SALARY))
		.from(Table.PERSON)
		.groupBy(Person.GENDER, Person.DATE_OF_BIRTH)
		.having(count(Person.ID).equal(1));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_gender, prsn_date_of_birth, count(prsn_id) as prsn_id_count, sum(prsn_salary) as prsn_salary_sum, avg(prsn_salary) as prsn_salary_avg, min(prsn_salary) as prsn_salary_min, max(prsn_salary) as prsn_salary_max from public.person group by prsn_gender, prsn_date_of_birth having count(prsn_id) = ?");
		isParametersEquals(parameters, 1);
		isRecordEqual(record,
						entry(Person.GENDER, 'M'),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(count(Person.ID), 1),
						entry(sum(Person.SALARY), 400.5),
						entry(average(Person.SALARY), 400.5),
						entry(min(Person.SALARY), 400.5),
						entry(max(Person.SALARY), 400.5));
		
		deleteFrom(Table.STUDENT).executeDelete();
		deleteFrom(Table.PERSON).executeDelete();
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with group by with multiple having condition");
		
		insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.GENDER, Person.DATE_OF_BIRTH)
		.select(count(Person.ID))
		.select(sum(Person.SALARY))
		.select(average(Person.SALARY))
		.select(min(Person.SALARY))
		.select(max(Person.SALARY))
		.from(Table.PERSON)
		.groupBy(Person.GENDER, Person.DATE_OF_BIRTH)
		.having(count(Person.ID).notEqual(0).and(sum(Person.SALARY).greater(1)));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_gender, prsn_date_of_birth, count(prsn_id) as prsn_id_count, sum(prsn_salary) as prsn_salary_sum, avg(prsn_salary) as prsn_salary_avg, min(prsn_salary) as prsn_salary_min, max(prsn_salary) as prsn_salary_max from public.person group by prsn_gender, prsn_date_of_birth having (count(prsn_id) <> ? and sum(prsn_salary) > ?)");
		isParametersEquals(parameters, 0, 1);
		isRecordEqual(record,
						entry(Person.GENDER, 'M'),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(count(Person.ID), 1),
						entry(sum(Person.SALARY), 400.5),
						entry(average(Person.SALARY), 400.5),
						entry(min(Person.SALARY), 400.5),
						entry(max(Person.SALARY), 400.5));
	}
	
	public void testPaginationClause() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with limit");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.limit(1);
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = ? limit ?");
		isParametersEquals(parameters, id, 1);
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with offset");
		
		insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan Yusuf")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		insertInto(Table.PERSON)
		.values(Person.NAME, "Hasan Yusuf")
		.values(Person.GENDER, 'M')
		.values(Person.AGE, (short)33)
		.values(Person.CPR, 870501236)
		.values(Person.ACCOUNT_NO, 12345678901234l)
		.values(Person.GPA, 3.12f)
		.values(Person.SALARY, 400.5)
		.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
		.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
		.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
		.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
		.values(Person.GRADUATED, true)
		.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
		.executeInsert();
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.NAME.equal("Hasan Yusuf"))
		.offset(1);
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_name = ? offset ?");
		isParametersEquals(parameters, "Hasan Yusuf", 1);
		isRecordEqual(record,
						entry(Person.NAME, "Hasan Yusuf"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with offset & limit");
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.NAME.equal("Hasan Yusuf"))
		.limit(1)
		.offset(1);
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_name = ? limit ? offset ?");
		isParametersEquals(parameters, "Hasan Yusuf", 1, 1);
		isRecordEqual(record,
						entry(Person.NAME, "Hasan Yusuf"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
	}
	
	public void testCombineQueriesClause() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single union combine query");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		secondId = insertInto(Table.PERSON)
				.values(Person.NAME, "Hasan")
				.values(Person.GENDER, 'M')
				.values(Person.AGE, (short)33)
				.values(Person.CPR, 870501236)
				.values(Person.ACCOUNT_NO, 12345678901234l)
				.values(Person.GPA, 3.12f)
				.values(Person.SALARY, 400.5)
				.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
				.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
				.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
				.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
				.values(Person.GRADUATED, true)
				.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
				.executeInsert();
		
		select(Person.CPR, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.union(new Query().select(Person.CPR, Person.NAME)
				.from(Table.PERSON)
				.where(Person.ID.equal(secondId)));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from public.person where prsn_id = ? union select prsn_cpr, prsn_name from public.person where prsn_id = ?");
		isParametersEquals(parameters, id, secondId);
		isRecordEqual(record,
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single union all combine query");
		
		select(Person.CPR, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.unionAll(new Query().select(Person.CPR, Person.NAME)
				.from(Table.PERSON)
				.where(Person.ID.equal(secondId)));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from public.person where prsn_id = ? union all select prsn_cpr, prsn_name from public.person where prsn_id = ?");
		isParametersEquals(parameters, id, secondId);
		isRecordEqual(record,
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single intersect combine query");
		
		select(Person.CPR, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.intersect(new Query().select(Person.CPR, Person.NAME)
				.from(Table.PERSON)
				.where(Person.ID.equal(secondId)));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from public.person where prsn_id = ? intersect select prsn_cpr, prsn_name from public.person where prsn_id = ?");
		isParametersEquals(parameters, id, secondId);
		isRecordEqual(record,
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single intersect all combine query");
		
		select(Person.CPR, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.intersectAll(new Query().select(Person.CPR, Person.NAME)
				.from(Table.PERSON)
				.where(Person.ID.equal(secondId)));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from public.person where prsn_id = ? intersect all select prsn_cpr, prsn_name from public.person where prsn_id = ?");
		isParametersEquals(parameters, id, secondId);
		isRecordEqual(record,
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single except combine query");
		
		select(Person.CPR, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.except(new Query().select(Person.CPR, Person.NAME)
				.from(Table.PERSON)
				.where(Person.ID.equal(secondId)));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from public.person where prsn_id = ? except select prsn_cpr, prsn_name from public.person where prsn_id = ?");
		isParametersEquals(parameters, id, secondId);
		isRecordEqual(record);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with single except all combine query");
		
		select(Person.CPR, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.exceptAll(new Query().select(Person.CPR, Person.NAME)
				.from(Table.PERSON)
				.where(Person.ID.equal(secondId)));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from public.person where prsn_id = ? except all select prsn_cpr, prsn_name from public.person where prsn_id = ?");
		isParametersEquals(parameters, id, secondId);
		isRecordEqual(record);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with multiple combine queries");
		
		select(Person.CPR, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id))
		.union(new Query().select(Person.CPR, Person.NAME)
				.from(Table.PERSON)
				.where(Person.ID.equal(secondId)))
		.unionAll(new Query().select(Person.CPR, Person.NAME)
				.from(Table.PERSON)
				.where(Person.ID.equal(secondId)))
		.intersect(new Query().select(Person.CPR, Person.NAME)
				.from(Table.PERSON)
				.where(Person.ID.equal(secondId)))
		.intersectAll(new Query().select(Person.CPR, Person.NAME)
				.from(Table.PERSON)
				.where(Person.ID.equal(secondId)));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from public.person where prsn_id = ? union select prsn_cpr, prsn_name from public.person where prsn_id = ? union all select prsn_cpr, prsn_name from public.person where prsn_id = ? intersect select prsn_cpr, prsn_name from public.person where prsn_id = ? intersect all select prsn_cpr, prsn_name from public.person where prsn_id = ?");
		isParametersEquals(parameters, id, secondId, secondId, secondId, secondId);
		isRecordEqual(record, 
				entry(Person.CPR, 870501236),
				entry(Person.NAME, "Hasan"));
	}
	
	public void testGetObjectResult() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObject with String");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getObjectResult();
		
		isQueryEqual(query, "select prsn_name from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, "Hasan");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObject with Character");
		
		select(Person.GENDER)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getObjectResult();
		
		isQueryEqual(query, "select prsn_gender from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, 'M');
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObject with Short");
		
		select(Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getObjectResult();
		
		isQueryEqual(query, "select prsn_age from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, (short)33);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObject with Integer");
		
		select(Person.CPR)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getObjectResult();
		
		isQueryEqual(query, "select prsn_cpr from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, 870501236);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObject with Long");
		
		select(Person.ACCOUNT_NO)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getObjectResult();
		
		isQueryEqual(query, "select prsn_account_no from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, 12345678901234l);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObject with Float");
		
		select(Person.GPA)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getObjectResult();
		
		isQueryEqual(query, "select prsn_gpa from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, 3.12f);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObject with Double");
		
		select(Person.SALARY)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getObjectResult();
		
		isQueryEqual(query, "select prsn_salary from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, 400.5);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObject with Decimal");
		
		select(Person.ANNUAL_INCOME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getObjectResult();
		
		isQueryEqual(query, "select prsn_annual_income from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, new BigDecimal("500.40"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObject with Date");
		
		select(Person.DATE_OF_BIRTH)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getObjectResult();
		
		isQueryEqual(query, "select prsn_date_of_birth from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, Date.valueOf("2015-10-10"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObject with Timestamp");
		
		select(Person.REGISTRATION_DATE_TIME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getObjectResult();
		
		isQueryEqual(query, "select prsn_registration_date_time from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, Timestamp.valueOf("2000-10-10 10:10:10"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObject with Time");
		
		select(Person.SLEEP_TIME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getObjectResult();
		
		isQueryEqual(query, "select prsn_sleep_time from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, Time.valueOf("10:10:10"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getBooleanResult");
		
		select(Person.GRADUATED)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getObjectResult();
		
		isQueryEqual(query, "select prsn_graduated from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, true);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getArrayResult");
		
		select(Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getObjectResult();
		
		isQueryEqual(query, "select prsn_certificates from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, Arrays.asList("DB2", "Java"));
	}
	
	public void testGetSingleResult() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringResult");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getStringResult();
		
		isQueryEqual(query, "select prsn_name from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, "Hasan");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getCharacterResult");
		
		select(Person.GENDER)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getCharacterResult();
		
		isQueryEqual(query, "select prsn_gender from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, 'M');
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getShortResult");
		
		select(Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getShortResult();
		
		isQueryEqual(query, "select prsn_age from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, (short)33);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getIntResult");
		
		select(Person.CPR)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getIntegerResult();
		
		isQueryEqual(query, "select prsn_cpr from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, 870501236);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getLongResult");
		
		select(Person.ACCOUNT_NO)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getLongResult();
		
		isQueryEqual(query, "select prsn_account_no from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, 12345678901234l);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getFloatResult");
		
		select(Person.GPA)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getFloatResult();
		
		isQueryEqual(query, "select prsn_gpa from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, 3.12f);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getDoubleResult");
		
		select(Person.SALARY)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getDoubleResult();
		
		isQueryEqual(query, "select prsn_salary from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, 400.5);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getDecimalResult");
		
		select(Person.ANNUAL_INCOME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getDecimalResult();
		
		isQueryEqual(query, "select prsn_annual_income from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, new BigDecimal("500.40"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getDateResult");
		
		select(Person.DATE_OF_BIRTH)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getDateResult();
		
		isQueryEqual(query, "select prsn_date_of_birth from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, Date.valueOf("2015-10-10"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getTimestampResult");
		
		select(Person.REGISTRATION_DATE_TIME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getTimestampResult();
		
		isQueryEqual(query, "select prsn_registration_date_time from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, Timestamp.valueOf("2000-10-10 10:10:10"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getTimeResult");
		
		select(Person.SLEEP_TIME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getTimeResult();
		
		isQueryEqual(query, "select prsn_sleep_time from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, Time.valueOf("10:10:10"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getBooleanResult");
		
		select(Person.GRADUATED)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getBooleanResult();
		
		isQueryEqual(query, "select prsn_graduated from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, true);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getArrayResult");
		
		select(Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getArrayResult();
		
		isQueryEqual(query, "select prsn_certificates from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, Arrays.asList("DB2", "Java"));
	}
	
	public void testGetSingleEntry() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with String");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.ID, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult();
		
		isQueryEqual(query, "select prsn_id, prsn_name from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, "Hasan"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Character");
		
		select(Person.ID, Person.GENDER)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult();
		
		isQueryEqual(query, "select prsn_id, prsn_gender from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, 'M'));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Short");
		
		select(Person.ID, Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult();
		
		isQueryEqual(query, "select prsn_id, prsn_age from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, (short)33));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Integer");
		
		select(Person.ID, Person.CPR)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult();
		
		isQueryEqual(query, "select prsn_id, prsn_cpr from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, 870501236));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Float");
		
		select(Person.ID, Person.ACCOUNT_NO)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult();
		
		isQueryEqual(query, "select prsn_id, prsn_account_no from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, 12345678901234l));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Long");
		
		select(Person.ID, Person.GPA)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult();
		
		isQueryEqual(query, "select prsn_id, prsn_gpa from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, 3.12f));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Double");
		
		select(Person.ID, Person.SALARY)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult();
		
		isQueryEqual(query, "select prsn_id, prsn_salary from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, 400.5));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Decimal");
		
		select(Person.ID, Person.ANNUAL_INCOME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult();
		
		isQueryEqual(query, "select prsn_id, prsn_annual_income from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, new BigDecimal("500.40")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Date");
		
		select(Person.ID, Person.DATE_OF_BIRTH)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult();
		
		isQueryEqual(query, "select prsn_id, prsn_date_of_birth from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, Date.valueOf("2015-10-10")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Timestamp");
		
		select(Person.ID, Person.REGISTRATION_DATE_TIME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult();
		
		isQueryEqual(query, "select prsn_id, prsn_registration_date_time from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, Timestamp.valueOf("2000-10-10 10:10:10")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Time");
		
		select(Person.ID, Person.SLEEP_TIME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult();
		
		isQueryEqual(query, "select prsn_id, prsn_sleep_time from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, Time.valueOf("10:10:10")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Boolean");
		
		select(Person.ID, Person.GRADUATED)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult();
		
		isQueryEqual(query, "select prsn_id, prsn_graduated from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, true));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Array");
		
		select(Person.ID, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult();
		
		isQueryEqual(query, "select prsn_id, prsn_certificates from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, Arrays.asList("DB2", "Java")));
	}
	
	public void testGetSingleEntryWithClasses() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with String class");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.ID, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(Integer.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_name from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, "Hasan"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Character class");
		
		select(Person.ID, Person.GENDER)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(Integer.class, Character.class);
		
		isQueryEqual(query, "select prsn_id, prsn_gender from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, 'M'));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Short");
		
		select(Person.ID, Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(Integer.class, Short.class);
		
		isQueryEqual(query, "select prsn_id, prsn_age from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, (short)33));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Integer class");
		
		select(Person.ID, Person.CPR)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(Integer.class, Integer.class);
		
		isQueryEqual(query, "select prsn_id, prsn_cpr from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, 870501236));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Float class");
		
		select(Person.ID, Person.ACCOUNT_NO)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(Integer.class, Long.class);
		
		isQueryEqual(query, "select prsn_id, prsn_account_no from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, 12345678901234l));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Long class");
		
		select(Person.ID, Person.GPA)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(Integer.class, Float.class);
		
		isQueryEqual(query, "select prsn_id, prsn_gpa from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, 3.12f));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Double class");
		
		select(Person.ID, Person.SALARY)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(Integer.class, Double.class);
		
		isQueryEqual(query, "select prsn_id, prsn_salary from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, 400.5));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Decimal class");
		
		select(Person.ID, Person.ANNUAL_INCOME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(Integer.class, BigDecimal.class);
		
		isQueryEqual(query, "select prsn_id, prsn_annual_income from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, new BigDecimal("500.40")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Date class");
		
		select(Person.ID, Person.DATE_OF_BIRTH)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(Integer.class, Date.class);
		
		isQueryEqual(query, "select prsn_id, prsn_date_of_birth from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, Date.valueOf("2015-10-10")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Timestamp class");
		
		select(Person.ID, Person.REGISTRATION_DATE_TIME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(Integer.class, Timestamp.class);
		
		isQueryEqual(query, "select prsn_id, prsn_registration_date_time from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, Timestamp.valueOf("2000-10-10 10:10:10")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Time class");
		
		select(Person.ID, Person.SLEEP_TIME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(Integer.class, Time.class);
		
		isQueryEqual(query, "select prsn_id, prsn_sleep_time from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, Time.valueOf("10:10:10")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with Boolean class");
		
		select(Person.ID, Person.GRADUATED)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(Integer.class, Boolean.class);
		
		isQueryEqual(query, "select prsn_id, prsn_graduated from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, true));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with List class");
		
		select(Person.ID, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(Integer.class, List.class);
		
		isQueryEqual(query, "select prsn_id, prsn_certificates from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(id, Arrays.asList("DB2", "Java")));
	}
	
	public void testGetSingleStringResult() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringResult with String Column");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getStringResult();
		
		isQueryEqual(query, "select prsn_name from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, "Hasan");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringResult with Character Column");
		
		select(Person.GENDER)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getStringResult();
		
		isQueryEqual(query, "select prsn_gender from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, "M");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringResult with Short Column");
		
		select(Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getStringResult();
		
		isQueryEqual(query, "select prsn_age from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, "33");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringResult with Integer Column");
		
		select(Person.CPR)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getStringResult();
		
		isQueryEqual(query, "select prsn_cpr from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, "870501236");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringResult with Long Column");
		
		select(Person.ACCOUNT_NO)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getStringResult();
		
		isQueryEqual(query, "select prsn_account_no from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, "12345678901234");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringResult with Float Column");
		
		select(Person.GPA)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getStringResult();
		
		isQueryEqual(query, "select prsn_gpa from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, "3.12");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringResult with Double Column");
		
		select(Person.SALARY)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getStringResult();
		
		isQueryEqual(query, "select prsn_salary from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, "400.5");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringResult with Decimal Column");
		
		select(Person.ANNUAL_INCOME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getStringResult();
		
		isQueryEqual(query, "select prsn_annual_income from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, "500.40");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringResult with Date Column");
		
		select(Person.DATE_OF_BIRTH)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getStringResult();
		
		isQueryEqual(query, "select prsn_date_of_birth from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, "10-10-2015");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringResult with Timestamp Column");
		
		select(Person.REGISTRATION_DATE_TIME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getStringResult();
		
		isQueryEqual(query, "select prsn_registration_date_time from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, "10-10-2000 10:10:10");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringResult with Time Column");
		
		select(Person.SLEEP_TIME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getStringResult();
		
		isQueryEqual(query, "select prsn_sleep_time from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, "10:10:10");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringResult with Boolean Column");
		
		select(Person.GRADUATED)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getStringResult();
		
		isQueryEqual(query, "select prsn_graduated from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, "true");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringResult with Array Column");
		
		select(Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		result = getStringResult();
		
		isQueryEqual(query, "select prsn_certificates from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(result, "DB2,Java");
	}
	
	public void testGetSingleStringEntryWithClasses() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with String class for String");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		select(Person.ID, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_name from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(String.valueOf(id), "Hasan"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with String class for Character");
		
		select(Person.ID, Person.GENDER)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_gender from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(String.valueOf(id), "M"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with String class for Short");
		
		select(Person.ID, Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_age from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(String.valueOf(id), "33"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with String class for Integer");
		
		select(Person.ID, Person.CPR)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_cpr from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(String.valueOf(id), "870501236"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with String class for Float");
		
		select(Person.ID, Person.ACCOUNT_NO)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_account_no from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(String.valueOf(id), "12345678901234"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with String class for Long");
		
		select(Person.ID, Person.GPA)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_gpa from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(String.valueOf(id), "3.12"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with String class for Double");
		
		select(Person.ID, Person.SALARY)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_salary from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(String.valueOf(id), "400.5"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with String class for Decimal");
		
		select(Person.ID, Person.ANNUAL_INCOME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_annual_income from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(String.valueOf(id),"500.40"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with String class for Date");
		
		select(Person.ID, Person.DATE_OF_BIRTH)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_date_of_birth from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(String.valueOf(id), "10-10-2015"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with String class for Timestamp");
		
		select(Person.ID, Person.REGISTRATION_DATE_TIME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_registration_date_time from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(String.valueOf(id), "10-10-2000 10:10:10"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with String class for Time");
		
		select(Person.ID, Person.SLEEP_TIME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_sleep_time from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(String.valueOf(id), "10:10:10"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with String class for Boolean");
		
		select(Person.ID, Person.GRADUATED)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_graduated from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(String.valueOf(id), "true"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryResult with String class for Array");
		
		select(Person.ID, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		entry = getEntryResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_certificates from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isEntryEqual(entry, new SimpleEntry<Object, Object>(String.valueOf(id), "DB2,Java"));
	}
	
	public void testGetObjectList() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObjectList with String Column");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		secondId = insertInto(Table.PERSON)
				.values(Person.NAME, "Fatima")
				.values(Person.GENDER, 'F')
				.values(Person.AGE, 20)
				.values(Person.CPR, 910101265)
				.values(Person.ACCOUNT_NO, 9876543210987l)
				.values(Person.GPA, 3.8)
				.values(Person.SALARY, 600.8)
				.values(Person.ANNUAL_INCOME, new BigDecimal("900.23"))
				.values(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11"))
				.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05"))
				.values(Person.SLEEP_TIME, Time.valueOf("04:04:04"))
				.values(Person.GRADUATED, false)
				.values(Person.CERTIFICATES, Arrays.asList("Postgres", "C++"))
				.executeInsert();
		
		select(Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getObjectList();
		
		isQueryEqual(query, "select prsn_name from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, "Hasan", "Fatima");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObjectList with Character Column");
		
		select(Person.GENDER)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getObjectList();
		
		isQueryEqual(query, "select prsn_gender from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, 'M', 'F');
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObjectList with Short Column");
		
		select(Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getObjectList();
		
		isQueryEqual(query, "select prsn_age from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, (short)33, (short)20);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObjectList with Integer Column");
		
		select(Person.CPR)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getObjectList();
		
		isQueryEqual(query, "select prsn_cpr from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, 870501236, 910101265);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObjectList with Long Column");
		
		select(Person.ACCOUNT_NO)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getObjectList();
		
		isQueryEqual(query, "select prsn_account_no from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, 12345678901234l, 9876543210987l);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObjectList with Float Column");
		
		select(Person.GPA)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getObjectList();
		
		isQueryEqual(query, "select prsn_gpa from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, 3.12f, 3.8f);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObjectList with Double Column");
		
		select(Person.SALARY)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getObjectList();
		
		isQueryEqual(query, "select prsn_salary from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, 400.5, 600.8);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObjectList with Decimal Column");
		
		select(Person.ANNUAL_INCOME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getObjectList();
		
		isQueryEqual(query, "select prsn_annual_income from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new BigDecimal("500.40"), new BigDecimal("900.23"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObjectList with Date Column");
		
		select(Person.DATE_OF_BIRTH)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getObjectList();
		
		isQueryEqual(query, "select prsn_date_of_birth from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, Date.valueOf("2015-10-10"), Date.valueOf("2011-11-11"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObjectList with Timestamp Column");
		
		select(Person.REGISTRATION_DATE_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getObjectList();
		
		isQueryEqual(query, "select prsn_registration_date_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, Timestamp.valueOf("2000-10-10 10:10:10"), Timestamp.valueOf("2005-05-05 05:05:05"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObjectList with Time Column");
		
		select(Person.SLEEP_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getObjectList();
		
		isQueryEqual(query, "select prsn_sleep_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, Time.valueOf("10:10:10"), Time.valueOf("04:04:04"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObjectList with Boolean Column");
		
		select(Person.GRADUATED)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getObjectList();
		
		isQueryEqual(query, "select prsn_graduated from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, true, false);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getObjectList with Array Column");
		
		select(Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getObjectList();
		
		isQueryEqual(query, "select prsn_certificates from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, Arrays.asList("DB2", "Java"), Arrays.asList("Postgres", "C++"));
	}

	public void testGetList() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringList");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		secondId = insertInto(Table.PERSON)
				.values(Person.NAME, "Fatima")
				.values(Person.GENDER, 'F')
				.values(Person.AGE, 20)
				.values(Person.CPR, 910101265)
				.values(Person.ACCOUNT_NO, 9876543210987l)
				.values(Person.GPA, 3.8)
				.values(Person.SALARY, 600.8)
				.values(Person.ANNUAL_INCOME, new BigDecimal("900.23"))
				.values(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11"))
				.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05"))
				.values(Person.SLEEP_TIME, Time.valueOf("04:04:04"))
				.values(Person.GRADUATED, false)
				.values(Person.CERTIFICATES, Arrays.asList("Postgres", "C++"))
				.executeInsert();
		
		select(Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getStringList();
		
		isQueryEqual(query, "select prsn_name from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, "Hasan", "Fatima");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getCharacterList");
		
		select(Person.GENDER)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getCharacterList();
		
		isQueryEqual(query, "select prsn_gender from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, 'M', 'F');
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getShortList");
		
		select(Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getShortList();
		
		isQueryEqual(query, "select prsn_age from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, (short)33, (short)20);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getIntList");
		
		select(Person.CPR)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getIntegerList();
		
		isQueryEqual(query, "select prsn_cpr from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, 870501236, 910101265);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getLongList");
		
		select(Person.ACCOUNT_NO)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getLongList();
		
		isQueryEqual(query, "select prsn_account_no from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, 12345678901234l, 9876543210987l);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getFloatList");
		
		select(Person.GPA)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getFloatList();
		
		isQueryEqual(query, "select prsn_gpa from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, 3.12f, 3.8f);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getDoubleList");
		
		select(Person.SALARY)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getDoubleList();
		
		isQueryEqual(query, "select prsn_salary from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, 400.5, 600.8);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getDecimalList");
		
		select(Person.ANNUAL_INCOME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getDecimalList();
		
		isQueryEqual(query, "select prsn_annual_income from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new BigDecimal("500.40"), new BigDecimal("900.23"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getDateList");
		
		select(Person.DATE_OF_BIRTH)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getDateList();
		
		isQueryEqual(query, "select prsn_date_of_birth from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, Date.valueOf("2015-10-10"), Date.valueOf("2011-11-11"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getTimestampList");
		
		select(Person.REGISTRATION_DATE_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getTimestampList();
		
		isQueryEqual(query, "select prsn_registration_date_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, Timestamp.valueOf("2000-10-10 10:10:10"), Timestamp.valueOf("2005-05-05 05:05:05"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getTimeList");
		
		select(Person.SLEEP_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getTimeList();
		
		isQueryEqual(query, "select prsn_sleep_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, Time.valueOf("10:10:10"), Time.valueOf("04:04:04"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getBooleanList");
		
		select(Person.GRADUATED)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getBooleanList();
		
		isQueryEqual(query, "select prsn_graduated from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, true, false);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getArrayList");
		
		select(Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getArrayList();
		
		isQueryEqual(query, "select prsn_certificates from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, Arrays.asList("DB2", "Java"), Arrays.asList("Postgres", "C++"));
	}
	
	public void testGetEntryList() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with String Column");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		secondId = insertInto(Table.PERSON)
				.values(Person.NAME, "Fatima")
				.values(Person.GENDER, 'F')
				.values(Person.AGE, 20)
				.values(Person.CPR, 910101265)
				.values(Person.ACCOUNT_NO, 9876543210987l)
				.values(Person.GPA, 3.8)
				.values(Person.SALARY, 600.8)
				.values(Person.ANNUAL_INCOME, new BigDecimal("900.23"))
				.values(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11"))
				.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05"))
				.values(Person.SLEEP_TIME, Time.valueOf("04:04:04"))
				.values(Person.GRADUATED, false)
				.values(Person.CERTIFICATES, Arrays.asList("Postgres", "C++"))
				.executeInsert();
		
		select(Person.ID, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList();
		
		isQueryEqual(query, "select prsn_id, prsn_name from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, String>(id, "Hasan"), new SimpleEntry<Integer, String>(secondId, "Fatima"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Character Column");
		
		select(Person.ID, Person.GENDER)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList();
		
		isQueryEqual(query, "select prsn_id, prsn_gender from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Character>(id, 'M'), new SimpleEntry<Integer, Character>(secondId, 'F'));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Short Column");
		
		select(Person.ID, Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList();
		
		isQueryEqual(query, "select prsn_id, prsn_age from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Short>(id, (short)33), new SimpleEntry<Integer, Short>(secondId, (short)20));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Integer Column");
		
		select(Person.ID, Person.CPR)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList();
		
		isQueryEqual(query, "select prsn_id, prsn_cpr from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Integer>(id, 870501236), new SimpleEntry<Integer, Integer>(secondId, 910101265));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Long Column");
		
		select(Person.ID, Person.ACCOUNT_NO)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList();
		
		isQueryEqual(query, "select prsn_id, prsn_account_no from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Long>(id, 12345678901234l), new SimpleEntry<Integer, Long>(secondId, 9876543210987l));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Float Column");
		
		select(Person.ID, Person.GPA)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList();
		
		isQueryEqual(query, "select prsn_id, prsn_gpa from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Float>(id, 3.12f), new SimpleEntry<Integer, Float>(secondId, 3.8f));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Double Column");
		
		select(Person.ID, Person.SALARY)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList();
		
		isQueryEqual(query, "select prsn_id, prsn_salary from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Double>(id, 400.5), new SimpleEntry<Integer, Double>(secondId, 600.8));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Decimal Column");
		
		select(Person.ID, Person.ANNUAL_INCOME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList();
		
		isQueryEqual(query, "select prsn_id, prsn_annual_income from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, BigDecimal>(id, new BigDecimal("500.40")), new SimpleEntry<Integer, BigDecimal>(secondId, new BigDecimal("900.23")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Date Column");
		
		select(Person.ID, Person.DATE_OF_BIRTH)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList();
		
		isQueryEqual(query, "select prsn_id, prsn_date_of_birth from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Date>(id, Date.valueOf("2015-10-10")), new SimpleEntry<Integer, Date>(secondId, Date.valueOf("2011-11-11")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Timestamp Column");
		
		select(Person.ID, Person.REGISTRATION_DATE_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList();
		
		isQueryEqual(query, "select prsn_id, prsn_registration_date_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Timestamp>(id, Timestamp.valueOf("2000-10-10 10:10:10")), new SimpleEntry<Integer, Timestamp>(secondId, Timestamp.valueOf("2005-05-05 05:05:05")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Time Column");
		
		select(Person.ID, Person.SLEEP_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList();
		
		isQueryEqual(query, "select prsn_id, prsn_sleep_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Time>(id, Time.valueOf("10:10:10")), new SimpleEntry<Integer, Time>(secondId, Time.valueOf("04:04:04")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Boolean Column");
		
		select(Person.ID, Person.GRADUATED)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList();
		
		isQueryEqual(query, "select prsn_id, prsn_graduated from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Boolean>(id, true), new SimpleEntry<Integer, Boolean>(secondId, false));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Array Column");
		
		select(Person.ID, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList();
		
		isQueryEqual(query, "select prsn_id, prsn_certificates from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, List>(id, Arrays.asList("DB2", "Java")), new SimpleEntry<Integer, List>(secondId, Arrays.asList("Postgres", "C++")));
	}
	
	public void testGetEntryListWithClasses() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with String class");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		secondId = insertInto(Table.PERSON)
				.values(Person.NAME, "Fatima")
				.values(Person.GENDER, 'F')
				.values(Person.AGE, 20)
				.values(Person.CPR, 910101265)
				.values(Person.ACCOUNT_NO, 9876543210987l)
				.values(Person.GPA, 3.8)
				.values(Person.SALARY, 600.8)
				.values(Person.ANNUAL_INCOME, new BigDecimal("900.23"))
				.values(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11"))
				.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05"))
				.values(Person.SLEEP_TIME, Time.valueOf("04:04:04"))
				.values(Person.GRADUATED, false)
				.values(Person.CERTIFICATES, Arrays.asList("Postgres", "C++"))
				.executeInsert();
		
		select(Person.ID, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(Integer.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_name from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, String>(id, "Hasan"), new SimpleEntry<Integer, String>(secondId, "Fatima"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Character class");
		
		select(Person.ID, Person.GENDER)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(Integer.class, Character.class);
		
		isQueryEqual(query, "select prsn_id, prsn_gender from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Character>(id, 'M'), new SimpleEntry<Integer, Character>(secondId, 'F'));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Short class");
		
		select(Person.ID, Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(Integer.class, Short.class);
		
		isQueryEqual(query, "select prsn_id, prsn_age from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Short>(id, (short)33), new SimpleEntry<Integer, Short>(secondId, (short)20));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Integer class");
		
		select(Person.ID, Person.CPR)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(Integer.class, Integer.class);
		
		isQueryEqual(query, "select prsn_id, prsn_cpr from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Integer>(id, 870501236), new SimpleEntry<Integer, Integer>(secondId, 910101265));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Long class");
		
		select(Person.ID, Person.ACCOUNT_NO)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(Integer.class, Long.class);
		
		isQueryEqual(query, "select prsn_id, prsn_account_no from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Long>(id, 12345678901234l), new SimpleEntry<Integer, Long>(secondId, 9876543210987l));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Float class");
		
		select(Person.ID, Person.GPA)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(Integer.class, Float.class);
		
		isQueryEqual(query, "select prsn_id, prsn_gpa from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Float>(id, 3.12f), new SimpleEntry<Integer, Float>(secondId, 3.8f));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Double class");
		
		select(Person.ID, Person.SALARY)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(Integer.class, Double.class);
		
		isQueryEqual(query, "select prsn_id, prsn_salary from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Double>(id, 400.5), new SimpleEntry<Integer, Double>(secondId, 600.8));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Decimal class");
		
		select(Person.ID, Person.ANNUAL_INCOME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(Integer.class, BigDecimal.class);
		
		isQueryEqual(query, "select prsn_id, prsn_annual_income from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, BigDecimal>(id, new BigDecimal("500.40")), new SimpleEntry<Integer, BigDecimal>(secondId, new BigDecimal("900.23")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Date class");
		
		select(Person.ID, Person.DATE_OF_BIRTH)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(Integer.class, Date.class);
		
		isQueryEqual(query, "select prsn_id, prsn_date_of_birth from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Date>(id, Date.valueOf("2015-10-10")), new SimpleEntry<Integer, Date>(secondId, Date.valueOf("2011-11-11")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Timestamp class");
		
		select(Person.ID, Person.REGISTRATION_DATE_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(Integer.class, Timestamp.class);
		
		isQueryEqual(query, "select prsn_id, prsn_registration_date_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Timestamp>(id, Timestamp.valueOf("2000-10-10 10:10:10")), new SimpleEntry<Integer, Timestamp>(secondId, Timestamp.valueOf("2005-05-05 05:05:05")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Time class");
		
		select(Person.ID, Person.SLEEP_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(Integer.class, Time.class);
		
		isQueryEqual(query, "select prsn_id, prsn_sleep_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Time>(id, Time.valueOf("10:10:10")), new SimpleEntry<Integer, Time>(secondId, Time.valueOf("04:04:04")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Boolean class");
		
		select(Person.ID, Person.GRADUATED)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(Integer.class, Boolean.class);
		
		isQueryEqual(query, "select prsn_id, prsn_graduated from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, Boolean>(id, true), new SimpleEntry<Integer, Boolean>(secondId, false));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Array class");
		
		select(Person.ID, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(Integer.class, List.class);
		
		isQueryEqual(query, "select prsn_id, prsn_certificates from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Integer, List>(id, Arrays.asList("DB2", "Java")), new SimpleEntry<Integer, List>(secondId, Arrays.asList("Postgres", "C++")));
	}
	
	public void testGetStringEntryList() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with String class and String Column");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		secondId = insertInto(Table.PERSON)
				.values(Person.NAME, "Fatima")
				.values(Person.GENDER, 'F')
				.values(Person.AGE, 20)
				.values(Person.CPR, 910101265)
				.values(Person.ACCOUNT_NO, 9876543210987l)
				.values(Person.GPA, 3.8)
				.values(Person.SALARY, 600.8)
				.values(Person.ANNUAL_INCOME, new BigDecimal("900.23"))
				.values(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11"))
				.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05"))
				.values(Person.SLEEP_TIME, Time.valueOf("04:04:04"))
				.values(Person.GRADUATED, false)
				.values(Person.CERTIFICATES, Arrays.asList("Postgres", "C++"))
				.executeInsert();
		
		select(Person.ID, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_name from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<String, String>(String.valueOf(id), "Hasan"), new SimpleEntry<String, String>(String.valueOf(secondId), "Fatima"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with String class and Character Column");
		
		select(Person.ID, Person.GENDER)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_gender from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<String, String>(String.valueOf(id), "M"), new SimpleEntry<String, String>(String.valueOf(secondId), "F"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with String class and Short Column");
		
		select(Person.ID, Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_age from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<String, String>(String.valueOf(id), "33"), new SimpleEntry<String, String>(String.valueOf(secondId), "20"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with String class and Integer Column");
		
		select(Person.ID, Person.CPR)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_cpr from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<String, String>(String.valueOf(id), "870501236"), new SimpleEntry<String, String>(String.valueOf(secondId), "910101265"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with String class and Long Column");
		
		select(Person.ID, Person.ACCOUNT_NO)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_account_no from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<String, String>(String.valueOf(id), "12345678901234"), new SimpleEntry<String, String>(String.valueOf(secondId), "9876543210987"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with String class and Float Column");
		
		select(Person.ID, Person.GPA)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_gpa from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<String, String>(String.valueOf(id), "3.12"), new SimpleEntry<String, String>(String.valueOf(secondId), "3.8"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with String class and Double Column");
		
		select(Person.ID, Person.SALARY)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_salary from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<String, String>(String.valueOf(id), "400.5"), new SimpleEntry<String, String>(String.valueOf(secondId), "600.8"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with String class and Decimal Column");
		
		select(Person.ID, Person.ANNUAL_INCOME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_annual_income from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<String, String>(String.valueOf(id), "500.40"), new SimpleEntry<String, String>(String.valueOf(secondId), "900.23"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with String class and Date Column");
		
		select(Person.ID, Person.DATE_OF_BIRTH)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_date_of_birth from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<String, String>(String.valueOf(id), "10-10-2015"), new SimpleEntry<String, String>(String.valueOf(secondId), "11-11-2011"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with String class and Timestamp Column");
		
		select(Person.ID, Person.REGISTRATION_DATE_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_registration_date_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<String, String>(String.valueOf(id), "10-10-2000 10:10:10"), new SimpleEntry<String, String>(String.valueOf(secondId), "05-05-2005 05:05:05"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with String class and Time Column");
		
		select(Person.ID, Person.SLEEP_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_sleep_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<String, String>(String.valueOf(id), "10:10:10"), new SimpleEntry<String, String>(String.valueOf(secondId), "04:04:04"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with String class and Boolean Column");
		
		select(Person.ID, Person.GRADUATED)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_graduated from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<String, String>(String.valueOf(id), "true"), new SimpleEntry<String, String>(String.valueOf(secondId), "false"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with String class and Array Column");
		
		select(Person.ID, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_certificates from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<String, String>(String.valueOf(id), "DB2,Java"), new SimpleEntry<String, String>(String.valueOf(secondId), "Postgres,C++"));
	}
	
	public void testGetStringList() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringList with String Column");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		secondId = insertInto(Table.PERSON)
				.values(Person.NAME, "Fatima")
				.values(Person.GENDER, 'F')
				.values(Person.AGE, 20)
				.values(Person.CPR, 910101265)
				.values(Person.ACCOUNT_NO, 9876543210987l)
				.values(Person.GPA, 3.8)
				.values(Person.SALARY, 600.8)
				.values(Person.ANNUAL_INCOME, new BigDecimal("900.23"))
				.values(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11"))
				.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05"))
				.values(Person.SLEEP_TIME, Time.valueOf("04:04:04"))
				.values(Person.GRADUATED, false)
				.values(Person.CERTIFICATES, Arrays.asList("Postgres", "C++"))
				.executeInsert();
		
		select(Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getStringList();
		
		isQueryEqual(query, "select prsn_name from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, "Hasan", "Fatima");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringList with Character Column");
		
		select(Person.GENDER)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getStringList();
		
		isQueryEqual(query, "select prsn_gender from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, "M", "F");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringList with Short Column");
		
		select(Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getStringList();
		
		isQueryEqual(query, "select prsn_age from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, "33", "20");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringList with Integer Column");
		
		select(Person.CPR)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getStringList();
		
		isQueryEqual(query, "select prsn_cpr from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, "870501236", "910101265");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringList with Long Column");
		
		select(Person.ACCOUNT_NO)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getStringList();
		
		isQueryEqual(query, "select prsn_account_no from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, "12345678901234", "9876543210987");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringList with Float Column");
		
		select(Person.GPA)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getStringList();
		
		isQueryEqual(query, "select prsn_gpa from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, "3.12", "3.8");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringList with Double Column");
		
		select(Person.SALARY)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getStringList();
		
		isQueryEqual(query, "select prsn_salary from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, "400.5", "600.8");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringList with Decimal Column");
		
		select(Person.ANNUAL_INCOME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getStringList();
		
		isQueryEqual(query, "select prsn_annual_income from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, "500.40", "900.23");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringList with Date Column");
		
		select(Person.DATE_OF_BIRTH)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getStringList();
		
		isQueryEqual(query, "select prsn_date_of_birth from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, "10-10-2015", "11-11-2011");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringList with Timestamp Column");
		
		select(Person.REGISTRATION_DATE_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getStringList();
		
		isQueryEqual(query, "select prsn_registration_date_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, "10-10-2000 10:10:10", "05-05-2005 05:05:05");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringList with Time Column");
		
		select(Person.SLEEP_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getStringList();
		
		isQueryEqual(query, "select prsn_sleep_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, "10:10:10", "04:04:04");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringList with Boolean Column");
		
		select(Person.GRADUATED)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getStringList();
		
		isQueryEqual(query, "select prsn_graduated from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, "true", "false");
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getStringList with Array Column");
		
		select(Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getStringList();
		
		isQueryEqual(query, "select prsn_certificates from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, "DB2,Java", "Postgres,C++");
	}
	
	public void testGetMapResult() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with String Column");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		secondId = insertInto(Table.PERSON)
				.values(Person.NAME, "Fatima")
				.values(Person.GENDER, 'F')
				.values(Person.AGE, 20)
				.values(Person.CPR, 910101265)
				.values(Person.ACCOUNT_NO, 9876543210987l)
				.values(Person.GPA, 3.8)
				.values(Person.SALARY, 600.8)
				.values(Person.ANNUAL_INCOME, new BigDecimal("900.23"))
				.values(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11"))
				.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05"))
				.values(Person.SLEEP_TIME, Time.valueOf("04:04:04"))
				.values(Person.GRADUATED, false)
				.values(Person.CERTIFICATES, Arrays.asList("Postgres", "C++"))
				.executeInsert();
		
		select(Person.ID, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult();
		
		isQueryEqual(query, "select prsn_id, prsn_name from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, "Hasan"), new SimpleEntry<Object, Object>(secondId, "Fatima"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Character Column");
		
		select(Person.ID, Person.GENDER)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult();
		
		isQueryEqual(query, "select prsn_id, prsn_gender from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, 'M'), new SimpleEntry<Object, Object>(secondId, 'F'));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getEntryList with Short Column");
		
		select(Person.ID, Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult();
		
		isQueryEqual(query, "select prsn_id, prsn_age from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, (short)33), new SimpleEntry<Object, Object>(secondId, (short)20));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Integer Column");
		
		select(Person.ID, Person.CPR)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult();
		
		isQueryEqual(query, "select prsn_id, prsn_cpr from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, 870501236), new SimpleEntry<Object, Object>(secondId, 910101265));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Long Column");
		
		select(Person.ID, Person.ACCOUNT_NO)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult();
		
		isQueryEqual(query, "select prsn_id, prsn_account_no from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, 12345678901234l), new SimpleEntry<Object, Object>(secondId, 9876543210987l));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Float Column");
		
		select(Person.ID, Person.GPA)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult();
		
		isQueryEqual(query, "select prsn_id, prsn_gpa from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, 3.12f), new SimpleEntry<Object, Object>(secondId, 3.8f));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Double Column");
		
		select(Person.ID, Person.SALARY)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult();
		
		isQueryEqual(query, "select prsn_id, prsn_salary from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, 400.5), new SimpleEntry<Object, Object>(secondId, 600.8));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Decimal Column");
		
		select(Person.ID, Person.ANNUAL_INCOME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult();
		
		isQueryEqual(query, "select prsn_id, prsn_annual_income from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, new BigDecimal("500.40")), new SimpleEntry<Object, Object>(secondId, new BigDecimal("900.23")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Date Column");
		
		select(Person.ID, Person.DATE_OF_BIRTH)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult();
		
		isQueryEqual(query, "select prsn_id, prsn_date_of_birth from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, Date.valueOf("2015-10-10")), new SimpleEntry<Object, Object>(secondId, Date.valueOf("2011-11-11")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Timestamp Column");
		
		select(Person.ID, Person.REGISTRATION_DATE_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult();
		
		isQueryEqual(query, "select prsn_id, prsn_registration_date_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, Timestamp.valueOf("2000-10-10 10:10:10")), new SimpleEntry<Object, Object>(secondId, Timestamp.valueOf("2005-05-05 05:05:05")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Time Column");
		
		select(Person.ID, Person.SLEEP_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult();
		
		isQueryEqual(query, "select prsn_id, prsn_sleep_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, Time.valueOf("10:10:10")), new SimpleEntry<Object, Object>(secondId, Time.valueOf("04:04:04")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Boolean Column");
		
		select(Person.ID, Person.GRADUATED)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult();
		
		isQueryEqual(query, "select prsn_id, prsn_graduated from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, true), new SimpleEntry<Object, Object>(secondId, false));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Array Column");
		
		select(Person.ID, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult();
		
		isQueryEqual(query, "select prsn_id, prsn_certificates from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, Arrays.asList("DB2", "Java")), new SimpleEntry<Object, Object>(secondId, Arrays.asList("Postgres", "C++")));
	}
	
	public void testGetStringMapResult() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with String class and String Column");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		secondId = insertInto(Table.PERSON)
				.values(Person.NAME, "Fatima")
				.values(Person.GENDER, 'F')
				.values(Person.AGE, 20)
				.values(Person.CPR, 910101265)
				.values(Person.ACCOUNT_NO, 9876543210987l)
				.values(Person.GPA, 3.8)
				.values(Person.SALARY, 600.8)
				.values(Person.ANNUAL_INCOME, new BigDecimal("900.23"))
				.values(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11"))
				.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05"))
				.values(Person.SLEEP_TIME, Time.valueOf("04:04:04"))
				.values(Person.GRADUATED, false)
				.values(Person.CERTIFICATES, Arrays.asList("Postgres", "C++"))
				.executeInsert();
		
		select(Person.ID, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_name from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(String.valueOf(id), "Hasan"), new SimpleEntry<Object, Object>(String.valueOf(secondId), "Fatima"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with String class and Character Column");
		
		select(Person.ID, Person.GENDER)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_gender from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(String.valueOf(id), "M"), new SimpleEntry<Object, Object>(String.valueOf(secondId), "F"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with String class and Short Column");
		
		select(Person.ID, Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_age from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(String.valueOf(id), "33"), new SimpleEntry<Object, Object>(String.valueOf(secondId), "20"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with String class and Integer Column");
		
		select(Person.ID, Person.CPR)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_cpr from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(String.valueOf(id), "870501236"), new SimpleEntry<Object, Object>(String.valueOf(secondId), "910101265"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with String class and Long Column");
		
		select(Person.ID, Person.ACCOUNT_NO)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_account_no from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(String.valueOf(id), "12345678901234"), new SimpleEntry<Object, Object>(String.valueOf(secondId), "9876543210987"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with String class and Float Column");
		
		select(Person.ID, Person.GPA)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_gpa from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(String.valueOf(id), "3.12"), new SimpleEntry<Object, Object>(String.valueOf(secondId), "3.8"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with String class and Double Column");
		
		select(Person.ID, Person.SALARY)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		results = getEntryList(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_salary from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultListEqual(results, new SimpleEntry<Object, Object>(String.valueOf(id), "400.5"), new SimpleEntry<Object, Object>(String.valueOf(secondId), "600.8"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with String class and Decimal Column");
		
		select(Person.ID, Person.ANNUAL_INCOME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_annual_income from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(String.valueOf(id), "500.40"), new SimpleEntry<Object, Object>(String.valueOf(secondId), "900.23"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with String class and Date Column");
		
		select(Person.ID, Person.DATE_OF_BIRTH)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_date_of_birth from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(String.valueOf(id), "10-10-2015"), new SimpleEntry<Object, Object>(String.valueOf(secondId), "11-11-2011"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with String class and Timestamp Column");
		
		select(Person.ID, Person.REGISTRATION_DATE_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_registration_date_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(String.valueOf(id), "10-10-2000 10:10:10"), new SimpleEntry<Object, Object>(String.valueOf(secondId), "05-05-2005 05:05:05"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with String class and Time Column");
		
		select(Person.ID, Person.SLEEP_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_sleep_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(String.valueOf(id), "10:10:10"), new SimpleEntry<Object, Object>(String.valueOf(secondId), "04:04:04"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with String class and Boolean Column");
		
		select(Person.ID, Person.GRADUATED)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_graduated from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(String.valueOf(id), "true"), new SimpleEntry<Object, Object>(String.valueOf(secondId), "false"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with String class and Array Column");
		
		select(Person.ID, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(String.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_certificates from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(String.valueOf(id), "DB2,Java"), new SimpleEntry<Object, Object>(String.valueOf(secondId), "Postgres,C++"));
	}
	
	public void testGetMapResultWithClasses() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with String class");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		secondId = insertInto(Table.PERSON)
				.values(Person.NAME, "Fatima")
				.values(Person.GENDER, 'F')
				.values(Person.AGE, 20)
				.values(Person.CPR, 910101265)
				.values(Person.ACCOUNT_NO, 9876543210987l)
				.values(Person.GPA, 3.8)
				.values(Person.SALARY, 600.8)
				.values(Person.ANNUAL_INCOME, new BigDecimal("900.23"))
				.values(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11"))
				.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05"))
				.values(Person.SLEEP_TIME, Time.valueOf("04:04:04"))
				.values(Person.GRADUATED, false)
				.values(Person.CERTIFICATES, Arrays.asList("Postgres", "C++"))
				.executeInsert();
		
		select(Person.ID, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(Integer.class, String.class);
		
		isQueryEqual(query, "select prsn_id, prsn_name from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, "Hasan"), new SimpleEntry<Object, Object>(secondId, "Fatima"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Character class");
		
		select(Person.ID, Person.GENDER)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(Integer.class, Character.class);
		
		isQueryEqual(query, "select prsn_id, prsn_gender from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, 'M'), new SimpleEntry<Object, Object>(secondId, 'F'));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Short class");
		
		select(Person.ID, Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(Integer.class, Short.class);
		
		isQueryEqual(query, "select prsn_id, prsn_age from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, (short)33), new SimpleEntry<Object, Object>(secondId, (short)20));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Integer class");
		
		select(Person.ID, Person.CPR)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(Integer.class, Integer.class);
		
		isQueryEqual(query, "select prsn_id, prsn_cpr from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, 870501236), new SimpleEntry<Object, Object>(secondId, 910101265));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Long class");
		
		select(Person.ID, Person.ACCOUNT_NO)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(Integer.class, Long.class);
		
		isQueryEqual(query, "select prsn_id, prsn_account_no from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, 12345678901234l), new SimpleEntry<Object, Object>(secondId, 9876543210987l));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Float class");
		
		select(Person.ID, Person.GPA)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(Integer.class, Float.class);
		
		isQueryEqual(query, "select prsn_id, prsn_gpa from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, 3.12f), new SimpleEntry<Object, Object>(secondId, 3.8f));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Double class");
		
		select(Person.ID, Person.SALARY)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(Integer.class, Double.class);
		
		isQueryEqual(query, "select prsn_id, prsn_salary from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, 400.5), new SimpleEntry<Object, Object>(secondId, 600.8));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Decimal class");
		
		select(Person.ID, Person.ANNUAL_INCOME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(Integer.class, BigDecimal.class);
		
		isQueryEqual(query, "select prsn_id, prsn_annual_income from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, new BigDecimal("500.40")), new SimpleEntry<Object, Object>(secondId, new BigDecimal("900.23")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Date class");
		
		select(Person.ID, Person.DATE_OF_BIRTH)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(Integer.class, Date.class);
		
		isQueryEqual(query, "select prsn_id, prsn_date_of_birth from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, Date.valueOf("2015-10-10")), new SimpleEntry<Object, Object>(secondId, Date.valueOf("2011-11-11")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Timestamp class");
		
		select(Person.ID, Person.REGISTRATION_DATE_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(Integer.class, Timestamp.class);
		
		isQueryEqual(query, "select prsn_id, prsn_registration_date_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, Timestamp.valueOf("2000-10-10 10:10:10")), new SimpleEntry<Object, Object>(secondId, Timestamp.valueOf("2005-05-05 05:05:05")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Time class");
		
		select(Person.ID, Person.SLEEP_TIME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(Integer.class, Time.class);
		
		isQueryEqual(query, "select prsn_id, prsn_sleep_time from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, Time.valueOf("10:10:10")), new SimpleEntry<Object, Object>(secondId, Time.valueOf("04:04:04")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with Boolean class");
		
		select(Person.ID, Person.GRADUATED)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(Integer.class, Boolean.class);
		
		isQueryEqual(query, "select prsn_id, prsn_graduated from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, true), new SimpleEntry<Object, Object>(secondId, false));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query with getMapResult with List class");
		
		select(Person.ID, Person.CERTIFICATES)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		resultsMap = getMapResult(Integer.class, List.class);
		
		isQueryEqual(query, "select prsn_id, prsn_certificates from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isMapEqual(resultsMap, new SimpleEntry<Object, Object>(id, Arrays.asList("DB2", "Java")), new SimpleEntry<Object, Object>(secondId, Arrays.asList("Postgres", "C++")));
	}
	
	public void testGetRecordCount() throws Exception {
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test getRecordCount with single record");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.executeInsert();
		
		secondId = insertInto(Table.PERSON)
				.values(Person.NAME, "Fatima")
				.values(Person.GENDER, 'F')
				.values(Person.AGE, 20)
				.values(Person.CPR, 910101265)
				.values(Person.ACCOUNT_NO, 9876543210987l)
				.values(Person.GPA, 3.8)
				.values(Person.SALARY, 600.8)
				.values(Person.ANNUAL_INCOME, new BigDecimal("900.23"))
				.values(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11"))
				.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05"))
				.values(Person.SLEEP_TIME, Time.valueOf("04:04:04"))
				.values(Person.GRADUATED, false)
				.values(Person.CERTIFICATES, Arrays.asList("Postgres", "C++"))
				.executeInsert();
		
		select(Person.ID, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		count = getRecordCount();
		
		isQueryEqual(query, "select prsn_id, prsn_name from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isResultEqual(count, 1);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test getRecordCount with multiple records");
		
		select(Person.ID, Person.NAME)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		count = getRecordCount();
		
		isQueryEqual(query, "select prsn_id, prsn_name from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isResultEqual(count, 2);
	}
	
	public void testGetRecord() throws Exception {

		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.values(Person.CITY_ID, 1)
			.values(Person.SCHOOL_ID, 1)
			.values(Person.COMPANY_ID, 1)
			.values(Person.COUNTRY_ID, 1)
			.values(Person.HOME_ADDRESS_ID, 1)
			.values(Person.WORK_ADDRESS_ID, 2)
			.executeInsert();
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(Person.CITY_ID, Person.SCHOOL_ID)
		.select(Person.COMPANY_ID, Person.COUNTRY_ID)
		.select(Person.HOME_ADDRESS_ID, Person.WORK_ADDRESS_ID)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, prsn_city_id, prsn_school_id, prsn_company_id, prsn_country_id, prsn_home_address_id, prsn_work_address_id from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(record, 
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")),
						entry(Person.CITY_ID, 1),
						entry(Person.SCHOOL_ID, 1),
						entry(Person.COMPANY_ID, 1),
						entry(Person.COUNTRY_ID, 1),
						entry(Person.HOME_ADDRESS_ID, 1),
						entry(Person.WORK_ADDRESS_ID, 2));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record with joins");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(City.ID, City.NAME)
		.select(Company.ID, Company.NAME)
		.select(Country.ID, Country.NAME)
		.select(School.ID, School.NAME)
		.from(Table.PERSON)
		.innerJoin(Table.CITY).on(Person.CITY_ID.equal(City.ID))
		.rightJoin(Table.COMPANY).on(Person.COMPANY_ID.equal(Company.ID))
		.leftJoin(Table.COUNTRY).on(Person.COUNTRY_ID.equal(Country.ID))
		.fullJoin(Table.SCHOOL).on(Person.SCHOOL_ID.equal(School.ID))
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, ct_id, ct_name, cmp_id, cmp_name, cnt_id, cnt_name, sch_id, sch_name from public.person inner join public.city on prsn_city_id = ct_id right join public.company on prsn_company_id = cmp_id left join public.country on prsn_country_id = cnt_id full join public.school on prsn_school_id = sch_id where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(record, 
				entry(Person.ID, id),
				entry(Person.NAME, "Hasan"),
				entry(Person.GENDER, 'M'),
				entry(Person.AGE, (short)33),
				entry(Person.CPR, 870501236),
				entry(Person.ACCOUNT_NO, 12345678901234l),
				entry(Person.GPA, 3.12f),
				entry(Person.SALARY, 400.5),
				entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
				entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
				entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
				entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
				entry(Person.GRADUATED, true),
				entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")),
				entry(City.ID, 1),
				entry(City.NAME, "Manama"),
				entry(Company.ID, 1),
				entry(Company.NAME, "Maraei"),
				entry(Country.ID, 1),
				entry(Country.NAME, "Kuwait"),
				entry(School.ID, 1),
				entry(School.NAME, "Bin Khuldon"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query select clause with alias");
		
		select(Person.ID.as("person_id"), Person.NAME.as("person_name"), 
				Person.GENDER.as("person_gender"), Person.AGE.as("person_age"))
		.select(Person.CPR.as("person_cpr"), Person.ACCOUNT_NO.as("person_account_no"), 
				Person.GPA.as("person_gpa"))
		.select(Person.SALARY.as("person_salary"), Person.ANNUAL_INCOME.as("person_annual_income"), 
				Person.DATE_OF_BIRTH.as("person_date_of_birth"))
		.select(Person.REGISTRATION_DATE_TIME.as("person_registration_date_time"), Person.SLEEP_TIME.as("person_sleep_time"))
		.select(Person.GRADUATED.as("person_graduated"), Person.CERTIFICATES.as("person_certificates"))
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id as person_id, prsn_name as person_name, prsn_gender as person_gender, prsn_age as person_age, prsn_cpr as person_cpr, prsn_account_no as person_account_no, prsn_gpa as person_gpa, prsn_salary as person_salary, prsn_annual_income as person_annual_income, prsn_date_of_birth as person_date_of_birth, prsn_registration_date_time as person_registration_date_time, prsn_sleep_time as person_sleep_time, prsn_graduated as person_graduated, prsn_certificates as person_certificates from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(record, 
				entry(Person.ID, id),
				entry(Person.NAME, "Hasan"),
				entry(Person.GENDER, 'M'),
				entry(Person.AGE, (short)33),
				entry(Person.CPR, 870501236),
				entry(Person.ACCOUNT_NO, 12345678901234l),
				entry(Person.GPA, 3.12f),
				entry(Person.SALARY, 400.5),
				entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
				entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
				entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
				entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
				entry(Person.GRADUATED, true),
				entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query select clause with math/date");
		
		select(Person.AGE.plus(1))
		.select(Person.CPR.minus(2), 
				Person.ACCOUNT_NO.divide(Person.ACCOUNT_NO), 
				Person.GPA.multiply(Person.GPA))
		.select(Person.SALARY.plus(Person.SALARY), 
				Person.ANNUAL_INCOME.divide(Person.ANNUAL_INCOME), 
				Person.DATE_OF_BIRTH.plusMinutes(1))
		.select(Person.REGISTRATION_DATE_TIME.minusMinutes(3), 
				Person.SLEEP_TIME.minusHours(3))
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_age + ? as prsn_age_plus, prsn_cpr - ? as prsn_cpr_minus, prsn_account_no / prsn_account_no as prsn_account_no_divide, prsn_gpa * prsn_gpa as prsn_gpa_multiply, prsn_salary + prsn_salary as prsn_salary_plus, prsn_annual_income / prsn_annual_income as prsn_annual_income_divide, prsn_date_of_birth + interval '1 minutes' as prsn_date_of_birth_plus, prsn_registration_date_time - interval '3 minutes' as prsn_registration_date_time_minus, prsn_sleep_time - interval '3 hours' as prsn_sleep_time_minus from public.person where prsn_id = ?");
		isParametersEquals(parameters, 1, 2, id);
		isRecordEqual(record, 
				entry(Person.AGE.plus(1), (short)34),
				entry(Person.CPR.minus(2), 870501234),
				entry(Person.ACCOUNT_NO.divide(Person.ACCOUNT_NO), 1l),
				entry(Person.GPA.multiply(Person.GPA), 9.734399f),
				entry(Person.SALARY.plus(Person.SALARY), 801.0),
				entry(Person.ANNUAL_INCOME.divide(Person.ANNUAL_INCOME), new BigDecimal("1.00000000000000000000")),
				entry(Person.DATE_OF_BIRTH.plusMinutes(1), Date.valueOf("2015-10-10")),
				entry(Person.REGISTRATION_DATE_TIME.minusMinutes(3), Timestamp.valueOf("2000-10-10 10:07:10.0")),
				entry(Person.SLEEP_TIME.minusHours(3), Time.valueOf("07:10:10")));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query select clause with aggregation function");
		
		select(sum(Person.AGE))
		.select(count(Person.CPR), 
				average(Person.ACCOUNT_NO), 
				min(Person.GPA))
		.select(max(Person.SALARY))
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select sum(prsn_age) as prsn_age_sum, count(prsn_cpr) as prsn_cpr_count, avg(prsn_account_no) as prsn_account_no_avg, min(prsn_gpa) as prsn_gpa_min, max(prsn_salary) as prsn_salary_max from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(record, 
				entry(sum(Person.AGE), (short)33),
				entry(count(Person.CPR), 1),
				entry(average(Person.ACCOUNT_NO), 12345678901234l),
				entry(min(Person.GPA), 3.12f),
				entry(max(Person.SALARY), 400.5));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query select clause with alias table");
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD.of("home_address").as("home_address_road")
				, Address.BLOCK.of("home_address"))
		.select(Address.ROAD.of("work_address"), Address.BLOCK.of("work_address"))
		.from(Table.PERSON.as("person"))
		.join(Table.ADDRESS.as("home_address"))
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID.of("home_address")))
		.join(Table.ADDRESS.as("work_address"))
			.on(Person.WORK_ADDRESS_ID.equal(Address.ID.of("work_address")))
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_name, prsn_gender, prsn_age, home_address.addr_road as home_address_road, home_address.addr_block as home_address_addr_block, work_address.addr_road as work_address_addr_road, work_address.addr_block as work_address_addr_block from public.person as person inner join public.address as home_address on prsn_home_address_id = home_address.addr_id inner join public.address as work_address on prsn_work_address_id = work_address.addr_id where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(record,
				entry(Person.NAME, "Hasan"),
				entry(Person.GENDER, 'M'),
				entry(Person.AGE, (short)33),
				entry(Address.ROAD.of("home_address"), "Road 1"),
				entry(Address.BLOCK.of("home_address"), "Block 1"),
				entry(Address.ROAD.of("work_address"), "Road 2"),
				entry(Address.BLOCK.of("work_address"), "Block 2"));
	}
	
	public void testGetRecordList() throws Exception {

		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record List");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.values(Person.CITY_ID, 1)
			.values(Person.SCHOOL_ID, 1)
			.values(Person.COMPANY_ID, 1)
			.values(Person.COUNTRY_ID, 1)
			.values(Person.HOME_ADDRESS_ID, 1)
			.values(Person.WORK_ADDRESS_ID, 2)
			.executeInsert();
		
		secondId = insertInto(Table.PERSON)
				.values(Person.NAME, "Fatima")
				.values(Person.GENDER, 'F')
				.values(Person.AGE, 20)
				.values(Person.CPR, 910101265)
				.values(Person.ACCOUNT_NO, 9876543210987l)
				.values(Person.GPA, 3.8)
				.values(Person.SALARY, 600.8)
				.values(Person.ANNUAL_INCOME, new BigDecimal("900.23"))
				.values(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11"))
				.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05"))
				.values(Person.SLEEP_TIME, Time.valueOf("04:04:04"))
				.values(Person.GRADUATED, false)
				.values(Person.CERTIFICATES, Arrays.asList("Postgres", "C++"))
				.values(Person.CITY_ID, 1)
				.values(Person.SCHOOL_ID, 1)
				.values(Person.COMPANY_ID, 1)
				.values(Person.COUNTRY_ID, 1)
				.values(Person.HOME_ADDRESS_ID, 1)
				.values(Person.WORK_ADDRESS_ID, 2)
				.executeInsert();
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(Person.CITY_ID, Person.SCHOOL_ID)
		.select(Person.COMPANY_ID, Person.COUNTRY_ID)
		.select(Person.HOME_ADDRESS_ID, Person.WORK_ADDRESS_ID)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		records = getRecordList();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, prsn_city_id, prsn_school_id, prsn_company_id, prsn_country_id, prsn_home_address_id, prsn_work_address_id from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(records, 
						map(
							entry(Person.ID, id),
							entry(Person.NAME, "Hasan"),
							entry(Person.GENDER, 'M'),
							entry(Person.AGE, (short)33),
							entry(Person.CPR, 870501236),
							entry(Person.ACCOUNT_NO, 12345678901234l),
							entry(Person.GPA, 3.12f),
							entry(Person.SALARY, 400.5),
							entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
							entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
							entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
							entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
							entry(Person.GRADUATED, true),
							entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")),
							entry(Person.CITY_ID, 1),
							entry(Person.SCHOOL_ID, 1),
							entry(Person.COMPANY_ID, 1),
							entry(Person.COUNTRY_ID, 1),
							entry(Person.HOME_ADDRESS_ID, 1),
							entry(Person.WORK_ADDRESS_ID, 2)
						),
						map(
							entry(Person.ID, secondId),
							entry(Person.NAME, "Fatima"),
							entry(Person.GENDER, 'F'),
							entry(Person.AGE, (short)20),
							entry(Person.CPR, 910101265),
							entry(Person.ACCOUNT_NO, 9876543210987l),
							entry(Person.GPA, 3.8f),
							entry(Person.SALARY, 600.8),
							entry(Person.ANNUAL_INCOME, new BigDecimal("900.23")),
							entry(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11")),
							entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05")),
							entry(Person.SLEEP_TIME, Time.valueOf("04:04:04")),
							entry(Person.GRADUATED, false),
							entry(Person.CERTIFICATES, Arrays.asList("Postgres", "C++")),
							entry(Person.CITY_ID, 1),
							entry(Person.SCHOOL_ID, 1),
							entry(Person.COMPANY_ID, 1),
							entry(Person.COUNTRY_ID, 1),
							entry(Person.HOME_ADDRESS_ID, 1),
							entry(Person.WORK_ADDRESS_ID, 2)
						)
				);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record List with joins");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(City.ID, City.NAME)
		.select(Company.ID, Company.NAME)
		.select(Country.ID, Country.NAME)
		.select(School.ID, School.NAME)
		.from(Table.PERSON)
		.innerJoin(Table.CITY).on(Person.CITY_ID.equal(City.ID))
		.rightJoin(Table.COMPANY).on(Person.COMPANY_ID.equal(Company.ID))
		.leftJoin(Table.COUNTRY).on(Person.COUNTRY_ID.equal(Country.ID))
		.fullJoin(Table.SCHOOL).on(Person.SCHOOL_ID.equal(School.ID))
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		records = getRecordList();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, ct_id, ct_name, cmp_id, cmp_name, cnt_id, cnt_name, sch_id, sch_name from public.person inner join public.city on prsn_city_id = ct_id right join public.company on prsn_company_id = cmp_id left join public.country on prsn_country_id = cnt_id full join public.school on prsn_school_id = sch_id where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(records, 
				map(
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")),
						entry(City.ID, 1),
						entry(City.NAME, "Manama"),
						entry(Company.ID, 1),
						entry(Company.NAME, "Maraei"),
						entry(Country.ID, 1),
						entry(Country.NAME, "Kuwait"),
						entry(School.ID, 1),
						entry(School.NAME, "Bin Khuldon")
				),
				map(
					entry(Person.ID, secondId),
					entry(Person.NAME, "Fatima"),
					entry(Person.GENDER, 'F'),
					entry(Person.AGE, (short)20),
					entry(Person.CPR, 910101265),
					entry(Person.ACCOUNT_NO, 9876543210987l),
					entry(Person.GPA, 3.8f),
					entry(Person.SALARY, 600.8),
					entry(Person.ANNUAL_INCOME, new BigDecimal("900.23")),
					entry(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11")),
					entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05")),
					entry(Person.SLEEP_TIME, Time.valueOf("04:04:04")),
					entry(Person.GRADUATED, false),
					entry(Person.CERTIFICATES, Arrays.asList("Postgres", "C++")),
					entry(City.ID, 1),
					entry(City.NAME, "Manama"),
					entry(Company.ID, 1),
					entry(Company.NAME, "Maraei"),
					entry(Country.ID, 1),
					entry(Country.NAME, "Kuwait"),
					entry(School.ID, 1),
					entry(School.NAME, "Bin Khuldon")
				)
		);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record List with alias");
		
		select(Person.ID.as("person_id"), Person.NAME.as("person_name"), 
				Person.GENDER.as("person_gender"), Person.AGE.as("person_age"))
		.select(Person.CPR.as("person_cpr"), Person.ACCOUNT_NO.as("person_account_no"), 
				Person.GPA.as("person_gpa"))
		.select(Person.SALARY.as("person_salary"), Person.ANNUAL_INCOME.as("person_annual_income"), 
				Person.DATE_OF_BIRTH.as("person_date_of_birth"))
		.select(Person.REGISTRATION_DATE_TIME.as("person_registration_date_time"), Person.SLEEP_TIME.as("person_sleep_time"))
		.select(Person.GRADUATED.as("person_graduated"), Person.CERTIFICATES.as("person_certificates"))
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		records = getRecordList();
		
		isQueryEqual(query, "select prsn_id as person_id, prsn_name as person_name, prsn_gender as person_gender, prsn_age as person_age, prsn_cpr as person_cpr, prsn_account_no as person_account_no, prsn_gpa as person_gpa, prsn_salary as person_salary, prsn_annual_income as person_annual_income, prsn_date_of_birth as person_date_of_birth, prsn_registration_date_time as person_registration_date_time, prsn_sleep_time as person_sleep_time, prsn_graduated as person_graduated, prsn_certificates as person_certificates from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(records, 
				map(
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
				),
				map(
					entry(Person.ID, secondId),
					entry(Person.NAME, "Fatima"),
					entry(Person.GENDER, 'F'),
					entry(Person.AGE, (short)20),
					entry(Person.CPR, 910101265),
					entry(Person.ACCOUNT_NO, 9876543210987l),
					entry(Person.GPA, 3.8f),
					entry(Person.SALARY, 600.8),
					entry(Person.ANNUAL_INCOME, new BigDecimal("900.23")),
					entry(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11")),
					entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05")),
					entry(Person.SLEEP_TIME, Time.valueOf("04:04:04")),
					entry(Person.GRADUATED, false),
					entry(Person.CERTIFICATES, Arrays.asList("Postgres", "C++"))
				)
		);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record List with math/date");
		
		select(Person.AGE.plus(1))
		.select(Person.CPR.minus(2), 
				Person.ACCOUNT_NO.divide(Person.ACCOUNT_NO), 
				Person.GPA.multiply(Person.GPA))
		.select(Person.SALARY.plus(Person.SALARY), 
				Person.ANNUAL_INCOME.divide(Person.ANNUAL_INCOME), 
				Person.DATE_OF_BIRTH.plusMinutes(1))
		.select(Person.REGISTRATION_DATE_TIME.minusMinutes(3), 
				Person.SLEEP_TIME.minusHours(3))
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		records = getRecordList();
		
		isQueryEqual(query, "select prsn_age + ? as prsn_age_plus, prsn_cpr - ? as prsn_cpr_minus, prsn_account_no / prsn_account_no as prsn_account_no_divide, prsn_gpa * prsn_gpa as prsn_gpa_multiply, prsn_salary + prsn_salary as prsn_salary_plus, prsn_annual_income / prsn_annual_income as prsn_annual_income_divide, prsn_date_of_birth + interval '1 minutes' as prsn_date_of_birth_plus, prsn_registration_date_time - interval '3 minutes' as prsn_registration_date_time_minus, prsn_sleep_time - interval '3 hours' as prsn_sleep_time_minus from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, 1, 2, id, secondId);
		isRecordListEqual(records, 
				map(
						entry(Person.AGE.plus(1), (short)34),
						entry(Person.CPR.minus(2), 870501234),
						entry(Person.ACCOUNT_NO.divide(Person.ACCOUNT_NO), 1l),
						entry(Person.GPA.multiply(Person.GPA), 9.734399f),
						entry(Person.SALARY.plus(Person.SALARY), 801.0),
						entry(Person.ANNUAL_INCOME.divide(Person.ANNUAL_INCOME), new BigDecimal("1.00000000000000000000")),
						entry(Person.DATE_OF_BIRTH.plusMinutes(1), Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME.minusMinutes(3), Timestamp.valueOf("2000-10-10 10:07:10.0")),
						entry(Person.SLEEP_TIME.minusHours(3), Time.valueOf("07:10:10"))
				),
				map(
						entry(Person.AGE.plus(1), (short)21),
						entry(Person.CPR.minus(2), 910101263),
						entry(Person.ACCOUNT_NO.divide(Person.ACCOUNT_NO), 1l),
						entry(Person.GPA.multiply(Person.GPA), 14.44f),
						entry(Person.SALARY.plus(Person.SALARY), 1201.6),
						entry(Person.ANNUAL_INCOME.divide(Person.ANNUAL_INCOME), new BigDecimal("1.00000000000000000000")),
						entry(Person.DATE_OF_BIRTH.plusMinutes(1), Date.valueOf("2011-11-11")),
						entry(Person.REGISTRATION_DATE_TIME.minusMinutes(3), Timestamp.valueOf("2005-05-05 05:02:05.0")),
						entry(Person.SLEEP_TIME.minusHours(3), Time.valueOf("01:04:04"))
				)
		);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record List with aggregation function");
		
		select(sum(Person.AGE))
		.select(count(Person.CPR), 
				average(Person.ACCOUNT_NO), 
				min(Person.GPA))
		.select(max(Person.SALARY))
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		records = getRecordList();
		
		isQueryEqual(query, "select sum(prsn_age) as prsn_age_sum, count(prsn_cpr) as prsn_cpr_count, avg(prsn_account_no) as prsn_account_no_avg, min(prsn_gpa) as prsn_gpa_min, max(prsn_salary) as prsn_salary_max from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(records, 
				map(
						entry(sum(Person.AGE), (short)53),
						entry(count(Person.CPR), 2),
						entry(average(Person.ACCOUNT_NO), 11111111056110l),
						entry(min(Person.GPA), 3.12f),
						entry(max(Person.SALARY), 600.8)
				)
		);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record List with alias table");
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD.of("home_address").as("home_address_road")
				, Address.BLOCK.of("home_address"))
		.select(Address.ROAD.of("work_address"), Address.BLOCK.of("work_address"))
		.from(Table.PERSON.as("person"))
		.join(Table.ADDRESS.as("home_address"))
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID.of("home_address")))
		.join(Table.ADDRESS.as("work_address"))
			.on(Person.WORK_ADDRESS_ID.equal(Address.ID.of("work_address")))
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		records = getRecordList();
		
		isQueryEqual(query, "select prsn_name, prsn_gender, prsn_age, home_address.addr_road as home_address_road, home_address.addr_block as home_address_addr_block, work_address.addr_road as work_address_addr_road, work_address.addr_block as work_address_addr_block from public.person as person inner join public.address as home_address on prsn_home_address_id = home_address.addr_id inner join public.address as work_address on prsn_work_address_id = work_address.addr_id where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(records, 
				map(
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Address.ROAD.of("home_address"), "Road 1"),
						entry(Address.BLOCK.of("home_address"), "Block 1"),
						entry(Address.ROAD.of("work_address"), "Road 2"),
						entry(Address.BLOCK.of("work_address"), "Block 2")
				),
				map(
						entry(Person.NAME, "Fatima"),
						entry(Person.GENDER, 'F'),
						entry(Person.AGE, (short)20),
						entry(Address.ROAD.of("home_address"), "Road 1"),
						entry(Address.BLOCK.of("home_address"), "Block 1"),
						entry(Address.ROAD.of("work_address"), "Road 2"),
						entry(Address.BLOCK.of("work_address"), "Block 2")
				)
		);
	}
	
	public void testGetRecordMap() throws Exception {

		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record Map");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.values(Person.CITY_ID, 1)
			.values(Person.SCHOOL_ID, 1)
			.values(Person.COMPANY_ID, 1)
			.values(Person.COUNTRY_ID, 1)
			.values(Person.HOME_ADDRESS_ID, 1)
			.values(Person.WORK_ADDRESS_ID, 2)
			.executeInsert();
		
		secondId = insertInto(Table.PERSON)
				.values(Person.NAME, "Fatima")
				.values(Person.GENDER, 'F')
				.values(Person.AGE, 20)
				.values(Person.CPR, 910101265)
				.values(Person.ACCOUNT_NO, 9876543210987l)
				.values(Person.GPA, 3.8)
				.values(Person.SALARY, 600.8)
				.values(Person.ANNUAL_INCOME, new BigDecimal("900.23"))
				.values(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11"))
				.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05"))
				.values(Person.SLEEP_TIME, Time.valueOf("04:04:04"))
				.values(Person.GRADUATED, false)
				.values(Person.CERTIFICATES, Arrays.asList("Postgres", "C++"))
				.values(Person.CITY_ID, 1)
				.values(Person.SCHOOL_ID, 1)
				.values(Person.COMPANY_ID, 1)
				.values(Person.COUNTRY_ID, 1)
				.values(Person.HOME_ADDRESS_ID, 1)
				.values(Person.WORK_ADDRESS_ID, 2)
				.executeInsert();
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(Person.CITY_ID, Person.SCHOOL_ID)
		.select(Person.COMPANY_ID, Person.COUNTRY_ID)
		.select(Person.HOME_ADDRESS_ID, Person.WORK_ADDRESS_ID)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		recordsMap = getRecordMap();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, prsn_city_id, prsn_school_id, prsn_company_id, prsn_country_id, prsn_home_address_id, prsn_work_address_id from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordMapEqual(recordsMap, 
						entry(id, map(
							entry(Person.ID, id),
							entry(Person.NAME, "Hasan"),
							entry(Person.GENDER, 'M'),
							entry(Person.AGE, (short)33),
							entry(Person.CPR, 870501236),
							entry(Person.ACCOUNT_NO, 12345678901234l),
							entry(Person.GPA, 3.12f),
							entry(Person.SALARY, 400.5),
							entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
							entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
							entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
							entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
							entry(Person.GRADUATED, true),
							entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")),
							entry(Person.CITY_ID, 1),
							entry(Person.SCHOOL_ID, 1),
							entry(Person.COMPANY_ID, 1),
							entry(Person.COUNTRY_ID, 1),
							entry(Person.HOME_ADDRESS_ID, 1),
							entry(Person.WORK_ADDRESS_ID, 2)
						)),
						entry(secondId, map(
							entry(Person.ID, secondId),
							entry(Person.NAME, "Fatima"),
							entry(Person.GENDER, 'F'),
							entry(Person.AGE, (short)20),
							entry(Person.CPR, 910101265),
							entry(Person.ACCOUNT_NO, 9876543210987l),
							entry(Person.GPA, 3.8f),
							entry(Person.SALARY, 600.8),
							entry(Person.ANNUAL_INCOME, new BigDecimal("900.23")),
							entry(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11")),
							entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05")),
							entry(Person.SLEEP_TIME, Time.valueOf("04:04:04")),
							entry(Person.GRADUATED, false),
							entry(Person.CERTIFICATES, Arrays.asList("Postgres", "C++")),
							entry(Person.CITY_ID, 1),
							entry(Person.SCHOOL_ID, 1),
							entry(Person.COMPANY_ID, 1),
							entry(Person.COUNTRY_ID, 1),
							entry(Person.HOME_ADDRESS_ID, 1),
							entry(Person.WORK_ADDRESS_ID, 2)
						))
				);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record Map with joins");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(City.ID, City.NAME)
		.select(Company.ID, Company.NAME)
		.select(Country.ID, Country.NAME)
		.select(School.ID, School.NAME)
		.from(Table.PERSON)
		.innerJoin(Table.CITY).on(Person.CITY_ID.equal(City.ID))
		.rightJoin(Table.COMPANY).on(Person.COMPANY_ID.equal(Company.ID))
		.leftJoin(Table.COUNTRY).on(Person.COUNTRY_ID.equal(Country.ID))
		.fullJoin(Table.SCHOOL).on(Person.SCHOOL_ID.equal(School.ID))
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		recordsMap = getRecordMap();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, ct_id, ct_name, cmp_id, cmp_name, cnt_id, cnt_name, sch_id, sch_name from public.person inner join public.city on prsn_city_id = ct_id right join public.company on prsn_company_id = cmp_id left join public.country on prsn_country_id = cnt_id full join public.school on prsn_school_id = sch_id where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordMapEqual(recordsMap, 
				entry(id, map(
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java")),
						entry(City.ID, 1),
						entry(City.NAME, "Manama"),
						entry(Company.ID, 1),
						entry(Company.NAME, "Maraei"),
						entry(Country.ID, 1),
						entry(Country.NAME, "Kuwait"),
						entry(School.ID, 1),
						entry(School.NAME, "Bin Khuldon")
				))
				,
				entry(secondId, map(
					entry(Person.ID, secondId),
					entry(Person.NAME, "Fatima"),
					entry(Person.GENDER, 'F'),
					entry(Person.AGE, (short)20),
					entry(Person.CPR, 910101265),
					entry(Person.ACCOUNT_NO, 9876543210987l),
					entry(Person.GPA, 3.8f),
					entry(Person.SALARY, 600.8),
					entry(Person.ANNUAL_INCOME, new BigDecimal("900.23")),
					entry(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11")),
					entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05")),
					entry(Person.SLEEP_TIME, Time.valueOf("04:04:04")),
					entry(Person.GRADUATED, false),
					entry(Person.CERTIFICATES, Arrays.asList("Postgres", "C++")),
					entry(City.ID, 1),
					entry(City.NAME, "Manama"),
					entry(Company.ID, 1),
					entry(Company.NAME, "Maraei"),
					entry(Country.ID, 1),
					entry(Country.NAME, "Kuwait"),
					entry(School.ID, 1),
					entry(School.NAME, "Bin Khuldon")
				))
		);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record Map with alias");
		
		select(Person.ID.as("person_id"), Person.NAME.as("person_name"), 
				Person.GENDER.as("person_gender"), Person.AGE.as("person_age"))
		.select(Person.CPR.as("person_cpr"), Person.ACCOUNT_NO.as("person_account_no"), 
				Person.GPA.as("person_gpa"))
		.select(Person.SALARY.as("person_salary"), Person.ANNUAL_INCOME.as("person_annual_income"), 
				Person.DATE_OF_BIRTH.as("person_date_of_birth"))
		.select(Person.REGISTRATION_DATE_TIME.as("person_registration_date_time"), Person.SLEEP_TIME.as("person_sleep_time"))
		.select(Person.GRADUATED.as("person_graduated"), Person.CERTIFICATES.as("person_certificates"))
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		recordsMap = getRecordMap();
		
		isQueryEqual(query, "select prsn_id as person_id, prsn_name as person_name, prsn_gender as person_gender, prsn_age as person_age, prsn_cpr as person_cpr, prsn_account_no as person_account_no, prsn_gpa as person_gpa, prsn_salary as person_salary, prsn_annual_income as person_annual_income, prsn_date_of_birth as person_date_of_birth, prsn_registration_date_time as person_registration_date_time, prsn_sleep_time as person_sleep_time, prsn_graduated as person_graduated, prsn_certificates as person_certificates from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordMapEqual(recordsMap, 
				entry(id, map(
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(Person.ACCOUNT_NO, 12345678901234l),
						entry(Person.GPA, 3.12f),
						entry(Person.SALARY, 400.5),
						entry(Person.ANNUAL_INCOME, new BigDecimal("500.40")),
						entry(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10")),
						entry(Person.SLEEP_TIME, Time.valueOf("10:10:10")),
						entry(Person.GRADUATED, true),
						entry(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
				)),
				entry(secondId, map(
					entry(Person.ID, secondId),
					entry(Person.NAME, "Fatima"),
					entry(Person.GENDER, 'F'),
					entry(Person.AGE, (short)20),
					entry(Person.CPR, 910101265),
					entry(Person.ACCOUNT_NO, 9876543210987l),
					entry(Person.GPA, 3.8f),
					entry(Person.SALARY, 600.8),
					entry(Person.ANNUAL_INCOME, new BigDecimal("900.23")),
					entry(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11")),
					entry(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05")),
					entry(Person.SLEEP_TIME, Time.valueOf("04:04:04")),
					entry(Person.GRADUATED, false),
					entry(Person.CERTIFICATES, Arrays.asList("Postgres", "C++"))
				))
		);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record Map with math/date");
		
		select(Person.ID, Person.AGE.plus(1))
		.select(Person.CPR.minus(2), 
				Person.ACCOUNT_NO.divide(Person.ACCOUNT_NO), 
				Person.GPA.multiply(Person.GPA))
		.select(Person.SALARY.plus(Person.SALARY), 
				Person.ANNUAL_INCOME.divide(Person.ANNUAL_INCOME), 
				Person.DATE_OF_BIRTH.plusMinutes(1))
		.select(Person.REGISTRATION_DATE_TIME.minusMinutes(3), 
				Person.SLEEP_TIME.minusHours(3))
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		recordsMap = getRecordMap();
		
		isQueryEqual(query, "select prsn_id, prsn_age + ? as prsn_age_plus, prsn_cpr - ? as prsn_cpr_minus, prsn_account_no / prsn_account_no as prsn_account_no_divide, prsn_gpa * prsn_gpa as prsn_gpa_multiply, prsn_salary + prsn_salary as prsn_salary_plus, prsn_annual_income / prsn_annual_income as prsn_annual_income_divide, prsn_date_of_birth + interval '1 minutes' as prsn_date_of_birth_plus, prsn_registration_date_time - interval '3 minutes' as prsn_registration_date_time_minus, prsn_sleep_time - interval '3 hours' as prsn_sleep_time_minus from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, 1, 2, id, secondId);
		isRecordMapEqual(recordsMap, 
				entry(id, map(
						entry(Person.ID, id),
						entry(Person.AGE.plus(1), (short)34),
						entry(Person.CPR.minus(2), 870501234),
						entry(Person.ACCOUNT_NO.divide(Person.ACCOUNT_NO), 1l),
						entry(Person.GPA.multiply(Person.GPA), 9.734399f),
						entry(Person.SALARY.plus(Person.SALARY), 801.0),
						entry(Person.ANNUAL_INCOME.divide(Person.ANNUAL_INCOME), new BigDecimal("1.00000000000000000000")),
						entry(Person.DATE_OF_BIRTH.plusMinutes(1), Date.valueOf("2015-10-10")),
						entry(Person.REGISTRATION_DATE_TIME.minusMinutes(3), Timestamp.valueOf("2000-10-10 10:07:10.0")),
						entry(Person.SLEEP_TIME.minusHours(3), Time.valueOf("07:10:10"))
				)),
				entry(secondId, map(
						entry(Person.ID, secondId),
						entry(Person.AGE.plus(1), (short)21),
						entry(Person.CPR.minus(2), 910101263),
						entry(Person.ACCOUNT_NO.divide(Person.ACCOUNT_NO), 1l),
						entry(Person.GPA.multiply(Person.GPA), 14.44f),
						entry(Person.SALARY.plus(Person.SALARY), 1201.6),
						entry(Person.ANNUAL_INCOME.divide(Person.ANNUAL_INCOME), new BigDecimal("1.00000000000000000000")),
						entry(Person.DATE_OF_BIRTH.plusMinutes(1), Date.valueOf("2011-11-11")),
						entry(Person.REGISTRATION_DATE_TIME.minusMinutes(3), Timestamp.valueOf("2005-05-05 05:02:05.0")),
						entry(Person.SLEEP_TIME.minusHours(3), Time.valueOf("01:04:04"))
				))
		);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record Map with alias table");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD.of("home_address").as("home_address_road")
				, Address.BLOCK.of("home_address"))
		.select(Address.ROAD.of("work_address"), Address.BLOCK.of("work_address"))
		.from(Table.PERSON.as("person"))
		.join(Table.ADDRESS.as("home_address"))
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID.of("home_address")))
		.join(Table.ADDRESS.as("work_address"))
			.on(Person.WORK_ADDRESS_ID.equal(Address.ID.of("work_address")))
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		recordsMap = getRecordMap();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, home_address.addr_road as home_address_road, home_address.addr_block as home_address_addr_block, work_address.addr_road as work_address_addr_road, work_address.addr_block as work_address_addr_block from public.person as person inner join public.address as home_address on prsn_home_address_id = home_address.addr_id inner join public.address as work_address on prsn_work_address_id = work_address.addr_id where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordMapEqual(recordsMap, 
				entry(id, map(
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Address.ROAD.of("home_address"), "Road 1"),
						entry(Address.BLOCK.of("home_address"), "Block 1"),
						entry(Address.ROAD.of("work_address"), "Road 2"),
						entry(Address.BLOCK.of("work_address"), "Block 2")
				)),
				entry(secondId, map(
						entry(Person.ID, secondId),
						entry(Person.NAME, "Fatima"),
						entry(Person.GENDER, 'F'),
						entry(Person.AGE, (short)20),
						entry(Address.ROAD.of("home_address"), "Road 1"),
						entry(Address.BLOCK.of("home_address"), "Block 1"),
						entry(Address.ROAD.of("work_address"), "Road 2"),
						entry(Address.BLOCK.of("work_address"), "Block 2")
				))
		);
	}
	
	public void testGetRecordClass() throws Exception {

		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record with Class");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.values(Person.CITY_ID, 1)
			.values(Person.SCHOOL_ID, 1)
			.values(Person.COMPANY_ID, 1)
			.values(Person.COUNTRY_ID, 1)
			.values(Person.HOME_ADDRESS_ID, 1)
			.values(Person.WORK_ADDRESS_ID, 2)
			.executeInsert();
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(Person.CITY_ID, Person.SCHOOL_ID)
		.select(Person.COMPANY_ID, Person.COUNTRY_ID)
		.select(Person.HOME_ADDRESS_ID, Person.WORK_ADDRESS_ID)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		personDTO = getRecord(PersonDTO.class);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(id);
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setCpr(870501236);
		expectedPersonDTO.setAccountNo(12345678901234l);
		expectedPersonDTO.setGpa(3.12f);
		expectedPersonDTO.setSalary(400.5);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("500.40"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2015-10-10"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2000-10-10 10:10:10"));
		expectedPersonDTO.setSleepTime(Time.valueOf("10:10:10"));
		expectedPersonDTO.setGraduated(true);
		expectedPersonDTO.setCertificates(Arrays.asList("DB2", "Java"));
		expectedPersonDTO.setCity(new CityDTO());
		expectedPersonDTO.getCity().setId(1);
		expectedPersonDTO.setSchool(new SchoolDTO());
		expectedPersonDTO.getSchool().setId(1);
		expectedPersonDTO.setCompany(new CompanyDTO());
		expectedPersonDTO.getCompany().setId(1);
		expectedPersonDTO.setCountry(new CountryDTO());
		expectedPersonDTO.getCountry().setId(1);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setId(1);
		expectedPersonDTO.setWorkAddress(new AddressDTO());
		expectedPersonDTO.getWorkAddress().setId(2);
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, prsn_city_id, prsn_school_id, prsn_company_id, prsn_country_id, prsn_home_address_id, prsn_work_address_id from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(personDTO, expectedPersonDTO);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record with Class with joins");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(City.ID, City.NAME)
		.select(Company.ID, Company.NAME)
		.select(Country.ID, Country.NAME)
		.select(School.ID, School.NAME)
		.from(Table.PERSON)
		.innerJoin(Table.CITY).on(Person.CITY_ID.equal(City.ID))
		.rightJoin(Table.COMPANY).on(Person.COMPANY_ID.equal(Company.ID))
		.leftJoin(Table.COUNTRY).on(Person.COUNTRY_ID.equal(Country.ID))
		.fullJoin(Table.SCHOOL).on(Person.SCHOOL_ID.equal(School.ID))
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		personDTO = getRecord(PersonDTO.class);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(id);
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setCpr(870501236);
		expectedPersonDTO.setAccountNo(12345678901234l);
		expectedPersonDTO.setGpa(3.12f);
		expectedPersonDTO.setSalary(400.5);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("500.40"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2015-10-10"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2000-10-10 10:10:10"));
		expectedPersonDTO.setSleepTime(Time.valueOf("10:10:10"));
		expectedPersonDTO.setGraduated(true);
		expectedPersonDTO.setCertificates(Arrays.asList("DB2", "Java"));
		expectedPersonDTO.setCity(new CityDTO());
		expectedPersonDTO.getCity().setId(1);
		expectedPersonDTO.getCity().setName("Manama");
		expectedPersonDTO.setCompany(new CompanyDTO());
		expectedPersonDTO.getCompany().setId(1);
		expectedPersonDTO.getCompany().setName("Maraei");
		expectedPersonDTO.setSchool(new SchoolDTO());
		expectedPersonDTO.getSchool().setId(1);
		expectedPersonDTO.getSchool().setName("Bin Khuldon");
		expectedPersonDTO.setCountry(new CountryDTO());
		expectedPersonDTO.getCountry().setId(1);
		expectedPersonDTO.getCountry().setName("Kuwait");
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, ct_id, ct_name, cmp_id, cmp_name, cnt_id, cnt_name, sch_id, sch_name from public.person inner join public.city on prsn_city_id = ct_id right join public.company on prsn_company_id = cmp_id left join public.country on prsn_country_id = cnt_id full join public.school on prsn_school_id = sch_id where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(personDTO, expectedPersonDTO);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record with Class with alias");
		
		select(Person.ID.as("person_id"), Person.NAME.as("person_name"), 
				Person.GENDER.as("person_gender"), Person.AGE.as("person_age"))
		.select(Person.CPR.as("person_cpr"), Person.ACCOUNT_NO.as("person_account_no"), 
				Person.GPA.as("person_gpa"))
		.select(Person.SALARY.as("person_salary"), Person.ANNUAL_INCOME.as("person_annual_income"), 
				Person.DATE_OF_BIRTH.as("person_date_of_birth"))
		.select(Person.REGISTRATION_DATE_TIME.as("person_registration_date_time"), Person.SLEEP_TIME.as("person_sleep_time"))
		.select(Person.GRADUATED.as("person_graduated"), Person.CERTIFICATES.as("person_certificates"))
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		personDTO = getRecord(PersonDTO.class);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(id);
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setCpr(870501236);
		expectedPersonDTO.setAccountNo(12345678901234l);
		expectedPersonDTO.setGpa(3.12f);
		expectedPersonDTO.setSalary(400.5);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("500.40"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2015-10-10"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2000-10-10 10:10:10"));
		expectedPersonDTO.setSleepTime(Time.valueOf("10:10:10"));
		expectedPersonDTO.setGraduated(true);
		expectedPersonDTO.setCertificates(Arrays.asList("DB2", "Java"));
		
		isQueryEqual(query, "select prsn_id as person_id, prsn_name as person_name, prsn_gender as person_gender, prsn_age as person_age, prsn_cpr as person_cpr, prsn_account_no as person_account_no, prsn_gpa as person_gpa, prsn_salary as person_salary, prsn_annual_income as person_annual_income, prsn_date_of_birth as person_date_of_birth, prsn_registration_date_time as person_registration_date_time, prsn_sleep_time as person_sleep_time, prsn_graduated as person_graduated, prsn_certificates as person_certificates from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(personDTO, expectedPersonDTO);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Select Query select clause with math/date");
		
		select(Person.AGE.plus(1))
		.select(Person.CPR.minus(2), 
				Person.ACCOUNT_NO.divide(Person.ACCOUNT_NO), 
				Person.GPA.multiply(Person.GPA))
		.select(Person.SALARY.plus(Person.SALARY), 
				Person.ANNUAL_INCOME.divide(Person.ANNUAL_INCOME), 
				Person.DATE_OF_BIRTH.plusMinutes(1))
		.select(Person.REGISTRATION_DATE_TIME.minusMinutes(3), 
				Person.SLEEP_TIME.minusHours(3))
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		personDTO = getRecord(PersonDTO.class);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setAge((short) 34);
		expectedPersonDTO.setCpr(870501234);
		expectedPersonDTO.setAccountNo(1l);
		expectedPersonDTO.setGpa(9.734399f);
		expectedPersonDTO.setSalary(801.0);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("1.00000000000000000000"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2015-10-10"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2000-10-10 10:07:10.0"));
		expectedPersonDTO.setSleepTime(Time.valueOf("07:10:10"));
		
		isQueryEqual(query, "select prsn_age + ? as prsn_age_plus, prsn_cpr - ? as prsn_cpr_minus, prsn_account_no / prsn_account_no as prsn_account_no_divide, prsn_gpa * prsn_gpa as prsn_gpa_multiply, prsn_salary + prsn_salary as prsn_salary_plus, prsn_annual_income / prsn_annual_income as prsn_annual_income_divide, prsn_date_of_birth + interval '1 minutes' as prsn_date_of_birth_plus, prsn_registration_date_time - interval '3 minutes' as prsn_registration_date_time_minus, prsn_sleep_time - interval '3 hours' as prsn_sleep_time_minus from public.person where prsn_id = ?");
		isParametersEquals(parameters, 1, 2, id);
		isRecordEqual(personDTO, expectedPersonDTO);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record with Class with aggregation function");
		
		select(sum(Person.AGE))
		.select(count(Person.CPR), 
				average(Person.ACCOUNT_NO), 
				min(Person.GPA))
		.select(max(Person.SALARY))
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		personDTO = getRecord(PersonDTO.class);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setCpr(1);
		expectedPersonDTO.setAccountNo(12345678901234l);
		expectedPersonDTO.setGpa(3.12f);
		expectedPersonDTO.setSalary(400.5);
		
		isQueryEqual(query, "select sum(prsn_age) as prsn_age_sum, count(prsn_cpr) as prsn_cpr_count, avg(prsn_account_no) as prsn_account_no_avg, min(prsn_gpa) as prsn_gpa_min, max(prsn_salary) as prsn_salary_max from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(personDTO, expectedPersonDTO);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record with Class with multi-relation table");
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD, Address.BLOCK)
		.from(Table.PERSON)
		.join(Table.ADDRESS)
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID))
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		personDTO = getRecord(PersonDTO.class);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		
		isQueryEqual(query, "select prsn_name, prsn_gender, prsn_age, addr_road, addr_block from public.person inner join public.address on prsn_home_address_id = addr_id where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(personDTO, expectedPersonDTO);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record with Class with multi-relation table with column alias");
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD.as("road_block"), Address.BLOCK.as("address_block"))
		.from(Table.PERSON)
		.join(Table.ADDRESS)
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID))
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		personDTO = getRecord(PersonDTO.class);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		
		isQueryEqual(query, "select prsn_name, prsn_gender, prsn_age, addr_road as road_block, addr_block as address_block from public.person inner join public.address on prsn_home_address_id = addr_id where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(personDTO, expectedPersonDTO);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record with Join Query");
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD, Address.BLOCK)
		.from(Table.PERSON)
		.join(new Query().select().from(Table.ADDRESS))
			.as("all_addresses")
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID))
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		personDTO = getRecord(PersonDTO.class);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		
		isQueryEqual(query, "select prsn_name, prsn_gender, prsn_age, addr_road, addr_block from public.person inner join (select * from public.address) as all_addresses on prsn_home_address_id = addr_id where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(personDTO, expectedPersonDTO);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record with Class with alias table");
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD.of("home_address").as("home_address_road")
				, Address.BLOCK.of("home_address"))
		.select(Address.ROAD.of("work_address"), Address.BLOCK.of("work_address"))
		.from(Table.PERSON.as("person"))
		.join(Table.ADDRESS.as("home_address"))
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID.of("home_address")))
		.join(Table.ADDRESS.as("work_address"))
			.on(Person.WORK_ADDRESS_ID.equal(Address.ID.of("work_address")))
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		personDTO = getRecord(PersonDTO.class);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		expectedPersonDTO.setWorkAddress(new AddressDTO());
		expectedPersonDTO.getWorkAddress().setRoad("Road 2");
		expectedPersonDTO.getWorkAddress().setBlock("Block 2");
		
		isQueryEqual(query, "select prsn_name, prsn_gender, prsn_age, home_address.addr_road as home_address_road, home_address.addr_block as home_address_addr_block, work_address.addr_road as work_address_addr_road, work_address.addr_block as work_address_addr_block from public.person as person inner join public.address as home_address on prsn_home_address_id = home_address.addr_id inner join public.address as work_address on prsn_work_address_id = work_address.addr_id where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(personDTO, expectedPersonDTO);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record with Class with alias join query");
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD.of("home_address").as("home_address_road")
				, Address.BLOCK.of("home_address"))
		.select(Address.ROAD.of("work_address"), Address.BLOCK.of("work_address"))
		.from(Table.PERSON.as("person"))
		.join(new Query().select().from(Table.ADDRESS))
			.as("home_address")
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID.of("home_address")))
		.join(new Query().select().from(Table.ADDRESS))
			.as("work_address")
			.on(Person.WORK_ADDRESS_ID.equal(Address.ID.of("work_address")))
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		personDTO = getRecord(PersonDTO.class);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		expectedPersonDTO.setWorkAddress(new AddressDTO());
		expectedPersonDTO.getWorkAddress().setRoad("Road 2");
		expectedPersonDTO.getWorkAddress().setBlock("Block 2");
		
		isQueryEqual(query, "select prsn_name, prsn_gender, prsn_age, home_address.addr_road as home_address_road, home_address.addr_block as home_address_addr_block, work_address.addr_road as work_address_addr_road, work_address.addr_block as work_address_addr_block from public.person as person inner join (select * from public.address) as home_address on prsn_home_address_id = home_address.addr_id inner join (select * from public.address) as work_address on prsn_work_address_id = work_address.addr_id where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(personDTO, expectedPersonDTO);
	}
	
	public void testGetRecordListClass() throws Exception {

		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record List with Class");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.values(Person.CITY_ID, 1)
			.values(Person.SCHOOL_ID, 1)
			.values(Person.COMPANY_ID, 1)
			.values(Person.COUNTRY_ID, 1)
			.values(Person.HOME_ADDRESS_ID, 1)
			.values(Person.WORK_ADDRESS_ID, 2)
			.executeInsert();
		
		secondId = insertInto(Table.PERSON)
				.values(Person.NAME, "Fatima")
				.values(Person.GENDER, 'F')
				.values(Person.AGE, 20)
				.values(Person.CPR, 910101265)
				.values(Person.ACCOUNT_NO, 9876543210987l)
				.values(Person.GPA, 3.8)
				.values(Person.SALARY, 600.8)
				.values(Person.ANNUAL_INCOME, new BigDecimal("900.23"))
				.values(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11"))
				.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05"))
				.values(Person.SLEEP_TIME, Time.valueOf("04:04:04"))
				.values(Person.GRADUATED, false)
				.values(Person.CERTIFICATES, Arrays.asList("Postgres", "C++"))
				.values(Person.CITY_ID, 1)
				.values(Person.SCHOOL_ID, 1)
				.values(Person.COMPANY_ID, 1)
				.values(Person.COUNTRY_ID, 1)
				.values(Person.HOME_ADDRESS_ID, 1)
				.values(Person.WORK_ADDRESS_ID, 2)
				.executeInsert();
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(Person.CITY_ID, Person.SCHOOL_ID)
		.select(Person.COMPANY_ID, Person.COUNTRY_ID)
		.select(Person.HOME_ADDRESS_ID, Person.WORK_ADDRESS_ID)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		persons = getRecordList(PersonDTO.class);
		
		expectedPersons.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(id);
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setCpr(870501236);
		expectedPersonDTO.setAccountNo(12345678901234l);
		expectedPersonDTO.setGpa(3.12f);
		expectedPersonDTO.setSalary(400.5);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("500.40"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2015-10-10"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2000-10-10 10:10:10"));
		expectedPersonDTO.setSleepTime(Time.valueOf("10:10:10"));
		expectedPersonDTO.setGraduated(true);
		expectedPersonDTO.setCertificates(Arrays.asList("DB2", "Java"));
		expectedPersonDTO.setCity(new CityDTO());
		expectedPersonDTO.getCity().setId(1);
		expectedPersonDTO.setSchool(new SchoolDTO());
		expectedPersonDTO.getSchool().setId(1);
		expectedPersonDTO.setCompany(new CompanyDTO());
		expectedPersonDTO.getCompany().setId(1);
		expectedPersonDTO.setCountry(new CountryDTO());
		expectedPersonDTO.getCountry().setId(1);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setId(1);
		expectedPersonDTO.setWorkAddress(new AddressDTO());
		expectedPersonDTO.getWorkAddress().setId(2);
		
		expectedPersons.add(expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(secondId);
		expectedPersonDTO.setName("Fatima");
		expectedPersonDTO.setGender('F');
		expectedPersonDTO.setAge((short) 20);
		expectedPersonDTO.setCpr(910101265);
		expectedPersonDTO.setAccountNo(9876543210987l);
		expectedPersonDTO.setGpa(3.8f);
		expectedPersonDTO.setSalary(600.8);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("900.23"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2011-11-11"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2005-05-05 05:05:05"));
		expectedPersonDTO.setSleepTime(Time.valueOf("04:04:04"));
		expectedPersonDTO.setGraduated(false);
		expectedPersonDTO.setCertificates(Arrays.asList("Postgres", "C++"));
		expectedPersonDTO.setCity(new CityDTO());
		expectedPersonDTO.getCity().setId(1);
		expectedPersonDTO.setSchool(new SchoolDTO());
		expectedPersonDTO.getSchool().setId(1);
		expectedPersonDTO.setCompany(new CompanyDTO());
		expectedPersonDTO.getCompany().setId(1);
		expectedPersonDTO.setCountry(new CountryDTO());
		expectedPersonDTO.getCountry().setId(1);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setId(1);
		expectedPersonDTO.setWorkAddress(new AddressDTO());
		expectedPersonDTO.getWorkAddress().setId(2);
		
		expectedPersons.add(expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, prsn_city_id, prsn_school_id, prsn_company_id, prsn_country_id, prsn_home_address_id, prsn_work_address_id from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(persons, expectedPersons);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record List with Class with joins");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(City.ID, City.NAME)
		.select(Company.ID, Company.NAME)
		.select(Country.ID, Country.NAME)
		.select(School.ID, School.NAME)
		.from(Table.PERSON)
		.innerJoin(Table.CITY).on(Person.CITY_ID.equal(City.ID))
		.rightJoin(Table.COMPANY).on(Person.COMPANY_ID.equal(Company.ID))
		.leftJoin(Table.COUNTRY).on(Person.COUNTRY_ID.equal(Country.ID))
		.fullJoin(Table.SCHOOL).on(Person.SCHOOL_ID.equal(School.ID))
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		persons = getRecordList(PersonDTO.class);
		
		expectedPersons.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(id);
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setCpr(870501236);
		expectedPersonDTO.setAccountNo(12345678901234l);
		expectedPersonDTO.setGpa(3.12f);
		expectedPersonDTO.setSalary(400.5);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("500.40"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2015-10-10"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2000-10-10 10:10:10"));
		expectedPersonDTO.setSleepTime(Time.valueOf("10:10:10"));
		expectedPersonDTO.setGraduated(true);
		expectedPersonDTO.setCertificates(Arrays.asList("DB2", "Java"));
		expectedPersonDTO.setCity(new CityDTO());
		expectedPersonDTO.getCity().setId(1);
		expectedPersonDTO.getCity().setName("Manama");
		expectedPersonDTO.setCompany(new CompanyDTO());
		expectedPersonDTO.getCompany().setId(1);
		expectedPersonDTO.getCompany().setName("Maraei");
		expectedPersonDTO.setSchool(new SchoolDTO());
		expectedPersonDTO.getSchool().setId(1);
		expectedPersonDTO.getSchool().setName("Bin Khuldon");
		expectedPersonDTO.setCountry(new CountryDTO());
		expectedPersonDTO.getCountry().setId(1);
		expectedPersonDTO.getCountry().setName("Kuwait");
		
		expectedPersons.add(expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(secondId);
		expectedPersonDTO.setName("Fatima");
		expectedPersonDTO.setGender('F');
		expectedPersonDTO.setAge((short) 20);
		expectedPersonDTO.setCpr(910101265);
		expectedPersonDTO.setAccountNo(9876543210987l);
		expectedPersonDTO.setGpa(3.8f);
		expectedPersonDTO.setSalary(600.8);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("900.23"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2011-11-11"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2005-05-05 05:05:05"));
		expectedPersonDTO.setSleepTime(Time.valueOf("04:04:04"));
		expectedPersonDTO.setGraduated(false);
		expectedPersonDTO.setCertificates(Arrays.asList("Postgres", "C++"));
		expectedPersonDTO.setCity(new CityDTO());
		expectedPersonDTO.getCity().setId(1);
		expectedPersonDTO.getCity().setName("Manama");
		expectedPersonDTO.setCompany(new CompanyDTO());
		expectedPersonDTO.getCompany().setId(1);
		expectedPersonDTO.getCompany().setName("Maraei");
		expectedPersonDTO.setSchool(new SchoolDTO());
		expectedPersonDTO.getSchool().setId(1);
		expectedPersonDTO.getSchool().setName("Bin Khuldon");
		expectedPersonDTO.setCountry(new CountryDTO());
		expectedPersonDTO.getCountry().setId(1);
		expectedPersonDTO.getCountry().setName("Kuwait");
		
		expectedPersons.add(expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, ct_id, ct_name, cmp_id, cmp_name, cnt_id, cnt_name, sch_id, sch_name from public.person inner join public.city on prsn_city_id = ct_id right join public.company on prsn_company_id = cmp_id left join public.country on prsn_country_id = cnt_id full join public.school on prsn_school_id = sch_id where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(persons, expectedPersons);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record List with Class with alias");
		
		select(Person.ID.as("person_id"), Person.NAME.as("person_name"), 
				Person.GENDER.as("person_gender"), Person.AGE.as("person_age"))
		.select(Person.CPR.as("person_cpr"), Person.ACCOUNT_NO.as("person_account_no"), 
				Person.GPA.as("person_gpa"))
		.select(Person.SALARY.as("person_salary"), Person.ANNUAL_INCOME.as("person_annual_income"), 
				Person.DATE_OF_BIRTH.as("person_date_of_birth"))
		.select(Person.REGISTRATION_DATE_TIME.as("person_registration_date_time"), Person.SLEEP_TIME.as("person_sleep_time"))
		.select(Person.GRADUATED.as("person_graduated"), Person.CERTIFICATES.as("person_certificates"))
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		persons = getRecordList(PersonDTO.class);
		
		expectedPersons.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(id);
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setCpr(870501236);
		expectedPersonDTO.setAccountNo(12345678901234l);
		expectedPersonDTO.setGpa(3.12f);
		expectedPersonDTO.setSalary(400.5);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("500.40"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2015-10-10"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2000-10-10 10:10:10"));
		expectedPersonDTO.setSleepTime(Time.valueOf("10:10:10"));
		expectedPersonDTO.setGraduated(true);
		expectedPersonDTO.setCertificates(Arrays.asList("DB2", "Java"));
		
		expectedPersons.add(expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(secondId);
		expectedPersonDTO.setName("Fatima");
		expectedPersonDTO.setGender('F');
		expectedPersonDTO.setAge((short) 20);
		expectedPersonDTO.setCpr(910101265);
		expectedPersonDTO.setAccountNo(9876543210987l);
		expectedPersonDTO.setGpa(3.8f);
		expectedPersonDTO.setSalary(600.8);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("900.23"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2011-11-11"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2005-05-05 05:05:05"));
		expectedPersonDTO.setSleepTime(Time.valueOf("04:04:04"));
		expectedPersonDTO.setGraduated(false);
		expectedPersonDTO.setCertificates(Arrays.asList("Postgres", "C++"));
		
		expectedPersons.add(expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_id as person_id, prsn_name as person_name, prsn_gender as person_gender, prsn_age as person_age, prsn_cpr as person_cpr, prsn_account_no as person_account_no, prsn_gpa as person_gpa, prsn_salary as person_salary, prsn_annual_income as person_annual_income, prsn_date_of_birth as person_date_of_birth, prsn_registration_date_time as person_registration_date_time, prsn_sleep_time as person_sleep_time, prsn_graduated as person_graduated, prsn_certificates as person_certificates from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(persons, expectedPersons);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record List with Class with math/date");
		
		select(Person.AGE.plus(1))
		.select(Person.CPR.minus(2), 
				Person.ACCOUNT_NO.divide(Person.ACCOUNT_NO), 
				Person.GPA.multiply(Person.GPA))
		.select(Person.SALARY.plus(Person.SALARY), 
				Person.ANNUAL_INCOME.divide(Person.ANNUAL_INCOME), 
				Person.DATE_OF_BIRTH.plusMinutes(1))
		.select(Person.REGISTRATION_DATE_TIME.minusMinutes(3), 
				Person.SLEEP_TIME.minusHours(3))
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		persons = getRecordList(PersonDTO.class);
		
		expectedPersons.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setAge((short) 34);
		expectedPersonDTO.setCpr(870501234);
		expectedPersonDTO.setAccountNo(1l);
		expectedPersonDTO.setGpa(9.734399f);
		expectedPersonDTO.setSalary(801.0);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("1.00000000000000000000"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2015-10-10"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2000-10-10 10:07:10.0"));
		expectedPersonDTO.setSleepTime(Time.valueOf("07:10:10"));
		
		expectedPersons.add(expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setAge((short) 21);
		expectedPersonDTO.setCpr(910101263);
		expectedPersonDTO.setAccountNo(1l);
		expectedPersonDTO.setGpa(14.44f);
		expectedPersonDTO.setSalary(1201.6);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("1.00000000000000000000"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2011-11-11"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2005-05-05 05:02:05.0"));
		expectedPersonDTO.setSleepTime(Time.valueOf("01:04:04"));
		
		expectedPersons.add(expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_age + ? as prsn_age_plus, prsn_cpr - ? as prsn_cpr_minus, prsn_account_no / prsn_account_no as prsn_account_no_divide, prsn_gpa * prsn_gpa as prsn_gpa_multiply, prsn_salary + prsn_salary as prsn_salary_plus, prsn_annual_income / prsn_annual_income as prsn_annual_income_divide, prsn_date_of_birth + interval '1 minutes' as prsn_date_of_birth_plus, prsn_registration_date_time - interval '3 minutes' as prsn_registration_date_time_minus, prsn_sleep_time - interval '3 hours' as prsn_sleep_time_minus from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, 1, 2, id, secondId);
		isRecordListEqual(persons, expectedPersons);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record List with Class with aggregation function");
		
		select(sum(Person.AGE))
		.select(count(Person.CPR), 
				average(Person.ACCOUNT_NO), 
				min(Person.GPA))
		.select(max(Person.SALARY))
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		persons = getRecordList(PersonDTO.class);
		
		expectedPersons.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setAge((short) 53);
		expectedPersonDTO.setCpr(2);
		expectedPersonDTO.setAccountNo(11111111056110l);
		expectedPersonDTO.setGpa(3.12f);
		expectedPersonDTO.setSalary(600.8);
		
		expectedPersons.add(expectedPersonDTO);
		
		isQueryEqual(query, "select sum(prsn_age) as prsn_age_sum, count(prsn_cpr) as prsn_cpr_count, avg(prsn_account_no) as prsn_account_no_avg, min(prsn_gpa) as prsn_gpa_min, max(prsn_salary) as prsn_salary_max from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(persons, expectedPersons);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record List with Class with multi-relation table");
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD, Address.BLOCK)
		.from(Table.PERSON)
		.join(Table.ADDRESS)
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID))
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		persons = getRecordList(PersonDTO.class);
		
		expectedPersons.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setName("Fatima");
		expectedPersonDTO.setGender('F');
		expectedPersonDTO.setAge((short) 20);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		
		expectedPersons.add(expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		
		expectedPersons.add(expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_name, prsn_gender, prsn_age, addr_road, addr_block from public.person inner join public.address on prsn_home_address_id = addr_id where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(persons, expectedPersons);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record List with Class with multi-relation table with column alias");
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD.as("road_block"), Address.BLOCK.as("address_block"))
		.from(Table.PERSON)
		.join(Table.ADDRESS)
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID))
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		persons = getRecordList(PersonDTO.class);
		
		expectedPersons.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setName("Fatima");
		expectedPersonDTO.setGender('F');
		expectedPersonDTO.setAge((short) 20);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		
		expectedPersons.add(expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		
		expectedPersons.add(expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_name, prsn_gender, prsn_age, addr_road as road_block, addr_block as address_block from public.person inner join public.address on prsn_home_address_id = addr_id where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(persons, expectedPersons);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record List with Join Query");
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD, Address.BLOCK)
		.from(Table.PERSON)
		.join(new Query().select().from(Table.ADDRESS))
			.as("all_addresses")
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID))
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		persons = getRecordList(PersonDTO.class);
		
		expectedPersons.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setName("Fatima");
		expectedPersonDTO.setGender('F');
		expectedPersonDTO.setAge((short) 20);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		
		expectedPersons.add(expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		
		expectedPersons.add(expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_name, prsn_gender, prsn_age, addr_road, addr_block from public.person inner join (select * from public.address) as all_addresses on prsn_home_address_id = addr_id where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(persons, expectedPersons);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record List with Class with alias table");
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD.of("home_address").as("home_address_road")
				, Address.BLOCK.of("home_address"))
		.select(Address.ROAD.of("work_address"), Address.BLOCK.of("work_address"))
		.from(Table.PERSON.as("person"))
		.join(Table.ADDRESS.as("home_address"))
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID.of("home_address")))
		.join(Table.ADDRESS.as("work_address"))
			.on(Person.WORK_ADDRESS_ID.equal(Address.ID.of("work_address")))
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		persons = getRecordList(PersonDTO.class);
		
		expectedPersons.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		expectedPersonDTO.setWorkAddress(new AddressDTO());
		expectedPersonDTO.getWorkAddress().setRoad("Road 2");
		expectedPersonDTO.getWorkAddress().setBlock("Block 2");
		
		expectedPersons.add(expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setName("Fatima");
		expectedPersonDTO.setGender('F');
		expectedPersonDTO.setAge((short) 20);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		expectedPersonDTO.setWorkAddress(new AddressDTO());
		expectedPersonDTO.getWorkAddress().setRoad("Road 2");
		expectedPersonDTO.getWorkAddress().setBlock("Block 2");
		
		expectedPersons.add(expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_name, prsn_gender, prsn_age, home_address.addr_road as home_address_road, home_address.addr_block as home_address_addr_block, work_address.addr_road as work_address_addr_road, work_address.addr_block as work_address_addr_block from public.person as person inner join public.address as home_address on prsn_home_address_id = home_address.addr_id inner join public.address as work_address on prsn_work_address_id = work_address.addr_id where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(persons, expectedPersons);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record List with Class with alias join query");
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD.of("home_address").as("home_address_road")
				, Address.BLOCK.of("home_address"))
		.select(Address.ROAD.of("work_address"), Address.BLOCK.of("work_address"))
		.from(Table.PERSON.as("person"))
		.join(new Query().select().from(Table.ADDRESS))
			.as("home_address")
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID.of("home_address")))
		.join(new Query().select().from(Table.ADDRESS))
			.as("work_address")
			.on(Person.WORK_ADDRESS_ID.equal(Address.ID.of("work_address")))
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		persons = getRecordList(PersonDTO.class);
		
		expectedPersons.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		expectedPersonDTO.setWorkAddress(new AddressDTO());
		expectedPersonDTO.getWorkAddress().setRoad("Road 2");
		expectedPersonDTO.getWorkAddress().setBlock("Block 2");
		
		expectedPersons.add(expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setName("Fatima");
		expectedPersonDTO.setGender('F');
		expectedPersonDTO.setAge((short) 20);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		expectedPersonDTO.setWorkAddress(new AddressDTO());
		expectedPersonDTO.getWorkAddress().setRoad("Road 2");
		expectedPersonDTO.getWorkAddress().setBlock("Block 2");
		
		expectedPersons.add(expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_name, prsn_gender, prsn_age, home_address.addr_road as home_address_road, home_address.addr_block as home_address_addr_block, work_address.addr_road as work_address_addr_road, work_address.addr_block as work_address_addr_block from public.person as person inner join (select * from public.address) as home_address on prsn_home_address_id = home_address.addr_id inner join (select * from public.address) as work_address on prsn_work_address_id = work_address.addr_id where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(persons, expectedPersons);
	}
	
	public void testGetRecordMapClass() throws Exception {

		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record Map with Class");
		
		id = insertInto(Table.PERSON)
			.values(Person.NAME, "Hasan")
			.values(Person.GENDER, 'M')
			.values(Person.AGE, (short)33)
			.values(Person.CPR, 870501236)
			.values(Person.ACCOUNT_NO, 12345678901234l)
			.values(Person.GPA, 3.12f)
			.values(Person.SALARY, 400.5)
			.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
			.values(Person.DATE_OF_BIRTH, Date.valueOf("2015-10-10"))
			.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
			.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
			.values(Person.GRADUATED, true)
			.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
			.values(Person.CITY_ID, 1)
			.values(Person.SCHOOL_ID, 1)
			.values(Person.COMPANY_ID, 1)
			.values(Person.COUNTRY_ID, 1)
			.values(Person.HOME_ADDRESS_ID, 1)
			.values(Person.WORK_ADDRESS_ID, 2)
			.executeInsert();
		
		secondId = insertInto(Table.PERSON)
				.values(Person.NAME, "Fatima")
				.values(Person.GENDER, 'F')
				.values(Person.AGE, 20)
				.values(Person.CPR, 910101265)
				.values(Person.ACCOUNT_NO, 9876543210987l)
				.values(Person.GPA, 3.8)
				.values(Person.SALARY, 600.8)
				.values(Person.ANNUAL_INCOME, new BigDecimal("900.23"))
				.values(Person.DATE_OF_BIRTH, Date.valueOf("2011-11-11"))
				.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2005-05-05 05:05:05"))
				.values(Person.SLEEP_TIME, Time.valueOf("04:04:04"))
				.values(Person.GRADUATED, false)
				.values(Person.CERTIFICATES, Arrays.asList("Postgres", "C++"))
				.values(Person.CITY_ID, 1)
				.values(Person.SCHOOL_ID, 1)
				.values(Person.COMPANY_ID, 1)
				.values(Person.COUNTRY_ID, 1)
				.values(Person.HOME_ADDRESS_ID, 1)
				.values(Person.WORK_ADDRESS_ID, 2)
				.executeInsert();
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(Person.CITY_ID, Person.SCHOOL_ID)
		.select(Person.COMPANY_ID, Person.COUNTRY_ID)
		.select(Person.HOME_ADDRESS_ID, Person.WORK_ADDRESS_ID)
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		personsMap = getRecordMap(PersonDTO.class);
		
		expectedPersonsMap.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(id);
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setCpr(870501236);
		expectedPersonDTO.setAccountNo(12345678901234l);
		expectedPersonDTO.setGpa(3.12f);
		expectedPersonDTO.setSalary(400.5);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("500.40"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2015-10-10"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2000-10-10 10:10:10"));
		expectedPersonDTO.setSleepTime(Time.valueOf("10:10:10"));
		expectedPersonDTO.setGraduated(true);
		expectedPersonDTO.setCertificates(Arrays.asList("DB2", "Java"));
		expectedPersonDTO.setCity(new CityDTO());
		expectedPersonDTO.getCity().setId(1);
		expectedPersonDTO.setSchool(new SchoolDTO());
		expectedPersonDTO.getSchool().setId(1);
		expectedPersonDTO.setCompany(new CompanyDTO());
		expectedPersonDTO.getCompany().setId(1);
		expectedPersonDTO.setCountry(new CountryDTO());
		expectedPersonDTO.getCountry().setId(1);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setId(1);
		expectedPersonDTO.setWorkAddress(new AddressDTO());
		expectedPersonDTO.getWorkAddress().setId(2);
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(secondId);
		expectedPersonDTO.setName("Fatima");
		expectedPersonDTO.setGender('F');
		expectedPersonDTO.setAge((short) 20);
		expectedPersonDTO.setCpr(910101265);
		expectedPersonDTO.setAccountNo(9876543210987l);
		expectedPersonDTO.setGpa(3.8f);
		expectedPersonDTO.setSalary(600.8);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("900.23"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2011-11-11"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2005-05-05 05:05:05"));
		expectedPersonDTO.setSleepTime(Time.valueOf("04:04:04"));
		expectedPersonDTO.setGraduated(false);
		expectedPersonDTO.setCertificates(Arrays.asList("Postgres", "C++"));
		expectedPersonDTO.setCity(new CityDTO());
		expectedPersonDTO.getCity().setId(1);
		expectedPersonDTO.setSchool(new SchoolDTO());
		expectedPersonDTO.getSchool().setId(1);
		expectedPersonDTO.setCompany(new CompanyDTO());
		expectedPersonDTO.getCompany().setId(1);
		expectedPersonDTO.setCountry(new CountryDTO());
		expectedPersonDTO.getCountry().setId(1);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setId(1);
		expectedPersonDTO.setWorkAddress(new AddressDTO());
		expectedPersonDTO.getWorkAddress().setId(2);
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, prsn_city_id, prsn_school_id, prsn_company_id, prsn_country_id, prsn_home_address_id, prsn_work_address_id from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordMapEqual(personsMap, expectedPersonsMap);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record Map with Class with joins");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME)
		.select(Person.GRADUATED, Person.CERTIFICATES)
		.select(City.ID, City.NAME)
		.select(Company.ID, Company.NAME)
		.select(Country.ID, Country.NAME)
		.select(School.ID, School.NAME)
		.from(Table.PERSON)
		.innerJoin(Table.CITY).on(Person.CITY_ID.equal(City.ID))
		.rightJoin(Table.COMPANY).on(Person.COMPANY_ID.equal(Company.ID))
		.leftJoin(Table.COUNTRY).on(Person.COUNTRY_ID.equal(Country.ID))
		.fullJoin(Table.SCHOOL).on(Person.SCHOOL_ID.equal(School.ID))
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		personsMap = getRecordMap(PersonDTO.class);
		
		expectedPersonsMap.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(id);
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setCpr(870501236);
		expectedPersonDTO.setAccountNo(12345678901234l);
		expectedPersonDTO.setGpa(3.12f);
		expectedPersonDTO.setSalary(400.5);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("500.40"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2015-10-10"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2000-10-10 10:10:10"));
		expectedPersonDTO.setSleepTime(Time.valueOf("10:10:10"));
		expectedPersonDTO.setGraduated(true);
		expectedPersonDTO.setCertificates(Arrays.asList("DB2", "Java"));
		expectedPersonDTO.setCity(new CityDTO());
		expectedPersonDTO.getCity().setId(1);
		expectedPersonDTO.getCity().setName("Manama");
		expectedPersonDTO.setCompany(new CompanyDTO());
		expectedPersonDTO.getCompany().setId(1);
		expectedPersonDTO.getCompany().setName("Maraei");
		expectedPersonDTO.setSchool(new SchoolDTO());
		expectedPersonDTO.getSchool().setId(1);
		expectedPersonDTO.getSchool().setName("Bin Khuldon");
		expectedPersonDTO.setCountry(new CountryDTO());
		expectedPersonDTO.getCountry().setId(1);
		expectedPersonDTO.getCountry().setName("Kuwait");
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(secondId);
		expectedPersonDTO.setName("Fatima");
		expectedPersonDTO.setGender('F');
		expectedPersonDTO.setAge((short) 20);
		expectedPersonDTO.setCpr(910101265);
		expectedPersonDTO.setAccountNo(9876543210987l);
		expectedPersonDTO.setGpa(3.8f);
		expectedPersonDTO.setSalary(600.8);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("900.23"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2011-11-11"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2005-05-05 05:05:05"));
		expectedPersonDTO.setSleepTime(Time.valueOf("04:04:04"));
		expectedPersonDTO.setGraduated(false);
		expectedPersonDTO.setCertificates(Arrays.asList("Postgres", "C++"));
		expectedPersonDTO.setCity(new CityDTO());
		expectedPersonDTO.getCity().setId(1);
		expectedPersonDTO.getCity().setName("Manama");
		expectedPersonDTO.setCompany(new CompanyDTO());
		expectedPersonDTO.getCompany().setId(1);
		expectedPersonDTO.getCompany().setName("Maraei");
		expectedPersonDTO.setSchool(new SchoolDTO());
		expectedPersonDTO.getSchool().setId(1);
		expectedPersonDTO.getSchool().setName("Bin Khuldon");
		expectedPersonDTO.setCountry(new CountryDTO());
		expectedPersonDTO.getCountry().setId(1);
		expectedPersonDTO.getCountry().setName("Kuwait");
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates, ct_id, ct_name, cmp_id, cmp_name, cnt_id, cnt_name, sch_id, sch_name from public.person inner join public.city on prsn_city_id = ct_id right join public.company on prsn_company_id = cmp_id left join public.country on prsn_country_id = cnt_id full join public.school on prsn_school_id = sch_id where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordMapEqual(personsMap, expectedPersonsMap);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record Map with Class with alias");
		
		select(Person.ID.as("person_id"), Person.NAME.as("person_name"), 
				Person.GENDER.as("person_gender"), Person.AGE.as("person_age"))
		.select(Person.CPR.as("person_cpr"), Person.ACCOUNT_NO.as("person_account_no"), 
				Person.GPA.as("person_gpa"))
		.select(Person.SALARY.as("person_salary"), Person.ANNUAL_INCOME.as("person_annual_income"), 
				Person.DATE_OF_BIRTH.as("person_date_of_birth"))
		.select(Person.REGISTRATION_DATE_TIME.as("person_registration_date_time"), Person.SLEEP_TIME.as("person_sleep_time"))
		.select(Person.GRADUATED.as("person_graduated"), Person.CERTIFICATES.as("person_certificates"))
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		personsMap = getRecordMap(PersonDTO.class);
		
		expectedPersonsMap.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(id);
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setCpr(870501236);
		expectedPersonDTO.setAccountNo(12345678901234l);
		expectedPersonDTO.setGpa(3.12f);
		expectedPersonDTO.setSalary(400.5);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("500.40"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2015-10-10"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2000-10-10 10:10:10"));
		expectedPersonDTO.setSleepTime(Time.valueOf("10:10:10"));
		expectedPersonDTO.setGraduated(true);
		expectedPersonDTO.setCertificates(Arrays.asList("DB2", "Java"));
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(secondId);
		expectedPersonDTO.setName("Fatima");
		expectedPersonDTO.setGender('F');
		expectedPersonDTO.setAge((short) 20);
		expectedPersonDTO.setCpr(910101265);
		expectedPersonDTO.setAccountNo(9876543210987l);
		expectedPersonDTO.setGpa(3.8f);
		expectedPersonDTO.setSalary(600.8);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("900.23"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2011-11-11"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2005-05-05 05:05:05"));
		expectedPersonDTO.setSleepTime(Time.valueOf("04:04:04"));
		expectedPersonDTO.setGraduated(false);
		expectedPersonDTO.setCertificates(Arrays.asList("Postgres", "C++"));
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_id as person_id, prsn_name as person_name, prsn_gender as person_gender, prsn_age as person_age, prsn_cpr as person_cpr, prsn_account_no as person_account_no, prsn_gpa as person_gpa, prsn_salary as person_salary, prsn_annual_income as person_annual_income, prsn_date_of_birth as person_date_of_birth, prsn_registration_date_time as person_registration_date_time, prsn_sleep_time as person_sleep_time, prsn_graduated as person_graduated, prsn_certificates as person_certificates from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordMapEqual(personsMap, expectedPersonsMap);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record Map with Class with math/date");
		
		select(Person.ID, Person.AGE.plus(1))
		.select(Person.CPR.minus(2), 
				Person.ACCOUNT_NO.divide(Person.ACCOUNT_NO), 
				Person.GPA.multiply(Person.GPA))
		.select(Person.SALARY.plus(Person.SALARY), 
				Person.ANNUAL_INCOME.divide(Person.ANNUAL_INCOME), 
				Person.DATE_OF_BIRTH.plusMinutes(1))
		.select(Person.REGISTRATION_DATE_TIME.minusMinutes(3), 
				Person.SLEEP_TIME.minusHours(3))
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		personsMap = getRecordMap(PersonDTO.class);
		
		expectedPersonsMap.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(id);
		expectedPersonDTO.setAge((short) 34);
		expectedPersonDTO.setCpr(870501234);
		expectedPersonDTO.setAccountNo(1l);
		expectedPersonDTO.setGpa(9.734399f);
		expectedPersonDTO.setSalary(801.0);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("1.00000000000000000000"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2015-10-10"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2000-10-10 10:07:10.0"));
		expectedPersonDTO.setSleepTime(Time.valueOf("07:10:10"));
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(secondId);
		expectedPersonDTO.setAge((short) 21);
		expectedPersonDTO.setCpr(910101263);
		expectedPersonDTO.setAccountNo(1l);
		expectedPersonDTO.setGpa(14.44f);
		expectedPersonDTO.setSalary(1201.6);
		expectedPersonDTO.setAnnualIncome(new BigDecimal("1.00000000000000000000"));
		expectedPersonDTO.setDateOfBirth(Date.valueOf("2011-11-11"));
		expectedPersonDTO.setRegistrationDateTime(Timestamp.valueOf("2005-05-05 05:02:05.0"));
		expectedPersonDTO.setSleepTime(Time.valueOf("01:04:04"));
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_id, prsn_age + ? as prsn_age_plus, prsn_cpr - ? as prsn_cpr_minus, prsn_account_no / prsn_account_no as prsn_account_no_divide, prsn_gpa * prsn_gpa as prsn_gpa_multiply, prsn_salary + prsn_salary as prsn_salary_plus, prsn_annual_income / prsn_annual_income as prsn_annual_income_divide, prsn_date_of_birth + interval '1 minutes' as prsn_date_of_birth_plus, prsn_registration_date_time - interval '3 minutes' as prsn_registration_date_time_minus, prsn_sleep_time - interval '3 hours' as prsn_sleep_time_minus from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, 1, 2, id, secondId);
		isRecordMapEqual(personsMap, expectedPersonsMap);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record Map with Class with multi-relation table");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD, Address.BLOCK)
		.from(Table.PERSON)
		.join(Table.ADDRESS)
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID))
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		personsMap = getRecordMap(PersonDTO.class);
		
		expectedPersonsMap.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(secondId);
		expectedPersonDTO.setName("Fatima");
		expectedPersonDTO.setGender('F');
		expectedPersonDTO.setAge((short) 20);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(id);
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, addr_road, addr_block from public.person inner join public.address on prsn_home_address_id = addr_id where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordMapEqual(personsMap, expectedPersonsMap);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record Map with Class with multi-relation table with column alias");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD.as("road_block"), Address.BLOCK.as("address_block"))
		.from(Table.PERSON)
		.join(Table.ADDRESS)
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID))
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		personsMap = getRecordMap(PersonDTO.class);
		
		expectedPersonsMap.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(id);
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(secondId);
		expectedPersonDTO.setName("Fatima");
		expectedPersonDTO.setGender('F');
		expectedPersonDTO.setAge((short) 20);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, addr_road as road_block, addr_block as address_block from public.person inner join public.address on prsn_home_address_id = addr_id where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordMapEqual(personsMap, expectedPersonsMap);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record Map with Join Query");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD, Address.BLOCK)
		.from(Table.PERSON)
		.join(new Query().select().from(Table.ADDRESS))
			.as("all_addresses")
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID))
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		personsMap = getRecordMap(PersonDTO.class);
		
		expectedPersonsMap.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(id);
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(secondId);
		expectedPersonDTO.setName("Fatima");
		expectedPersonDTO.setGender('F');
		expectedPersonDTO.setAge((short) 20);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, addr_road, addr_block from public.person inner join (select * from public.address) as all_addresses on prsn_home_address_id = addr_id where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordMapEqual(personsMap, expectedPersonsMap);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record Map with Class with alias table");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD.of("home_address").as("home_address_road")
				, Address.BLOCK.of("home_address"))
		.select(Address.ROAD.of("work_address"), Address.BLOCK.of("work_address"))
		.from(Table.PERSON.as("person"))
		.join(Table.ADDRESS.as("home_address"))
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID.of("home_address")))
		.join(Table.ADDRESS.as("work_address"))
			.on(Person.WORK_ADDRESS_ID.equal(Address.ID.of("work_address")))
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		personsMap = getRecordMap(PersonDTO.class);
		
		expectedPersonsMap.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(id);
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		expectedPersonDTO.setWorkAddress(new AddressDTO());
		expectedPersonDTO.getWorkAddress().setRoad("Road 2");
		expectedPersonDTO.getWorkAddress().setBlock("Block 2");
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(secondId);
		expectedPersonDTO.setName("Fatima");
		expectedPersonDTO.setGender('F');
		expectedPersonDTO.setAge((short) 20);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		expectedPersonDTO.setWorkAddress(new AddressDTO());
		expectedPersonDTO.getWorkAddress().setRoad("Road 2");
		expectedPersonDTO.getWorkAddress().setBlock("Block 2");
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, home_address.addr_road as home_address_road, home_address.addr_block as home_address_addr_block, work_address.addr_road as work_address_addr_road, work_address.addr_block as work_address_addr_block from public.person as person inner join public.address as home_address on prsn_home_address_id = home_address.addr_id inner join public.address as work_address on prsn_work_address_id = work_address.addr_id where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordMapEqual(personsMap, expectedPersonsMap);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Get Record Map with Class with alias join query");
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD.of("home_address").as("home_address_road")
				, Address.BLOCK.of("home_address"))
		.select(Address.ROAD.of("work_address"), Address.BLOCK.of("work_address"))
		.from(Table.PERSON.as("person"))
		.join(new Query().select().from(Table.ADDRESS))
			.as("home_address")
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID.of("home_address")))
		.join(new Query().select().from(Table.ADDRESS))
			.as("work_address")
			.on(Person.WORK_ADDRESS_ID.equal(Address.ID.of("work_address")))
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		personsMap = getRecordMap(PersonDTO.class);
		
		expectedPersonsMap.clear();
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(id);
		expectedPersonDTO.setName("Hasan");
		expectedPersonDTO.setGender('M');
		expectedPersonDTO.setAge((short) 33);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		expectedPersonDTO.setWorkAddress(new AddressDTO());
		expectedPersonDTO.getWorkAddress().setRoad("Road 2");
		expectedPersonDTO.getWorkAddress().setBlock("Block 2");
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		expectedPersonDTO = new PersonDTO();
		expectedPersonDTO.setId(secondId);
		expectedPersonDTO.setName("Fatima");
		expectedPersonDTO.setGender('F');
		expectedPersonDTO.setAge((short) 20);
		expectedPersonDTO.setHomeAddress(new AddressDTO());
		expectedPersonDTO.getHomeAddress().setRoad("Road 1");
		expectedPersonDTO.getHomeAddress().setBlock("Block 1");
		expectedPersonDTO.setWorkAddress(new AddressDTO());
		expectedPersonDTO.getWorkAddress().setRoad("Road 2");
		expectedPersonDTO.getWorkAddress().setBlock("Block 2");
		
		expectedPersonsMap.put(expectedPersonDTO.getId(), expectedPersonDTO);
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, home_address.addr_road as home_address_road, home_address.addr_block as home_address_addr_block, work_address.addr_road as work_address_addr_road, work_address.addr_block as work_address_addr_block from public.person as person inner join (select * from public.address) as home_address on prsn_home_address_id = home_address.addr_id inner join (select * from public.address) as work_address on prsn_work_address_id = work_address.addr_id where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordMapEqual(personsMap, expectedPersonsMap);
	}
	
	public void testRowNumberColumn() throws Exception {
		
		id = insertInto(Table.PERSON)
				.values(Person.NAME, "Hasan")
				.values(Person.GENDER, 'M')
				.values(Person.AGE, (short)33)
				.values(Person.CPR, 870501236)
				.executeInsert();
		
		secondId = insertInto(Table.PERSON)
				.values(Person.NAME, "Fatima")
				.values(Person.GENDER, 'F')
				.values(Person.AGE, 20)
				.values(Person.CPR, 910102548)
				.executeInsert();
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test basic Row Number with one row");
		
		select(Person.ID, Person.NAME, Person.GENDER)
		.select(Person.AGE, Person.CPR, row_number())
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, row_number() over() as row_num from public.person where prsn_id = ?");
		isParametersEquals(parameters, id);
		isRecordEqual(record,
						entry(Person.ID, id),
						entry(Person.NAME, "Hasan"),
						entry(Person.GENDER, 'M'),
						entry(Person.AGE, (short)33),
						entry(Person.CPR, 870501236),
						entry(row_number(), 1));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test basic Row Number with multiple row");
		
		select(Person.ID, Person.NAME, Person.GENDER)
		.select(Person.AGE, Person.CPR, row_number())
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		records = getRecordList();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, row_number() over() as row_num from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(records,
							map(entry(Person.ID, id),
								entry(Person.NAME, "Hasan"),
								entry(Person.GENDER, 'M'),
								entry(Person.AGE, (short)33),
								entry(Person.CPR, 870501236),
								entry(row_number(), 1)
							),
							map(entry(Person.ID, secondId),
								entry(Person.NAME, "Fatima"),
								entry(Person.GENDER, 'F'),
								entry(Person.AGE, (short)20),
								entry(Person.CPR, 910102548),
								entry(row_number(), 2)
							)
						);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Row Number with one order by with multiple row");
		
		select(Person.ID, Person.NAME, Person.GENDER)
		.select(Person.AGE, Person.CPR, row_number().over(order_by(Person.ID)))
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		records = getRecordList();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, row_number() over(order by prsn_id) as row_num from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(records,
							map(entry(Person.ID, id),
								entry(Person.NAME, "Hasan"),
								entry(Person.GENDER, 'M'),
								entry(Person.AGE, (short)33),
								entry(Person.CPR, 870501236),
								entry(row_number().over(order_by(Person.ID)), 1)
							),
							map(entry(Person.ID, secondId),
								entry(Person.NAME, "Fatima"),
								entry(Person.GENDER, 'F'),
								entry(Person.AGE, (short)20),
								entry(Person.CPR, 910102548),
								entry(row_number().over(order_by(Person.ID)), 2)
							)
						);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Row Number with multiple order by with multiple row");
		
		select(Person.ID, Person.NAME, Person.GENDER)
		.select(Person.AGE, Person.CPR, row_number().over(order_by(Person.ID.asc(), Person.DATE_OF_BIRTH.desc())))
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		records = getRecordList();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, row_number() over(order by prsn_id, prsn_date_of_birth desc) as row_num from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(records,
							map(entry(Person.ID, id),
								entry(Person.NAME, "Hasan"),
								entry(Person.GENDER, 'M'),
								entry(Person.AGE, (short)33),
								entry(Person.CPR, 870501236),
								entry(row_number().over(order_by(Person.ID.asc(), Person.DATE_OF_BIRTH.desc())), 1)
							),
							map(entry(Person.ID, secondId),
								entry(Person.NAME, "Fatima"),
								entry(Person.GENDER, 'F'),
								entry(Person.AGE, (short)20),
								entry(Person.CPR, 910102548),
								entry(row_number().over(order_by(Person.ID.asc(), Person.DATE_OF_BIRTH.desc())), 2)
							)
						);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Row Number with one partition by with multiple row");
		
		select(Person.ID, Person.NAME, Person.GENDER)
		.select(Person.AGE, Person.CPR, row_number().over(partition_by(Person.ID)))
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		records = getRecordList();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, row_number() over(partition by prsn_id) as row_num from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(records,
							map(entry(Person.ID, id),
								entry(Person.NAME, "Hasan"),
								entry(Person.GENDER, 'M'),
								entry(Person.AGE, (short)33),
								entry(Person.CPR, 870501236),
								entry(row_number().over(partition_by(Person.ID)), 1)
							),
							map(entry(Person.ID, secondId),
								entry(Person.NAME, "Fatima"),
								entry(Person.GENDER, 'F'),
								entry(Person.AGE, (short)20),
								entry(Person.CPR, 910102548),
								entry(row_number().over(partition_by(Person.ID)), 1)
							)
						);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Row Number with multiple partition by with multiple row");
		
		select(Person.ID, Person.NAME, Person.GENDER)
		.select(Person.AGE, Person.CPR, row_number().over(partition_by(Person.ID, Person.DATE_OF_BIRTH)))
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		records = getRecordList();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, row_number() over(partition by prsn_id, prsn_date_of_birth) as row_num from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(records,
							map(entry(Person.ID, id),
								entry(Person.NAME, "Hasan"),
								entry(Person.GENDER, 'M'),
								entry(Person.AGE, (short)33),
								entry(Person.CPR, 870501236),
								entry(row_number().over(partition_by(Person.ID, Person.DATE_OF_BIRTH)), 1)
							),
							map(entry(Person.ID, secondId),
								entry(Person.NAME, "Fatima"),
								entry(Person.GENDER, 'F'),
								entry(Person.AGE, (short)20),
								entry(Person.CPR, 910102548),
								entry(row_number().over(partition_by(Person.ID, Person.DATE_OF_BIRTH)), 1)
							)
						);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Row Number with one partition by and one order by with multiple row");
		
		select(Person.ID, Person.NAME, Person.GENDER)
		.select(Person.AGE, Person.CPR, row_number().over(partition_by(Person.DATE_OF_BIRTH).order_by(Person.ID)))
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		records = getRecordList();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, row_number() over(partition by prsn_date_of_birth order by prsn_id) as row_num from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(records,
							map(entry(Person.ID, id),
								entry(Person.NAME, "Hasan"),
								entry(Person.GENDER, 'M'),
								entry(Person.AGE, (short)33),
								entry(Person.CPR, 870501236),
								entry(row_number().over(partition_by(Person.DATE_OF_BIRTH).order_by(Person.ID)), 1)
							),
							map(entry(Person.ID, secondId),
								entry(Person.NAME, "Fatima"),
								entry(Person.GENDER, 'F'),
								entry(Person.AGE, (short)20),
								entry(Person.CPR, 910102548),
								entry(row_number().over(partition_by(Person.DATE_OF_BIRTH).order_by(Person.ID)), 2)
							)
						);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Row Number with multiple partition by and multiple order by with multiple row");
		
		select(Person.ID, Person.NAME, Person.GENDER)
		.select(Person.AGE, Person.CPR, row_number().over(partition_by(Person.DATE_OF_BIRTH, Person.AGE).order_by(Person.ID.asc(), Person.NAME.desc())))
		.from(Table.PERSON)
		.where(Person.ID.in(id, secondId));
		
		query = getSelectQuery(parameters);
		
		records = getRecordList();
		
		isQueryEqual(query, "select prsn_id, prsn_name, prsn_gender, prsn_age, prsn_cpr, row_number() over(partition by prsn_date_of_birth, prsn_age order by prsn_id, prsn_name desc) as row_num from public.person where prsn_id in (?, ?)");
		isParametersEquals(parameters, id, secondId);
		isRecordListEqual(records,
							map(entry(Person.ID, secondId),
								entry(Person.NAME, "Fatima"),
								entry(Person.GENDER, 'F'),
								entry(Person.AGE, (short)20),
								entry(Person.CPR, 910102548),
								entry(row_number().over(partition_by(Person.DATE_OF_BIRTH, Person.AGE).order_by(Person.ID.asc(), Person.NAME.desc())), 1)
							),
							map(entry(Person.ID, id),
								entry(Person.NAME, "Hasan"),
								entry(Person.GENDER, 'M'),
								entry(Person.AGE, (short)33),
								entry(Person.CPR, 870501236),
								entry(row_number().over(partition_by(Person.DATE_OF_BIRTH, Person.AGE).order_by(Person.ID.asc(), Person.NAME.desc())), 1)
							)
						);
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Nested Row Number with multiple partition by and multiple order by with multiple row");
		
		select(Person.CPR, Person.NAME)
		.from(new Query().select(Person.ID, Person.CPR, Person.NAME, row_number()
										.over(partition_by(Person.DATE_OF_BIRTH, 
												Person.AGE)
												.order_by(Person.ID.asc(), 
											Person.NAME.desc())).as("person_row_number"))
				.from(Table.PERSON)
				.where(Person.ID.in(id, secondId)))
		.as("ordered_persons")
		.where(row_number().alias("person_row_number").less(3));
		
		query = getSelectQuery(parameters);
		
		records = getRecordList();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name from (select prsn_id, prsn_cpr, prsn_name, row_number() over(partition by prsn_date_of_birth, prsn_age order by prsn_id, prsn_name desc) as person_row_number from public.person where prsn_id in (?, ?)) as ordered_persons where person_row_number < ?");
		isParametersEquals(parameters, id, secondId, 3);
		isRecordListEqual(records,
							map(
								entry(Person.CPR, 910102548),
								entry(Person.NAME, "Fatima")
							),
							map(entry(Person.CPR, 870501236),
								entry(Person.NAME, "Hasan")
							)
						);
	}
	
	public void testCaseColumnWithConditions() throws Exception {
		
		id = insertInto(Table.PERSON)
				.values(Person.NAME, "Hasan")
				.values(Person.GENDER, 'M')
				.values(Person.AGE, (short)33)
				.values(Person.CPR, 870501236)
				.executeInsert();
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Case with basic condition and value");
		
		select(Person.CPR, Person.NAME, 
					case_(
							when(Person.AGE.greater(20))
							.then("Man"),
							when(Person.AGE.less(20))
							.then("Young")
						)
					.as("age_title")
				)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name, case when prsn_age > ? then ? when prsn_age < ? then ? end as age_title from public.person where prsn_id = ?");
		isParametersEquals(parameters, 20, "Man", 20, "Young", id);
		isRecordEqual(record,
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"),
						entry(case_(
								when(Person.AGE.greater(20))
								.then("Man"),
								when(Person.AGE.less(20))
								.then("Young")
							), "Man"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Case with basic condition and value with else");
		
		select(Person.CPR, Person.NAME, 
					case_(
							when(Person.AGE.greater(20))
							.then("Man"),
							when(Person.AGE.less(20))
							.then("Young"),
							else_("Baby")
						)
					.as("age_title")
				)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name, case when prsn_age > ? then ? when prsn_age < ? then ? else ? end as age_title from public.person where prsn_id = ?");
		isParametersEquals(parameters, 20, "Man", 20, "Young", "Baby", id);
		isRecordEqual(record,
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"),
						entry(case_(
								when(Person.AGE.greater(20))
								.then("Man"),
								when(Person.AGE.less(20))
								.then("Young"),
								else_("Baby")
							), "Man"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Case with nested condition and value with else");
		
		select(Person.CPR, Person.NAME, 
					case_(
							when(Person.AGE.greater(20).and(Person.AGE.less(40)))
							.then("Man"),
							when(Person.AGE.less(20).and(Person.AGE.greater(10)))
							.then("Young"),
							else_("Baby")
						)
					.as("age_title")
				)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name, case when (prsn_age > ? and prsn_age < ?) then ? when (prsn_age < ? and prsn_age > ?) then ? else ? end as age_title from public.person where prsn_id = ?");
		isParametersEquals(parameters, 20, 40, "Man", 20, 10, "Young", "Baby", id);
		isRecordEqual(record,
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"),
						entry(case_(
								when(Person.AGE.greater(20).and(Person.AGE.less(40)))
								.then("Man"),
								when(Person.AGE.less(20).and(Person.AGE.greater(10)))
								.then("Young"),
								else_("Baby")
							), "Man"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Case with nested condition and column with else");
		
		select(Person.CPR, Person.NAME, 
					case_(
							when(Person.AGE.greater(20).and(Person.AGE.less(40)))
							.then(Person.SALARY),
							when(Person.AGE.less(20).and(Person.AGE.greater(10)))
							.then(Person.ANNUAL_INCOME),
							else_(Person.GPA)
						)
					.as("age_title")
				)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name, case when (prsn_age > ? and prsn_age < ?) then prsn_salary when (prsn_age < ? and prsn_age > ?) then prsn_annual_income else prsn_gpa end as age_title from public.person where prsn_id = ?");
		isParametersEquals(parameters, 20, 40, 20, 10, id);
		isRecordEqual(record,
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"),
						entry(case_(
								when(Person.AGE.greater(20).and(Person.AGE.less(40)))
								.then(Person.SALARY),
								when(Person.AGE.less(20).and(Person.AGE.greater(10)))
								.then(Person.ANNUAL_INCOME),
								else_(Person.GPA)
							), 666.55f));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Case with nested condition and math column with else");
		
		select(Person.CPR, Person.NAME, 
					case_(
							when(Person.AGE.greater(20).and(Person.AGE.less(40)))
							.then(Person.SALARY.plus(100)),
							when(Person.AGE.less(20).and(Person.AGE.greater(10)))
							.then(Person.ANNUAL_INCOME.minus(200)),
							else_(Person.GPA.multiply(3))
						)
					.as("age_title")
				)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name, case when (prsn_age > ? and prsn_age < ?) then prsn_salary + ? when (prsn_age < ? and prsn_age > ?) then prsn_annual_income - ? else prsn_gpa * ? end as age_title from public.person where prsn_id = ?");
		isParametersEquals(parameters, 20, 40, 100, 20, 10, 200, 3, id);
		isRecordEqual(record,
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"),
						entry(case_(
								when(Person.AGE.greater(20).and(Person.AGE.less(40)))
								.then(Person.SALARY.plus(100)),
								when(Person.AGE.less(20).and(Person.AGE.greater(10)))
								.then(Person.ANNUAL_INCOME.minus(200)),
								else_(Person.GPA.multiply(3))
							), 766.55f));
	}
	
	public void testCaseColumnWithSwitch() throws Exception {
		
		id = insertInto(Table.PERSON)
				.values(Person.NAME, "Hasan")
				.values(Person.GENDER, 'M')
				.values(Person.AGE, (short)33)
				.values(Person.CPR, 870501236)
				.executeInsert();
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Case with basic switch value");
		
		select(Person.CPR, Person.NAME, 
					case_(Person.AGE,
							when(33)
							.then("Man"),
							when(20)
							.then("Young")
						)
					.as("age_title")
				)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name, case prsn_age when ? then ? when ? then ? end as age_title from public.person where prsn_id = ?");
		isParametersEquals(parameters, 33, "Man", 20, "Young", id);
		isRecordEqual(record,
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"),
						entry(case_(Person.AGE,
								when(33)
								.then("Man"),
								when(20)
								.then("Young")
							), "Man"));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Case with basic switch value with else");
		
		select(Person.CPR, Person.NAME, 
					case_(Person.AGE,
							when(20)
							.then("Man"),
							when(20)
							.then("Young"),
							else_("Baby")
						)
					.as("age_title")
				)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name, case prsn_age when ? then ? when ? then ? else ? end as age_title from public.person where prsn_id = ?");
		isParametersEquals(parameters, 20, "Man", 20, "Young", "Baby", id);
		isRecordEqual(record,
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"),
						entry(case_(
								Person.AGE,
								when(20)
								.then("Man"),
								when(20)
								.then("Young"),
								else_("Baby")
							), "Baby"));
		
		parameters.clear();
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Case with basic switch column with else");
		
		select(Person.CPR, Person.NAME, 
					case_(Person.AGE,
							when(20)
							.then(Person.SALARY),
							when(40)
							.then(Person.ANNUAL_INCOME),
							else_(Person.GPA)
						)
					.as("age_title")
				)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name, case prsn_age when ? then prsn_salary when ? then prsn_annual_income else prsn_gpa end as age_title from public.person where prsn_id = ?");
		isParametersEquals(parameters, 20, 40, id);
		isRecordEqual(record,
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"),
						entry(case_(
								Person.AGE,
								when(20)
								.then(Person.SALARY),
								when(40)
								.then(Person.ANNUAL_INCOME),
								else_(Person.GPA)
							), 3.14f));
		
		parameters.clear();
		
		excelUtils.addRecordSequance();
		excelUtils.addRecordValue("Test Case with nested condition and math column with else");
		
		select(Person.CPR, Person.NAME, 
					case_(
							Person.AGE,
							when(20)
							.then(Person.SALARY.plus(100)),
							when(40)
							.then(Person.ANNUAL_INCOME.minus(200)),
							else_(Person.GPA.minus(1))
						)
					.as("age_title")
				)
		.from(Table.PERSON)
		.where(Person.ID.equal(id));
		
		query = getSelectQuery(parameters);
		
		record = getRecord();
		
		isQueryEqual(query, "select prsn_cpr, prsn_name, case prsn_age when ? then prsn_salary + ? when ? then prsn_annual_income - ? else prsn_gpa - ? end as age_title from public.person where prsn_id = ?");
		isParametersEquals(parameters, 20, 100, 40, 200, 1, id);
		isRecordEqual(record,
						entry(Person.CPR, 870501236),
						entry(Person.NAME, "Hasan"),
						entry(case_(
								Person.AGE,
								when(20)
								.then(Person.SALARY.plus(100)),
								when(40)
								.then(Person.ANNUAL_INCOME.minus(200)),
								else_(Person.GPA.minus(1))
							), 2.14f));
	}
	
	public static void main(String[] args) throws Exception {
		
		try(Connection connection = GeneratorUtils.getConnection()){
			
			TestSelectQuery testSelectQuery = new TestSelectQuery(connection);
			
			testSelectQuery.testSelectClause();
			
			testSelectQuery.testFromClause();
			
			testSelectQuery.testJoinClause();
			
			testSelectQuery.testWhereClause();
			
			testSelectQuery.testOrderByClause();
			
			testSelectQuery.testGroupByHavingClause();
			
			testSelectQuery.testPaginationClause();
			
			testSelectQuery.testCombineQueriesClause();
			
			testSelectQuery.testGetSingleResult();

			testSelectQuery.testGetSingleStringResult();
			
			testSelectQuery.testGetList();
			
			testSelectQuery.testGetStringList();
			
			testSelectQuery.testGetSingleEntry();
			
			testSelectQuery.testGetSingleEntryWithClasses();
			
			testSelectQuery.testGetSingleStringEntryWithClasses();
			
			testSelectQuery.testGetEntryList();
			
			testSelectQuery.testGetEntryListWithClasses();
			
			testSelectQuery.testGetStringEntryList();
			
			testSelectQuery.testGetObjectResult();
			
			testSelectQuery.testGetObjectList();
			
			testSelectQuery.testGetMapResult();
			
			testSelectQuery.testGetMapResultWithClasses();
			
			testSelectQuery.testGetStringMapResult();
			
			testSelectQuery.testGetRecordCount();
			
			testSelectQuery.testGetRecord();
			
			testSelectQuery.testGetRecordList();
			
			testSelectQuery.testGetRecordMap();
			
			testSelectQuery.testGetRecordClass();
			
			testSelectQuery.testGetRecordListClass();
			
			testSelectQuery.testGetRecordMapClass();
			
			testSelectQuery.testRowNumberColumn();
			
			testSelectQuery.testCaseColumnWithConditions();
			
			testSelectQuery.testCaseColumnWithSwitch();
			
			testSelectQuery.writeWorkbook();
		}
	}
}
