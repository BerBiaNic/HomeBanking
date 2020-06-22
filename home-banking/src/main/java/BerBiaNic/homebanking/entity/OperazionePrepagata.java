package BerBiaNic.homebanking.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;

import BerBiaNic.homebanking.exceptions.InputValidationException;
/**
 * 
 * @authors Antonino Bertuccio, Giuseppe Bianchino, Giovanni Nicotera
 *
 */
public class OperazionePrepagata implements Comparable<OperazionePrepagata>{

	private int id;
	private Date data;
	private double importo;
	private String tipologia;
	private String cartaDestinatario;
	private CartaPrepagata cartaPrepagata;
	
	/**
	 * 
	 * 
	 * @param id							,  numero identificativo dell'oggetto. Unico per tutte istanze di tipo OperazionePrepagata;
	 * 
	 * @param data							, data dell'operazione associata alla carta prepagata;
	 * 
	 * @param importo						, importo dell'operazione associata alla carta prepagata;
	 * 
	 * @param tipologia						, tipologia dell'operazione associata alla carta prepagata;
	 * 
	 * @param destinatario					, numero per l'identificazione del beneficiario dell'operazione;
	 * 
	 * @param cartaPrepagata				, oggetto di tipo carta prepagata del cliente che effettua l'operazione;
	 * 
	 * @throws InputValidationException		, eccezione lanciata dal sistema in caso di errore nell'inserimento dei parametri per la creazione di un oggetto di tipo Account.
	 */
	@JsonbCreator 
	public OperazionePrepagata(@JsonbProperty("id") int id, @JsonbProperty("data") Date data, @JsonbProperty("importo") double importo, 
			@JsonbProperty("tipologia") String tipologia, @JsonbProperty("destinatario") String destinatario, 
			@JsonbProperty("carta_prepagata") CartaPrepagata cartaPrepagata) throws InputValidationException {
		
		validazioneParametri(id, data, importo, tipologia, destinatario, cartaPrepagata);
		this.id = id;
		this.data = data;
		this.importo = importo;
		this.tipologia = tipologia;
		this.cartaDestinatario = destinatario;
		this.cartaPrepagata = cartaPrepagata;
	}
	
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
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
	public void setDestinatario(String destinatario) {
		this.cartaDestinatario = destinatario;
	}
	public void setCartaPrepagata(CartaPrepagata cartaPrepagata) {
		this.cartaPrepagata = cartaPrepagata;
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
	public String getDestinatario() {
		return cartaDestinatario;
	}
	public CartaPrepagata getCartaPrepagata() {
		return cartaPrepagata;
	}
	
	public String getTipologia() {
		return tipologia;
	}
	
	private void validazioneParametri(int id, Date data, double importo, String tipologia, String destinatario, CartaPrepagata cartaPrepagata) throws InputValidationException {
		
		if(id < 0)
			throw new InputValidationException("Id operazione carta prepagata");
		
		if (data == null)
			throw new InputValidationException("Data operazione carta prepagata");

		if(importo < 0)
			throw new InputValidationException("Importo operazione carta prepagata");
		
		if(tipologia == null || tipologia.isBlank())
			throw new InputValidationException("Tipologia operazione carta prepagata");
		if(tipologia.length() > 45)
			throw new InputValidationException("Tipologia operazione carta prepagata non valida. Numero massimO caratteri consentiti 45, inseriti: ", tipologia.length());
		if(!tipologia.matches("[\\w,: ]{2,45}")) 
			throw new InputValidationException("Tipologia operazione carta prepagata. Caratteri speciali consentiti (,:)");
		
		if(cartaPrepagata == null)
			throw new InputValidationException("Carta prepagata associata all'operazione");
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
		if (!(obj instanceof OperazionePrepagata))
			return false;
		OperazionePrepagata other = (OperazionePrepagata) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return tipologia + ", effettuato da " + cartaPrepagata.getAccount().getNomeCognome() + 
				"\nid: " + id + ", data: " + data + ", importo: " + importo + ", a " + cartaDestinatario;
	}
	@Override
	public int compareTo(OperazionePrepagata o) {
		return o.getData().compareTo(data);
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
