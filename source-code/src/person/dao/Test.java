package person.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import person.dto.PersonDTO;
import person.generator.GeneratorUtils;

public class Test {

	public static void main(String[] args) throws Exception {
		
		Connection connection = GeneratorUtils.getConnection();
		
		ModernPersonDAO personDAO = new ModernPersonDAO(connection);
		
		PersonDTO personDTO = new PersonDTO();
		personDTO.setName("Hasan Yusuf");
		personDTO.setGender('M');
		personDTO.setAge((short) 35);
		personDTO.setCpr(40100162);
		personDTO.setAccountNo(12345678912345l);
		personDTO.setGpa(3.14f);
		personDTO.setSalary(555.45);
		personDTO.setAnnualIncome(new BigDecimal(9000.5));
		personDTO.setDateOfBirth(Date.valueOf("1988-01-27"));
		personDTO.setRegistrationDateTime(Timestamp.valueOf("2000-10-10 04:04:04"));
		personDTO.setSleepTime(Time.valueOf("05:05:05"));
		personDTO.setGraduated(true);
		
		personDAO.insertPerson(personDTO);
		
		personDAO.getPerson(personDTO);
		
		personDAO.updatePerson(personDTO);
		
		personDAO.deletePerson(personDTO);
		
		personDAO.getPerson(personDTO);
	}
}
