package BerBiaNic.homebanking.logica;

import java.util.*;

public class ContoCorrente{

	private final int numero;
	private final String iban;
	private double saldo_disponibile;
	private double saldo_contabile;
	private final Account account;
	private Set<CartaDiDebito> listaCarteDiDebito;
	private Set<Operazione> insiemeOperazioni;

	public ContoCorrente(int numero, String iban, double saldo_disponibile, double saldo_contabile, Account account) {
		this.numero = numero;
		this.iban = iban;
		this.saldo_disponibile = saldo_disponibile;
		this.saldo_contabile = saldo_contabile;
		this.account = account;
		this.listaCarteDiDebito = new HashSet<CartaDiDebito>();
		this.insiemeOperazioni = new TreeSet<Operazione>();
	}
	
	public boolean inserisciCartaDiDebito(CartaDiDebito cdd) {
		return listaCarteDiDebito.add(cdd);
	}
	
	public boolean rimuoviCartaDiDebito(CartaDiDebito cdd) {
		return listaCarteDiDebito.remove(cdd);
	}
	
	public boolean inserisciOperazione(Operazione op) {
		return insiemeOperazioni.add(op);
	}
	
	public boolean rimuoviOperazione(Operazione op) {
		return insiemeOperazioni.remove(op);
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
	
	public Set<Operazione> getInsiemeOperazioni() {
		return insiemeOperazioni;
	}
	
	public List<CartaDiDebito> getListaCarteDiDebito(){
		return listaCarteDiDebito;
	}

	public void setSaldo_disponibile(double saldo_disponibile) {
		this.saldo_disponibile = saldo_disponibile;
	}

	public void setSaldo_contabile(double saldo_contabile) {
		this.saldo_contabile = saldo_contabile;
	}
	
	public void setInsiemeOperazioni(Set<Operazione> insiemeOperazioni) {
		this.insiemeOperazioni = insiemeOperazioni;
	}
	
	public void setListaCarteDiDebito(List<CartaDiDebito> listaCarteDiDebito) {
		this.listaCarteDiDebito = listaCarteDiDebito;
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
	
	
	

}
