package BerBiaNic.homebanking.main;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import BerBiaNic.homebanking.dao.Dao;
import BerBiaNic.homebanking.dao.DaoCartaPrepagata;
import BerBiaNic.homebanking.dao.DaoCliente;
import BerBiaNic.homebanking.db.Database;
import BerBiaNic.homebanking.entity.Account;
import BerBiaNic.homebanking.entity.CartaPrepagata;
import BerBiaNic.homebanking.entity.Cliente;
import BerBiaNic.homebanking.exceptions.InputValidationException;

public class CartaPrepagataTest {

	public CartaPrepagataTest() {}

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException, SQLException, InputValidationException {

		Database db = new Database();

		Dao<CartaPrepagata,String> daoCarta = new DaoCartaPrepagata();

		DaoCliente dc = new DaoCliente();
		Cliente cliente = dc.getOne("SSSDRA50A13C842B").get();
		Account a = new Account(2, "sossininoad", "Sss456", "sossinidario@libero.it", 2991537, "hp-13664ds, asusZenfone-9965ac", cliente);

		System.out.println("------------------------------- A G G I U N G I		C A R T A -------------------------------\n");
		CartaPrepagata cartaTmp = new CartaPrepagata("1234512412653633", 100d, 100d, Date.valueOf(LocalDate.of(2019, 06, 23)), 000, 12456,a);
		Future<CartaPrepagata> futureAddCard = daoCarta.insert(cartaTmp);
		if(futureAddCard!=null)
			System.out.println("carta aggiunta");

		System.out.println("------------------------------- L I S T A		C A R T E -------------------------------\n");
		Future<List<CartaPrepagata>> futureCards = daoCarta.getAll();
		List<CartaPrepagata> cards = new ArrayList<>(futureCards.get());
		for(CartaPrepagata c: cards) {
			System.out.println(c);
			System.out.println();
		}

		System.out.println("------------------------------- S I N G O L A		C A R T A -------------------------------\n");
		Future<CartaPrepagata> futureCard2 = daoCarta.getOne("1234512412653633");
		System.out.println(futureCard2);









//		System.out.println("------------------------------- E L I M I N A		C A R T A -------------------------------\n");
//		Future<Integer> futureDeleteCard = daoCarta.delete(cartaTmp.getNumero());
//		if( futureDeleteCard.get() == 1 ) {
//			System.out.println("Carta eliminata");
//		}else
//			System.out.println("Nessuna carta da eliminare!");
		


	}
}

