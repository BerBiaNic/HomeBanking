package BerBiaNic.homebanking.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;
import javax.ws.rs.core.Response;

import BerBiaNic.homebanking.exceptions.InputValidationException;
/**
 * 
 * @authors Antonino Bertuccio, Giuseppe Bianchino, Giovanni Nicotera
 *
 */
public class ContoCorrente{

	private int numero;
	private String iban;
	private double saldo_disponibile;
	private double saldo_contabile;
	private Account account;

	/**
	 * Crea un oggetto di tipo ContoCorrente.
	 * 
	 * @param numero						, numero identificativo dell'oggetto. Unico per tutte istanze di tipo ContoCorrente;
	 * 
	 * @param iban							, codice per l'identificazione del conto corrente, necessario per eseguire tutte le operazioni;
	 * 
	 * @param saldo_disponibile				, Somma algebrica tra le entrate e le uscite registrate sull’estratto conto in una data specifica. 
	 * 										  Si utilizza il termine “contabile” per specificare che le operazioni effettuate sono state contabilizzate 
	 * 										  sul conto corrente ma non sono ancora effettive per il correntista.
	 * 
	 * @param saldo_contabile				, somma effettivamente a disposizione del correntista;
	 * 
	 * @param account						, oggetto di tipo account associato al conto corrente;
	 * 
	 * @throws InputValidationException		, eccezione lanciata dal sistema in caso di errore nell'inserimento dei parametri per la creazione di un oggetto di tipo Account.
	 */
	@JsonbCreator
	public ContoCorrente(@JsonbProperty("numero") int numero, @JsonbProperty("iban") String iban, @JsonbProperty("saldo_disponibile") double saldo_disponibile, 
			@JsonbProperty("saldo_contabile") double saldo_contabile, @JsonbProperty("account") Account account) throws InputValidationException {

		validazioneParametri(numero, iban, saldo_disponibile, saldo_contabile, account);
		this.numero = numero;
		this.iban = iban;
		this.saldo_disponibile = saldo_disponibile;
		this.saldo_contabile = saldo_contabile;
		this.account = account;
	}

	public int getNumero() {
		return numero;
	}

	public String getIban() {
		return iban;
	}

	public double getSaldo_disponibile() {
		return saldo_disponibile;
	}

	public double getSaldo_contabile() {
		return saldo_contabile;
	}

	public Account getAccount() {
		return account;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public void setSaldo_disponibile(double saldo_disponibile) {
		this.saldo_disponibile = saldo_disponibile;
	}

	public void setSaldo_contabile(double saldo_contabile) {
		this.saldo_contabile = saldo_contabile;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	private void validazioneParametri(int numero, String iban, double saldo_disponibile, double saldo_contabile, Account account) throws InputValidationException {
		if(numero < 100000)
			throw new InputValidationException("Numero conto corrente", Response.Status.METHOD_NOT_ALLOWED);

		if (iban == null || iban.isBlank())
			throw new InputValidationException("IBAN conto corrente", Response.Status.METHOD_NOT_ALLOWED);
		if(iban.length() != 31)
			throw new InputValidationException("IBAN conto corrente non valido. Caratteri richiesti 27, inseriti: " + iban.length(), Response.Status.METHOD_NOT_ALLOWED);

		if(!iban.matches("IT+\\d{2}+[a-zA-Z]+\\d{22}"))
			throw new InputValidationException("Formato IBAN conto corrente (esempio inserimento IT28W8000000292100645211151)", Response.Status.METHOD_NOT_ALLOWED);

		if(saldo_contabile < 0)
			throw new InputValidationException("Saldo contabile", Response.Status.METHOD_NOT_ALLOWED);

		if(saldo_disponibile < 0)
			throw new InputValidationException("Saldo disponibile", Response.Status.METHOD_NOT_ALLOWED);

		if (account == null)
			throw new InputValidationException("Account associato al conto corrente", Response.Status.METHOD_NOT_ALLOWED);
	}

	@Override
	public int hashCode() {
		int hash = 37;
		hash = hash * 37 + numero;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ContoCorrente))
			return false;
		ContoCorrente other = (ContoCorrente) obj;
		return numero==other.numero;
	}

	@Override
	public String toString() {
		return " --------------------- CONTO CORRENTE ---------------------\n "
				+ "numero = " + numero + ", \tiban = " + iban + ", \tsaldo_disponibile = " + saldo_disponibile
				+ ", \tsaldo_contabile = " + saldo_contabile + "\n";
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
