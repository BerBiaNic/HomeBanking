package BerBiaNic.homebanking.logica;

import java.sql.Date;
import java.util.*;

public class CartaDiDebito {
	
	private final int numero;
	private final String iban;
	private final Date data_di_scadenza;
	private final int cvv;
	private final int pin;
	private final ContoCorrente conto_corrente;
	private Set<Operazione> insiemeOperazioni;
	
	public CartaDiDebito(int numero, String iban, Date data_di_scadenza, int cvv, int pin,
			ContoCorrente conto_corrente) {
		this.numero = numero;
		this.iban = iban;
		this.data_di_scadenza = data_di_scadenza;
		this.cvv = cvv;
		this.pin = pin;
		this.conto_corrente = conto_corrente;
		this.insiemeOperazioni = new TreeSet<Operazione>();
	}
	
	public boolean aggiungiOperazione(Operazione op) {
		return insiemeOperazioni.add(op);
	}
	
	public boolean rimuoviOperazione(Operazione op) {
		return insiemeOperazioni.remove(op);
	}


	public Set<Operazione> getInsiemeOperazioni() {
		return insiemeOperazioni;
	}

	public int getNumero() {
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
	
	public void setInsiemeOperazioni(Set<Operazione> insiemeOperazioni) {
		this.insiemeOperazioni = insiemeOperazioni;
	}
	
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
		if (!(obj instanceof CartaDiDebito))
			return false;
		CartaDiDebito other = (CartaDiDebito) obj;
		return numero==other.numero;
	}
	
	@Override
	public String toString() {
		return " --------------------- CARTA DI DEBITO ---------------------\n "
				+ "numero = " + numero + ", \tiban = " + iban 
				+ ", \tdata di scadenza = " + data_di_scadenza.getDay() + "/" + data_di_scadenza.getMonth() 
				+ ", \tcvv = " + cvv + "\tpin = " + pin + "\n";
	}	
	
}
