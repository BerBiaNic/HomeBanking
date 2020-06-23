package BerBiaNic.homebanking.api.utilities;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;

import BerBiaNic.homebanking.entity.CartaPrepagata;

public class DatiBonificoCP {
	private CartaPrepagata cartaProprietario, cartaDestinatario;
	private double importo;
	
	@JsonbCreator
	public DatiBonificoCP(@JsonbProperty("carta_proprietario") CartaPrepagata cartaProprietario, @JsonbProperty("carta_destinatario") CartaPrepagata cartaDestinatario,
			            @JsonbProperty("importo") double importo) {
		this.cartaProprietario = cartaProprietario;
		this.cartaDestinatario = cartaDestinatario;
		this.importo = importo;
	}


	public void setCartaProprietario(CartaPrepagata cartaProprietario) {
		this.cartaProprietario = cartaProprietario;
	}
	public void setCartaDestinatario(CartaPrepagata cartaDestinatario) {
		this.cartaDestinatario = cartaDestinatario;
	}
	public void setImporto(double importo) {
		this.importo = importo;
	}
	public CartaPrepagata getCartaProprietario() {
		return cartaProprietario;
	}
	public CartaPrepagata getCartaDestinatario() {
		return cartaDestinatario;
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
