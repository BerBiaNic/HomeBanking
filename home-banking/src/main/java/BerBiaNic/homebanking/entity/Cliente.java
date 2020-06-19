package BerBiaNic.homebanking.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;

public class Cliente {

	private String codiceFiscale;
	private String cognome;
	private String nome;
	private String cittaDiNascita;
	private Date dataDinascita;
	private String numeroDiTelefono;
	private String indirizzoDiResidenza;
	private String cittaDiResidenza;

	@JsonbCreator
	public Cliente(@JsonbProperty("codice_fiscale") String codiceFiscale, @JsonbProperty("cognome") String cognome, @JsonbProperty("nome") String nome, 
			@JsonbProperty("citta_di_nascita") String cittaDiNascita, @JsonbProperty("data_di_nascita") Date dataDinascita,
			@JsonbProperty("numero_di_telefono") String numeroDiTelefono, @JsonbProperty("indirizzo_di_residenza") String indirizzoDiResidenza, 
			@JsonbProperty("citta_di_residenza") String cittaDiResidenza) {
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

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCittaDiNascita(String cittaDiNascita) {
		this.cittaDiNascita = cittaDiNascita;
	}

	public void setDataDinascita(Date dataDinascita) {
		this.dataDinascita = dataDinascita;
	}

	public void setNumeroDiTelefono(String numeroDiTelefono) {
		this.numeroDiTelefono = numeroDiTelefono;
	}

	public void setIndirizzoDiResidenza(String indirizzoDiResidenza) {
		this.indirizzoDiResidenza = indirizzoDiResidenza;
	}

	public void setCittaDiResidenza(String cittaDiResidenza) {
		this.cittaDiResidenza = cittaDiResidenza;
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
