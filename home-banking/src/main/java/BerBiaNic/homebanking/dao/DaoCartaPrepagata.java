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
import BerBiaNic.homebanking.entity.Account;
import BerBiaNic.homebanking.entity.CartaPrepagata;

public class DaoCartaPrepagata implements Dao<CartaPrepagata,String>{

	@Override
	public Future<CartaPrepagata> getOne(String primaryKey) {


		String query = "SELECT * FROM carta_prepagata WHERE numero = ?";
		CompletableFuture<CartaPrepagata> carta = CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {

				ps = conn.prepareStatement(query);
				ps.setString(1, primaryKey);
				rs = ps.executeQuery();
				rs.next();

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
			} catch (SQLException | InterruptedException | ExecutionException e) {
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
					CartaPrepagata carta = getOne(rs.getString("numero")).get();
					result.add(carta);
				}		
				return result;
			} catch (SQLException | InterruptedException | ExecutionException e) {
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

	@Override
	public Future<CartaPrepagata> insert(CartaPrepagata element) {
		String query = "INSERT INTO carta_prepagata(numero, saldo_contabile, saldo_disponibile, data_di_scadenza, cvv, pin, id_account)" +
				"VALUES(?,?,?,?,?,?,?)";
		CompletableFuture.runAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, element.getNumero());
				ps.setDouble(2, element.getSaldoContabile());
				ps.setDouble(3, element.getSaldoDisponibile());
				ps.setDate(4, element.getDataDiScadenza());
				ps.setInt(5, element.getCvv());
				ps.setInt(6, element.getPin());
				ps.setInt(7, element.getAccount().getId());
				ps.executeUpdate();
			} catch (SQLException e) {
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

	@Override
	public Future<Integer> delete(String primaryKey) {
		String query = "DELETE FROM carta_prepagata WHERE numero = ?";
		CompletableFuture<Integer> del= CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, primaryKey);
				return ps.executeUpdate();
			} catch (SQLException e) {
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

	@Override
	public Future<CartaPrepagata> update(CartaPrepagata element) {
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
	}


}
