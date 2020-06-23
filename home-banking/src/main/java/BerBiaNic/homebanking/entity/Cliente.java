package BerBiaNic.homebanking.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;
import javax.ws.rs.core.Response;

import BerBiaNic.homebanking.exceptions.InputValidationException;
/**
 * 
 * @authors Antonino Bertuccio, Giuseppe Bianchino, Giovanni Nicotera
 *
 */
public class Cliente {

	private String codiceFiscale;
	private String cognome;
	private String nome;
	private String cittaDiNascita;
	private Date dataDinascita;
	private String numeroDiTelefono;
	private String indirizzoDiResidenza;
	private String cittaDiResidenza;

	/**
	 * Crea un oggetto di tipo Cliente.
	 * 
	 * @param codiceFiscale					, codice fiscale del cliente;
	 * 
	 * @param cognome						, cognome del cliente;
	 *
	 * @param nome							, nome del cliente;
	 * 
	 * @param cittaDiNascita				, città di nascita del cliente;
	 * 
	 * @param dataDinascita					, data di nascita del cliente;
	 * 
	 * @param numeroDiTelefono				, numero di telefono del cliente;
	 * 
	 * @param indirizzoDiResidenza			, indirizzo di residenza del cliente;
	 * 
	 * @param cittaDiResidenza				, città di residenza del cliente;
	 * 
	 * @throws InputValidationException		, eccezione lanciata dal sistema in caso di errore nell'inserimento dei parametri per la creazione di un oggetto di tipo Account.
	 */
	@JsonbCreator
	public Cliente(@JsonbProperty("codice_fiscale") String codiceFiscale, @JsonbProperty("cognome") String cognome, @JsonbProperty("nome") String nome, 
			@JsonbProperty("citta_di_nascita") String cittaDiNascita, @JsonbProperty("data_di_nascita") Date dataDinascita,
			@JsonbProperty("numero_di_telefono") String numeroDiTelefono, @JsonbProperty("indirizzo_di_residenza") String indirizzoDiResidenza, 
			@JsonbProperty("citta_di_residenza") String cittaDiResidenza) throws InputValidationException {

		validazioneParametri(codiceFiscale, cognome, nome, cittaDiNascita, dataDinascita, numeroDiTelefono, indirizzoDiResidenza, cittaDiResidenza);
		this.codiceFiscale = codiceFiscale.trim();
		this.cognome = cognome.trim();
		this.nome = nome.trim();
		this.cittaDiNascita = cittaDiNascita.trim();
		this.dataDinascita = dataDinascita;
		this.numeroDiTelefono = numeroDiTelefono.trim();
		this.indirizzoDiResidenza = indirizzoDiResidenza.trim();
		this.cittaDiResidenza = cittaDiResidenza.trim();
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

	private void validazioneParametri(String codiceFiscale, String cognome, String nome, String cittaDiNascita, 
			Date dataDinascita, String numeroDiTelefono, String indirizzoDiResidenza, String cittaDiResidenza) throws InputValidationException {
		if(codiceFiscale == null || codiceFiscale.isBlank())
			throw new InputValidationException("Codice fiscale", Response.Status.METHOD_NOT_ALLOWED);
		if(codiceFiscale.length() != 16)
			throw new InputValidationException("Codice fiscale non valido. Caratteri richiesti 16, inseriti: " + codiceFiscale.length(), Response.Status.METHOD_NOT_ALLOWED);
		if(!codiceFiscale.matches("[a-zA-Z]{6}\\d{2}[a-zA-Z]\\d{2}[a-zA-Z]\\d{3}[a-zA-Z]"))  
			throw new InputValidationException("Formato codice fiscale (esempio inserimento CTTLMN86B09T0659X)", Response.Status.METHOD_NOT_ALLOWED);

		if(cognome == null || cognome.isBlank())
			throw new InputValidationException("Cognome", Response.Status.METHOD_NOT_ALLOWED);
		if (!cognome.matches("[a-zA-Zàèò, ]{2,45}")) 
			throw new InputValidationException("Cognome errato, caratteri consentiti da 2 a 45, inseriti: " + cognome.length(), Response.Status.METHOD_NOT_ALLOWED);

		if(nome == null || nome.isBlank())
			throw new InputValidationException("Nome", Response.Status.METHOD_NOT_ALLOWED);
		if (!nome.matches("[a-zA-Zàèò, ]{2,45}"))
			throw new InputValidationException("Nome errato, caratteri richiesti da 2 a 45, inseriti: " + nome.length(), Response.Status.METHOD_NOT_ALLOWED);

		if(cittaDiNascita == null || cittaDiNascita.isBlank())
			throw new InputValidationException("Città di nascita", Response.Status.METHOD_NOT_ALLOWED);
		if (!cittaDiNascita.matches("[a-zA-Zàèò, ]{2,45}"))
			throw new InputValidationException("Città di nascita errata, caratteri massimi consentiti 45, inseriti: " + cittaDiNascita.length(), Response.Status.METHOD_NOT_ALLOWED);

		if(dataDinascita == null)
			throw new InputValidationException("Data di nascita (aaaa-mm-gg)", Response.Status.METHOD_NOT_ALLOWED);

		if(numeroDiTelefono == null || numeroDiTelefono.isBlank())
			throw new InputValidationException("Numero di Telefono", Response.Status.METHOD_NOT_ALLOWED);
		if(!numeroDiTelefono.matches("[0-9]{8,15}")) 
			throw new InputValidationException("Numero di Telefono, formato errato", Response.Status.METHOD_NOT_ALLOWED);

		if(indirizzoDiResidenza == null || indirizzoDiResidenza.isBlank())
			throw new InputValidationException("Indirizzo di residenza", Response.Status.METHOD_NOT_ALLOWED);
		if(!indirizzoDiResidenza.matches("[\\w()���', ]{5,100}"))
			throw new InputValidationException("Indirizzo di residenza", Response.Status.METHOD_NOT_ALLOWED);

		if(cittaDiResidenza == null || cittaDiResidenza.isBlank())
			throw new InputValidationException("Indirizzo di residenza", Response.Status.METHOD_NOT_ALLOWED);
		if(!cittaDiResidenza.matches("[\\w()���', ]{5,100}"))
			throw new InputValidationException("Indirizzo di residenza errato, caratteri massimi consentiti 100, inseriti: " + cittaDiResidenza.length(), Response.Status.METHOD_NOT_ALLOWED);
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
