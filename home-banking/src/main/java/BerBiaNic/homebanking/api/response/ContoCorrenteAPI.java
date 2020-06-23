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

import BerBiaNic.homebanking.api.utilities.DatiBonificoCC;
import BerBiaNic.homebanking.api.utilities.DatiGirocontoCC;
import BerBiaNic.homebanking.api.utilities.IbanContoCorrente;
import BerBiaNic.homebanking.dao.*;
import BerBiaNic.homebanking.entity.*;
import BerBiaNic.homebanking.exceptions.*;

@Path("homeBanking/account/contocorrente")
public class ContoCorrenteAPI {
	
	private DaoContoCorrente daoCC = new DaoContoCorrente();
	private DaoCartaPrepagata daoCP = new DaoCartaPrepagata();
	private DaoOperazioneContoCorrente daoOCC = new DaoOperazioneContoCorrente();
	private static int id = 0;


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addContoCorrente(ContoCorrente contoC) {
		daoCC.insert(contoC);
		return Response.ok().build();
	}

	@POST
	@Path("/one")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchContoCorrente(IbanContoCorrente icc){
		if( icc == null ) {
			Response.status(400);
			return Response.ok("Numero conto corrente null.").build();
		}
		ContoCorrente cc = null;
		try {
			cc = daoCC.getOne(icc.getIban()).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Response.ok(cc).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchAllContoCorrente() {
		List<ContoCorrente> lista = new ArrayList<ContoCorrente>();
		try {
			lista = daoCC.getAll().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Response.ok(lista).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response bonifico(DatiBonificoCC datiB) throws InputValidationException{
		if( datiB == null ) {
			throw new InputValidationException("Dati bonifico", Response.Status.METHOD_NOT_ALLOWED);
		}

		ContoCorrente ccPro = null, ccDest = null;
		try {
			ccPro = daoCC.getOne(datiB.getIbanProprietario()).get();
			ccDest = daoCC.getOne(datiB.getIbanDestinatario()).get();
			if( ccPro == null )
				throw new EmptyResultSet("Conto Corrente proprietario non presente!", Response.Status.NOT_FOUND);
			if( ccDest == null )
				throw new EmptyResultSet("Conto Corrente destinatario non presente!", Response.Status.NOT_FOUND);			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (EmptyResultSet e) {
			e.printStackTrace();
		}
		
		double saldoPro = ccPro.getSaldoDisponibile();
		double saldoDest = ccDest.getSaldoDisponibile();
		double importo = datiB.getImporto();

		if( saldoPro - importo < 0 ) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}

		ccPro.setSaldoDisponibile(saldoPro - importo);
		ccPro.setSaldoContabile(saldoPro - importo);
		ccDest.setSaldoDisponibile(saldoDest + importo);
		ccDest.setSaldoContabile(saldoDest + importo);
		daoCC.update(ccPro);
		daoCC.update(ccDest);
		java.sql.Date dataO = java.sql.Date.valueOf(LocalDate.now());
		OperazioneContoCorrente occ = new OperazioneContoCorrente(id, dataO, importo, "Bonifico", ccPro, ccDest.getIban());
		id++;
		daoOCC.insert(occ);
		return Response.ok().build();	
	}
	
	
	@PUT
	@Path("/giroconto")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response giroconto(DatiGirocontoCC datiG) throws InputValidationException {
		if( datiG == null )
			throw new InputValidationException("Dati giroconto", Response.Status.METHOD_NOT_ALLOWED);
		ContoCorrente cc = null;
		CartaPrepagata cp = null;
		try {
			cc = daoCC.getOne(datiG.getIban()).get();
			cp = daoCP.getOne(datiG.getCartaProprietario().getNumero()).get();
			if( cc == null )
				throw new EmptyResultSet("Conto Corrente non presente!", Response.Status.NOT_FOUND);
			if( cp == null )
				throw new EmptyResultSet("Carta prepagata non presente!", Response.Status.NOT_FOUND);			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (EmptyResultSet e) {
			e.printStackTrace();
		}
		
		double saldoConto = cc.getSaldoDisponibile();
		double saldoCarta = cp.getSaldoDisponibile();
		double importo = datiG.getImporto();

		if( saldoConto - importo < 0 ) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}

		cc.setSaldoDisponibile(saldoConto - importo);
		cc.setSaldoContabile(saldoConto - importo);
		cp.setSaldoDisponibile(saldoCarta + importo);
		cp.setSaldoContabile(saldoCarta + importo);
		daoCC.update(cc);
		daoCP.update(cp);
		java.sql.Date dataO = java.sql.Date.valueOf(LocalDate.now());
		OperazioneContoCorrente occ = new OperazioneContoCorrente(id, dataO, importo, "Giroconto", cc, cp.getNumero());
		id++;
		daoOCC.insert(occ);
		return Response.ok().build();
	}


}
