package BerBiaNic.homebanking.api.utilities;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;

import BerBiaNic.homebanking.entity.*;

public class DatiGirocontoCC {
	private CartaPrepagata carta;
	private String iban;
	private double importo;
	
	@JsonbCreator
	public DatiGirocontoCC(@JsonbProperty("iban") String iban, @JsonbProperty("carta") CartaPrepagata carta, @JsonbProperty("importo") double importo) {
		this.carta = carta;
		this.iban = iban;
		this.importo = importo;
	}


	public void setCarta(CartaPrepagata carta) {
		this.carta = carta;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public void setImporto(double importo) {
		this.importo = importo;
	}
	public CartaPrepagata getCartaProprietario() {
		return carta;
	}
	public String getIban() {
		return iban;
	}
	public double getImporto() {
		return importo;
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
