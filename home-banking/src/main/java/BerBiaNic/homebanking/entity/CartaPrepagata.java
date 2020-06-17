package BerBiaNic.homebanking.entity;

import java.sql.Date;

public class CartaPrepagata {

	private int numero;
	private double saldoContabile;
	private double saldoDisponibile;
	private Date dataDiScadenza;
	private int cvv;
	private Account account;
	
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

	public void setNumero(int numero) {
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
		return "Carta prepagata di " + account.getNomeCognome() + "\nnumero: " + numero + ", saldo contabile: " + saldoContabile + ", saldo disponibile: " +
				"\ndata di scadenza: " + dataDiScadenza.toString() + ", cvv: " + cvv;
	}
}
