package BerBiaNic.homebanking.db.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import BerBiaNic.homebanking.entity.Account;
import BerBiaNic.homebanking.entity.CartaPrepagata;
import BerBiaNic.homebanking.entity.Cliente;
import BerBiaNic.homebanking.exceptions.InputValidationException;

public class CartaPrepagataTest {

	@Test
	void test_notNullObjects() {
		String numero = "1234569874521456";
		double saldoContabile = 26598.69;
		double saldoDisponibile = 26598.69;
		Date dataDiScadenza = new Date(2021-05-25);
		int cvv = 999;
		int pin = 645289;
		Account account = null;
		CartaPrepagata cartaPrepagataTest = null;

		try {
			Cliente cliente = new Cliente("BGGRRT67B18B403U", "Baggio", "Roberto", "Caldogno", new Date(1967-02-18), "3392688412", "Via Pallone d'Oro 1993, Caldogno (VI)", "Caldogno");
			account = new Account(12345, "divincodino10", "Roby#Baggio10", "roberto_baggio67@brescia.it", 156841324, "SamsungAce2_51686v4s", cliente);
			cartaPrepagataTest = new CartaPrepagata(numero, saldoContabile, saldoDisponibile, dataDiScadenza, cvv, pin, account);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		Assert.assertNotNull(cartaPrepagataTest);
	}
	
	@Test
	void test_getNumero() {
		String numero = "1234569874521456";
		double saldoContabile = 26598.69;
		double saldoDisponibile = 26598.69;
		Date dataDiScadenza = new Date(2021-05-25);
		int cvv = 999;
		int pin = 645289;
		Account account = null;
		CartaPrepagata cartaPrepagataTest = null;

		try {
			Cliente cliente = new Cliente("BGGRRT67B18B403U", "Baggio", "Roberto", "Caldogno", new Date(1967-02-18), "3392688412", "Via Pallone d'Oro 1993, Caldogno (VI)", "Caldogno");
			account = new Account(12345, "divincodino10", "Roby#Baggio10", "roberto_baggio67@brescia.it", 156841324, "SamsungAce2_51686v4s", cliente);
			cartaPrepagataTest = new CartaPrepagata(numero, saldoContabile, saldoDisponibile, dataDiScadenza, cvv, pin, account);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		String numeroResult = cartaPrepagataTest.getNumero();
		assertEquals(numero, numeroResult, "Il numero della carta dev'essere uguale");
	}
	
	@Test
	void test_getSaldoContabile() {
		String numero = "1234569874521456";
		double saldoContabile = 26598.69;
		double saldoDisponibile = 26598.69;
		Date dataDiScadenza = new Date(2021-05-25);
		int cvv = 999;
		int pin = 645289;
		Account account = null;
		CartaPrepagata cartaPrepagataTest = null;

		try {
			Cliente cliente = new Cliente("BGGRRT67B18B403U", "Baggio", "Roberto", "Caldogno", new Date(1967-02-18), "3392688412", "Via Pallone d'Oro 1993, Caldogno (VI)", "Caldogno");
			account = new Account(12345, "divincodino10", "Roby#Baggio10", "roberto_baggio67@brescia.it", 156841324, "SamsungAce2_51686v4s", cliente);
			cartaPrepagataTest = new CartaPrepagata(numero, saldoContabile, saldoDisponibile, dataDiScadenza, cvv, pin, account);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		double saldoContabileResult = cartaPrepagataTest.getSaldoContabile();
		assertEquals(saldoContabile, saldoContabileResult, "Il saldo contabile della carta dev'essere uguale");
	}
	
	@Test
	void test_getSaldoDisponibile() {
		String numero = "1234569874521456";
		double saldoContabile = 26598.69;
		double saldoDisponibile = 26598.69;
		Date dataDiScadenza = new Date(2021-05-25);
		int cvv = 999;
		int pin = 645289;
		Account account = null;
		CartaPrepagata cartaPrepagataTest = null;

		try {
			Cliente cliente = new Cliente("BGGRRT67B18B403U", "Baggio", "Roberto", "Caldogno", new Date(1967-02-18), "3392688412", "Via Pallone d'Oro 1993, Caldogno (VI)", "Caldogno");
			account = new Account(12345, "divincodino10", "Roby#Baggio10", "roberto_baggio67@brescia.it", 156841324, "SamsungAce2_51686v4s", cliente);
			cartaPrepagataTest = new CartaPrepagata(numero, saldoContabile, saldoDisponibile, dataDiScadenza, cvv, pin, account);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		double saldoDisponibileResult = cartaPrepagataTest.getSaldoDisponibile();
		assertEquals(saldoDisponibile, saldoDisponibileResult, "Il saldo disponibile della carta dev'essere uguale");
	}
	
	@Test
	void test_getCvv() {
		String numero = "1234569874521456";
		double saldoContabile = 26598.69;
		double saldoDisponibile = 26598.69;
		Date dataDiScadenza = new Date(2021-05-25);
		int cvv = 999;
		int pin = 645289;
		Account account = null;
		CartaPrepagata cartaPrepagataTest = null;

		try {
			Cliente cliente = new Cliente("BGGRRT67B18B403U", "Baggio", "Roberto", "Caldogno", new Date(1967-02-18), "3392688412", "Via Pallone d'Oro 1993, Caldogno (VI)", "Caldogno");
			account = new Account(12345, "divincodino10", "Roby#Baggio10", "roberto_baggio67@brescia.it", 156841324, "SamsungAce2_51686v4s", cliente);
			cartaPrepagataTest = new CartaPrepagata(numero, saldoContabile, saldoDisponibile, dataDiScadenza, cvv, pin, account);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		int cvvResult = cartaPrepagataTest.getCvv();
		assertEquals(cvv, cvvResult, "Il codice cvv della carta dev'essere uguale");
	}
	
	@Test
	void test_getPin() {
		String numero = "1234569874521456";
		double saldoContabile = 26598.69;
		double saldoDisponibile = 26598.69;
		Date dataDiScadenza = new Date(2021-05-25);
		int cvv = 999;
		int pin = 645289;
		Account account = null;
		CartaPrepagata cartaPrepagataTest = null;

		try {
			Cliente cliente = new Cliente("BGGRRT67B18B403U", "Baggio", "Roberto", "Caldogno", new Date(1967-02-18), "3392688412", "Via Pallone d'Oro 1993, Caldogno (VI)", "Caldogno");
			account = new Account(12345, "divincodino10", "Roby#Baggio10", "roberto_baggio67@brescia.it", 156841324, "SamsungAce2_51686v4s", cliente);
			cartaPrepagataTest = new CartaPrepagata(numero, saldoContabile, saldoDisponibile, dataDiScadenza, cvv, pin, account);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		int pinResult = cartaPrepagataTest.getPin();
		assertEquals(pin, pinResult, "Il codice PIN della carta dev'essere uguale");
	}
}
