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

public class OperazioneCartaDebito implements Comparable<OperazioneCartaDebito> {

	private int id;
	private Date data;
	private double importo;
	private String tipologia;
	private CartaDiDebito carta_proprietario;
	private String carta_beneficiario;

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
			throw new InputValidationException("Id Operazione carta di debito");

		if(data == null)
			throw new InputValidationException("Data operazione carta di debito");

		if(importo < 0)
			throw new InputValidationException("Importo operazione carta di debito");

		if(tipologia == null || tipologia.isBlank())
			throw new InputValidationException("Tipologia operazione carta di debito");
		if(tipologia.length() > 45)
			throw new InputValidationException("Tipologia carta di debito non valida. Numero massimo caratteri consentiti 45, inseriti: ", tipologia.length());
		if(!tipologia.matches("[\\w,: ]{2,45}")) 
			throw new InputValidationException("Tipologia carta di debito. Caratteri speciali consentiti (,:)");
		
		if(carta_proprietario == null)
			throw new InputValidationException("Carta di debito proprietario");
		
		if (carta_beneficiario == null || carta_beneficiario.isBlank())
			throw new InputValidationException("Carta di debito beneficiario");
		if(carta_beneficiario.length() != 27)
			throw new InputValidationException("Carta di debito beneficiario non valida. Caratteri richiesti 27, inseriti: ", carta_beneficiario.length());
		if(!carta_beneficiario.matches("IT+\\d{2}+[a-zA-Z]+\\d{22}"))
			throw new InputValidationException("Formato carta di debito beneficiario non valido (esempio inserimento  IT28W8000000292100645211151)");
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
