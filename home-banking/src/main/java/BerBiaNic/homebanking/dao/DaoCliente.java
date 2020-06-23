package BerBiaNic.homebanking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import BerBiaNic.homebanking.db.Database;
import BerBiaNic.homebanking.entity.Cliente;
import BerBiaNic.homebanking.exceptions.InputValidationException;
/**
 * 
 * @authors Antonino Bertuccio, Giuseppe Bianchino, Giovanni Nicotera
 *
 */
public class DaoCliente implements Dao <Cliente,String> {

	/**
	 * Cerca tramite codice fiscale un cliente presente all'interno del database. Ritorna un oggetto di tipo Future<Cliente>. 
	 */
	@Override
	public Future<Cliente> getOne(String primaryKey) {
		String query = "SELECT * FROM cliente WHERE codice_fiscale = ?";
		CompletableFuture<Cliente> cliente = CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, primaryKey);
				rs = ps.executeQuery();
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
			} catch (SQLException | InputValidationException e) {
				e.printStackTrace();
				return null;
			} 			finally {
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
		return cliente;
	}

	/**
	 * Ritorna un oggetto di tipo Future<List<Cliente>> contenente tutti i clienti presenti nel database.
	 */
	@Override
	public Future<List<Cliente>> getAll() {
		String query = "SELECT * FROM cliente";
		CompletableFuture<List<Cliente>> clients = CompletableFuture.supplyAsync(() -> {
			List<Cliente> result = new ArrayList<Cliente>();
			
			Connection conn = Database.getConnection();
			Statement s = null;
			ResultSet rs = null;
			
			try {
				s = conn.createStatement();
				rs = s.executeQuery(query);
				while( rs.next() ) {
					Cliente c = getOne(rs.getString("codice_fiscale")).get();
					result.add(c);
				}
				return result;
			} catch (SQLException | InterruptedException | ExecutionException e) {
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
		return clients;
	}

	/**
	 * Inserisce all'interno del database un cliente. Ritorna un oggetto di tipo Future<Cliente>.
	 */
	@Override
	public Future<Cliente> insert(Cliente element) {
		String query = "insert into cliente(codice_fiscale, cognome, nome, citta_di_nascita, data_di_nascita, numero_di_telefono, indirizzo_di_residenza, citta_di_residenza)" + 
				"values (?,?,?,?,?,?,?,?)";
		Connection conn = Database.getConnection();
		CompletableFuture.runAsync(() -> {
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);
				
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
				System.out.println("Registrato");
			} catch (SQLException e) {
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
		return getOne(element.getCodiceFiscale());
	}

	/**
	 * Elimina dal database un cliente cercandolo tramite codice fiscale. Ritorna un oggetto di tipo Future<Integer>.
	 */
	@Override
	public Future<Integer> delete(String primaryKey) {
		String query = "DELETE FROM cliente WHERE codice_fiscale = ?";
		
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
	 * Modifica un cliente presente all'interno del database. Ritorna un oggetto di tipo Future<Cliente>.
	 */
	@Override
	public Future<Cliente> update(Cliente element) {
		try {
			CompletableFuture<Cliente> res = CompletableFuture.supplyAsync(() -> {
				return delete(element.getCodiceFiscale());

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
