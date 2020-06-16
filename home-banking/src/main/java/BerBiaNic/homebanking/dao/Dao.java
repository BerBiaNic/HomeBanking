package BerBiaNic.homebanking.dao;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Dao stands for Data Access Object.
 *
 * @param <E> type of the entity class
 * @param <K> type of the primary key
 */
public interface Dao<E, K> {

	public Future<E> getOne(K primaryKey);

	public Future<List<E>> getAll();
    
	public Future<E> insert(E element);

	public Future<Integer> delete(K primaryKey);

	public Future<E> update(E element);
}
