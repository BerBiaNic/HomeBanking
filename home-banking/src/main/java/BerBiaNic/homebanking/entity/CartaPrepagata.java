package BerBiaNic.homebanking.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;

import BerBiaNic.homebanking.exceptions.InputValidationException;

public class CartaPrepagata {

	private String numero;
	private double saldoContabile;
	private double saldoDisponibile;
	private Date dataDiScadenza;
	private int cvv;
	private int pin;
	private Account account;

	@JsonbCreator
	public CartaPrepagata(@JsonbProperty("numero") String numero, @JsonbProperty("saldo_contabile") double saldoContabile, @JsonbProperty("saldo_disponibile") double saldoDisponibile, 
			@JsonbProperty("data_di_sadenza") Date dataDiScadenza, @JsonbProperty("cvv") int cvv, @JsonbProperty("pin") int pin, @JsonbProperty("account")Account account) throws InputValidationException {

		if(numero == null || numero.isBlank())
			throw new InputValidationException("Numero carta prepagata");
		if(numero.length() != 16)
			throw new InputValidationException("Numero carta prepagata. Caratteri richiesti 16, inseriti: ", numero.length());
		if(!numero.matches("[\\d]*")) 
			throw new InputValidationException("Formato numero carta prepagata (esempio inserimento 1234569874521456).");

		if(saldoContabile < 0)
			throw new InputValidationException("Saldo contabile carta prepagata");

		if(saldoDisponibile < 0)
			throw new InputValidationException("Saldo disponibile carta prepagata");

		if(dataDiScadenza == null)
			throw new InputValidationException("Data di scadenza carta prepagata");

		if(cvv < 100 || cvv > 999)
			throw new InputValidationException("Codice cvv carta prepagata");
		
		if(pin < 100000)
			throw new InputValidationException("Codice PIN carta prepagata");
			
		if(account == null)
			throw new InputValidationException("Account associato alla carta prepagata");

		this.numero = numero;
		this.saldoContabile = saldoContabile;
		this.saldoDisponibile = saldoDisponibile;
		this.dataDiScadenza = dataDiScadenza;
		this.cvv = cvv;
		this.pin = pin;
		this.account = account;
	}

	public String getNumero() {
		return numero;
	}

	public double getSaldoContabile() {
		return saldoContabile;
	}

	public double getSaldoDisponibile() {
		return saldoDisponibile;
	}

	public Date getDataDiScadenza() {
		return dataDiScadenza;
	}

	public int getCvv() {
		return cvv;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public Account getAccount() {
		return account;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setSaldoContabile(double saldoContabile) {
		this.saldoContabile = saldoContabile;
	}

	public void setSaldoDisponibile(double saldoDisponibile) {
		this.saldoDisponibile = saldoDisponibile;
	}

	public void setDataDiScadenza(Date dataDiScadenza) {
		this.dataDiScadenza = dataDiScadenza;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Carta prepagata di " + account.getNomeCognome() + "\nnumero: " + numero + ", saldo contabile: " + saldoContabile + ", saldo disponibile: " +
				"\ndata di scadenza: " + dataDiScadenza.toString() + ", cvv: " + cvv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CartaPrepagata))
			return false;
		CartaPrepagata other = (CartaPrepagata) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
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
