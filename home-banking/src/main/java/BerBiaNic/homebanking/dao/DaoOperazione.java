package BerBiaNic.homebanking.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import BerBiaNic.homebanking.db.Database;
import BerBiaNic.homebanking.entity.*;

public class DaoOperazione implements Dao<OperazioneCartaDebito, Integer> {

	@Override
	public Future<OperazioneCartaDebito> getOne(Integer primaryKey) {
		return null;
	}

	@Override
	public Future<List<OperazioneCartaDebito>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<OperazioneCartaDebito> insert(OperazioneCartaDebito element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Integer> delete(Integer primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<OperazioneCartaDebito> update(OperazioneCartaDebito element) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
