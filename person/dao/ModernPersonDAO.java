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
}
