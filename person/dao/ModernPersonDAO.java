package person.dao;

import java.sql.Connection;
import java.sql.SQLException;

import person.column.Doctor;
import person.column.Person;
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
		.and(Person.AGE.equal(12).and(Person.SALARY.greater(100)))
		.and(Person.CPR.equal(8811111).and(Person.NAME.like("Ali")
											.or(Person.NAME.like("Mohd"))))
		.executeSelect();
	}
}
