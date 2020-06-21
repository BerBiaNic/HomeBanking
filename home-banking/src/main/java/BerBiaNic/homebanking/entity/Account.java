package BerBiaNic.homebanking.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;

import BerBiaNic.homebanking.exceptions.InputValidationException;

public class Account {

	private int id;
	private String username;
	private String password;
	private String email;
	private long improntaDigitale;
	private String dispositiviAssociati;
	private Cliente cliente;

	@JsonbCreator
	public Account(@JsonbProperty("id") int id, @JsonbProperty ("username") String username, @JsonbProperty ("password") String password, 
			@JsonbProperty("email") String email, @JsonbProperty("impronta_digitale") long improntaDigitale,
			@JsonbProperty ("dispositivi_associati") String dispositiviAssociati, @JsonbProperty ("cliente") Cliente cliente) throws InputValidationException {

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
		
		this.cliente = cliente;
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.improntaDigitale = improntaDigitale;
		this.dispositiviAssociati = dispositiviAssociati;
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
