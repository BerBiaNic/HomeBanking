package BerBiaNic.homebanking.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("user")
public class Registrazione {

	@GET
	public Response add() {
		
		return Response.ok("ciao").build();
	}
}
