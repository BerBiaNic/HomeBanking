package BerBiaNic.homebanking.db.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import BerBiaNic.homebanking.entity.Account;
import BerBiaNic.homebanking.entity.CartaDiDebito;
import BerBiaNic.homebanking.entity.Cliente;
import BerBiaNic.homebanking.entity.ContoCorrente;
import BerBiaNic.homebanking.entity.OperazioneCartaDebito;
import BerBiaNic.homebanking.exceptions.InputValidationException;

public class OperazioneCartaDebitoTest {

	@Test
	void test_NotNullObject() {
		int id = 1684;
		Date data = new Date(2020-01-23);
		double importo = 153.99;
		String tipologia = "Bonifico";
		CartaDiDebito carta_proprietario = null;
		String carta_beneficiario = "IT28K8000000292100645222159";
		OperazioneCartaDebito operazioneCartaDiDebitoTest = null;

		try {
			Cliente cliente = new Cliente("PSSPBL40S29F927F", "Picasso", "Pablo", "Malaga", new Date(1973-04-8), "3895669877445", "Via Les demoiselles d'Avignon, n 1906, Malaga", "Malaga");
			Account account = new Account(123, "cubismo_periodo-rosa", "Guernic@666", "pablo_picasso@cubismo.es", 156815321, "Xiaomi11Pro-ssdvs", cliente);
			ContoCorrente conto_corrente = new ContoCorrente(1568455, "IT28W8000000292100645211151", 0.0, 0.0, account);
			carta_proprietario = new CartaDiDebito ("12345", "IT28W8000000292100645211151", new Date(2022-01-31), 555, 456789, conto_corrente);
			operazioneCartaDiDebitoTest = new OperazioneCartaDebito(id, data, importo, tipologia, carta_proprietario, carta_beneficiario);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		Assert.assertNotNull("L'oggetto carta di debito non dev'essere null", operazioneCartaDiDebitoTest);
	}

	@Test
	void test_getId() {
		int id = 1684;
		Date data = new Date(2020-01-23);
		double importo = 153.99;
		String tipologia = "Bonifico";
		CartaDiDebito carta_proprietario = null;
		String carta_beneficiario = "IT28K8000000292100645222159";
		OperazioneCartaDebito operazioneCartaDiDebitoTest = null;

		try {
			Cliente cliente = new Cliente("PSSPBL40S29F927F", "Picasso", "Pablo", "Malaga", new Date(1973-04-8), "3895669877445", "Via Les demoiselles d'Avignon, n 1906, Malaga", "Malaga");
			Account account = new Account(123, "cubismo_periodo-rosa", "Guernic@666", "pablo_picasso@cubismo.es", 156815321, "Xiaomi11Pro-ssdvs", cliente);
			ContoCorrente conto_corrente = new ContoCorrente(1568455, "IT28W8000000292100645211151", 0.0, 0.0, account);
			carta_proprietario = new CartaDiDebito ("12345", "IT28W8000000292100645211151", new Date(2022-01-31), 555, 456789, conto_corrente);
			operazioneCartaDiDebitoTest = new OperazioneCartaDebito(id, data, importo, tipologia, carta_proprietario, carta_beneficiario);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		int idResult = operazioneCartaDiDebitoTest.getId();
		assertEquals(id, idResult, "L'id dell'operazione dev'essere uguale");				
	}

	@Test
	void test_getImporto() {
		int id = 1684;
		Date data = new Date(2020-01-23);
		double importo = 153.99;
		String tipologia = "Bonifico";
		CartaDiDebito carta_proprietario = null;
		String carta_beneficiario = "IT28K8000000292100645222159";
		OperazioneCartaDebito operazioneCartaDiDebitoTest = null;

		try {
			Cliente cliente = new Cliente("PSSPBL40S29F927F", "Picasso", "Pablo", "Malaga", new Date(1973-04-8), "3895669877445", "Via Les demoiselles d'Avignon, n 1906, Malaga", "Malaga");
			Account account = new Account(123, "cubismo_periodo-rosa", "Guernic@666", "pablo_picasso@cubismo.es", 156815321, "Xiaomi11Pro-ssdvs", cliente);
			ContoCorrente conto_corrente = new ContoCorrente(1568455, "IT28W8000000292100645211151", 0.0, 0.0, account);
			carta_proprietario = new CartaDiDebito ("12345", "IT28W8000000292100645211151", new Date(2022-01-31), 555, 456789, conto_corrente);
			operazioneCartaDiDebitoTest = new OperazioneCartaDebito(id, data, importo, tipologia, carta_proprietario, carta_beneficiario);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		double importoResult = operazioneCartaDiDebitoTest.getImporto();
		assertEquals(importo, importoResult, "L'importo dell'operazione dev'essere uguale");	
	}
	
	@Test
	void test_getTipologia() {
		int id = 1684;
		Date data = new Date(2020-01-23);
		double importo = 153.99;
		String tipologia = "Bonifico";
		CartaDiDebito carta_proprietario = null;
		String carta_beneficiario = "IT28K8000000292100645222159";
		OperazioneCartaDebito operazioneCartaDiDebitoTest = null;

		try {
			Cliente cliente = new Cliente("PSSPBL40S29F927F", "Picasso", "Pablo", "Malaga", new Date(1973-04-8), "3895669877445", "Via Les demoiselles d'Avignon, n 1906, Malaga", "Malaga");
			Account account = new Account(123, "cubismo_periodo-rosa", "Guernic@666", "pablo_picasso@cubismo.es", 156815321, "Xiaomi11Pro-ssdvs", cliente);
			ContoCorrente conto_corrente = new ContoCorrente(1568455, "IT28W8000000292100645211151", 0.0, 0.0, account);
			carta_proprietario = new CartaDiDebito ("12345", "IT28W8000000292100645211151", new Date(2022-01-31), 555, 456789, conto_corrente);
			operazioneCartaDiDebitoTest = new OperazioneCartaDebito(id, data, importo, tipologia, carta_proprietario, carta_beneficiario);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		String tipologiaResult = operazioneCartaDiDebitoTest.getTipologia();
		assertEquals(tipologia, tipologiaResult, "La tipologia dell'operazione dev'essere uguale");				
	}


	@Test
	void test_getCartaDiDebito() {
		int id = 1684;
		Date data = new Date(2020-01-23);
		double importo = 153.99;
		String tipologia = "Bonifico";
		CartaDiDebito carta_proprietario = null;
		String carta_beneficiario = "IT28K8000000292100645222159";
		OperazioneCartaDebito operazioneCartaDiDebitoTest = null;

		try {
			Cliente cliente = new Cliente("PSSPBL40S29F927F", "Picasso", "Pablo", "Malaga", new Date(1973-04-8), "3895669877445", "Via Les demoiselles d'Avignon, n 1906, Malaga", "Malaga");
			Account account = new Account(123, "cubismo_periodo-rosa", "Guernic@666", "pablo_picasso@cubismo.es", 156815321, "Xiaomi11Pro-ssdvs", cliente);
			ContoCorrente conto_corrente = new ContoCorrente(1568455, "IT28W8000000292100645211151", 0.0, 0.0, account);
			carta_proprietario = new CartaDiDebito ("12345", "IT28W8000000292100645211151", new Date(2022-01-31), 555, 456789, conto_corrente);
			operazioneCartaDiDebitoTest = new OperazioneCartaDebito(id, data, importo, tipologia, carta_proprietario, carta_beneficiario);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		CartaDiDebito carta_proprietarioResult = operazioneCartaDiDebitoTest.getCarta_proprietario();
		assertEquals(carta_proprietario, carta_proprietarioResult, "L'oggetto carta_proprietario dell'operazione dev'essere uguale");	
	}

	@Test
	void test_getCartaBeneficiario() {
		int id = 1684;
		Date data = new Date(2020-01-23);
		double importo = 153.99;
		String tipologia = "Bonifico";
		CartaDiDebito carta_proprietario = null;
		String carta_beneficiario = "IT28K8000000292100645222159";
		OperazioneCartaDebito operazioneCartaDiDebitoTest = null;

		try {
			Cliente cliente = new Cliente("PSSPBL40S29F927F", "Picasso", "Pablo", "Malaga", new Date(1973-04-8), "3895669877445", "Via Les demoiselles d'Avignon, n 1906, Malaga", "Malaga");
			Account account = new Account(123, "cubismo_periodo-rosa", "Guernic@666", "pablo_picasso@cubismo.es", 156815321, "Xiaomi11Pro-ssdvs", cliente);
			ContoCorrente conto_corrente = new ContoCorrente(1568455, "IT28W8000000292100645211151", 0.0, 0.0, account);
			carta_proprietario = new CartaDiDebito ("12345", "IT28W8000000292100645211151", new Date(2022-01-31), 555, 456789, conto_corrente);
			operazioneCartaDiDebitoTest = new OperazioneCartaDebito(id, data, importo, tipologia, carta_proprietario, carta_beneficiario);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		String carta_beneficiarioResult = operazioneCartaDiDebitoTest.getCarta_beneficiario();
		assertEquals(carta_beneficiario, carta_beneficiarioResult, "La carta beneficiario dev'essere uguale");	
	}

	@Test
	void test_equals() {
		int id = 1684;
		Date data = new Date(2020-01-23);
		double importo = 153.99;
		String tipologia = "Bonifico";
		CartaDiDebito carta_proprietario = null;
		String carta_beneficiario = "IT28K8000000292100645222159";
		OperazioneCartaDebito operazioneCartaDiDebitoTest = null;
		OperazioneCartaDebito operazioneCartaDiDebitoTestEquals = null;

		try {
			Cliente cliente = new Cliente("PSSPBL40S29F927F", "Picasso", "Pablo", "Malaga", new Date(1973-04-8), "3895669877445", "Via Les demoiselles d'Avignon, n 1906, Malaga", "Malaga");
			Account account = new Account(123, "cubismo_periodo-rosa", "Guernic@666", "pablo_picasso@cubismo.es", 156815321, "Xiaomi11Pro-ssdvs", cliente);
			ContoCorrente conto_corrente = new ContoCorrente(1568455, "IT28W8000000292100645211151", 0.0, 0.0, account);
			carta_proprietario = new CartaDiDebito ("12345", "IT28W8000000292100645211151", new Date(2022-01-31), 555, 456789, conto_corrente);
			operazioneCartaDiDebitoTest = new OperazioneCartaDebito(id, data, importo, tipologia, carta_proprietario, carta_beneficiario);
			operazioneCartaDiDebitoTestEquals = new OperazioneCartaDebito(id, data, importo, tipologia, carta_proprietario, carta_beneficiario);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		assertEquals(operazioneCartaDiDebitoTest, operazioneCartaDiDebitoTestEquals, "Le operazioni della carta di debito devono essere uguali");	
	}

}
