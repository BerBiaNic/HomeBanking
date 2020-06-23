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
import BerBiaNic.homebanking.entity.*;
import BerBiaNic.homebanking.exceptions.EmptyResultSet;
import BerBiaNic.homebanking.exceptions.InputValidationException;
/**
 * 
 * @authors Antonino Bertuccio, Giuseppe Bianchino, Giovanni Nicotera
 *
 */
public class DaoCartaDiDebito implements Dao<CartaDiDebito, String> {

	/**
	 * Cerca tramite id una carta di debito presente all'interno del database. Ritorna un oggetto di tipo Future<CartaDiDebito>. 
	 */
	@Override
	public Future<CartaDiDebito> getOne(String primaryKey) {
		String query = "SELECT * FROM carta_di_debito WHERE numero = ?";
		CompletableFuture<CartaDiDebito> futureCartaDebito = CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				if(primaryKey == null || primaryKey.isBlank())
					throw new InputValidationException("Numero carta di debito", Response.Status.METHOD_NOT_ALLOWED);
				if(primaryKey.length() != 16)
					throw new InputValidationException("Numero carta di debito non valido. Caratteri richiesti 16, inseriti: " + primaryKey.length(), Response.Status.METHOD_NOT_ALLOWED);
				if(!primaryKey.matches("[\\d]*")) 
					throw new InputValidationException("Formato numero carta di debito errato.", Response.Status.METHOD_NOT_ALLOWED);

				ps = conn.prepareStatement(query);
				ps.setString(1, primaryKey);
				rs = ps.executeQuery();
				rs.next();
				if(rs == null)
					throw new EmptyResultSet("Nessuna carta di debito con questo numero", Response.Status.METHOD_NOT_ALLOWED);
				String numero = rs.getString("numero"); 
				String iban =  rs.getString("iban");
				Date dataS = rs.getDate("data_di_scadenza"); 
				int cvv = rs.getInt("cvv");
				int pin = rs.getInt("pin"); 
				DaoContoCorrente daoCC = new DaoContoCorrente();
				ContoCorrente cc = daoCC.getOne(rs.getString("iban_conto_corrente")).get();
				CartaDiDebito c = new CartaDiDebito(numero, iban, dataS, cvv, pin, cc);
				return c;
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
				}if( ps != null ) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}if( rs != null ) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		return futureCartaDebito;
	}

	/**
	 * Ritorna un oggetto di tipo Future<List<CaratDiDebito>> contenente tutte le carte di debito associate ad un conto corrente.
	 */
	@Override
	public Future<List<CartaDiDebito>> getAll() {
		String query = "SELECT * FROM carta_di_debito";
		CompletableFuture<List<CartaDiDebito>> cards = CompletableFuture.supplyAsync(() -> {
			List<CartaDiDebito> result = new ArrayList<CartaDiDebito>();
			Connection conn = Database.getConnection();
			Statement s = null;
			ResultSet rs = null;
			try {
				s = conn.createStatement();
				rs = s.executeQuery(query);
				while( rs.next() ) {
					if(rs == null)
						throw new EmptyResultSet("Nessuna carta di debito con questo numero", Response.Status.METHOD_NOT_ALLOWED);
					CartaDiDebito c = getOne(rs.getString("numero")).get();
					result.add(c);
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
				}if( s != null ) {
					try {
						s.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}if( rs != null ) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		return cards;
	}

	/**
	 * Inserisce all'interno del database una carta di debito. Ritorna un oggetto di tipo Future<CartaDiDebito>.
	 */
	@Override
	public Future<CartaDiDebito> insert(CartaDiDebito element) {
		String query = "insert into carta_di_debito(numero, iban, data_di_scadenza, cvv, pin, numero_conto_corrente)" + 
				"values (?,?,?,?,?,?)";
		CompletableFuture.runAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				if(element == null)
					throw new InputValidationException("", Response.Status.METHOD_NOT_ALLOWED);
				ps = conn.prepareStatement(query);
				rs = ps.executeQuery();
				rs.next();

				if(rs == null)
					throw new EmptyResultSet("Nessuna carta di debito trovata", Response.Status.METHOD_NOT_ALLOWED);

				String numero = rs.getString("numero"); 
				String iban =  rs.getString("iban");
				Date dataS = rs.getDate("data_di_scadenza"); 
				int cvv = rs.getInt("cvv");
				int pin = rs.getInt("pin"); 
				DaoContoCorrente daoCC = new DaoContoCorrente();
				ContoCorrente cc = daoCC.getOne(rs.getString("iban_conto_corrente")).get();
				ps.setString(1, numero);
				ps.setString(2, iban);
				ps.setDate(3, dataS);
				ps.setInt(4, cvv);
				ps.setInt(5, pin);
				ps.setObject(6, cc);
				ps.executeUpdate();
			} catch (SQLException | InterruptedException | ExecutionException | InputValidationException | EmptyResultSet e) {
				e.printStackTrace();
			}finally {
				if( conn != null ) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}if( ps != null ) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}if( rs != null ) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
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
		String query = "DELETE FROM carta_di_debito WHERE numero = ?";
		CompletableFuture<Integer> del = CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			try {
				if(primaryKey == null || primaryKey.isBlank())
					throw new InputValidationException("Numero carta di debito", Response.Status.METHOD_NOT_ALLOWED);
				if(primaryKey.length() != 16)
					throw new InputValidationException("Numero carta di debito non valido. Caratteri richiesti 16, inseriti: " + primaryKey.length(), Response.Status.METHOD_NOT_ALLOWED);
				if(!primaryKey.matches("[\\d]*")) 
					throw new InputValidationException("Formato numero carta di debito errato.", Response.Status.METHOD_NOT_ALLOWED);
				ps = conn.prepareStatement(query);
				ps.setString(1, primaryKey);
				return ps.executeUpdate();
			} catch (SQLException | InputValidationException e) {
				e.printStackTrace();
				return 0;
			}finally{
				if( conn != null ) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}if( ps != null ) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		return del;
	}

	/**
	 * Modifica una carta di debito presente all'interno del database. Ritorna un oggetto di tipo Future<CartaDiDebito>.
	 */
	@Override
	public Future<CartaDiDebito> update(CartaDiDebito element) {
		CompletableFuture<CartaDiDebito> res = CompletableFuture.supplyAsync(() -> {
			try {
				if(element == null)
					throw new InputValidationException("", Response.Status.METHOD_NOT_ALLOWED);
				delete(element.getNumero());
				return insert(element).get();
			} catch (InterruptedException | ExecutionException | InputValidationException e) {
				e.printStackTrace();
				return null;
			}

		});
		return res;
	}

}
