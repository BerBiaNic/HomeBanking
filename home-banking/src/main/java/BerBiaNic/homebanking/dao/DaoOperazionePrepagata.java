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
import BerBiaNic.homebanking.entity.CartaPrepagata;
import BerBiaNic.homebanking.entity.OperazionePrepagata;

public class DaoOperazionePrepagata implements Dao<OperazionePrepagata,Integer> {

	@Override
	public Future<OperazionePrepagata> getOne(Integer primaryKey) {
		String query = "SELECT * FROM operazione_prepagata WHERE id = ?";
		CompletableFuture<OperazionePrepagata> operazione = CompletableFuture.supplyAsync(() -> {
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection conn = Database.getConnection();
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, primaryKey);
				rs = ps.executeQuery();
				rs.next();			

				DaoCartaPrepagata daoPrepagata = new DaoCartaPrepagata();
				CartaPrepagata carta = daoPrepagata.getOne(rs.getString("numero_carta")).get();

				int id = rs.getInt("id");
				Date data = rs.getDate("data");
				double importo = rs.getDouble("importo");
				String tipologia = rs.getString("tipologia");
				String destinatario = rs.getString("destinatario");


				OperazionePrepagata op = new OperazionePrepagata(id, data, importo, tipologia, destinatario, carta);
				return op;
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
				while(rs.next()) {
					OperazionePrepagata op = getOne(rs.getInt("id")).get();
					result.add(op);
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
		return operazioni;
	}

	@Override
	public Future<OperazionePrepagata> insert(OperazionePrepagata element) {
		String query = "INSERT INTO operazione_prepagata(id,data,importo,tipologia,destinatario,numero_carta)" +
				"VALUES (?,?,?,?,?,?)";
		CompletableFuture.runAsync(() -> {
			PreparedStatement ps = null;
			Connection conn = Database.getConnection();
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, element.getId());
				ps.setDate(2, element.getData());
				ps.setDouble(3, element.getImporto());
				ps.setString(4, element.getTipologia());
				ps.setString(5, element.getDestinatario());
				ps.setString(6, element.getCartaPrepagata().getNumero());

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
		return getOne(element.getId());
	}

	@Override
	public Future<Integer> delete(Integer primaryKey) {
		String query = "DELETE FROM operazione_prepagata WHERE id = ?";
		CompletableFuture<Integer> del= CompletableFuture.supplyAsync(() -> {
			PreparedStatement ps = null;
			Connection conn = Database.getConnection();
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, primaryKey);
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
	public Future<OperazionePrepagata> update(OperazionePrepagata element) {
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
	}
}
