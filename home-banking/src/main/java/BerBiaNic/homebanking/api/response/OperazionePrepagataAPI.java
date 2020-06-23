package BerBiaNic.homebanking.api.response;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import BerBiaNic.homebanking.dao.DaoOperazionePrepagata;
import BerBiaNic.homebanking.entity.OperazionePrepagata;

@Path("homeBanking/account/opcartaprepagata")
public class OperazionePrepagataAPI {
	private DaoOperazionePrepagata daoOP = new DaoOperazionePrepagata();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchOperazionePrepagata(@QueryParam("id") int id){
		OperazionePrepagata op = null;
		try {
			op = daoOP.getOne(id).get();
		} catch (InterruptedException | ExecutionException e) {

			e.printStackTrace();
		}
		return Response.ok(op).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchAllCartaPrepagata() {
		List<OperazionePrepagata> lista = new ArrayList<OperazionePrepagata>();
		try {
			lista = daoOP.getAll().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Response.ok(lista).build();
	}


}
