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
import BerBiaNic.homebanking.entity.ContoCorrente;
import BerBiaNic.homebanking.exceptions.EmptyResultSet;
import BerBiaNic.homebanking.exceptions.InputValidationException;
/**
 * 
 * @authors Antonino Bertuccio, Giuseppe Bianchino, Giovanni Nicotera
 *
 */

public class DaoContoCorrente implements Dao<ContoCorrente, String> {
	
	/**
	 * Cerca tramite iban un conto corrente presente all'interno del database. Ritorna un oggetto di tipo Future<ContoCorrente>. 
	 */
	@Override
	public Future<ContoCorrente> getOne(String primaryKey) {
		String query = "SELECT * FROM conto_corrente WHERE iban = ?";
		CompletableFuture<ContoCorrente> futureContoCorrente = CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				if (primaryKey == null || primaryKey.isBlank())
					throw new InputValidationException("IBAN conto corrente", Response.Status.METHOD_NOT_ALLOWED);
				if(primaryKey.length() != 31)
					throw new InputValidationException("IBAN conto corrente non valido. Caratteri richiesti 27, inseriti: " + primaryKey.length(), Response.Status.METHOD_NOT_ALLOWED);
				if(!primaryKey.matches("IT+\\d{2}+[a-zA-Z]+\\d{22}"))
					throw new InputValidationException("Formato IBAN conto corrente (esempio inserimento IT28W8000000292100645211151)", Response.Status.METHOD_NOT_ALLOWED);
				ps = conn.prepareStatement(query);
				ps.setString(1, primaryKey);
				rs = ps.executeQuery();
				rs.next();
				if(rs == null)
					throw new EmptyResultSet("Nessuna operazione trovata con questo id", Response.Status.METHOD_NOT_ALLOWED);
				int numero = rs.getInt("numero"); 
				String iban =  rs.getString("iban");
				double saldoD = rs.getDouble("saldo_disponibile"); 
				double saldoC = rs.getDouble("saldo_contabile"); 

				DaoAccount daoAccount = new DaoAccount();
				Account a = daoAccount.getOne(rs.getInt("id_account")).get();
				return new ContoCorrente(numero, iban, saldoD, saldoC, a);
			} catch (SQLException | InterruptedException | ExecutionException | InputValidationException | EmptyResultSet e) {
				e.printStackTrace();
				return null;
			}finally {
				if( conn != null ) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if( ps != null ) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if( rs != null ) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		return futureContoCorrente;
	}
	
	/**
	 * Ritorna un oggetto di tipo Future<List<ContoCorrente>> contenente tutti i conti correnti presenti nel database.
	 */
	@Override
	public Future<List<ContoCorrente>> getAll() {
		String query = "SELECT * FROM conto_corrente";
		CompletableFuture<List<ContoCorrente>> futureAccounts = CompletableFuture.supplyAsync(() -> {
			List<ContoCorrente> result = new ArrayList<>();
			Connection conn = Database.getConnection();
			Statement s = null;
			try {
				s = conn.createStatement();
				ResultSet rs = s.executeQuery(query);

				while(rs.next()) {
					if(rs == null)
						throw new EmptyResultSet("Nessuna operazione trovata con questo id", Response.Status.METHOD_NOT_ALLOWED);
					ContoCorrente a = getOne(rs.getString("iban")).get();
					result.add(a);
				}
				return result;
			} catch (SQLException | InterruptedException | ExecutionException | EmptyResultSet e) {
				e.printStackTrace();
				return null;
			}finally {
				if( conn != null ) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if( s != null ) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		return futureAccounts;
	}

	/**
	 * Inserisce all'interno del database un conto corrente. Ritorna un oggetto di tipo Future<ContoCorrente>.
	 */
	@Override
	public Future<ContoCorrente> insert(ContoCorrente element) {
		String query = "insert into conto_corrente(numero,iban,saldo_disponibile,saldo_contabile,id_account)" + 
				"values (?,?,?,?,?)";
		CompletableFuture.runAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);
				if(element == null)
					throw new InputValidationException("", Response.Status.METHOD_NOT_ALLOWED);
				int numero = element.getNumero(); 
				String iban = element.getIban();
				double saldoD = element.getSaldo_disponibile(); 
				double saldoC = element.getSaldo_contabile();
				int idA = element.getAccount().getId();

				ps.setInt(1, numero);
				ps.setString(2, iban);
				ps.setDouble(3, saldoD);
				ps.setDouble(4, saldoC);
				ps.setInt(5, idA);
				ps.executeUpdate();

			} catch (SQLException | InputValidationException e) {
				e.printStackTrace();
			}finally {
				if( conn != null ) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if( ps != null ) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		return getOne(element.getIban());
	}

	/**
	 * Elimina dal database un conto corrente cercandolo tramite iban. Ritorna un oggetto di tipo Future<Integer>.
	 */
	@Override
	public Future<Integer> delete(String primaryKey) {
		String query = "DELETE FROM conto_corrente WHERE iban = ?";

		CompletableFuture<Integer> del= CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			try {
				if (primaryKey == null || primaryKey.isBlank())
					throw new InputValidationException("IBAN conto corrente", Response.Status.METHOD_NOT_ALLOWED);
				if(primaryKey.length() != 31)
					throw new InputValidationException("IBAN conto corrente non valido. Caratteri richiesti 27, inseriti: " + primaryKey.length(), Response.Status.METHOD_NOT_ALLOWED);
				if(!primaryKey.matches("IT+\\d{2}+[a-zA-Z]+\\d{22}"))
					throw new InputValidationException("Formato IBAN conto corrente (esempio inserimento IT28W8000000292100645211151)", Response.Status.METHOD_NOT_ALLOWED);
				ps = conn.prepareStatement(query);
				ps.setString(1, primaryKey);
				return ps.executeUpdate();
			} catch (SQLException | InputValidationException e) {
				e.printStackTrace();
				return 0;
			}finally {
				if( conn != null ) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if( ps != null ) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		return del;
	}

	/**
	 * Modifica un conto corrente presente all'interno del database. Ritorna un oggetto di tipo Future<ContoCorrente>.
	 */
	@Override
	public Future<ContoCorrente> update(ContoCorrente element) {
		try {
			if(element == null)
				throw new InputValidationException("", Response.Status.METHOD_NOT_ALLOWED);
			CompletableFuture<ContoCorrente> res = CompletableFuture.supplyAsync(() -> {
				return delete(element.getIban());

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
