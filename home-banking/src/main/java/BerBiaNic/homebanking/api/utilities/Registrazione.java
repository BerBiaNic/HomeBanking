package BerBiaNic.homebanking.api.utilities;


import javax.ws.rs.*;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import BerBiaNic.homebanking.dao.DaoCliente;
import BerBiaNic.homebanking.entity.*;
import BerBiaNic.homebanking.exceptions.InputValidationException;

@Path("homeBanking/cliente/signup")
public class Registrazione {
	private DaoCliente daoC = new DaoCliente();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createClient(Cliente cliente) throws InputValidationException {
		if( cliente == null )
			throw new InputValidationException("cliente", Response.Status.METHOD_NOT_ALLOWED);
		daoC.insert(cliente);
		return Response.ok().build();
	}
}
