package person.dao;

import java.sql.Connection;

import person.generator.GeneratorUtils;

public class TestSelectQueryWithString {

	public static void main(String[] args) throws Exception {
		
		Connection connection = GeneratorUtils.getConnection();
		
		ModernPersonDAO personDAO = new ModernPersonDAO(connection);
		
		personDAO.selectPersonWithString();
	}
}
