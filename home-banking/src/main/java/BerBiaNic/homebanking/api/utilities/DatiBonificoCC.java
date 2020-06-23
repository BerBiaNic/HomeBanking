package BerBiaNic.homebanking.api.utilities;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;

public class DatiBonificoCC {
	private String ibanProprietario, ibanDestinatario;
	private double importo;

	@JsonbCreator
	public DatiBonificoCC(@JsonbProperty("iban_proprietario") String ibanProprietario, @JsonbProperty("iban_proprietario") String ibanDestinatario,
			@JsonbProperty("importo") double importo) {
		this.ibanProprietario = ibanProprietario;
		this.ibanDestinatario = ibanDestinatario;
		this.importo = importo;
	}


	public void setIbanProprietario(String ibanProprietario) {
		this.ibanProprietario = ibanProprietario;
	}
	public void setCartaDestinatario(String cartaDestinatario) {
		this.ibanDestinatario = cartaDestinatario;
	}
	public void setImporto(double importo) {
		this.importo = importo;
	}
	public String getIbanProprietario() {
		return ibanProprietario;
	}
	public String getIbanDestinatario() {
		return ibanDestinatario;
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
