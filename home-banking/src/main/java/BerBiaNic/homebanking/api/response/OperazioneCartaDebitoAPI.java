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

import BerBiaNic.homebanking.dao.DaoOperazioneDebito;
import BerBiaNic.homebanking.entity.OperazioneCartaDebito;

@Path("homeBanking/account/opcartadebito")
public class OperazioneCartaDebitoAPI {
	private DaoOperazioneDebito daoOP = new DaoOperazioneDebito();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchOperazioneDebito(@QueryParam("id") int id){
		OperazioneCartaDebito op = null;
		try {
			op = daoOP.getOne(id).get();
		} catch (InterruptedException | ExecutionException e) {

			e.printStackTrace();
		}
		return Response.ok(op).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchAllOperazioneDebito() {
		List<OperazioneCartaDebito> lista = new ArrayList<OperazioneCartaDebito>();
		try {
			lista = daoOP.getAll().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Response.ok(lista).build();
	}

	
}
