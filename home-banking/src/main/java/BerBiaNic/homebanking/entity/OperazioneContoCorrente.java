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

public class OperazioneContoCorrente implements Comparable<OperazioneContoCorrente> {

	private int id;
	private Date data;
	private double importo;
	private String tipologia;
	private ContoCorrente conto_corrente_proprietario;
	private String conto_corrente_destinatario;

	@JsonbCreator
	public OperazioneContoCorrente(@JsonbProperty("id") int id, @JsonbProperty("data") Date data, @JsonbProperty("importo") double importo, 
			@JsonbProperty("tipologia") String tipologia, @JsonbProperty("conto_corrente") ContoCorrente conto_corrente_proprietario,
			String conto_corrente_destinatario) throws InputValidationException {
		
		if(id < 0)
			throw new InputValidationException("Id operazione conto corrente");
		
		if (data == null)
			throw new InputValidationException("Data operazione conto corrente");

		if(importo < 0)
			throw new InputValidationException("Importo operazione conto corrente");
		
		if(tipologia == null || tipologia.isBlank())
			throw new InputValidationException("Tipologia conto corrente");
		if(tipologia.length() > 45)
			throw new InputValidationException("Tipologia conto corrente non valida. Numero massimi caratteri consentiti 45, inseriti: ", tipologia.length());
		if(!tipologia.matches("[\\w,: ]{2,45}")) 
			throw new InputValidationException("Tipologia conto corrente. Caratteri speciali consentiti (,:)");
		
		if(conto_corrente_proprietario == null)
			throw new InputValidationException("Conto corrente proprietario");
		
		if (conto_corrente_destinatario == null || conto_corrente_destinatario.isBlank())
			throw new InputValidationException("Conto corrente destinatario");
		if(conto_corrente_destinatario.length() != 27)
			throw new InputValidationException("Conto corrente destinatario non valido. Caratteri richiesti 27, inseriti: ", conto_corrente_destinatario.length());
		if(!conto_corrente_destinatario.matches("IT+\\d{2}+[a-zA-Z]+\\d{22}"))
			throw new InputValidationException("Formato conto corrente destinatario (esempio inserimento  IT28W8000000292100645211151)");
		
		this.id = id;
		this.data = data;
		this.importo = importo;
		this.tipologia = tipologia;
		this.conto_corrente_proprietario = conto_corrente_proprietario;
		this.conto_corrente_destinatario = conto_corrente_destinatario;
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

	public ContoCorrente getConto_corrente_proprietario() {
		return conto_corrente_proprietario;
	}

	public String getConto_corrente_destinatario() {
		return conto_corrente_destinatario;
	}

	public void setConto_corrente_proprietario(ContoCorrente conto_corrente_proprietario) {
		this.conto_corrente_proprietario = conto_corrente_proprietario;
	}

	public void setConto_corrente_destinatario(String conto_corrente_destinatario) {
		this.conto_corrente_destinatario = conto_corrente_destinatario;
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

	public int hashCode() {
		int hash = 37;
		hash = hash * 37 + id;
		return hash;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof OperazioneContoCorrente))
			return false;
		OperazioneContoCorrente other = (OperazioneContoCorrente) obj;
		return id==other.id;
	}

	@Override
	public String toString() {
		return " --------------------- OPERAZIONE ---------------------\n "
				+ "id = " + id + ", \tdata = " + data + ", \ttipologia = " + tipologia + "\n";
	}

	public int compareTo(OperazioneContoCorrente o) {
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
