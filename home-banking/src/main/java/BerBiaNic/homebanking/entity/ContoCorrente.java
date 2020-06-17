package BerBiaNic.homebanking.entity;


public class ContoCorrente{

	private int numero;
	private String iban;
	private double saldo_disponibile;
	private double saldo_contabile;
	private Account account;

	public ContoCorrente(int numero, String iban, double saldo_disponibile, double saldo_contabile, Account account) {
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
