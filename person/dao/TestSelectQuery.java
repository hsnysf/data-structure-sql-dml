package person.dao;

import java.sql.Connection;
import person.generator.GeneratorUtils;

public class TestSelectQuery {

	public static void main(String[] args) throws Exception {
		
		Connection connection = GeneratorUtils.getConnection();
		
		ModernPersonDAO personDAO = new ModernPersonDAO(connection);
		
		personDAO.selectAdvanced();
	}
}
