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
import BerBiaNic.homebanking.entity.CartaDiDebito;
import BerBiaNic.homebanking.entity.CartaPrepagata;
import BerBiaNic.homebanking.entity.OperazioneCartaDebito;

public class DaoOperazioneDebito implements Dao<OperazioneCartaDebito,Integer>{

	@Override
	public Future<OperazioneCartaDebito> getOne(Integer primaryKey) {
		String query ="SELECT * FROM operazione_carta_debito WHERE id = ?";
		CompletableFuture<OperazioneCartaDebito> operazione = CompletableFuture.supplyAsync(() ->{
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, primaryKey);
				rs = ps.executeQuery();
				rs.next();
				
				DaoCartaDiDebito daoCarta = new DaoCartaDiDebito();
				CartaDiDebito carta = daoCarta.getOne(rs.getString("numero_carta_proprietario")).get();
				
				int id = rs.getInt("id");
				Date data = rs.getDate("data");
				double importo = rs.getDouble("importo");
				String tipologia = rs.getString("tipologia");
				String numeroCartaBeneficiario = rs.getString("numero_carta_beneficiario");
				
				OperazioneCartaDebito op = new OperazioneCartaDebito(id, data, importo, tipologia, carta, numeroCartaBeneficiario);
				return op;
			} catch (Exception e) {
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
	public Future<List<OperazioneCartaDebito>> getAll() {
		String query = "SELECT * FROM operazione_carta_debito";
		CompletableFuture<List<OperazioneCartaDebito>> operazioni = CompletableFuture.supplyAsync(() -> {
			List<OperazioneCartaDebito> result = new ArrayList<>();
			ResultSet rs = null;
			Statement s = null;
			Connection conn = Database.getConnection();
			try {
				s = conn.createStatement();
				rs = s.executeQuery(query);	
				while(rs.next()) {
					OperazioneCartaDebito op = getOne(rs.getInt("id")).get();
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
	public Future<OperazioneCartaDebito> insert(OperazioneCartaDebito element) {
		String query = "INSERT INTO operazione_carta_debito(id, data, importo, tipologia, numero_carta_proprietario, numero_carta_beneficiario)" +
				"VALUES(?,?,?,?,?,?)";
		CompletableFuture.runAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, element.getId());
				ps.setDate(2, element.getData());
				ps.setDouble(3, element.getImporto());
				ps.setString(4, element.getTipologia());
				ps.setString(5, element.getCarta_proprietario().getNumero());
				ps.setString(6, element.getCarta_beneficiario());
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
		String query = "DELETE FROM operazione_carta_debito WHERE id = ?";
		CompletableFuture<Integer> del= CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
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
	public Future<OperazioneCartaDebito> update(OperazioneCartaDebito element) {
		CompletableFuture<OperazioneCartaDebito> res = CompletableFuture.supplyAsync(() ->{
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

