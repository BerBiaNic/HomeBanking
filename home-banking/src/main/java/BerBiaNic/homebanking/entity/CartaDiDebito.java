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
public class CartaDiDebito {
	
	private String numero;
	private String iban;
	private Date data_di_scadenza;
	private int cvv;
	private int pin;
	private ContoCorrente conto_corrente;

/**
 * Crea un oggetto di tipo CartaDiDebito.
 * 
 * @param numero						, numero identificativo dell'oggetto. Unico per tutte istanze di tipo CartaDiDebito;
 * 
 * @param iban							, codice per l'identificazione del conto corrente, necessario per eseguire tutte le operazioni;
 * 
 * @param data_di_scadenza				, data di scadenza della carta di debito;
 * 
 * @param cvv							, codice di sicurezza composto da 3 cifre;
 * 
 * @param pin							, codice di sicurezza composto da 6 cifre;
 * 
 * @param conto_corrente				, oggetto  di tipo conto corrente collegato alla carta di debito;
 * 
 * @throws InputValidationException		, eccezione lanciata dal sistema in caso di errore nell'inserimento dei parametri per la creazione di un oggetto di tipo CartaDiDebito.
 */
	@JsonbCreator
	public CartaDiDebito(@JsonbProperty("numero") String numero, @JsonbProperty("iban") String iban, @JsonbProperty("data_di_scadenza") Date data_di_scadenza, 
			@JsonbProperty("cvv") int cvv, @JsonbProperty("pin") int pin, @JsonbProperty("conto_corrente") ContoCorrente conto_corrente) throws InputValidationException {
		
		validazioneParametri(numero, iban, data_di_scadenza, cvv, pin, conto_corrente);
		this.numero = numero.trim();
		this.iban = iban.trim();
		this.data_di_scadenza = data_di_scadenza;
		this.cvv = cvv;
		this.pin = pin;
		this.conto_corrente = conto_corrente;
	}

	public String getNumero() {
		return numero;
	}

	public String getIban() {
		return iban;
	}

	public Date getData_di_scadenza() {
		return data_di_scadenza;
	}

	public int getCvv() {
		return cvv;
	}

	public int getPin() {
		return pin;
	}

	public ContoCorrente getConto_corrente() {
		return conto_corrente;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public void setData_di_scadenza(Date data_di_scadenza) {
		this.data_di_scadenza = data_di_scadenza;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public void setConto_corrente(ContoCorrente conto_corrente) {
		this.conto_corrente = conto_corrente;
	}
	
	private void validazioneParametri(String numero, String iban, Date data_di_scadenza, int cvv, int pin, ContoCorrente conto_corrente) throws InputValidationException {
		if(numero == null || numero.isBlank())
			throw new InputValidationException("Numero carta di debito");
		if(numero.length() > 10)
			throw new InputValidationException("Numero carta di debito non valido. Caratteri richiesti 16, inseriti: ", numero.length());
		if(!numero.matches("[\\d]*")) 
			throw new InputValidationException("Formato numero carta di debito errato.");
		
		if (iban == null || iban.isBlank())
			throw new InputValidationException("IBAN carta di debito");
		if(iban.length() != 27)
			throw new InputValidationException("IBAN carta di debito non valido. Caratteri richiesti 27, inseriti: ", iban.length());
		if(!iban.matches("IT+\\d{2}+[a-zA-Z]+\\d{22}"))
			throw new InputValidationException("Formato IBAN carta di debito (esempio inserimento IT28W8000000292100645211151) .");
		
		if(data_di_scadenza == null)
			throw new InputValidationException("Data di scadenza carta di debito");
		
		if(cvv < 100 || cvv > 999)
			throw new InputValidationException("Codice cvv carta di debito");
		
		if(pin < 100000 || pin > 999999)
			throw new InputValidationException("Codice PIN carta di debito");
		
		if(conto_corrente == null)
			throw new InputValidationException("Conto corrente associato alla carta di debito");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		CartaDiDebito other = (CartaDiDebito) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		}
		return numero.equals(other.numero);
	}
	
	@Override
	public String toString() {
		return " --------------------- CARTA DI DEBITO ---------------------\n "
				+ "numero = " + numero + ", \tiban = " + iban 
				+ ", \tdata di scadenza = " + data_di_scadenza.getDay() + "/" + data_di_scadenza.getMonth() 
				+ ", \tcvv = " + cvv + "\tpin = " + pin + "\n";
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
