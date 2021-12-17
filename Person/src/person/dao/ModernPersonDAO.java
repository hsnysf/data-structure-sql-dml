package person.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
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
import person.dto.PersonDTO;

public class ModernPersonDAO extends Query {

	public ModernPersonDAO(Connection connection) {
		super(connection);
	}
	
	public PersonDTO insertPerson(PersonDTO personDTO) throws SQLException {
		
		int id = insertInto(Table.PERSON)
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
					.executeInsert();
		
		personDTO.setId(id);
		
		return personDTO;
	}
	
	public int deletePerson(PersonDTO personDTO) throws SQLException {
		
		return deleteFrom(Table.PERSON)
					.where(Person.NAME.like(personDTO.getName()))
					.and(Person.GENDER.equal(personDTO.getGender()))
					.and(Person.AGE.equal(personDTO.getAge()))
					.and(Person.CPR.equal(personDTO.getCpr()))
					.and(Person.ACCOUNT_NO.equal(personDTO.getAccountNo()))
					.and(Person.GPA.equal(personDTO.getGpa()))
					.and(Person.SALARY.equal(personDTO.getSalary()))
					.and(Person.ANNUAL_INCOME.equal(personDTO.getAnnualIncome()))
					.and(Person.DATE_OF_BIRTH.equal(personDTO.getDateOfBirth()))
					.and(Person.REGISTRATION_DATE_TIME.equal(personDTO.getRegistrationDateTime()))
					.and(Person.SLEEP_TIME.equal(personDTO.getSleepTime()))
					.and(Person.GRADUATED.equal(personDTO.getGraduated()))
					.and(Person.ID.equal(personDTO.getId()))
					.executeDelete();
	}
	
	public int updatePerson(PersonDTO personDTO) throws SQLException {
		
		return update(Table.PERSON)
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
				.where(Person.ID.equal(personDTO.getId()))
					.executeUpdate();
	}
	
	public PersonDTO getPerson(PersonDTO personDTO) throws SQLException {
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME, Person.GRADUATED)
		.from(Table.PERSON)
		.where(Person.NAME.like(personDTO.getName()))
		.and(Person.GENDER.equal(personDTO.getGender()))
		.and(Person.AGE.equal(personDTO.getAge()))
		.and(Person.CPR.equal(personDTO.getCpr()))
		.and(Person.ACCOUNT_NO.equal(personDTO.getAccountNo()))
		.and(Person.GPA.equal(personDTO.getGpa()))
		.and(Person.SALARY.equal(personDTO.getSalary()))
		.and(Person.ANNUAL_INCOME.equal(personDTO.getAnnualIncome()))
		.and(Person.DATE_OF_BIRTH.equal(personDTO.getDateOfBirth()))
		.and(Person.REGISTRATION_DATE_TIME.equal(personDTO.getRegistrationDateTime()))
		.and(Person.SLEEP_TIME.equal(personDTO.getSleepTime()))
		.and(Person.GRADUATED.equal(personDTO.getGraduated()))
		.and(Person.ID.equal(personDTO.getId()))
		.orderBy(Person.NAME.asc(), Person.ID.desc())
			.executeSelect();
		
		return personDTO;
	}
	
	public void selectPersonWithRestrictions() throws SQLException {
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.equal(1))
		.and(Person.NAME.like("Hasan"))
		.and(Person.CPR.notEqual(88111111))
		.and(Person.AGE.greater(12))
		.and(Person.GPA.less(3.12))
		.and(Person.SALARY.lessEqual(500))
		.and(Person.ANNUAL_INCOME.greaterEqual(100))
		.and(Person.ACCOUNT_NO.in(111, 222, 333))
		.and(Person.AGE.notIn(11, 12, 13))
		.and(Person.SLEEP_TIME.between("08:10:10", "10:30:10"))
		.and(Person.GENDER.isNull())
		.and(Person.GRADUATED.isNotNull())
		.and(Person.DATE_OF_BIRTH.in(new Query().select(UniqueDateOfBirth.DATE)
				.from(Table.UNIQUE_DATE_OF_BIRTH).where(UniqueDateOfBirth.LEAP_YEAR.equal(true))))
		.and(Person.DATE_OF_BIRTH.notIn(new Query().select(UniqueDateOfBirth.DATE)
				.from(Table.UNIQUE_DATE_OF_BIRTH).where(UniqueDateOfBirth.LEAP_YEAR.equal(false))))
		.and(exists(new Query().select(Doctor.ID)
				.from(Table.DOCTOR).where(Doctor.CPR.equal(Person.CPR)).and(Doctor.HOSPITAL.equal("Alkindi"))))
		.and(notExists(new Query().select(Doctor.ID)
				.from(Table.DOCTOR).where(Doctor.CPR.equal(Person.CPR)).and(Doctor.HOSPITAL.equal("Bin Hayan"))))
		.orderBy(Person.ID.asc(), Person.NAME.desc())
		.executeSelect();
	}
	
	public void selectPersonWithNestedRestrictions() throws SQLException {
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.from(Table.PERSON)
		.where(Person.GENDER.equal('M'))
		.and(Person.ID.equal(1).and(Person.NAME.like("Hasan").or(Person.NAME.like("Ali"))))
		.and(Person.CPR.notEqual(88111111).or(Person.GENDER.isNull()))
		.executeSelect();
	}
	
	public void selectPersonWithJoins() throws SQLException {
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(City.ID, City.NAME)
		.select(Company.ID, Company.NAME)
		.select(Country.ID, Country.NAME)
		.select(School.ID, School.NAME)
		.from(Table.PERSON)
		.innerJoin(Table.CITY).on(Person.CITY_ID.equal(City.ID))
		.rightJoin(Table.COMPANY).on(Person.COMPANY_ID.equal(Company.ID).and(Company.NAME.like("Google")))
		.leftJoin(Table.COUNTRY).on(Person.COUNTRY_ID.equal(Country.ID))
		.fullJoin(Table.SCHOOL).on(Person.SCHOOL_ID.equal(School.ID).and(School.NAME.equal("Microsoft")))
		.where(Person.ID.equal(1))
		.executeSelect();
	}
	
	public void selectPersonWithCombinedQueries() throws SQLException {
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.equal(1))
		.union(
			new Query().select(Person.NAME, Person.GENDER, Person.AGE)
			.from(Table.PERSON)
			.where(Person.ID.equal(2))
		)
		.unionAll(
			new Query().select(Person.NAME, Person.GENDER, Person.AGE)
			.from(Table.PERSON)
			.where(Person.ID.equal(3))
		)
		.intersect(
			new Query().select(Person.NAME, Person.GENDER, Person.AGE)
			.from(Table.PERSON)
			.where(Person.ID.equal(4))
		)
		.intersectAll(
			new Query().select(Person.NAME, Person.GENDER, Person.AGE)
			.from(Table.PERSON)
			.where(Person.ID.equal(5))
		)
		.except(
			new Query().select(Person.NAME, Person.GENDER, Person.AGE)
			.from(Table.PERSON)
			.where(Person.ID.equal(6))
		)
		.exceptAll(
			new Query().select(Person.NAME, Person.GENDER, Person.AGE)
			.from(Table.PERSON)
			.where(Person.ID.equal(7))
		)
		.executeSelect();
	}
	
	public void selectPersonWithFunctions() throws SQLException {
		
		select(count(Person.ID), sum(Person.SALARY), average(Person.ANNUAL_INCOME))
		.select(min(Person.CPR), max(Person.GPA))
		.from(Table.PERSON)
		.where(Person.ID.equal(1))
		.groupBy(Person.GENDER, Person.AGE)
		.having(count(Person.ID).greater(10).and(sum(Person.SALARY).less(100)))
		.orderBy(Person.GENDER)
		.executeSelect();
		
		select(distinct(Person.DATE_OF_BIRTH))
		.from(Table.PERSON)
		.where(Person.GPA.greater(3.12))
		.executeSelect();
		
		select(coalesce(Person.AGE, 20))
		.from(Table.PERSON)
		.executeSelect();
		
		select(coalesce(Person.ANNUAL_INCOME, Person.SALARY))
		.from(Table.PERSON)
		.executeSelect();
		
		select(Person.DATE_OF_BIRTH, count(Person.ID))
		.from(Table.PERSON)
		.groupBy(Person.DATE_OF_BIRTH)
		.having(count(Person.ID).greater(2))
		.orderBy(Person.DATE_OF_BIRTH)
		.executeSelect();
	}
	
	public void selectPersonWithAlias() throws SQLException {
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(Address.ROAD.of("home_address"), Address.BLOCK.of("home_address"))
		.select(Address.ROAD.of("work_address"), Address.BLOCK.of("work_address"))
		.from(Table.PERSON.as("person"))
		.join(Table.ADDRESS.as("home_address"))
			.on(Person.HOME_ADDRESS_ID.equal(Address.ID.of("home_address")))
		.join(Table.ADDRESS.as("work_address"))
			.on(Person.WORK_ADDRESS_ID.equal(Address.ID.of("work_address")))
		.where(Person.ID.equal(1))
		.executeSelect();
	}
	
	public void selectPersonWithPagination() throws SQLException {
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.from(Table.PERSON)
		.limit(2)
		.offset(4)
		.executeSelect();
	}
	
	public void selectPersonWithSubQuery() throws SQLException {
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.from(new Query().from(Table.PERSON)
				.where(Person.GENDER.equal('M'))).as("male_person")
		.join(new Query().from(Table.DOCTOR)
				.where(Doctor.HOSPITAL.equal("Alkindi")))
				.as("alkindi_doctor")
			.on(Doctor.CPR.equal(Person.CPR))
		.executeSelect();
		
		select(Person.ID)
		.from(
				new Query().from(
									new Query().from(Table.PERSON)
									.where(Person.GENDER.equal('M'))
								).as("person_gender")
				.where(Person.AGE.equal(12))
			 ).as("person_age")
		.join(
				new Query().from(
									new Query().from(Table.CITY)
									.where(City.ID.equal(1))
								).as("city_id")
				.where(City.NAME.equal("Manama"))
			 ).as("city_name")
		.on(Person.CITY_ID.equal(City.ID))
		.where(Person.GPA.equal(3.12))
		.executeSelect();
	}
	
	public void selectPersonWithRowNumber() throws SQLException {
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(row_number().over())
		.from(Table.PERSON)
		.executeSelect();
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(row_number().over(partiton_by(Person.AGE, Person.GRADUATED)))
		.from(Table.PERSON)
		.executeSelect();
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(row_number().over(order_by(Person.AGE.asc(), Person.GRADUATED.desc())))
		.from(Table.PERSON)
		.executeSelect();
		
		select(Person.NAME, Person.GENDER, Person.AGE)
		.select(row_number().over(
								partiton_by(Person.AGE, Person.GRADUATED)
								.order_by(Person.AGE.asc(), Person.GRADUATED.desc())))
		.from(Table.PERSON)
		.executeSelect();
		
		select(Person.NAME, Person.AGE)
		.from(new Query()
				.select(Person.NAME, Person.AGE)
				.select(row_number().over(partiton_by(Person.AGE)
						.order_by(Person.GPA.desc()))
						.as("person_row_number"))
				.from(Table.PERSON)
			  ).as("person")
		.where(row_number().alias("person_row_number").equal(1))
		.executeSelect();
	}
	
	public void selectPersonWithCase() throws SQLException {
		
		select(Person.NAME, Person.GENDER)
		.select(
				case_(
						when(Person.AGE.equal(12)).then("Young"),
						when(Person.AGE.equal(13)).then("Man"),
						else_("Baby")
					 )
					.as("person_age_case")
				)
		.from(Table.PERSON)
		.executeSelect();
		
		select(Person.NAME, Person.GENDER)
		.select(
				case_(
						when(Person.AGE.equal(12)).then(Person.SALARY),
						when(Person.AGE.equal(13)).then(Person.ACCOUNT_NO),
						else_(Person.ANNUAL_INCOME)
					 )
					.as("person_age_case")
				)
		.from(Table.PERSON)
		.executeSelect();
		
		select(Person.NAME, Person.GENDER)
		.select(
				case_(Person.AGE,
						when(12).then("Young"),
						when(13).then("Man"),
						else_("Baby")
					 )
					.as("person_age_case")
				)
		.from(Table.PERSON)
		.executeSelect();
		
		select(Person.NAME, Person.GENDER)
		.select(
				case_(Person.AGE,
						when(12).then(Person.SALARY),
						when(13).then(Person.ACCOUNT_NO),
						else_(Person.ANNUAL_INCOME)
					 )
					.as("person_age_case")
				)
		.from(Table.PERSON)
		.executeSelect();
	}
	
	public void selectPersonWithMath() throws SQLException {
		
		select(Person.AGE.plus(2).as("new_age"), 
				Person.SALARY.minus(3).as("new_salary"),
				Person.GPA.multiply(4).as("new_gpa"), 
				Person.ANNUAL_INCOME.divide(5).as("new_income"))
		.from(Table.PERSON)
		.executeSelect();
		
		select(Person.ID)
		.from(Table.PERSON)
		.where(Person.AGE.plus(2).equal(1))
		.and(Person.SALARY.minus(3).equal(2))
		.and(Person.GPA.multiply(4).equal(3))
		.and(Person.ANNUAL_INCOME.divide(5).equal(4))
 		.executeSelect();
		
		select(Person.ANNUAL_INCOME.plus(Person.SALARY).as("total"))
		.select(Person.ANNUAL_INCOME.minus(Person.SALARY).as("minus"))
		.select(Person.ANNUAL_INCOME.multiply(Person.SALARY).as("multiply"))
		.select(Person.ANNUAL_INCOME.divide(Person.SALARY).as("divide"))
		.from(Table.PERSON)
		.executeSelect();
		
		select(Person.DATE_OF_BIRTH.plusDays(1), CURRENT_DATE.plusHours(2))
		.select(Person.REGISTRATION_DATE_TIME.minusMonths(2).minusDays(2))
		.from(Table.PERSON)
		.where(Person.DATE_OF_BIRTH.plusDays(1).equal(CURRENT_DATE))
		.executeSelect();
	}
	
	public void insertPersonWithSelect() throws SQLException {
		
		insertInto(Table.PERSON, Person.CPR, Person.NAME)
			.from(new Query().select(Person.CPR, Person.NAME)
					.from(Table.PERSON).where(Person.ID.equal(1)))
			.executeInsert();
	}
	
	
	public void insertPersonWithString() throws SQLException {
		
		insertInto(Table.PERSON)
		.values(Person.NAME, "Hasan")
		.values(Person.GENDER, "M")
		.values(Person.AGE, "23")
		.values(Person.CPR, "880101")
		.values(Person.ACCOUNT_NO, "324234")
		.values(Person.GPA, "4324")
		.values(Person.SALARY, "400")
		.values(Person.ANNUAL_INCOME, "500")
		.values(Person.DATE_OF_BIRTH, "12-12-2000")
		.values(Person.REGISTRATION_DATE_TIME, "04-04-2010 04:04:04")
		.values(Person.SLEEP_TIME, "04:04:04")
		.values(Person.GRADUATED, "true")
		.executeInsert();
	}
	
	public void updatePersonWithString() throws SQLException {
		
		update(Table.PERSON)
		.set(Person.NAME, "Hasan")
		.set(Person.GENDER, "M")
		.set(Person.AGE, "23")
		.set(Person.CPR, "880101")
		.set(Person.ACCOUNT_NO, "324234")
		.set(Person.GPA, "4324")
		.set(Person.SALARY, "400")
		.set(Person.ANNUAL_INCOME, "500")
		.set(Person.DATE_OF_BIRTH, "12-12-2000")
		.set(Person.REGISTRATION_DATE_TIME, "04-04-2010 04:04:04")
		.set(Person.SLEEP_TIME, "04:04:04")
		.set(Person.GRADUATED, "true")
		.executeUpdate();
 	}
	
	public void selectPersonWithString() throws SQLException {
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.from(Table.PERSON)
		.where(Person.ID.equal("1"))
		.where(Person.NAME.equal("Hasan"))
		.and(Person.GENDER.notEqual("M"))
		.and(Person.CPR.greater("88111111"))
		.and(Person.ACCOUNT_NO.greaterEqual("43543553"))
		.and(Person.GPA.less("3.12"))
		.and(Person.SALARY.lessEqual("500"))
		.and(Person.ANNUAL_INCOME.lessEqual("100"))
		.and(Person.DATE_OF_BIRTH.in("12-12-2000", "13-12-2000"))
		.and(Person.REGISTRATION_DATE_TIME.notIn("14-12-2000 05:05:05", "15-12-2000 05:05:05"))
		.and(Person.SLEEP_TIME.between("08:10:10", "10:30:10"))
		.executeSelect();
	}
	
	public void insertPersonWithDate() throws SQLException {
		
		insertInto(Table.PERSON)
		.values(Person.NAME, "Hasan")
		.values(Person.GENDER, "M")
		.values(Person.AGE, "23")
		.values(Person.CPR, "880101")
		.values(Person.ACCOUNT_NO, "324234")
		.values(Person.GPA, "4324")
		.values(Person.SALARY, "400")
		.values(Person.ANNUAL_INCOME, "500")
		.values(Person.DATE_OF_BIRTH, new Date())
		.values(Person.REGISTRATION_DATE_TIME, new Date())
		.values(Person.SLEEP_TIME, new Date())
		.values(Person.GRADUATED, "true")
		.executeInsert();
	}
	
	public void updatePersonWithDate() throws SQLException {
		
		update(Table.PERSON)
		.set(Person.NAME, "Hasan")
		.set(Person.GENDER, "M")
		.set(Person.AGE, "23")
		.set(Person.CPR, "880101")
		.set(Person.ACCOUNT_NO, "324234")
		.set(Person.GPA, "4324")
		.set(Person.SALARY, "400")
		.set(Person.ANNUAL_INCOME, "500")
		.set(Person.DATE_OF_BIRTH, new Date())
		.set(Person.REGISTRATION_DATE_TIME, new Date())
		.set(Person.SLEEP_TIME, new Date())
		.set(Person.GRADUATED, "true")
		.where(Person.ID.equal(1))
		.executeUpdate();
 	}
	
	public void selectPersonWithDate() throws SQLException {
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.from(Table.PERSON)
		.where(Person.DATE_OF_BIRTH.equal(new Date()))
		.and(Person.REGISTRATION_DATE_TIME.notEqual(new Date()))
		.and(Person.SLEEP_TIME.greater(new Date()))
		.and(Person.DATE_OF_BIRTH.less(new Date()))
		.and(Person.REGISTRATION_DATE_TIME.lessEqual(new Date()))
		.and(Person.SLEEP_TIME.greaterEqual(new Date()))
		.and(Person.DATE_OF_BIRTH.in(new Date(), new Date()))
		.and(Person.REGISTRATION_DATE_TIME.notIn(new Date(), new Date()))
		.and(Person.SLEEP_TIME.between(new Date(), new Date()))
		.executeSelect();
	}
	
	public void insertPersonWithArray() throws SQLException {
		
		insertInto(Table.PERSON)
		.values(Person.NAME, "Hasan")
		.values(Person.GENDER, "M")
		.values(Person.AGE, "23")
		.values(Person.CPR, "880101")
		.values(Person.ACCOUNT_NO, "324234")
		.values(Person.GPA, "4324")
		.values(Person.SALARY, "400")
		.values(Person.ANNUAL_INCOME, "500")
		.values(Person.DATE_OF_BIRTH, new Date())
		.values(Person.REGISTRATION_DATE_TIME, new Date())
		.values(Person.SLEEP_TIME, new Date())
		.values(Person.GRADUATED, "true")
		.values(Person.CERTIFICATES, Arrays.asList("DB2", "Java"))
		.executeInsert();
	}
	
	public void updatePersonWithArray() throws SQLException {
		
		update(Table.PERSON)
		.set(Person.NAME, "Hasan")
		.set(Person.GENDER, "M")
		.set(Person.AGE, "23")
		.set(Person.CPR, "880101")
		.set(Person.ACCOUNT_NO, "324234")
		.set(Person.GPA, "4324")
		.set(Person.SALARY, "400")
		.set(Person.ANNUAL_INCOME, "500")
		.set(Person.DATE_OF_BIRTH, new Date())
		.set(Person.REGISTRATION_DATE_TIME, new Date())
		.set(Person.SLEEP_TIME, new Date())
		.set(Person.GRADUATED, "true")
		.set(Person.CERTIFICATES, Arrays.asList("Postgres", "JavaEE"))
		.where(Person.ID.equal(1))
		.executeUpdate();
 	}
	
	public void insertPersonWithConditions(PersonDTO personDTO) throws SQLException {
		
		/*
		insertInto(Table.PERSON);
		
		if(personDTO.getName() != null 
				&& !"".equals(personDTO.getName().trim())
				&& !"0".equals(personDTO.getName().trim())) {
			values(Person.NAME, personDTO.getName());
		}
		
		if(personDTO.getGender() != null) {
			values(Person.GENDER, personDTO.getGender());
		}
		
		if(personDTO.getAge() != null && personDTO.getAge() != 0) {
			values(Person.AGE, personDTO.getAge());
		}
		
		if(personDTO.getCpr() != null && personDTO.getCpr() != 0) {
			values(Person.CPR, personDTO.getCpr());
		}
		
		if(personDTO.getAccountNo() != null && personDTO.getAccountNo() != 0) {
			values(Person.ACCOUNT_NO, personDTO.getAccountNo());
		}
		
		if(personDTO.getGpa() != null && personDTO.getGpa() != 0) {
			values(Person.GPA, personDTO.getGpa());
		}
		
		if(personDTO.getSalary() != null && personDTO.getSalary() != 0) {
			values(Person.SALARY, personDTO.getSalary());
		}
		
		if(personDTO.getAnnualIncome() != null && !personDTO.getAnnualIncome().equals(new BigDecimal(0))) {
			values(Person.ANNUAL_INCOME, personDTO.getAnnualIncome());
		}
		
		if(personDTO.getDateOfBirth() != null) {
			values(Person.DATE_OF_BIRTH, personDTO.getDateOfBirth());
		}
		
		if(personDTO.getRegistrationDateTime() != null) {
			values(Person.REGISTRATION_DATE_TIME, personDTO.getRegistrationDateTime());
		}
		
		if(personDTO.getSleepTime() != null) {
			values(Person.SLEEP_TIME, personDTO.getSleepTime());
		}
		
		if(personDTO.getGraduated() != null) {
			values(Person.GRADUATED, personDTO.getGraduated());
		}
		
		executeInsert();
		*/
		
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
		.executeInsert();
		
		/*
		insertInto(Table.PERSON);
		
		if(personDTO.getName() != null 
				&& !"".equals(personDTO.getName().trim())
				&& !"0".equals(personDTO.getName().trim())) {
			values(Person.NAME, personDTO.getName());
		}else {
			String empty = null;
			values(Person.NAME, empty);
		}
		
		if(personDTO.getGender() != null) {
			values(Person.GENDER, personDTO.getGender());
		}else {
			Character empty = null;
			values(Person.GENDER, empty);
		}
		
		if(personDTO.getAge() != null && personDTO.getAge() != 0) {
			values(Person.AGE, personDTO.getAge());
		}else {
			Short empty = null;
			values(Person.AGE, empty);
		}
		
		if(personDTO.getCpr() != null && personDTO.getCpr() != 0) {
			values(Person.CPR, personDTO.getCpr());
		}else {
			Integer empty = null;
			values(Person.CPR, empty);
		}
		
		if(personDTO.getAccountNo() != null && personDTO.getAccountNo() != 0) {
			values(Person.ACCOUNT_NO, personDTO.getAccountNo());
		}else {
			Long empty = null;
			values(Person.ACCOUNT_NO, empty);
		}
		
		if(personDTO.getGpa() != null && personDTO.getGpa() != 0) {
			values(Person.GPA, personDTO.getGpa());
		}else {
			Float empty = null;
			values(Person.GPA, empty);
		}
		
		if(personDTO.getSalary() != null && personDTO.getSalary() != 0) {
			values(Person.SALARY, personDTO.getSalary());
		}else {
			Double empty = null;
			values(Person.SALARY, empty);
		}
		
		if(personDTO.getAnnualIncome() != null && !personDTO.getAnnualIncome().equals(new BigDecimal(0))) {
			values(Person.ANNUAL_INCOME, personDTO.getAnnualIncome());
		}else {
			BigDecimal empty = null;
			values(Person.ANNUAL_INCOME, empty);
		}
		
		if(personDTO.getDateOfBirth() != null) {
			values(Person.DATE_OF_BIRTH, personDTO.getDateOfBirth());
		}else {
			Date empty = null;
			values(Person.DATE_OF_BIRTH, empty);
		}
		
		if(personDTO.getRegistrationDateTime() != null) {
			values(Person.REGISTRATION_DATE_TIME, personDTO.getRegistrationDateTime());
		}else {
			Timestamp empty = null;
			values(Person.REGISTRATION_DATE_TIME, empty);
		}
		
		if(personDTO.getSleepTime() != null) {
			values(Person.SLEEP_TIME, personDTO.getSleepTime());
		}else {
			Time empty = null;
			values(Person.SLEEP_TIME, empty);
		}
		
		if(personDTO.getGraduated() != null) {
			set(Person.GRADUATED, personDTO.getGraduated());
		}else {
			Boolean empty = null;
			set(Person.GRADUATED, empty);
		}
		
		executeInsert();
		*/
		
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
		.executeInsert();
		
		/*
		insertInto(Table.PERSON);
		
		if(personDTO.getName() != null 
				&& !"".equals(personDTO.getName().trim())
				&& !"0".equals(personDTO.getName().trim())) {
			values(Person.NAME, personDTO.getName());
		}else {
			values(Person.NAME, 0);
		}
		
		if(personDTO.getAge() != null) {
			values(Person.AGE, personDTO.getAge());
		}else {
			values(Person.AGE, 0);
		}
		
		if(personDTO.getCpr() != null) {
			values(Person.CPR, personDTO.getCpr());
		}else {
			values(Person.CPR, 0);
		}
		
		if(personDTO.getAccountNo() != null) {
			values(Person.ACCOUNT_NO, personDTO.getAccountNo());
		}else {
			values(Person.ACCOUNT_NO, 0);
		}
		
		if(personDTO.getGpa() != null) {
			values(Person.GPA, personDTO.getGpa());
		}else {
			values(Person.GPA, 0);
		}
		
		if(personDTO.getSalary() != null) {
			values(Person.SALARY, personDTO.getSalary());
		}else {
			values(Person.SALARY, 0);
		}
		
		if(personDTO.getAnnualIncome() != null) {
			values(Person.ANNUAL_INCOME, personDTO.getAnnualIncome());
		}else {
			values(Person.ANNUAL_INCOME, 0);
		}
		
		executeInsert();
		*/
		
		insertInto(Table.PERSON)
		.valuesZero(Person.NAME, personDTO.getName())
		.valuesZero(Person.AGE, personDTO.getAge())
		.valuesZero(Person.CPR, personDTO.getCpr())
		.valuesZero(Person.ACCOUNT_NO, personDTO.getAccountNo())
		.valuesZero(Person.GPA, personDTO.getGpa())
		.valuesZero(Person.SALARY, personDTO.getSalary())
		.valuesZero(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
		.executeInsert();
		
		insertInto(Table.PERSON)
		.valuesExact(Person.NAME, personDTO.getName())
		.valuesExact(Person.AGE, personDTO.getAge())
		.valuesExact(Person.CPR, personDTO.getCpr())
		.valuesExact(Person.ACCOUNT_NO, personDTO.getAccountNo())
		.valuesExact(Person.GPA, personDTO.getGpa())
		.valuesExact(Person.SALARY, personDTO.getSalary())
		.valuesExact(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
		.executeInsert();
	}
	
	public void updatePersonWithConditions(PersonDTO personDTO) throws SQLException {
		
		/*
		update(Table.PERSON);
		
		if(personDTO.getName() != null 
				&& !"".equals(personDTO.getName().trim())
				&& !"0".equals(personDTO.getName().trim())) {
			set(Person.NAME, personDTO.getName());
		}
		
		if(personDTO.getGender() != null) {
			set(Person.GENDER, personDTO.getGender());
		}
		
		if(personDTO.getAge() != null && personDTO.getAge() != 0) {
			set(Person.AGE, personDTO.getAge());
		}
		
		if(personDTO.getCpr() != null && personDTO.getCpr() != 0) {
			set(Person.CPR, personDTO.getCpr());
		}
		
		if(personDTO.getAccountNo() != null && personDTO.getAccountNo() != 0) {
			set(Person.ACCOUNT_NO, personDTO.getAccountNo());
		}
		
		if(personDTO.getGpa() != null && personDTO.getGpa() != 0) {
			set(Person.GPA, personDTO.getGpa());
		}
		
		if(personDTO.getSalary() != null && personDTO.getSalary() != 0) {
			set(Person.SALARY, personDTO.getSalary());
		}
		
		if(personDTO.getAnnualIncome() != null  && !personDTO.getAnnualIncome().equals(new BigDecimal(0))) {
			set(Person.ANNUAL_INCOME, personDTO.getAnnualIncome());
		}
		
		if(personDTO.getDateOfBirth() != null) {
			set(Person.DATE_OF_BIRTH, personDTO.getDateOfBirth());
		}
		
		if(personDTO.getRegistrationDateTime() != null) {
			set(Person.REGISTRATION_DATE_TIME, personDTO.getRegistrationDateTime());
		}
		
		if(personDTO.getSleepTime() != null) {
			set(Person.SLEEP_TIME, personDTO.getSleepTime());
		}
		
		if(personDTO.getGraduated() != null) {
			set(Person.GRADUATED, personDTO.getGraduated());
		}
		
		executeUpdate();
		*/
		
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
		.where(Person.ID.equal(personDTO.getId()))
			.executeUpdate();
		
		/*
		update(Table.PERSON);
		
		if(personDTO.getName() != null 
				&& !"".equals(personDTO.getName().trim())
				&& !"0".equals(personDTO.getName().trim())) {
			set(Person.NAME, personDTO.getName());
		}else {
			String empty = null;
			set(Person.NAME, empty);
		}
		
		if(personDTO.getGender() != null) {
			set(Person.GENDER, personDTO.getGender());
		}else {
			Character empty = null;
			set(Person.GENDER, empty);
		}
		
		if(personDTO.getAge() != null && personDTO.getAge() != 0) {
			set(Person.AGE, personDTO.getAge());
		}else {
			Short empty = null;
			set(Person.AGE, empty);
		}
		
		if(personDTO.getCpr() != null && personDTO.getCpr() != 0) {
			set(Person.CPR, personDTO.getCpr());
		}else {
			Integer empty = null;
			set(Person.CPR, empty);
		}
		
		if(personDTO.getAccountNo() != null && personDTO.getAccountNo() != 0) {
			set(Person.ACCOUNT_NO, personDTO.getAccountNo());
		}else {
			Long empty = null;
			set(Person.ACCOUNT_NO, empty);
		}
		
		if(personDTO.getGpa() != null && personDTO.getGpa() != 0) {
			set(Person.GPA, personDTO.getGpa());
		}else {
			Float empty = null;
			set(Person.GPA, empty);
		}
		
		if(personDTO.getSalary() != null && personDTO.getSalary() != 0) {
			set(Person.SALARY, personDTO.getSalary());
		}else {
			Double empty = null;
			set(Person.SALARY, empty);
		}
		
		if(personDTO.getAnnualIncome() != null && !personDTO.getAnnualIncome().equals(new BigDecimal(0))) {
			set(Person.ANNUAL_INCOME, personDTO.getAnnualIncome());
		}else {
			BigDecimal empty = null;
			set(Person.ANNUAL_INCOME, empty);
		}
		
		if(personDTO.getDateOfBirth() != null) {
			set(Person.DATE_OF_BIRTH, personDTO.getDateOfBirth());
		}else {
			Date empty = null;
			set(Person.DATE_OF_BIRTH, empty);
		}
		
		if(personDTO.getRegistrationDateTime() != null) {
			set(Person.REGISTRATION_DATE_TIME, personDTO.getRegistrationDateTime());
		}else {
			Timestamp empty = null;
			set(Person.REGISTRATION_DATE_TIME, empty);
		}
		
		if(personDTO.getSleepTime() != null) {
			set(Person.SLEEP_TIME, personDTO.getSleepTime());
		}else {
			Time empty = null;
			set(Person.SLEEP_TIME, empty);
		}
		
		if(personDTO.getGraduated() != null) {
			set(Person.GRADUATED, personDTO.getGraduated());
		}else {
			Boolean empty = null;
			set(Person.GRADUATED, empty);
		}
		
		where(Person.ID.equal(personDTO.getId()));
		executeUpdate();
		*/
		
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
		.where(Person.ID.equal(personDTO.getId()))
			.executeUpdate();
		
		/*
		update(Table.PERSON);
		
		if(personDTO.getName() != null 
				&& !"".equals(personDTO.getName().trim())
				&& !"0".equals(personDTO.getName().trim())) {
			set(Person.NAME, personDTO.getName());
		}else {
			set(Person.NAME, 0);
		}
		
		if(personDTO.getAge() != null) {
			set(Person.AGE, personDTO.getAge());
		}else {
			set(Person.AGE, 0);
		}
		
		if(personDTO.getCpr() != null) {
			set(Person.CPR, personDTO.getCpr());
		}else {
			set(Person.CPR, 0);
		}
		
		if(personDTO.getAccountNo() != null) {
			set(Person.ACCOUNT_NO, personDTO.getAccountNo());
		}else {
			set(Person.ACCOUNT_NO, 0);
		}
		
		if(personDTO.getGpa() != null) {
			set(Person.GPA, personDTO.getGpa());
		}else {
			set(Person.GPA, 0);
		}
		
		if(personDTO.getSalary() != null) {
			set(Person.SALARY, personDTO.getSalary());
		}else {
			set(Person.SALARY, 0);
		}
		
		if(personDTO.getAnnualIncome() != null) {
			set(Person.ANNUAL_INCOME, personDTO.getAnnualIncome());
		}else {
			set(Person.ANNUAL_INCOME, 0);
		}
		
		set(Person.GRADUATED, personDTO.getGraduated());
		where(Person.ID.equal(personDTO.getId()));
		executeUpdate();
		*/
		
		update(Table.PERSON)
		.setZero(Person.NAME, personDTO.getName())
		.setZero(Person.AGE, personDTO.getAge())
		.setZero(Person.CPR, personDTO.getCpr())
		.setZero(Person.ACCOUNT_NO, personDTO.getAccountNo())
		.setZero(Person.GPA, personDTO.getGpa())
		.setZero(Person.SALARY, personDTO.getSalary())
		.setZero(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
		.where(Person.ID.equal(personDTO.getId()))
			.executeUpdate();
		
		update(Table.PERSON)
		.setExact(Person.NAME, personDTO.getName())
		.setExact(Person.AGE, personDTO.getAge())
		.setExact(Person.CPR, personDTO.getCpr())
		.setExact(Person.ACCOUNT_NO, personDTO.getAccountNo())
		.setExact(Person.GPA, personDTO.getGpa())
		.setExact(Person.SALARY, personDTO.getSalary())
		.setExact(Person.ANNUAL_INCOME, personDTO.getAnnualIncome())
		.where(Person.ID.equal(personDTO.getId()))
			.executeUpdate();
	}
	
	public void selectPersonWithConditions(PersonDTO personDTO) throws SQLException {
		
		/*
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE);
		select(Person.CPR, Person.ACCOUNT_NO, Person.GPA);
		select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH);
		select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME, Person.GRADUATED);
		from(Table.PERSON);
		
		if(personDTO.getId() != null && personDTO.getId() != 0) {
			where(Person.ID.equal(personDTO.getId()));
		}
		
		if(personDTO.getName() != null 
				&& !"".equals(personDTO.getName().trim())
				&& !"0".equals(personDTO.getName().trim())) {
			where(Person.NAME.like(personDTO.getName()));
		}
		
		if(personDTO.getGender() != null) {
			where(Person.GENDER.equal(personDTO.getGender()));
		}
		
		if(personDTO.getAge() != null && personDTO.getAge() != 0) {
			where(Person.AGE.equal(personDTO.getAge()));
		}
		
		if(personDTO.getCpr() != null && personDTO.getCpr() != 0) {
			where(Person.CPR.equal(personDTO.getCpr()));
		}
		
		if(personDTO.getAccountNo() != null && personDTO.getAccountNo() != 0) {
			where(Person.ACCOUNT_NO.equal(personDTO.getAccountNo()));
		}
		
		if(personDTO.getGpa() != null && personDTO.getGpa() != 0) {
			where(Person.GPA.equal(personDTO.getGpa()));
		}
		
		if(personDTO.getSalary() != null && personDTO.getSalary() != 0) {
			where(Person.SALARY.equal(personDTO.getSalary()));
		}
		
		if(personDTO.getAnnualIncome() != null && !personDTO.getAnnualIncome().equals(new BigDecimal(0))) {
			where(Person.ANNUAL_INCOME.equal(personDTO.getAnnualIncome()));
		}
		
		if(personDTO.getDateOfBirth() != null) {
			where(Person.DATE_OF_BIRTH.equal(personDTO.getDateOfBirth()));
		}
		
		if(personDTO.getRegistrationDateTime() != null) {
			where(Person.REGISTRATION_DATE_TIME.equal(personDTO.getRegistrationDateTime()));
		}
		
		if(personDTO.getSleepTime() != null) {
			where(Person.SLEEP_TIME.equal(personDTO.getSleepTime()));
		}
		
		if(personDTO.getGraduated() != null) {
			where(Person.GRADUATED.equal(personDTO.getGraduated()));
		}
		
		executeSelect();
		*/
		
		select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME, Person.GRADUATED)
		.from(Table.PERSON)
		.search(Person.ID.equal(personDTO.getId()))
		.search(Person.NAME.like(personDTO.getName()))
		.search(Person.GENDER.equal(personDTO.getGender()))
		.search(Person.AGE.equal(personDTO.getAge()))
		.search(Person.CPR.equal(personDTO.getCpr()))
		.search(Person.ACCOUNT_NO.equal(personDTO.getAccountNo()))
		.search(Person.GPA.equal(personDTO.getGpa()))
		.search(Person.SALARY.equal(personDTO.getSalary()))
		.search(Person.ANNUAL_INCOME.equal(personDTO.getAnnualIncome()))
		.search(Person.DATE_OF_BIRTH.equal(personDTO.getDateOfBirth()))
		.search(Person.REGISTRATION_DATE_TIME.equal(personDTO.getRegistrationDateTime()))
		.search(Person.SLEEP_TIME.equal(personDTO.getSleepTime()))
		.search(Person.GRADUATED.equal(personDTO.getGraduated()))
		.executeSelect();
	}
	
	public void insertPersonWithColumn() throws SQLException {
		
		insertInto(Table.PERSON)
		.values(Person.DATE_OF_BIRTH, CURRENT_DATE.plusDays(1).minusMonths(2))
		.values(Person.REGISTRATION_DATE_TIME, CURRENT_TIMESTAMP.minusHours(1).plusYears(2))
		.values(Person.SLEEP_TIME, CURRENT_TIME.plusMinutes(1).minusHours(2))
		.executeInsert();
	}
	
	public void updatePersonWithColumn() throws SQLException {
		
		update(Table.PERSON)
		.set(Person.AGE, Person.AGE.plus(1))
		.set(Person.CPR, Person.CPR.minus(2))
		.set(Person.ACCOUNT_NO, Person.ACCOUNT_NO.multiply(3))
		.set(Person.GPA, Person.GPA.divide(4))
		.set(Person.SALARY, Person.SALARY.multiply(Person.SALARY))
		.set(Person.ANNUAL_INCOME, Person.ANNUAL_INCOME.plus(Person.ANNUAL_INCOME))
		.set(Person.DATE_OF_BIRTH, CURRENT_DATE.plusDays(1).minusMonths(2))
		.set(Person.REGISTRATION_DATE_TIME, CURRENT_TIMESTAMP.minusHours(1).plusYears(2))
		.set(Person.SLEEP_TIME, CURRENT_TIME.plusMinutes(1).minusHours(2))
		.where(Person.ID.equal(1))
		.executeUpdate();
	}
	
	public void selectPersonWithResultSetToCollection() throws Exception {
		
		System.out.println("Delete all records");
		
		deleteFrom(Table.PERSON).executeDelete();
		
		System.out.println("Check Existance");
		
		System.out.println(select(_1).from(Table.PERSON).isExist());
		
		System.out.println("Insert Person");
		
		int id = insertInto(Table.PERSON)
		.values(Person.NAME, "Hasan")
		.values(Person.GENDER, 'M')
		.values(Person.AGE, 33)
		.values(Person.CPR, 870501236)
		.values(Person.ACCOUNT_NO, 12345678901234l)
		.values(Person.GPA, 3.12)
		.values(Person.SALARY, 400.5)
		.values(Person.ANNUAL_INCOME, new BigDecimal("500.40"))
		.values(Person.DATE_OF_BIRTH, java.sql.Date.valueOf("2015-10-10"))
		.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2000-10-10 10:10:10"))
		.values(Person.SLEEP_TIME, Time.valueOf("10:10:10"))
		.values(Person.CERTIFICATES, "DB2", "Java")
		.executeInsert();
		
		System.out.println("Check Existance");
		
		System.out.println(select(_1).from(Table.PERSON).where(Person.ID.equal(id)).isExist());
		
		System.out.println("Read All Columns as String Result");
		
		System.out.println(select(Person.NAME).from(Table.PERSON).where(Person.ID.equal(id)).getStringResult());
		System.out.println(select(Person.GENDER).from(Table.PERSON).where(Person.ID.equal(id)).getStringResult());
		System.out.println(select(Person.AGE).from(Table.PERSON).where(Person.ID.equal(id)).getStringResult());
		System.out.println(select(Person.CPR).from(Table.PERSON).where(Person.ID.equal(id)).getStringResult());
		System.out.println(select(Person.ACCOUNT_NO).from(Table.PERSON).where(Person.ID.equal(id)).getStringResult());
		System.out.println(select(Person.GPA).from(Table.PERSON).where(Person.ID.equal(id)).getStringResult());
		System.out.println(select(Person.SALARY).from(Table.PERSON).where(Person.ID.equal(id)).getStringResult());
		System.out.println(select(Person.ANNUAL_INCOME).from(Table.PERSON).where(Person.ID.equal(id)).getStringResult());
		System.out.println(select(Person.DATE_OF_BIRTH).from(Table.PERSON).where(Person.ID.equal(id)).getStringResult());
		System.out.println(select(Person.REGISTRATION_DATE_TIME).from(Table.PERSON).where(Person.ID.equal(id)).getStringResult());
		System.out.println(select(Person.SLEEP_TIME).from(Table.PERSON).where(Person.ID.equal(id)).getStringResult());
		System.out.println(select(Person.GRADUATED).from(Table.PERSON).where(Person.ID.equal(id)).getStringResult());
		
		System.out.println("Read All Columns as Single Result");
		
		System.out.println(select(Person.NAME).from(Table.PERSON).where(Person.ID.equal(id)).getStringResult());
		System.out.println(select(Person.GENDER).from(Table.PERSON).where(Person.ID.equal(id)).getCharacterResult());
		System.out.println(select(Person.AGE).from(Table.PERSON).where(Person.ID.equal(id)).getShortResult());
		System.out.println(select(Person.CPR).from(Table.PERSON).where(Person.ID.equal(id)).getIntegerResult());
		System.out.println(select(Person.ACCOUNT_NO).from(Table.PERSON).where(Person.ID.equal(id)).getLongResult());
		System.out.println(select(Person.GPA).from(Table.PERSON).where(Person.ID.equal(id)).getFloatResult());
		System.out.println(select(Person.SALARY).from(Table.PERSON).where(Person.ID.equal(id)).getDoubleResult());
		System.out.println(select(Person.ANNUAL_INCOME).from(Table.PERSON).where(Person.ID.equal(id)).getDecimalResult());
		System.out.println(select(Person.DATE_OF_BIRTH).from(Table.PERSON).where(Person.ID.equal(id)).getDateResult());
		System.out.println(select(Person.REGISTRATION_DATE_TIME).from(Table.PERSON).where(Person.ID.equal(id)).getTimestampResult());
		System.out.println(select(Person.SLEEP_TIME).from(Table.PERSON).where(Person.ID.equal(id)).getTimeResult());
		System.out.println(select(Person.GRADUATED).from(Table.PERSON).where(Person.ID.equal(id)).getBooleanResult());
		System.out.println(select(Person.CERTIFICATES).from(Table.PERSON).where(Person.ID.equal(id)).getArrayResult());
		
		System.out.println("Insert Another Person");
		
		insertInto(Table.PERSON)
		.values(Person.NAME, "Fatima")
		.values(Person.GENDER, 'F')
		.values(Person.AGE, 20)
		.values(Person.CPR, 910102654)
		.values(Person.ACCOUNT_NO, 9876543219875L)
		.values(Person.GPA, 3.8)
		.values(Person.SALARY, 100.5)
		.values(Person.ANNUAL_INCOME, new BigDecimal("700.45"))
		.values(Person.DATE_OF_BIRTH, java.sql.Date.valueOf("2008-08-08"))
		.values(Person.REGISTRATION_DATE_TIME, Timestamp.valueOf("2010-05-05 06:06:06"))
		.values(Person.SLEEP_TIME, Time.valueOf("03:03:03"))
		.values(Person.GRADUATED, false)
		.values(Person.CERTIFICATES, "Postgres", "JavaEE")
		.executeInsert();
		
		System.out.println("Read All Columns as String List");
		
		System.out.println(select(Person.NAME).from(Table.PERSON).getStringList());
		System.out.println(select(Person.GENDER).from(Table.PERSON).getStringList());
		System.out.println(select(Person.AGE).from(Table.PERSON).getStringList());
		System.out.println(select(Person.CPR).from(Table.PERSON).getStringList());
		System.out.println(select(Person.ACCOUNT_NO).from(Table.PERSON).getStringList());
		System.out.println(select(Person.GPA).from(Table.PERSON).getStringList());
		System.out.println(select(Person.SALARY).from(Table.PERSON).getStringList());
		System.out.println(select(Person.ANNUAL_INCOME).from(Table.PERSON).getStringList());
		System.out.println(select(Person.DATE_OF_BIRTH).from(Table.PERSON).getStringList());
		System.out.println(select(Person.REGISTRATION_DATE_TIME).from(Table.PERSON).getStringList());
		System.out.println(select(Person.SLEEP_TIME).from(Table.PERSON).getStringList());
		System.out.println(select(Person.GRADUATED).from(Table.PERSON).getStringList());
		System.out.println(select(Person.CERTIFICATES).from(Table.PERSON).getStringList());
		
		System.out.println("Read All Columns as List");
		
		System.out.println(select(Person.NAME).from(Table.PERSON).getStringList());
		System.out.println(select(Person.GENDER).from(Table.PERSON).getCharacterList());
		System.out.println(select(Person.AGE).from(Table.PERSON).getShortList());
		System.out.println(select(Person.CPR).from(Table.PERSON).getIntegerList());
		System.out.println(select(Person.ACCOUNT_NO).from(Table.PERSON).getLongList());
		System.out.println(select(Person.GPA).from(Table.PERSON).getFloatList());
		System.out.println(select(Person.SALARY).from(Table.PERSON).getDoubleList());
		System.out.println(select(Person.ANNUAL_INCOME).from(Table.PERSON).getDecimalList());
		System.out.println(select(Person.DATE_OF_BIRTH).from(Table.PERSON).getDateList());
		System.out.println(select(Person.REGISTRATION_DATE_TIME).from(Table.PERSON).getTimestampList());
		System.out.println(select(Person.SLEEP_TIME).from(Table.PERSON).getTimeList());
		System.out.println(select(Person.GRADUATED).from(Table.PERSON).getBooleanList());
		System.out.println(select(Person.CERTIFICATES).from(Table.PERSON).getArrayList());
		
		System.out.println("Use get entry result");
		
		System.out.println(select(Person.ID, Person.NAME).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult());
		System.out.println(select(Person.ID, Person.GENDER).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult());
		System.out.println(select(Person.ID, Person.AGE).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult());
		System.out.println(select(Person.ID, Person.CPR).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult());
		System.out.println(select(Person.ID, Person.ACCOUNT_NO).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult());
		System.out.println(select(Person.ID, Person.GPA).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult());
		System.out.println(select(Person.ID, Person.SALARY).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult());
		System.out.println(select(Person.ID, Person.ANNUAL_INCOME).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult());
		System.out.println(select(Person.ID, Person.DATE_OF_BIRTH).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult());
		System.out.println(select(Person.ID, Person.REGISTRATION_DATE_TIME).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult());
		System.out.println(select(Person.ID, Person.SLEEP_TIME).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult());
		System.out.println(select(Person.ID, Person.GRADUATED).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult());
		System.out.println(select(Person.ID, Person.CERTIFICATES).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult());
		
		System.out.println("Use get entry list result");
		
		System.out.println(select(Person.ID, Person.NAME).from(Table.PERSON).getEntryList());
		System.out.println(select(Person.ID, Person.GENDER).from(Table.PERSON).getEntryList());
		System.out.println(select(Person.ID, Person.AGE).from(Table.PERSON).getEntryList());
		System.out.println(select(Person.ID, Person.CPR).from(Table.PERSON).getEntryList());
		System.out.println(select(Person.ID, Person.ACCOUNT_NO).from(Table.PERSON).getEntryList());
		System.out.println(select(Person.ID, Person.GPA).from(Table.PERSON).getEntryList());
		System.out.println(select(Person.ID, Person.SALARY).from(Table.PERSON).getEntryList());
		System.out.println(select(Person.ID, Person.ANNUAL_INCOME).from(Table.PERSON).getEntryList());
		System.out.println(select(Person.ID, Person.DATE_OF_BIRTH).from(Table.PERSON).getEntryList());
		System.out.println(select(Person.ID, Person.REGISTRATION_DATE_TIME).from(Table.PERSON).getEntryList());
		System.out.println(select(Person.ID, Person.SLEEP_TIME).from(Table.PERSON).getEntryList());
		System.out.println(select(Person.ID, Person.GRADUATED).from(Table.PERSON).getEntryList());
		System.out.println(select(Person.ID, Person.CERTIFICATES).from(Table.PERSON).getEntryList());
		
		System.out.println("Use get map result");
		
		System.out.println(select(Person.ID, Person.NAME).from(Table.PERSON).getMapResult());
		System.out.println(select(Person.ID, Person.GENDER).from(Table.PERSON).getMapResult());
		System.out.println(select(Person.ID, Person.AGE).from(Table.PERSON).getMapResult());
		System.out.println(select(Person.ID, Person.CPR).from(Table.PERSON).getMapResult());
		System.out.println(select(Person.ID, Person.ACCOUNT_NO).from(Table.PERSON).getMapResult());
		System.out.println(select(Person.ID, Person.GPA).from(Table.PERSON).getMapResult());
		System.out.println(select(Person.ID, Person.SALARY).from(Table.PERSON).getMapResult());
		System.out.println(select(Person.ID, Person.ANNUAL_INCOME).from(Table.PERSON).getMapResult());
		System.out.println(select(Person.ID, Person.DATE_OF_BIRTH).from(Table.PERSON).getMapResult());
		System.out.println(select(Person.ID, Person.REGISTRATION_DATE_TIME).from(Table.PERSON).getMapResult());
		System.out.println(select(Person.ID, Person.SLEEP_TIME).from(Table.PERSON).getMapResult());
		System.out.println(select(Person.ID, Person.GRADUATED).from(Table.PERSON).getMapResult());
		System.out.println(select(Person.ID, Person.CERTIFICATES).from(Table.PERSON).getMapResult());
		
		System.out.println("Use get entry result with string");
		
		System.out.println(select(Person.ID, Person.NAME).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.GENDER).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.AGE).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.CPR).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.ACCOUNT_NO).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.GPA).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.SALARY).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.ANNUAL_INCOME).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.REGISTRATION_DATE_TIME).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.DATE_OF_BIRTH).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.REGISTRATION_DATE_TIME).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.SLEEP_TIME).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.GRADUATED).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.CERTIFICATES).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, String.class));
		
		System.out.println("Use get entry result with type");
		
		System.out.println(select(Person.ID, Person.NAME).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.GENDER).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, Character.class));
		System.out.println(select(Person.ID, Person.AGE).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, Short.class));
		System.out.println(select(Person.ID, Person.CPR).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, Integer.class));
		System.out.println(select(Person.ID, Person.ACCOUNT_NO).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, Long.class));
		System.out.println(select(Person.ID, Person.GPA).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, Float.class));
		System.out.println(select(Person.ID, Person.SALARY).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, Double.class));
		System.out.println(select(Person.ID, Person.ANNUAL_INCOME).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, BigDecimal.class));
		System.out.println(select(Person.ID, Person.REGISTRATION_DATE_TIME).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, Date.class));
		System.out.println(select(Person.ID, Person.DATE_OF_BIRTH).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, java.sql.Date.class));
		System.out.println(select(Person.ID, Person.REGISTRATION_DATE_TIME).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, Timestamp.class));
		System.out.println(select(Person.ID, Person.SLEEP_TIME).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, Time.class));
		System.out.println(select(Person.ID, Person.GRADUATED).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, Boolean.class));
		System.out.println(select(Person.ID, Person.CERTIFICATES).from(Table.PERSON).where(Person.ID.equal(id)).getEntryResult(Integer.class, List.class));
		
		System.out.println("Use get entry list with string");
		
		System.out.println(select(Person.ID, Person.NAME).from(Table.PERSON).getEntryList(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.GENDER).from(Table.PERSON).getEntryList(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.AGE).from(Table.PERSON).getEntryList(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.CPR).from(Table.PERSON).getEntryList(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.ACCOUNT_NO).from(Table.PERSON).getEntryList(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.GPA).from(Table.PERSON).getEntryList(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.SALARY).from(Table.PERSON).getEntryList(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.ANNUAL_INCOME).from(Table.PERSON).getEntryList(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.REGISTRATION_DATE_TIME).from(Table.PERSON).getEntryList(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.DATE_OF_BIRTH).from(Table.PERSON).getEntryList(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.REGISTRATION_DATE_TIME).from(Table.PERSON).getEntryList(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.SLEEP_TIME).from(Table.PERSON).getEntryList(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.GRADUATED).from(Table.PERSON).getEntryList(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.CERTIFICATES).from(Table.PERSON).getEntryList(Integer.class, String.class));
		
		System.out.println("Use get entry list with type");
		
		System.out.println(select(Person.ID, Person.NAME).from(Table.PERSON).getEntryList(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.GENDER).from(Table.PERSON).getEntryList(Integer.class, Character.class));
		System.out.println(select(Person.ID, Person.AGE).from(Table.PERSON).getEntryList(Integer.class, Short.class));
		System.out.println(select(Person.ID, Person.CPR).from(Table.PERSON).getEntryList(Integer.class, Integer.class));
		System.out.println(select(Person.ID, Person.ACCOUNT_NO).from(Table.PERSON).getEntryList(Integer.class, Long.class));
		System.out.println(select(Person.ID, Person.GPA).from(Table.PERSON).getEntryList(Integer.class, Float.class));
		System.out.println(select(Person.ID, Person.SALARY).from(Table.PERSON).getEntryList(Integer.class, Double.class));
		System.out.println(select(Person.ID, Person.ANNUAL_INCOME).from(Table.PERSON).getEntryList(Integer.class, BigDecimal.class));
		System.out.println(select(Person.ID, Person.REGISTRATION_DATE_TIME).from(Table.PERSON).getEntryList(Integer.class, Date.class));
		System.out.println(select(Person.ID, Person.DATE_OF_BIRTH).from(Table.PERSON).getEntryList(Integer.class, java.sql.Date.class));
		System.out.println(select(Person.ID, Person.REGISTRATION_DATE_TIME).from(Table.PERSON).getEntryList(Integer.class, Timestamp.class));
		System.out.println(select(Person.ID, Person.SLEEP_TIME).from(Table.PERSON).getEntryList(Integer.class, Time.class));
		System.out.println(select(Person.ID, Person.GRADUATED).from(Table.PERSON).getEntryList(Integer.class, Boolean.class));
		System.out.println(select(Person.ID, Person.CERTIFICATES).from(Table.PERSON).getEntryList(Integer.class, List.class));
		
		System.out.println("Use get map result with string");
		
		System.out.println(select(Person.ID, Person.NAME).from(Table.PERSON).getMapResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.GENDER).from(Table.PERSON).getMapResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.AGE).from(Table.PERSON).getMapResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.CPR).from(Table.PERSON).getMapResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.ACCOUNT_NO).from(Table.PERSON).getMapResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.GPA).from(Table.PERSON).getMapResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.SALARY).from(Table.PERSON).getMapResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.ANNUAL_INCOME).from(Table.PERSON).getMapResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.REGISTRATION_DATE_TIME).from(Table.PERSON).getMapResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.DATE_OF_BIRTH).from(Table.PERSON).getMapResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.REGISTRATION_DATE_TIME).from(Table.PERSON).getMapResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.SLEEP_TIME).from(Table.PERSON).getMapResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.GRADUATED).from(Table.PERSON).getMapResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.CERTIFICATES).from(Table.PERSON).getMapResult(Integer.class, String.class));
		
		System.out.println("Use get map result with type");
		
		System.out.println(select(Person.ID, Person.NAME).from(Table.PERSON).getMapResult(Integer.class, String.class));
		System.out.println(select(Person.ID, Person.GENDER).from(Table.PERSON).getMapResult(Integer.class, Character.class));
		System.out.println(select(Person.ID, Person.AGE).from(Table.PERSON).getMapResult(Integer.class, Short.class));
		System.out.println(select(Person.ID, Person.CPR).from(Table.PERSON).getMapResult(Integer.class, Integer.class));
		System.out.println(select(Person.ID, Person.ACCOUNT_NO).from(Table.PERSON).getMapResult(Integer.class, Long.class));
		System.out.println(select(Person.ID, Person.GPA).from(Table.PERSON).getMapResult(Integer.class, Float.class));
		System.out.println(select(Person.ID, Person.SALARY).from(Table.PERSON).getMapResult(Integer.class, Double.class));
		System.out.println(select(Person.ID, Person.ANNUAL_INCOME).from(Table.PERSON).getMapResult(Integer.class, BigDecimal.class));
		System.out.println(select(Person.ID, Person.REGISTRATION_DATE_TIME).from(Table.PERSON).getMapResult(Integer.class, Date.class));
		System.out.println(select(Person.ID, Person.DATE_OF_BIRTH).from(Table.PERSON).getMapResult(Integer.class, java.sql.Date.class));
		System.out.println(select(Person.ID, Person.REGISTRATION_DATE_TIME).from(Table.PERSON).getMapResult(Integer.class, Timestamp.class));
		System.out.println(select(Person.ID, Person.SLEEP_TIME).from(Table.PERSON).getMapResult(Integer.class, Time.class));
		System.out.println(select(Person.ID, Person.GRADUATED).from(Table.PERSON).getMapResult(Integer.class, Boolean.class));
		System.out.println(select(Person.ID, Person.CERTIFICATES).from(Table.PERSON).getMapResult(Integer.class, List.class));
		
		System.out.println(select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
				.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
				.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
				.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME, Person.GRADUATED)
				.from(Table.PERSON).where(Person.ID.equal(id)).getRecord());
		
		System.out.println(select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
		.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
		.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
		.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME, Person.GRADUATED, Person.CERTIFICATES)
		.from(Table.PERSON).getRecordList());
		
		System.out.println(select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
				.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
				.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
				.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME, Person.GRADUATED, Person.CERTIFICATES)
				.from(Table.PERSON).getRecordMap());
	}
	
	public void selectPersonWithResultSetToClassObject() throws Exception {
		
		/*
		System.out.println(select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
				.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
				.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
				.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME, Person.GRADUATED, Person.CERTIFICATES)
				.from(Table.PERSON).where(Person.ID.equal(1)).getRecord(PersonDTO.class));
		
		PersonDTO personDTO = new PersonDTO();
		
		System.out.println(select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
				.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
				.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
				.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME, Person.GRADUATED, Person.CERTIFICATES)
				.from(Table.PERSON).where(Person.ID.equal(1)).getRecord(personDTO));
		
		System.out.println(select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
				.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
				.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
				.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME, Person.GRADUATED, Person.CERTIFICATES)
				.from(Table.PERSON).getRecordList(PersonDTO.class));
		
		System.out.println(select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
				.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
				.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
				.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME, Person.GRADUATED, Person.CERTIFICATES)
				.from(Table.PERSON).getRecordMap(PersonDTO.class));
		
		System.out.println(select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
				.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
				.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
				.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME, Person.GRADUATED, Person.CERTIFICATES)
				.from(Table.PERSON).where(Person.ID.equal(1)).getRecord(PersonStringDTO.class));
		
		System.out.println(select(Person.ID, Person.NAME, Person.GENDER, Person.AGE)
				.select(Person.CPR, Person.ACCOUNT_NO, Person.GPA)
				.select(Person.SALARY, Person.ANNUAL_INCOME, Person.DATE_OF_BIRTH)
				.select(Person.REGISTRATION_DATE_TIME, Person.SLEEP_TIME, Person.GRADUATED, Person.CERTIFICATES)
				.from(Table.PERSON).where(Person.ID.equal(1)).getRecord(PersonPrimitiveDateDTO.class));
		
		System.out.println(select(Person.ID, Person.NAME)
				.select(Person.CITY_ID, City.ID, City.NAME)
				.select(Person.COMPANY_ID, Company.ID, Company.NAME)
				.select(Person.COUNTRY_ID, Country.ID, Country.NAME)
				.select(Person.SCHOOL_ID, School.ID, School.NAME)
				.from(Table.PERSON)
				.join(Table.CITY).on(Person.CITY_ID.equal(City.ID))
				.join(Table.COMPANY).on(Person.COMPANY_ID.equal(Company.ID))
				.join(Table.COUNTRY).on(Person.COUNTRY_ID.equal(Country.ID))
				.join(Table.SCHOOL).on(Person.SCHOOL_ID.equal(School.ID))
				.where(Person.ID.equal(1))
				.getRecord(PersonDTO.class));

		System.out.println(select(Person.NAME, Person.GENDER, Person.AGE)
				.select(Address.ID, Address.BUILDING, 
						Address.ROAD, Address.BLOCK)
				.from(Table.PERSON)
				.join(Table.ADDRESS)
					.on(Person.HOME_ADDRESS_ID.equal(Address.ID))
				.where(Person.ID.equal(1))
				.getRecord(PersonDTO.class));
		*/
		
		System.out.println(select(Person.NAME, Person.GENDER, Person.AGE)
				.select(Address.ROAD.of("home_address").as("home_address_road")
						, Address.BLOCK.of("home_address"))
				.select(Address.ROAD.of("work_address"), Address.BLOCK.of("work_address"))
				.from(Table.PERSON.as("person"))
				.join(Table.ADDRESS.as("home_address"))
					.on(Person.HOME_ADDRESS_ID.equal(Address.ID.of("home_address")))
				.join(Table.ADDRESS.as("work_address"))
					.on(Person.WORK_ADDRESS_ID.equal(Address.ID.of("work_address")))
				.where(Person.ID.equal(1))
				.getRecord(PersonDTO.class));
	}
}
