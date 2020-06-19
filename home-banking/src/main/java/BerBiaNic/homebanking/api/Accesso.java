package BerBiaNic.homebanking.api;
import java.util.concurrent.ExecutionException;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import BerBiaNic.homebanking.dao.*;
import BerBiaNic.homebanking.entity.Account;

@Path("homeBanking/login")
public class Accesso {
	private DaoAccount daoA = new DaoAccount();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAccount(Account account) {
		try {
			daoA.insert(account).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Response.ok().build();		
	}
	
	@GET
	@Path("/UserPass")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginUsernamePassword(Credenziali dati) {
		DaoAccount daoA = new DaoAccount();
		Account a = null;
		try {
			a = daoA.getOne(dati.getUsername(), dati.getPassword()).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Response.ok(a).build();
	}
	
	@GET
	@Path("/loginFingerprint")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginFingerprint(Impronta impronta) {
		DaoAccount daoA = new DaoAccount();
		Account a = null;
		try {
			a = daoA.getOne(impronta.getImpronta()).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Response.ok(a).build();
	}
	
}