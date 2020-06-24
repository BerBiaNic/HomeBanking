package BerBiaNic.homebanking.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
	
	@POST
	@Path("/one")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchCartaPrepagata(NumeroCarta nc){
		if( nc == null ) {
			Response.status(400);
			return Response.ok("Numero carta null.").build();
		}
		CartaPrepagata cp = null;
		try {
			cp = daoCP.getOne(nc.getNumero()).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Response.ok(cp).build();
	}
	
	@GET
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
	
//	@PUT
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response bonifico(NumeroCarta ncProprietario, NumeroCarta ncDestinatario, double importo){
//		if( ncProprietario == null ) {
//			Response.status(403);
//			return Response.ok("Numero carta proprietario non valido!").build();
//		}
//		if( ncDestinatario == null ) {
//			throw new Exception("Parametro carta destinatario non valido!");
//		}
//		if( importo < 0.0 ) {
//			throw new Exception("Importo minore di zero!");
//		}
//		CartaPrepagata cpPro = null, cpDest = null;
//		try {
//			cpPro = daoCP.getOne(ncProprietario.getNumero()).get();
//			cpDest = daoCP.getOne(ncDestinatario.getNumero()).get();
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}
//		double saldoPro = cpPro.getSaldoDisponibile();
//		double saldoDest = cpDest.getSaldoDisponibile();
//		if( saldoPro - importo < 0 ) {
//			throw new Exception("Mancanza di fondi!");
//		}
//		cpPro.setSaldoDisponibile(saldoPro - importo);
//		cpPro.setSaldoContabile(saldoPro - importo);
//		cpDest.setSaldoDisponibile(saldoDest + importo);
//		cpDest.setSaldoContabile(saldoDest + importo);
//		daoCP.update(cpPro);
//		return Response.ok(cpPro).build();	
//	}

}
