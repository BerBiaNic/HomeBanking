package BerBiaNic.homebanking.db.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import BerBiaNic.homebanking.entity.Account;
import BerBiaNic.homebanking.entity.Cliente;
import BerBiaNic.homebanking.entity.ContoCorrente;
import BerBiaNic.homebanking.exceptions.InputValidationException;

public class ContoCorrenteTest {

	@Test
	void test_notNullObjects() {
		int numero = 123456;
		String iban = "IT28W8000000292100645211151";
		double saldo_disponibile = 123586132.00;
		double saldo_contabile = 123586132.00;
		ContoCorrente contoCorrenteTest = null;
		try {
			Cliente cliente = new Cliente("WYNBRC72L14D226V", "Wayne", "Bruce", "Gotham City", new Date(1939-05-22), "3209677458", "Villa Wayne, Gotham City", "Gotham City");
			Account account = new Account(12345, "bat_bruce123", "Joker#123", "brucewayne@wayneent.com", 25681531, "Bat-Computer", cliente);
			contoCorrenteTest = new ContoCorrente(numero, iban, saldo_disponibile, saldo_contabile, account);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		Assert.assertNotNull(contoCorrenteTest);
	}

	@Test
	void test_getnumero() {
		int numero = 123456;
		String iban = "IT28W8000000292100645211151";
		double saldo_disponibile = 123586132.00;
		double saldo_contabile = 123586132.00;
		ContoCorrente contoCorrenteTest = null;
		try {
			Cliente cliente = new Cliente("WYNBRC72L14D226V", "Wayne", "Bruce", "Gotham City", new Date(1939-05-22), "3209677458", "Villa Wayne, Gotham City", "Gotham City");
			Account account = new Account(12345, "bat_bruce123", "Joker#123", "brucewayne@wayneent.com", 25681531, "Bat-Computer", cliente);
			contoCorrenteTest = new ContoCorrente(numero, iban, saldo_disponibile, saldo_contabile, account);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		int numeroResult = contoCorrenteTest.getNumero();
		assertEquals(numero, numeroResult, "I numeri devono essere uguali");
	}

	@Test
	void test_getIban() {
		int numero = 123456;
		String iban = "IT28W8000000292100645211151";
		double saldo_disponibile = 123586132.00;
		double saldo_contabile = 123586132.00;
		ContoCorrente contoCorrenteTest = null;
		try {
			Cliente cliente = new Cliente("WYNBRC72L14D226V", "Wayne", "Bruce", "Gotham City", new Date(1939-05-22), "3209677458", "Villa Wayne, Gotham City", "Gotham City");
			Account account = new Account(12345, "bat_bruce123", "Joker#123", "brucewayne@wayneent.com", 25681531, "Bat-Computer", cliente);
			contoCorrenteTest = new ContoCorrente(numero, iban, saldo_disponibile, saldo_contabile, account);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		String ibanResult = contoCorrenteTest.getIban();
		assertEquals(iban, ibanResult, "I codici IBAN devono essere uguali");
	}

	@Test
	void test_getSaldoDisponibile() {
		int numero = 123456;
		String iban = "IT28W8000000292100645211151";
		double saldo_disponibile = 123586132.00;
		double saldo_contabile = 123586132.00;
		ContoCorrente contoCorrenteTest = null;
		try {
			Cliente cliente = new Cliente("WYNBRC72L14D226V", "Wayne", "Bruce", "Gotham City", new Date(1939-05-22), "3209677458", "Villa Wayne, Gotham City", "Gotham City");
			Account account = new Account(12345, "bat_bruce123", "Joker#123", "brucewayne@wayneent.com", 25681531, "Bat-Computer", cliente);
			contoCorrenteTest = new ContoCorrente(numero, iban, saldo_disponibile, saldo_contabile, account);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		double saldo_disponibileResult = contoCorrenteTest.getSaldoDisponibile();
		assertEquals(saldo_disponibile, saldo_disponibileResult, "I saldi disponibili devono essere uguali");
	}

	@Test
	void test_getSaldoContabile() {
		int numero = 123456;
		String iban = "IT28W8000000292100645211151";
		double saldo_disponibile = 123586132.00;
		double saldo_contabile = 123586132.00;
		ContoCorrente contoCorrenteTest = null;
		try {
			Cliente cliente = new Cliente("WYNBRC72L14D226V", "Wayne", "Bruce", "Gotham City", new Date(1939-05-22), "3209677458", "Villa Wayne, Gotham City", "Gotham City");
			Account account = new Account(12345, "bat_bruce123", "Joker#123", "brucewayne@wayneent.com", 25681531, "Bat-Computer", cliente);
			contoCorrenteTest = new ContoCorrente(numero, iban, saldo_disponibile, saldo_contabile, account);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		double saldo_contabileResult = contoCorrenteTest.getSaldoContabile();
		assertEquals(saldo_contabile, saldo_contabileResult, "I saldi contabili devono essere uguali");
	}

	@Test
	void test_equals() {
		int numero = 123456;
		String iban = "IT28W8000000292100645211151";
		double saldo_disponibile = 123586132.00;
		double saldo_contabile = 123586132.00;
		ContoCorrente contoCorrenteTest = null;
		ContoCorrente contoCorrenteTestEquals = null;
		try {
			Cliente cliente = new Cliente("WYNBRC72L14D226V", "Wayne", "Bruce", "Gotham City", new Date(1939-05-22), "3209677458", "Villa Wayne, Gotham City", "Gotham City");
			Account account = new Account(12345, "bat_bruce123", "Joker#123", "brucewayne@wayneent.com", 25681531, "Bat-Computer", cliente);
			contoCorrenteTest = new ContoCorrente(numero, iban, saldo_disponibile, saldo_contabile, account);
			contoCorrenteTestEquals = new ContoCorrente(123456, "IT28W8000000292100645211151", 123586132.00, 123586132.00, account);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		assertEquals(contoCorrenteTest, contoCorrenteTestEquals, "I conti correnti devono essere uguali");
	}
}
