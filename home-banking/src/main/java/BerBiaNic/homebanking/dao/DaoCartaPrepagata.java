package BerBiaNic.homebanking.dao;

import java.sql.Connection;
import java.sql.Date;
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
import BerBiaNic.homebanking.entity.CartaPrepagata;
import BerBiaNic.homebanking.exceptions.EmptyResultSet;
import BerBiaNic.homebanking.exceptions.InputValidationException;
/**
 * 
 * @authors Antonino Bertuccio, Giuseppe Bianchino, Giovanni Nicotera
 *
 */
public class DaoCartaPrepagata implements Dao<CartaPrepagata,String>{

	/**
	 * Cerca tramite numero una carta prepagata presente all'interno del database. Ritorna un oggetto di tipo Future<CartaPrepagata>. 
	 */
	@Override
	public Future<CartaPrepagata> getOne(String primaryKey) {
		String query = "SELECT * FROM carta_prepagata WHERE numero = ?";
		CompletableFuture<CartaPrepagata> carta = CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				if(primaryKey == null || primaryKey.isBlank())
					throw new InputValidationException("Numero carta prepagata", Response.Status.METHOD_NOT_ALLOWED);
				if(primaryKey.length() != 16)
					throw new InputValidationException("Numero carta prepagata. Caratteri richiesti 16, inseriti: " + primaryKey.length(), Response.Status.METHOD_NOT_ALLOWED);
				if(!primaryKey.matches("[\\d]{16}"))  
					throw new InputValidationException("Formato numero carta prepagata (esempio inserimento 1234569874521456).", Response.Status.METHOD_NOT_ALLOWED);

				ps = conn.prepareStatement(query);
				ps.setString(1, primaryKey);
				rs = ps.executeQuery();
				rs.next();

				if(rs == null)
					throw new EmptyResultSet("Nessuna carta prepagata con questo numero", Response.Status.METHOD_NOT_ALLOWED);
				DaoAccount daoAccount = new DaoAccount();

				Account a = daoAccount.getOne(rs.getInt("id_account")).get();

				String numero = rs.getString("numero");
				double saldoContabile = rs.getDouble("saldo_contabile");
				double saldoDisponibile = rs.getDouble("saldo_disponibile");
				Date dataScadenza = rs.getDate("data_di_scadenza");
				int cvv = rs.getInt("cvv");
				int pin = rs.getInt("pin");

				CartaPrepagata c = new CartaPrepagata(numero,saldoContabile,saldoDisponibile,dataScadenza,cvv,pin,a);
				return c;
			} catch (SQLException | InterruptedException | ExecutionException | InputValidationException | EmptyResultSet e) {
				e.printStackTrace();
				return null;
			} finally {
				if(conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				if(ps != null)
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				if(rs != null)
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		});
		return carta;
	}

	/**
	 * Ritorna un oggetto di tipo Future<List<CartaPrepagata>> contenente tutte le carte prepagate associate ad un account.
	 */
	@Override
	public Future<List<CartaPrepagata>> getAll() {
		String query = "SELECT * FROM carta_prepagata";
		CompletableFuture<List<CartaPrepagata>> carte = CompletableFuture.supplyAsync(() -> {
			List<CartaPrepagata> result = new ArrayList<CartaPrepagata>();
			ResultSet rs = null;
			Statement s = null;
			Connection conn = Database.getConnection();
			try {
				s = conn.createStatement();
				rs = s.executeQuery(query);	
				while(rs.next()) {
					if(rs == null)
						throw new EmptyResultSet("Nessuna carta prepagata con questo numero", Response.Status.METHOD_NOT_ALLOWED);
					CartaPrepagata carta = getOne(rs.getString("numero")).get();
					result.add(carta);
				}		
				return result;
			} catch (SQLException | InterruptedException | ExecutionException | EmptyResultSet e) {
				e.printStackTrace();
				return null;
			}finally {
				if(conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				if(s != null)
					try {
						s.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				if(rs != null)
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		});
		return carte;
	}

	/**
	 * Inserisce all'interno del database una carta prepagata. Ritorna un oggetto di tipo Future<CartaPrepagata>.
	 */
	@Override
	public Future<CartaPrepagata> insert(CartaPrepagata element) {
		String query = "INSERT INTO carta_prepagata(numero, saldo_contabile, saldo_disponibile, data_di_scadenza, cvv, pin, id_account)" +
				"VALUES(?,?,?,?,?,?,?)";
		CompletableFuture.runAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			try {
				if(element == null)
					throw new InputValidationException("", Response.Status.METHOD_NOT_ALLOWED);
				ps = conn.prepareStatement(query);
				ps.setString(1, element.getNumero());
				ps.setDouble(2, element.getSaldoContabile());
				ps.setDouble(3, element.getSaldoDisponibile());
				ps.setDate(4, element.getDataDiScadenza());
				ps.setInt(5, element.getCvv());
				ps.setInt(6, element.getPin());
				ps.setInt(7, element.getAccount().getId());
				ps.executeUpdate();
			} catch (SQLException | InputValidationException e) {
				e.printStackTrace();
			}finally {
				if(conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				if(ps != null)
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		});
		return getOne(element.getNumero());
	}

	/**
	 * Elimina dal database una carta di debito cercandola tramite il numero. Ritorna un oggetto di tipo Future<Integer>.
	 */
	@Override
	public Future<Integer> delete(String primaryKey) {
		String query = "DELETE FROM carta_prepagata WHERE numero = ?";
		CompletableFuture<Integer> del= CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			try {
				if(primaryKey == null || primaryKey.isBlank())
					throw new InputValidationException("Numero carta prepagata", Response.Status.METHOD_NOT_ALLOWED);
				if(primaryKey.length() != 16)
					throw new InputValidationException("Numero carta prepagata. Caratteri richiesti 16, inseriti: " + primaryKey.length(), Response.Status.METHOD_NOT_ALLOWED);
				if(!primaryKey.matches("[\\d]{16}"))  
					throw new InputValidationException("Formato numero carta prepagata (esempio inserimento 1234569874521456).", Response.Status.METHOD_NOT_ALLOWED);

				ps = conn.prepareStatement(query);
				ps.setString(1, primaryKey);
				return ps.executeUpdate();
			} catch (SQLException | InputValidationException e) {
				e.printStackTrace();
				return 0;
			}finally {
				if(conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				if(ps != null)
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		});
		return del;
	}

	/**
	 * Modifica una carta prepagata presente all'interno del database. Ritorna un oggetto di tipo Future<CartaPrepagata>.
	 */
	@Override
	public Future<CartaPrepagata> update(CartaPrepagata element) {
		try {
			CompletableFuture<CartaPrepagata> res = CompletableFuture.supplyAsync(() ->{
				return delete(element.getNumero());
			}).thenApply(value ->{
				try {
					return insert(element).get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
					return null;
				}
			});
			return res;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
