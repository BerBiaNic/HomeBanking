package BerBiaNic.homebanking.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import BerBiaNic.homebanking.dao.*;
import BerBiaNic.homebanking.entity.*;

@Path("homeBanking/account/cartaprepagata")
public class CartaPrepagataAPI {
	private DaoCartaPrepagata daoCP = new DaoCartaPrepagata();
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCartaPrepagata(CartaPrepagata cartaP) {
		daoCP.insert(cartaP);
		return Response.ok().build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchCartaPrepagata(String numero) {
		CartaPrepagata cp = null;
		try {
			cp = daoCP.getOne(numero).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Response.ok(cp).build();
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchAllCartaPrepagata() {
		List<CartaPrepagata> lista = new ArrayList<CartaPrepagata>();
		try {
			 lista = daoCP.getAll().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Response.ok(lista).build();
	}

}
