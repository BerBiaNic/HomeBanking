package BerBiaNic.homebanking.api.response;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import BerBiaNic.homebanking.api.utilities.Credenziali;
import BerBiaNic.homebanking.api.utilities.Impronta;
import BerBiaNic.homebanking.dao.*;
import BerBiaNic.homebanking.entity.Account;
import BerBiaNic.homebanking.exceptions.InputValidationException;

@Path("homeBanking/account")
public class AccessoAPI {
	private DaoAccount daoA = new DaoAccount();

	@POST
	@Path("/signup")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAccount(Account account) throws InputValidationException {
		if( account == null )
			throw new InputValidationException("Account", Status.METHOD_NOT_ALLOWED);
		daoA.insert(account);
		return Response.ok().build();	
	}
	
	@POST
	@Path("/login/userpass")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginUsernamePassword(Credenziali dati) throws InputValidationException {
		if( dati == null )
			throw new InputValidationException("Credenziali", Status.METHOD_NOT_ALLOWED);
		daoA.getOne(dati.getUsername(), dati.getPassword());
		return Response.ok().build();
	}
	
	@POST
	@Path("/login/fingerprint")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginFingerprint(Impronta impronta) throws InputValidationException {
		if( impronta == null )
			throw new InputValidationException("Impronta digitale", Status.METHOD_NOT_ALLOWED);
		daoA.getOne(impronta.getImpronta());
		return Response.ok().build();
	}
}