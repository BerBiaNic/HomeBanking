package BerBiaNic.homebanking.api.response;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import BerBiaNic.homebanking.api.utilities.Credenziali;
import BerBiaNic.homebanking.api.utilities.Impronta;
import BerBiaNic.homebanking.dao.*;
import BerBiaNic.homebanking.entity.Account;

@Path("homeBanking/account")
public class AccessoAPI {
	private DaoAccount daoA = new DaoAccount();

	@POST
	@Path("/signup")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAccount(Account account) {
		daoA.insert(account);
		return Response.ok().build();	
	}
	
	@POST
	@Path("/login/userpass")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginUsernamePassword(Credenziali dati) {
		daoA.getOne(dati.getUsername(), dati.getPassword());
		return Response.ok().build();
	}
	
	@POST
	@Path("/login/fingerprint")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginFingerprint(Impronta impronta) {
		daoA.getOne(impronta.getImpronta());
		return Response.ok().build();
	}
}