package BerBiaNic.homebanking.logica;

import java.sql.Date;
import java.util.TreeSet;

public class CartaPrepagata<T extends OperazionePrepagata> extends TreeSet<T> {

	private final int numero;
	private double saldoContabile;
	private double saldoDisponibile;
	private Date dataDiScadenza;
	private final int cvv;
	private final Account account;
	
	public CartaPrepagata(int numero, double saldoContabile, double saldoDisponibile, Date dataDiScadenza, int cvv, Account account) {
		this.numero = numero;
		this.saldoContabile = saldoContabile;
		this.saldoDisponibile = saldoDisponibile;
		this.dataDiScadenza = dataDiScadenza;
		this.cvv = cvv;
		this.account = account;
	}

	public int getNumero() {
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

	public Account getAccount() {
		return account;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CartaPrepagata))
			return false;
		CartaPrepagata other = (CartaPrepagata) obj;
		if (numero != other.numero)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Carta prepagata di " + account.getNome() + " " + account.getCognome() + "\nnumero: " + numero + ", saldo contabile: " + saldoContabile + ", saldo disponibile: " +
				"\ndata di scadenza: " + dataDiScadenza.toString() + ", cvv: " + cvv;
	}

	
	
	
}