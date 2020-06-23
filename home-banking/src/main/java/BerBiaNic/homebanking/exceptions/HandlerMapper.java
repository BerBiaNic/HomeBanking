package BerBiaNic.homebanking.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.Errors.ErrorMessage;

@Provider
public class HandlerMapper implements ExceptionMapper<InputValidationException>{

	@Override
	public Response toResponse(InputValidationException exception) {
		return Response.status(exception.getStatus().getStatusCode(), exception.getMessage()).build();
	}

}
