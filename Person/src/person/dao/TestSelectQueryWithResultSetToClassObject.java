package person.dao;

import java.sql.Connection;

import person.generator.GeneratorUtils;

public class TestSelectQueryWithResultSetToClassObject {

	public static void main(String[] args) throws Exception {
		
		Connection connection = GeneratorUtils.getConnection();
		
		ModernPersonDAO personDAO = new ModernPersonDAO(connection);
		
		personDAO.selectPersonWithResultSetToClassObject();
	}
}
