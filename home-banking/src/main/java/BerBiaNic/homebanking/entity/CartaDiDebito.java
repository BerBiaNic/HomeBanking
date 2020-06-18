package BerBiaNic.homebanking.entity;

import java.sql.Date;
import java.util.*;

public class CartaDiDebito {
	
	private String numero;
	private String iban;
	private Date data_di_scadenza;
	private int cvv;
	private int pin;
	private ContoCorrente conto_corrente;
	
	public CartaDiDebito(String numero, String iban, Date data_di_scadenza, int cvv, int pin,
			ContoCorrente conto_corrente) {
		this.numero = numero;
		this.iban = iban;
		this.data_di_scadenza = data_di_scadenza;
		this.cvv = cvv;
		this.pin = pin;
		this.conto_corrente = conto_corrente;
	}

	public String getNumero() {
		return numero;
	}

	public String getIban() {
		return iban;
	}

	public Date getData_di_scadenza() {
		return data_di_scadenza;
	}

	public int getCvv() {
		return cvv;
	}

	public int getPin() {
		return pin;
	}

	public ContoCorrente getConto_corrente() {
		return conto_corrente;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public void setData_di_scadenza(Date data_di_scadenza) {
		this.data_di_scadenza = data_di_scadenza;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public void setConto_corrente(ContoCorrente conto_corrente) {
		this.conto_corrente = conto_corrente;
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartaDiDebito other = (CartaDiDebito) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		}
		return numero.equals(other.numero);
	}

	@Override
	public String toString() {
		return " --------------------- CARTA DI DEBITO ---------------------\n "
				+ "numero = " + numero + ", \tiban = " + iban 
				+ ", \tdata di scadenza = " + data_di_scadenza.getDay() + "/" + data_di_scadenza.getMonth() 
				+ ", \tcvv = " + cvv + "\tpin = " + pin + "\n";
	}	
	
}
