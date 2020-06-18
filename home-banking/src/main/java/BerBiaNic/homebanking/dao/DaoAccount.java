package BerBiaNic.homebanking.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import BerBiaNic.homebanking.db.Database;
import BerBiaNic.homebanking.entity.Account;
import BerBiaNic.homebanking.entity.Cliente;

public class DaoAccount implements Dao<Account,Integer > {

	@Override
	public Future<Account> getOne(Integer primaryKey) {
		String query = "SELECT * FROM account WHERE  id = ?";
		CompletableFuture<Account> account = CompletableFuture.supplyAsync(() -> {

			try {
				PreparedStatement ps = Database.getConnection().prepareStatement(query);
				ps.setInt(1, primaryKey);
				ResultSet rs = ps.executeQuery();
				rs.next();

				DaoCliente cliente = new DaoCliente();
				Cliente c = cliente.getOne(rs.getString("codice_fiscale")).get();

				int id = rs.getInt("id"); 
				String username =  rs.getString("username");
				String password = rs.getString("password"); 
				String email = rs.getString("email");
				long improntaDigitale = rs.getLong("impronta_digitale"); 
				String dispositiviAssociati = rs.getString("dispositivi_associati");

				Account a = new Account(c, id, username, password, email, improntaDigitale, dispositiviAssociati);
				return a;
			} catch (SQLException | InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return null;
			}
		});
		return account;
	}

	@Override
	public Future<List<Account>> getAll() {
		String query = "SELECT * FROM account";
		CompletableFuture<List<Account>> accounts = CompletableFuture.supplyAsync(() -> {
			List<Account> result = new ArrayList<>();
			try {
				Statement s = Database.getConnection().createStatement();
				ResultSet rs = s.executeQuery(query);

				while(rs.next()) {
					Account a = getOne(rs.getInt("id")).get();
					result.add(a);
				}
				return result;
			} catch (SQLException | InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return null;
			}
		});
		return accounts;
	}

	@Override
	public Future<Account> insert(Account element) {
		String query = "insert into cliente(id, username, password, email, impronta_digitale, dispositivi_associati)" + 
				"values (?,?,?,?,?,?)";
		CompletableFuture.runAsync(() -> {

			try {
				PreparedStatement ps = Database.getConnection().prepareStatement(query);

				int id = element.getId(); 
				String username = element.getUsername();
				String password = element.getPassword(); 
				String email = element.getEmail();
				long improntaDigitale = element.getImprontaDigitale();
				String dispositiviAssociati = element.getDispositiviAssociati();
				
				ps.setInt(1, id);
				ps.setString(2, username);
				ps.setString(3, password);
				ps.setString(4, email);
				ps.setLong(5, improntaDigitale);
				ps.setString(6, dispositiviAssociati);

				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		return getOne(element.getId());
	}

	@Override
	public Future<Integer> delete(Integer primaryKey) {
		String query = "DELETE FROM Account WHERE id = ?";

		CompletableFuture<Integer> del= CompletableFuture.supplyAsync(() -> {

			try {
				PreparedStatement ps = Database.getConnection().prepareStatement(query);
				ps.setInt(1, primaryKey);
				return ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		});
		return del;
	}

	@Override
	public Future<Account> update(Account element) {

		CompletableFuture<Account> res = CompletableFuture.supplyAsync(() -> {
			try {
				delete(element.getId());
				return insert(element).get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return null;
			}
			
		});
		return res;
	}
}
