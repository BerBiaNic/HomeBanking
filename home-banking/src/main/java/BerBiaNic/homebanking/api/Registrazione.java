package BerBiaNic.homebanking.api;

import java.util.concurrent.ExecutionException;

import javax.ws.rs.*;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import BerBiaNic.homebanking.dao.DaoCliente;
import BerBiaNic.homebanking.entity.*;

@Path("homeBanking/signup")
public class Registrazione {
	private DaoCliente daoC = new DaoCliente();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createClient(Cliente cliente) {
		try {
			daoC.insert(cliente).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Response.ok().build();
	}
}
