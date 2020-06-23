package BerBiaNic.homebanking.api.response;

import java.time.LocalDate;
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
import javax.ws.rs.core.Response.Status;

import BerBiaNic.homebanking.api.utilities.DatiBonificoCP;
import BerBiaNic.homebanking.api.utilities.NumeroCarta;
import BerBiaNic.homebanking.dao.*;
import BerBiaNic.homebanking.entity.*;
import BerBiaNic.homebanking.exceptions.EmptyResultSet;
import BerBiaNic.homebanking.exceptions.InputValidationException;

@Path("homeBanking/account/cartaprepagata")
public class CartaPrepagataAPI {
	private DaoCartaPrepagata daoCP = new DaoCartaPrepagata();
	private DaoOperazionePrepagata daoOP = new DaoOperazionePrepagata();
	private static int id = 0;
	
	
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
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response bonifico(DatiBonificoCP datiB) throws InputValidationException{
		if( datiB == null ) {
			throw new InputValidationException("Dati bonifico", Response.Status.METHOD_NOT_ALLOWED);
		}
		
		CartaPrepagata cpPro = null, cpDest = null;
		try {
			cpPro = daoCP.getOne(datiB.getCartaProprietario().getNumero()).get();
			cpDest = daoCP.getOne(datiB.getCartaDestinatario().getNumero()).get();
			if( cpPro == null )
				throw new EmptyResultSet("Carta proprietario non presente!", Response.Status.NOT_FOUND);
			if( cpDest == null )
				throw new EmptyResultSet("Carta destinatario non presente!", Response.Status.NOT_FOUND);			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (EmptyResultSet e) {
			e.printStackTrace();
		}
		
		
		double saldoPro = datiB.getCartaProprietario().getSaldoDisponibile();
		double saldoDest = datiB.getCartaDestinatario().getSaldoDisponibile();
		double importo = datiB.getImporto();
		
		if( saldoPro - importo < 0 ) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		
		cpPro.setSaldoDisponibile(saldoPro - importo);
		cpPro.setSaldoContabile(saldoPro - importo);
		cpDest.setSaldoDisponibile(saldoDest + importo);
		cpDest.setSaldoContabile(saldoDest + importo);
		daoCP.update(cpPro);
		daoCP.update(cpDest);
		java.sql.Date dataO = java.sql.Date.valueOf(LocalDate.now());
		OperazionePrepagata op = new OperazionePrepagata(id, dataO, importo, "Bonifico", cpPro, cpDest.getNumero());
		id++;
		daoOP.insert(op);
		return Response.ok(cpPro).build();	
	}

}
