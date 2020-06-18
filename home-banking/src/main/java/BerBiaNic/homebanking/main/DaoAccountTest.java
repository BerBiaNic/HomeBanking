package BerBiaNic.homebanking.main;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;

import BerBiaNic.homebanking.dao.Dao;
import BerBiaNic.homebanking.dao.DaoAccount;
import BerBiaNic.homebanking.db.Database;
import BerBiaNic.homebanking.entity.Account;
import BerBiaNic.homebanking.entity.Cliente;

public class DaoAccountTest {
	public static void main(String[] args) throws SQLException, IOException, InterruptedException, ExecutionException {

		Database db = new Database();
		Statement stmt = (Statement) db.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM cliente");

		//		while(rs.next()) {
		//			System.out.println("nome: " + rs.getString("nome"));
		//			System.out.println("numero: " + rs.getString("codice_fiscale"));
		//		}

		Dao<Account,Integer> daoAccount = new DaoAccount();


		System.out.println("------------------------------- L I S T A		A C C O U N T S -------------------------------\n");
		Future<List<Account>> futureClients = daoAccount.getAll();
		List<Account> accounts = futureClients.get();
		for(Account a: accounts) {
			System.out.println(a);
			System.out.println();
		}

		//		System.out.println("------------------------------- S I N G O L O		A C C O U N T -------------------------------\n");
		//		Future<Account> futureCliente = daoAccount.getOne(1);
		//		System.out.println(futureCliente.get());
		//
		//		Cliente c = new Cliente("BNCCHR99H63G113Y", "Bianchi", "Chiara", "Oristano", Date.valueOf(LocalDate.of(1999, 06, 23)), "0310 0012114", "Via Francesco Girardi, 96", "SALERNO");
		//		Account accountAdd = new Account(c, 1, "bianchina", "Bnc123", "bianchichiara@libero.it", 23131534, "hp-15684ds, asusZenfone-3546ac");



		//		System.out.println("------------------------------- A G G I U N G I		A C C O U N T -------------------------------\n");
		//		Future<Cliente> futureAddClient = daoAccount.insert(clienteAdd);
		//		System.out.println("Cliente " + futureAddClient.get().getNome() + " - " + futureAddClient.get().getCognome() + " aggiunto!");

		System.out.println("------------------------------- A G G I U N G I		A C C O U N T -------------------------------\n");
		Cliente clienteAdd = new Cliente("SSSDRA50A13C842B", "Sossini", "Dario", "COLLALTO", Date.valueOf(LocalDate.of(1950, 01, 13)), "393778254710", "Via Salita Truglio, 9", "SALERNO");
		Account accountAdd2 = new Account(clienteAdd, 2, "sossininoad", "Sss456", "sossinidario@libero.it", 2991537, "hp-13664ds, asusZenfone-9965ac");

		((CompletableFuture<Account>)daoAccount.insert(accountAdd2)).thenApply( value ->{
			System.out.println(value);
			return daoAccount.delete(accountAdd2.getId());
		}).thenAccept( a ->{
			System.out.println("return");
			System.out.println(a);
		});
		
//		System.out.println("Account " + accountAdd2.getId() + " aggiunto!");
	

//		System.out.println("------------------------------- E L I M I N A		A C C O U N T -------------------------------\n");
//		while(true) {
//			if( account.isDone()) {
//				System.out.println(accountAdd2.getId());
//				Future<Integer> futureDeleteClient = daoAccount.delete(accountAdd2.getId());
//				if( futureDeleteClient.get() == 1 ) {
//					System.out.println("Account " + accountAdd2.getId() + " eliminato!");
//				}else
//					System.out.println("Nessun cliente da eliminare!");
//				break;
//			}
//		}

	}
}
