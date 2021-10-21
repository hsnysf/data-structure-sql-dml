package person.dao;

import java.sql.Connection;
import java.sql.SQLException;

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
		.where(Person.ID.equal(personDTO.getId()))
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
		
		select(coalesce(Person.DATE_OF_BIRTH, "12-12-2020"))
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
		.select(row_number()
				.partitionBy(Person.AGE, Person.GRADUATED)
				.orderBy(Person.AGE.asc(), Person.CPR.desc()))
		.from(Table.PERSON)
		.executeSelect();
		
		select(Person.NAME, Person.AGE)
		.from(new Query()
				.select(Person.NAME, Person.AGE)
				.select(row_number()
						.partitionBy(Person.AGE)
						.orderBy(Person.GPA.desc())
						.as("person_row_number"))
				.from(Table.PERSON)
			  ).as("person")
		.where(row_number().alias("person_row_number").equal(1))
		.executeSelect();
	}
}
