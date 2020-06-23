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

import BerBiaNic.homebanking.dao.DaoOperazioneContoCorrente;
import BerBiaNic.homebanking.entity.OperazioneContoCorrente;

@Path("homeBanking/account/opcontocorrente")
public class OperazioneContoCorrenteAPI {
	private DaoOperazioneContoCorrente daoOCC = new DaoOperazioneContoCorrente();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchOperazioneContoCorrente(@QueryParam("id") int id){
		OperazioneContoCorrente op = null;
		try {
			op = daoOCC.getOne(id).get();
		} catch (InterruptedException | ExecutionException e) {

			e.printStackTrace();
		}
		return Response.ok(op).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchAllOperazioneContoCorrente() {
		List<OperazioneContoCorrente> lista = new ArrayList<OperazioneContoCorrente>();
		try {
			lista = daoOCC.getAll().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Response.ok(lista).build();
	}
}
