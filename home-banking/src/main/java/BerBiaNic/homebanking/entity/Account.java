package BerBiaNic.homebanking.entity;

import java.util.HashSet;
import java.util.Set;

public class Account {

	private Cliente cliente;
	private int id;
	private String username;
	private String password;
	private String email;
	private long improntaDigitale;
	private String dispositiviAssociati;
	private String ibanContoCorrente;

	public Account(Cliente cliente, int id, String username, String password, String email, long improntaDigitale,
			String dispositiviAssociati) {
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

	public String getIbanContoCorrente() {
		return ibanContoCorrente;
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

	public void setIbanContoCorrente(String ibanContoCorrente) {
		this.ibanContoCorrente = ibanContoCorrente;
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
				+ "\nImpronta digitale: " + improntaDigitale + "\nDispositivi associati: " + dispositiviAssociati + "\nIBAN: " + ibanContoCorrente;
	}
}
