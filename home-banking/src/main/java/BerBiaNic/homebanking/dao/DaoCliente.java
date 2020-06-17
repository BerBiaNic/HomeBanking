package BerBiaNic.homebanking.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import BerBiaNic.homebanking.db.Database;
import BerBiaNic.homebanking.entity.Cliente;

public class DaoCliente implements Dao <Cliente,String> {

	@Override
	public Future<Cliente> getOne(String primaryKey) {
		String query = "SELECT * FROM cliente WHERE codice_fiscale = ?";
		CompletableFuture<Cliente> cliente = CompletableFuture.supplyAsync(() -> {

			try {
				PreparedStatement ps = Database.getConnection().prepareStatement(query);
				ps.setString(1, primaryKey);
				ResultSet rs = ps.executeQuery();
				rs.next();
				String codiceF = rs.getString("codice_fiscale"); 
				String cognome =  rs.getString("cognome");
				String nome = rs.getString("nome"); 
				String cittaN = rs.getString("citta_di_nascita");
				Date dataN = rs.getDate("data_di_nascita"); 
				String numeroT = rs.getString("numero_di_telefono");
				String indirizzoR =  rs.getString("indirizzo_di_residenza");
				String cittaR = rs.getString("citta_di_residenza");
				Cliente c = new Cliente(codiceF, cognome, nome, cittaN, dataN, numeroT, indirizzoR, cittaR);
				return c;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		});
		return cliente;
	}

	@Override
	public Future<List<Cliente>> getAll() {
		String query = "SELECT * FROM cliente";
		CompletableFuture<List<Cliente>> clients = CompletableFuture.supplyAsync(() -> {
			List<Cliente> result = new ArrayList<Cliente>();
			try {
				Statement s = Database.getConnection().createStatement();
				ResultSet rs = s.executeQuery(query);
				while( rs.next() ) {
					String codiceF = rs.getString("codice_fiscale"); 
					String cognome =  rs.getString("cognome");
					String nome = rs.getString("nome"); 
					String cittaN = rs.getString("citta_di_nascita");
					Date dataN = rs.getDate("data_di_nascita"); 
					String numeroT = rs.getString("numero_di_telefono");
					String indirizzoR =  rs.getString("indirizzo_di_residenza");
					String cittaR = rs.getString("citta_di_residenza");
					Cliente c = new Cliente(codiceF, cognome, nome, cittaN, dataN, numeroT, indirizzoR, cittaR);
					result.add(c);
				}
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		});
		return clients;
	}

	@Override
	public Future<Cliente> insert(Cliente element) {
		String query = "insert into cliente(codice_fiscale, cognome, nome, citta_di_nascita, data_di_nascita, numero_di_telefono, indirizzo_di_residenza, citta_di_residenza)" + 
				"values (?,?,?,?,?,?,?,?)";
		CompletableFuture.runAsync(() -> {

			try {
				PreparedStatement ps = Database.getConnection().prepareStatement(query);
				String codiceF = element.getCodiceFiscale(); 
				String cognome = element.getCognome();
				String nome = element.getNome(); 
				String cittaN = element.getCittaDiNascita();
				Date dataN = element.getDataDinascita(); 
				String numeroT = element.getNumeroDiTelefono();
				String indirizzoR = element.getIndirizzoDiResidenza();
				String cittaR = element.getCittaDiResidenza();
				ps.setString(1, codiceF);
				ps.setString(2, cognome);
				ps.setString(3, nome);
				ps.setString(4, cittaN);
				ps.setDate(5, dataN);
				ps.setString(6, numeroT);
				ps.setString(7, indirizzoR);
				ps.setString(8, cittaR);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		return getOne(element.getCodiceFiscale());
	}

	@Override
	public Future<Integer> delete(String primaryKey) {
		String query = "DELETE FROM cliente WHERE codice_fiscale = ?";
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
	public Future<Cliente> update(Cliente element) {
		//		String query = "UPDATE cliente"
		//				+ 	   "SET codice_fiscale = ?, cognome = ?, nome = ?, citta_di_nascita = ?, data_di_nascita = ?,"
		//				+      "numero_di_telefono = ?, indirizzo_di_residenza = ?, citta_di_residenza = ?"
		//				+ 	   "WHERE codice_fiscale = ?";
		//		CompletableFuture.runAsync(() -> {
		//			try {
		//				PreparedStatement ps = Database.getConnection().prepareStatement(query);
		//				String codiceF = element.getCodiceFiscale(); 
		//				String cognome = element.getCognome();
		//				String nome = element.getNome(); 
		//				String cittaN = element.getCittaDiNascita();
		//				Date dataN = element.getDataDinascita(); 
		//				String numeroT = element.getNumeroDiTelefono();
		//				String indirizzoR = element.getIndirizzoDiResidenza();
		//				String cittaR = element.getCittaDiResidenza();
		//				ps.setString(1, codiceF);
		//				ps.setString(2, cognome);
		//				ps.setString(3, nome);
		//				ps.setString(4, cittaN);
		//				ps.setDate(5, dataN);
		//				ps.setString(6, numeroT);
		//				ps.setString(7, indirizzoR);
		//				ps.setString(8, cittaR);
		//				ps.setString(9, codiceF);
		//				ps.executeUpdate();
		//			} catch (SQLException e) {
		//				e.printStackTrace();
		//			}
		//		});
		return getOne(element.getCodiceFiscale());
	}



}
