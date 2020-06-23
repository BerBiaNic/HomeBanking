package BerBiaNic.homebanking.db.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import BerBiaNic.homebanking.entity.Cliente;
import BerBiaNic.homebanking.exceptions.InputValidationException;

class ClienteTest {

	@Test
	void test_notNullObjects() {

		String codiceFiscale = "DVNLRD52A15M059Z";
		String cognome = "da Vinci";
		String nome = "Leonardo";
		String cittaDiNascita = "Anchiano";
		Date dataDinascita = new Date(1452-04-15);
		String numeroDiTelefono = "3398965477852";
		String indirizzoDiResidenza = "Via Gioconda, n 1503, Anchiano, frazione di Vinci (FI)";
		String cittaDiResidenza = "Anchiano";
		Cliente clienteTest = null;
		try {
			clienteTest = new Cliente(codiceFiscale, cognome, nome, cittaDiNascita, dataDinascita, numeroDiTelefono, indirizzoDiResidenza, cittaDiResidenza);
		} catch (InputValidationException e) {
			System.out.println(e);;
		}
		Assert.assertNotNull("L'oggetto cliente non dev'essere null", clienteTest);
	}

	@Test
	void test_getCodiceFiscale() {

		String codiceFiscale = "DVNLRD52A15M059Z";
		String cognome = "da Vinci";
		String nome = "Leonardo";
		String cittaDiNascita = "Anchiano";
		Date dataDinascita = new Date(1452-04-15);
		String numeroDiTelefono = "3398965477852";
		String indirizzoDiResidenza = "Via Gioconda, n 1503, Anchiano, frazione di Vinci (FI)";
		String cittaDiResidenza = "Anchiano";
		Cliente clienteTest = null;
		try {
			clienteTest = new Cliente(codiceFiscale, cognome, nome, cittaDiNascita, dataDinascita, numeroDiTelefono, indirizzoDiResidenza, cittaDiResidenza);
		} catch (InputValidationException e) {
			System.out.println(e);;
		}
		String codiceFiscaleResult = clienteTest.getCodiceFiscale();
		assertEquals(codiceFiscale, codiceFiscaleResult, "Il codice fiscale dev'essere uguale");
	}

	@Test
	void test_getCognome() {

		String codiceFiscale = "DVNLRD52A15M059Z";
		String cognome = "da Vinci";
		String nome = "Leonardo";
		String cittaDiNascita = "Anchiano";
		Date dataDinascita = new Date(1452-04-15);
		String numeroDiTelefono = "3398965477852";
		String indirizzoDiResidenza = "Via Gioconda, n 1503, Anchiano, frazione di Vinci (FI)";
		String cittaDiResidenza = "Anchiano";
		Cliente clienteTest = null;
		try {
			clienteTest = new Cliente(codiceFiscale, cognome, nome, cittaDiNascita, dataDinascita, numeroDiTelefono, indirizzoDiResidenza, cittaDiResidenza);
		} catch (InputValidationException e) {
			System.out.println(e);;
		}
		String cognomeResult = clienteTest.getCognome();
		assertEquals(cognome, cognomeResult, "Il cognome dev'essere uguale");
	}

	@Test
	void test_getNome() {

		String codiceFiscale = "DVNLRD52A15M059Z";
		String cognome = "da Vinci";
		String nome = "Leonardo";
		String cittaDiNascita = "Anchiano";
		Date dataDinascita = new Date(1452-04-15);
		String numeroDiTelefono = "3398965477852";
		String indirizzoDiResidenza = "Via Gioconda, n 1503, Anchiano, frazione di Vinci (FI)";
		String cittaDiResidenza = "Anchiano";
		Cliente clienteTest = null;
		try {
			clienteTest = new Cliente(codiceFiscale, cognome, nome, cittaDiNascita, dataDinascita, numeroDiTelefono, indirizzoDiResidenza, cittaDiResidenza);
		} catch (InputValidationException e) {
			System.out.println(e);;
		}
		String nomeResult = clienteTest.getNome();
		assertEquals(nome, nomeResult, "Il nome dev'essere uguale");
	}

	@Test
	void test_getCittaDiNascita() {

		String codiceFiscale = "DVNLRD52A15M059Z";
		String cognome = "da Vinci";
		String nome = "Leonardo";
		String cittaDiNascita = "Anchiano";
		Date dataDinascita = new Date(1452-04-15);
		String numeroDiTelefono = "3398965477852";
		String indirizzoDiResidenza = "Via Gioconda, n 1503, Anchiano, frazione di Vinci (FI)";
		String cittaDiResidenza = "Anchiano";
		Cliente clienteTest = null;
		try {
			clienteTest = new Cliente(codiceFiscale, cognome, nome, cittaDiNascita, dataDinascita, numeroDiTelefono, indirizzoDiResidenza, cittaDiResidenza);
		} catch (InputValidationException e) {
			System.out.println(e);;
		}
		String cittaDiNascitaResult = clienteTest.getCittaDiNascita();
		assertEquals(cittaDiNascita, cittaDiNascitaResult, "La citta di nascita dev'essere uguale");
	}
	
	@Test
	void test_getDataDiNascita() {

		String codiceFiscale = "DVNLRD52A15M059Z";
		String cognome = "da Vinci";
		String nome = "Leonardo";
		String cittaDiNascita = "Anchiano";
		Date dataDinascita = new Date(1452-04-15);
		String numeroDiTelefono = "3398965477852";
		String indirizzoDiResidenza = "Via Gioconda, n 1503, Anchiano, frazione di Vinci (FI)";
		String cittaDiResidenza = "Anchiano";
		Cliente clienteTest = null;
		try {
			clienteTest = new Cliente(codiceFiscale, cognome, nome, cittaDiNascita, dataDinascita, numeroDiTelefono, indirizzoDiResidenza, cittaDiResidenza);
		} catch (InputValidationException e) {
			System.out.println(e);;
		}
		Date dataDinascitaResult = new Date(1452-04-15);
		assertEquals(dataDinascita, dataDinascitaResult, "La data di nascita dev'essere uguale");
	}

	@Test
	void test_getNumeroDiTelefono() {

		String codiceFiscale = "DVNLRD52A15M059Z";
		String cognome = "da Vinci";
		String nome = "Leonardo";
		String cittaDiNascita = "Anchiano";
		Date dataDinascita = new Date(1452-04-15);
		String numeroDiTelefono = "3398965477852";
		String indirizzoDiResidenza = "Via Gioconda, n 1503, Anchiano, frazione di Vinci (FI)";
		String cittaDiResidenza = "Anchiano";
		Cliente clienteTest = null;
		try {
			clienteTest = new Cliente(codiceFiscale, cognome, nome, cittaDiNascita, dataDinascita, numeroDiTelefono, indirizzoDiResidenza, cittaDiResidenza);
		} catch (InputValidationException e) {
			System.out.println(e);;
		}
		String numeroDiTelefonoResult = clienteTest.getNumeroDiTelefono();
		assertEquals(numeroDiTelefono, numeroDiTelefonoResult, "Il numero di telefono dev'essere uguale");
	}

	@Test
	void test_getIndirizzoDiResidenza() {

		String codiceFiscale = "DVNLRD52A15M059Z";
		String cognome = "da Vinci";
		String nome = "Leonardo";
		String cittaDiNascita = "Anchiano";
		Date dataDinascita = new Date(1452-04-15);
		String numeroDiTelefono = "3398965477852";
		String indirizzoDiResidenza = "Via Gioconda, n 1503, Anchiano, frazione di Vinci (FI)";
		String cittaDiResidenza = "Anchiano";
		Cliente clienteTest = null;
		try {
			clienteTest = new Cliente(codiceFiscale, cognome, nome, cittaDiNascita, dataDinascita, numeroDiTelefono, indirizzoDiResidenza, cittaDiResidenza);
		} catch (InputValidationException e) {
			System.out.println(e);;
		}
		String indirizzoDiResidenzaResult = clienteTest.getIndirizzoDiResidenza();
		assertEquals(indirizzoDiResidenza, indirizzoDiResidenzaResult, "L'indirizzo di residenza dev'essere uguale");
	}

	@Test
	void test_getCittaDiResidenza() {

		String codiceFiscale = "DVNLRD52A15M059Z";
		String cognome = "da Vinci";
		String nome = "Leonardo";
		String cittaDiNascita = "Anchiano";
		Date dataDinascita = new Date(1452-04-15);
		String numeroDiTelefono = "3398965477852";
		String indirizzoDiResidenza = "Via Gioconda, n 1503, Anchiano, frazione di Vinci (FI)";
		String cittaDiResidenza = "Anchiano";
		Cliente clienteTest = null;
		try {
			clienteTest = new Cliente(codiceFiscale, cognome, nome, cittaDiNascita, dataDinascita, numeroDiTelefono, indirizzoDiResidenza, cittaDiResidenza);
		} catch (InputValidationException e) {
			System.out.println(e);;
		}
		String cittaDiResidenzaResult = clienteTest.getCittaDiResidenza();
		assertEquals(cittaDiResidenza, cittaDiResidenzaResult, "La citta di residenza dev'essere uguale");
	}

	@Test
	void test_equals() {

		String codiceFiscale = "DVNLRD52A15M059Z";
		String cognome = "da Vinci";
		String nome = "Leonardo";
		String cittaDiNascita = "Anchiano";
		Date dataDinascita = new Date(1452-04-15);
		String numeroDiTelefono = "3398965477852";
		String indirizzoDiResidenza = "Via Gioconda, n 1503, Anchiano, frazione di Vinci (FI)";
		String cittaDiResidenza = "Anchiano";
		Cliente clienteTest = null;
		Cliente clienteTestEquals = null;


		try {
			clienteTest = new Cliente(codiceFiscale, cognome, nome, cittaDiNascita, dataDinascita, numeroDiTelefono, indirizzoDiResidenza, cittaDiResidenza);
			clienteTestEquals = new Cliente("DVNLRD52A15M059Z", "da Vinci", "Leonardo", "Anchiano", new Date(1452-04-15), "3398965477852", "Via Gioconda, n 1503, Anchiano, frazione di Vinci (FI)", "Anchiano");		
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		assertEquals(clienteTest, clienteTestEquals, "I clienti devono essere uguali");
	}
}
