package person.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import person.dto.PersonDTO;

public class ClassicPersonDAO {

	private Connection connection;
	
	public ClassicPersonDAO(Connection connection) {
		this.connection = connection;
	}
	
	public PersonDTO insertPerson(PersonDTO personDTO) throws SQLException {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("insert into public.person");
		builder.append("(prsn_name, prsn_gender,"); 
		builder.append("prsn_age, prsn_cpr, prsn_account_no,"); 
		builder.append("prsn_gpa, prsn_salary, prsn_annual_income,"); 
		builder.append("prsn_date_of_birth, prsn_registration_date_time,");
		builder.append("prsn_sleep_time, prsn_graduated)");
		builder.append("values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		try(PreparedStatement statement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS)){
			
			if(personDTO.getName() != null && !"".equals(personDTO.getName())) {
				statement.setString(1, personDTO.getName());
			}else {
				statement.setNull(1, Types.VARCHAR);
			}
			
			if(personDTO.getGender() != null) {
				statement.setString(2, String.valueOf(personDTO.getGender()));
			}else {
				statement.setNull(2, Types.CHAR);
			}
			
			if(personDTO.getAge() != null && personDTO.getAge() != 0) {
				statement.setShort(3, personDTO.getAge());
			}else {
				statement.setNull(3, Types.SMALLINT);
			}
			
			if(personDTO.getCpr() != null && personDTO.getCpr() != 0) {
				statement.setInt(4, personDTO.getCpr());
			}else {
				statement.setNull(4, Types.INTEGER);
			}
			
			if(personDTO.getAccountNo() != null && personDTO.getAccountNo() != 0) {
				statement.setLong(5, personDTO.getAccountNo());
			}else {
				statement.setNull(5, Types.BIGINT);
			}
			
			if(personDTO.getGpa() != null && personDTO.getGpa() != 0) {
				statement.setFloat(6, personDTO.getGpa());
			}else {
				statement.setNull(6, Types.FLOAT);
			}
			
			if(personDTO.getSalary() != null && personDTO.getSalary() != 0) {
				statement.setDouble(7, personDTO.getSalary());
			}else {
				statement.setNull(7, Types.DOUBLE);
			}
			
			if(personDTO.getAnnualIncome() != null && !personDTO.getAnnualIncome().equals(new BigDecimal(0))) {
				statement.setBigDecimal(8, personDTO.getAnnualIncome());
			}else {
				statement.setNull(8, Types.DECIMAL);
			}
			
			if(personDTO.getDateOfBirth() != null) {
				statement.setDate(9, personDTO.getDateOfBirth());
			}else {
				statement.setNull(9, Types.DATE);
			}
			
			if(personDTO.getRegistrationDateTime() != null) {
				statement.setTimestamp(10, personDTO.getRegistrationDateTime());
			}else {
				statement.setNull(10, Types.TIMESTAMP);
			}
			
			if(personDTO.getSleepTime() != null) {
				statement.setTime(11, personDTO.getSleepTime());
			}else {
				statement.setNull(11, Types.TIME);
			}
			
			if(personDTO.getGraduated() != null) {
				statement.setBoolean(12, personDTO.getGraduated());
			}else {
				statement.setNull(12, Types.BOOLEAN);
			}
			
			statement.executeUpdate();
			
			try(ResultSet result = statement.getGeneratedKeys()){
				
				if(result.next()) {

					personDTO.setId(result.getInt("prsn_id"));
				}
			}
		}
		
		return personDTO;
	}
	
	public int deletePerson(PersonDTO personDTO) throws SQLException {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("delete from public.person ");
		builder.append("where prsn_name like '%' || ? || '%' ");
		builder.append("and prsn_gender = ? ");
		builder.append("and prsn_age = ? ");
		builder.append("and prsn_cpr = ? ");
		builder.append("and prsn_account_no = ? ");
		builder.append("and prsn_gpa = ? ");
		builder.append("and prsn_salary = ? ");
		builder.append("and prsn_annual_income = ? ");
		builder.append("and prsn_date_of_birth = ? ");
		builder.append("and prsn_registration_date_time = ? ");
		builder.append("and prsn_sleep_time = ? ");
		builder.append("and prsn_graduated = ? ");
		builder.append("and prsn_id = ?");

		try(PreparedStatement statement = connection.prepareStatement(builder.toString())){
			
			if(personDTO.getName() != null && !"".equals(personDTO.getName())) {
				statement.setString(1, personDTO.getName());
			}else {
				statement.setNull(1, Types.VARCHAR);
			}
			
			if(personDTO.getGender() != null) {
				statement.setString(2, String.valueOf(personDTO.getGender()));
			}else {
				statement.setNull(2, Types.CHAR);
			}
			
			if(personDTO.getAge() != null && personDTO.getAge() != 0) {
				statement.setShort(3, personDTO.getAge());
			}else {
				statement.setNull(3, Types.SMALLINT);
			}
			
			if(personDTO.getCpr() != null && personDTO.getCpr() != 0) {
				statement.setInt(4, personDTO.getCpr());
			}else {
				statement.setNull(4, Types.INTEGER);
			}
			
			if(personDTO.getAccountNo() != null && personDTO.getAccountNo() != 0) {
				statement.setLong(5, personDTO.getAccountNo());
			}else {
				statement.setNull(5, Types.BIGINT);
			}
			
			if(personDTO.getGpa() != null && personDTO.getGpa() != 0) {
				statement.setFloat(6, personDTO.getGpa());
			}else {
				statement.setNull(6, Types.FLOAT);
			}
			
			if(personDTO.getSalary() != null && personDTO.getSalary() != 0) {
				statement.setDouble(7, personDTO.getSalary());
			}else {
				statement.setNull(7, Types.DOUBLE);
			}
			
			if(personDTO.getAnnualIncome() != null && !personDTO.getAnnualIncome().equals(new BigDecimal(0))) {
				statement.setBigDecimal(8, personDTO.getAnnualIncome());
			}else {
				statement.setNull(8, Types.DECIMAL);
			}
			
			if(personDTO.getDateOfBirth() != null) {
				statement.setDate(9, personDTO.getDateOfBirth());
			}else {
				statement.setNull(9, Types.DATE);
			}
			
			if(personDTO.getRegistrationDateTime() != null) {
				statement.setTimestamp(10, personDTO.getRegistrationDateTime());
			}else {
				statement.setNull(10, Types.TIMESTAMP);
			}
			
			if(personDTO.getSleepTime() != null) {
				statement.setTime(11, personDTO.getSleepTime());
			}else {
				statement.setNull(11, Types.TIME);
			}
			
			if(personDTO.getGraduated() != null) {
				statement.setBoolean(12, personDTO.getGraduated());
			}else {
				statement.setNull(12, Types.BOOLEAN);
			}
			
			if(personDTO.getGraduated() != null) {
				statement.setBoolean(12, personDTO.getGraduated());
			}else {
				statement.setNull(12, Types.BOOLEAN);
			}
			
			if(personDTO.getId() != null) {
				statement.setInt(13, personDTO.getId());
			}else {
				statement.setNull(13, Types.INTEGER);
			}
			
			return statement.executeUpdate();
		}
	}
}
