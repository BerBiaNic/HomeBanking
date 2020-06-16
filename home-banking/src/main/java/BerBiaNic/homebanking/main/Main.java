package BerBiaNic.homebanking.main;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BerBiaNic.homebanking.db.Database;

public class Main {
	public static void main(String[] args) throws SQLException, IOException {
		
		Database db = new Database();
		
		Statement stmt = (Statement) db.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM cliente");
		
		while(rs.next())
			System.out.println("nome: " + rs.getString("nome"));
	}
}