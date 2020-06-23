package BerBiaNic.homebanking.exceptions;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;
import javax.ws.rs.core.Response;

public class InputValidationException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7460722154807850220L;
	
	private String errorMessage;
	private Response.Status status;
	
	@JsonbCreator
	public InputValidationException (@JsonbProperty("message") String message, @JsonbProperty("status") Response.Status status) {
		this.errorMessage = "Parametro inserito non valido: " + message;
		this.status = status;
	}
	
	@Override
	public String getMessage() {
		return errorMessage;
	}
	
	public Response.Status getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return this.errorMessage;
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
