package BerBiaNic.homebanking.api;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import BerBiaNic.homebanking.dao.*;
import BerBiaNic.homebanking.entity.Account;
import BerBiaNic.homebanking.entity.Cliente;

@Path("homeBanking/login")
public class Accesso {
	private DaoAccount daoA = new DaoAccount();
	private final Map<Integer, Account> accountsStore = new ConcurrentHashMap<>();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAccount(Account account) {
		Account a = null;
		accountsStore.put(account.getId(), account);
		try {
			a = daoA.insert(account).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Response.ok("L'account \n" + a + "\nè stato registrato nel sistema").build();		
	}
	
	@GET
	@Path("/UserPass")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginUsernamePassword(@QueryParam("username") String username, @QueryParam("password") String password) {
		DaoCliente daoC = new DaoCliente();
		for( Entry<Integer, Account> elem : accountsStore.entrySet() ) {
			Account acc = elem.getValue();
			String user = acc.getUsername(), pass = acc.getPassword();
			if( username.equals(user) && password.equals(pass) ) {
				Cliente c = null;
				try {
					c =  daoC.getOne(acc.getCliente().getCodiceFiscale()).get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
				return Response.ok("Il cliente\n " + c + "\nha appena effettuato l'accesso").build();
			}
		}
		return Response.ok("Le credenziali di accesso non sono state trovate!").build();
	}
	
	@GET
	@Path("/loginFingerprint")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginFingerprint(long fingerprint) {
		DaoCliente daoC = new DaoCliente();
		for( Entry<Integer, Account> elem : accountsStore.entrySet() ) {
			Account acc = elem.getValue();
			long fingerP = acc.getImprontaDigitale();
			if( fingerprint == fingerP ) {
				Cliente c = null;
				try {
					c = daoC.getOne(acc.getCliente().getCodiceFiscale()).get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
				return Response.ok("Il cliente\n " + c + "\nha appena effettuato l'accesso").build();
			}
		}
		return Response.ok("Le impronte digitali non sono state rilevate, riprovare!").build();
	}
	
	
}