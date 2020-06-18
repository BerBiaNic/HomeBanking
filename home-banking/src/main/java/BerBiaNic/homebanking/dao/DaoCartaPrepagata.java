package BerBiaNic.homebanking.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import BerBiaNic.homebanking.db.Database;
import BerBiaNic.homebanking.entity.Account;
import BerBiaNic.homebanking.entity.CartaPrepagata;
import BerBiaNic.homebanking.entity.Cliente;

public class DaoCartaPrepagata implements Dao<CartaPrepagata,String>{

	@Override
	public Future<CartaPrepagata> getOne(String primaryKey) {
		String query = "SELECT * FROM carta_prepagata WHERE numero = ?";
		CompletableFuture<CartaPrepagata> carta = CompletableFuture.supplyAsync(() -> {

			try {
				PreparedStatement ps = Database.getConnection().prepareStatement(query);
				ps.setString(1, primaryKey);
				ResultSet rs = ps.executeQuery();
				rs.next();
				
				DaoAccount daoAccount = new DaoAccount();
				
				Account a = daoAccount.getOne(rs.getInt("id")).get();
				
				String numero = rs.getString("numero");
				double saldoContabile = rs.getDouble("saldo_contabile");
				double saldoDisponibile = rs.getDouble("saldo_disponibile");
				Date dataScadenza = rs.getDate("data_di_scadenza");
				int cvv = rs.getInt("cvv");
				int pin = rs.getInt("pin");
				
				CartaPrepagata c = new CartaPrepagata(numero,saldoContabile,saldoDisponibile,dataScadenza,cvv,pin,a);
				return c;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		});
		return carta;
	}

	@Override
	public Future<List<CartaPrepagata>> getAll() {
		String query = "SELECT * FROM carta_prepagata";
		CompletableFuture<List<CartaPrepagata>> carte = CompletableFuture.supplyAsync(() -> {
			List<CartaPrepagata> result = new ArrayList<CartaPrepagata>();
			try {
				Statement s = Database.getConnection().createStatement();
				ResultSet rs = s.executeQuery(query);	
				while(rs.next()) {
					CartaPrepagata carta = getOne(rs.getString("numero")).get();
					result.add(carta);
				}		
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		});
		return carte;
	}

	@Override
	public Future<CartaPrepagata> insert(CartaPrepagata element) {
		String query = "INSERT INTO carta_prepagata(numero, saldo_contabile, saldo_disponibile, data_di_scadenza, cvv, pin, id)" +
				"VALUES(?,?,?,?,?,?,?)";
		CompletableFuture.runAsync(() -> {
			try {
				PreparedStatement ps = Database.getConnection().prepareStatement(query);
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
			}
		});
		return getOne(element.getNumero());
	}

	@Override
	public Future<Integer> delete(String primaryKey) {
		String query = "DELETE FROM carta_prepagata WHERE numero = ?";
		CompletableFuture<Integer> del= CompletableFuture.supplyAsync(() -> {

			try {
				PreparedStatement ps = Database.getConnection().prepareStatement(query);
				ps.setString(1, primaryKey);
				return ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		});
		return del;
	}

	@Override
	public Future<CartaPrepagata> update(CartaPrepagata element) {
		CompletableFuture<CartaPrepagata> res = CompletableFuture.supplyAsync(() ->{
			try {
				delete(element.getNumero());
				return insert(element).get();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		});
		
		return res;
	}

	
}
