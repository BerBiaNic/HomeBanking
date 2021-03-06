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
import BerBiaNic.homebanking.entity.CartaPrepagata;
import BerBiaNic.homebanking.entity.OperazionePrepagata;
import BerBiaNic.homebanking.exceptions.EmptyResultSet;
import BerBiaNic.homebanking.exceptions.InputValidationException;
/**
 * 
 * @authors Antonino Bertuccio, Giuseppe Bianchino, Giovanni Nicotera
 *
 */
public class DaoOperazionePrepagata implements Dao<OperazionePrepagata,Integer> {

	/**
	 * Cerca tramite id un'operazione eseguita con la carta prepagata presente all'interno del database. Ritorna un oggetto di tipo Future<OperazionePrepagata>. 
	 */
	@Override
	public Future<OperazionePrepagata> getOne(Integer primaryKey) {
		String query = "SELECT * FROM operazione_prepagata WHERE id = ?";
		CompletableFuture<OperazionePrepagata> operazione = CompletableFuture.supplyAsync(() -> {
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection conn = Database.getConnection();
			try {
				if(primaryKey < 0)
					throw new InputValidationException("Id operazione carta prepagata", Response.Status.METHOD_NOT_ALLOWED);

				ps = conn.prepareStatement(query);
				ps.setInt(1, primaryKey);
				rs = ps.executeQuery();
				if(rs == null)
					throw new EmptyResultSet("Nessuna operazione trovata con questo id", Response.Status.METHOD_NOT_ALLOWED);
				rs.next();			

				DaoCartaPrepagata daoPrepagata = new DaoCartaPrepagata();
				CartaPrepagata carta = daoPrepagata.getOne(rs.getString("numero_carta")).get();

				int id = rs.getInt("id");
				Date data = rs.getDate("data");
				double importo = rs.getDouble("importo");
				String tipologia = rs.getString("tipologia");
				String destinatario = rs.getString("destinatario");


				OperazionePrepagata op = new OperazionePrepagata(id, data, importo, tipologia, carta, destinatario);
				return op;
			} catch (SQLException | InterruptedException | ExecutionException | InputValidationException | EmptyResultSet e) {
				e.printStackTrace();
				return null;
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
				if(rs != null)
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		});
		return operazione;
	}

	/**
	 * Ritorna un oggetto di tipo Future<List<OperazionePrepagata>> contenente tutte le operazioniassociate ad una carta prepagata.
	 */
	@Override
	public Future<List<OperazionePrepagata>> getAll() {
		String query = "SELECT * FROM operazione_prepagata";
		CompletableFuture<List<OperazionePrepagata>> operazioni = CompletableFuture.supplyAsync(() -> {
			List<OperazionePrepagata> result = new ArrayList<OperazionePrepagata>();
			Statement s = null;
			ResultSet rs = null;
			Connection conn = Database.getConnection();
			try {
				s = conn.createStatement();
				rs = s.executeQuery(query);	
				if(rs == null)
					throw new EmptyResultSet("Nessuna operazione trovata con questo id", Response.Status.METHOD_NOT_ALLOWED);
				while(rs.next()) {

					OperazionePrepagata op = getOne(rs.getInt("id")).get();
					result.add(op);
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
		return operazioni;
	}

	/**
	 * Inserisce all'interno del database un'operazione eseguita con la carta prepagata. Ritorna un oggetto di tipo Future<OperazionePrepagata>.
	 */
	@Override
	public Future<OperazionePrepagata> insert(OperazionePrepagata element) {
		String query = "INSERT INTO operazione_prepagata(id,data,importo,tipologia,destinatario,numero_carta)" +
				"VALUES (?,?,?,?,?,?)";
		CompletableFuture.runAsync(() -> {
			PreparedStatement ps = null;
			Connection conn = Database.getConnection();
			try {
				if(element == null)
					throw new InputValidationException("", Response.Status.METHOD_NOT_ALLOWED);
				ps = conn.prepareStatement(query);
				ps.setInt(1, element.getId());
				ps.setDate(2, element.getData());
				ps.setDouble(3, element.getImporto());
				ps.setString(4, element.getTipologia());
				ps.setString(5, element.getDestinatario());
				ps.setString(6, element.getCartaPrepagata().getNumero());

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
		return getOne(element.getId());
	}

	/**
	 * Elimina dal database un'operazione eseguita con la carta prepagata cercandola tramite id. Ritorna un oggetto di tipo Future<Integer>.
	 */
	@Override
	public Future<Integer> delete(Integer primaryKey) {
		String query = "DELETE FROM operazione_prepagata WHERE id = ?";
		CompletableFuture<Integer> del= CompletableFuture.supplyAsync(() -> {
			PreparedStatement ps = null;
			Connection conn = Database.getConnection();
			try {
				if(primaryKey < 0)
					throw new InputValidationException("Id operazione carta prepagata", Response.Status.METHOD_NOT_ALLOWED);
				ps = conn.prepareStatement(query);
				ps.setInt(1, primaryKey);
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
	 * Modifica un'operazione prepagata presente all'interno del database. Ritorna un oggetto di tipo Future<OperazionePrepagata>.
	 */
	@Override
	public Future<OperazionePrepagata> update(OperazionePrepagata element) {
		try {
			if(element == null)
				throw new InputValidationException("", Response.Status.METHOD_NOT_ALLOWED);
			CompletableFuture<OperazionePrepagata> res = CompletableFuture.supplyAsync(() ->{
				return delete(element.getId());
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
