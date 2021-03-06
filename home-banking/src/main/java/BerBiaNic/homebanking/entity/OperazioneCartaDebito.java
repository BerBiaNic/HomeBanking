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
public class OperazioneCartaDebito implements Comparable<OperazioneCartaDebito> {

	private int id;
	private Date data;
	private double importo;
	private String tipologia;
	private CartaDiDebito carta_proprietario;
	private String carta_beneficiario;

	/**
	 * Crea un oggetto di tipo OperazioneCartaDebito.
	 * 
	 * @param id							, numero identificativo dell'oggetto. Unico per tutte istanze di tipo OperazioneCartaDebito;
	 * 
	 * @param data							, data dell'operazione associata alla carta di debito;
	 * 
	 * @param importo						, importo dell'operazione associata alla carta di debito;
	 * 
	 * @param tipologia						, tipologia dell'operazione associata alla carta di debito;
	 * 
	 * @param carta_proprietario			, oggetto di tipo carta di debito del cliente che effettua l'operazione;
	 * 
	 * @param carta_beneficiario			, codice iban per l'identificazione del conto corrente beneficiario dell'operazione;
	 * 
	 * @throws InputValidationException		, eccezione lanciata dal sistema in caso di errore nell'inserimento dei parametri per la creazione di un oggetto di tipo Account.
	 */
	@JsonbCreator
	public OperazioneCartaDebito(@JsonbProperty("id") int id, @JsonbProperty("data") Date data, @JsonbProperty("importo") double importo, 
			@JsonbProperty("tipologia") String tipologia, @JsonbProperty("carta_proprietario") CartaDiDebito carta_proprietario,
			@JsonbProperty("carta_beneficiario") String carta_beneficiario) throws InputValidationException {

		validazioneParametri(id, data, importo, tipologia, carta_proprietario, carta_beneficiario);
		this.id = id;
		this.data = data;
		this.importo = importo;
		this.tipologia = tipologia;
		this.carta_proprietario = carta_proprietario;
		this.carta_beneficiario = carta_beneficiario;
	}

	public int getId() {
		return id;
	}

	public Date getData() {
		return data;
	}

	public double getImporto() {
		return importo;
	}

	public String getTipologia() {
		return tipologia;
	}


	public CartaDiDebito getCarta_proprietario() {
		return carta_proprietario;
	}

	public String getCarta_beneficiario() {
		return carta_beneficiario;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public void setCarta_proprietario(CartaDiDebito carta_proprietario) {
		this.carta_proprietario = carta_proprietario;
	}

	public void setCarta_beneficiario(String carta_beneficiario) {
		this.carta_beneficiario = carta_beneficiario;
	}
	
	private void validazioneParametri(int id, Date data, double importo, String tipologia, CartaDiDebito carta_proprietario, String carta_beneficiario) throws InputValidationException {
		if(id < 0)
			throw new InputValidationException("Id Operazione carta di debito", Response.Status.METHOD_NOT_ALLOWED);

		if(data == null)
			throw new InputValidationException("Data operazione carta di debito", Response.Status.METHOD_NOT_ALLOWED);

		if(importo < 0)
			throw new InputValidationException("Importo operazione carta di debito", Response.Status.METHOD_NOT_ALLOWED);

		if(tipologia == null || tipologia.isBlank())
			throw new InputValidationException("Tipologia operazione carta di debito", Response.Status.METHOD_NOT_ALLOWED);
		if(tipologia.length() > 45)
			throw new InputValidationException("Tipologia carta di debito non valida. Numero massimo caratteri consentiti 45, inseriti: " + tipologia.length(), Response.Status.METHOD_NOT_ALLOWED);
		if(!tipologia.matches("[\\w,: ]{2,45}")) 
			throw new InputValidationException("Tipologia carta di debito. Caratteri speciali consentiti (,:)", Response.Status.METHOD_NOT_ALLOWED);
		
		if(carta_proprietario == null)
			throw new InputValidationException("Carta di debito proprietario", Response.Status.METHOD_NOT_ALLOWED);
		
		if (carta_beneficiario == null || carta_beneficiario.isBlank())
			throw new InputValidationException("Carta di debito beneficiario", Response.Status.METHOD_NOT_ALLOWED);
		if(carta_beneficiario.length() != 27)
			throw new InputValidationException("Carta di debito beneficiario non valida. Caratteri richiesti 27, inseriti: " + carta_beneficiario.length(), Response.Status.METHOD_NOT_ALLOWED);
		if(!carta_beneficiario.matches("IT+\\d{2}+[a-zA-Z]+\\d{22}"))
			throw new InputValidationException("Formato carta di debito beneficiario non valido (esempio inserimento  IT28W8000000292100645211151)", Response.Status.METHOD_NOT_ALLOWED);
	}
	
	@Override
	public int hashCode() {
		int hash = 37;
		hash = hash * 37 + id;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof OperazioneCartaDebito))
			return false;
		OperazioneCartaDebito other = (OperazioneCartaDebito) obj;
		return id==other.id;
	}

	@Override
	public String toString() {
		return " --------------------- OPERAZIONE ---------------------\n "
				+ "id = " + id + ", \tdata = " + data + ", \ttipologia = " + tipologia + "\n";
	}

	@Override
	public int compareTo(OperazioneCartaDebito o) {
		return this.data.compareTo(data);
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
