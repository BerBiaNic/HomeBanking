package BerBiaNic.homebanking.db.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import BerBiaNic.homebanking.entity.Account;
import BerBiaNic.homebanking.entity.CartaPrepagata;
import BerBiaNic.homebanking.entity.Cliente;
import BerBiaNic.homebanking.entity.OperazionePrepagata;
import BerBiaNic.homebanking.exceptions.InputValidationException;

public class OperazionePrepagataTest {

	@Test
	void test_NotNullObject() {
		int id = 12345;
		Date data = new Date(20-06-06);
		double importo = 299.99;
		String tipologia = "Pagamento POS";
		String cartaDestinatario = "IT28K8000000292100645222159";
		CartaPrepagata cartaPrepagata = null;
		OperazionePrepagata operazionePrepagataTest = null;
		Account account = null;

		try {
			Cliente cliente = new Cliente("BGGRRT67B18B403U", "Baggio", "Roberto", "Caldogno", new Date(1967-02-18), "3392688412", "Via Pallone d'Oro 1993, Caldogno (VI)", "Caldogno");
			account = new Account(12345, "divincodino10", "Roby#Baggio10", "roberto_baggio67@brescia.it", 156841324, "SamsungAce2_51686v4s", cliente);
			cartaPrepagata = new CartaPrepagata("1234569874521456", 26598.69, 26598.69, new Date(2023-05-31), 753, 593742, account);
			operazionePrepagataTest = new OperazionePrepagata(id, data, importo, tipologia, cartaPrepagata, cartaDestinatario);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		Assert.assertNotNull(operazionePrepagataTest);
	}
	
	@Test
	void test_getId() {
		int id = 12345;
		Date data = new Date(20-06-06);
		double importo = 299.99;
		String tipologia = "Pagamento POS";
		String cartaDestinatario = "IT28K8000000292100645222159";
		CartaPrepagata cartaPrepagata = null;
		OperazionePrepagata operazionePrepagataTest = null;
		Account account = null;

		try {
			Cliente cliente = new Cliente("BGGRRT67B18B403U", "Baggio", "Roberto", "Caldogno", new Date(1967-02-18), "3392688412", "Via Pallone d'Oro 1993, Caldogno (VI)", "Caldogno");
			account = new Account(12345, "divincodino10", "Roby#Baggio10", "roberto_baggio67@brescia.it", 156841324, "SamsungAce2_51686v4s", cliente);
			cartaPrepagata = new CartaPrepagata("1234569874521456", 26598.69, 26598.69, new Date(2023-05-31), 753, 593742, account);
			operazionePrepagataTest = new OperazionePrepagata(id, data, importo, tipologia, cartaPrepagata, cartaDestinatario);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		int idResult = operazionePrepagataTest.getId();
		assertEquals(id, idResult, "L'id dell'operazione dev'essere uguale");
	}

	@Test
	void test_getImporto() {
		int id = 12345;
		Date data = new Date(20-06-06);
		double importo = 299.99;
		String tipologia = "Pagamento POS";
		String cartaDestinatario = "IT28K8000000292100645222159";
		CartaPrepagata cartaPrepagata = null;
		OperazionePrepagata operazionePrepagataTest = null;
		Account account = null;

		try {
			Cliente cliente = new Cliente("BGGRRT67B18B403U", "Baggio", "Roberto", "Caldogno", new Date(1967-02-18), "3392688412", "Via Pallone d'Oro 1993, Caldogno (VI)", "Caldogno");
			account = new Account(12345, "divincodino10", "Roby#Baggio10", "roberto_baggio67@brescia.it", 156841324, "SamsungAce2_51686v4s", cliente);
			cartaPrepagata = new CartaPrepagata("1234569874521456", 26598.69, 26598.69, new Date(2023-05-31), 753, 593742, account);
			operazionePrepagataTest = new OperazionePrepagata(id, data, importo, tipologia, cartaPrepagata, cartaDestinatario);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		double importoResult = operazionePrepagataTest.getImporto();
		assertEquals(importo, importoResult, "L'importo dell'operazione dev'essere uguale");
	}
	
	@Test
	void test_getTipologia() {
		int id = 12345;
		Date data = new Date(20-06-06);
		double importo = 299.99;
		String tipologia = "Pagamento POS";
		String cartaDestinatario = "IT28K8000000292100645222159";
		CartaPrepagata cartaPrepagata = null;
		OperazionePrepagata operazionePrepagataTest = null;
		Account account = null;

		try {
			Cliente cliente = new Cliente("BGGRRT67B18B403U", "Baggio", "Roberto", "Caldogno", new Date(1967-02-18), "3392688412", "Via Pallone d'Oro 1993, Caldogno (VI)", "Caldogno");
			account = new Account(12345, "divincodino10", "Roby#Baggio10", "roberto_baggio67@brescia.it", 156841324, "SamsungAce2_51686v4s", cliente);
			cartaPrepagata = new CartaPrepagata("1234569874521456", 26598.69, 26598.69, new Date(2023-05-31), 753, 593742, account);
			operazionePrepagataTest = new OperazionePrepagata(id, data, importo, tipologia, cartaPrepagata, cartaDestinatario);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		String tipologiaResult = operazionePrepagataTest.getTipologia();
		assertEquals(tipologia, tipologiaResult, "La tipologia dell'operazione dev'essere uguale");
	}
	
	@Test
	void test_getCartaDestinatario() {
		int id = 12345;
		Date data = new Date(20-06-06);
		double importo = 299.99;
		String tipologia = "Pagamento POS";
		String cartaDestinatario = "IT28K8000000292100645222159";
		CartaPrepagata cartaPrepagata = null;
		OperazionePrepagata operazionePrepagataTest = null;
		Account account = null;

		try {
			Cliente cliente = new Cliente("BGGRRT67B18B403U", "Baggio", "Roberto", "Caldogno", new Date(1967-02-18), "3392688412", "Via Pallone d'Oro 1993, Caldogno (VI)", "Caldogno");
			account = new Account(12345, "divincodino10", "Roby#Baggio10", "roberto_baggio67@brescia.it", 156841324, "SamsungAce2_51686v4s", cliente);
			cartaPrepagata = new CartaPrepagata("1234569874521456", 26598.69, 26598.69, new Date(2023-05-31), 753, 593742, account);
			operazionePrepagataTest = new OperazionePrepagata(id, data, importo, tipologia, cartaPrepagata, cartaDestinatario);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		String cartaDestinatarioResult = operazionePrepagataTest.getDestinatario();
		assertEquals(cartaDestinatario, cartaDestinatarioResult, "La carta destinatario dev'essere uguale");	
	}
	
	@Test
	void test_equals() {
			int id = 12345;
			Date data = new Date(20-06-06);
			double importo = 299.99;
			String tipologia = "Pagamento POS";
			String cartaDestinatario = "IT28K8000000292100645222159";
			CartaPrepagata cartaPrepagata = null;
			OperazionePrepagata operazionePrepagataTest = null;
			Account account = null;
			OperazionePrepagata operazionePrepagataTestEquals = null;
					
			try {
				Cliente cliente = new Cliente("BGGRRT67B18B403U", "Baggio", "Roberto", "Caldogno", new Date(1967-02-18), "3392688412", "Via Pallone d'Oro 1993, Caldogno (VI)", "Caldogno");
				account = new Account(12345, "divincodino10", "Roby#Baggio10", "roberto_baggio67@brescia.it", 156841324, "SamsungAce2_51686v4s", cliente);
				cartaPrepagata = new CartaPrepagata("1234569874521456", 26598.69, 26598.69, new Date(2023-05-31), 753, 593742, account);
				operazionePrepagataTest = new OperazionePrepagata(id, data, importo, tipologia, cartaPrepagata, cartaDestinatario);
				operazionePrepagataTestEquals = new OperazionePrepagata(id, data, importo, tipologia, cartaPrepagata, cartaDestinatario);
			} catch (InputValidationException e) {
				System.out.println(e);		
			}
			assertEquals(operazionePrepagataTest, operazionePrepagataTestEquals, "L'operazione prepagata dev'essere uguale");	
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
