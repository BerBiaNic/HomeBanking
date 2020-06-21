package BerBiaNic.homebanking.api;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;

public class NumeroCarta {
	private String numero;
	
	@JsonbCreator
	public NumeroCarta(@JsonbProperty("numero") String numero) throws Exception {
		if(!numero.matches("[\\d]{16}"))  
			throw new Exception("Formato numero carta prepagata (esempio inserimento 1234569874521456).");
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) throws Exception {
		if(!numero.matches("[\\d]{16}"))  
			throw new Exception("Formato numero carta prepagata (esempio inserimento 1234569874521456).");
		this.numero = numero;
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
