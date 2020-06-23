package BerBiaNic.homebanking.api.utilities;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;

import BerBiaNic.homebanking.entity.CartaDiDebito;

public class DatiBonificoCD {
	private CartaDiDebito cartaProprietario, cartaDestinatario;
	private double importo;

	@JsonbCreator
	public DatiBonificoCD(@JsonbProperty("carta_proprietario") CartaDiDebito cartaProprietario, @JsonbProperty("carta_destinatario") CartaDiDebito cartaDestinatario,
			@JsonbProperty("importo") double importo) {
		this.cartaProprietario = cartaProprietario;
		this.cartaDestinatario = cartaDestinatario;
		this.importo = importo;
	}


	public void setCartaProprietario(CartaDiDebito cartaProprietario) {
		this.cartaProprietario = cartaProprietario;
	}
	public void setCartaDestinatario(CartaDiDebito cartaDestinatario) {
		this.cartaDestinatario = cartaDestinatario;
	}
	public void setImporto(double importo) {
		this.importo = importo;
	}
	public CartaDiDebito getCartaProprietario() {
		return cartaProprietario;
	}
	public CartaDiDebito getCartaDestinatario() {
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
