package person.dao;

import java.sql.Connection;
import java.sql.SQLException;

import person.column.Person;
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
}
