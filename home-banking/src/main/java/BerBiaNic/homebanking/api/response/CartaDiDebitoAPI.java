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

import BerBiaNic.homebanking.api.utilities.DatiBonificoCD;
import BerBiaNic.homebanking.api.utilities.NumeroCarta;
import BerBiaNic.homebanking.dao.DaoCartaDiDebito;
import BerBiaNic.homebanking.dao.DaoOperazioneDebito;
import BerBiaNic.homebanking.entity.CartaDiDebito;
import BerBiaNic.homebanking.entity.OperazioneCartaDebito;
import BerBiaNic.homebanking.exceptions.EmptyResultSet;
import BerBiaNic.homebanking.exceptions.InputValidationException;

@Path("homeBanking/account/cartadidebito")
public class CartaDiDebitoAPI {
	private DaoCartaDiDebito daoCD = new DaoCartaDiDebito();
	private DaoOperazioneDebito daoOD = new DaoOperazioneDebito();
	private static int id = 0;


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCartaDebito(CartaDiDebito cartaD) {
		daoCD.insert(cartaD);
		return Response.ok().build();
	}

	@POST
	@Path("/one")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchCartaDebito(NumeroCarta nc){
		if( nc == null ) {
			Response.status(400);
			return Response.ok("Numero carta null.").build();
		}
		CartaDiDebito cd = null;
		try {
			cd = daoCD.getOne(nc.getNumero()).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Response.ok(cd).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchAllCartaDebito() {
		List<CartaDiDebito> lista = new ArrayList<CartaDiDebito>();
		try {
			lista = daoCD.getAll().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Response.ok(lista).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response bonifico(DatiBonificoCD datiB) throws InputValidationException{
		if( datiB == null ) {
			throw new InputValidationException("Dati bonifico", Response.Status.METHOD_NOT_ALLOWED);
		}

		CartaDiDebito cddPro = null, cddDest = null;
		try {
			cddPro = daoCD.getOne(datiB.getCartaProprietario().getNumero()).get();
			cddDest = daoCD.getOne(datiB.getCartaDestinatario().getNumero()).get();
			if( cddPro == null )
				throw new EmptyResultSet("Carta proprietario non presente!", Response.Status.NOT_FOUND);
			if( cddDest == null )
				throw new EmptyResultSet("Carta destinatario non presente!", Response.Status.NOT_FOUND);			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (EmptyResultSet e) {
			e.printStackTrace();
		}


		double saldoPro = datiB.getCartaProprietario().getConto_corrente().getSaldoDisponibile();
		double saldoDest = datiB.getCartaDestinatario().getConto_corrente().getSaldoDisponibile();
		double importo = datiB.getImporto();

		if( saldoPro - importo < 0 ) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}

		cddPro.getConto_corrente().setSaldoDisponibile(saldoPro - importo);
		cddPro.getConto_corrente().setSaldoContabile(saldoPro - importo);
		cddDest.getConto_corrente().setSaldoDisponibile(saldoDest + importo);
		cddDest.getConto_corrente().setSaldoContabile(saldoDest + importo);
		daoCD.update(cddPro);
		daoCD.update(cddDest);
		java.sql.Date dataO = java.sql.Date.valueOf(LocalDate.now());
		OperazioneCartaDebito op = new OperazioneCartaDebito(id, dataO, importo, "Bonifico", cddPro, cddDest.getNumero());
		id++;
		daoOD.insert(op);
		return Response.ok(cddPro).build();	
	}
}
