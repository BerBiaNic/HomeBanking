package BerBiaNic.homebanking.dao;

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

public class DaoCartaDiDebito implements Dao<CartaDiDebito, String> {

	@Override
	public Future<CartaDiDebito> getOne(String primaryKey) {
		String query = "SELECT * FROM carta_di_debito WHERE numero = ?";
		CompletableFuture<CartaDiDebito> futureCartaDebito = CompletableFuture.supplyAsync(() -> {

			try {
				PreparedStatement ps = Database.getConnection().prepareStatement(query);
				ps.setString(1, primaryKey);
				ResultSet rs = ps.executeQuery();
				rs.next();
				String numero = rs.getString("numero"); 
				String iban =  rs.getString("iban");
				Date dataS = rs.getDate("data_di_scadenza"); 
				int cvv = rs.getInt("cvv");
				int pin = rs.getInt("pin"); 
				DaoContoCorrente daoCC = new DaoContoCorrente();
				ContoCorrente cc = daoCC.getOne(rs.getInt("numero_conto_corrente")).get();
				CartaDiDebito c = new CartaDiDebito(numero, iban, dataS, cvv, pin, cc);
				return c;
			} catch (SQLException | InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return null;
			}
		});
		return futureCartaDebito;
	}

	@Override
	public Future<List<CartaDiDebito>> getAll() {
		String query = "SELECT * FROM carta_di_debito";
		CompletableFuture<List<CartaDiDebito>> cards = CompletableFuture.supplyAsync(() -> {
			List<CartaDiDebito> result = new ArrayList<CartaDiDebito>();
			try {
				Statement s = Database.getConnection().createStatement();
				ResultSet rs = s.executeQuery(query);
				while( rs.next() ) {
					CartaDiDebito c = getOne(rs.getString("numero")).get();
					result.add(c);
				}
				return result;
			} catch (SQLException | InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return null;
			}
		});
		return cards;
	}

	@Override
	public Future<CartaDiDebito> insert(CartaDiDebito element) {
		String query = "insert into carta_di_debito(numero, iban, data_di_scadenza, cvv, pin, numero_conto_corrente)" + 
				"values (?,?,?,?,?,?)";
		CompletableFuture.runAsync(() -> {

			try {
				PreparedStatement ps = Database.getConnection().prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				rs.next();
				String numero = rs.getString("numero"); 
				String iban =  rs.getString("iban");
				Date dataS = rs.getDate("data_di_scadenza"); 
				int cvv = rs.getInt("cvv");
				int pin = rs.getInt("pin"); 
				DaoContoCorrente daoCC = new DaoContoCorrente();
				ContoCorrente cc = daoCC.getOne(rs.getInt("numero_conto_corrente")).get();
				ps.setString(1, numero);
				ps.setString(2, iban);
				ps.setDate(3, dataS);
				ps.setInt(4, cvv);
				ps.setInt(5, pin);
				ps.setObject(6, cc);
				ps.executeUpdate();
			} catch (SQLException | InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
		return getOne(element.getNumero());
	}

	@Override
	public Future<Integer> delete(String primaryKey) {
		String query = "DELETE FROM carta_di_credito WHERE numero = ?";
		CompletableFuture<Integer> del = CompletableFuture.supplyAsync(() -> {
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
