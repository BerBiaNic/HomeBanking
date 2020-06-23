package BerBiaNic.homebanking.db.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import BerBiaNic.homebanking.entity.Account;
import BerBiaNic.homebanking.entity.Cliente;
import BerBiaNic.homebanking.exceptions.InputValidationException;


class AccountTest {

	@Test
	void test_notNullObjects() {

		int id = 1234;
		String username = "isaac_newton1727";
		String password = "25#Dicembre#1642";
		String email = "isaac-newton@royalsoc.uk";
		long improntaDigitale = 31031727;
		String dispositiviAssociati = "apple-iphoneXs_2156";
		Cliente cliente= null;
		Account accountTest = null;

		try {
			cliente = new Cliente("ISCNTW43L25E772N", "Newton", "Isaac", "Woolsthorpe Manor, Regno Unito", new Date(25-12-1642), "3336088456", 
					" Via Philosophiae Naturalis Principia Mathematica,n 1687, London", "Woolsthorpe Manor");
			accountTest = new Account(id, username, password, email, improntaDigitale, dispositiviAssociati, cliente);
		} catch (InputValidationException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		Assert.assertNotNull("L'oggetto cliente non dev'essere null", cliente);
		Assert.assertNotNull("L'oggetto account non dev'essere null", accountTest);
	}

	@Test
	void test_getId() {
		int id = 1234;
		String username = "isaac_newton1727";
		String password = "25#Dicembre#1642";
		String email = "isaac-newton@royalsoc.uk";
		long improntaDigitale = 31031727;
		String dispositiviAssociati = "apple-iphoneXs_2156";
		Cliente cliente= null;
		Account accountTest = null;

		try {
			cliente = new Cliente("ISCNTW43L25E772N", "Newton", "Isaac", "Woolsthorpe Manor, Regno Unito", new Date(25-12-1642), "3336088456", 
					"Via Philosophiae Naturalis Principia Mathematica,n 1687, London", "Woolsthorpe Manor");
			accountTest = new Account(id, username, password, email, improntaDigitale, dispositiviAssociati, cliente);
		} catch (InputValidationException e) {
			e.printStackTrace();
		}
		int idResult = accountTest.getId();
		assertEquals(id, idResult, "L'id dev'essere uguale"); 
	}

	@Test
	void test_getUsername() {
		int id = 1234;
		String username = "isaac_newton1727";
		String password = "25#Dicembre#1642";
		String email = "isaac-newton@royalsoc.uk";
		long improntaDigitale = 31031727;
		String dispositiviAssociati = "apple-iphoneXs_2156";
		Cliente cliente= null;
		Account accountTest = null;

		try {
			cliente = new Cliente("ISCNTW43L25E772N", "Newton", "Isaac", "Woolsthorpe Manor, Regno Unito", new Date(25-12-1642), "3336088456", 
					"Via Philosophiae Naturalis Principia Mathematica,n 1687, London", "Woolsthorpe Manor");
			accountTest = new Account(id, username, password, email, improntaDigitale, dispositiviAssociati, cliente);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		String usernameResult = accountTest.getUsername();
		assertEquals(username, usernameResult, "Lo username dev'essere uguale"); 
	}

	@Test
	void test_getPassword() {
		int id = 1234;
		String username = "isaac_newton1727";
		String password = "25#Dicembre#1642";
		String email = "isaac-newton@royalsoc.uk";
		long improntaDigitale = 31031727;
		String dispositiviAssociati = "apple-iphoneXs_2156";
		Cliente cliente= null;
		Account accountTest = null;

		try {
			cliente = new Cliente("ISCNTW43L25E772N", "Newton", "Isaac", "Woolsthorpe Manor, Regno Unito", new Date(25-12-1642), "3336088456", 
					"Via Philosophiae Naturalis Principia Mathematica,n 1687, London", "Woolsthorpe Manor");
			accountTest = new Account(id, username, password, email, improntaDigitale, dispositiviAssociati, cliente);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		String passwordResult = accountTest.getPassword();
		assertEquals(password, passwordResult, "La password dev'essere uguale"); 
	}

	@Test
	void test_getEmail() {
		int id = 1234;
		String username = "isaac_newton1727";
		String password = "25#Dicembre#1642";
		String email = "isaac-newton@royalsoc.uk";
		long improntaDigitale = 31031727;
		String dispositiviAssociati = "apple-iphoneXs_2156";
		Cliente cliente= null;
		Account accountTest = null;

		try {
			cliente = new Cliente("ISCNTW43L25E772N", "Newton", "Isaac", "Woolsthorpe Manor, Regno Unito", new Date(25-12-1642), "3336088456", 
					"Via Philosophiae Naturalis Principia Mathematica,n 1687, London", "Woolsthorpe Manor");
			accountTest = new Account(id, username, password, email, improntaDigitale, dispositiviAssociati, cliente);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		String emailResult = accountTest.getEmail();
		assertEquals(email, emailResult, "L'email dev'essere uguale"); 
	}

	@Test
	void test_getImprontaDigitale() {
		int id = 1234;
		String username = "isaac_newton1727";
		String password = "25#Dicembre#1642";
		String email = "isaac-newton@royalsoc.uk";
		long improntaDigitale = 31031727;
		String dispositiviAssociati = "apple-iphoneXs_2156";
		Cliente cliente= null;
		Account accountTest = null;

		try {
			cliente = new Cliente("ISCNTW43L25E772N", "Newton", "Isaac", "Woolsthorpe Manor, Regno Unito", new Date(25-12-1642), "3336088456", 
					"Via Philosophiae Naturalis Principia Mathematica,n 1687, London", "Woolsthorpe Manor");
			accountTest = new Account(id, username, password, email, improntaDigitale, dispositiviAssociati, cliente);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		long improntaDigitaleResult = accountTest.getImprontaDigitale();
		assertEquals(improntaDigitale, improntaDigitaleResult, "L'impronta digitale dev'essere uguale"); 
	}

	@Test
	void test_getDispositiviAssociati() {
		int id = 1234;
		String username = "isaac_newton1727";
		String password = "25#Dicembre#1642";
		String email = "isaac-newton@royalsoc.uk";
		long improntaDigitale = 31031727;
		String dispositiviAssociati = "apple-iphoneXs_2156";
		Cliente cliente= null;
		Account accountTest = null;

		try {
			cliente = new Cliente("ISCNTW43L25E772N", "Newton", "Isaac", "Woolsthorpe Manor, Regno Unito", new Date(25-12-1642), "3336088456", 
					"Via Philosophiae Naturalis Principia Mathematica,n 1687, London", "Woolsthorpe Manor");
			accountTest = new Account(id, username, password, email, improntaDigitale, dispositiviAssociati, cliente);
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		String dispositiviAssociatiResult = accountTest.getDispositiviAssociati();
		assertEquals(dispositiviAssociati, dispositiviAssociatiResult, "I dispositivi associati devono essere uguale");
	}

	@Test
	void test_equals() {	

		int id = 1234;
		String username = "isaac_newton1727";
		String password = "25#Dicembre#1642";
		String email = "isaac-newton@royalsoc.uk";
		long improntaDigitale = 31031727;
		String dispositiviAssociati = "apple-iphoneXs_2156";
		Cliente cliente= null;
		Account accountTest = null;
		Account accountTestEquals = null;

		try {
			cliente = new Cliente("ISCNTW43L25E772N", "Newton", "Isaac", "Woolsthorpe Manor, Regno Unito", new Date(25-12-1642), "3336088456", 
					"Via Philosophiae Naturalis Principia Mathematica,n 1687, London", "Woolsthorpe Manor");
			accountTest = new Account(id, username, password, email, improntaDigitale, dispositiviAssociati, cliente);
			accountTestEquals = new Account(1234, "isaac_newton1727", "25#Dicembre#1642", "isaac-newton@royalsoc.uk", 31031727, "apple-iphoneXs_2156", cliente);
			
		} catch (InputValidationException e) {
			System.out.println(e);
		}
		assertEquals(accountTest, accountTestEquals, "Gli account devono essere uguali");
	}
}
