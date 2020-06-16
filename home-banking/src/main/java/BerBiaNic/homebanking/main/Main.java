package BerBiaNic.homebanking.main;


import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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
		
//		while(rs.next()) {
//			System.out.println("nome: " + rs.getString("nome"));
//			System.out.println("numero: " + rs.getString("codice_fiscale"));
//		}
		
		Dao<Cliente,String> daoAccount = new DaoCliente();
		
	
		System.out.println("------------------------------- L I S T A		C L I E N T I -------------------------------\n");
		Future<List<Cliente>> futureClients = daoAccount.getAll();
		List<Cliente> clients = futureClients.get();
		for(Cliente c: clients) {
			System.out.println(c);
			System.out.println();
		}
		
		System.out.println("------------------------------- S I N G O L O		C L I E N T E -------------------------------\n");
		Future<Cliente> futureCliente = daoAccount.getOne("SSSDRA50A13C842B");
		System.out.println(futureCliente.get());
		
		
		Cliente clienteAdd = new Cliente("BNCCHR99H63G113Y", "Bianchi", "Chiara", "Oristano", Date.valueOf(LocalDate.of(1999, 06, 23)), "0310 0012114", "Via Francesco Girardi, 96", "SALERNO");

		
		
//		System.out.println("------------------------------- A G G I U N G I		C L I E N T E -------------------------------\n");
//		Future<Cliente> futureAddClient = daoAccount.insert(clienteAdd);
//		System.out.println("Cliente " + futureAddClient.get().getNome() + " - " + futureAddClient.get().getCognome() + " aggiunto!");
		
		
		System.out.println("------------------------------- E L I M I N A		C L I E N T E -------------------------------\n");
		Future<Integer> futureDeleteClient = daoAccount.delete(clienteAdd.getCodiceFiscale());
		if( futureDeleteClient.get() == 1 ) {
			System.out.println("Cliente " + clienteAdd.getNome() + " - " + clienteAdd.getCognome() + " eliminato!");
		}else
			System.out.println("Nessun cliente da eliminare!");
//		
//		System.out.println("------------------------------- A G G I U N G I		C L I E N T E -------------------------------\n");
//		Cliente clienteAdd = new Cliente("SSSDRA50A13C842B", "Sossini", "Dario", "COLLALTO", Date.valueOf(LocalDate.of(1950, 01, 13)), "393778254710", "Via Salita Truglio, 9", "SALERNO");
//		Future<Cliente> futureAddClient = daoAccount.insert(clienteAdd);
//		System.out.println("Cliente " + futureAddClient.get().getNome() + ", " + futureAddClient.get().getCognome() + " aggiunto!");
				
		
		
	}
}