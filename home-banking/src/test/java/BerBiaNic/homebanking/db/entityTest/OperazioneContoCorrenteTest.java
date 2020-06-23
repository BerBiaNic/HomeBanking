package BerBiaNic.homebanking.db.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import BerBiaNic.homebanking.entity.Account;
import BerBiaNic.homebanking.entity.Cliente;
import BerBiaNic.homebanking.entity.ContoCorrente;
import BerBiaNic.homebanking.entity.OperazioneContoCorrente;
import BerBiaNic.homebanking.exceptions.InputValidationException;

public class OperazioneContoCorrenteTest {

	@Test
	void test_NotNullObject() {
		int id = 15534831;
		Date data = new Date(2020-12-22);
		double importo = 999.99;
		String tipologia = "Bonifico";
		ContoCorrente conto_corrente_proprietario = null;
		String conto_corrente_destinatario = "IT28K8000000292100645222159";
		OperazioneContoCorrente operazioneContoCorrenteTest = null;

		Cliente cliente;
		try {
			cliente = new Cliente("PSSPBL40S29F927F", "Picasso", "Pablo", "Malaga", new Date(1973-04-8), "3895669877445", "Via Les demoiselles d'Avignon, n 1906, Malaga", "Malaga");
			Account account = new Account(123, "cubismo_periodo-rosa", "Guernic@666", "pablo_picasso@cubismo.es", 156815321, "Xiaomi11Pro-ssdvs", cliente);
			conto_corrente_proprietario = new ContoCorrente(1568455, "IT28W8000000292100645211151", 10.0, 10.0, account);
			operazioneContoCorrenteTest = new OperazioneContoCorrente(id, data, importo, tipologia, conto_corrente_proprietario, conto_corrente_destinatario);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		Assert.assertNotNull(operazioneContoCorrenteTest);
	}

	@Test
	void test_getId() {
		int id = 15534831;
		Date data = new Date(2020-12-22);
		double importo = 999.99;
		String tipologia = "Bonifico";
		ContoCorrente conto_corrente_proprietario = null;
		String conto_corrente_destinatario = "IT28K8000000292100645222159";
		OperazioneContoCorrente operazioneContoCorrenteTest = null;

		Cliente cliente;
		try {
			cliente = new Cliente("PSSPBL40S29F927F", "Picasso", "Pablo", "Malaga", new Date(1973-04-8), "3895669877445", "Via Les demoiselles d'Avignon, n 1906, Malaga", "Malaga");
			Account account = new Account(123, "cubismo_periodo-rosa", "Guernic@666", "pablo_picasso@cubismo.es", 156815321, "Xiaomi11Pro-ssdvs", cliente);
			conto_corrente_proprietario = new ContoCorrente(1568455, "IT28W8000000292100645211151", 0.0, 0.0, account);
			operazioneContoCorrenteTest = new OperazioneContoCorrente(id, data, importo, tipologia, conto_corrente_proprietario, conto_corrente_destinatario);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		int idResult = operazioneContoCorrenteTest.getId();
		assertEquals(id, idResult, "L'id dell'operazione dev'essere uguale");
	}

	@Test
	void test_getImporto() {
		int id = 15534831;
		Date data = new Date(2020-12-22);
		double importo = 999.99;
		String tipologia = "Bonifico";
		ContoCorrente conto_corrente_proprietario = null;
		String conto_corrente_destinatario = "IT28K8000000292100645222159";
		OperazioneContoCorrente operazioneContoCorrenteTest = null;

		Cliente cliente;
		try {
			cliente = new Cliente("PSSPBL40S29F927F", "Picasso", "Pablo", "Malaga", new Date(1973-04-8), "3895669877445", "Via Les demoiselles d'Avignon, n 1906, Malaga", "Malaga");
			Account account = new Account(123, "cubismo_periodo-rosa", "Guernic@666", "pablo_picasso@cubismo.es", 156815321, "Xiaomi11Pro-ssdvs", cliente);
			conto_corrente_proprietario = new ContoCorrente(1568455, "IT28W8000000292100645211151", 0.0, 0.0, account);
			operazioneContoCorrenteTest = new OperazioneContoCorrente(id, data, importo, tipologia, conto_corrente_proprietario, conto_corrente_destinatario);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		double importoResult = operazioneContoCorrenteTest.getImporto();
		assertEquals(importo, importoResult, "L'importo dell'operazione dev'essere uguale");
	}

	@Test
	void test_Tipologia() {
		int id = 15534831;
		Date data = new Date(2020-12-22);
		double importo = 999.99;
		String tipologia = "Bonifico";
		ContoCorrente conto_corrente_proprietario = null;
		String conto_corrente_destinatario = "IT28K8000000292100645222159";
		OperazioneContoCorrente operazioneContoCorrenteTest = null;

		Cliente cliente;
		try {
			cliente = new Cliente("PSSPBL40S29F927F", "Picasso", "Pablo", "Malaga", new Date(1973-04-8), "3895669877445", "Via Les demoiselles d'Avignon, n 1906, Malaga", "Malaga");
			Account account = new Account(123, "cubismo_periodo-rosa", "Guernic@666", "pablo_picasso@cubismo.es", 156815321, "Xiaomi11Pro-ssdvs", cliente);
			conto_corrente_proprietario = new ContoCorrente(1568455, "IT28W8000000292100645211151", 0.0, 0.0, account);
			operazioneContoCorrenteTest = new OperazioneContoCorrente(id, data, importo, tipologia, conto_corrente_proprietario, conto_corrente_destinatario);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		String tipologiaResult = operazioneContoCorrenteTest.getTipologia();
		assertEquals(tipologia, tipologiaResult, "La tipologia dell'operazione dev'essere uguale");
	}

	@Test
	void test_getContoCorrente() {
		int id = 15534831;
		Date data = new Date(2020-12-22);
		double importo = 999.99;
		String tipologia = "Bonifico";
		ContoCorrente conto_corrente_proprietario = null;
		String conto_corrente_destinatario = "IT28K8000000292100645222159";
		OperazioneContoCorrente operazioneContoCorrenteTest = null;

		Cliente cliente;
		try {
			cliente = new Cliente("PSSPBL40S29F927F", "Picasso", "Pablo", "Malaga", new Date(1973-04-8), "3895669877445", "Via Les demoiselles d'Avignon, n 1906, Malaga", "Malaga");
			Account account = new Account(123, "cubismo_periodo-rosa", "Guernic@666", "pablo_picasso@cubismo.es", 156815321, "Xiaomi11Pro-ssdvs", cliente);
			conto_corrente_proprietario = new ContoCorrente(1568455, "IT28W8000000292100645211151", 0.0, 0.0, account);
			operazioneContoCorrenteTest = new OperazioneContoCorrente(id, data, importo, tipologia, conto_corrente_proprietario, conto_corrente_destinatario);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		ContoCorrente conto_corrente_proprietarioResult = operazioneContoCorrenteTest.getConto_corrente_proprietario();
		assertEquals(conto_corrente_proprietario, conto_corrente_proprietarioResult, "L'oggetto conto_corrente_proprietario dell'operazione dev'essere uguale");
	}

	@Test
	void test_getContoCorrenteDestinatario() {
		int id = 15534831;
		Date data = new Date(2020-12-22);
		double importo = 999.99;
		String tipologia = "Bonifico";
		ContoCorrente conto_corrente_proprietario = null;
		String conto_corrente_destinatario = "IT28K8000000292100645222159";
		OperazioneContoCorrente operazioneContoCorrenteTest = null;

		Cliente cliente;
		try {
			cliente = new Cliente("PSSPBL40S29F927F", "Picasso", "Pablo", "Malaga", new Date(1973-04-8), "3895669877445", "Via Les demoiselles d'Avignon, n 1906, Malaga", "Malaga");
			Account account = new Account(123, "cubismo_periodo-rosa", "Guernic@666", "pablo_picasso@cubismo.es", 156815321, "Xiaomi11Pro-ssdvs", cliente);
			conto_corrente_proprietario = new ContoCorrente(1568455, "IT28W8000000292100645211151", 0.0, 0.0, account);
			operazioneContoCorrenteTest = new OperazioneContoCorrente(id, data, importo, tipologia, conto_corrente_proprietario, conto_corrente_destinatario);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		String conto_corrente_destinatarioResult = operazioneContoCorrenteTest.getConto_corrente_destinatario();
		assertEquals(conto_corrente_destinatario, conto_corrente_destinatarioResult, "La carta beneficiario dev'essere uguale");	
	}

	@Test
	void test_equals() {
		int id = 15534831;
		Date data = new Date(2020-12-22);
		double importo = 999.99;
		String tipologia = "Bonifico";
		ContoCorrente conto_corrente_proprietario = null;
		String conto_corrente_destinatario = "IT28K8000000292100645222159";
		OperazioneContoCorrente operazioneContoCorrenteTest = null;
		OperazioneContoCorrente operazioneContoCorrenteTestEquals = null;

		Cliente cliente;
		try {
			cliente = new Cliente("PSSPBL40S29F927F", "Picasso", "Pablo", "Malaga", new Date(1973-04-8), "3895669877445", "Via Les demoiselles d'Avignon, n 1906, Malaga", "Malaga");
			Account account = new Account(123, "cubismo_periodo-rosa", "Guernic@666", "pablo_picasso@cubismo.es", 156815321, "Xiaomi11Pro-ssdvs", cliente);
			conto_corrente_proprietario = new ContoCorrente(1568455, "IT28W8000000292100645211151", 0.0, 0.0, account);
			operazioneContoCorrenteTest = new OperazioneContoCorrente(id, data, importo, tipologia, conto_corrente_proprietario, conto_corrente_destinatario);
			operazioneContoCorrenteTestEquals = new OperazioneContoCorrente(id, data, importo, tipologia, conto_corrente_proprietario, conto_corrente_destinatario);
		} catch (InputValidationException e) {
			System.out.println(e);		
		}
		assertEquals(operazioneContoCorrenteTest, operazioneContoCorrenteTestEquals, "La carta beneficiario dev'essere uguale");		
	}
}
