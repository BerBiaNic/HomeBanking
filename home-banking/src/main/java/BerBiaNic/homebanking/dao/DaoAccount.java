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

import javax.ws.rs.core.Response;

import BerBiaNic.homebanking.db.Database;
import BerBiaNic.homebanking.entity.Account;
import BerBiaNic.homebanking.entity.Cliente;
import BerBiaNic.homebanking.exceptions.EmptyResultSet;
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
				if(primaryKey <= 0)
					throw new InputValidationException("Id account", Response.Status.METHOD_NOT_ALLOWED);
				ps = conn.prepareStatement(query);
				ps.setInt(1, primaryKey);
				rs = ps.executeQuery();
				rs.next();
				
				if(rs == null)
					throw new EmptyResultSet("Nessun Account trovato con questo id", Response.Status.METHOD_NOT_ALLOWED);
				
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
			} catch (InputValidationException e) {
				e.printStackTrace();
				return null;
			} catch (EmptyResultSet e) {
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
				
				if(username == null || username.isBlank())
					throw new InputValidationException("Username", Response.Status.METHOD_NOT_ALLOWED);
				if (!username.matches("[\\w_-]{3,45}"))
					throw new InputValidationException("Formato username (caratteri speciali consentiti _-)", Response.Status.METHOD_NOT_ALLOWED);

				if(password == null || password.isBlank())
					throw new InputValidationException("Password", Response.Status.METHOD_NOT_ALLOWED);
				if(!password.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}")) 
					throw new InputValidationException("La password deve contenere un numero, un carattere minuscolo, "
							+ "uno maiuscolo, un carattere speciale tra @#$% e deve avere lunghezza min 8 e max 20", Response.Status.METHOD_NOT_ALLOWED);
				
				ps = conn.prepareStatement(query);
				ps.setString(1, username);
				ps.setString(2, password);
				
				rs = ps.executeQuery();
				rs.next();
				
				if(rs == null)
					throw new EmptyResultSet("Username o Password errate", Response.Status.METHOD_NOT_ALLOWED);

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
			} catch (SQLException | InterruptedException | ExecutionException | InputValidationException | EmptyResultSet e) {
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
		String query = "SELECT * FROM account WHERE impronta_digitale = ?";
		CompletableFuture<Account> account = CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				
				if(improntaDigitale <= 0)
					throw new InputValidationException("Impronta digitale", Response.Status.METHOD_NOT_ALLOWED);
				
				ps = conn.prepareStatement(query);
				ps.setLong(1, improntaDigitale);
				
				rs = ps.executeQuery();
				rs.next();

				if(rs == null)
					throw new EmptyResultSet("Username o Password errate", Response.Status.METHOD_NOT_ALLOWED);
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
			} catch (SQLException | InterruptedException | ExecutionException | InputValidationException | EmptyResultSet e) {
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
					if(rs == null)
						throw new EmptyResultSet("Username o Password errate", Response.Status.METHOD_NOT_ALLOWED);
					Account a = getOne(rs.getInt("id")).get();
					result.add(a);
				}
				return result;
			} catch (SQLException | InterruptedException | ExecutionException | EmptyResultSet e) {
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
				if(element == null)
					throw new InputValidationException("", Response.Status.METHOD_NOT_ALLOWED);
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
			} catch (SQLException | InputValidationException e) {
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
				if(primaryKey <= 0)
					throw new InputValidationException("Id account", Response.Status.METHOD_NOT_ALLOWED);
				
				ps = conn.prepareStatement(query);
				ps.setInt(1, primaryKey);
				return ps.executeUpdate();
			} catch (SQLException | InputValidationException e) {
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
			if(element == null)
				throw new InputValidationException("", Response.Status.METHOD_NOT_ALLOWED);
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
