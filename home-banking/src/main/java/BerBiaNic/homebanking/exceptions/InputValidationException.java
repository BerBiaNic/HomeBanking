package BerBiaNic.homebanking.exceptions;

public class InputValidationException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7460722154807850220L;
	
	private String errorMessage;
	
	public InputValidationException (String message) {
		this.errorMessage = "Parametro inserito non valido: " + message;
	}
	
	public InputValidationException (String message, int length) {
		this.errorMessage = message + length;
	}
	
	@Override
	public String toString() {
		return this.errorMessage;
	}
}
