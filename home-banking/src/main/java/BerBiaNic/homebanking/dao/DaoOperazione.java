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

public class DaoOperazione implements Dao<Operazione, Integer> {

	@Override
	public Future<Operazione> getOne(Integer primaryKey) {
		return null;
	}

	@Override
	public Future<List<Operazione>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Operazione> insert(Operazione element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Integer> delete(Integer primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Operazione> update(Operazione element) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
