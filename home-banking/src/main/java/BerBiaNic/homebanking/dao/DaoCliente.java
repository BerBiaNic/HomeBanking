package BerBiaNic.homebanking.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
				String cf = rs.getString(1); 
				String cm =  rs.getString("cognome");
				String nm = rs.getString("nome"); 
				String ctt = rs.getString("citta_di_nascita");
				Date dn = rs.getDate("data_di_nascita"); 
				int nt = rs.getInt("numero_di_telefono");
				String ind =  rs.getString("indirizzo_di_residenza");
				String cr = rs.getString("citta_di_residenza");
				Cliente c = new Cliente(cf, cm, nm, ctt, dn, nt, ind, cr);
				System.out.println(cf +", "+ cm +", "+ nm+", "+ ctt+", "+ dn+", "+ nt+", "+ ind+", "+ cr);
				
				System.out.println(c);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Cliente> insert(Cliente element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Integer> delete(String primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Cliente> update(Cliente element) {
		// TODO Auto-generated method stub
		return null;
	}



}
