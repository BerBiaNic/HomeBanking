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

import BerBiaNic.homebanking.db.Database;
import BerBiaNic.homebanking.entity.*;
import BerBiaNic.homebanking.exceptions.InputValidationException;

public class DaoCartaDiDebito implements Dao<CartaDiDebito, String> {

	@Override
	public Future<CartaDiDebito> getOne(String primaryKey) {
		String query = "SELECT * FROM carta_di_debito WHERE numero = ?";
		CompletableFuture<CartaDiDebito> futureCartaDebito = CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, primaryKey);
				rs = ps.executeQuery();
				rs.next();
				String numero = rs.getString("numero"); 
				String iban =  rs.getString("iban");
				Date dataS = rs.getDate("data_di_scadenza"); 
				int cvv = rs.getInt("cvv");
				int pin = rs.getInt("pin"); 
				DaoContoCorrente daoCC = new DaoContoCorrente();
				ContoCorrente cc = daoCC.getOne(rs.getString("numero_conto_corrente")).get();
				CartaDiDebito c = new CartaDiDebito(numero, iban, dataS, cvv, pin, cc);
				return c;
			} catch (SQLException | InterruptedException | ExecutionException e) {
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
					CartaDiDebito c = getOne(rs.getString("numero")).get();
					result.add(c);
				}
				return result;
			} catch (SQLException | InterruptedException | ExecutionException e) {
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

	@Override
	public Future<CartaDiDebito> insert(CartaDiDebito element) {
		String query = "insert into carta_di_debito(numero, iban, data_di_scadenza, cvv, pin, numero_conto_corrente)" + 
				"values (?,?,?,?,?,?)";
		CompletableFuture.runAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(query);
				rs = ps.executeQuery();
				rs.next();
				String numero = rs.getString("numero"); 
				String iban =  rs.getString("iban");
				Date dataS = rs.getDate("data_di_scadenza"); 
				int cvv = rs.getInt("cvv");
				int pin = rs.getInt("pin"); 
				DaoContoCorrente daoCC = new DaoContoCorrente();
				ContoCorrente cc = daoCC.getOne(rs.getString("numero_conto_corrente")).get();
				ps.setString(1, numero);
				ps.setString(2, iban);
				ps.setDate(3, dataS);
				ps.setInt(4, cvv);
				ps.setInt(5, pin);
				ps.setObject(6, cc);
				ps.executeUpdate();
			} catch (SQLException | InterruptedException | ExecutionException e) {
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

	@Override
	public Future<Integer> delete(String primaryKey) {
		String query = "DELETE FROM carta_di_debito WHERE numero = ?";
		CompletableFuture<Integer> del = CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, primaryKey);
				return ps.executeUpdate();
			} catch (SQLException e) {
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

	@Override
	public Future<CartaDiDebito> update(CartaDiDebito element) {
		CompletableFuture<CartaDiDebito> res = CompletableFuture.supplyAsync(() -> {
			try {
				delete(element.getNumero());
				return insert(element).get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return null;
			}

		});
		return res;
	}
}
