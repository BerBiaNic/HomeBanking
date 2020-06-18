package BerBiaNic.homebanking.main;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import BerBiaNic.homebanking.dao.Dao;
import BerBiaNic.homebanking.dao.DaoCartaPrepagata;
import BerBiaNic.homebanking.dao.DaoCliente;
import BerBiaNic.homebanking.entity.Account;
import BerBiaNic.homebanking.entity.CartaPrepagata;
import BerBiaNic.homebanking.entity.Cliente;

public class CartaPrepagataTest {

	public CartaPrepagataTest() {}
	
	public void test() throws InterruptedException, ExecutionException {
		
		Dao<CartaPrepagata,String> daoCarta = new DaoCartaPrepagata();
		
		Cliente clienteAdd = new Cliente("BNCCHR99H63G113Y", "Bianchi", "Chiara", "Oristano", Date.valueOf(LocalDate.of(1999, 06, 23)), "0310 0012114", "Via Francesco Girardi, 96", "SALERNO");
		Account account = new Account(clienteAdd,12365, "ottimax", "123Stella", "prova@gmail.com", 12548, "iphone");
		
		System.out.println("------------------------------- A G G I U N G I		C A R T A -------------------------------\n");
		CartaPrepagata cartaTmp = new CartaPrepagata("1234512412653633", 100d, 100d, Date.valueOf(LocalDate.of(2019, 06, 23)), 000, 12456,account);
		Future<CartaPrepagata> futureAddCard = daoCarta.insert(cartaTmp);
		System.out.println("carta aggiunta");
		
		System.out.println("------------------------------- L I S T A		C A R T E -------------------------------\n");
		Future<List<CartaPrepagata>> futureCards = daoCarta.getAll();
		List<CartaPrepagata> cards = futureCards.get();
		for(CartaPrepagata c: cards) {
			System.out.println(c);
			System.out.println();
		}
		
		System.out.println("------------------------------- S I N G O L A		C A R T A -------------------------------\n");
		Future<CartaPrepagata> futureCard2 = daoCarta.getOne("1234512412653633");
		System.out.println(futureCard2);
		
		
		

		
		

		
		
		System.out.println("------------------------------- E L I M I N A		C A R T A -------------------------------\n");
		Future<Integer> futureDeleteCard = daoCarta.delete(cartaTmp.getNumero());
		if( futureDeleteCard.get() == 1 ) {
			System.out.println("Carta eliminata");
		}else
			System.out.println("Nessuna carta da eliminare!");

	}
}
