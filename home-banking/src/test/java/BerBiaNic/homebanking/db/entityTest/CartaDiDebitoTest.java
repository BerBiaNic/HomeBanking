package BerBiaNic.homebanking.db.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import BerBiaNic.homebanking.entity.Account;
import BerBiaNic.homebanking.entity.CartaDiDebito;
import BerBiaNic.homebanking.entity.Cliente;
import BerBiaNic.homebanking.entity.ContoCorrente;
import BerBiaNic.homebanking.exceptions.InputValidationException;

public class CartaDiDebitoTest {

	@Test
	void test_notNullObjects() {
		String numero = "123456";
		String iban = "IT28W8000000292100645211151";
		Date data_di_scadenza = new Date(2025-05-31);
		int cvv = 695;
		int pin = 159357;
		ContoCorrente conto_corrente = null;
		CartaDiDebito cartaDiDebito = null;
		try {
			Cliente cliente = new Cliente("WYNBRC72L14D226V", "Wayne", "Bruce", "Gotham City", new Date(1939-05-22), "3209677458", "Villa Wayne, Gotham City", "Gotham City");
			Account account = new Account(12345, "bat_bruce123", "Joker#123", "brucewayne@wayneent.com", 25681531, "Bat-Computer", cliente);
			conto_corrente = new ContoCorrente(123456, "IT28W8000000292100645211151", 123586132.00, 123586132.00, account);
			cartaDiDebito = new CartaDiDebito(numero, iban, data_di_scadenza, cvv, pin, conto_corrente);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		Assert.assertNotNull(cartaDiDebito);
	}
	
	@Test
	void test_getNumero() {
		String numero = "123456";
		String iban = "IT28W8000000292100645211151";
		Date data_di_scadenza = new Date(2025-05-31);
		int cvv = 695;
		int pin = 159357;
		ContoCorrente conto_corrente = null;
		CartaDiDebito cartaDiDebito = null;
		try {
			Cliente cliente = new Cliente("WYNBRC72L14D226V", "Wayne", "Bruce", "Gotham City", new Date(1939-05-22), "3209677458", "Villa Wayne, Gotham City", "Gotham City");
			Account account = new Account(12345, "bat_bruce123", "Joker#123", "brucewayne@wayneent.com", 25681531, "Bat-Computer", cliente);
			conto_corrente = new ContoCorrente(123456, "IT28W8000000292100645211151", 123586132.00, 123586132.00, account);
			cartaDiDebito = new CartaDiDebito(numero, iban, data_di_scadenza, cvv, pin, conto_corrente);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		String numeroResult = cartaDiDebito.getNumero();
		assertEquals(numero, numeroResult, "Il numero della carta dev'essere uguale");
	}
	
	@Test
	void test_getIban() {
		String numero = "123456";
		String iban = "IT28W8000000292100645211151";
		Date data_di_scadenza = new Date(2025-05-31);
		int cvv = 695;
		int pin = 159357;
		ContoCorrente conto_corrente = null;
		CartaDiDebito cartaDiDebito = null;
		try {
			Cliente cliente = new Cliente("WYNBRC72L14D226V", "Wayne", "Bruce", "Gotham City", new Date(1939-05-22), "3209677458", "Villa Wayne, Gotham City", "Gotham City");
			Account account = new Account(12345, "bat_bruce123", "Joker#123", "brucewayne@wayneent.com", 25681531, "Bat-Computer", cliente);
			conto_corrente = new ContoCorrente(123456, "IT28W8000000292100645211151", 123586132.00, 123586132.00, account);
			cartaDiDebito = new CartaDiDebito(numero, iban, data_di_scadenza, cvv, pin, conto_corrente);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		String ibanResult = cartaDiDebito.getIban();
		assertEquals(iban, ibanResult, "Il codice IBAN della carta dev'essere uguale");
	}
	
	@Test
	void test_DataDiScadenza() {
		String numero = "123456";
		String iban = "IT28W8000000292100645211151";
		Date data_di_scadenza = new Date(2025-05-31);
		int cvv = 695;
		int pin = 159357;
		ContoCorrente conto_corrente = null;
		CartaDiDebito cartaDiDebito = null;
		try {
			Cliente cliente = new Cliente("WYNBRC72L14D226V", "Wayne", "Bruce", "Gotham City", new Date(1939-05-22), "3209677458", "Villa Wayne, Gotham City", "Gotham City");
			Account account = new Account(12345, "bat_bruce123", "Joker#123", "brucewayne@wayneent.com", 25681531, "Bat-Computer", cliente);
			conto_corrente = new ContoCorrente(123456, "IT28W8000000292100645211151", 123586132.00, 123586132.00, account);
			cartaDiDebito = new CartaDiDebito(numero, iban, data_di_scadenza, cvv, pin, conto_corrente);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		Date data_di_scadenzaResult = new Date(2025-05-31);
		assertEquals(data_di_scadenza, data_di_scadenzaResult, "La data di scadenza della carta dev'essere uguale");
	}
	
	@Test
	void test_getCvv() {
		String numero = "123456";
		String iban = "IT28W8000000292100645211151";
		Date data_di_scadenza = new Date(2025-05-31);
		int cvv = 695;
		int pin = 159357;
		ContoCorrente conto_corrente = null;
		CartaDiDebito cartaDiDebito = null;
		try {
			Cliente cliente = new Cliente("WYNBRC72L14D226V", "Wayne", "Bruce", "Gotham City", new Date(1939-05-22), "3209677458", "Villa Wayne, Gotham City", "Gotham City");
			Account account = new Account(12345, "bat_bruce123", "Joker#123", "brucewayne@wayneent.com", 25681531, "Bat-Computer", cliente);
			conto_corrente = new ContoCorrente(123456, "IT28W8000000292100645211151", 123586132.00, 123586132.00, account);
			cartaDiDebito = new CartaDiDebito(numero, iban, data_di_scadenza, cvv, pin, conto_corrente);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		int cvvResult = cartaDiDebito.getCvv();
		assertEquals(cvv, cvvResult, "Il codice cvv della carta dev'essere uguale");
	}
	
	@Test
	void test_getPin() {
		String numero = "123456";
		String iban = "IT28W8000000292100645211151";
		Date data_di_scadenza = new Date(2025-05-31);
		int cvv = 695;
		int pin = 159357;
		ContoCorrente conto_corrente = null;
		CartaDiDebito cartaDiDebito = null;
		try {
			Cliente cliente = new Cliente("WYNBRC72L14D226V", "Wayne", "Bruce", "Gotham City", new Date(1939-05-22), "3209677458", "Villa Wayne, Gotham City", "Gotham City");
			Account account = new Account(12345, "bat_bruce123", "Joker#123", "brucewayne@wayneent.com", 25681531, "Bat-Computer", cliente);
			conto_corrente = new ContoCorrente(123456, "IT28W8000000292100645211151", 123586132.00, 123586132.00, account);
			cartaDiDebito = new CartaDiDebito(numero, iban, data_di_scadenza, cvv, pin, conto_corrente);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		int pinResult = cartaDiDebito.getPin();
		assertEquals(pin, pinResult, "Il codice PIN della carta dev'essere uguale");
	}
}
