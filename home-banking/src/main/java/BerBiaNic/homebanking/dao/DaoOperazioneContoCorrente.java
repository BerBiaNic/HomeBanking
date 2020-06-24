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
import BerBiaNic.homebanking.entity.ContoCorrente;
import BerBiaNic.homebanking.entity.OperazioneContoCorrente;
import BerBiaNic.homebanking.exceptions.EmptyResultSet;
import BerBiaNic.homebanking.exceptions.InputValidationException;

public class DaoOperazioneContoCorrente implements Dao<OperazioneContoCorrente,Integer>{

	@Override
	public Future<OperazioneContoCorrente> getOne(Integer primaryKey) {
		String query ="SELECT * FROM operazione_conto_corrente WHERE id = ?";
		CompletableFuture<OperazioneContoCorrente> operazione = CompletableFuture.supplyAsync(() ->{
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				if(primaryKey < 0)
					throw new InputValidationException("Id Operazione carta di debito", Response.Status.METHOD_NOT_ALLOWED);
				ps = conn.prepareStatement(query);
				ps.setInt(1, primaryKey);
				rs = ps.executeQuery();
				if(rs == null)
					throw new EmptyResultSet("Nessuna operazione trovata con questo id", Response.Status.METHOD_NOT_ALLOWED);
				rs.next();

				DaoContoCorrente daoContoCorrente = new DaoContoCorrente();
				ContoCorrente conto = daoContoCorrente.getOne(rs.getString("iban_proprietario")).get();

				int id = rs.getInt("id");
				Date data = rs.getDate("data");
				double importo = rs.getDouble("importo");
				String tipologia = rs.getString("tipologia");
				String ibanBeneficiario = rs.getString("iban_beneficiario");

				OperazioneContoCorrente op = new OperazioneContoCorrente(id, data, importo, tipologia, conto, ibanBeneficiario);
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
	public Future<List<OperazioneContoCorrente>> getAll() {
		String query = "SELECT * FROM operazione_carta_debito";
		CompletableFuture<List<OperazioneContoCorrente>> operazioni = CompletableFuture.supplyAsync(() -> {
			List<OperazioneContoCorrente> result = new ArrayList<>();
			ResultSet rs = null;
			Statement s = null;
			Connection conn = Database.getConnection();
			try {
				s = conn.createStatement();
				rs = s.executeQuery(query);	
				if(rs == null)
					throw new EmptyResultSet("Nessuna operazione trovata con questo id", Response.Status.METHOD_NOT_ALLOWED);
				while(rs.next()) {

					OperazioneContoCorrente op = getOne(rs.getInt("id")).get();
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

	@Override
	public Future<OperazioneContoCorrente> insert(OperazioneContoCorrente element) {
		String query = "INSERT INTO operazione_conto_corrente(id, data, importo, tipologia, iban_proprietario, iban_beneficiario)" +
				"VALUES(?,?,?,?,?,?)";
		CompletableFuture.runAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			try {
				if(element == null)
					throw new InputValidationException("", Response.Status.METHOD_NOT_ALLOWED);
				ps = conn.prepareStatement(query);
				ps.setInt(1, element.getId());
				ps.setDate(2, element.getData());
				ps.setDouble(3, element.getImporto());
				ps.setString(4, element.getTipologia());
				ps.setString(5, element.getConto_corrente_proprietario().getIban());
				ps.setString(6, element.getConto_corrente_destinatario());
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

	@Override
	public Future<Integer> delete(Integer primaryKey) {
		String query = "DELETE FROM operazione_conto_corrente WHERE id = ?";
		CompletableFuture<Integer> del= CompletableFuture.supplyAsync(() -> {
			Connection conn = Database.getConnection();
			PreparedStatement ps = null;
			try {
				if(primaryKey < 0)
					throw new InputValidationException("Id Operazione carta di debito", Response.Status.METHOD_NOT_ALLOWED);
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

	@Override
	public Future<OperazioneContoCorrente> update(OperazioneContoCorrente element) {
		try {
			if(element == null)
				throw new InputValidationException("", Response.Status.METHOD_NOT_ALLOWED);
			CompletableFuture<OperazioneContoCorrente> res = CompletableFuture.supplyAsync(() ->{
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


