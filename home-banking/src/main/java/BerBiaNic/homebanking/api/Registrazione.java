package BerBiaNic.homebanking.api;

import java.util.concurrent.ExecutionException;

import javax.ws.rs.*;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import BerBiaNic.homebanking.dao.DaoCliente;
import BerBiaNic.homebanking.entity.*;

@Path("homeBanking/logup")
public class Registrazione {
	private DaoCliente daoC = new DaoCliente();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response printClient(String codiceFiscale) {
		Cliente client = (Cliente) daoC.getOne(codiceFiscale);
		return Response.ok(client).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createClient(Cliente cliente) {
		Cliente c = null;
		try {
			c = daoC.insert(cliente).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Response.ok("Il cliente \n " + c + "\nè stato inserito nel sistema!").build();
	}	
	
}
