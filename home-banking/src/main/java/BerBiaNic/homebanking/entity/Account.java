package BerBiaNic.homebanking.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;

import BerBiaNic.homebanking.exceptions.InputValidationException;
/**
 * 
 * @authors Antonino Bertuccio, Giuseppe Bianchino, Giovanni Nicotera
 *
 */
public class Account {

	private int id;
	private String username;
	private String password;
	private String email;
	private long improntaDigitale;
	private String dispositiviAssociati;
	private Cliente cliente;

	/**
	 * Crea un oggetto di tipo Account.
	 * 
	 * @param id							, numero identificativo dell'oggetto. Unico per tutte istanze di tipo Account;
	 * 
	 * @param username						, nome scelto dal cliente e utilizzato per far riconoscere il cliente dal sistema; 
	 * 										  Può essere composto da caratteri alfanumerici e i caratteri speciali "-" e "_";
	 * 
	 * @param password						, La password deve contenere un numero, un carattere minuscolo, uno maiuscolo, 
	 * 										  un carattere speciale tra @#$% e deve avere una lunghezza minima di 8 caratteri e massima di 20;
	 * 
	 * @param email							, indirizzo email associato all'account, può essere composto da massimo 35 caratteri alfanumerici a sinistra del carattere "@";
	 * 
	 * @param improntaDigitale				, impronta digitale del cliente associata all'account in modo da poter accedere al proprio account senza effettuare il login con username e password;
	 * 
	 * 
	 * @param dispositiviAssociati			, dispositivi associati all'account tramite i quali il cliente può accedere all'account;
	 * 
	 * @param cliente						, oggetto di tipo cliente associato all'account;
	 * 
	 * @throws InputValidationException		, eccezione lanciata dal sistema in caso di errore nell'inserimento dei parametri per la creazione di un oggetto di tipo Account.
	 */
	@JsonbCreator
	public Account(@JsonbProperty("id") int id, @JsonbProperty ("username") String username, @JsonbProperty ("password") String password, 
			@JsonbProperty("email") String email, @JsonbProperty("impronta_digitale") long improntaDigitale,
			@JsonbProperty ("dispositivi_associati") String dispositiviAssociati, @JsonbProperty ("cliente") Cliente cliente) throws InputValidationException {

		validazioneParametri(id, username, password, email, improntaDigitale, dispositiviAssociati, cliente);
		this.id = id;
		this.username = username.trim();
		this.password = password.trim();
		this.email = email.trim();
		this.improntaDigitale = improntaDigitale;
		this.dispositiviAssociati = dispositiviAssociati.trim();
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getNomeCognome() {
		return "Nome: " + cliente.getNome() +", Cognome: " + cliente.getCognome();
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public long getImprontaDigitale() {
		return improntaDigitale;
	}

	public String getDispositiviAssociati() {
		return dispositiviAssociati;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setImprontaDigitale(long improntaDigitale) {
		this.improntaDigitale = improntaDigitale;
	}

	public void setDispositiviAssociati(String dispositiviAssociati) {
		this.dispositiviAssociati = dispositiviAssociati;
	}
	
	private void validazioneParametri(int id, String username, String password, String email, long improntaDigitale, String dispositiviAssociati, Cliente cliente) throws InputValidationException {
		if(id <= 0)
			throw new InputValidationException("Id account");

		if(username == null || username.isBlank())
			throw new InputValidationException("Username");
		if (!username.matches("[\\w_-]{3,45}"))
			throw new InputValidationException("Formato username (caratteri speciali consentiti _-)");

		if(password == null || password.isBlank())
			throw new InputValidationException("Password");
		if(!password.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}")) 
			throw new InputValidationException("La password deve contenere un numero, un carattere minuscolo, "
					+ "uno maiuscolo, un carattere speciale tra @#$% e deve avere lunghezza min 8 e max 20");

		if(email == null || email.isBlank())
			throw new InputValidationException("Email account");
		if(!email.matches("[\\w_-]{5,35}+@[\\w]{2,11}+\\.[\\w]{2,4}"))
			throw new InputValidationException("Formato email account (caratteri speciali consentiti _-)");
		
		if(improntaDigitale <= 0)
			throw new InputValidationException("Impronta digitale");
		
		if(dispositiviAssociati == null || dispositiviAssociati.isBlank())
			throw new InputValidationException("Dispositivi associati");
		if(!dispositiviAssociati.matches("[\\w_-]{3,100}"))
			throw new InputValidationException("Formato dispositivi associati (caratteri speciali consentiti _-)");
		
		if(cliente == null)
			throw new InputValidationException("Cliente");		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nCliente: " + cliente + "\nID: " + id + "\nUsername: " + username + "\nPassword: " + password + "\nE-mail: " + email 
				+ "\nImpronta digitale: " + improntaDigitale + "\nDispositivi associati: " + dispositiviAssociati + "\n";
	}

	public String toJson() {
		JsonbConfig config = new JsonbConfig().withPropertyVisibilityStrategy(new PropertyVisibilityStrategy() {
			@Override
			public boolean isVisible(Method arg0) {
				return false;
			}
			@Override
			public boolean isVisible(Field arg0) {
				return true;
			}
		});
		return JsonbBuilder.newBuilder().withConfig(config).build().toJson(this);
	}
}
