package BerBiaNic.homebanking.main;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import BerBiaNic.homebanking.dao.*;
import BerBiaNic.homebanking.db.Database;
import BerBiaNic.homebanking.entity.*;

public class Main {
	public static void main(String[] args) throws SQLException, IOException, InterruptedException, ExecutionException {
		
		Database db = new Database();
		
		Statement stmt = (Statement) db.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM cliente");
		
		while(rs.next()) {
			System.out.println("nome: " + rs.getString("nome"));
			System.out.println();
			System.out.println("numero: " + rs.getInt("numero_di_telefono"));
		}
		
		Dao<Cliente,String> daoAccount = new DaoCliente();
		Future<Cliente> futureCliente = daoAccount.getOne("");
		System.out.println(futureCliente.get());
		
		
	}
}