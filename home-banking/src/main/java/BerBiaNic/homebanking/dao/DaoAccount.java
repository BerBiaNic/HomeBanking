package BerBiaNic.homebanking.dao;

import java.sql.Connection;
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
import BerBiaNic.homebanking.exceptions.InputValidationException;
/**
 * 
 * @authors Antonino Bertuccio, Giuseppe Bianchino, Giovanni Nicotera
 *
 */
public class DaoAccount implements Dao<Account,Integer > {

	/**
	 * Cerca tramite id un account presente all'interno del database. Ritorna un oggetto di tipo Future<Account>. 
	 */
	@Override
	public Future<Account> getOne(Integer primaryKey) {
		String query = "SELECT * FROM account WHERE id = ?";
		CompletableFuture<Account> account = CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, primaryKey);
				rs = ps.executeQuery();
				rs.next();

				DaoCliente cliente = new DaoCliente();
				Cliente c = cliente.getOne(rs.getString("codice_fiscale_cliente")).get();

				int id = rs.getInt("id"); 
				String username =  rs.getString("username");
				String password = rs.getString("password"); 
				String email = rs.getString("email");
				long improntaDigitale = rs.getLong("impronta_digitale"); 
				String dispositiviAssociati = rs.getString("dispositivi_associati");

				Account a = new Account(id, username, password, email, improntaDigitale, dispositiviAssociati, c);
				return a;
			} catch (SQLException | InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return null;
			} finally {
				try {
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					if(ps!=null)
						ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					if(rs!=null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		return account;
	}
	
	/**
	 * Cerca tramite username e password un account presente all'interno del database. Ritorna un oggetto di tipo Future<Account>. 
	 */
	public Future<Account> getOne(String username, String password) {
		String query = "SELECT * FROM account WHERE username = ? AND password = ?";
		CompletableFuture<Account> account = CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, username);
				ps.setString(2, password);
				
				rs = ps.executeQuery();
				rs.next();

				DaoCliente cliente = new DaoCliente();
				Cliente c = cliente.getOne(rs.getString("codice_fiscale_cliente")).get();

				int id = rs.getInt("id"); 
				String user =  rs.getString("username");
				String pass = rs.getString("password"); 
				String email = rs.getString("email");
				long improntaD = rs.getLong("impronta_digitale"); 
				String dispositiviAssociati = rs.getString("dispositivi_associati");

				Account a = new Account(id, user, pass, email, improntaD, dispositiviAssociati, c);
				return a;
			} catch (SQLException | InterruptedException | ExecutionException | InputValidationException e) {
				e.printStackTrace();
				return null;
			} finally {
				try {
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					if(ps!=null)
						ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					if(rs!=null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		return account;
	}
	
	/**
	 * Cerca tramite impronta digitale un account presente all'interno del database. Ritorna un oggetto di tipo Future<Account>. 
	 */
	public Future<Account> getOne(long improntaDigitale) {
		String query = "SELECT * FROM account WHERE username = ? AND password = ?";
		CompletableFuture<Account> account = CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = conn.prepareStatement(query);
				ps.setLong(1, improntaDigitale);
				
				rs = ps.executeQuery();
				rs.next();

				DaoCliente cliente = new DaoCliente();
				Cliente c = cliente.getOne(rs.getString("codice_fiscale_cliente")).get();

				int id = rs.getInt("id"); 
				String user =  rs.getString("username");
				String pass = rs.getString("password"); 
				String email = rs.getString("email");
				long improntaD = rs.getLong("impronta_digitale"); 
				String dispositiviAssociati = rs.getString("dispositivi_associati");

				Account a = new Account(id, user, pass, email, improntaD, dispositiviAssociati, c);
				return a;
			} catch (SQLException | InterruptedException | ExecutionException | InputValidationException e) {
				e.printStackTrace();
				return null;
			} finally {
				try {
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					if(ps!=null)
						ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					if(rs!=null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		return account;
	}

	/**
	 * Ritorna un oggetto di tipo Future<List<Account>> contenente tutti gli account presenti nel database.
	 */
	@Override
	public Future<List<Account>> getAll() {
		String query = "SELECT * FROM account";
		CompletableFuture<List<Account>> accounts = CompletableFuture.supplyAsync(() -> {
			List<Account> result = new ArrayList<>();

			Connection conn = Database.getConnection();
			Statement s = null;
			ResultSet rs = null;

			try {
				s = conn.createStatement();
				rs = s.executeQuery(query);

				while(rs.next()) {
					Account a = getOne(rs.getInt("id")).get();
					result.add(a);
				}
				return result;
			} catch (SQLException | InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return null;
			} finally {
				try {
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					if(s!=null)
						s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					if(rs!=null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		return accounts;
	}

	/**
	 * Inserisce all'interno del database un'accoun. Ritorna un oggetto di tipo Future<Account>.
	 */
	@Override
	public Future<Account> insert(Account element) {
		String query = "insert into account(id, username, password, email, impronta_digitale, dispositivi_associati, codice_fiscale_cliente)" + 
				"values (?,?,?,?,?,?,?)";

		CompletableFuture.runAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;

			try {
				ps = conn.prepareStatement(query);

				int id = element.getId(); 
				String username = element.getUsername();
				String password = element.getPassword(); 
				String email = element.getEmail();
				long improntaDigitale = element.getImprontaDigitale();
				String dispositiviAssociati = element.getDispositiviAssociati();
				String codiceFiscaleC = element.getCliente().getCodiceFiscale();

				ps.setInt(1, id);
				ps.setString(2, username);
				ps.setString(3, password);
				ps.setString(4, email);
				ps.setLong(5, improntaDigitale);
				ps.setString(6, dispositiviAssociati);
				ps.setString(7, codiceFiscaleC);

				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					if(ps!=null)
						ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		return getOne(element.getId());
	}

	/**
	 * Elimina dal database un account cercandolo tramite id. Ritorna un oggetto di tipo Future<Account>.
	 */
	@Override
	public Future<Integer> delete(Integer primaryKey) {
		String query = "DELETE FROM account WHERE id = ?";

		CompletableFuture<Integer> del= CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;

			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, primaryKey);
				return ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
				
			} finally {
				try {
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					if(ps!=null)
						ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		return del;
	}

	/**
	 * Modifica un account presente all'interno del database. Ritorna un oggetto di tipo Future<Account>.
	 */
	@Override
	public Future<Account> update(Account element) {
		try {
			CompletableFuture<Account> res = CompletableFuture.supplyAsync(() -> {
				return delete(element.getId());

			}).thenApply(value->{
				try {
					return insert(element).get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
					return null;
				}
			});
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
