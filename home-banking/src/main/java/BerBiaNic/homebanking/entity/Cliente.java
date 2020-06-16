package BerBiaNic.homebanking.entity;

import java.sql.Date;

public class Cliente {

	private final String codiceFiscale;
	private final String cognome;
	private final String nome;
	private final String cittaDiNascita;
	private final Date dataDinascita;
	private final String numeroDiTelefono;
	private final String indirizzoDiResidenza;
	private final String cittaDiResidenza;

	public Cliente(String codiceFiscale, String cognome, String nome, String cittaDiNascita, Date dataDinascita,
			String numeroDiTelefono, String indirizzoDiResidenza, String cittaDiResidenza) {
		this.codiceFiscale = codiceFiscale;
		this.cognome = cognome;
		this.nome = nome;
		this.cittaDiNascita = cittaDiNascita;
		this.dataDinascita = dataDinascita;
		this.numeroDiTelefono = numeroDiTelefono;
		this.indirizzoDiResidenza = indirizzoDiResidenza;
		this.cittaDiResidenza = cittaDiResidenza;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}

	public String getCittaDiNascita() {
		return cittaDiNascita;
	}

	public Date getDataDinascita() {
		return dataDinascita;
	}

	public String getNumeroDiTelefono() {
		return numeroDiTelefono;
	}

	public String getIndirizzoDiResidenza() {
		return indirizzoDiResidenza;
	}

	public String getCittaDiResidenza() {
		return cittaDiResidenza;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceFiscale == null) ? 0 : codiceFiscale.hashCode());
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
		Cliente other = (Cliente) obj;
		if (codiceFiscale == null) {
			if (other.codiceFiscale != null)
				return false;
		} else if (!codiceFiscale.equals(other.codiceFiscale))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nCodice Fiscale: " + codiceFiscale + "\nCognome: " + cognome + "\nNome: " + nome + "\nCitta di nascita: " + cittaDiNascita + 
				"\nData di nascita: " + dataDinascita + "\nNumero di telefono: " + numeroDiTelefono + "\nIndirizzo di residenza: " + indirizzoDiResidenza
				+ "\nCitta di residenza: " + cittaDiResidenza + "\n";
	}
}
